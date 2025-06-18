package ch.pledarigrond.api.config;

import ch.pledarigrond.api.services.SlackService;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Profile("!dev")
public class GlobalExceptionHandler {

    private final SlackService slackService;

    public GlobalExceptionHandler(SlackService slackService) {
        this.slackService = slackService;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        slackService.sendException(ex, "Global error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
    }
}
