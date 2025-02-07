package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.common.SearchMethod;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.common.data.lucene.SuggestionField;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;


public class TestIndexCRUD extends BaseLuceneIndexTest {



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

		List<LexEntry> entries = new ArrayList<>();
		for(int i = 0;  i < 5; i++) {
			entries.add(generateValidEntry());
		}
		luceneIndexManager.addToIndex(entries.iterator());
		statistics = luceneIndexManager.getIndexStatistics();
		Assert.assertEquals(entries.size(),statistics.getNumberOfEntries());

		luceneIndexManager.dropIndex();
		statistics = luceneIndexManager.getIndexStatistics();
		Assert.assertEquals(0, statistics.getNumberOfEntries());
	}
	
	
	@Test
	public void testCreateIndex() throws Exception  {
		luceneIndexManager.dropIndex();
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
		luceneIndexManager.dropIndex();
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

		// accept one version
		LemmaVersion acceptedVersion = result.getContent().get(0);
		acceptedVersion.setVerification(LemmaVersion.Verification.ACCEPTED);

		entry = generateValidEntryFromLemmaVersion(acceptedVersion);
		entry.setId(acceptedVersion.getLexEntryId());
		luceneIndexManager.update(entry);

		result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.EXACT, "r22"), IndexTestHelpers.getPagination());
		Assert.assertEquals(1, result.getTotalElements());
	}

	@Test
	public void testDeleteEntryFromIndex() throws Exception  {
		luceneIndexManager.dropIndex();
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

		luceneIndexManager.dropIndex();
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

		luceneIndexManager.dropIndex();
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
		Assert.assertEquals("d2", result.get(2));
		Assert.assertEquals("d3", result.get(3));
		Assert.assertEquals("d4", result.get(4));
		Assert.assertEquals("d5", result.get(5));
		Assert.assertEquals("d6", result.get(6));
		Assert.assertEquals("d7", result.get(7));
		Assert.assertEquals("d8", result.get(8));
		Assert.assertEquals("d9", result.get(9));

		result = luceneIndexManager.getSuggestionsForField("RSubsemantik", "r", 5);

		Assert.assertEquals(5, result.size());

		Assert.assertEquals("r0", result.get(0));
		Assert.assertEquals("r1", result.get(1));
		Assert.assertEquals("r2", result.get(2));
		Assert.assertEquals("r3", result.get(3));
		Assert.assertEquals("r4", result.get(4));
	}

	@Test
	public void testCategorySuggestions() throws Exception {

		luceneIndexManager.dropIndex();
		List<LexEntry> entries = new ArrayList<>();

		LexEntry entry1 = generateValidEntry();
		entry1.getCurrent().putEntryValue("DStichwort", "d" + 1);
		entry1.getCurrent().putEntryValue("RStichwort", "r" + 1);
		entry1.getCurrent().putEntryValue("categories", "stringA, stringB, stringC");
		entries.add(entry1);

		LexEntry entry2 = generateValidEntry();
		entry2.getCurrent().putEntryValue("DStichwort", "d" + 2);
		entry2.getCurrent().putEntryValue("RStichwort", "r" + 2);
		entry2.getCurrent().putEntryValue("categories", "stringB, stringC, stringD");
		entries.add(entry2);

		luceneIndexManager.addToIndex(entries.iterator());

		IndexStatistics afterUpdate = luceneIndexManager.getIndexStatistics();
		Assert.assertEquals(afterUpdate.getNumberOfEntries(), 2);

		ArrayList<String> result = luceneIndexManager.getSuggestionsForField("categories", "s", 10);

		Assert.assertEquals(4, result.size());

		Assert.assertEquals("stringA", result.get(0));
		Assert.assertEquals("stringB", result.get(1));
		Assert.assertEquals("stringC", result.get(2));
		Assert.assertEquals("stringD", result.get(3));
	}

	@Test
	public void testGrammarSuggestion() throws Exception {
		int numberOfEntries = 36;

		luceneIndexManager.dropIndex();
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
