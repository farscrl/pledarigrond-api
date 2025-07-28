package ch.pledarigrond.users.services;

import ch.pledarigrond.common.data.user.UserDto;
import ch.pledarigrond.common.data.user.UserForLoginDto;
import ch.pledarigrond.common.exception.user.UserNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    final public static String DEFAULT_USER_NAME = "guest@pledarigrond.ch";

    /**
     * Returns user infos of the user currently logged in. If no user is logged in, a default user is returned.
     */
    UserDto getCurrentUserOrDefaultUser();

    /**
     * Modifies personal information of the user.
     * Roles can only be modified by an admin; otherwise, the roles are ignored.
     */
    UserDto updateUser(UserDto updated) throws UserNotFoundException;

    /**
     * This method deletes all users in the database.
     */
    void deleteAllEntries();

    /**
     * Returns <code>true</code> if a user with the given email exists. <br>
     */
    boolean userExists(String login);

    /**
     * Returns the user with the given Email.
     */
    UserDto getByEmail(String email) throws UserNotFoundException;

    /**
     * Returns an object with the data expected for the Spring Boot login.
     * Only for use with spring boot.
     */
    UserForLoginDto getUserForLogin(String email) throws UserNotFoundException;

    /**
     * Inserts a new user into the database.
     * Roles can only be set by an admin; otherwise, the roles are ignored and default roles are applied.
     */
    UserDto addUser(UserDto.UserWithPasswordDto user);

    /**
     * Inserts the default user into the database.
     * Used in spring boot startup. Do not expose.
     */
    UserDto generateDefaultUser(String email, String password);

    /**
     * Returns the list of roles of the user as list.
     * Only transforms the roles fo the given userDto, no DB lookup involved.
     */
    List<String> getRolesList(UserDto userDto);

    /**
     * Returns the list of all users.
     * @param searchText
     *            a substring which must match one of email, firstName or lastname. <code>null</code> for any.
     * @param sortColumn
     *            the column used for sorting, <code>null</code> for any.
     * @param sortAscending
     *            true or false, for ascending or descending
     * @param page
     *            the page number
     * @param pageSize
     *            the size of the page
     */
    Page<UserDto> getAllUsers(String searchText, String sortColumn, boolean sortAscending, int page, int pageSize);

    /**
     * Deletes a user.
     */
    void deleteUser(String email);
}
