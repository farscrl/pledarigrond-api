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

public class TestQueryBuildersPrefix extends TestQueryBuildersBase {
	
	@Test
	public void testPrefixQueryGerman() throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException {
		Page<LemmaVersion> result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.GERMAN, SearchMethod.PREFIX, "haus"), IndexTestHelpers.getPagination());
		String[] found = getStrings("DStichwort", result);
		String[] expected = new String[] { "Haus", "Haus", "Haus", "Haus", "hausen", "Haushalt" };
		printArray(found);
		assertArrayEquals(expected, found);
	}

	@Test
	public void testPrefixQueryRomansh() throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException {
		Page<LemmaVersion> result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.ROMANSH, SearchMethod.PREFIX, "chasa"), IndexTestHelpers.getPagination());
		String[] found = getStrings("RStichwort", result);
		String[] expected = new String[] { "chasa", "chasa", "chasa", "chasan" };
		printArray(found);
		assertArrayEquals(expected, found);
	}

	@Test
	public void testPrefixQueryBoth() throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException {
		Page<LemmaVersion> result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.BOTH, SearchMethod.PREFIX, "chasa"), IndexTestHelpers.getPagination());
		String[] found = getStrings("RStichwort", result);
		String[] expected = new String[] { "chasa", "chasa", "chasa", "chasan" };
		printArray(found);
		assertArrayEquals(expected, found);

		result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.BOTH, SearchMethod.PREFIX, "haus"), IndexTestHelpers.getPagination());
		found = getStrings("DStichwort", result);
		expected = new String[] { "Haus", "Haus", "Haus", "Haus", "hausen", "Haushalt" };
		printArray(found);
		assertArrayEquals(expected, found);
	}
}
