package ch.pledarigrond.api.controllers.user;

import ch.pledarigrond.api.services.UserService;
import ch.pledarigrond.common.data.common.LightUserInfo;
import ch.pledarigrond.mongodb.exceptions.InvalidUserException;
import ch.pledarigrond.mongodb.model.PgUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("user/users")
public class PublicUsersController {

    private final Logger logger = LoggerFactory.getLogger(PublicUsersController.class);

    @Autowired
    private UserService userService;

    @PostMapping()
    ResponseEntity<?> create(@Validated @RequestBody LightUserInfo user) {
        PgUser pgUser = new PgUser(user.getEmail());
        pgUser.setPassword(user.getPassword());

        try {
            return ResponseEntity.ok(userService.insert(pgUser).toLightUser());
        } catch (InvalidUserException e) {
            logger.error("Error while creating user", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
