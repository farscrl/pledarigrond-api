package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.config.LuceneConfiguration;
import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class TestIndexCRUD {

	private LuceneIndexManager luceneIndexManager;

	private File indexDir;
	
	@Before
	public void beforeTest() throws Exception {
		Language language = Language.SURMIRAN;
		File file = File.createTempFile("pledarigrond", "test");
		indexDir = new File(file.getParentFile(), "pg_test" + UUID.randomUUID() + "_idx");
		Assert.assertFalse(indexDir.exists());
		indexDir.mkdir();
		file.deleteOnExit();
		LuceneConfiguration luceneConfiguration = new LuceneConfiguration(language, indexDir.getAbsolutePath());
		luceneIndexManager = new LuceneIndexManager(luceneConfiguration);
	}
	
	@After
	public void afterTest() {
		deleteRecursive(indexDir);
	}
	
	private void deleteRecursive(File fileOrDir) {
		if(fileOrDir.isDirectory()) {
			File[] files = fileOrDir.listFiles();
            assert files != null;
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

	@Test
	public void testGetStatistics() {
		IndexStatistics stats = luceneIndexManager.getIndexStatistics();
		Assert.assertNotNull(stats);
	}
	
	@Test
	public void testDropIndex() throws Exception  {
		luceneIndexManager.dropIndex();
		IndexStatistics statistics = luceneIndexManager.getIndexStatistics();
		Assert.assertEquals(0, statistics.getNumberOfEntries());
	}
	
	
	@Test
	public void testCreateIndex() throws Exception  {
		testDropIndex();
		List<LexEntry> entries = new ArrayList<>();
		for(int i = 0;  i < 5; i++) {
			entries.add(generateValidEntry());
		}
		luceneIndexManager.addToIndex(entries.iterator());
		IndexStatistics statistics = luceneIndexManager.getIndexStatistics();
		Assert.assertEquals(entries.size(),statistics.getNumberOfEntries());
	}
	
	@Test
	public void testUpdateIndex() throws Exception  {
		testCreateIndex();
		IndexStatistics beforeUpdate = luceneIndexManager.getIndexStatistics();
		List<LexEntry> entries = new ArrayList<>();
		for(int i = 0;  i < 5; i++) {
			entries.add(generateValidEntry());
		}
		luceneIndexManager.addToIndex(entries.iterator());
		IndexStatistics afterUpdate = luceneIndexManager.getIndexStatistics();
		Assert.assertEquals(afterUpdate.getNumberOfEntries(), beforeUpdate.getNumberOfEntries() * 2);
	}

	@Test
	public void testSortOrder() throws Exception {
		// The number must be greater than 10 to cover the case with several digits.
		int numberOfEntries = 12;

		testDropIndex();
		List<LexEntry> entries = new ArrayList<>();
		for(int i = 0;  i < numberOfEntries; i++) {
			LexEntry entry = generateValidEntry();
			entry.getCurrent().putEntryValue("DStichwort", "a" + i);
			entry.getCurrent().putEntryValue("RStichwort", "b");
			entry.getCurrent().putEntryValue("RStichwort_sort", String.valueOf(numberOfEntries + 1 - i));
			entries.add(entry);
		}
		luceneIndexManager.addToIndex(entries.iterator());

		Page<LemmaVersion> result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.NORMAL, "b"), IndexTestHelpers.getPagination());

		Assert.assertEquals(numberOfEntries, result.getTotalElements());

		for(int i = 0;  i < numberOfEntries; i++) {
			Assert.assertEquals("a" + (numberOfEntries - i - 1), result.getContent().get(i).getLemmaValues().get("DStichwort"));
		}
	}
}
