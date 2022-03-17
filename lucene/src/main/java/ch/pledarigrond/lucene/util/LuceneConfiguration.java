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
package ch.pledarigrond.lucene.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * This class gives access to some base information of the currently used lucene instance.
 */
public class LuceneConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(LuceneConfiguration.class);

	private File indexDir;

	public File getLuceneTimestampFile() {
		return new File(indexDir, ".stamp");
	}

	public File getLuceneIndexDir() {
		return indexDir;
	}

	public void setBaseDirectory(String luceneDir) {
		this.indexDir = new File(luceneDir);
		boolean result = indexDir.mkdirs();
		if (!result) {
			logger.error("Impossible to create index directory");
		}
	}
}
