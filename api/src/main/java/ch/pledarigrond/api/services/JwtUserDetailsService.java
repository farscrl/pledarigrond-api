package ch.pledarigrond.api.services;

import ch.pledarigrond.mongodb.model.PgUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        PgUserInfo userInfo = userService.getByEmail(email);

        if (userInfo == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return new User(userInfo.getEmail(), userInfo.getPassword(), userInfo.getRoles());
    }
}
