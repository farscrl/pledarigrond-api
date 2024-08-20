package ch.pledarigrond.lucene.core;


import ch.pledarigrond.common.config.LuceneConfiguration;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.springframework.data.domain.Page;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class TestQueryBuildersBase {

	protected LuceneIndexManager luceneIndexManager;

	protected File indexDir;

	@Before
	public void beforeTest() throws Exception {
		Language language = Language.SURMIRAN;
		File file = File.createTempFile("pledarigrond", "test");
		indexDir = new File(file.getParentFile(), "pg_test" + UUID.randomUUID() + "_idx");
		Assert.assertFalse(indexDir.exists());
		boolean success = indexDir.mkdir();
		if (!success) {
			throw new Exception("Could not create index directory");
		}

		// file.deleteOnExit();
		LuceneConfiguration luceneConfiguration = new LuceneConfiguration(language, indexDir.getAbsolutePath());
		luceneIndexManager = new LuceneIndexManager(luceneConfiguration);
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("dictionary.tsv");
        assert input != null;
        BufferedReader br = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
		String line;
		List<LexEntry> entries = new ArrayList<>();
		int counter = 0;
		while((line = br.readLine()) != null) {
			String[] parts = line.split("\t");
			LemmaVersion lv = new LemmaVersion();
			lv.setValue("DStichwort", parts[0]);
			lv.setValue("RStichwort", parts[1]);
			lv.setValue("RStichwort_sort", "1");
			lv.setValue("DStichwort_sort", "1");
			lv.setVerification(LemmaVersion.Verification.ACCEPTED);
			lv.setStatus(LemmaVersion.Status.NEW_ENTRY);
			LexEntry entry = new LexEntry(lv);
			entry.setId(counter+"");
			counter++;
			entries.add(entry);
		}
		luceneIndexManager.addToIndex(entries.iterator());
	}
	
	@After
	public void afterTest() {
		// deleteRecursive(indexDir);
	}

	protected String[] getStrings(String key, Page<LemmaVersion> result) {
		List<String> toReturn = new ArrayList<>();
		List<LemmaVersion> entries = result.getContent();
		for (LemmaVersion lv : entries) {
			toReturn.add(lv.getEntryValue(key));
		}
		return toReturn.toArray(new String[0]);
	}

	private void deleteRecursive(File fileOrDir) {
		if(fileOrDir.isDirectory()) {
			File[] files = fileOrDir.listFiles();
            assert files != null;
            for (File file : files) {
				deleteRecursive(file);
			}
		} else {
			boolean success = fileOrDir.delete();
			if(!success) {
				throw new RuntimeException("Could not delete file " + fileOrDir);
			}
		}
	}

	protected void printArray(String[] strings) {
		System.out.print("{ ");
		for (int i = 0; i < strings.length; i++) {
			System.out.print("\"" + strings[i] + "\"");
			if (i < strings.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println(" }");
	}
}
