package ch.pledarigrond.lucene.suggestions;

import ch.pledarigrond.common.config.LuceneConfiguration;
import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.lucene.analyzers.CaseInsensitiveStandardTokenizer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
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
    private IndexReader readerRm;
    private IndexReader readerDe;

    public SuggestionsIndex(LuceneConfiguration luceneConfiguration) throws IOException {
        this.luceneConfiguration = luceneConfiguration;

        spellIndexDirectoryRm = FSDirectory.open(luceneConfiguration.getLuceneSuggestionIndexDirRm().toPath());
        spellIndexDirectoryDe = FSDirectory.open(luceneConfiguration.getLuceneSuggestionIndexDirDe().toPath());

        spellCheckerRm = new SpellChecker(spellIndexDirectoryRm);
        spellCheckerDe = new SpellChecker(spellIndexDirectoryDe);

        readerRm = DirectoryReader.open(spellIndexDirectoryRm);
        readerDe = DirectoryReader.open(spellIndexDirectoryDe);

        logger.info("Initialized suggestions index.");
    }

    public void reIndex() throws IOException {
        deleteCurrentIndexContents();

        // Open the main index reader
        Directory indexDirectory = FSDirectory.open(luceneConfiguration.getLuceneIndexDir().toPath());
        IndexReader reader = DirectoryReader.open(indexDirectory);

        spellCheckerDe.indexDictionary(new LuceneDictionary(reader, "DStichwort_dict"), new IndexWriterConfig(new CaseInsensitiveStandardTokenizer()), true);
        spellCheckerRm.indexDictionary(new LuceneDictionary(reader, "RStichwort_dict"), new IndexWriterConfig(new CaseInsensitiveStandardTokenizer()), true);

        logIndexedWords(false);

        // Close resources
        spellCheckerRm.close();
        spellCheckerDe.close();
        reader.close();
        this.readerRm.close();
        this.readerDe.close();

        spellCheckerRm = new SpellChecker(spellIndexDirectoryRm);
        spellCheckerDe = new SpellChecker(spellIndexDirectoryDe);
        this.readerRm = DirectoryReader.open(spellIndexDirectoryRm);
        this.readerDe = DirectoryReader.open(spellIndexDirectoryDe);
    }

    public String[] suggestSimilar(String word, int numSuggestions, SearchDirection direction) throws IOException {
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
    }

    private void logIndexedWords(boolean includeDetails) throws IOException {
        try (IndexReader spellIndexReader = DirectoryReader.open(spellIndexDirectoryRm)) {
            logger.info("Total number of documents in RM spell index: " + spellIndexReader.numDocs());
            if (includeDetails) {
                for (int i = 0; i < spellIndexReader.maxDoc(); i++) {
                    Document doc = spellIndexReader.document(i);
                    logger.info("  Indexed word: " + doc.get("word"));
                }
            }
        }

        try (IndexReader spellIndexReader = DirectoryReader.open(spellIndexDirectoryDe)) {
            logger.info("Total number of documents in DE spell index: " + spellIndexReader.numDocs());
            if (includeDetails) {
                for (int i = 0; i < spellIndexReader.maxDoc(); i++) {
                    Document doc = spellIndexReader.document(i);
                    logger.info("  Indexed word: " + doc.get("word"));
                }
            }
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
}
