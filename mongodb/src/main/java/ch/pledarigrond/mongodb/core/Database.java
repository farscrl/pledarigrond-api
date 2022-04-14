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
import ch.pledarigrond.common.data.common.EditorRole;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.mongodb.exceptions.InvalidEntryException;
import ch.pledarigrond.mongodb.model.DatabaseStatistics;
import ch.pledarigrond.mongodb.util.MongoHelper;
import ch.pledarigrond.mongodb.util.Validator;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.*;
import java.io.*;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static com.mongodb.client.model.Filters.eq;

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

	private static final Map<String, Database> instances = new HashMap<>();

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private MongoCollection<Document> entryCollection;

	private final String locale;

	// private LemmaDescription description = Configuration.getInstance().getLemmaDescription();

	private final boolean debugging;

	Database(String locale) throws UnknownHostException, DatabaseException {
		this.locale = locale;
		debugging = logger.isDebugEnabled();
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
		// TODO: re-implement
		/*
		String first = description.toUnescapedString(lemma, UseCase.RESULT_LIST, true);
		String second = description.toUnescapedString(lemma, UseCase.RESULT_LIST, false);
		if (first.length() > 15) {
			first = first.substring(0, 15) + "...";
		}
		if (second.length() > 15) {
			second = second.substring(0, 15) + "...";
		}
		return first + " ⇔ " + second;
		*/
		return "TODO";
	}

	public void insert(final LexEntry entry) throws InvalidEntryException {
		Validator.validate(entry);
		Document document = new Document(Converter.convertLexEntry(entry));
		entryCollection.insertOne(document);
		ObjectId id = (ObjectId) document.get("_id");
		entry.setId(id.toString());
		logger.info("INSERTED: {}, entryId {}", toLogString(entry.getVersionHistory().get(0)), entry.getId());
	}

	public void insertBatch(final List<Document> entries) throws InvalidEntryException {
		if (entries == null) {
			throw new IllegalArgumentException("Parameter must not be null!");
		}
		if (entries.isEmpty()) {
			return;
		}
		long s = System.nanoTime();
		entryCollection.insertMany(entries);
		long e = System.nanoTime();
		logger.info("Batch insert of " + entries.size() + " entries completed in " + (e - s) / 1000000 + " ms.");
	}

	public Iterator<LexEntry> getEntries() {
		final MongoCursor<Document> cursor = entryCollection.find().iterator();
		Iterator<LexEntry> entryIterator = new Iterator<LexEntry>() {
			@Override
			public boolean hasNext() {
				if (!cursor.hasNext()) {
					cursor.close();
					return false;
				}
				return cursor.hasNext();
			}

			@Override
			public LexEntry next() {
				return Converter.convertToLexEntry(new BasicDBObject(cursor.next()));
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
			}
		};
		return entryIterator;
	}

	public BasicDBObject getById(final String id) {
		return new BasicDBObject(
			Objects.requireNonNull(
				entryCollection.find(eq("_id", new ObjectId(id))).first()
			)
		);
	}

	public void delete(LexEntry entry) throws InvalidEntryException {
		if (entry == null || entry.getId() == null) {
			throw new InvalidEntryException("Cannot delete entry without id!");
		}
		entryCollection.deleteOne(eq("_id", new ObjectId(entry.getId())));
		logger.info("DELETED: {}/{}", toLogString(entry.getCurrent()), entry.getId());
	}

	public void accept(LexEntry entry, LemmaVersion version) throws InvalidEntryException {
		Validator.validate(entry);
		version.setVerification(LemmaVersion.Verification.ACCEPTED);
		entry.setCurrent(version);
		BasicDBObject object = Converter.convertLexEntry(entry);
		save(entryCollection, new Document(object));
		logger.info("ACCEPTED: {}/{}", toLogString(entry.getCurrent()), entry.getId());
	}

	public void acceptAfterUpdate(LexEntry entry, LemmaVersion suggestion, LemmaVersion modified)
			throws InvalidEntryException {
		Validator.validate(entry);
		suggestion.setVerification(LemmaVersion.Verification.OUTDATED);
		modified.setTimestamp(System.currentTimeMillis());
		modified.setUserId(suggestion.getUserId());
		modified.setIP(suggestion.getIP());
		modified.setVerification(LemmaVersion.Verification.ACCEPTED);
		modified.setCreatorRole(suggestion.getCreatorRole());
		modified.setStatus(LemmaVersion.Status.NEW_MODIFICATION);
		entry.addLemma(modified);
		entry.setCurrent(modified);
		BasicDBObject object = Converter.convertLexEntry(entry);
		save(entryCollection, new Document(object));
		logger.info("ACCEPTED/UPDATED: {}/{}", toLogString(entry.getCurrent()), entry.getId());
	}

	public void reject(LexEntry entry, LemmaVersion version) throws InvalidEntryException {
		Validator.validate(entry);
		if (version.equals(entry.getCurrent())) {
			throw new InvalidEntryException("Please choose a new current version before rejecting!");
		}
		version.setVerification(LemmaVersion.Verification.REJECTED);
		BasicDBObject object = Converter.convertLexEntry(entry);
		save(entryCollection, new Document(object));
		logger.info("REJECTED: {}/{}", toLogString(entry.getCurrent()), entry.getId());
	}

	public void rate(final LexEntry entry, boolean rateUp) {
		if (debugging)
			logger.debug("Rating " + entry + ", " + rateUp);
	}

	public void update(LexEntry entry, LemmaVersion update) throws InvalidEntryException {
		update.setStatus(LemmaVersion.Status.NEW_MODIFICATION);
		entry.addLemma(update);
		if (update.isApproved()) {
			LemmaVersion current = entry.getCurrent();
			if (current != null) {
				current.setVerification(LemmaVersion.Verification.OUTDATED);
			}
			entry.setCurrent(update);
		}
		BasicDBObject object = Converter.convertLexEntry(entry);
		save(entryCollection, new Document(object));

		if (update.isApproved()) {
			logger.info("UPDATED: {}/{}", toLogString(entry.getCurrent()), entry.getId());
		} else {
			logger.info("SUGGESTED UPDATE: {}/{}", toLogString(entry.getCurrent()), entry.getId());
		}
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

	/**
	 * <strong>For unit tests only!</strong>
	 * 
	 * @return
	 */
	public long getNumberOfEntries() {
		return entryCollection.countDocuments();
	}

	public Page<LexEntry> queryForLexEntries(String login, EditorRole role, LemmaVersion.Verification verification, String verifier,
											 long startTime, long endTime, LemmaVersion.Status[] states, int pageSize, int page, String orderField,
											 boolean ascending) {
		logger.info("Query Params: " + login + ", " + role + ", " + verification + ", " + startTime + ", " + endTime + ", " + Arrays.toString(states) + ", " + pageSize + ", " + page + ", " + orderField + ", " + ascending);
		MongoCursor<Document> cursor = query(login, role, verification, verifier, startTime, endTime, states, pageSize, page, orderField, ascending);
		List<LexEntry> results = new ArrayList<LexEntry>();
		while (cursor.hasNext()) {
			DBObject next = new BasicDBObject(cursor.next());
			LexEntry entry = Converter.convertToLexEntry(next);
			results.add(entry);
		}
		cursor.close();
		Pageable pageable = PageRequest.of(page, pageSize);
		return new PageImpl<>(results, pageable, getTotalResults(login, role, verification, verifier, startTime, endTime, states));
	}

	private MongoCursor<Document> query(String loginOrIP, EditorRole role, LemmaVersion.Verification verification, String verifier,
										long startTime, long endTime, LemmaVersion.Status[] states, int pageSize, int page, String orderField,
										boolean ascending) {
		Optional<BasicDBObject> queryOptional = getQuery(loginOrIP, role, verification, verifier, startTime, endTime, states);

		FindIterable<Document> found;
		if (queryOptional.isPresent()) {
			found = entryCollection.find(queryOptional.get());
		} else {
			found = entryCollection.find();
		}
		if (orderField != null) {
			if (ascending) {
				found = found.sort(Sorts.ascending(LexEntry.VERSIONS + "." + orderField));
			} else {
				found = found.sort(Sorts.descending(LexEntry.VERSIONS + "." + orderField));
			}

		}
		// TODO: This is inefficient! However, it should be ok for
		// small queries, which is the expected usecase.

		if (page >= 0 && pageSize > 0) {
			found = found.skip(page * pageSize);
		}

		if (pageSize > 0) {
			found = found.limit(pageSize);
		}

		return found.iterator();
	}

	private long getTotalResults(String loginOrIP, EditorRole role, LemmaVersion.Verification verification, String verifier,
								 long startTime, long endTime, LemmaVersion.Status[] states) {
		Optional<BasicDBObject> queryOptional = getQuery(loginOrIP, role, verification, verifier, startTime, endTime, states);

		if (queryOptional.isPresent()) {
			return entryCollection.countDocuments(queryOptional.get());
		} else {
			return entryCollection.countDocuments();
		}
	}

	private Optional<BasicDBObject> getQuery(String loginOrIP, EditorRole role, LemmaVersion.Verification verification, String verifier,
											 long startTime, long endTime, LemmaVersion.Status[] states) {

		// TODO: Add querying for 'current' state
		BasicDBObject query = new BasicDBObject();
		BasicDBObject attributes = new BasicDBObject();
		if (loginOrIP != null && loginOrIP.trim().length() > 0) {
			BasicDBList or = new BasicDBList();
			DBObject creator = new BasicDBObject();
			creator.put(QUERY_VERSION_CREATOR, loginOrIP);
			or.add(creator);
			DBObject ip = new BasicDBObject();
			ip.put(QUERY_VERSION_IP, loginOrIP);
			or.add(ip);
			attributes.put("$or", or);
		}
		if (verifier != null && verifier.trim().length() > 0) {
			attributes.put(QUERY_VERSION_VERIFIER, verifier);
		}
		if (role != null) {
			attributes.put(QUERY_VERSION_ROLE, role.toString());
		}
		if (verification != null) {
			attributes.put(QUERY_VERSION_VERIFICATION, verification.toString());
		}
		if (states != null && states.length > 0) {
			BasicDBList or = new BasicDBList();
			for (LemmaVersion.Status s : states) {
				or.add(s.toString());
			}
			DBObject nestedOr = new BasicDBObject();
			nestedOr.put("$in", or);
			attributes.put(QUERY_VERSION_STATE, nestedOr);
		}
		if (startTime > 0) {
			attributes.put(QUERY_VERSION_TIMESTAMP, new BasicDBObject("$gt", startTime));
		}
		if (endTime > 0) {
			attributes.put(QUERY_VERSION_TIMESTAMP, new BasicDBObject("$lt", endTime));
		}
		if (startTime > 0 && endTime > 0) {
			DBObject obj = new BasicDBObject("$lt", endTime);
			obj.put("$gt", startTime);
			attributes.put(QUERY_VERSION_TIMESTAMP, obj);
		}
		if (attributes.size() > 0) {
			BasicDBObject elemMatch = new BasicDBObject("$elemMatch", attributes);
			query.append(LexEntry.VERSIONS, elemMatch);
			return Optional.of(query);
		} else {
			return Optional.empty();
		}
	}

	public String getCollection() {
		return entryCollection.getNamespace().getCollectionName();
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

	public void dropHistory(LexEntry entry) {
		Iterator<LemmaVersion> history = entry.getVersionHistory().iterator();
		while (history.hasNext()) {
			LemmaVersion version = history.next();
			if (version.getVerification() == LemmaVersion.Verification.OUTDATED
					|| version.getVerification() == LemmaVersion.Verification.REJECTED) {
				history.remove();
			}
		}
		BasicDBObject object = Converter.convertLexEntry(entry);
		save(entryCollection, new Document(object));
		logger.info("HISTORY DROPPED: " + toLogString(entry.getCurrent()) + ", entry " + entry.getId());
	}

	public String export(boolean allVersions) throws JAXBException, IOException, NoSuchAlgorithmException {
		String fileName = null;
		if (allVersions) {
			fileName = "all_entries_" + DateFormat.getDateInstance().format(new Date());
		} else {
			fileName = "approved_entries_" + DateFormat.getDateInstance().format(new Date());
		}
		File file = new File(fileName + ".zip");
		FileOutputStream fos = new FileOutputStream(file);
		exportData(allVersions, false, fos, fileName);
		return file.getAbsolutePath();
	}

	public void exportData(boolean allVersions, boolean dropInternalKeys, OutputStream output, String fileName)
			throws JAXBException, IOException, NoSuchAlgorithmException {
		MongoCursor<Document> cursor = entryCollection.find().iterator();
		JAXBContext context = null;
		if (allVersions) {
			context = JAXBContext.newInstance(LexEntry.class);
		} else {
			context = JAXBContext.newInstance(LemmaVersion.class);
		}
		ZipOutputStream zout = new ZipOutputStream(new BufferedOutputStream(output));
		zout.putNextEntry(new ZipEntry(fileName + ".xml"));
		MD5OutputStream md5 = new MD5OutputStream(zout);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(md5, StandardCharsets.UTF_8));
		out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		out.write("\n<entries>\n");
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		int entryCounter = 0;
		int versionCounter = 0;
		while (cursor.hasNext()) {
			DBObject object = new BasicDBObject(cursor.next());
			LexEntry entry = Converter.convertToLexEntry(object);
			entryCounter++;
			if (allVersions) {
				if (dropInternalKeys) {
					ArrayList<LemmaVersion> versions = entry.getVersionHistory();
					for (LemmaVersion version : versions) {
						version.setMaalrValues(new HashMap<String, String>());
					}
				}
				marshaller.marshal(entry, out);
				out.write("\n");
				versionCounter += entry.getVersionHistory().size();
			} else {
				LemmaVersion version = entry.getCurrent();
				if (version != null) {
					if (dropInternalKeys) {
						version.setMaalrValues(new HashMap<String, String>());
					}
					marshaller.marshal(version, out);
				}
				versionCounter++;
			}
		}
		out.write("\n</entries>\n");
		out.flush();
		zout.closeEntry();
		zout.putNextEntry(new ZipEntry("About.txt"));
		out = new BufferedWriter(new OutputStreamWriter(zout, StandardCharsets.UTF_8));
		out.write("MD5:\t" + md5.getHash() + "\n");
		out.write("Entries:\t" + entryCounter + "\n");
		out.write("Versions:\t" + versionCounter + "\n");
		out.flush();
		zout.closeEntry();
		zout.close();
	}

	public void importData(InputStream input) throws JAXBException, IOException, XMLStreamException, InvalidEntryException {
		XMLStreamReader xsr = getXMLStreamReader(new BufferedInputStream(input));
		xsr.nextTag(); // Advance to statements element
		Unmarshaller unmarshaller = JAXBContext.newInstance(LexEntry.class).createUnmarshaller();
		List<Document> toInsert = new ArrayList<>();
		int counter = 0;
		while (xsr.nextTag() == XMLStreamConstants.START_ELEMENT) {
			LexEntry entry = (LexEntry) unmarshaller.unmarshal(xsr);
			entry.getVersionHistory().forEach(lemmaVersion -> {
				HashMap<String, String> values = lemmaVersion.getEntryValues();
				String flexType = values.get(LemmaVersion.OVERLAY_LANG_RM__DEPRECATED);
				if (flexType != null) {
					values.put(LemmaVersion.RM_FLEX_TYPE, flexType);
				}
				values.remove(LemmaVersion.OVERLAY_LANG_RM__DEPRECATED);
				values.remove(LemmaVersion.OVERLAY_LANG_DE__DEPRECATED);
				lemmaVersion.setEntryValues(values);
			});
			toInsert.add(new Document(Converter.convertLexEntry(entry)));
			counter++;
			if (counter % 10000 == 0) {
				insertBatch(toInsert);
				toInsert.clear();
			}
		}
		insertBatch(toInsert);
		logger.info("Importing done.");
	}

	public Iterator<LexEntry> getExportedData(InputStream input) throws JAXBException, IOException, XMLStreamException {
		final XMLStreamReader xsr = getXMLStreamReader(new BufferedInputStream(input));
		xsr.nextTag();
		xsr.nextTag();
		final Unmarshaller unmarshaller = JAXBContext.newInstance(LexEntry.class).createUnmarshaller();
		Iterator<LexEntry> allEntries = new Iterator<>() {

			private LexEntry next = (LexEntry) unmarshaller.unmarshal(xsr);

			@Override
			public boolean hasNext() {
				return next != null;
			}

			@Override
			public LexEntry next() {
				LexEntry toReturn = next;
				try {
					xsr.nextTag();
					next = (LexEntry) unmarshaller.unmarshal(xsr);
				} catch (JAXBException e) {
					throw new RuntimeException("Failed to unmarshal entry", e);
				} catch (IllegalStateException e) {
					next = null;
				} catch (XMLStreamException e) {
					throw new RuntimeException("Failed to unmarshal entry", e);
				}
				return toReturn;
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
			}
		};
		return allEntries;
	}

	private static void save(MongoCollection<Document> collection, Document document) {
		Object id = document.get("_id");
		if (id == null) {
			collection.insertOne(document);
		} else {
			collection.replaceOne(eq("_id", id), document, new ReplaceOptions().upsert(true));
		}
	}

	private XMLStreamReader getXMLStreamReader(InputStream input)
			throws IOException, FactoryConfigurationError, XMLStreamException, UnsupportedEncodingException {
		ZipInputStream in = new ZipInputStream(input);
		getNextEntry(in);
		XMLInputFactory xif = XMLInputFactory.newInstance();
		XMLStreamReader xsr = xif.createXMLStreamReader(new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)));
		return xsr;
	}

	private void getNextEntry(ZipInputStream in) throws IOException {
		ZipEntry ze = in.getNextEntry();
		while (!ze.getName().endsWith(".xml")) {
			ze = in.getNextEntry();
		}
	}

	public MongoCursor<Document> getAll() {
		return entryCollection.find().iterator();
	}

}
