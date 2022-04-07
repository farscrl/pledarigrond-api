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
import ch.pledarigrond.common.data.mongodb.IDBOperation;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.common.exception.OperationRejectedException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.mongodb.exceptions.InvalidEntryException;
import ch.pledarigrond.mongodb.model.DictionaryStatistics;
import ch.pledarigrond.mongodb.util.Validator;

public class UpdateOperation extends BaseOperation implements IDBOperation {

	private final LexEntry entry;
	private final LemmaVersion newVersion;

	public UpdateOperation(PgEnvironment pgEnvironment, Language language, LexEntry oldEntry, LemmaVersion newEntry) {
		this.entry = oldEntry;
		this.newVersion = newEntry;
		this.language = language;
		this.pgEnvironment = pgEnvironment;
	}

	@Override
	public void execute() throws InvalidEntryException, OperationRejectedException {
		Validator.validatePostInsert(entry);
		newVersion.setStatus(LemmaVersion.Status.UNDEFINED);
		newVersion.setVerification(LemmaVersion.Verification.UNVERIFIED);
		Validator.validatePreUpdate(newVersion);
		if(isSuggestion()) {
			newVersion.setVerification(LemmaVersion.Verification.UNVERIFIED);
		} else {
			newVersion.setVerification(LemmaVersion.Verification.ACCEPTED);
			newVersion.setVerifierId(getUserId());
		}
		newVersion.setUserId(getUserId());
		newVersion.setTimestamp(System.currentTimeMillis());
		try {
			Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language)).update(entry, newVersion);
			if(isSuggestion()) {
				DictionaryStatistics.newEntryModificationSuggestion();
			} else {
				DictionaryStatistics.newEntryModification();
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
