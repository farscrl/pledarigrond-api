package ch.pledarigrond.api.controllers.editor;

import ch.pledarigrond.api.services.RegistrationService;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.pronunciation.entities.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.UnknownHostException;

@RestController
@RequestMapping("{language}/editor/registrations")
public class RegistrationsController {

    @Autowired
    private RegistrationService registrationService;

    @PreAuthorize("hasPermission('registrations', 'editor')")
    @GetMapping("")
    ResponseEntity<?> list(Pagination pagination) {
        Page<Registration> registrations = registrationService.getRegistrations(pagination.getPage(), pagination.getPageSize());
        return ResponseEntity.ok(registrations);
    }

    @PreAuthorize("hasPermission('registrations', 'editor')")
    @GetMapping("/next")
    ResponseEntity<?> getNext() {
        return ResponseEntity.ok(registrationService.getNextRegistration());
    }

    @PreAuthorize("hasPermission('registrations', 'editor')")
    @PostMapping("/postpone")
    ResponseEntity<?> postpone(@Validated @RequestBody Registration registration) {
        return ResponseEntity.ok(registrationService.postponeRegistration(registration));
    }

    @PreAuthorize("hasPermission('registrations', 'editor')")
    @GetMapping("/statistics")
    ResponseEntity<?> statistics() {
        return ResponseEntity.ok(registrationService.getStatistics());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/extract_single_words")
    ResponseEntity<?> extractSingleWords() throws UnknownHostException, DatabaseException {
        registrationService.extractSingleWords();
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasPermission('registrations', 'editor')")
    @PostMapping("/upload")
    public ResponseEntity<Registration> uploadFile(@RequestPart("registration") Registration registration, @RequestPart("file") MultipartFile file) {
        try {
            registration = registrationService.uploadRegistration(registration, file);
            return ResponseEntity.ok(registration);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
