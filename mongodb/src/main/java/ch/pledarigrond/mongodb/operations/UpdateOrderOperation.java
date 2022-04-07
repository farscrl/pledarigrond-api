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
package ch.pledarigrond.mongodb.operations;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.DictionaryLanguage;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.mongodb.IDBOperation;
import ch.pledarigrond.common.exception.OperationRejectedException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UpdateOrderOperation implements IDBOperation<List<LexEntry>> {

	@Autowired
	private PgEnvironment pgEnvironment;

	private final List<LemmaVersion> items;
	private final String sortField;
	private String login;
	private final List<LexEntry> entries = new ArrayList<LexEntry>();
	private final long timeStamp;
	private final Language language;

	public UpdateOrderOperation(PgEnvironment pgEnvironment, Language language, DictionaryLanguage dictionaryLanguage, List<LemmaVersion> ordered) {
		this.items = ordered;
		sortField = dictionaryLanguage == DictionaryLanguage.GERMAN ? "DStichwort_sort" : "RStichwort_sort";
		this.timeStamp = System.nanoTime();
		this.language = language;
		this.pgEnvironment = pgEnvironment;
	}

	@Override
	public void execute() throws OperationRejectedException {
		/*
		 * FIXME: This method may lead to a bug: If the order of results is changed,
		 * and afterwards one of the results is reverted to an older version, it will
		 * get an invalid order index. Probably won't happen often, and will "only" result
		 * in one element being in the wrong position, but nevertheless is an issue which
		 * should be solved. One possible solution would be to store the order index within
		 * the lexentry, keep it immutable to lemmaversion history changes.
		 */
		for(int i = 0; i < items.size(); i++) {
			LemmaVersion version = items.get(i);
			BasicDBObject object = Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language)).getById(version.getLexEntryId());
			LexEntry entry = Converter.convertToLexEntry(object);
			System.out.println("Entry " + version.getEntryValue("DStichwort") + " <-> " + version.getEntryValue("RStichwort") + " had sortVal " + version.getEntryValue(sortField) + ", now is " + i + ", sortField is " + sortField);
			version.setValue(sortField, i+"");
			LemmaVersion newVersion = new LemmaVersion();
			newVersion.getEntryValues().putAll(version.getEntryValues());
			newVersion.getMaalrValues().putAll(version.getMaalrValues());
			Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language)).update(entry, version);
			newVersion.setVerification(LemmaVersion.Verification.ACCEPTED);
			newVersion.setVerifierId(login);
			newVersion.setUserId(login);
			newVersion.setTimestamp(System.currentTimeMillis());
			entries.add(entry);
		}
	}

	@Override
	public long getTimeStamp() {
		return timeStamp;
	}

	@Override
	public List<LexEntry> getLexEntry() {
		return entries;
	}

	public IDBOperation<List<LexEntry>> setLogin(String login) {
		this.login = login;
		return this;
	}

}
