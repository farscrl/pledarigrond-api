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
package ch.pledarigrond.mongodb.tests;

import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestLexEntry {

	@Test
	public void testHistory() {
		LemmaVersion version = new LemmaVersion();
		version.putEntryValue("a", "b");
		LexEntry entry = new LexEntry(version);
		List<LemmaVersion> history = entry.getVersionHistory();
		assertEquals(1, history.size());
		assertTrue(history.contains(version));
		assertTrue(entry.hasUnapprovedVersions());
		assertEquals(1, entry.getUnapprovedVersions().size());
		assertEquals(version, entry.getCurrent());
		LemmaVersion version2 = new LemmaVersion();
		version2.putEntryValue("c", "d");
		assertNotSame(version, version2);
		entry.addLemma(version2);
		history = entry.getVersionHistory();
		assertEquals(2, history.size());
		assertTrue(history.contains(version));
		assertTrue(history.contains(version2));
		assertEquals(2, entry.getUnapprovedVersions().size());
		assertEquals(version, entry.getCurrent());
		entry.setCurrent(version2);
		assertEquals(version2, entry.getCurrent());
		
	}
	
}
