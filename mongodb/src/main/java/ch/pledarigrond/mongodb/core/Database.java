/*******************************************************************************
 * Copyright 2013 Sprachliche Informationsverarbeitung, University of Cologne
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package ch.pledarigrond.mongodb.core;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.mongodb.exceptions.InvalidEntryException;
import ch.pledarigrond.mongodb.model.DatabaseStatistics;
import ch.pledarigrond.mongodb.util.MongoHelper;
import ch.pledarigrond.mongodb.util.Validator;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Indexes;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

	@Autowired
	private PgEnvironment pgEnvironment;

	private static final String ENTRIES = "entries";
	private static final String QUERY_VERSION_CREATOR = LemmaVersion.CREATOR;
	private static final String QUERY_VERSION_ROLE = LemmaVersion.CREATOR_ROLE;
	private static final String QUERY_VERSION_VERIFICATION = LemmaVersion.VERIFICATION;
	private static final String QUERY_VERSION_IP = LemmaVersion.IP_ADDRESS;
	private static final String QUERY_VERSION_TIMESTAMP = LemmaVersion.TIMESTAMP;
	private static final String QUERY_VERSION_STATE = LemmaVersion.STATUS;
	private static final String QUERY_VERSION_VERIFIER = LemmaVersion.VERIFIER;
	private static final String QUERY_VERSION_AUTOMATIC_CHANGE = LemmaVersion.AUTOMATIC_CHANGE;

	private static final Map<String, Database> instances = new HashMap<>();

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private MongoCollection<Document> entryCollection;

	private final String locale;



	Database(String locale) throws UnknownHostException, DatabaseException {
		this.locale = locale;
		entryCollection = MongoHelper.getDB(pgEnvironment, this.locale).getCollection(ENTRIES);
		long entries = entryCollection.countDocuments();
		logger.info("Connected to entries-collection containing " + entries + " items.");
		createIndex();
	}

	private void createIndex() {
		entryCollection.createIndex(Indexes.ascending(LexEntry.VERSIONS + "." + QUERY_VERSION_CREATOR));
		entryCollection.createIndex(Indexes.ascending(LexEntry.VERSIONS + "." + QUERY_VERSION_IP));
		entryCollection.createIndex(Indexes.ascending(LexEntry.VERSIONS + "." + QUERY_VERSION_ROLE));
		entryCollection.createIndex(Indexes.ascending(LexEntry.VERSIONS + "." + QUERY_VERSION_STATE));
		entryCollection.createIndex(Indexes.ascending(LexEntry.VERSIONS + "." + QUERY_VERSION_TIMESTAMP));
		entryCollection.createIndex(Indexes.ascending(LexEntry.VERSIONS + "." + QUERY_VERSION_VERIFICATION));
		entryCollection.createIndex(Indexes.ascending(LexEntry.VERSIONS + "." + QUERY_VERSION_VERIFIER));
		logger.info("DB indices have been created.");
	}

	public static synchronized Database getInstance(String dbName) throws NoDatabaseAvailableException {
		try {
			Database instance = instances.get(dbName);
			if (instance == null) {
				instance = new Database(dbName);
				instances.put(dbName, instance);
			}
			return instance;
		} catch (UnknownHostException | DatabaseException e) {
			throw new NoDatabaseAvailableException("No Database Available!", e);
		}
	}

	private String toLogString(LemmaVersion lemma) {
		return lemma.getLemmaValues().get("DStichwort") + " ⇔ " + lemma.getLemmaValues().get("RStichwort");
	}

	public void insert(final LexEntry entry) throws InvalidEntryException {
		Validator.validate(entry);
		Document document = new Document(Converter.convertLexEntry(entry));
		entryCollection.insertOne(document);
		ObjectId id = (ObjectId) document.get("_id");
		entry.setId(id.toString());
		logger.info("INSERTED: {}, entryId {}", toLogString(entry.getVersionHistory().get(0)), entry.getId());
	}




	/**
	 * <strong>For unit tests only!</strong> This method drops the entire collection
	 * of entries and creates a new one.
	 */
	public void deleteAllEntries() {
		logger.warn("Dropping database!");
		entryCollection.drop();
		try {
			entryCollection = MongoHelper.getDB(pgEnvironment, this.locale).getCollection(ENTRIES);
			createIndex();
		} catch (UnknownHostException | DatabaseException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isEmpty() {
		return entryCollection.countDocuments() == 0;
	}

	public DatabaseStatistics getStatistics() {
		logger.info("getStatistics called...");

		DatabaseStatistics stats = new DatabaseStatistics();
		stats.setNumberOfEntries((int) entryCollection.countDocuments());
		Map<LemmaVersion.Verification, Integer> count = new HashMap<>();
		MongoCursor<Document> cursor = entryCollection.find().iterator();
		int entryCount = 0, lemmaCount = 0;
		LemmaVersion.Verification[] values = LemmaVersion.Verification.values();
		for (LemmaVersion.Verification verification : values) {
			count.put(verification, 0);
		}
		count.put(null, 0);
		while (cursor.hasNext()) {
			LexEntry entry = Converter.convertToLexEntry(new BasicDBObject(cursor.next()));
			List<LemmaVersion> history = entry.getVersionHistory();
			entryCount++;
			lemmaCount += history.size();
			for (LemmaVersion lemma : history) {
				Integer old = count.get(lemma.getVerification());
				count.put(lemma.getVerification(), old + 1);
			}
		}
		stats.setNumberOfApproved(count.get(LemmaVersion.Verification.ACCEPTED));
		stats.setNumberOfSuggestions(count.get(LemmaVersion.Verification.UNVERIFIED));
		stats.setNumberOfDeleted(count.get(LemmaVersion.Verification.REJECTED));
		stats.setNumberOfOutdated(count.get(LemmaVersion.Verification.OUTDATED));
		stats.setNumberOfUndefined(count.get(null));
		stats.setNumberOfEntries(entryCount);
		stats.setNumberOfLemmata(lemmaCount);
		
		logger.info("getStatistics finished.");
		
		return stats;
	}

	public MongoCursor<Document> getAll() {
		return entryCollection.find().iterator();
	}
}
