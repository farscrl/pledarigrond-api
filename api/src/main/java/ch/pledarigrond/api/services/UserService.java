package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.LightUserInfo;
import ch.pledarigrond.common.data.common.Role;
import ch.pledarigrond.mongodb.exceptions.InvalidUserException;
import ch.pledarigrond.mongodb.model.PgUserInfo;

import java.util.List;

public interface UserService {

    PgUserInfo getOrCreateCurrentUser();

    void updateUserRole(PgUserInfo user, Role role) throws InvalidUserException;

    void updateUserFields(PgUserInfo updated) throws InvalidUserException;

    void deleteAllEntries();

    boolean userExists(String login);

    PgUserInfo getByLogin(String login);

    PgUserInfo getByEmail(String email);

    PgUserInfo insert(PgUserInfo user) throws InvalidUserException;

    List<PgUserInfo> getAllUsers(Role role, String text, String sortColumn, boolean sortAscending, int from, int length);

    List<PgUserInfo> getAllUsers(int from, int length, String sortColumn, boolean sortAscending);

    int getNumberOfUsers();

    boolean deleteUser(LightUserInfo user);
}
