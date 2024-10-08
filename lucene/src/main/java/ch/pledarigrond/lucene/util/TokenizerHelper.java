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

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;


/**
 * Helper class to tokenize strings using a lucene analyzer.
 */
public class TokenizerHelper {
	
	public static String tokenizeString(Analyzer analyzer, String string) {
		// Inspired by stackoverflow:
		// http://stackoverflow.com/questions/6334692/how-to-use-a-lucene-analyzer-to-tokenize-a-string
	    StringBuilder builder = new StringBuilder();
	    try {
	      TokenStream stream  = analyzer.tokenStream(null, new StringReader(string));
	      stream.reset();
	      while (stream.incrementToken()) {
	        builder.append(stream.getAttribute(CharTermAttribute.class).toString());
	        builder.append(" ");
	      }
	      stream.close();
	    } catch (IOException e) {
	      // not thrown b/c we're using a string reader...
	      throw new RuntimeException(e);
	    }
	    return builder.toString().trim();
	  }
}
