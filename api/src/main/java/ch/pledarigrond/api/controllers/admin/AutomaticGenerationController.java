package ch.pledarigrond.api.controllers.admin;

import ch.pledarigrond.api.services.AutomaticGenerationService;
import ch.pledarigrond.common.data.common.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("{language}/admin/generation")
public class AutomaticGenerationController {

    @Autowired
    private AutomaticGenerationService automaticGenerationService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/generate_noun_forms")
    ResponseEntity<?> generateNounForms(@PathVariable("language")Language language) {
        boolean success = automaticGenerationService.generateNounForms(language);

        if (!success) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during form generation");
        }

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/generate_adjective_forms")
    ResponseEntity<?> generateAdjectiveForms(@PathVariable("language")Language language) {
        boolean success = automaticGenerationService.generateAdjectiveForms(language);

        if (!success) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during form generation");
        }

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/generate_verb_forms")
    ResponseEntity<?> generateVerbForms(@PathVariable("language")Language language) {
        boolean success = automaticGenerationService.generateVerbForms(language);

        if (!success) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during form generation");
        }

        return ResponseEntity.ok().build();
    }
}
