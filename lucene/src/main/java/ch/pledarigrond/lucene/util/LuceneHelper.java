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
package ch.pledarigrond.lucene.util;

import ch.pledarigrond.lucene.analyzers.ApostropheAndWhitespaceAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import java.io.IOException;
import java.io.StringReader;

/**
 * Helper class to generate the needed Lucene Analyzers.
 */
public class LuceneHelper {

	public static Analyzer newAnalyzer() {
		try {
			return new StandardAnalyzer(new StringReader(""));
		} catch (IOException e) {
			throw new RuntimeException("IO-Exception while reading from in-memory-string...?", e);
		}
	}

	// For initial indexing (preserve special chars) to enable suggestion search
	public static Analyzer newWhitespaceAnalyzer() {
		return new ApostropheAndWhitespaceAnalyzer();
	}
}
