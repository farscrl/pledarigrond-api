package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.config.LuceneConfiguration;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.common.SearchMethod;
import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class TestQueryBuildersDefaultSubsemanticsWeighted extends TestQueryBuildersBase {

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
		List<EntryDto> entries = new ArrayList<>();
		int counter = 0;

		addEntryVersionDto(entries, counter++, "Dummkopf", "pistola", null, "vulg.");
		addEntryVersionDto(entries, counter++, "Penis", "pistola", null, "vulg.");
		addEntryVersionDto(entries, counter++, "Pistole", "pistola", null, null);
		addEntryVersionDto(entries, counter++, "Pimmel", "pistola", "fam. für Penis", "vulg.");
		addEntryVersionDto(entries, counter++, "Schwanz", "pistola", "vulg. für Penis", "vulg.");
		addEntryVersionDto(entries, counter++, "Zipfel", "pistola", "fam. für Penis", "vulg.");

		luceneIndexManager.addToIndex(entries.stream());
	}

	
	@Test
	public void testDefaultQueryWithSubsemantics() throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException {
		Page<EntryVersionDto> result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.BOTH, SearchMethod.NORMAL, "pistola"), IndexTestHelpers.getPagination());
		String[] found = getStrings(FieldName.DESTICHWORT, result);
		String[] expected = new String[] { "Pistole", "Dummkopf", "Penis", "Pimmel", "Schwanz", "Zipfel" };
		printArray(found);
		assertArrayEquals(expected, found);
	}

	private void addEntryVersionDto(List<EntryDto> entries, int counter, String DStichwort, String RStichwort, String DSubsemantik, String RSubsemantik) {
		EntryVersionDto ev = new EntryVersionDto();
		ev.setDeStichwort(DStichwort);
		ev.setRmStichwort(RStichwort);
		ev.setDeSubsemantik(DSubsemantik);
		ev.setRmSubsemantik(RSubsemantik);
		ev.setRmStichwortSort("1");
		ev.setDeStichwortSort("1");

		EntryDto entry = new EntryDto();
		entry.setEntryId(counter+"");
		entry.setCurrent(ev);
		entries.add(entry);
	}
}
