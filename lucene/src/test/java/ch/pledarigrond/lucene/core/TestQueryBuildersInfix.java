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

public class TestQueryBuildersInfix extends TestQueryBuildersBase {
	
	@Test
	public void testInfixQueryGerman() throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException {
		Page<EntryVersionDto> result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.GERMAN, SearchMethod.INTERN, "haus"), IndexTestHelpers.getPagination());
		String[] found = getStrings(FieldName.DESTICHWORT, result);
		String[] expected = new String[] { "Haus", "Haus", "Haus", "Haus", "Das Haus brennt", "Flohausstellung", "hausen", "Haushalt", "Kranken(haus)", "nach Hause", "Wohnhaus", "zu Hause", "zu Hause", "zu Hause" };
		printArray(found);
		assertArrayEquals(expected, found);
	}

	@Test
	public void testInfixQueryRomansh() throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException {
		Page<EntryVersionDto> result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.INTERN, "chasa"), IndexTestHelpers.getPagination());
		String[] found = getStrings(FieldName.RMSTICHWORT, result);
		String[] expected = new String[] { "chasa", "chasa", "chasa", "a chasa", "a chasa", "a chasa", "a chasa", "a chasa", "anavos a chasa", "avantchasa", "chasan", "encunter chasa", "fatg en chasa", "La chasa arda", "Ve a chasa!" };
		printArray(found);
		assertArrayEquals(expected, found);
	}

	@Test
	public void testInfixQueryBoth() throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException {
		Page<EntryVersionDto> result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.BOTH, SearchMethod.INTERN, "chasa"), IndexTestHelpers.getPagination());
		String[] found = getStrings(FieldName.RMSTICHWORT, result);
		String[] expected = new String[] { "chasa", "chasa", "chasa", "a chasa", "a chasa", "La chasa arda", "encunter chasa", "chasan", "Ve a chasa!", "a chasa", "fatg en chasa", "avantchasa", "a chasa", "a chasa", "anavos a chasa" };
		printArray(found);
		assertArrayEquals(expected, found);

		result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.BOTH, SearchMethod.INTERN, "haus"), IndexTestHelpers.getPagination());
		found = getStrings(FieldName.DESTICHWORT, result);
		expected = new String[] { "Haus", "Haus", "Haus", "Haus", "Das Haus brennt", "Flohausstellung", "hausen", "Haushalt", "Kranken(haus)", "nach Hause", "Wohnhaus", "zu Hause", "zu Hause", "zu Hause" };
		printArray(found);
		assertArrayEquals(expected, found);
	}
}
