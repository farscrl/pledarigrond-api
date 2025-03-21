package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.data.common.DictionaryLanguage;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class TestQueryExactMatchSearch extends TestQueryBuildersBase {

	@Test
	public void testSearchExactMatches() throws NoIndexAvailableException, BrokenIndexException, InvalidQueryException {
		Page<EntryVersionDto> result = luceneIndexManager.searchExactMatches("haus", DictionaryLanguage.GERMAN);
		String[] found = getStrings(FieldName.DESTICHWORT, result);
		String[] expected = new String[] { "Haus", "Haus", "Haus", "Haus" };
		printArray(found);
		assertArrayEquals(expected, found);
	}
}
