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
import java.util.List;

public class SuggestionsIndex {

    protected static final Logger logger = LoggerFactory.getLogger(SuggestionsIndex.class);

    private final LuceneConfiguration luceneConfiguration;
    private final Directory spellIndexDirectory;
    private IndexReader reader;
    private SpellChecker spellChecker;

    public SuggestionsIndex(LuceneConfiguration luceneConfiguration) throws IOException {
        this.luceneConfiguration = luceneConfiguration;
        spellIndexDirectory = FSDirectory.open(luceneConfiguration.getLuceneSuggestionIndexDir().toPath());
        spellChecker = new SpellChecker(spellIndexDirectory);
        reader = DirectoryReader.open(spellIndexDirectory);
        logger.info("Initialized suggestions index.");
    }

    public void reIndex() throws IOException {

        // delete current content
        Directory suggestionsDirectory = FSDirectory.open(luceneConfiguration.getLuceneSuggestionIndexDir().toPath());
        IndexWriterConfig config = new IndexWriterConfig();
        IndexWriter writer = new IndexWriter(suggestionsDirectory, config);
        writer.deleteDocuments(new MatchAllDocsQuery());
        writer.commit();
        writer.close();
        logger.info("Deleted current content of index.");

        // Open the main index reader
        Directory indexDirectory = FSDirectory.open(luceneConfiguration.getLuceneIndexDir().toPath());
        IndexReader reader = DirectoryReader.open(indexDirectory);

        spellChecker.indexDictionary(new LuceneDictionary(reader, "DStichwort_dict"), new IndexWriterConfig(new CaseInsensitiveStandardTokenizer()), true);
        spellChecker.indexDictionary(new LuceneDictionary(reader, "RStichwort_dict"), new IndexWriterConfig(new CaseInsensitiveStandardTokenizer()), true);

        logIndexedWords(false);

        // Close resources
        spellChecker.close();
        reader.close();
        this.reader.close();

        spellChecker = new SpellChecker(spellIndexDirectory);
        this.reader = DirectoryReader.open(spellIndexDirectory);
    }

    public String[] suggestSimilar(String word, int numSuggestions, SearchDirection direction) throws IOException {
        return prioritizeSuggestions(word, spellChecker.suggestSimilar(word, numSuggestions, 0.5F)).toArray(new String[0]);
    }

    private void logIndexedWords(boolean includeDetails) throws IOException {
        try (IndexReader spellIndexReader = DirectoryReader.open(spellIndexDirectory)) {
            logger.info("Total number of documents in spell index: " + spellIndexReader.numDocs());
            if (includeDetails) {
                for (int i = 0; i < spellIndexReader.maxDoc(); i++) {
                    Document doc = spellIndexReader.document(i);
                    logger.info("  Indexed word: " + doc.get("word"));
                }
            }
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
