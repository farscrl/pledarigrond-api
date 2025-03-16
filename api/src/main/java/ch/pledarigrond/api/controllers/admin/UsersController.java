package ch.pledarigrond.api.controllers.admin;

import ch.pledarigrond.common.data.common.ApiError;
import ch.pledarigrond.common.data.user.UserDto;
import ch.pledarigrond.common.exception.user.UserNotFoundException;
import ch.pledarigrond.database.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("")
    ResponseEntity<?> list(
            @RequestParam(name = "searchText", required = false) String searchText,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "sortColumn", required = false) String sortColumn,
            @RequestParam(name = "sortAscending", defaultValue = "true") boolean sortAscending
    ) {
        return ResponseEntity.ok(userService.getAllUsers(searchText, sortColumn, sortAscending, page, pageSize));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    ResponseEntity<?> create(@Validated @RequestBody UserDto.UserWithPasswordDto dto) {
        return ResponseEntity.ok(userService.addUser(dto));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{email}")
    ResponseEntity<?> getOne(@PathVariable("email") String email) {
        UserDto user = userService.getByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{email}")
    ResponseEntity<?> update(@PathVariable("email")String email, @Validated @RequestBody UserDto user) {
        if (!email.equals(user.getEmail())) {
            ApiError error = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "email does not match.",
                    List.of("email does not match.")
            );
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{email}")
    ResponseEntity<?> delete(@PathVariable("email") String email) {
        try {
            userService.deleteUser(email);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
