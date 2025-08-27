package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.common.SearchMethod;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class TestQueryBuildersSuffix extends TestQueryBuildersBase {
	
	@Test
	public void testSuffixQueryGerman() throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException {
		Page<EntryVersionDto> result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.GERMAN, SearchMethod.SUFFIX, "aus"), IndexTestHelpers.getPagination());
		String[] found = getStrings(FieldName.DESTICHWORT, result);
		String[] expected = new String[] { "Haus", "Haus", "Haus", "Haus", "Kranken(haus)", "Wohnhaus" };
		printArray(found);
		assertArrayEquals(expected, found);
	}

	@Test
	public void testSuffixQueryRomansh() throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException {
		Page<EntryVersionDto> result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.SUFFIX, "asa"), IndexTestHelpers.getPagination());
		String[] found = getStrings(FieldName.RMSTICHWORT, result);
		String[] expected = new String[] { "a chasa", "a chasa", "a chasa", "a chasa", "a chasa", "anavos a chasa", "avantchasa", "cabasa", "chasa", "chasa", "chasa", "encunter chasa", "fatg en chasa", "Ve a chasa!" };
		printArray(found);
		assertArrayEquals(expected, found);
	}

	@Test
	public void testSuffixQueryBoth() throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException {
		Page<EntryVersionDto> result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.BOTH, SearchMethod.SUFFIX, "asa"), IndexTestHelpers.getPagination());
		String[] found = getStrings(FieldName.RMSTICHWORT, result);
		String[] expected = new String[] { "cabasa", "a chasa", "a chasa", "chasa", "chasa", "encunter chasa", "Ve a chasa!", "a chasa", "fatg en chasa", "avantchasa", "chasa", "a chasa", "a chasa", "anavos a chasa" };
		printArray(found);
		assertArrayEquals(expected, found);

		result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.BOTH, SearchMethod.SUFFIX, "aus"), IndexTestHelpers.getPagination());
		found = getStrings(FieldName.DESTICHWORT, result);
		expected = new String[] { "Beifall", "Haus", "Haus", "Haus", "Haus", "Kranken(haus)", "Wohnhaus" };
		printArray(found);
		assertArrayEquals(expected, found);
	}
}
