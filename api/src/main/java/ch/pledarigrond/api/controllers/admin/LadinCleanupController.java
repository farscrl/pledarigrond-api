package ch.pledarigrond.api.controllers.admin;

import ch.pledarigrond.api.services.LadinCleanupService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.exception.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.UnknownHostException;

@RestController
@RequestMapping("{language}/admin/cleanup")
public class LadinCleanupController {

    @Autowired
    private LadinCleanupService ladinCleanupService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/remove_etymology")
    ResponseEntity<?> removeEtymology(@PathVariable("language")Language language) {
        if (language != Language.PUTER && language != Language.VALLADER) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }

        boolean success = false;
        try {
            success = ladinCleanupService.removeEtymology(language);
        } catch (DatabaseException | UnknownHostException e) {
            throw new RuntimeException(e);
        }

        if (!success) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during form generation");
        }

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/remove_empty_words")
    ResponseEntity<?> removeEmptyWords(@PathVariable("language")Language language) {
        if (language != Language.PUTER && language != Language.VALLADER) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }

        boolean success = false;
        try {
            success = ladinCleanupService.removeEmptyWords(language);
        } catch (DatabaseException | UnknownHostException e) {
            throw new RuntimeException(e);
        }

        if (!success) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during form generation");
        }

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/remove_old_forms")
    ResponseEntity<?> removeOldForms(@PathVariable("language")Language language) {
        if (language != Language.PUTER && language != Language.VALLADER) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }

        boolean success = false;
        try {
            success = ladinCleanupService.removeOldForms(language);
        } catch (DatabaseException | UnknownHostException e) {
            throw new RuntimeException(e);
        }

        if (!success) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during form generation");
        }

        return ResponseEntity.ok().build();
    }

}
