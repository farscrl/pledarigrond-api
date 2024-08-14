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
package ch.pledarigrond.common.data.lucene;

/**
 * Defines the way an {@link IndexedColumn} is mapped onto Lucene fields.
 * TEXT, STRING and INTEGER correspond to TextField, StringField and 
 * IntField, whereas SEMICOLON_SEPERATED is a customized option to generate multiple
 * StringFields.
 * 
 * @author sschwieb
 *
 */
public enum FieldType {
	
	/**
	 * A field that is indexed and tokenized, without term vectors. 
	 */
	TEXT, 
	
	/**
	 * A field that is indexed but not tokenized: the entire
	 * String value is indexed as a single token.
	 */
	STRING,

	/**
	 * A special field type is needed for sorting, as this type adds two fields to the index, one
	 * optimized for sorting and one for retrieving the original data.
	 * This type is used to sort strings.
	 */
	STRING_SORTED,

	/**
	 * A special field type is needed for sorting, as this type adds two fields to the index, one
	 * optimized for sorting and one for retrieving the original data.
	 * This type is used to sort integers.
	 */
	INTEGER_SORTED,
	
	/**
	 * Multiple fields of type STRING, separated by semicolons.
	 */
	SEMICOLON_SEPERATED;
}
