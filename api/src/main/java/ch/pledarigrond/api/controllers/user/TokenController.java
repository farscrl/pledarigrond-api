package ch.pledarigrond.api.controllers.user;

import ch.pledarigrond.api.dtos.JwtRequest;
import ch.pledarigrond.api.dtos.JwtResponse;
import ch.pledarigrond.api.utils.JwtTokenUtil;
import ch.pledarigrond.common.data.user.UserDto;
import ch.pledarigrond.users.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user/token")
public class TokenController {

    private final Logger logger = LoggerFactory.getLogger(TokenController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDto userInfo = userService.getByEmail(authenticationRequest.getUsername());
        List<String> roles = userService.getRolesList(userInfo);
        final String token = jwtTokenUtil.generateToken(userInfo, roles);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody UserDto.UserWithPasswordDto dto)  {
        UserDto newUser = userService.addUser(dto);
        List<String> roles = userService.getRolesList(newUser);
        final String token = jwtTokenUtil.generateToken(newUser, roles);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
