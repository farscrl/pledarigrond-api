package ch.pledarigrond.api.controllers.admin;

import ch.pledarigrond.api.services.AutomaticGenerationService;
import ch.pledarigrond.common.data.common.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("{language}/admin/generation")
public class AutomaticGenerationController {

    @Autowired
    private AutomaticGenerationService automaticGenerationService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/generate_noun_forms")
    ResponseEntity<?> generateNounForms(@PathVariable("language")Language language) {
        if (language != Language.SURSILVAN) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }
        boolean success = automaticGenerationService.generateNounForms();

        if (!success) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during form generation");
        }

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/generate_adjective_forms")
    ResponseEntity<?> generateAdjectiveForms(@PathVariable("language")Language language) {
        if (language != Language.SURSILVAN) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }
        boolean success = automaticGenerationService.generateAdjectiveForms();

        if (!success) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during form generation");
        }

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/generate_verb_forms")
    ResponseEntity<?> generateVerbForms(@PathVariable("language")Language language) {
        if (language != Language.SURSILVAN) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }
        boolean success = automaticGenerationService.generateVerbForms();

        if (!success) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during form generation");
        }

        return ResponseEntity.ok().build();
    }

    /**
     * Get verb list with probable conjugation class
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/verb_list_conjugation_class")
    ResponseEntity<?> getVerbListWithConjugationClass(@PathVariable("language")Language language) {
        if (language != Language.SURSILVAN) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }
        String data = automaticGenerationService.getVerbListWithConjugationClass();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=conjugacziuns_sursilvan.csv");
        headers.add("Content-Type", "text/csv");

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    /**
     * This command allows to migrate the data from the old database structure to the new one.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/db_migrate")
    ResponseEntity<?> migrateDbStructure(@PathVariable("language")Language language) throws IOException {
        boolean success = automaticGenerationService.migrateDb();
        if (!success) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during db migration");
        }

        return ResponseEntity.ok().build();
    }
}
