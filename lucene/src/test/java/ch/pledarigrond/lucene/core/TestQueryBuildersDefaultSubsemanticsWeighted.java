package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.config.LuceneConfiguration;
import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertArrayEquals;

public class TestQueryBuildersDefaultSubsemanticsWeighted extends TestQueryBuildersBase {

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

		file.deleteOnExit();
		LuceneConfiguration luceneConfiguration = new LuceneConfiguration(language, indexDir.getAbsolutePath());
		luceneIndexManager = new LuceneIndexManager(luceneConfiguration);
		List<LexEntry> entries = new ArrayList<>();
		int counter = 0;

		addLemmaVersion(entries, counter++, "Dummkopf", "pistola", null, "vulg.");
		addLemmaVersion(entries, counter++, "Penis", "pistola", null, "vulg.");
		addLemmaVersion(entries, counter++, "Pistole", "pistola", null, null);
		addLemmaVersion(entries, counter++, "Pimmel", "pistola", "fam. für Penis", "vulg.");
		addLemmaVersion(entries, counter++, "Schwanz", "pistola", "vulg. für Penis", "vulg.");
		addLemmaVersion(entries, counter++, "Zipfel", "pistola", "fam. für Penis", "vulg.");

		luceneIndexManager.addToIndex(entries.iterator());
	}

	
	@Test
	public void testDefaultQueryWithSubsemantics() throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException {
		Page<LemmaVersion> result = luceneIndexManager.query(IndexTestHelpers.getSearchCriteria(SearchDirection.BOTH, SearchMethod.NORMAL, "pistola"), IndexTestHelpers.getPagination());
		String[] found = getStrings("DStichwort", result);
		String[] expected = new String[] { "Pistole", "Dummkopf", "Penis", "Pimmel", "Schwanz", "Zipfel" };
		printArray(found);
		assertArrayEquals(expected, found);
	}

	private void addLemmaVersion(List<LexEntry> entries, int counter, String DStichwort, String RStichwort, String DSubsemantik, String RSubsemantik) {
		LemmaVersion lv = new LemmaVersion();
		lv.setValue("DStichwort", DStichwort);
		lv.setValue("RStichwort", RStichwort);
		lv.setValue("DSubsemantik", DSubsemantik);
		lv.setValue("RSubsemantik", RSubsemantik);
		lv.setValue("RStichwort_sort", "1");
		lv.setValue("DStichwort_sort", "1");
		lv.setVerification(LemmaVersion.Verification.ACCEPTED);
		lv.setStatus(LemmaVersion.Status.NEW_ENTRY);

		LexEntry entry = new LexEntry(lv);
		entry.setId(counter+"");
		entries.add(entry);
	}
}
