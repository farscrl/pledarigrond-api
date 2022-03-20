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

import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.mongodb.IDBOperation;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.common.exception.OperationRejectedException;
import ch.pledarigrond.mongodb.core.Database;

public class DropHistoryOperation extends BaseOperation implements IDBOperation {

	private LexEntry entry;

	public DropHistoryOperation(LexEntry entry, String locale) {
		this.entry = entry;
		this.locale = locale;
	}

	@Override
	public void execute() throws OperationRejectedException {
		validate(entry);
		try {
			Database.getInstance(this.locale).dropHistory(entry);
		} catch (NoDatabaseAvailableException e) {
			throw new OperationRejectedException(e);
		}
	}
	
	@Override
	public LexEntry getLexEntry() {
		return entry;
	}

}
