package ch.pledarigrond.common.data.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;

@Data
public class UserForLoginDto {
    private String id;

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    ArrayList<GrantedAuthority> roles = new ArrayList<>();

    public void addRole(GrantedAuthority role) {
        roles.add(role);
    }

    @Override
    public String toString() {
        return email;
    }
}
