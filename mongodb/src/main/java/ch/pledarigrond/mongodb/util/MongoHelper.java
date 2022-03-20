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
package ch.pledarigrond.mongodb.util;

import ch.pledarigrond.common.exception.DatabaseException;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;

public class MongoHelper {

	private static Logger logger = LoggerFactory.getLogger(MongoHelper.class);

	private static MongoClient MONGO_CLIENT;

	private static MongoDatabase DATABASE;

	private static final Object lock = new Object();

	public static MongoDatabase getDB(String dbName) throws UnknownHostException, DatabaseException {
		synchronized(lock) {

			if (dbName == null) {
				throw new DatabaseException("No DB name provided");
			}

			if(MONGO_CLIENT == null) {
				// TODO: import values from application.properties
				MONGO_CLIENT = MongoClients.create("mongodb://rootuser:rootpass@localhost:27017/?authSource=admin");
			}
			
			DATABASE = MONGO_CLIENT.getDatabase(dbName);
			
			logger.info("Connecting to data base... " + DATABASE.getName());
			
			return DATABASE;	
		}
	}
	
	public static boolean isRunning() {
		return DATABASE != null && MONGO_CLIENT != null;
	}

	public static void shutdown() {
		synchronized(lock ) {
			MONGO_CLIENT.close();
			DATABASE = null;
			MONGO_CLIENT = null;
		}
	}

}
