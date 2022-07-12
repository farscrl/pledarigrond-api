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
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.common.exception.OperationRejectedException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.mongodb.exceptions.InvalidEntryException;
import ch.pledarigrond.mongodb.model.DictionaryStatistics;
import ch.pledarigrond.mongodb.util.Validator;


public class InsertOperation extends BaseOperation {

	private final LexEntry entry;

	public InsertOperation(PgEnvironment pgEnvironment, Language language, LexEntry entry) {
		this.entry = entry;
		this.language = language;
		this.pgEnvironment = pgEnvironment;
	}

	@Override
	public void execute() throws InvalidEntryException, OperationRejectedException {
		//entry.prepareForUpdateOrInsert();
		Validator.validatePreInsert(entry);
		LemmaVersion current = entry.getCurrent();
		current.setStatus(LemmaVersion.Status.NEW_ENTRY);
		if(isSuggestion()) {
			current.setVerification(LemmaVersion.Verification.UNVERIFIED);
		} else {
			current.setVerification(LemmaVersion.Verification.ACCEPTED);
			current.setVerifierId(getUserId());
		}
		current.setUserId(getUserId());
		current.setTimestamp(System.currentTimeMillis());
		entry.setCurrent(current);
		entry.setNextInternalId(entry.getVersionHistory().size());
		try {
			Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language)).insert(entry);
			if(isSuggestion()) {
				DictionaryStatistics.newSuggestion();
			} else {
				DictionaryStatistics.newEntry();
			}
		} catch (NoDatabaseAvailableException e) {
			throw new OperationRejectedException(e);
		}
		Validator.validatePostInsert(entry);
	}

	@Override
	public LexEntry getLexEntry() {
		return entry;
	}

}
