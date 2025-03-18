package ch.pledarigrond.common.data.common;

import lombok.Data;

@Data
public class UserInfoDto {
    private String email;
    private String ipAddress;
    private EditorRole role;

    public static UserInfoDto getSystemDto() {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setEmail("import@pledarigrond.ch");
        userInfoDto.setIpAddress("localhost");
        userInfoDto.setRole(EditorRole.ADMIN);
        return userInfoDto;
    }
}
