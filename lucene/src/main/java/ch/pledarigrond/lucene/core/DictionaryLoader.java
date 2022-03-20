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

import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.lucene.config.IndexManagerSurmiran;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import ch.pledarigrond.common.config.LuceneConfiguration;
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
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Helper class used by {@link Dictionary}. It manages the lucene index
 * kept in a {@link RAMDirectory}.
 *
 */
 class DictionaryLoader {

	private IndexSearcher searcher;
	private static final Logger logger = LoggerFactory.getLogger(DictionaryLoader.class);

	private LuceneConfiguration luceneConfiguration;
	private IndexManagerSurmiran indexManager;

	private RAMDirectory ram;
	private DirectoryReader reader;
	
	LuceneConfiguration getLuceneConfiguration() {
		return luceneConfiguration;
	}

	void setLuceneConfiguration(LuceneConfiguration luceneConfiguration) {
		this.luceneConfiguration = luceneConfiguration;
		indexManager = IndexManagerSurmiran.getInstance();
	}

	IndexSearcher getSearcher() throws NoIndexAvailableException {
		if (searcher == null) {
			loadIndex();
		}
		return searcher;
	}

	synchronized void reloadIndex() throws NoIndexAvailableException {
		searcher = null;
		if(ram != null) {
			ram.close();
		}
		loadIndex();
	}
	
	private synchronized void loadIndex() throws NoIndexAvailableException {
		if (searcher == null) {
			try {
				logger.info("Loading index from directory " + luceneConfiguration.getLuceneIndexDir().getAbsolutePath());
				NIOFSDirectory directory = new NIOFSDirectory(luceneConfiguration.getLuceneIndexDir());
				ram = new RAMDirectory(directory, new IOContext());
				reader = DirectoryReader.open(ram);
				searcher = new IndexSearcher(reader);
				searcher.setSimilarity(new SimilarityBase() {
					
					@Override
					public String toString() {
						return "Constant Similarity";
					}
					
					@Override
					protected float score(BasicStats stats, float freq, float docLen) {
						return stats.getTotalBoost();
					}
					
					
				});
				directory.close();
				logger.info("Index loaded.");
			} catch (IOException e) {
				throw new NoIndexAvailableException("Failed to load index", e);
			}
		}
	}
	
	private IndexWriter initIndexWriter() throws IOException {
		IndexWriterConfig writerConfig = new IndexWriterConfig(LuceneHelper.CURRENT, LuceneHelper.newAnalyzer());
		writerConfig.setOpenMode(OpenMode.APPEND);
		writerConfig.setRAMBufferSizeMB(512.0);
		IndexWriter writer = new IndexWriter(ram, writerConfig);
		return writer;
	}

	void update(LexEntry entry) throws IOException {
		IndexWriter writer = initIndexWriter();
		Term queryTerm = new Term(LexEntry.ID, entry.getId());
		writer.deleteDocuments(queryTerm);
		if(entry.getCurrent() != null) {
			List<Document> docs = createDocument(new HashSet<String>(), entry);
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
	
	private List<Document> createDocument(Set<String> keys, LexEntry lexEntry) {
		List<Document> docs = new ArrayList<Document>();
		Set<LemmaVersion> versions = new HashSet<LemmaVersion>();
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

	void delete(LexEntry entry) throws IOException {
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
