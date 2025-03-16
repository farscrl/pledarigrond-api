package ch.pledarigrond.api.controllers.user;

import ch.pledarigrond.common.data.user.UserDto;
import ch.pledarigrond.database.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/users")
public class PublicUsersController {

    private final Logger logger = LoggerFactory.getLogger(PublicUsersController.class);

    @Autowired
    private UserService userService;

    @PostMapping()
    ResponseEntity<?> create(@Validated @RequestBody UserDto.UserWithPasswordDto dto) {
        return ResponseEntity.ok(userService.addUser(dto));
    }
}
