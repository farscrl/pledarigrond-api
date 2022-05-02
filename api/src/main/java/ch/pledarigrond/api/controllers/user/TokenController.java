package ch.pledarigrond.api.controllers.user;

import ch.pledarigrond.api.dtos.ErrorDto;
import ch.pledarigrond.api.dtos.JwtRequest;
import ch.pledarigrond.api.dtos.JwtResponse;
import ch.pledarigrond.api.services.JwtUserDetailsService;
import ch.pledarigrond.api.services.UserService;
import ch.pledarigrond.api.transformers.LightUserToPgUserTransformer;
import ch.pledarigrond.api.utils.JwtTokenUtil;
import ch.pledarigrond.common.data.common.EditorRole;
import ch.pledarigrond.common.data.common.LightUserInfo;
import ch.pledarigrond.mongodb.exceptions.InvalidUserException;
import ch.pledarigrond.mongodb.model.PgUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/token")
public class TokenController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final PgUser userInfo = userService.getByEmail(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userInfo);

        return ResponseEntity.ok(new JwtResponse(token));

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody LightUserInfo user)  {
        PgUser pgUser = LightUserToPgUserTransformer.toPgUserInfo(user);
        pgUser.setAdmin(false);
        pgUser.setPuterRole(EditorRole.NONE);
        pgUser.setRumantschgrischunRole(EditorRole.NONE);
        pgUser.setSurmiranRole(EditorRole.NONE);
        pgUser.setSursilvanRole(EditorRole.NONE);
        pgUser.setSutsilvanRole(EditorRole.NONE);
        pgUser.setValladerRole(EditorRole.NONE);
        pgUser.setNamesRole(EditorRole.NONE);

        try {
            userService.insert(pgUser).toLightUser();
        } catch (InvalidUserException e) {
            e.printStackTrace();
            ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST, "User already exists.");
            return ResponseEntity.badRequest().body(errorDto);
        }
        final String token = jwtTokenUtil.generateToken(pgUser);
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
