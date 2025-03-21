package ch.pledarigrond.lucene.core;


import ch.pledarigrond.common.config.LuceneConfiguration;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.data.domain.Page;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;

public abstract class TestQueryBuildersBase {

	protected LuceneIndexManager luceneIndexManager;

	protected File indexDir;

	@BeforeEach
	public void beforeTest() throws Exception {
		Language language = Language.SURMIRAN;
		File file = File.createTempFile("pledarigrond", "test");
		indexDir = new File(file.getParentFile(), "pg_test" + UUID.randomUUID() + "_idx");
		assertFalse(indexDir.exists());
		boolean success = indexDir.mkdir();
		if (!success) {
			throw new Exception("Could not create index directory");
		}

		file.deleteOnExit();
		LuceneConfiguration luceneConfiguration = new LuceneConfiguration(language, indexDir.getAbsolutePath());
		luceneIndexManager = new LuceneIndexManager(luceneConfiguration);
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("dictionary.tsv");
        assert input != null;
        BufferedReader br = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
		String line;
		List<EntryDto> entries = new ArrayList<>();
		int counter = 0;
		while((line = br.readLine()) != null) {
			String[] parts = line.split("\t");
			EntryDto entry = new EntryDto();
			EntryVersionDto ev = new EntryVersionDto();
			ev.setDeStichwort(parts[0]);
			ev.setRmStichwort(parts[1]);
			ev.setDeStichwortSort("1");
			ev.setRmStichwortSort("1");
			entry.setCurrent(ev);
			entry.setEntryId(counter+"");
			counter++;
			entries.add(entry);
		}
		luceneIndexManager.addToIndex(entries.stream());
	}
	
	@AfterEach
	public void afterTest() {
		deleteRecursive(indexDir);
	}

	protected String[] getStrings(FieldName field, Page<EntryVersionDto> dto) {
		List<String> toReturn = new ArrayList<>();
		List<EntryVersionDto> entries = dto.getContent();
		for (EntryVersionDto ev : entries) {
			if (field == FieldName.RMSTICHWORT) {
				toReturn.add(ev.getRmStichwort());
			} else if (field == FieldName.DESTICHWORT) {
				toReturn.add(ev.getDeStichwort());
			}
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

	protected enum FieldName {
		DESTICHWORT, RMSTICHWORT
	}
}
