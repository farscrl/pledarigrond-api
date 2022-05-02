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
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.LightUserInfo;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.mongodb.exceptions.InvalidUserException;
import ch.pledarigrond.mongodb.model.PgUser;
import ch.pledarigrond.mongodb.util.MongoHelper;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.mongodb.client.model.Filters.eq;

@Component
public class UserInfoDatabase {

	private final PgEnvironment pgEnvironment;

	private MongoCollection<Document> userCollection;
	private static final Logger logger = LoggerFactory.getLogger(UserInfoDatabase.class);

	private static UserInfoDatabase instance;

	public static synchronized UserInfoDatabase getInstance(PgEnvironment pgEnvironment) {
		if (instance == null) {
			try {
				instance = new UserInfoDatabase(pgEnvironment);
			} catch (DatabaseException e) {
				logger.error(String.valueOf(e));
			}
		}
		return instance;
	}

	@Autowired
	UserInfoDatabase(PgEnvironment pgEnvironment) throws DatabaseException {
		this.pgEnvironment = pgEnvironment;

		try {
			MongoDatabase db = MongoHelper.getDB(pgEnvironment, "users");
			userCollection = db.getCollection("users");
			if(userCollection.countDocuments() == 0) {
				createIndex();
			}

		} catch (UnknownHostException | DatabaseException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void createIndex() {
		userCollection.createIndex(Indexes.ascending(Constants.Users.EMAIL), new IndexOptions().unique(true));
		userCollection.createIndex(Indexes.ascending(Constants.Users.CREATION_DATE));
		userCollection.createIndex(Indexes.ascending(Constants.Users.LAST_MODIFICATION));
		userCollection.createIndex(Indexes.ascending(Constants.Users.PASSWORD));
	}

	public boolean userExists(String email) {
		BasicDBObject obj = new BasicDBObject();
		obj.put(Constants.Users.EMAIL, email);
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

	public PgUser insert(PgUser user) throws InvalidUserException {
		if(userExists(user.getEmail())) throw new InvalidUserException("User already exists!");
		long now = System.currentTimeMillis();
		user.setCreationDate(now);
		user.setLastModificationDate(now);
		userCollection.insertOne(new Document(user));
		logger.info("Inserted new user " + user.getEmail());
		return user;
	}
	
	public PgUser getByEmail(String email) {
		BasicDBObject obj = new BasicDBObject();
		obj.put(Constants.Users.EMAIL, email);
		return findByDbObject(obj);
	}

	private PgUser findByDbObject(BasicDBObject obj) {
		MongoCursor<Document> cursor = userCollection.find(obj).iterator();
		if(!cursor.hasNext()) return null;
		PgUser toReturn = new PgUser(new BasicDBObject(cursor.next()));
		cursor.close();
		return toReturn;
	}

	public PgUser getOrCreate(String email) throws InvalidUserException {
		if(userExists(email)) {
			return getByEmail(email);
		} else {
			return insert(new PgUser(email));
		}
	}

	public PgUser updateUser(PgUser user) throws InvalidUserException {
		if(!userExists(user.getEmail())) {
			throw new InvalidUserException("User does not exist");
		}
		return update(user);
	}

	private PgUser update(PgUser user) {
		Document document = new Document(user);
		// userCollection.updateOne(Filters.eq("_id", new ObjectId(user.getString("_id"))), document);
		userCollection.replaceOne(eq("_id", new ObjectId(user.getString("_id"))), document, new ReplaceOptions());
		logger.info("user updated: {}", document.toJson());
		return user;
	}
	
	public List<PgUser> getAllUsers(int from, int length, String sortColumn, boolean sortAscending) {
		
		FindIterable<Document> find = userCollection.find();
		
		if(sortColumn != null) {
			BasicDBObject sort = new BasicDBObject();
			sort.put(sortColumn, sortAscending ? 1 : -1);
			find.sort(sort);
		}
		
		find = find.skip(from).limit(length);
		
		List<PgUser> all = new ArrayList<PgUser>();
		MongoCursor<Document> cursor = find.iterator();
		
		while(cursor.hasNext()) {
			DBObject o = new BasicDBObject(cursor.next());
			PgUser user = new PgUser(o);
			all.add(user);
		}
		cursor.close();
		return all;
	}
	
	public List<PgUser> getAllUsers(String text, String sortColumn, boolean sortAscending, int from, int length) {
		BasicDBObject query = new BasicDBObject();
		Pattern pattern = Pattern.compile(".*" + text + ".*", Pattern.CASE_INSENSITIVE);

		if(text != null && text.trim().length() > 0) {
			BasicDBList attributes = new BasicDBList();
			DBObject email = new BasicDBObject();
			email.put(Constants.Users.EMAIL, pattern);
			attributes.add(email);
			query.append("$or", attributes);
		}
		FindIterable<Document> find = userCollection.find(query);
		if(sortColumn != null) {
			BasicDBObject sort = new BasicDBObject();
			sort.put(sortColumn, sortAscending ? 1 : -1);
			find.sort(sort);
		}
		
		find = find.skip(from).limit(length);
		List<PgUser> all = new ArrayList<PgUser>();
		
		MongoCursor<Document> cursor = find.iterator();
		while(cursor.hasNext()) {
			DBObject o = new BasicDBObject(cursor.next());
			PgUser user = new PgUser(o);
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
		obj.put(Constants.Users.EMAIL, user.getEmail());
		DeleteResult deleteOne = userCollection.deleteOne(obj);
		return deleteOne.getDeletedCount() == 1;
	}

	public void exportData(OutputStream output, String fileName) throws JAXBException, IOException, NoSuchAlgorithmException {
		MongoCursor<Document> cursor = userCollection.find().iterator();
		JAXBContext context = JAXBContext.newInstance(PgUser.class);
		ZipOutputStream zout = new ZipOutputStream(new BufferedOutputStream(output));
		zout.putNextEntry(new ZipEntry(fileName + ".xml"));
		MD5OutputStream md5 = new MD5OutputStream(zout);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(md5, StandardCharsets.UTF_8));
		out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		out.write("\n<users>\n");
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		int entryCounter = 0;
		int versionCounter = 0;
		while (cursor.hasNext()) {
			DBObject object = new BasicDBObject(cursor.next());
			PgUser user = new PgUser(object);
			marshaller.marshal(user, out);
			out.write("\n");
			entryCounter++;

		}
		out.write("\n</users>\n");
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
}
