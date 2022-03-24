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

import ch.pledarigrond.common.config.Constants;
import ch.pledarigrond.common.data.common.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PgUserInfo extends BasicDBObject {

	private static final long serialVersionUID = 1188500902567455955L;

	public PgUserInfo() {
	}

	public PgUserInfo(DBObject obj) {
		super.putAll(obj);
	}

	public PgUserInfo(String email) {
		setEmail(email);
	}

	public PgUserInfo(final String email, final String password) {
		setEmail(email);
		setPassword(password);
	}

	public void setEmail(final String email) {
		super.put(Constants.Users.EMAIL, email);
	}

	public String getEmail() {
		return super.getString(Constants.Users.EMAIL);
	}
	
	public void setPassword(final String password) {
		if (password == null) {
			return;
		}
		super.put(Constants.Users.PASSWORD, BCrypt.hashpw(password, BCrypt.gensalt()));
	}

	public boolean getAdmin() {
		return super.getBoolean(Constants.Users.IS_ADMIN);
	}

	public void setAdmin(boolean isAdmin) {
		super.put(Constants.Users.IS_ADMIN, isAdmin);
	}

	public EditorRole getPuterRole() {
		String role = super.getString(Constants.Users.ROLE_PUTER);
		if (role == null) {
			return EditorRole.NONE;
		}
		return EditorRole.valueOf(role);
	}

	public void setPuterRole(EditorRole role) {
		super.put(Constants.Users.ROLE_PUTER, role.toString());
	}

	public EditorRole getRumantschgrischunRole() {
		String role = super.getString(Constants.Users.ROLE_RUMANTSCHGRISCHUN);
		if (role == null) {
			return EditorRole.NONE;
		}
		return EditorRole.valueOf(role);
	}

	public void setRumantschgrischunRole(EditorRole role) {
		super.put(Constants.Users.ROLE_RUMANTSCHGRISCHUN, role.toString());
	}

	public EditorRole getSurmiranRole() {
		String role = super.getString(Constants.Users.ROLE_SURMIRAN);
		if (role == null) {
			return EditorRole.NONE;
		}
		return EditorRole.valueOf(role);
	}

	public void setSurmiranRole(EditorRole role) {
		super.put(Constants.Users.ROLE_SURMIRAN, role.toString());
	}

	public EditorRole getSursilvanRole() {
		String role = super.getString(Constants.Users.ROLE_SURSILVAN);
		if (role == null) {
			return EditorRole.NONE;
		}
		return EditorRole.valueOf(role);
	}

	public void setSursilvanRole(EditorRole role) {
		super.put(Constants.Users.ROLE_SURSILVAN, role.toString());
	}

	public EditorRole getSutsilvanRole() {
		String role = super.getString(Constants.Users.ROLE_SUTSILVAN);
		if (role == null) {
			return EditorRole.NONE;
		}
		return EditorRole.valueOf(role);
	}

	public void setSutsilvanRole(EditorRole role) {
		super.put(Constants.Users.ROLE_SUTSILVAN, role.toString());
	}

	public EditorRole getValladerRole() {
		String role = super.getString(Constants.Users.ROLE_VALLADER);
		if (role == null) {
			return EditorRole.NONE;
		}
		return EditorRole.valueOf(role);
	}

	public void setValladerRole(EditorRole role) {
		super.put(Constants.Users.ROLE_VALLADER, role.toString());
	}

	public EditorRole getNamesRole() {
		String role = super.getString(Constants.Users.ROLE_NAMES);
		if (role == null) {
			return EditorRole.NONE;
		}
		return EditorRole.valueOf(role);
	}

	public void setNamesRole(EditorRole role) {
		super.put(Constants.Users.ROLE_NAMES, role.toString());
	}

	public String getPassword() {
		return super.getString(Constants.Users.PASSWORD);
	}
	
	public LightUserInfo toLightUser() {
		RolesObject roles = new RolesObject();
		roles.setPuterRole(getPuterRole());
		roles.setRumantschgrischunRole(getRumantschgrischunRole());
		roles.setSurmiranRole(getSurmiranRole());
		roles.setSursilvanRole(getSursilvanRole());
		roles.setSutsilvanRole(getSutsilvanRole());
		roles.setValladerRole(getValladerRole());
		roles.setNamesRole(getNamesRole());

		LightUserInfo userInfo = new LightUserInfo();
		userInfo.setEmail(getEmail());
		userInfo.setAdmin(getAdmin());
		userInfo.setRoles(roles);
		userInfo.setLastModificationDate(getLastModificationDate());
		userInfo.setCreationDate(getCreationDate());
		return userInfo;
	}

	public void setCreationDate(long currentTimeMillis) {
		super.put(Constants.Users.CREATION_DATE, currentTimeMillis);
	}
	
	public long getCreationDate() {
		return super.getLong(Constants.Users.CREATION_DATE);
	}
	
	public void setLastModificationDate(long currentTimeMillis) {
		super.put(Constants.Users.LAST_MODIFICATION, currentTimeMillis);
	}
	
	public long getLastModificationDate() {
		return super.getLong(Constants.Users.LAST_MODIFICATION);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}
}
