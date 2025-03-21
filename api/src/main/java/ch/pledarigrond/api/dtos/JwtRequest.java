package ch.pledarigrond.api.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtRequest {
    private String username;
    private String password;
}
