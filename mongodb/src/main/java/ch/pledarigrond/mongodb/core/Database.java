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
import ch.pledarigrond.mongodb.util.MongoHelper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class Database {

	@Autowired
	private PgEnvironment pgEnvironment;

	private static final String ENTRIES = "entries";

	private static final Map<String, Database> instances = new HashMap<>();

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private MongoCollection<Document> entryCollection;

	private final String locale;

	Database(String locale) {
		this.locale = locale;
		entryCollection = MongoHelper.getDB(pgEnvironment, this.locale).getCollection(ENTRIES);
		long entries = entryCollection.countDocuments();
		logger.info("Connected to entries-collection containing " + entries + " items.");
	}

	public static synchronized Database getInstance(String dbName) {
		Database instance = instances.get(dbName);
		if (instance == null) {
			instance = new Database(dbName);
			instances.put(dbName, instance);
		}
		return instance;
	}

	public MongoCursor<Document> getAll() {
		return entryCollection.find().iterator();
	}
}
