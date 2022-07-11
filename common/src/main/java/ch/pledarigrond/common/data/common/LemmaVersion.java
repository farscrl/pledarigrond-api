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

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class LemmaVersion implements Serializable {

	private static final long serialVersionUID = -3937300849636141916L;

	public enum Status {
		DELETED("Deleted"), NEW_ENTRY("New"), NEW_MODIFICATION("Modified"), UNDEFINED("Undefined");

		private String value;

		private Status(String value) {
			this.value = value;
		}

		public String getStateName() {
			return value;
		}
	}

	public enum Verification {
		REJECTED ("Rejected"), ACCEPTED("Accepted"), OUTDATED ("Outdated"), UNVERIFIED ("Unverified");

		private String value;

		private Verification(String value) {
			this.value = value;
		}

		public String getVerificationName() {
			return value;
		}
	}

	public static final String TIMESTAMP = "timestamp";

	// this is the e-mail address of the creator of a lemma. If there is no user logged in, this value is PgUserInfo.DEFAULT_USER_NAME
	public static final String CREATOR = "creator";

	public static final String CREATOR_ROLE = "creator_role";

	public static final String ID = "internal_id";

	public static final String LEXENTRY_ID = "entry_id";

	public static final String STATUS = "status";

	public static final String VERIFICATION = "verification";

	public static final String VERIFIER = "verifier";

	public static final Set<String> PG_KEYS;

	public static final String COMMENT = "contact_comment";
	public static final String COMMENT__DEPRECATED = "maalr_comment";

	// this is the e-mail address indicated as contact when making a suggestion. As this is a freetext field, it has no connection to the user.
	public static final String EMAIL = "contact_email";
	public static final String EMAIL__DEPRECATED = "maalr_email";

	public static final String IP_ADDRESS = "user_ip";
	public static final String IP_ADDRESS__DEPRECATED = "maalr_ip";

	public static final HashSet<String> PUBLIC_PG_KEYS;

	public static final String OVERLAY_LANG_RM__DEPRECATED = "maalr_overlay_lang2";
	public static final String OVERLAY_LANG_DE__DEPRECATED = "maalr_overlay_lang1";
	public static final String RM_INFLECTION_TYPE = "RInflectionType";
	public static final String RM_INFLECTION_SUBTYPE = "RInflectionSubtype";

	public static final String CATEGORIES = "categories";
	public static final String CATEGORIES__DEPRECATED = "Bearbeitungshinweis";

	public static final String RM_REDIRECT = "RRedirect";
	public static final String RM_REDIRECT__DEPRECATED = "redirect_b";
	public static final String DE_REDIRECT = "DRedirect";
	public static final String DE_REDIRECT__DEPRECATED = "redirect_a";

	public static final String AUTOMATIC_CHANGE = "automatic_change";
	public static final String REVIEW_LATER = "review_later";

	public static final String FIELD_NAMES = "field_names";

	static {
		PG_KEYS = new HashSet<>();
		PG_KEYS.add(TIMESTAMP);
		PG_KEYS.add(CREATOR);
		PG_KEYS.add(ID);
		PG_KEYS.add(STATUS);
		PG_KEYS.add(IP_ADDRESS);
		PG_KEYS.add(CREATOR_ROLE);
		PG_KEYS.add(VERIFICATION);
		PG_KEYS.add(VERIFIER);
		PG_KEYS.add(LEXENTRY_ID);
		PG_KEYS.add(AUTOMATIC_CHANGE);
		PG_KEYS.add(REVIEW_LATER);
		PUBLIC_PG_KEYS = new HashSet<>();
		PUBLIC_PG_KEYS.add(ID);
		PUBLIC_PG_KEYS.add(STATUS);
		PUBLIC_PG_KEYS.add(VERIFICATION);
		PUBLIC_PG_KEYS.add(LEXENTRY_ID);
	}

	private LightUserInfo userInfo;

	private HashMap<String, String> lemmaValues = new HashMap<>();
	private HashMap<String, String> pgValues = new HashMap<>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((lemmaValues == null) ? 0 : lemmaValues.hashCode());
		result = prime * result
				+ ((pgValues == null) ? 0 : pgValues.hashCode());
		result = prime * result
				+ ((userInfo == null) ? 0 : userInfo.hashCode());
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
		LemmaVersion other = (LemmaVersion) obj;
		if (lemmaValues == null) {
			if (other.lemmaValues != null)
				return false;
		} else if (!lemmaValues.equals(other.lemmaValues))
			return false;
		if (pgValues == null) {
			if (other.pgValues != null)
				return false;
		} else if (!pgValues.equals(other.pgValues))
			return false;
		if (userInfo == null) {
			if (other.userInfo != null)
				return false;
		} else if (!userInfo.equals(other.userInfo))
			return false;
		return true;
	}

	public LemmaVersion() {
		setStatus(Status.UNDEFINED);
		setVerification(Verification.UNVERIFIED);
		setTimestamp(0L);
	}

	@XmlAttribute
	public Status getStatus() {
		if(pgValues.get(STATUS) == null) return null;
		return Status.valueOf(pgValues.get(STATUS));
	}

	public void setStatus(Status status) {
		pgValues.put(STATUS, status.name());
	}

	@XmlAttribute
	public Verification getVerification() {
		if(pgValues.get(VERIFICATION) == null) return null;
		return Verification.valueOf(pgValues.get(VERIFICATION));
	}

	public void setVerification(Verification status) {
		pgValues.put(VERIFICATION, status.name());
	}

	@XmlElement(name="creator")
	public LightUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(LightUserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@XmlElementWrapper(name="fields")
	public HashMap<String, String> getLemmaValues() {
		return lemmaValues;
	}

	@XmlTransient
	public HashMap<String, String> getPgValues() {
		return pgValues;
	}

	public void setLemmaValues(HashMap<String, String> lemmaValues) {
		this.lemmaValues = lemmaValues;
	}

	@XmlAttribute(name="timeStamp")
	public Long getTimestamp() {
		if(pgValues.get(TIMESTAMP) == null) return 0L;
		return Long.parseLong(pgValues.get(TIMESTAMP));
	}

	public void setTimestamp(Long timestamp) {
		pgValues.put(TIMESTAMP, timestamp+"");
	}

	@XmlElement(name="userId")
	public String getUserId() {
		return (String) pgValues.get(CREATOR);
	}

	public void setUserId(String userId) {
		pgValues.put(CREATOR, userId);
	}

	public void setVerifierId(String verifierId) {
		pgValues.put(VERIFIER, verifierId);
	}

	@XmlElement(name="verifierId")
	public String getVerifierId() {
		return pgValues.get(VERIFIER);
	}

	@XmlAttribute(name="id")
	public Integer getInternalId() {
		String idString = pgValues.get(ID);
		if(idString == null) return null;
		Integer id = Integer.parseInt(idString);
		return id;
	}

	public void setInternalId(Integer id) {
		pgValues.put(ID, id+"");
	}

	public boolean isApproved() {
		String verification = pgValues.get(VERIFICATION);
		if(verification == null) return false;
		return Verification.ACCEPTED == Verification.valueOf(verification);
	}

	@XmlAttribute
	public String getLexEntryId() {
		return pgValues.get(LEXENTRY_ID);
	}

	public void setLexEntryId(String id) {
		pgValues.put(LEXENTRY_ID, id);
	}

	public void setIP(String ip) {
		pgValues.put(IP_ADDRESS, ip);
	}

	public void setCreatorRole(EditorRole role) {
		if(role == null) {
			pgValues.put(CREATOR_ROLE, null);
		} else {
			pgValues.put(CREATOR_ROLE, role.toString());
		}
	}

	@XmlAttribute(name="creatorRole")
	public EditorRole getCreatorRole() {
		if(pgValues.get(CREATOR_ROLE) == null) return EditorRole.GUEST;
		try {
			return EditorRole.valueOf(pgValues.get(CREATOR_ROLE));
		} catch (Exception e) {
			String role = pgValues.get(CREATOR_ROLE);
			if (role.equals("ADMIN_5")) { return EditorRole.ADMIN; }
			if (role.equals("TRUSTED_IN_4")) { return EditorRole.EDITOR; }
			if (role.equals("TRUSTED_EX_3")) { return EditorRole.GUEST; }
			if (role.equals("GUEST_1")) { return EditorRole.GUEST; }
			return EditorRole.NONE;
		}
	}

	@XmlAttribute(name="ip")
	public String getIP() {
		return pgValues.get(IP_ADDRESS);
	}

	public boolean equalData(LemmaVersion other) {
		Map<String, String> myValues = getLemmaValues();
		Map<String, String> otherValues = other.getLemmaValues();
		Set<String> all = new HashSet<String>();
		all.addAll(myValues.keySet());
		all.addAll(otherValues.keySet());
		for (String key : all) {
			if(myValues.get(key) == null && otherValues.get(key) != null) {
				return false;
			}
			if(myValues.get(key) != null && otherValues.get(key) == null) {
				return false;
			}
			if(myValues.get(key) == null) continue;
			if(!myValues.get(key).equals(otherValues.get(key))) {
				return false;
			}
		}
		return true;
	}

	public String getEntryValue(String key) {
		return lemmaValues.get(key);
	}

	public void putEntryValue(String key, String value) {
		lemmaValues.put(key, value);
	}

	public void removeEntryValue(String key) {
		lemmaValues.remove(key);
	}

	public void putPgValue(String key, String value) {
		pgValues.put(key, value);
	}

	@Override
	public String toString() {
		return "LemmaVersion [entryValues=" + lemmaValues + ", pgValues="
				+ pgValues + "]";
	}

	public void setPgValues(HashMap<String, String> map) {
		this.pgValues = map;
	}

	public void setValue(String key, String value) {
		lemmaValues.put(key, value);
	}

}
