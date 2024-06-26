/*******************************************************************************
 * Copyright 2013 Sprachliche Informationsverarbeitung, University of Cologne
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.config.LuceneConfiguration;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.lucene.IndexManager;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.lucene.util.LuceneHelper;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.NIOFSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.*;

/**
 * Helper class used by {@link LuceneIndex}. It manages the lucene index
 * held in a {@link NIOFSDirectory}.
 *
 */
class LuceneIndexFilesystem {

	private LuceneConfiguration luceneConfiguration;

	private static final Logger logger = LoggerFactory.getLogger(LuceneIndexFilesystem.class);
	private NIOFSDirectory indexDirectory;
	private Analyzer analyzer;
	private final boolean tracing = logger.isTraceEnabled();
	private IndexManager indexManager;

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
		indexManager = IndexManager.getInstance();
	}
	
	int addToIndex(final Iterator<LexEntry> iterator) throws IndexException {
		logger.info("Indexing...");
		int counter = 0;
		try {
			Date begin = new Date();
			IndexWriter writer = initIndexWriter();
			counter = indexDocs(writer, iterator);
			writer.close();
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(luceneConfiguration.getLuceneTimestampFile()), StandardCharsets.UTF_8);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write("Created on " + new Date());
			bw.close();
            logger.info("Indexing prepared: {} total milliseconds", new Date().getTime() - begin.getTime());
			return counter;
		} catch (IOException e) {
			throw new IndexException(e);
		}
	}
	
	void resetIndexDirectory() throws IOException {
		if(indexDirectory != null) {
			indexDirectory.close();
		}
		indexDirectory = new NIOFSDirectory(luceneConfiguration.getLuceneIndexDir().toPath());
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

	private int indexDocs(final IndexWriter writer, final Iterator<LexEntry> iterator) throws IOException {
		int counter = 0;
		NumberFormat nf = NumberFormat.getNumberInstance();
		while(iterator.hasNext()) {
			LexEntry lexEntry = iterator.next();
			List<Document> docs = createDocument(lexEntry);
			if(tracing) {
                logger.trace("Indexing Documents: {}", docs);
			}
			for (Document doc : docs) {
				writer.addDocument(doc);
			}
			counter++;
			if(counter % 10000 == 0) {
                logger.debug("Indexed {} documents.", nf.format(counter));
			}
		}
		logger.info("###########################################");
        logger.info("Indexing completed - {} entries have been indexed.", nf.format(counter));
		logger.info("###########################################");
		return counter;
	}

	private List<Document> createDocument(LexEntry lexEntry) {
		List<Document> docs = new ArrayList<>();
		Set<LemmaVersion> versions = new HashSet<>();
		if(lexEntry.getCurrent() != null) {
			versions.add(lexEntry.getCurrent());
		}
		versions.addAll(lexEntry.getUnapprovedVersions());
		for (LemmaVersion version : versions) {
			Document doc = indexManager.getDocument(lexEntry, version);
			docs.add(doc);
		}
		return docs;
	}

	void dropIndex() throws IndexException {
        try (IndexWriter writer = initIndexWriter()) {
            writer.deleteAll();
        } catch (IOException e) {
            throw new IndexException(e);
        }
    }

	void update(LexEntry entry) throws IOException {
		IndexWriter writer = initIndexWriter();
		Term queryTerm = new Term(LexEntry.ID, entry.getId());
		writer.deleteDocuments(queryTerm);
		if(entry.getCurrent() != null) {
			List<Document> docs = createDocument(entry);
			for (Document document : docs) {
				writer.addDocument(document);
			}
		}
		writer.close();
	}

	void delete(LexEntry entry) throws IOException {
		IndexWriter writer = initIndexWriter();
		Term queryTerm = new Term(LexEntry.ID, entry.getId());
		writer.deleteDocuments(queryTerm);
		writer.commit();
		writer.close();
	}

	public long getLastUpdated() {
		File dir = indexDirectory.getDirectory().toFile();
		long lastModified = 0;
		File[] files = dir.listFiles();
        assert files != null;
        for (File file : files) {
			if(file.lastModified() > lastModified) {
				lastModified = file.lastModified();
			}
		}
		return lastModified;
	}
}
