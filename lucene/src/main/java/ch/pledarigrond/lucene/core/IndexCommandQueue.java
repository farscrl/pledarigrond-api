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
package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.data.common.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class IndexCommandQueue {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final ExecutorService executor;

	private final Language language;
	
	private static final Map<Language, IndexCommandQueue> instancesMap = new HashMap<>();
	
	public static synchronized IndexCommandQueue getInstance(Language language) {
		if(instancesMap.get(language) == null) {
			IndexCommandQueue instance = new IndexCommandQueue(language);
			instancesMap.put(language, instance);
		}
		return instancesMap.get(language);
	}
	
	private IndexCommandQueue(Language language) {
		this.language = language;

		ThreadFactory factory = r -> {
            Thread t = new Thread(r);
            t.setName("Index-Queue");
            return t;
        };
		executor = Executors.newSingleThreadExecutor(factory);
	}
	
	public void pushMulti(final List<IndexOperation> operation) throws Exception {
        logger.info("Received {} operations", operation.size());
		Callable<Void> callable = () -> {
            try {
                for (IndexOperation op : operation) {
                    op.execute(language);
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        };
		executeCallable(callable);
	}

	public void push(final IndexOperation operation) throws Exception {
		Callable<Void> callable = () -> {
            try {
                operation.execute(language);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        };
		executeCallable(callable);
	}

	private void executeCallable(Callable<Void> callable) throws Exception {
		Future<Void> future = executor.submit(callable);
		try {
			future.get();
		} catch (ExecutionException e) {
			if(e.getCause() != null && (e.getCause() instanceof Exception)) {
				throw (Exception) e.getCause();
			} else {
				throw e;
			}
		}
	}
}
