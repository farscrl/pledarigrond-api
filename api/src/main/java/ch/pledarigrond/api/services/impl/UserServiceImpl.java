package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.UserService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.EditorRole;
import ch.pledarigrond.common.data.common.LightUserInfo;
import ch.pledarigrond.mongodb.core.UserInfoDatabase;
import ch.pledarigrond.mongodb.exceptions.InvalidUserException;
import ch.pledarigrond.mongodb.model.PgUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final PgEnvironment pgEnvironment;

    public UserServiceImpl(PgEnvironment pgEnvironment) {
        this.pgEnvironment = pgEnvironment;
    }

    @Override
    public PgUser getCurrentUserOrDefaultUser() {

        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance(pgEnvironment);
        String name = PgUser.DEFAULT_USER_NAME;
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            name = SecurityContextHolder.getContext().getAuthentication().getName();
        }

        try {
            return userInfoDatabase.getOrCreate(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PgUser updateUser(PgUser updated) throws InvalidUserException {
        logger.info("Updating user data:" + updated + "");
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance(pgEnvironment);
        return userInfoDatabase.updateUser(updated);
    }

    @Override
    public void deleteAllEntries() {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance(pgEnvironment);
        userInfoDatabase.deleteAllEntries();
    }

    @Override
    public boolean userExists(String login) {
        if (login == null)
            return false;
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance(pgEnvironment);
        return userInfoDatabase.userExists(login);
    }

    @Override
    public PgUser getByEmail(String email) {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance(pgEnvironment);
        return userInfoDatabase.getByEmail(email);
    }

    @Override
    public PgUser insert(PgUser user) throws InvalidUserException {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance(pgEnvironment);
        return userInfoDatabase.insert(user);
    }

    @Override
    public List<PgUser> getAllUsers(EditorRole role, String text, String sortColumn, boolean sortAscending, int from, int length) {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance(pgEnvironment);
        return userInfoDatabase.getAllUsers(text, sortColumn, sortAscending, from, length);
    }

    @Override
    public List<PgUser> getAllUsers(int from, int length, String sortColumn, boolean sortAscending) {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance(pgEnvironment);
        return userInfoDatabase.getAllUsers(from, length, sortColumn, sortAscending);
    }

    @Override
    public int getNumberOfUsers() {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance(pgEnvironment);
        return userInfoDatabase.getNumberOfUsers();
    }

    @Override
    public boolean deleteUser(LightUserInfo user) {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance(pgEnvironment);
        return userInfoDatabase.deleteUser(user);
    }

    @Override
    public void exportData(OutputStream output, String fileName) throws JAXBException, IOException, NoSuchAlgorithmException {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance(pgEnvironment);
        userInfoDatabase.exportData(output, fileName);
    }
}
