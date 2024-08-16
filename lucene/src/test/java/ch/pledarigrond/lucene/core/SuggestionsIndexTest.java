package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.lucene.suggestions.SuggestionsIndex;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SuggestionsIndexTest extends BaseLuceneIndexTest {

    @Test
    public void testCreateIndex() throws Exception  {
        luceneIndexManager.dropIndex();
        List<LexEntry> entries = new ArrayList<>();

        LemmaVersion lv = generateValidLemmaVersion("Test", "test");
        LexEntry entry = generateValidEntryFromLemmaVersion(lv);
        entries.add(entry);
        lv = generateValidLemmaVersion("Rest", "rest");
        entry = generateValidEntryFromLemmaVersion(lv);
        entries.add(entry);
        lv = generateValidLemmaVersion("Igel", "erizun");
        entry = generateValidEntryFromLemmaVersion(lv);
        entries.add(entry);
        lv = generateValidLemmaVersion("Hund", "chaun");
        entry = generateValidEntryFromLemmaVersion(lv);
        entries.add(entry);
        lv = generateValidLemmaVersion("Löwe", "liun");
        entry = generateValidEntryFromLemmaVersion(lv);
        entries.add(entry);

        luceneIndexManager.addToIndex(entries.iterator());
        IndexStatistics statistics = luceneIndexManager.getIndexStatistics();
        Assert.assertEquals(5, statistics.getNumberOfEntries());

        SuggestionsIndex suggestionsIndex = new SuggestionsIndex(luceneConfiguration);
        suggestionsIndex.reIndex();

        String[] suggestions = suggestionsIndex.suggestSimilar("Testt", 1, SearchDirection.ROMANSH);
        Assert.assertEquals(1, suggestions.length);
        Assert.assertEquals("Test", suggestions[0]);

        suggestions = suggestionsIndex.suggestSimilar("testt", 1, SearchDirection.ROMANSH);
        Assert.assertEquals(1, suggestions.length);
        Assert.assertEquals("test", suggestions[0]);

        suggestions = suggestionsIndex.suggestSimilar("löwe", 1, SearchDirection.ROMANSH);
        Assert.assertEquals(1, suggestions.length);
        Assert.assertEquals("Löwe", suggestions[0]);

        suggestions = suggestionsIndex.suggestSimilar("tgaun", 1, SearchDirection.ROMANSH);
        Assert.assertEquals(1, suggestions.length);
        Assert.assertEquals("chaun", suggestions[0]);
    }

}
