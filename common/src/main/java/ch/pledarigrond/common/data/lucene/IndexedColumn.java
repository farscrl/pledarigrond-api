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
 * This class represents an index field.
 */
@Data
public class IndexedColumn {

	private String sourceColumnName;
	private String indexFieldName;
	private boolean stored;
	private boolean analyzed;
	private FieldType type;
	private boolean whitespaceAnalyzer;
	private boolean lowerCase;

	public IndexedColumn(String sourceColumnName) {
		this.sourceColumnName = sourceColumnName;
		this.indexFieldName = sourceColumnName;
		this.stored = true;
		this.analyzed = false;
		this.lowerCase = false;
		this.type = FieldType.TEXT;
	}

	public IndexedColumn(String sourceColumnName, FieldType type) {
		this(sourceColumnName);
		this.type = type;
	}

	@Override
	public String toString() {
		return "IndexField [sourceColumnName=" + sourceColumnName + ", indexFieldName=" + indexFieldName +
				", stored=" + stored + ", analyzed=" + analyzed + ", type=" + type + ", whitespaceAnalyzer="
				+ whitespaceAnalyzer + ", lowerCase=" + lowerCase + "]";
	}
}
