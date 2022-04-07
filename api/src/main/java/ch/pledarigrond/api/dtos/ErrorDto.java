package ch.pledarigrond.api.dtos;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class ErrorDto {
    private HttpStatus errorStatus;
    private String message;
    private Date timestamp = new Date();

    public ErrorDto(HttpStatus errorStatus, String message) {
        this.errorStatus = errorStatus;
        this.message = message;
    }
}
