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
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LightUserInfo;
import ch.pledarigrond.common.data.common.Role;
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

	public PgUserInfo(String login, Role role) {
		setLogin(login);
		setRole(role);
	}

	public PgUserInfo(final String login, final String password, Role role) {
		setLogin(login);
		setPassword(password);
		setRole(role);
	}
	
	public void setLogin(final String login) {
		super.put(Constants.Users.LOGIN, login);
	}
	
	public String getLogin() {
		return super.getString(Constants.Users.LOGIN);
	}
	
	public void setPassword(final String password) {
		super.put(Constants.Users.PASSWORD, BCrypt.hashpw(password, BCrypt.gensalt()));
	}
	
	public String getPassword() {
		return super.getString(Constants.Users.PASSWORD);
	}
	
	public void setRole(Role role) {
		super.put(Constants.Users.ROLE, role.toString());
	}
	
	public Role getRole() {
		String role = super.getString(Constants.Users.ROLE);
		if(role == null)
			return null;
		return Role.valueOf(role);
	}
	
	public LightUserInfo toLightUser() {
		LightUserInfo userInfo = new LightUserInfo();
		userInfo.setLogin(getLogin());
		userInfo.setRole(getRole());
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

	public void setLanguages(List<Language> languages) {
		super.put(Constants.Users.LANGUAGES, languages.toString());
	}

	public List<Language> getLanguages() {
		ArrayList<Language> list = new ArrayList<>();
		String languages = super.getString(Constants.Users.LANGUAGES);
		if(languages == null) {
			return list;
		}

		languages = languages.substring(1, languages.length() - 1); // chop off brackets
		for (String token : languages.split(",")) {
			list.add(Language.valueOf(token.trim()));
		}
		return list;
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
