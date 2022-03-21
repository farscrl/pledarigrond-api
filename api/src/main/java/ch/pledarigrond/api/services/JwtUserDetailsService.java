package ch.pledarigrond.api.services;

import ch.pledarigrond.mongodb.model.PgUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        PgUserInfo userInfo = userService.getByLogin(username);

        if (userInfo == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new User(userInfo.getLogin(), userInfo.getPassword(), new ArrayList<>());
    }
}
