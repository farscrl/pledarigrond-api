package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.UserService;
import ch.pledarigrond.common.config.Constants;
import ch.pledarigrond.common.data.common.LightUserInfo;
import ch.pledarigrond.common.data.common.Role;
import ch.pledarigrond.mongodb.core.UserInfoDatabase;
import ch.pledarigrond.mongodb.exceptions.InvalidUserException;
import ch.pledarigrond.mongodb.model.PgUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * Returns the user infos of the user currently logged in. If no user infos
     * exist for this user, a new user will be stored. <br>
     * <strong>Security:</strong> Any user may call this method.
     *
     * @return
     */
    // @Secured( { Constants.Roles.GUEST_1, Constants.Roles.OPENID_2,
    // Constants.Roles.TRUSTED_EXTERNAL_3, Constants.Roles.TRUSTED_INTERNAL_4,
    // Constants.Roles.ADMIN_5 })
    @Deprecated
    public PgUserInfo getOrCreateCurrentUser() {

        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        String name;
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            name = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            name = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRemoteAddr();
        }
        try {
            return userInfoDatabase.getOrCreate(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Modifies the role of the referenced user. The referenced user must exist. <br>
     * <strong>Security:</strong> Only admins may call this method.
     *
     * @param user
     * @param role
     * @throws InvalidUserException
     */
    @Secured(Constants.Roles.ADMIN_5)
    public void updateUserRole(PgUserInfo user, Role role) throws InvalidUserException {

        if (user.getLogin().equals("admin")) {
            throw new InvalidUserException("Not allowed to change admin role!");
        }
        if (user.getRole().equals(role))
            return;

        logger.info("Updating role of '" + user.getLogin() +"' from " + user.getRole() + " to " + role);

        user.setRole(role);
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        userInfoDatabase.updateUser(user);
    }

    /**
     * Modifies personal information of the user. Everyone with a unique user
     * name (but no guests) may execute this method. Only personal information
     * will be updated: email, firstname, lastname. <br>
     * <strong>Security:</strong> Any non-guest may call this method for
     * himself. Admins may call this method for everyone.
     *
     * @param updated
     *            The updated user
     * @throws InvalidUserException
     *             If the currently logged in user is not the user to update,
     *             and the currently logged in user is not an admin.
     */
    @Secured(Constants.Roles.ADMIN_5)
    public void updateUserFields(PgUserInfo updated) throws InvalidUserException {
        PgUserInfo current = getOrCreateCurrentUser();
        PgUserInfo toUpdate = null;
        if (current.getRole() == Role.ADMIN_5) {
            toUpdate = updated;
        } else {
            if (!current.getLogin().equals(updated.getLogin())) {
                // Only the logged in user may change these properties
                throw new InvalidUserException("Action not allowed");
            }
            toUpdate = current;
        }
        logger.info("Updating user data - old:" + toUpdate + ", new: " + updated + " (ignoring any role changes)");
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        userInfoDatabase.updateUser(toUpdate);
    }

    /**
     * For tests only! This method deletes all users in the database. <br>
     * <strong>Security:</strong> Only admins may call this method. However,
     * they should not.
     */
    @Secured(Constants.Roles.ADMIN_5)
    public void deleteAllEntries() {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        userInfoDatabase.deleteAllEntries();
    }

    /**
     * Returns <code>true</code> if a user with the given login exists. <br>
     * <strong>Security:</strong> Any user may call this method.
     *
     * @param login
     * @return
     */
    public boolean userExists(String login) {
        if (login == null)
            return false;
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        return userInfoDatabase.userExists(login);
    }

    /**
     * Returns the user with the given login, or <code>null</code>, if it
     * doesn't exist. <br>
     * <strong>Security:</strong> Any user may call this method.
     *
     * @param login
     * @return
     */
    public PgUserInfo getByLogin(String login) {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        return userInfoDatabase.getByLogin(login);
    }

    /**
     * Returns the user with the given Email, or <code>null</code>, if it
     * doesn't exist. <br>
     * <strong>Security:</strong> Any user may call this method.
     *
     * @param email
     * @return
     */
    public PgUserInfo getByEmail(String email) {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        return userInfoDatabase.getByEmail(email);
    }

    /**
     * Inserts a new user into the database. <br>
     * <strong>Security:</strong> Any user may call this method.
     *
     * @param user
     * @throws InvalidUserException
     */
    public PgUserInfo insert(PgUserInfo user) throws InvalidUserException {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        return userInfoDatabase.insert(user);
    }

    /**
     * Returns the list of all users. <br>
     * <strong>Security:</strong> Only admins may call this method.
     *
     * @param role
     *            the role to match, or <code>null</code> for any role
     * @param text
     *            a substring which must match one of email, login, firstname,
     *            or lastname. <code>null</code> for any.
     * @param sortColumn
     *            the column used for sorting, as defined in
     *            {@link LightUserInfo}. <code>null</code> for any.
     * @param sortAscending
     *            true or false, for ascending or descending
     * @param from
     *            the start index in the returned list
     * @param length
     *            the number of elements to return
     * @return
     */

    @Secured(Constants.Roles.ADMIN_5)
    public List<PgUserInfo> getAllUsers(Role role, String text, String sortColumn, boolean sortAscending, int from, int length) {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        return userInfoDatabase.getAllUsers(role, text, sortColumn, sortAscending, from, length);
    }

    @Secured(Constants.Roles.ADMIN_5)
    public List<PgUserInfo> getAllUsers(int from, int length, String sortColumn, boolean sortAscending) {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        return userInfoDatabase.getAllUsers(from, length, sortColumn, sortAscending);
    }

    /**
     * Returns the number of users in the database. <br>
     * <strong>Security:</strong> Only admins may call this method.
     *
     * @return
     */
    @Secured(Constants.Roles.ADMIN_5)
    public int getNumberOfUsers() {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        return userInfoDatabase.getNumberOfUsers();
    }

    @Secured(Constants.Roles.ADMIN_5)
    public boolean deleteUser(LightUserInfo user) {
        UserInfoDatabase userInfoDatabase = UserInfoDatabase.getInstance();
        return userInfoDatabase.deleteUser(user);
    }
}
