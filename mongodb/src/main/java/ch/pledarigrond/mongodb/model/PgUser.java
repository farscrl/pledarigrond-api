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
import ch.pledarigrond.common.data.common.EditorRole;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LightUserInfo;
import ch.pledarigrond.common.data.common.RolesObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class PgUser extends BasicDBObject {

	private static final long serialVersionUID = 1188500902567455955L;

	final public static String DEFAULT_USER_NAME = "guest@pledarigrond.ch";

	final String ROLE_PREFIX = "ROLE_";

	public PgUser() {
	}

	public PgUser(DBObject obj) {
		super.putAll(obj);
	}

	public PgUser(String email) {
		setEmail(email);
	}

	public PgUser(final String email, final String password) {
		setEmail(email);
		setPassword(password);
	}

	public void setEmail(final String email) {
		super.put(Constants.Users.EMAIL, email);
	}

	@XmlElement
	public String getEmail() {
		return super.getString(Constants.Users.EMAIL);
	}
	
	public void setPassword(final String password) {
		if (password == null) {
			return;
		}
		super.put(Constants.Users.PASSWORD, BCrypt.hashpw(password, BCrypt.gensalt()));
	}

	@XmlElement
	public String getPassword() {
		return super.getString(Constants.Users.PASSWORD);
	}

	@XmlElement
	public boolean getAdmin() {
		return super.getBoolean(Constants.Users.IS_ADMIN);
	}

	public void setAdmin(boolean isAdmin) {
		super.put(Constants.Users.IS_ADMIN, isAdmin);
	}

	@XmlElement
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

	@XmlElement
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

	@XmlElement
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

	@XmlElement
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

	@XmlElement
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

	@XmlElement
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

	@XmlElement
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

	@XmlElement
	public EditorRole getRegistrationsRole() {
		String role = super.getString(Constants.Users.ROLE_REGISTRATIONS);
		if (role == null) {
			return EditorRole.NONE;
		}
		return EditorRole.valueOf(role);
	}

	public void setRegistrationsRole(EditorRole role) {
		super.put(Constants.Users.ROLE_REGISTRATIONS, role.toString());
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
		roles.setRegistrationsRole(getRegistrationsRole());

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

	@XmlElement
	public long getCreationDate() {
		return super.getLong(Constants.Users.CREATION_DATE);
	}
	
	public void setLastModificationDate(long currentTimeMillis) {
		super.put(Constants.Users.LAST_MODIFICATION, currentTimeMillis);
	}

	@XmlElement
	public long getLastModificationDate() {
		return super.getLong(Constants.Users.LAST_MODIFICATION);
	}

	public ArrayList<GrantedAuthority> getRoles() {
		ArrayList<GrantedAuthority> roles = new ArrayList<>();
		if (getAdmin()) {
			roles.add(new SimpleGrantedAuthority(ROLE_PREFIX + "ADMIN"));
		}

		if (getPuterRole() == EditorRole.EDITOR) {
			roles.add(new SimpleGrantedAuthority(ROLE_PREFIX + "EDITOR_PUTER"));
		}
		if (getRumantschgrischunRole() == EditorRole.EDITOR) {
			roles.add(new SimpleGrantedAuthority(ROLE_PREFIX + "EDITOR_RUMANTSCHGRISCHUN"));
		}
		if (getSurmiranRole() == EditorRole.EDITOR) {
			roles.add(new SimpleGrantedAuthority(ROLE_PREFIX + "EDITOR_SURMIRAN"));
		}
		if (getSursilvanRole() == EditorRole.EDITOR) {
			roles.add(new SimpleGrantedAuthority(ROLE_PREFIX + "EDITOR_SURSILVAN"));
		}
		if (getSutsilvanRole() == EditorRole.EDITOR) {
			roles.add(new SimpleGrantedAuthority(ROLE_PREFIX + "EDITOR_SUTSILVAN"));
		}
		if (getValladerRole() == EditorRole.EDITOR) {
			roles.add(new SimpleGrantedAuthority(ROLE_PREFIX + "EDITOR_VALLADER"));
		}

		if (getNamesRole() == EditorRole.EDITOR) {
			roles.add(new SimpleGrantedAuthority(ROLE_PREFIX + "EDITOR_NAMES"));
		}

		if (getNamesRole() == EditorRole.EDITOR) {
			roles.add(new SimpleGrantedAuthority(ROLE_PREFIX + "EDITOR_REGISTRATIONS"));
		}

		return roles;
	}

	public EditorRole getRoleByLanguage(Language language) {
		if (getAdmin()) {
			return EditorRole.ADMIN;
		}

		return switch (language) {
			case PUTER -> getPuterRole();
			case RUMANTSCHGRISCHUN -> getRumantschgrischunRole();
			case SURMIRAN -> getSurmiranRole();
			case SURSILVAN -> getSursilvanRole();
			case SUTSILVAN -> getSutsilvanRole();
			case VALLADER -> getValladerRole();
		};
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
