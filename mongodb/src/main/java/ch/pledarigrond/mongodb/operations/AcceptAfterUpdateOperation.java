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
import ch.pledarigrond.mongodb.model.DictionaryStatistics;

public class AcceptAfterUpdateOperation extends BaseOperation implements IDBOperation {

	private final LexEntry entry;
	private final LemmaVersion modified;
	private final LemmaVersion suggested;

	public AcceptAfterUpdateOperation(PgEnvironment pgEnvironment, Language language, LexEntry entry, LemmaVersion suggested, LemmaVersion modified) {
		this.entry = entry;
		this.suggested = suggested;
		this.modified = modified;
		this.language = language;
		this.pgEnvironment = pgEnvironment;
	}

	@Override
	public void execute() throws OperationRejectedException {
		validate(entry);
		try {
			LemmaVersion current = entry.getCurrent();
			boolean approved = false; 
			if(current != null) {
				approved = current.isApproved();
				current.setVerification(LemmaVersion.Verification.OUTDATED);
			}
			suggested.setVerifierId(getUserId());
			modified.setVerification(LemmaVersion.Verification.ACCEPTED);
			modified.setVerifierId(getUserId());
			Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language)).acceptAfterUpdate(entry, suggested, modified);
			DictionaryStatistics.userSuggestionAccepted(approved);
		} catch (NoDatabaseAvailableException e) {
			throw new OperationRejectedException(e);
		}
	}

	@Override
	public LexEntry getLexEntry() {
		return entry;
	}

}
