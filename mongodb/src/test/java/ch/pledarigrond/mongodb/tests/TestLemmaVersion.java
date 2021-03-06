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
import org.junit.*;
import java.util.HashMap;

public class TestLemmaVersion {

	@Test
	public void testStatus() {
		LemmaVersion l = new LemmaVersion();
		l.setStatus(LemmaVersion.Status.NEW_ENTRY);
		LemmaVersion.Status s = l.getStatus();
		Assert.assertEquals(LemmaVersion.Status.NEW_ENTRY, s);
		l.setStatus(LemmaVersion.Status.DELETED);
		s = l.getStatus();
		Assert.assertEquals(LemmaVersion.Status.DELETED, s);
	}

	@Test
	public void testUserId() {
		LemmaVersion l = new LemmaVersion();
		l.setUserId("a");
		Assert.assertEquals("a", l.getUserId());
		l.setUserId("b");
		Assert.assertEquals("b", l.getUserId());
	}

	@Test
	public void testTimeStamp() {
		LemmaVersion l = new LemmaVersion();
		l.setTimestamp(10L);
		Assert.assertEquals(10, (long) l.getTimestamp());
		l.setTimestamp(20L);
		Assert.assertEquals(20, (long) l.getTimestamp());
	}

	@Test
	public void testValues() {
		LemmaVersion l = new LemmaVersion();
		Assert.assertNotNull(l.getLemmaValues());
		Assert.assertEquals(0, l.getLemmaValues().size());
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("a", "b");
		map.put("c", "d");
		l.setLemmaValues(map);
		Assert.assertEquals(map, l.getLemmaValues());
	}

}
