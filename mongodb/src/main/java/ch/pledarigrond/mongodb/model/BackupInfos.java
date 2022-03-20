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
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * Wrapper class for GWT module. Wraps general information about the scheduled
 * back up.
 * 
 * @author Mihail Atanassov (atanassov.mihail@gmail.com)
 * 
 */
@Data
@Accessors(chain = true)
public class BackupInfos implements Serializable {

	@Serial
	private static final long serialVersionUID = 753709468739198232L;

	private List<FileInfo> infos;

	private String backupLocation;

	private String backupNums;

	private String triggerTime;

	public BackupInfos() {
	}

	public BackupInfos(List<FileInfo> infos) {
		this.infos = infos;
	}
}
