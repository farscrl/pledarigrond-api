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

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.mongodb.IDBOperation;
import ch.pledarigrond.common.exception.OperationRejectedException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

public class DBCommandQueue {

	private final ExecutorService executor;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static DBCommandQueue instance;

	@Autowired
	private PgEnvironment pgEnvironment;

	public static synchronized DBCommandQueue getInstance() {
		if(instance == null) {
			instance = new DBCommandQueue();
		}
		return instance;
	}
	
	private DBCommandQueue() {
		ThreadFactory factory = new ThreadFactory() {
			
			@Override
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setName("DB-Queue");
				return t;
			}
		};
		executor = Executors.newSingleThreadExecutor(factory);
	}
	
	public List<LexEntry> pushMulti(final IDBOperation<List<LexEntry>> operation, Language language) throws Exception {
		final List<LexEntry> entries = operation.getLexEntry();
		logger.debug("Received operation for " + entries.size() + " entries");

		Callable<List<LexEntry>> callable = new Callable<List<LexEntry>>() {

			@Override
			public List<LexEntry> call() throws Exception {
				try {
					for (LexEntry entry : entries) {
						if(entry.getId() != null) {
							LexEntry stored = Converter.convertToLexEntry(Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language)).getById(entry.getId()));
							if(stored.getChangeStamp() != null && !stored.getChangeStamp().equals(entry.getChangeStamp())) {
								throw new OperationRejectedException("Local data outdated, please refresh the page and try again.");
							}
						}
					}
					for (LexEntry entry : entries) {
						entry.setChangeStamp(UUID.randomUUID().toString());
					}
					operation.execute();
					return operation.getLexEntry();
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			}
		};
		Future<List<LexEntry>> future = executor.submit(callable);
		try {
			List<LexEntry> toReturn = future.get();
			if(toReturn != null) {
				logger.debug("Successfully executed operation on " + toReturn.size() + " entries.");
			}
			return toReturn;
		} catch (ExecutionException e) {
			if(e.getCause() != null && (e.getCause() instanceof Exception)) {
				throw (Exception) e.getCause();
			} else {
				throw e;
			}
		}
	}
	
	public LexEntry push(final IDBOperation<LexEntry> operation, Language language) throws Exception {
		final LexEntry entry = operation.getLexEntry();
		logger.debug("Received operation for entry id " + entry.getId());
		Callable<LexEntry> callable = new Callable<LexEntry>() {

			@Override
			public LexEntry call() throws Exception {
				try {
					if(entry.getId() != null) {
						LexEntry stored = Converter.convertToLexEntry(Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language)).getById(entry.getId()));
						if(stored.getChangeStamp() != null && !stored.getChangeStamp().equals(entry.getChangeStamp())) {
							throw new OperationRejectedException("Local data outdated, please refresh the page and try again.");
						}
					}
					entry.setChangeStamp(UUID.randomUUID().toString());
					operation.execute();
					return operation.getLexEntry();
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			}
		};
		Future<LexEntry> future = executor.submit(callable);
		try {
			LexEntry toReturn = future.get();
			if(toReturn != null) {
				logger.debug("Successfully executed operation on entry id " + toReturn.getId());
			}
			return toReturn;
		} catch (ExecutionException e) {
			if(e.getCause() != null && (e.getCause() instanceof Exception)) {
				throw (Exception) e.getCause();
			} else {
				throw e;
			}
		}
	}

}
