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

import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Converts {@link LexEntry} and {@link LemmaVersion} objects to/from
 * {@link DBObject} objects.
 *
 * @return
 */
public class Converter {

	public static BasicDBObject convertLexEntry(LexEntry entry) {
		BasicDBObject object = new BasicDBObject();
		List<LemmaVersion> vHistory = entry.getVersionHistory();
		BasicDBList versions = new BasicDBList();
		for (LemmaVersion lemmaVersion : vHistory) {
			BasicDBObject obj = convertLemmaVersion(lemmaVersion);
			versions.add(obj);
		}
		object.put(LexEntry.VERSIONS, versions);
		if (entry.getId() != null) {
			object.put(LexEntry.ID, new ObjectId(entry.getId()));
		}
		object.put(LexEntry.CURRENT, entry.getCurrentId());
		object.put(LexEntry.NEXT_INTERNAL_ID, entry.getNextInternalId());
		object.put(LexEntry.CHANGE_STAMP, entry.getChangeStamp());
		return object;
	}

	private static BasicDBObject convertLemmaVersion(LemmaVersion lemmaVersion) {
		BasicDBObject obj = new BasicDBObject();
		Map<String, String> toStore = new HashMap<String, String>(lemmaVersion.getLemmaValues());
		toStore.keySet().removeAll(LemmaVersion.PG_KEYS);
		toStore.putAll(lemmaVersion.getPgValues());
		obj.putAll(toStore);
		obj.put(LemmaVersion.TIMESTAMP, lemmaVersion.getTimestamp());
		return obj;
	}

	public static LexEntry convertToLexEntry(DBObject obj) {
		ArrayList<Document> versions = (ArrayList<Document>) obj.get(LexEntry.VERSIONS);
		ArrayList<LemmaVersion> list = new ArrayList<LemmaVersion>();
		ObjectId objId = (ObjectId) obj.get(LexEntry.ID);
		for (Document doc : versions) {
			Map<String, String> lemmaValues = new HashMap(doc);
			lemmaValues.keySet().removeAll(LemmaVersion.PG_KEYS);
			Map<String, String> pgValues = new HashMap(doc);
			pgValues.keySet().retainAll(LemmaVersion.PG_KEYS);
			Long timeStamp = (Long) doc.remove(LemmaVersion.TIMESTAMP);
			LemmaVersion lemmaVersion = new LemmaVersion();
			lemmaVersion.getLemmaValues().putAll(lemmaValues);
			lemmaVersion.getPgValues().putAll(pgValues);
			lemmaVersion.setTimestamp(timeStamp);

			// adding the lexEntryId to the lemmaVersion
			if (lemmaVersion.getLexEntryId() == null || lemmaVersion.getLexEntryId().isEmpty()) {
				if (objId != null) {
					lemmaVersion.setLexEntryId(objId.toString());
				}
			}
			list.add(lemmaVersion);
		}
		LexEntry entry = new LexEntry();
		if (objId != null) {
			entry.setId(objId.toString());
		}
		entry.setNextInternalId((Integer) obj.get(LexEntry.NEXT_INTERNAL_ID));
		entry.setLemmaVersions(list);
		entry.setCurrentId((Integer) obj.get(LexEntry.CURRENT));
		entry.setChangeStamp((String) obj.get(LexEntry.CHANGE_STAMP));
		return entry;
	}
}