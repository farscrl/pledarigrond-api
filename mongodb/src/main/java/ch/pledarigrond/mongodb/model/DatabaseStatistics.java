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
package ch.pledarigrond.mongodb.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class DatabaseStatistics implements Serializable {

	private static final long serialVersionUID = 8517174780704841339L;

	private long numberOfLemmata;

	private long numberOfEntries;

	private long numberOfSuggestions;

	private long numberOfApproved;

	private long numberOfDeleted;

	private long numberOfUndefined;

	private ArrayList<StatEntry> dbStats = new ArrayList<StatEntry>();

	private Integer numberOfOutdated;

	public void addDBProperty(String key, String value) {
		dbStats.add(new StatEntry(key, value, StatEntry.Category.NORMAL));
	}

	public void addDBProperty(String key, String value, StatEntry.Category category) {
		dbStats.add(new StatEntry(key, value, category));
	}

	@Override
	public String toString() {
		return "DatabaseStatistics [numberOfLemmata=" + numberOfLemmata + ", numberOfEntries=" + numberOfEntries
				+ ", numberOfSuggestions=" + numberOfSuggestions + ", numberOfApproved=" + numberOfApproved
				+ ", numberOfDeleted=" + numberOfDeleted + ", numberOfUndefined=" + numberOfUndefined + ", dbStats="
				+ dbStats + ", numberOfOutdated=" + numberOfOutdated + "]";
	}
}
