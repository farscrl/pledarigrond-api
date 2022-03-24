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
package ch.pledarigrond.common.config;

public interface Constants {

	@Deprecated
	interface Roles {
		String ROLE = "Role";
		
		String GUEST_1 = "ROLE_GUEST";
		String TRUSTED_EX_3 = "ROLE_TRUSTED_EX";
		String TRUSTED_IN_4 = "ROLE_TRUSTED_IN";
		String ADMIN_5 = "ROLE_ADMIN";
	}
	
	interface Users {
		String PASSWORD = "password";
		String EMAIL = "email";
		String CREATION_DATE = "creationDate";
		String LAST_MODIFICATION = "lastModDate";

		String IS_ADMIN = "isAdmin";

		String ROLE_PUTER = "rolePuter";
		String ROLE_RUMANTSCHGRISCHUN = "roleRumantschgrischun";
		String ROLE_SURMIRAN = "roleSurmiran";
		String ROLE_SURSILVAN = "roleSursilvan";
		String ROLE_SUTSILVAN = "roleSutsilvan";
		String ROLE_VALLADER = "roleVallader";

		String ROLE_NAMES = "roleNames";
	}
}
