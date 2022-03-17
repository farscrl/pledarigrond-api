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


public class LightUserInfo implements Serializable {
	
	private static final long serialVersionUID = -8175500012750444290L;
	
	public static final String SORT_MODIFIED = Constants.Users.LAST_MODIFICATION,
			SORT_CREATED = Constants.Users.CREATION_DATE,
			SORT_LOGIN = Constants.Users.LOGIN, 
			SORT_ROLE = Constants.Users.ROLE;

	// @NotEmpty(message = "Login name is required!") // TODO: uncomment me
	private String login;

	private Role role;

	// @NotNull // TODO: uncomment me
	// @Size(min = 6, max = 12, message = "Size between 6 and 12!")	// TODO: uncomment me
	private String password;
	
	private long creationDate, lastModificationDate;
	
	public long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(long creationTime) {
		this.creationDate = creationTime;
	}

	public long getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(long lastChangeTime) {
		this.lastModificationDate = lastChangeTime;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public void setRole(String role) {
		Role[] values = Role.values();
		for (Role r : values) {
			if(r.getRoleId().equals(role)) {
				this.role = r;
			}
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public LightUserInfo getCopy() {
		LightUserInfo copy = new LightUserInfo();
		System.out.println("LightUserInfo getCopy() " + getLogin());
		copy.setLogin(getLogin());
		copy.setRole(getRole());
		copy.setCreationDate(getCreationDate());
		copy.setLastModificationDate(getLastModificationDate());
		return copy;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pasword) {
		this.password = pasword;
	}
	
	@Override
	public String toString() {
		return "LightUserInfo [login=" + login + ", role=" + role + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LightUserInfo other = (LightUserInfo) obj;

		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (role != other.role)
			return false;
		return true;
	}
}
