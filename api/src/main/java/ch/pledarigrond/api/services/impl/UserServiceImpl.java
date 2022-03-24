package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.UserService;
import ch.pledarigrond.common.data.common.LightUserInfo;
import ch.pledarigrond.common.data.common.Role;
import ch.pledarigrond.mongodb.core.UserInfoDatabase;
import ch.pledarigrond.mongodb.exceptions.InvalidUserException;
import ch.pledarigrond.mongodb.model.PgUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public PgUserInfo getCurrentUserOrDefaultUser() {

        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        String name = "guest@pledarigrond.ch";
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            name = SecurityContextHolder.getContext().getAuthentication().getName();
        }

        try {
            return userInfoDatabase.getOrCreate(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PgUserInfo updateUser(PgUserInfo updated) throws InvalidUserException {
        logger.info("Updating user data:" + updated + "");
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        return userInfoDatabase.updateUser(updated);
    }

    public void deleteAllEntries() {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        userInfoDatabase.deleteAllEntries();
    }

    public boolean userExists(String login) {
        if (login == null)
            return false;
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        return userInfoDatabase.userExists(login);
    }

    public PgUserInfo getByEmail(String email) {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        return userInfoDatabase.getByEmail(email);
    }

    public PgUserInfo insert(PgUserInfo user) throws InvalidUserException {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        return userInfoDatabase.insert(user);
    }

    public List<PgUserInfo> getAllUsers(Role role, String text, String sortColumn, boolean sortAscending, int from, int length) {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        return userInfoDatabase.getAllUsers(text, sortColumn, sortAscending, from, length);
    }

    public List<PgUserInfo> getAllUsers(int from, int length, String sortColumn, boolean sortAscending) {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        return userInfoDatabase.getAllUsers(from, length, sortColumn, sortAscending);
    }

    public int getNumberOfUsers() {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        return userInfoDatabase.getNumberOfUsers();
    }

    public boolean deleteUser(LightUserInfo user) {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        return userInfoDatabase.deleteUser(user);
    }
}
