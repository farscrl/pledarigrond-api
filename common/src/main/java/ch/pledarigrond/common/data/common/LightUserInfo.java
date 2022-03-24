package ch.pledarigrond.common.data.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Data
public class LightUserInfo implements Serializable {

	@NotEmpty(message = "email is required!")
	@Email
	private String email;

	@Size(min = 6, max = 12, message = "Size between 6 and 12!")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	private boolean isAdmin;

	private RolesObject roles;
	
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

	@JsonIgnore
	public LightUserInfo getCopy() {
		LightUserInfo copy = new LightUserInfo();
		System.out.println("LightUserInfo getCopy() " + getEmail());
		copy.setEmail(getEmail());

		copy.setAdmin(isAdmin());

		copy.setRoles(getRoles().getCopy());

		copy.setCreationDate(getCreationDate());
		copy.setLastModificationDate(getLastModificationDate());

		return copy;
	}

	@Override
	public String toString() {
		return "LightUserInfo [email=" + email + ", isAdmin=" + isAdmin + "]";
	}

}
