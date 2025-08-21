package ch.pledarigrond.lucene.suggestions;

import ch.pledarigrond.common.config.LuceneConfiguration;
import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.lucene.analyzers.CaseInsensitiveStandardTokenizer;
import ch.pledarigrond.lucene.util.FN;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SuggestionsIndex {

    protected static final Logger logger = LoggerFactory.getLogger(SuggestionsIndex.class);

    private final LuceneConfiguration luceneConfiguration;
    private final Directory spellIndexDirectoryRm;
    private final Directory spellIndexDirectoryDe;
    private SpellChecker spellCheckerRm;
    private SpellChecker spellCheckerDe;

    public SuggestionsIndex(LuceneConfiguration luceneConfiguration) throws IOException {
        this.luceneConfiguration = luceneConfiguration;

        spellIndexDirectoryRm = FSDirectory.open(luceneConfiguration.getLuceneSuggestionIndexDirRm().toPath());
        spellIndexDirectoryDe = FSDirectory.open(luceneConfiguration.getLuceneSuggestionIndexDirDe().toPath());

        spellCheckerRm = new SpellChecker(spellIndexDirectoryRm);
        spellCheckerDe = new SpellChecker(spellIndexDirectoryDe);

        logger.info("Initialized suggestions index.");
    }

    public void reIndex() throws IOException {
        deleteCurrentIndexContents();

        // release locks if present
        releaseLocksIfPresent(spellIndexDirectoryRm);
        releaseLocksIfPresent(spellIndexDirectoryDe);

        // Re-initialize SpellChecker instances and readers for the newly created suggestion index
        spellCheckerRm = new SpellChecker(spellIndexDirectoryRm);
        spellCheckerDe = new SpellChecker(spellIndexDirectoryDe);

        // Open the main index reader
        Directory indexDirectory = FSDirectory.open(luceneConfiguration.getLuceneIndexDir().toPath());
        IndexReader reader = DirectoryReader.open(indexDirectory);

        IndexReader readerRm = DirectoryReader.open(spellIndexDirectoryRm);
        IndexReader readerDe = DirectoryReader.open(spellIndexDirectoryDe);

        // Use the SpellChecker's indexDictionary method to build the suggestion index
        spellCheckerDe.indexDictionary(new LuceneDictionary(reader, FN.deStichwortDict), new IndexWriterConfig(new CaseInsensitiveStandardTokenizer()), true);
        spellCheckerRm.indexDictionary(new LuceneDictionary(reader, FN.rmStichwortDict), new IndexWriterConfig(new CaseInsensitiveStandardTokenizer()), true);

        logIndexedWords();

        // Close resources
        spellCheckerRm.close();
        spellCheckerDe.close();
        reader.close();
        readerRm.close();
        readerDe.close();

        spellCheckerRm = new SpellChecker(spellIndexDirectoryRm);
        spellCheckerDe = new SpellChecker(spellIndexDirectoryDe);
    }

    public String[] suggestSimilar(String word, int numSuggestions, SearchDirection direction) {
        try {
            if (direction == SearchDirection.GERMAN) {
                return spellCheckerDe.suggestSimilar(word, numSuggestions, 0.6F);
            } else if (direction == SearchDirection.ROMANSH) {
                return prioritizeSuggestions(word, spellCheckerRm.suggestSimilar(word, numSuggestions, 0.6F)).toArray(new String[0]);
            } else {
                List<String> suggestions = new ArrayList<>();
                suggestions.addAll(Arrays.asList(spellCheckerDe.suggestSimilar(word, numSuggestions, 0.6F)));
                suggestions.addAll(prioritizeSuggestions(word, spellCheckerRm.suggestSimilar(word, numSuggestions, 0.6F)));
                return suggestions.toArray(new String[0]);
            }
        } catch (IOException | AlreadyClosedException e) {
            logger.error("Error while suggesting similar words: {}", e.getMessage());
            return new String[0];
        }
    }

    private void logIndexedWords() throws IOException {
        try (IndexReader spellIndexReader = DirectoryReader.open(spellIndexDirectoryRm)) {
            logger.info("Total number of documents in RM spell index: " + spellIndexReader.numDocs());
        }

        try (IndexReader spellIndexReader = DirectoryReader.open(spellIndexDirectoryDe)) {
            logger.info("Total number of documents in DE spell index: " + spellIndexReader.numDocs());
        }
    }

    private void deleteCurrentIndexContents() throws IOException {
        // delete current content
        Directory suggestionsDirectoryRm = FSDirectory.open(luceneConfiguration.getLuceneSuggestionIndexDirRm().toPath());
        Directory suggestionsDirectoryDe = FSDirectory.open(luceneConfiguration.getLuceneSuggestionIndexDirDe().toPath());

        IndexWriterConfig configRm = new IndexWriterConfig();
        IndexWriterConfig configDe = new IndexWriterConfig();

        IndexWriter writerRm = new IndexWriter(suggestionsDirectoryRm, configRm);
        writerRm.deleteDocuments(new MatchAllDocsQuery());
        writerRm.commit();
        writerRm.close();

        IndexWriter writerDe = new IndexWriter(suggestionsDirectoryDe, configDe);
        writerDe.deleteDocuments(new MatchAllDocsQuery());
        writerDe.commit();
        writerDe.close();

        logger.info("Deleted current content of index.");
    }

    /**
     * In some cases, some suggestions should be prioritized over others. This method allows to do so. Currently,
     * the following rules are taken into account:
     * - If the search word starts with "tg" and the suggestion starts with "ch", the suggestion is prioritized.
     */
    static List<String> prioritizeSuggestions(String searchWord, String[] suggestions) {
        List<String> prioritized = new ArrayList<>();
        List<String> others = new ArrayList<>();

        for (String suggestion : suggestions) {
            if (searchWord.startsWith("tg") && suggestion.startsWith("ch")) {
                prioritized.add(suggestion);
            } else {
                others.add(suggestion);
            }
        }

        prioritized.addAll(others);
        return prioritized;
    }

    private static void releaseLocksIfPresent(Directory directory) {
        try {
            Lock lock = directory.obtainLock(IndexWriter.WRITE_LOCK_NAME);
            lock.close();
        } catch (LockObtainFailedException e) {
            logger.error("Lock not obtained: {}", e.getMessage());
        } catch (IOException e) {
            logger.error("IOException: {}", e.getMessage());
        }
    }
}
