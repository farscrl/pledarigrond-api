package ch.pledarigrond.lucene.suggestions;

import ch.pledarigrond.common.config.LuceneConfiguration;
import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.lucene.analyzers.CaseInsensitiveStandardTokenizer;
import ch.pledarigrond.lucene.util.FN;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.AlreadyClosedException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SuggestionsIndex {

    protected static final Logger logger = LoggerFactory.getLogger(SuggestionsIndex.class);

    private static final long READ_LOCK_TIMEOUT_MS = 10L; // tweak as desired
    private static final String[] EMPTY = new String[0];

    private final LuceneConfiguration luceneConfiguration;

    private final Directory spellIndexDirectoryRm;
    private final Directory spellIndexDirectoryDe;

    private final SpellChecker spellCheckerRm;
    private final SpellChecker spellCheckerDe;

    private final ReadWriteLock scLock = new ReentrantReadWriteLock();

    public SuggestionsIndex(LuceneConfiguration luceneConfiguration) throws IOException {
        this.luceneConfiguration = luceneConfiguration;

        spellIndexDirectoryRm = FSDirectory.open(luceneConfiguration.getLuceneSuggestionIndexDirRm().toPath());
        spellIndexDirectoryDe = FSDirectory.open(luceneConfiguration.getLuceneSuggestionIndexDirDe().toPath());

        spellCheckerRm = new SpellChecker(spellIndexDirectoryRm);
        spellCheckerDe = new SpellChecker(spellIndexDirectoryDe);

        logger.info("Initialized suggestions index.");
    }

    public void reIndex() throws IOException {
        scLock.writeLock().lock();
        try {
            // Open the main index reader (close automatically)
            try (Directory mainDir = FSDirectory.open(luceneConfiguration.getLuceneIndexDir().toPath());
                 IndexReader mainReader = DirectoryReader.open(mainDir)) {

                // Clear existing suggestion indices (safer than writing via external writers)
                try {
                    spellCheckerDe.clearIndex();
                } catch (UnsupportedOperationException uoe) {
                    // Fallback for very old Lucene: delete via writer
                    deleteAllFromDirectory(spellIndexDirectoryDe);
                }
                try {
                    spellCheckerRm.clearIndex();
                } catch (UnsupportedOperationException uoe) {
                    deleteAllFromDirectory(spellIndexDirectoryRm);
                }

                // Build suggestion indices from fields in the main index
                IndexWriterConfig deCfg = new IndexWriterConfig(new CaseInsensitiveStandardTokenizer());
                IndexWriterConfig rmCfg = new IndexWriterConfig(new CaseInsensitiveStandardTokenizer());

                spellCheckerDe.indexDictionary(new LuceneDictionary(mainReader, FN.deStichwortDict), deCfg, true);
                spellCheckerRm.indexDictionary(new LuceneDictionary(mainReader, FN.rmStichwortDict), rmCfg, true);
            }

            // Optional: log counts
            logIndexedWords();
        } finally {
            scLock.writeLock().unlock();
        }
    }

    public String[] suggestSimilar(String word, int numSuggestions, SearchDirection direction) {
        boolean locked = false;
        try {
            locked = scLock.readLock().tryLock(READ_LOCK_TIMEOUT_MS, TimeUnit.MILLISECONDS);
            if (!locked) {
                // Index is busy (likely a rebuild). Skip suggestions quickly.
                logger.debug("Suggestions skipped: read lock timeout ({} ms) for language {}", READ_LOCK_TIMEOUT_MS, luceneConfiguration.getLanguage());
                return EMPTY;
            }

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
            logger.debug("Suggestions skipped due to exception: {}. language: {}", e.toString(), luceneConfiguration.getLanguage());
            return EMPTY;
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            logger.debug("Suggestions skipped: interrupted while waiting for read lock for language {}", luceneConfiguration.getLanguage());
            return EMPTY;
        } finally {
            if (locked) {
                scLock.readLock().unlock();
            }
        }
    }

    private void logIndexedWords() throws IOException {
        try (IndexReader spellIndexReader = DirectoryReader.open(spellIndexDirectoryRm)) {
            logger.info("Total number of documents in RM spell index: {} for language: {}", spellIndexReader.numDocs(), luceneConfiguration.getLanguage());
        }
        try (IndexReader spellIndexReader = DirectoryReader.open(spellIndexDirectoryDe)) {
            logger.info("Total number of documents in DE spell index: {} for language: {}", spellIndexReader.numDocs(), luceneConfiguration.getLanguage());
        }
    }

    private static void deleteAllFromDirectory(Directory dir) throws IOException {
        // Use a writer over the same directory and delete all docs
        // Note: We intentionally avoid keeping a long-lived writer; this is only used during rebuild under the write lock.
        try (var writer = new org.apache.lucene.index.IndexWriter(dir, new IndexWriterConfig())) {
            writer.deleteDocuments(new MatchAllDocsQuery());
            writer.commit();
        }
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
}
