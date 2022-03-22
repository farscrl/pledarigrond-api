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
	
	interface Roles {
		String ROLE = "Role";
		
		String GUEST_1 = "ROLE_GUEST";
		String TRUSTED_EX_3 = "ROLE_TRUSTED_EX";
		String TRUSTED_IN_4 = "ROLE_TRUSTED_IN";
		String ADMIN_5 = "ROLE_ADMIN";
	}
	
	interface Users {
		String LOGIN = "login";
		String PASSWORD = "password";
		String EMAIL = "email";
		String ROLE = "role";
		String LANGUAGES = "languages";
		String CREATION_DATE = "creationDate";
		String LAST_MODIFICATION = "lastModDate";
		// SPRING SOCIAL SPECIFIC KEYS
		String PROVIDER_USER_ID = "providerUserId";
		String PROVIDER_ID = "providerId";
	}
}
