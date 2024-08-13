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

import ch.pledarigrond.common.config.LuceneConfiguration;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import org.junit.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class TestIndexCRUD {

	private LuceneIndex luceneIndex;

	private File indexDir;
	
	@Before
	public void beforeTest() throws Exception {
		Language language = Language.SURMIRAN;
		File file = File.createTempFile("pledarigrond", "test");
		indexDir = new File(file.getParentFile(), "pg_test" + UUID.randomUUID().toString() + "_idx");
		Assert.assertFalse(indexDir.exists());
		indexDir.mkdir();
		file.deleteOnExit();
		LuceneConfiguration luceneConfiguration = new LuceneConfiguration(language, indexDir.getAbsolutePath());
		luceneIndex = new LuceneIndex(luceneConfiguration);
	}
	
	@After
	public void afterTest() throws Exception {
		deleteRecursive(indexDir);
	}
	
	private void deleteRecursive(File fileOrDir) {
		if(fileOrDir.isDirectory()) {
			File[] files = fileOrDir.listFiles();
			for (File file : files) {
				deleteRecursive(file);
			}
		} else {
			fileOrDir.delete();
		}
	}

	private LexEntry generateValidEntry() {
		LemmaVersion lv = new LemmaVersion();
		lv.putEntryValue("DStichwort", "a" + UUID.randomUUID());
		lv.putEntryValue("RStichwort", "b" + UUID.randomUUID());
		LexEntry entry = new LexEntry(lv);
		lv.setVerification(LemmaVersion.Verification.ACCEPTED);
		entry.setId(UUID.randomUUID().toString());
		return entry;
	}

	@Ignore
	@Test
	public void testGetStatistics() {
		IndexStatistics stats = luceneIndex.getIndexStatistics();
		Assert.assertNotNull(stats);
	}
	
	@Test
	public void testDropIndex() throws Exception  {
		luceneIndex.dropIndex();
		IndexStatistics statistics = luceneIndex.getIndexStatistics();
		Assert.assertEquals(0, statistics.getNumberOfEntries());
	}
	
	
	@Test
	public void testCreateIndex() throws Exception  {
		testDropIndex();
		List<LexEntry> entries = new ArrayList<LexEntry>();
		for(int i = 0;  i < 5; i++) {
			entries.add(generateValidEntry());
		}
		luceneIndex.addToIndex(entries.iterator());
		IndexStatistics statistics = luceneIndex.getIndexStatistics();
		Assert.assertEquals(entries.size(),statistics.getNumberOfEntries());
	}
	
	@Test
	public void testUpdateIndex() throws Exception  {
		testCreateIndex();
		IndexStatistics beforeUpdate = luceneIndex.getIndexStatistics();
		List<LexEntry> entries = new ArrayList<LexEntry>();
		for(int i = 0;  i < 5; i++) {
			entries.add(generateValidEntry());
		}
		luceneIndex.addToIndex(entries.iterator());
		IndexStatistics afterUpdate = luceneIndex.getIndexStatistics();
		Assert.assertEquals(afterUpdate.getNumberOfEntries(), beforeUpdate.getNumberOfEntries() * 2);
	}
}
