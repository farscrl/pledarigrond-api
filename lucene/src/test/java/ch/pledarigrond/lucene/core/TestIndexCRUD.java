package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.common.SearchMethod;
import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.common.data.lucene.SuggestionField;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TestIndexCRUD extends BaseLuceneIndexTest {



	@Test
	public void testGetStatistics() {
		IndexStatistics stats = luceneIndexManager.getIndexStatistics();
		assertNotNull(stats);
	}
	
	@Test
	public void testDropIndex() throws Exception  {
		luceneIndexManager.dropIndex();
		IndexStatistics statistics = luceneIndexManager.getIndexStatistics();
		assertEquals(0, statistics.getNumberOfEntries());

		List<EntryDto> entries = new ArrayList<>();
		for(int i = 0;  i < 5; i++) {
			entries.add(generateValidEntry());
		}
		luceneIndexManager.addToIndex(entries.stream());
		statistics = luceneIndexManager.getIndexStatistics();
		assertEquals(entries.size(),statistics.getNumberOfEntries());

		luceneIndexManager.dropIndex();
		statistics = luceneIndexManager.getIndexStatistics();
		assertEquals(0, statistics.getNumberOfEntries());
	}
	
	
	@Test
	public void testCreateIndex() throws Exception  {
		luceneIndexManager.dropIndex();
		List<EntryDto> entries = new ArrayList<>();
		for(int i = 0;  i < 5; i++) {
			entries.add(generateValidEntry());
		}
		luceneIndexManager.addToIndex(entries.stream());
		IndexStatistics statistics = luceneIndexManager.getIndexStatistics();
		assertEquals(entries.size(),statistics.getNumberOfEntries());
	}
	
	@Test
	public void testUpdateIndex() throws Exception  {
		testCreateIndex();
		IndexStatistics beforeUpdate = luceneIndexManager.getIndexStatistics();
		List<EntryDto> entries = new ArrayList<>();
		for(int i = 0;  i < 5; i++) {
			entries.add(generateValidEntry());
		}
		luceneIndexManager.addToIndex(entries.stream());
		IndexStatistics afterUpdate = luceneIndexManager.getIndexStatistics();
		assertEquals(afterUpdate.getNumberOfEntries(), beforeUpdate.getNumberOfEntries() * 2);
	}

	@Test
	public void testUpdateEntityIndex() throws Exception  {
		luceneIndexManager.dropIndex();
		List<EntryDto> entries = new ArrayList<>();
		for(int i = 0;  i < 5; i++) {
			EntryDto entry = generateValidEntry();
			entry.getCurrent().setDeStichwort("d" + i);
			entry.getCurrent().setRmStichwort("r" + i);
			entries.add(entry);
		}
		luceneIndexManager.addToIndex(entries.stream());
		IndexStatistics afterUpdate = luceneIndexManager.getIndexStatistics();
		assertEquals(5, afterUpdate.getNumberOfEntries());

		Page<EntryVersionDto> result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.EXACT, "r2"), IndexTestHelpers.getPagination());
		assertEquals(1, result.getTotalElements());

		// Update existing lemma version
		EntryVersionDto EntryVersionDto = result.getContent().get(0);
		EntryVersionDto.setRmStichwort("r22");
		EntryDto entry = generateValidEntryFromEntryVersionDto(EntryVersionDto);
		entry.setEntryId(EntryVersionDto.getEntryId());
		luceneIndexManager.update(entry);

		result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.EXACT, "r2"), IndexTestHelpers.getPagination());
		assertEquals(0, result.getTotalElements());
		result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.EXACT, "r22"), IndexTestHelpers.getPagination());
		assertEquals(1, result.getTotalElements());

		// adding modified version
		EntryVersionDto = result.getContent().get(0);
		entry = generateValidEntryFromEntryVersionDto(EntryVersionDto);
		entry.setEntryId(EntryVersionDto.getEntryId());

		EntryVersionDto modifiedEntryVersionDto = new EntryVersionDto();
		modifiedEntryVersionDto.setDeStichwort("d22");
		modifiedEntryVersionDto.setRmStichwort("r22");
		entry.setCurrent(modifiedEntryVersionDto);
		luceneIndexManager.update(entry);

		result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.EXACT, "r22"), IndexTestHelpers.getPagination());
		assertEquals(1, result.getTotalElements());

		// accept one version
		EntryVersionDto acceptedVersion = result.getContent().get(0);

		entry = generateValidEntryFromEntryVersionDto(acceptedVersion);
		entry.setEntryId(acceptedVersion.getEntryId());
		luceneIndexManager.update(entry);

		result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.EXACT, "r22"), IndexTestHelpers.getPagination());
		assertEquals(1, result.getTotalElements());
	}

	@Test
	public void testDeleteEntryFromIndex() throws Exception  {
		luceneIndexManager.dropIndex();
		List<EntryDto> entries = new ArrayList<>();
		for(int i = 0;  i < 5; i++) {
			EntryDto entry = generateValidEntry();
			entry.getCurrent().setDeStichwort("d" + i);
			entry.getCurrent().setRmStichwort("r" + i);
			entries.add(entry);
		}
		luceneIndexManager.addToIndex(entries.stream());
		IndexStatistics afterUpdate = luceneIndexManager.getIndexStatistics();
		assertEquals(5, afterUpdate.getNumberOfEntries());

		Page<EntryVersionDto> result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.EXACT, "r3"), IndexTestHelpers.getPagination());
		assertEquals(1, result.getTotalElements());

		// Delete existing lemma version
		EntryVersionDto entryVersionDto = result.getContent().get(0);
		EntryDto entry = generateValidEntryFromEntryVersionDto(entryVersionDto);
		entry.setEntryId(entryVersionDto.getEntryId());
		luceneIndexManager.delete(entry);

		result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.NORMAL, "r"), IndexTestHelpers.getPagination());
		assertEquals(4, result.getTotalElements());
		afterUpdate = luceneIndexManager.getIndexStatistics();
		assertEquals(4, afterUpdate.getNumberOfEntries());
		result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.EXACT, "r3"), IndexTestHelpers.getPagination());
		assertEquals(0, result.getTotalElements());
	}

	@Test
	public void testSortOrder() throws Exception {
		// The number must be greater than 10 to cover the case with several digits.
		int numberOfEntries = 12;

		luceneIndexManager.dropIndex();
		List<EntryDto> entries = new ArrayList<>();
		for(int i = 0;  i < numberOfEntries; i++) {
			EntryDto entry = generateValidEntry();
			entry.getCurrent().setDeStichwort("a" + i);
			entry.getCurrent().setRmStichwort("b");
			entry.getCurrent().setRmStichwortSort(String.valueOf(numberOfEntries + 1 - i));
			entries.add(entry);
		}
		luceneIndexManager.addToIndex(entries.stream());

		Page<EntryVersionDto> result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.NORMAL, "b"), IndexTestHelpers.getPagination());

		assertEquals(numberOfEntries, result.getTotalElements());

		for(int i = 0;  i < numberOfEntries; i++) {
			assertEquals("a" + (numberOfEntries - i - 1), result.getContent().get(i).getDeStichwort());
		}
	}

	@Test
	public void testSubSemanticSuggestion() throws Exception {
		int numberOfEntries = 36;

		luceneIndexManager.dropIndex();
		List<EntryDto> entries = new ArrayList<>();
		for(int i = 0;  i < numberOfEntries; i++) {
			EntryDto entry = generateValidEntry();
			entry.getCurrent().setDeStichwort("d" + i);
			entry.getCurrent().setRmStichwort("r" + i);
			entry.getCurrent().setDeSubsemantik("d" + i % 12);
			entry.getCurrent().setRmSubsemantik("r" + i % 12);
			entries.add(entry);
		}
		luceneIndexManager.addToIndex(entries.stream());

		IndexStatistics afterUpdate = luceneIndexManager.getIndexStatistics();
		assertEquals(36, afterUpdate.getNumberOfEntries());

		ArrayList<String> result = luceneIndexManager.getSuggestionsForField("deSubsemantik", "d", 10);

		assertEquals(10, result.size());

		assertEquals("d0", result.get(0));
		assertEquals("d1", result.get(1));
		assertEquals("d2", result.get(2));
		assertEquals("d3", result.get(3));
		assertEquals("d4", result.get(4));
		assertEquals("d5", result.get(5));
		assertEquals("d6", result.get(6));
		assertEquals("d7", result.get(7));
		assertEquals("d8", result.get(8));
		assertEquals("d9", result.get(9));

		result = luceneIndexManager.getSuggestionsForField("rmSubsemantik", "r", 5);

		assertEquals(5, result.size());

		assertEquals("r0", result.get(0));
		assertEquals("r1", result.get(1));
		assertEquals("r2", result.get(2));
		assertEquals("r3", result.get(3));
		assertEquals("r4", result.get(4));
	}

	@Test
	public void testCategorySuggestions() throws Exception {

		luceneIndexManager.dropIndex();
		List<EntryDto> entries = new ArrayList<>();

		EntryDto entry1 = generateValidEntry();
		entry1.getCurrent().setDeStichwort("d" + 1);
		entry1.getCurrent().setRmStichwort("r" + 1);
		entry1.getCurrent().setCategories("stringA, stringB, stringC");
		entries.add(entry1);

		EntryDto entry2 = generateValidEntry();
		entry2.getCurrent().setDeStichwort("d" + 2);
		entry2.getCurrent().setRmStichwort("r" + 2);
		entry2.getCurrent().setCategories("stringB, stringC, stringD");
		entries.add(entry2);

		luceneIndexManager.addToIndex(entries.stream());

		IndexStatistics afterUpdate = luceneIndexManager.getIndexStatistics();
		assertEquals(2, afterUpdate.getNumberOfEntries());

		ArrayList<String> result = luceneIndexManager.getSuggestionsForField("categories", "s", 10);

		assertEquals(4, result.size());

		assertEquals("stringA", result.get(0));
		assertEquals("stringB", result.get(1));
		assertEquals("stringC", result.get(2));
		assertEquals("stringD", result.get(3));
	}

	@Test
	public void testGrammarSuggestion() throws Exception {
		int numberOfEntries = 36;

		luceneIndexManager.dropIndex();
		List<EntryDto> entries = new ArrayList<>();
		for(int i = 0;  i < numberOfEntries; i++) {
			EntryDto entry = generateValidEntry();
			entry.getCurrent().setDeStichwort("d" + i);
			entry.getCurrent().setRmStichwort("r" + i);
			entry.getCurrent().setDeGrammatik("d" + i % 12);
			entry.getCurrent().setRmGrammatik("r" + i % 12);
			entries.add(entry);
		}
		luceneIndexManager.addToIndex(entries.stream());

		IndexStatistics afterUpdate = luceneIndexManager.getIndexStatistics();
		assertEquals(36, afterUpdate.getNumberOfEntries());

		ArrayList<String> result = luceneIndexManager.getSuggestionsForFieldChoice(SuggestionField.GRAMMAR, "d", 10);

		assertEquals(10, result.size());

		assertEquals("d0", result.get(0));
		assertEquals("d1", result.get(1));
		assertEquals("d10", result.get(2));
		assertEquals("d11", result.get(3));
		assertEquals("d2", result.get(4));
		assertEquals("d3", result.get(5));
		assertEquals("d4", result.get(6));
		assertEquals("d5", result.get(7));
		assertEquals("d6", result.get(8));
		assertEquals("d7", result.get(9));

		result = luceneIndexManager.getSuggestionsForFieldChoice(SuggestionField.GRAMMAR, "r", 5);

		assertEquals(5, result.size());

		assertEquals("r0", result.get(0));
		assertEquals("r1", result.get(1));
		assertEquals("r10", result.get(2));
		assertEquals("r11", result.get(3));
		assertEquals("r2", result.get(4));
	}
}
