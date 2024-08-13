package ch.pledarigrond.lucene.core;


import ch.pledarigrond.common.config.LuceneConfiguration;
import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TestDefaultQueryBuilder {
	
	private LuceneIndexManager luceneIndexManager;

	private File indexDir;

	@Before
	public void beforeTest() throws Exception {
		Language language = Language.SURMIRAN;
		File file = File.createTempFile("pledarigrond", "test");
		indexDir = new File(file.getParentFile(), "pg_test" + UUID.randomUUID().toString() + "_idx");
		Assert.assertFalse(indexDir.exists());
		indexDir.mkdir();
		file.deleteOnExit();
		LuceneConfiguration luceneConfiguration = new LuceneConfiguration(language, indexDir.getAbsolutePath());
		luceneIndexManager = new LuceneIndexManager(luceneConfiguration);
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("dictionary.tsv");
		BufferedReader br = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
		String line = null;
		List<LexEntry> entries = new ArrayList<LexEntry>();
		int counter = 0;
		while((line = br.readLine()) != null) {
			String[] parts = line.split("\t");
			LemmaVersion lv = new LemmaVersion();
			lv.setValue("DStichwort", parts[0]);
			lv.setValue("RStichwort", parts[1]);
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
	public void afterTest() throws Exception {
		deleteRecursive(indexDir);
	}
	
	@Test
	public void testPrefixQuery() throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, IOException, InvalidTokenOffsetsException {
		Page<LemmaVersion> result = luceneIndexManager.query(getSearchCriteria(SearchDirection.GERMAN, SearchMethod.PREFIX, "haus"), getPagination());
		Set<String> found = getStrings("DStichwort", result);
		String[] expected = new String[] {"hausen", "Haushalt", "Haus"};
		validateResult(found, expected);
	}
	
	@Test
	public void testInfixQuery() throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, IOException, InvalidTokenOffsetsException {
		Page<LemmaVersion> result = luceneIndexManager.query(getSearchCriteria(SearchDirection.GERMAN, SearchMethod.INTERN, "haus"), getPagination());
		Set<String> found = getStrings("DStichwort", result);
		String[] expected = new String[] {"nach Hause", "Das Haus brennt", "Flohausstellung", "hausen", "Haushalt", "zu Hause", "Wohnhaus", "Haus", "Kranken(haus)"};
		validateResult(found, expected);
	}
	
	@Test
	public void testSuffix() throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, IOException, InvalidTokenOffsetsException {
		Page<LemmaVersion> result = luceneIndexManager.query(getSearchCriteria(SearchDirection.GERMAN, SearchMethod.SUFFIX, "haus"), getPagination());
		Set<String> found = getStrings("DStichwort", result);
		String[] expected = new String[] {"Wohnhaus", "Haus", "Kranken(haus)"};
		validateResult(found, expected);
	}
	
	@Test
	public void testExact() throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, IOException, InvalidTokenOffsetsException {
		Page<LemmaVersion> result = luceneIndexManager.query(getSearchCriteria(SearchDirection.GERMAN, SearchMethod.EXACT, "haus"), getPagination());
		Set<String> found = getStrings("DStichwort", result);
		String[] expected = new String[] {"Haus"};
		validateResult(found, expected);
	}
	
	@Test
	public void testDefault() throws InvalidQueryException, NoIndexAvailableException, BrokenIndexException, IOException, InvalidTokenOffsetsException {
		Page<LemmaVersion> result = luceneIndexManager.query(getSearchCriteria(SearchDirection.GERMAN, SearchMethod.NORMAL, "haus"), getPagination());
		Set<String> found = getStrings("DStichwort", result);
		String[] expected = new String[] {"Das Haus brennt", "hausen", "Haushalt", "Haus", "Kranken(haus)"};
		validateResult(found, expected);
	}
	
	@Test
	public void testExactQuery() throws NoIndexAvailableException, BrokenIndexException, InvalidQueryException {
		Page<LemmaVersion> result = luceneIndexManager.queryExact("haus", DictionaryLanguage.GERMAN);
		Set<String> found = getStrings("DStichwort", result);
		String[] expected = new String[] {"Haus"};
		validateResult(found, expected);
	}
	
	
	private Set<String> getStrings(String key, Page<LemmaVersion> result) {
		HashSet<String> toReturn = new HashSet<String>();
		List<LemmaVersion> entries = result.getContent();
		for (LemmaVersion lv : entries) {
			toReturn.add(lv.getEntryValue(key));
		}
		return toReturn;
	}

	private SearchCriteria getSearchCriteria(SearchDirection direction, SearchMethod method, String searchPhrase) {
		SearchCriteria query = new SearchCriteria();
		query.setSearchDirection(direction);
		query.setSearchMethod(method);
		query.setSearchPhrase(searchPhrase);
		return query;
	}

	private Pagination getPagination() {
		return new Pagination();
	}

	private void validateResult(Set<String> found, String[] expected) {
		List<String> expectedList = Arrays.asList(expected);
		Assert.assertTrue("Was expecting to find " + expectedList + " in " + found, found.containsAll(expectedList));
		found.removeAll(expectedList);
		Assert.assertTrue("Found more items than expecting: " + found, found.isEmpty());
	}

	private void deleteRecursive(File fileOrDir) {
		if(fileOrDir.isDirectory()) {
			File[] files = fileOrDir.listFiles();
			for (File file : files) {
				deleteRecursive(file);
			}
		} else {
			fileOrDir.delete();
		}
	}
}
