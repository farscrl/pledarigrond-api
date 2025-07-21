package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.config.LuceneConfiguration;
import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import ch.pledarigrond.lucene.util.FN;
import ch.pledarigrond.lucene.util.FieldTransformer;
import ch.pledarigrond.lucene.util.LuceneHelper;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.similarities.BasicStats;
import org.apache.lucene.search.similarities.SimilarityBase;
import org.apache.lucene.store.MMapDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Helper class used by {@link LuceneIndexManager}. It manages the lucene index
 * held in a {@link MMapDirectory}.
 */
class LuceneIndexFilesystem {

    private static final Logger logger = LoggerFactory.getLogger(LuceneIndexFilesystem.class);

    private LuceneConfiguration luceneConfiguration;

    private IndexSearcher searcher;

    private MMapDirectory indexDirectory;
    private DirectoryReader reader;
    private Analyzer analyzer;
    private final boolean tracing = logger.isTraceEnabled();

    public LuceneIndexFilesystem(LuceneConfiguration luceneConfiguration) {
        setLuceneConfiguration(luceneConfiguration);
    }

    void setLuceneConfiguration(LuceneConfiguration luceneConfiguration) {
        this.luceneConfiguration = luceneConfiguration;
    }

    void initialize() throws IOException {
        resetIndexDirectory();
//		analyzer = LuceneHelper.newAnalyzer();
        analyzer = LuceneHelper.newWhitespaceAnalyzer();

        try {
            createSearcher();
        } catch (NoIndexAvailableException ex) {
            logger.error("Could not initialize LuceneIndexFilesystem", ex);
        }
    }

    IndexSearcher getSearcher() throws NoIndexAvailableException {
        if (searcher == null) {
            createSearcher();
        }
        return searcher;
    }

    private synchronized void createSearcher() throws NoIndexAvailableException {
        if (searcher == null) {
            try {
                reader = DirectoryReader.open(indexDirectory);
                searcher = new IndexSearcher(reader);
                searcher.setSimilarity(new SimilarityBase() {

                    @Override
                    public String toString() {
                        return "Constant Similarity";
                    }

                    @Override
                    protected double score(BasicStats basicStats, double freq, double docLen) {
                        return basicStats.getBoost();
                    }
                });
                logger.info("Searcher created.");
            } catch (IOException e) {
                throw new NoIndexAvailableException("Failed to load index", e);
            }
        }
    }


    void addToIndex(final Stream<EntryDto> stream) throws IndexException {
        logger.info("Indexing...");
        int counter;
        try {
            Date begin = new Date();
            IndexWriter writer = initIndexWriter();
            counter = indexDocs(writer, stream);
            writer.close();
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(luceneConfiguration.getLuceneTimestampFile()), StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write("Created on " + new Date());
            bw.close();

            if (this.reader != null) {
                reader.close();
                reader = DirectoryReader.open(indexDirectory);
                searcher = new IndexSearcher(reader);
            }

            logger.info("Indexing prepared. {} items added. {} total milliseconds", counter, new Date().getTime() - begin.getTime());
        } catch (IOException e) {
            throw new IndexException(e);
        }
    }

    void resetIndexDirectory() throws IOException {
        if (indexDirectory != null) {
            indexDirectory.close();
        }
        indexDirectory = new MMapDirectory(luceneConfiguration.getLuceneIndexDir().toPath());
    }

    private IndexWriter initIndexWriter() throws IOException {
        IndexWriterConfig writerConfig = new IndexWriterConfig(analyzer);
        if (!indexAvailable()) {
            writerConfig.setOpenMode(OpenMode.CREATE);
        } else {
            writerConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
        }
        writerConfig.setRAMBufferSizeMB(512.0);
        return new IndexWriter(indexDirectory, writerConfig);
    }

    private boolean indexAvailable() {
        if (!luceneConfiguration.getLuceneTimestampFile().exists()) {
            logger.warn("No timestamp file available, preparing new index...");
            deleteIndexDirectory();
            return false;
        }
        return true;
    }

    private void deleteIndexDirectory() {
        File[] files = luceneConfiguration.getLuceneIndexDir().listFiles();
        if (files != null) {
            logger.warn("Cleanup - Deleting {} possibly broken index files in {}", files.length, luceneConfiguration.getLuceneIndexDir().getAbsolutePath());
            for (File file : files) {
                boolean deleted = file.delete();
                if (!deleted) {
                    logger.warn("Failed to delete during cleanup: {}", file.getAbsolutePath());
                }
            }
        }
    }

    private int indexDocs(final IndexWriter writer, final Stream<EntryDto> stream) throws IOException {
        AtomicInteger counter = new AtomicInteger();
        NumberFormat nf = NumberFormat.getNumberInstance();

        try (stream) {
            stream.forEach(entry -> {
                try {
                    List<Document> docs = createDocument(entry);
                    if (tracing) {
                        logger.trace("Indexing Documents: {}", docs);
                    }
                    for (Document doc : docs) {
                        writer.addDocument(doc);
                    }
                    int count = counter.incrementAndGet();
                    if (count % 10000 == 0) {
                        logger.debug("Indexed {} documents.", nf.format(count));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        logger.info("###########################################");
        logger.info("Indexing completed - {} entries have been indexed.", nf.format(counter));
        logger.info("###########################################");
        return counter.get();
    }

    private List<Document> createDocument(EntryDto entry) {
        List<Document> docs = new ArrayList<>();
        EntryVersionDto currentVersion = entry.getCurrent();
        if (currentVersion != null) {
            Document doc = FieldTransformer.getDocument(entry, currentVersion);
            docs.add(doc);
        }
        return docs;
    }

    void dropIndex() throws IndexException {
        try (IndexWriter writer = initIndexWriter()) {
            writer.deleteAll();
            writer.commit();
            writer.close();

            if (reader != null) {
                reader.close();
                reader = DirectoryReader.open(indexDirectory);
                searcher = new IndexSearcher(reader);
            }
        } catch (IOException e) {
            throw new IndexException(e);
        }
    }

    void update(EntryDto entry) throws IOException {
        IndexWriter writer = initIndexWriter();
        Term queryTerm = new Term(FN.entryId, entry.getEntryId());
        writer.deleteDocuments(queryTerm);
        if (entry.getCurrent() != null) {
            List<Document> docs = createDocument(entry);
            for (Document document : docs) {
                writer.addDocument(document);
            }
        }
        writer.commit();
        writer.close();

        reader.close();
        reader = DirectoryReader.open(indexDirectory);
        searcher = new IndexSearcher(reader);
    }

    void delete(EntryDto entry) throws IOException {
        IndexWriter writer = initIndexWriter();
        Term queryTerm = new Term(FN.entryId, entry.getEntryId());
        writer.deleteDocuments(queryTerm);
        writer.commit();
        writer.close();

        reader.close();
        reader = DirectoryReader.open(indexDirectory);
        searcher = new IndexSearcher(reader);
    }

    public long getLastUpdated() {
        File dir = indexDirectory.getDirectory().toFile();
        long lastModified = 0;
        File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.lastModified() > lastModified) {
                lastModified = file.lastModified();
            }
        }
        return lastModified;
    }
}
