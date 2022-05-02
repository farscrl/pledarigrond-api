package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.EditorRole;
import ch.pledarigrond.common.data.common.LightUserInfo;
import ch.pledarigrond.mongodb.exceptions.InvalidUserException;
import ch.pledarigrond.mongodb.model.PgUser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {

    /**
     * Returns the user infos of the user currently logged in. If the user
     * is not logged in, a default user is returned <br>
     */
    PgUser getCurrentUserOrDefaultUser();

    /**
     * Modifies personal information of the user. Everyone with a unique user
     * name (but no guests) may execute this method. Only personal information
     * will be updated: email, firstname, lastname. <br>
     *
     * @param updated
     *            The updated user
     * @throws InvalidUserException
     */
    PgUser updateUser(PgUser updated) throws InvalidUserException;

    /**
     * For tests only! This method deletes all users in the database. <br>
     */
    void deleteAllEntries();

    /**
     * Returns <code>true</code> if a user with the given login exists. <br>
     *
     * @param login
     * @return
     */
    boolean userExists(String login);

    /**
     * Returns the user with the given Email, or <code>null</code>, if it
     * doesn't exist. <br>
     *
     * @param email
     * @return
     */
    PgUser getByEmail(String email);

    /**
     * Inserts a new user into the database. <br>
     *
     * @param user
     * @throws InvalidUserException
     */
    PgUser insert(PgUser user) throws InvalidUserException;

    /**
     * Returns the list of all users. <br>
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
    List<PgUser> getAllUsers(EditorRole role, String text, String sortColumn, boolean sortAscending, int from, int length);

    List<PgUser> getAllUsers(int from, int length, String sortColumn, boolean sortAscending);

    /**
     * Returns the number of users in the database. <br>
     *
     * @return
     */
    int getNumberOfUsers();

    /**
     * Deletes a user. <br>
     */
    boolean deleteUser(LightUserInfo user);

    public void exportData(OutputStream output, String fileName) throws JAXBException, IOException, NoSuchAlgorithmException;
}
