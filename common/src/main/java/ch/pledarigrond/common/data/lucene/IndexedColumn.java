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

import lombok.Data;

/**
 * This class represents a mapping from a database column to a
 * lucene index field configuration. The field prefix will be
 * identical to the database column name, whereas the suffix
 * is a representation of the index field attributes.
 * 
 * @author sschwieb
 *
 */
@Data
public class IndexedColumn {

	private String columnName;
	
	private String indexFieldName;
	
	private boolean stored;
	
	private boolean analyzed;
	
	private FieldType type;

	private boolean whitespaceAnalyzer;

	private boolean lowerCase;

	public IndexedColumn() {
	}

	public IndexedColumn(String columnName) {
		this.columnName = columnName;
	}

	public IndexedColumn(String columnName, FieldType type) {
		this.columnName = columnName;
		this.type = type;
	}

	@Override
	public String toString() {
		return "IndexedItem [source=" + columnName + ", dest=" + indexFieldName + ", stored="
				+ stored + ", analyzed=" + analyzed + ", type=" + type
				+ ", whitespaceAnalyzer=" + whitespaceAnalyzer + ", lowerCase="
				+ lowerCase + "]";
	}
}
