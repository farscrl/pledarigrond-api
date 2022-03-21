package ch.pledarigrond.api.dtos;

public class JwtResponse {

    private static final long serialVersionUID = -8091879091924046844L;

    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
