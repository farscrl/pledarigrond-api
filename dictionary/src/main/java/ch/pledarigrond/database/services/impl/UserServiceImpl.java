package ch.pledarigrond.database.services.impl;

import ch.pledarigrond.common.data.common.EditorRole;
import ch.pledarigrond.common.data.user.UserDto;
import ch.pledarigrond.common.data.user.UserForLoginDto;
import ch.pledarigrond.common.exception.user.UserNotFoundException;
import ch.pledarigrond.database.services.UserService;
import ch.pledarigrond.database.user.entities.User;
import ch.pledarigrond.database.user.mappers.UserMapper;
import ch.pledarigrond.database.user.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    final public static String ROLE_PREFIX = "ROLE_";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto getCurrentUserOrDefaultUser() {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            UserForLoginDto user = (UserForLoginDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userMapper.toUserDto(userRepository.findByEmail(user.getEmail()).get());
        }
        return userMapper.toUserDto(getOrCreateGuestUser());
    }

    @Override
    public UserDto updateUser(UserDto updated) throws UserNotFoundException {
        User user = userRepository.findByEmail(updated.getEmail()).orElseThrow(() -> new UserNotFoundException(updated.getEmail()));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            user.setAdmin(updated.isAdmin());
            user.setRolePuter(updated.getRoles().getRolePuter());
            user.setRoleRumantschGrischun(updated.getRoles().getRoleRumantschGrischun());
            user.setRoleSurmiran(updated.getRoles().getRoleSurmiran());
            user.setRoleSursilvan(updated.getRoles().getRoleSursilvan());
            user.setRoleSutsilvan(updated.getRoles().getRoleSutsilvan());
            user.setRoleVallader(updated.getRoles().getRoleVallader());
            user.setRoleNames(updated.getRoles().getRoleNames());
            user.setRoleRegistrations(updated.getRoles().getRoleRegistrations());
        }

        return userMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public void deleteAllEntries() {
        userRepository.deleteAll();
    }

    @Override
    public boolean userExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDto getByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        return userMapper.toUserDto(user);
    }

    @Override
    public UserForLoginDto getUserForLogin(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        UserForLoginDto userForLoginDto = userMapper.toUserForLoginDto(user);
        addRolesListToUserForLoginDto(userForLoginDto, userMapper.toUserDto(user));
        return userForLoginDto;
    }

    @Override
    public UserDto addUser(UserDto.UserWithPasswordDto user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            newUser.setAdmin(user.isAdmin());
            newUser.setRolePuter(user.getRoles().getRolePuter());
            newUser.setRoleRumantschGrischun(user.getRoles().getRoleRumantschGrischun());
            newUser.setRoleSurmiran(user.getRoles().getRoleSurmiran());
            newUser.setRoleSursilvan(user.getRoles().getRoleSursilvan());
            newUser.setRoleSutsilvan(user.getRoles().getRoleSutsilvan());
            newUser.setRoleVallader(user.getRoles().getRoleVallader());
            newUser.setRoleNames(user.getRoles().getRoleNames());
            newUser.setRoleRegistrations(user.getRoles().getRoleRegistrations());
        }

        newUser.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return userMapper.toUserDto(userRepository.save(newUser));
    }

    @Override
    public UserDto generateDefaultUser(String email, String password) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        newUser.setAdmin(true);
        return userMapper.toUserDto(userRepository.save(newUser));
    }

    @Override
    public List<String> getRolesList(UserDto userDto) {
        List<String> roles = new ArrayList<>();
        if (userDto.isAdmin()) {
            roles.add(ROLE_PREFIX + "ADMIN");
        }

        if (userDto.getRoles().getRolePuter() == EditorRole.EDITOR) {
            roles.add(ROLE_PREFIX + "EDITOR_PUTER");
        }
        if (userDto.getRoles().getRoleRumantschGrischun() == EditorRole.EDITOR) {
            roles.add(ROLE_PREFIX + "EDITOR_RUMANTSCHGRISCHUN");
        }
        if (userDto.getRoles().getRoleSurmiran() == EditorRole.EDITOR) {
            roles.add(ROLE_PREFIX + "EDITOR_SURMIRAN");
        }
        if (userDto.getRoles().getRoleSursilvan() == EditorRole.EDITOR) {
            roles.add(ROLE_PREFIX + "EDITOR_SURSILVAN");
        }
        if (userDto.getRoles().getRoleSutsilvan() == EditorRole.EDITOR) {
            roles.add(ROLE_PREFIX + "EDITOR_SUTSILVAN");
        }
        if (userDto.getRoles().getRoleVallader() == EditorRole.EDITOR) {
            roles.add(ROLE_PREFIX + "EDITOR_VALLADER");
        }

        if (userDto.getRoles().getRoleNames() == EditorRole.EDITOR) {
            roles.add(ROLE_PREFIX + "EDITOR_NAMES");
        }

        if (userDto.getRoles().getRoleRegistrations() == EditorRole.EDITOR) {
            roles.add(ROLE_PREFIX + "EDITOR_REGISTRATIONS");
        }

        return roles;
    }

    @Override
    public Page<UserDto> getAllUsers(String searchText, String sortColumn, boolean sortAscending, int page, int pageSize) {
        Pageable pageable;
        if (sortColumn != null && !sortColumn.trim().isEmpty()) {
            Sort sort = Sort.by(sortAscending ? Sort.Direction.ASC : Sort.Direction.DESC, sortColumn);
            pageable = PageRequest.of(page, pageSize, sort);
        } else {
            pageable = PageRequest.of(page, pageSize);
        }

        return userRepository.getAllUsers(searchText, pageable).map(userMapper::toUserDto);
    }

    @Override
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        userRepository.delete(user);
    }


    private User getOrCreateGuestUser() {
        Optional<User> guestUser = userRepository.findByEmail(DEFAULT_USER_NAME);
        if (guestUser.isPresent()) {
            return guestUser.get();
        }

        User newGuestUser = new User();
        newGuestUser.setEmail(DEFAULT_USER_NAME);
        return userRepository.save(newGuestUser);
    }

    private void addRolesListToUserForLoginDto(UserForLoginDto userForLoginDto, UserDto user) {
        List<String> roles = getRolesList(user);
        for (String role : roles) {
            userForLoginDto.addRole(new SimpleGrantedAuthority(role));
        }
    }
}
