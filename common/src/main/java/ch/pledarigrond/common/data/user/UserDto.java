package ch.pledarigrond.common.data.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class UserDto {
    private String id;

    private String email;
    private String firstName;
    private String lastName;

    // permissions
    private boolean isAdmin = false;
    private RolesDto roles;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class UserWithPasswordDto extends UserDto {
        private String password;
    }
}
