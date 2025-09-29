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
import org.apache.lucene.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * Helper class used by {@link LuceneIndexManager}. It manages the lucene index
 * held in a {@link MMapDirectory}.
 */
class LuceneIndexFilesystem {

    private static final Logger logger = LoggerFactory.getLogger(LuceneIndexFilesystem.class);

    private LuceneConfiguration luceneConfiguration;

    private volatile IndexSearcher searcher;

    private MMapDirectory indexDirectory;
    private volatile DirectoryReader reader;
    private Analyzer analyzer;
    private final boolean tracing = logger.isTraceEnabled();

    // single lock object for reader/searcher lifecycle
    private final Object searcherLock = new Object();

    // serialize all IndexWriter lifecycles to avoid concurrent writers
    private final ReentrantLock writerMutex = new ReentrantLock(true); // fair lock

    public LuceneIndexFilesystem(LuceneConfiguration luceneConfiguration) {
        setLuceneConfiguration(luceneConfiguration);
    }

    void setLuceneConfiguration(LuceneConfiguration luceneConfiguration) {
        this.luceneConfiguration = luceneConfiguration;
    }

    void initialize() {
        try {
        resetIndexDirectory();
        } catch (IOException ioe) {
            logger.error("Could not initialize LuceneIndexFilesystem for language {}", luceneConfiguration.getLanguage(), ioe);
            return;
        }

//      analyzer = LuceneHelper.newAnalyzer();
        analyzer = LuceneHelper.newWhitespaceAnalyzer();
        try {
            ensureIndexExists();
        } catch (IOException ioe) {
            logger.error("Could not create initial index for language {}", luceneConfiguration.getLanguage(), ioe);
        }

        try {
            createSearcher();
        } catch (NoIndexAvailableException ex) {
            logger.error("Could not initialize LuceneIndexFilesystem for language {}", luceneConfiguration.getLanguage(), ex);
        }
    }

    IndexSearcher getSearcher() throws NoIndexAvailableException {
        synchronized (searcherLock) {
            if (searcher == null) {
                createSearcher();
            }
            return searcher;
        }
    }

    private void createSearcher() throws NoIndexAvailableException {
        synchronized (searcherLock) {
            if (searcher == null) {
                try {
                    // If index doesn't exist yet, throw a controlled exception
                    if (!DirectoryReader.indexExists(indexDirectory)) {
                        throw new NoIndexAvailableException("Index not available yet for language " + luceneConfiguration.getLanguage());
                    }
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
                    throw new NoIndexAvailableException("Failed to load index for language " + luceneConfiguration.getLanguage(), e);
                }
            }
        }
    }


    void addToIndex(final Stream<EntryDto> stream) throws IndexException {
        writerMutex.lock();
        try {
            logger.info("Indexing...");
            Date begin = new Date();
            IndexWriter writer = null;
            try {
                writer = initIndexWriter();
                int counter = indexDocs(writer, stream);
                writer.commit();
                writeTimestamp();
                refreshSearcher();
                logger.info("Indexing prepared. {} items added. {} total milliseconds", counter, new Date().getTime() - begin.getTime());
            } catch (Exception e) {
                safelyRollback(writer);
                throw new IndexException("Error when adding to language " + luceneConfiguration.getLanguage(), e);
            } finally {
                IOUtils.closeWhileHandlingException(writer);
            }
        } finally {
            writerMutex.unlock();
        }
    }

    void resetIndexDirectory() throws IOException {
        IOUtils.closeWhileHandlingException(indexDirectory);
        indexDirectory = new MMapDirectory(luceneConfiguration.getLuceneIndexDir().toPath());
    }

    private IndexWriter initIndexWriter() throws IOException {
        IndexWriterConfig writerConfig = new IndexWriterConfig(analyzer);
        OpenMode mode = DirectoryReader.indexExists(indexDirectory) ? OpenMode.CREATE_OR_APPEND : OpenMode.CREATE;
        writerConfig.setOpenMode(mode);
        writerConfig.setRAMBufferSizeMB(512.0);
        return new IndexWriter(indexDirectory, writerConfig);
    }

    private void writeTimestamp() throws IOException {
        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(luceneConfiguration.getLuceneTimestampFile()), StandardCharsets.UTF_8);
             BufferedWriter bw = new BufferedWriter(osw)) {
            bw.write("Created on " + new Date());
        }
    }

    private void refreshSearcher() throws IOException {
        synchronized (searcherLock) {
            // Close and reopen reader/searcher atomically
            if (reader != null) {
                DirectoryReader newReader = DirectoryReader.openIfChanged(reader);
                if (newReader != null) {
                    DirectoryReader oldReader = reader;
                    reader = newReader;
                    searcher = new IndexSearcher(reader);
                    IOUtils.closeWhileHandlingException(oldReader);
                } else if (searcher == null) {
                    // If reader exists but searcher doesn't (shouldn't happen), recreate searcher
                    searcher = new IndexSearcher(reader);
                }
            } else if (DirectoryReader.indexExists(indexDirectory)) {
                reader = DirectoryReader.open(indexDirectory);
                searcher = new IndexSearcher(reader);
            }
        }
    }

    private int indexDocs(final IndexWriter writer, final Stream<EntryDto> stream) throws IOException {
        AtomicInteger counter = new AtomicInteger();
        NumberFormat nf = NumberFormat.getNumberInstance();
        List<EntryDto> failures = new ArrayList<>();

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
                } catch (Exception e) {
                    logger.warn("Failed to index entry {}: {}", entry.getEntryId(), e.toString());
                    failures.add(entry);
                }
            });
        }

        logger.info("###########################################");
        logger.info("Indexing completed - {} entries have been indexed.", nf.format(counter));
        if (!failures.isEmpty()) {
            logger.warn("{} entries failed to index.", failures.size());
            throw new IOException("Failed to index " + failures.size() + " entries for language " + luceneConfiguration.getLanguage());
        }
        logger.info("###########################################");
        return counter.get();
    }

    private List<Document> createDocument(EntryDto entry) {
        List<Document> docs = new ArrayList<>();
        EntryVersionDto currentVersion = entry.getCurrent();
        if (currentVersion != null) {
            Document doc = FieldTransformer.getDocument(luceneConfiguration.getLanguage(), entry, currentVersion);
            docs.add(doc);
        }
        return docs;
    }

    void dropIndex() throws IndexException {
        writerMutex.lock();
        try {
            IndexWriter writer = null;
            try {
                writer = initIndexWriter();
                writer.deleteAll();
                writer.commit();
                // After deleting everything, refresh searcher to see empty index (or close it if index is now empty)
                synchronized (searcherLock) {
                    IOUtils.closeWhileHandlingException(reader);
                    reader = DirectoryReader.open(indexDirectory); // empty index still has segments_N
                    searcher = new IndexSearcher(reader);
                }
            } catch (IOException e) {
                safelyRollback(writer);
                throw new IndexException("Error when dropping index for language" + luceneConfiguration.getLanguage(), e);
            } finally {
                IOUtils.closeWhileHandlingException(writer);
            }
        } finally {
            writerMutex.unlock();
        }
    }

    void update(EntryDto entry) throws IOException {
        writerMutex.lock();
        try {
            IndexWriter writer = null;
            try {
                writer = initIndexWriter();
                Term term = new Term(FN.entryId, entry.getEntryId());
                if (entry.getCurrent() != null) {
                    List<Document> docs = createDocument(entry);
                    writer.updateDocuments(term, docs); // works also if docs.isEmpty()
                } else {
                    writer.deleteDocuments(term);
                }
                writer.commit();
                refreshSearcher();
            } catch (Exception e) {
                safelyRollback(writer);
                throw e instanceof IOException ? (IOException) e : new IOException(e);
            } finally {
                IOUtils.closeWhileHandlingException(writer);
            }
        } finally {
            writerMutex.unlock();
        }
    }

    void delete(EntryDto entry) throws IOException {
        writerMutex.lock();
        try {
            IndexWriter writer = null;
            try {
                writer = initIndexWriter();
                Term queryTerm = new Term(FN.entryId, entry.getEntryId());
                writer.deleteDocuments(queryTerm);
                writer.commit();
                refreshSearcher();
            } catch (Exception e) {
                safelyRollback(writer);
                throw e instanceof IOException ? (IOException) e : new IOException(e);
            } finally {
                IOUtils.closeWhileHandlingException(writer);
            }
        } finally {
            writerMutex.unlock();
        }
    }

    public long getLastUpdated() {
        File dir = indexDirectory.getDirectory().toFile();
        long lastModified = 0;
        File[] files = dir.listFiles();
        if (files == null) return 0L;
        for (File file : files) {
            lastModified = Math.max(lastModified, file.lastModified());
        }
        return lastModified;
    }

    private void safelyRollback(IndexWriter writer) {
        if (writer != null) {
            try {
                writer.rollback();
            } catch (IOException ioe) {
                logger.warn("Rollback failed: {}", ioe.toString());
            }
        }
    }

    private void ensureIndexExists() throws IOException {
        if (!DirectoryReader.indexExists(indexDirectory)) {
            writerMutex.lock();
            IndexWriter w = null;
            try {
                IndexWriterConfig cfg = new IndexWriterConfig(analyzer);
                cfg.setOpenMode(OpenMode.CREATE);
                w = new IndexWriter(indexDirectory, cfg);
                w.commit();
                writeTimestamp();
                logger.info("Bootstrapped empty index (no prior commit existed).");
            } finally {
                IOUtils.closeWhileHandlingException(w);
                writerMutex.unlock();
            }
        }
    }
}
