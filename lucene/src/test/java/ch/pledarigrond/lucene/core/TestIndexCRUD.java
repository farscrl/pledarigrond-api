package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.config.LuceneConfiguration;
import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.common.data.lucene.SuggestionField;
import ch.pledarigrond.common.data.user.SearchCriteria;
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
		return generateValidEntryFromLemmaVersion(lv);
	}

	private LexEntry generateValidEntryFromLemmaVersion(LemmaVersion lv) {
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
	public void testUpdateEntityIndex() throws Exception  {
		testDropIndex();
		IndexStatistics beforeUpdate = luceneIndexManager.getIndexStatistics();
		List<LexEntry> entries = new ArrayList<>();
		for(int i = 0;  i < 5; i++) {
			LexEntry entry = generateValidEntry();
			entry.getCurrent().putEntryValue("DStichwort", "d" + i);
			entry.getCurrent().putEntryValue("RStichwort", "r" + i);
			entries.add(entry);
		}
		luceneIndexManager.addToIndex(entries.iterator());
		IndexStatistics afterUpdate = luceneIndexManager.getIndexStatistics();
		Assert.assertEquals(5, afterUpdate.getNumberOfEntries());

		Page<LemmaVersion> result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.EXACT, "r2"), IndexTestHelpers.getPagination());
		Assert.assertEquals(1, result.getTotalElements());

		// Update existing lemma version
		LemmaVersion lemmaVersion = result.getContent().get(0);
		lemmaVersion.getLemmaValues().put("RStichwort", "r22");
		LexEntry entry = generateValidEntryFromLemmaVersion(lemmaVersion);
		entry.setId(lemmaVersion.getLexEntryId());
		luceneIndexManager.update(entry);

		result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.EXACT, "r2"), IndexTestHelpers.getPagination());
		Assert.assertEquals(0, result.getTotalElements());
		result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.EXACT, "r22"), IndexTestHelpers.getPagination());
		Assert.assertEquals(1, result.getTotalElements());

		// adding modified version
		lemmaVersion = result.getContent().get(0);
		entry = generateValidEntryFromLemmaVersion(lemmaVersion);
		entry.setId(lemmaVersion.getLexEntryId());

		LemmaVersion modifiedLemmaVersion = new LemmaVersion();
		modifiedLemmaVersion.putEntryValue("DStichwort", "d22");
		modifiedLemmaVersion.putEntryValue("RStichwort", "r22");
		modifiedLemmaVersion.setVerification(LemmaVersion.Verification.UNVERIFIED);
		entry.addLemma(modifiedLemmaVersion);
		luceneIndexManager.update(entry);

		result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.EXACT, "r22"), IndexTestHelpers.getPagination());
		Assert.assertEquals(1, result.getTotalElements());
		SearchCriteria criteria = IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.EXACT, "r22");
		criteria.setSuggestions(true);
		result = luceneIndexManager.query(criteria, IndexTestHelpers.getPagination());
		Assert.assertEquals(2, result.getTotalElements());

		// accept one version
		LemmaVersion acceptedVersion = result.getContent().get(0);
		acceptedVersion.setVerification(LemmaVersion.Verification.ACCEPTED);
		LemmaVersion outdatedVersion = result.getContent().get(1);
		outdatedVersion.setVerification(LemmaVersion.Verification.OUTDATED);

		entry = generateValidEntryFromLemmaVersion(outdatedVersion);
		entry.setId(acceptedVersion.getLexEntryId());
		entry.addLemma(acceptedVersion);
		luceneIndexManager.update(entry);

		result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.EXACT, "r22"), IndexTestHelpers.getPagination());
		Assert.assertEquals(1, result.getTotalElements());
		criteria = IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.EXACT, "r22");
		criteria.setSuggestions(true);
		result = luceneIndexManager.query(criteria, IndexTestHelpers.getPagination());
		Assert.assertEquals(1, result.getTotalElements());
	}

	@Test
	public void testDeleteEntryFromIndex() throws Exception  {
		testDropIndex();
		IndexStatistics beforeUpdate = luceneIndexManager.getIndexStatistics();
		List<LexEntry> entries = new ArrayList<>();
		for(int i = 0;  i < 5; i++) {
			LexEntry entry = generateValidEntry();
			entry.getCurrent().putEntryValue("DStichwort", "d" + i);
			entry.getCurrent().putEntryValue("RStichwort", "r" + i);
			entries.add(entry);
		}
		luceneIndexManager.addToIndex(entries.iterator());
		IndexStatistics afterUpdate = luceneIndexManager.getIndexStatistics();
		Assert.assertEquals(5, afterUpdate.getNumberOfEntries());

		Page<LemmaVersion> result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.EXACT, "r3"), IndexTestHelpers.getPagination());
		Assert.assertEquals(1, result.getTotalElements());

		// Delete existing lemma version
		LemmaVersion lemmaVersion = result.getContent().get(0);
		LexEntry entry = generateValidEntryFromLemmaVersion(lemmaVersion);
		entry.setId(lemmaVersion.getLexEntryId());
		luceneIndexManager.delete(entry);

		result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.NORMAL, "r"), IndexTestHelpers.getPagination());
		Assert.assertEquals(4, result.getTotalElements());
		afterUpdate = luceneIndexManager.getIndexStatistics();
		Assert.assertEquals(4, afterUpdate.getNumberOfEntries());
		result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.EXACT, "r3"), IndexTestHelpers.getPagination());
		Assert.assertEquals(0, result.getTotalElements());
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

	@Test
	public void testSubSemanticSuggestion() throws Exception {
		int numberOfEntries = 36;

		testDropIndex();
		List<LexEntry> entries = new ArrayList<>();
		for(int i = 0;  i < numberOfEntries; i++) {
			LexEntry entry = generateValidEntry();
			entry.getCurrent().putEntryValue("DStichwort", "d" + i);
			entry.getCurrent().putEntryValue("RStichwort", "r" + i);
			entry.getCurrent().putEntryValue("DSubsemantik", "d" + i % 12);
			entry.getCurrent().putEntryValue("RSubsemantik", "r" + i % 12);
			entries.add(entry);
		}
		luceneIndexManager.addToIndex(entries.iterator());

		IndexStatistics afterUpdate = luceneIndexManager.getIndexStatistics();
		Assert.assertEquals(afterUpdate.getNumberOfEntries(), 36);

		ArrayList<String> result = luceneIndexManager.getSuggestionsForField("DSubsemantik", "d", 10);

		Assert.assertEquals(10, result.size());

		Assert.assertEquals("d0", result.get(0));
		Assert.assertEquals("d1", result.get(1));
		Assert.assertEquals("d10", result.get(2));
		Assert.assertEquals("d11", result.get(3));
		Assert.assertEquals("d2", result.get(4));
		Assert.assertEquals("d3", result.get(5));
		Assert.assertEquals("d4", result.get(6));
		Assert.assertEquals("d5", result.get(7));
		Assert.assertEquals("d6", result.get(8));
		Assert.assertEquals("d7", result.get(9));

		result = luceneIndexManager.getSuggestionsForField("RSubsemantik", "r", 5);

		Assert.assertEquals(5, result.size());

		Assert.assertEquals("r0", result.get(0));
		Assert.assertEquals("r1", result.get(1));
		Assert.assertEquals("r10", result.get(2));
		Assert.assertEquals("r11", result.get(3));
		Assert.assertEquals("r2", result.get(4));
	}

	@Test
	public void testGrammarSuggestion() throws Exception {
		int numberOfEntries = 36;

		testDropIndex();
		List<LexEntry> entries = new ArrayList<>();
		for(int i = 0;  i < numberOfEntries; i++) {
			LexEntry entry = generateValidEntry();
			entry.getCurrent().putEntryValue("DStichwort", "d" + i);
			entry.getCurrent().putEntryValue("RStichwort", "r" + i);
			entry.getCurrent().putEntryValue("DGrammatik", "d" + i % 12);
			entry.getCurrent().putEntryValue("RGrammatik", "r" + i % 12);
			entries.add(entry);
		}
		luceneIndexManager.addToIndex(entries.iterator());

		IndexStatistics afterUpdate = luceneIndexManager.getIndexStatistics();
		Assert.assertEquals(afterUpdate.getNumberOfEntries(), 36);

		ArrayList<String> result = luceneIndexManager.getSuggestionsForFieldChoice(SuggestionField.GRAMMAR, "d", 10);

		Assert.assertEquals(10, result.size());

		Assert.assertEquals("d0", result.get(0));
		Assert.assertEquals("d1", result.get(1));
		Assert.assertEquals("d10", result.get(2));
		Assert.assertEquals("d11", result.get(3));
		Assert.assertEquals("d2", result.get(4));
		Assert.assertEquals("d3", result.get(5));
		Assert.assertEquals("d4", result.get(6));
		Assert.assertEquals("d5", result.get(7));
		Assert.assertEquals("d6", result.get(8));
		Assert.assertEquals("d7", result.get(9));

		result = luceneIndexManager.getSuggestionsForFieldChoice(SuggestionField.GRAMMAR, "r", 5);

		Assert.assertEquals(5, result.size());

		Assert.assertEquals("r0", result.get(0));
		Assert.assertEquals("r1", result.get(1));
		Assert.assertEquals("r10", result.get(2));
		Assert.assertEquals("r11", result.get(3));
		Assert.assertEquals("r2", result.get(4));
	}
}
