package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.data.common.DictionaryLanguage;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import org.junit.Test;
import org.springframework.data.domain.Page;

import static org.junit.Assert.assertArrayEquals;

public class TestQueryExactMatchSearch extends TestQueryBuildersBase {

	@Test
	public void testSearchExactMatches() throws NoIndexAvailableException, BrokenIndexException, InvalidQueryException {
		Page<LemmaVersion> result = luceneIndexManager.searchExactMatches("haus", DictionaryLanguage.GERMAN);
		String[] found = getStrings("DStichwort", result);
		String[] expected = new String[] { "Haus", "Haus", "Haus", "Haus" };
		printArray(found);
		assertArrayEquals(expected, found);
	}
}
