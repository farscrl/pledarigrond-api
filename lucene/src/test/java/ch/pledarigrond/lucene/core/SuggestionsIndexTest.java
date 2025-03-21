package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.lucene.suggestions.SuggestionsIndex;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuggestionsIndexTest extends BaseLuceneIndexTest {

    @Test
    public void testCreateIndex() throws Exception  {
        luceneIndexManager.dropIndex();
        List<EntryDto> entries = new ArrayList<>();

        EntryVersionDto lv = generateValidEntryVersionDto("Test", "test");
        EntryDto entry = generateValidEntryFromEntryVersionDto(lv);
        entries.add(entry);
        lv = generateValidEntryVersionDto("Rest", "rest");
        entry = generateValidEntryFromEntryVersionDto(lv);
        entries.add(entry);
        lv = generateValidEntryVersionDto("Igel", "erizun");
        entry = generateValidEntryFromEntryVersionDto(lv);
        entries.add(entry);
        lv = generateValidEntryVersionDto("Hund", "chaun");
        entry = generateValidEntryFromEntryVersionDto(lv);
        entries.add(entry);
        lv = generateValidEntryVersionDto("Löwe", "liun");
        entry = generateValidEntryFromEntryVersionDto(lv);
        entries.add(entry);

        luceneIndexManager.addToIndex(entries.stream());
        IndexStatistics statistics = luceneIndexManager.getIndexStatistics();
        assertEquals(5, statistics.getNumberOfEntries());

        SuggestionsIndex suggestionsIndex = new SuggestionsIndex(luceneConfiguration);
        suggestionsIndex.reIndex();

        String[] suggestions = suggestionsIndex.suggestSimilar("Testt", 1, SearchDirection.GERMAN);
        assertEquals(1, suggestions.length);
        assertEquals("Test", suggestions[0]);

        suggestions = suggestionsIndex.suggestSimilar("testt", 1, SearchDirection.ROMANSH);
        assertEquals(1, suggestions.length);
        assertEquals("test", suggestions[0]);

        suggestions = suggestionsIndex.suggestSimilar("löwe", 1, SearchDirection.GERMAN);
        assertEquals(1, suggestions.length);
        assertEquals("Löwe", suggestions[0]);

        suggestions = suggestionsIndex.suggestSimilar("tgaun", 1, SearchDirection.ROMANSH);
        assertEquals(1, suggestions.length);
        assertEquals("chaun", suggestions[0]);
    }

}
