package ch.pledarigrond.api.security;

import ch.pledarigrond.common.data.common.Language;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public class LanguagePermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (targetDomainObject.equals("names")) {
            return hasNamesPermission(authentication, targetDomainObject, permission);
        }
        if (targetDomainObject.equals("registrations")) {
            return hasRegistrationsPermission(authentication, targetDomainObject, permission);
        }
        return hasLanguagePermission(authentication, targetDomainObject, permission);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }

    private boolean hasLanguagePermission(Authentication authentication, Object targetDomainObject, Object permission) {
        Language language = (Language) targetDomainObject;
        for (GrantedAuthority grantedAuth : authentication.getAuthorities()) {
            if (grantedAuth.getAuthority().equals("ROLE_ADMIN")) {
                return true;
            }
            if (grantedAuth.getAuthority().startsWith("ROLE_") && grantedAuth.getAuthority().contains(language.toString())) {
                return true;
            }
        }
        return false;
    }

    private boolean hasNamesPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        for (GrantedAuthority grantedAuth : authentication.getAuthorities()) {
            if (grantedAuth.getAuthority().equals("ROLE_ADMIN")) {
                return true;
            }
            if (grantedAuth.getAuthority().startsWith("ROLE_") && grantedAuth.getAuthority().contains("NAMES")) {
                return true;
            }
        }
        return false;
    }

    private boolean hasRegistrationsPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        for (GrantedAuthority grantedAuth : authentication.getAuthorities()) {
            if (grantedAuth.getAuthority().equals("ROLE_ADMIN")) {
                return true;
            }
            if (grantedAuth.getAuthority().startsWith("ROLE_") && grantedAuth.getAuthority().contains("REGISTRATIONS")) {
                return true;
            }
        }
        return false;
    }
}
