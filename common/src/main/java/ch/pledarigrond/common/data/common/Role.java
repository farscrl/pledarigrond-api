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
package ch.pledarigrond.common.data.common;

import ch.pledarigrond.common.config.Constants;

import java.io.Serializable;

public enum Role implements Serializable {
	
	ADMIN_5(Constants.Roles.ADMIN_5, "Admin"),
	TRUSTED_IN_4(Constants.Roles.TRUSTED_IN_4, "Internal"),
	TRUSTED_EX_3(Constants.Roles.TRUSTED_EX_3, "External"),
	GUEST_1(Constants.Roles.GUEST_1, "Guest");
	
	private final String role;
	private final String roleName;

	public String getRoleName() {
		return roleName;
	}

	public static final String[] ROLE_DISPLAY_NAMES = new String[] {GUEST_1.roleName, TRUSTED_EX_3.roleName, TRUSTED_IN_4.roleName, ADMIN_5.roleName};

	private Role(String roleId, String roleName) {
		this.role = roleId;
		this.roleName = roleName;
	}

	public String getRoleId() {
		return role;
	}
	
}
