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
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import ch.pledarigrond.lucene.util.LuceneHelper;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Helper class used by {@link LuceneIndex}. It manages the lucene index
 * kept in a {@link MMapDirectory}.
 *
 */
 class LuceneIndexRam {

	private IndexSearcher searcher;
	private static final Logger logger = LoggerFactory.getLogger(LuceneIndexRam.class);

	private LuceneConfiguration luceneConfiguration;
	private IndexManager indexManager;

	private MMapDirectory ram;
	private DirectoryReader reader;

    public LuceneIndexRam(LuceneConfiguration luceneConfiguration) {
        setLuceneConfiguration(luceneConfiguration);
	}

	void setLuceneConfiguration(LuceneConfiguration luceneConfiguration) {
		this.luceneConfiguration = luceneConfiguration;
		indexManager = IndexManager.getInstance();
	}

	IndexSearcher getSearcher() throws NoIndexAvailableException {
		if (searcher == null) {
			loadIndex();
		}
		return searcher;
	}

	synchronized void reloadIndex() throws NoIndexAvailableException, IOException {
		searcher = null;
		if(ram != null) {
			ram.close();
		}
		loadIndex();
	}
	
	private synchronized void loadIndex() throws NoIndexAvailableException {
		if (searcher == null) {
			try {
                logger.info("Loading index from directory {}", luceneConfiguration.getLuceneIndexDir().getAbsolutePath());
				ram = new MMapDirectory(luceneConfiguration.getLuceneIndexDir().toPath());
				reader = DirectoryReader.open(ram);
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
				logger.info("Index loaded.");
			} catch (IOException e) {
				throw new NoIndexAvailableException("Failed to load index", e);
			}
		}
	}
	
	private IndexWriter initIndexWriter() throws IOException, NoIndexAvailableException {
		if (ram == null) {
			this.loadIndex();
		}
		IndexWriterConfig writerConfig = new IndexWriterConfig(LuceneHelper.newAnalyzer());
		writerConfig.setOpenMode(OpenMode.APPEND);
		writerConfig.setRAMBufferSizeMB(512.0);
        return new IndexWriter(ram, writerConfig);
	}

	void update(LexEntry entry) throws IOException, NoIndexAvailableException {
		IndexWriter writer = initIndexWriter();
		Term queryTerm = new Term(LexEntry.ID, entry.getId());
		writer.deleteDocuments(queryTerm);
		if(entry.getCurrent() != null) {
			List<Document> docs = createDocument(entry);
			for (Document document : docs) {
				writer.addDocument(document);
			}
		}
		writer.commit();
		writer.close();
		reader.close();
		reader = DirectoryReader.open(ram);
		searcher = new IndexSearcher(reader);
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

	void delete(LexEntry entry) throws IOException, NoIndexAvailableException {
		IndexWriter writer = initIndexWriter();
		Term queryTerm = new Term(LexEntry.ID, entry.getId());
		writer.deleteDocuments(queryTerm);
		writer.commit();
		writer.close();
		reader.close();
		reader = DirectoryReader.open(ram);
		searcher = new IndexSearcher(reader);
	}
}
