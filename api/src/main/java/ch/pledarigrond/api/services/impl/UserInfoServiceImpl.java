package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.UserInfoService;
import ch.pledarigrond.common.data.common.EditorRole;
import ch.pledarigrond.common.data.common.RequestContext;
import ch.pledarigrond.common.data.common.UserInfoDto;
import ch.pledarigrond.common.data.user.UserDto;
import ch.pledarigrond.users.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserService userService;

    @Override
    public UserInfoDto getCurrentUserInfo() {
        UserDto user = userService.getCurrentUserOrDefaultUser();

        UserInfoDto dto = new UserInfoDto();
        dto.setEmail(user.getEmail());
        dto.setIpAddress(getClientIp());
        dto.setRole(getEditorRole(user));
        return dto;
    }

    private String getClientIp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress != null && !ipAddress.isEmpty()) {
            return ipAddress.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }

    private EditorRole getEditorRole(UserDto user) {
        if (user.isAdmin()) {
            return EditorRole.ADMIN;
        }
        return switch (RequestContext.getLanguage()) {
            case PUTER -> user.getRoles().getRolePuter();
            case RUMANTSCHGRISCHUN -> user.getRoles().getRoleRumantschGrischun();
            case SURSILVAN -> user.getRoles().getRoleSursilvan();
            case SURMIRAN -> user.getRoles().getRoleSurmiran();
            case SUTSILVAN -> user.getRoles().getRoleSutsilvan();
            case VALLADER -> user.getRoles().getRoleVallader();
            default -> EditorRole.GUEST;
        };
    }
}
