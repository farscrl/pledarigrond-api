package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.common.SearchMethod;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import org.junit.Test;
import org.springframework.data.domain.Page;

import static org.junit.Assert.assertArrayEquals;

public class TestQueryBuildersDefault extends TestQueryBuildersBase {
	
	@Test
	public void testDefaultQueryGerman() throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException {
		Page<LemmaVersion> result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.GERMAN, SearchMethod.NORMAL, "haus"), IndexTestHelpers.getPagination());
		String[] found = getStrings("DStichwort", result);
		String[] expected = new String[] {"Haus", "Haus", "Haus", "Haus", "Das Haus brennt", "Kranken(haus)", "hausen", "Haushalt"};
		printArray(found);
		assertArrayEquals(expected, found);
	}

	@Test
	public void testDefaultQueryRomansh() throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException {
		Page<LemmaVersion> result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.NORMAL, "chasa"), IndexTestHelpers.getPagination());
		String[] found = getStrings("RStichwort", result);
		String[] expected = new String[] { "chasa", "chasa", "chasa", "a chasa", "a chasa", "a chasa", "a chasa", "a chasa", "anavos a chasa", "encunter chasa", "fatg en chasa", "La chasa arda", "Ve a chasa!", "chasan" };
		printArray(found);
		assertArrayEquals(expected, found);
	}

	@Test
	public void testDefaultQueryBoth() throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException {
		Page<LemmaVersion> result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.BOTH, SearchMethod.NORMAL, "chasa"), IndexTestHelpers.getPagination());
		String[] found = getStrings("RStichwort", result);
		String[] expected = new String[] { "chasa", "chasa", "chasa", "a chasa", "a chasa", "La chasa arda", "encunter chasa", "Ve a chasa!", "a chasa", "fatg en chasa", "a chasa", "anavos a chasa", "a chasa", "chasan" };
		printArray(found);
		assertArrayEquals(expected, found);

		result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.BOTH, SearchMethod.NORMAL, "haus"), IndexTestHelpers.getPagination());
		found = getStrings("DStichwort", result);

		expected = new String[] {"Haus", "Haus", "Haus", "Haus", "Das Haus brennt", "Kranken(haus)", "hausen", "Haushalt"};
		printArray(found);
		assertArrayEquals(expected, found);
	}
}