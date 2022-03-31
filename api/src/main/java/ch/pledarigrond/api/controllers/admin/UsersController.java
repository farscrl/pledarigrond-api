package ch.pledarigrond.api.controllers.admin;

import ch.pledarigrond.api.services.UserService;
import ch.pledarigrond.api.transformers.LightUserToPgUserTransformer;
import ch.pledarigrond.common.data.common.LightUserInfo;
import ch.pledarigrond.mongodb.exceptions.InvalidUserException;
import ch.pledarigrond.mongodb.model.PgUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("")
    ResponseEntity<?> list() {
        Pageable pageable = PageRequest.of(0, 10000);
        List<LightUserInfo> users = new ArrayList<>();
        for(PgUserInfo user : userService.getAllUsers(0, 10000, null, true)) {
            users.add(user.toLightUser());
        }
        return ResponseEntity.ok(new PageImpl<>(users, pageable, users.size()));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    ResponseEntity<?> create(@Validated @RequestBody LightUserInfo user) throws InvalidUserException {
        PgUserInfo pgUserInfo = LightUserToPgUserTransformer.toPgUserInfo(user);

        return ResponseEntity.ok(userService.insert(pgUserInfo).toLightUser());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{email}")
    ResponseEntity<?> getOne(@PathVariable("email")String email) {
        PgUserInfo user = userService.getByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user.toLightUser());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{email}")
    ResponseEntity<?> update(@PathVariable("email")String email, @Validated @RequestBody LightUserInfo user) throws InvalidUserException {
        if (!email.equals(user.getEmail())) {
            throw new InvalidUserException("emails are not equal");
        }

        PgUserInfo currentUser = userService.getByEmail(email);
        PgUserInfo pgUserInfo = LightUserToPgUserTransformer.updatePgUserInfo(currentUser, user);
        return ResponseEntity.ok(userService.updateUser(pgUserInfo));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{email}")
    ResponseEntity<?> delete(@PathVariable("email")String email) {
        PgUserInfo user = userService.getByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        if (userService.deleteUser(user.toLightUser())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
