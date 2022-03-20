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

import ch.pledarigrond.common.config.Constants;
import ch.pledarigrond.common.data.common.LightUserInfo;
import ch.pledarigrond.common.data.common.Role;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.mongodb.exceptions.InvalidUserException;
import ch.pledarigrond.mongodb.model.PgUserInfo;
import ch.pledarigrond.mongodb.util.MongoHelper;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class UserInfoDatabase {

	private MongoCollection<Document> userCollection;
	private static final Logger logger = LoggerFactory.getLogger(UserInfoDatabase.class);

	private static UserInfoDatabase instance;

	public static synchronized UserInfoDatabase getInstance() {
		if (instance == null) {
			try {
				instance = new UserInfoDatabase();
			} catch (DatabaseException e) {
				logger.error(String.valueOf(e));
			}
		}
		return instance;
	}

	UserInfoDatabase() throws DatabaseException {
		
		try {
			MongoDatabase db = MongoHelper.getDB("users");
			userCollection = db.getCollection("sm_users");
			if(userCollection.countDocuments() == 0) {
				createIndex();
			}
				
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void createIndex() {
		userCollection.createIndex(Indexes.ascending(Constants.Users.LOGIN), new IndexOptions().unique(true));
		userCollection.createIndex(Indexes.ascending(Constants.Users.CREATION_DATE));
		userCollection.createIndex(Indexes.ascending(Constants.Users.LAST_MODIFICATION));
		userCollection.createIndex(Indexes.ascending(Constants.Users.PASSWORD));
	}

	public boolean userExists(String login) {
		BasicDBObject obj = new BasicDBObject();
		obj.put(Constants.Users.LOGIN, login);
		MongoCursor<Document> cursor = userCollection.find(obj).iterator();
		boolean hasNext = cursor.hasNext();
		cursor.close();
		return hasNext;
	}

	/**
	 * <strong>For unit tests only!</strong> This method drops the entire
	 * collection of entries and creates a new one.
	 */
	public void deleteAllEntries() {
		userCollection.drop();
	}

	public PgUserInfo insert(PgUserInfo user) throws InvalidUserException {
		if(userExists(user.getLogin())) throw new InvalidUserException("User already exists!");
		long now = System.currentTimeMillis();
		user.setCreationDate(now);
		user.setLastModificationDate(now);
		userCollection.insertOne(new Document(user));
		logger.info("Inserted new user " + user.getLogin());
		return user;
	}

	public PgUserInfo getByLogin(String login) {
		BasicDBObject obj = new BasicDBObject();
		obj.put(Constants.Users.LOGIN, login);
		return findByDbObject(obj);
	}
	
	public PgUserInfo getByEmail(String email) {
		BasicDBObject obj = new BasicDBObject();
		obj.put(Constants.Users.EMAIL, email);
		return findByDbObject(obj);
	}

	private PgUserInfo findByDbObject(BasicDBObject obj) {
		MongoCursor<Document> cursor = userCollection.find(obj).iterator();
		if(!cursor.hasNext()) return null;
		PgUserInfo toReturn = new PgUserInfo(new BasicDBObject(cursor.next()));
		cursor.close();
		return toReturn;
	}

	public PgUserInfo getOrCreate(String login) throws InvalidUserException {
		if(userExists(login)) {
			return getByLogin(login);
		} else {
			Role role = "admin".equals(login) ? Role.ADMIN_5 : Role.GUEST_1;
			return insert(new PgUserInfo(login, role));
		}
	}

	public void updateUser(PgUserInfo user) throws InvalidUserException {
		if(!userExists(user.getLogin())) {
			throw new InvalidUserException("User does not exist");
		}
		update(user);
	}

	private void update(PgUserInfo user) {
		Document document = new Document(user);
		userCollection.insertOne(document);
		logger.info("user updated: {}", document.toJson());
	}
	
	public List<PgUserInfo> getAllUsers(int from, int length, String sortColumn, boolean sortAscending) {
		
		FindIterable<Document> find = userCollection.find();
		
		if(sortColumn != null) {
			BasicDBObject sort = new BasicDBObject();
			sort.put(sortColumn, sortAscending ? 1 : -1);
			find.sort(sort);
		}
		
		find = find.skip(from).limit(length);
		
		List<PgUserInfo> all = new ArrayList<PgUserInfo>();
		MongoCursor<Document> cursor = find.iterator();
		
		while(cursor.hasNext()) {
			DBObject o = new BasicDBObject(cursor.next());
			PgUserInfo user = new PgUserInfo(o);
			all.add(user);
		}
		cursor.close();
		return all;
	}
	
	public List<PgUserInfo> getAllUsers(Role role, String text, String sortColumn, boolean sortAscending, int from, int length) {
		BasicDBObject query = new BasicDBObject();
		Pattern pattern = Pattern.compile(".*" + text + ".*", Pattern.CASE_INSENSITIVE);
		if(role != null) {
			query.put(Constants.Users.ROLE, role.toString());
		}
		// The value for the variable 'text' is set in 'maalr.gwt > ListFilter.java'
		if(text != null && text.trim().length() > 0) {
			BasicDBList attributes = new BasicDBList();
			DBObject login = new BasicDBObject();
			login.put(Constants.Users.LOGIN, pattern);
			attributes.add(login);
			query.append("$or", attributes);
		}
		FindIterable<Document> find = userCollection.find(query);
		if(sortColumn != null) {
			BasicDBObject sort = new BasicDBObject();
			sort.put(sortColumn, sortAscending ? 1 : -1);
			find.sort(sort);
		}
		
		find = find.skip(from).limit(length);
		List<PgUserInfo> all = new ArrayList<PgUserInfo>();
		
		MongoCursor<Document> cursor = find.iterator();
		while(cursor.hasNext()) {
			DBObject o = new BasicDBObject(cursor.next());
			PgUserInfo user = new PgUserInfo(o);
			if(!all.contains(user)) 
				all.add(user);
		}
		cursor.close();
		return all;
	}

	public int getNumberOfUsers() {
		return (int) userCollection.countDocuments();
	}

	public boolean deleteUser(LightUserInfo user) {
		BasicDBObject obj = new BasicDBObject();
		obj.put(Constants.Users.LOGIN, user.getLogin());
		DeleteResult deleteOne = userCollection.deleteOne(obj);
		return deleteOne.getDeletedCount() == 1;
	}

}
