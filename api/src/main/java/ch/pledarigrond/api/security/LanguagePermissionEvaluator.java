package ch.pledarigrond.api.security;

import ch.pledarigrond.common.data.common.Language;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.Serializable;

public class LanguagePermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (targetDomainObject == null) {
            return false;
        }
        if (targetDomainObject.equals("names")) {
            return hasNamesPermission(authentication, targetDomainObject, permission);
        }
        if (targetDomainObject.equals("registrations")) {
            return hasRegistrationsPermission(authentication, targetDomainObject, permission);
        }
        if (targetDomainObject.equals("language")) {
            HttpServletRequest request = getCurrentHttpRequest();
            if (request == null) {
                return false;
            }
            String language = extractLanguageFromRequest(request);
            if (language == null) {
                return false;
            }
            try {
                Language lang = Language.valueOf(language.toUpperCase());
                return hasLanguagePermission(authentication, lang, permission);
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }

    private boolean hasLanguagePermission(Authentication authentication, Language language, Object permission) {
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

    private HttpServletRequest getCurrentHttpRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return (attrs != null) ? attrs.getRequest() : null;
    }

    private String extractLanguageFromRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String[] parts = uri.split("/");
        if (parts.length >= 2) {
            return parts[1];
        }

        return null;
    }
}
