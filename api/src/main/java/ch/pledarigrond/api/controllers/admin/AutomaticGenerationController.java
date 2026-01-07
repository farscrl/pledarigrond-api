package ch.pledarigrond.api.controllers.admin;

import ch.pledarigrond.api.services.AutomaticGenerationService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.dictionary.DuplicateGroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

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
     * Delete all exact duplicate entries.
     * Without the 'delete' parameter or when delete=false, returns a JSON list of all duplicate groups without deleting them.
     * With delete=true, performs the actual deletion and returns the list of deleted duplicate groups.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/delete_exact_duplicates")
    ResponseEntity<?> deleteExactDuplicates(@PathVariable("language") Language language, @RequestParam(value = "delete", required = false, defaultValue = "false") boolean delete) {
        try {
            List<DuplicateGroupDto> duplicateGroups = automaticGenerationService.deleteExactDuplicates(delete);
            return ResponseEntity.ok().body(duplicateGroups);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    delete ? "Error during duplicate deletion" : "Error during duplicate detection");
        }
    }

    /**
     * Set auxiliar "haver" for transitive verbs that don't have a composedWith value.
     * Returns the count of updated entries.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/set_auxiliar")
    ResponseEntity<?> setAuxiliar(@PathVariable("language") Language language) {
        if (language != Language.SURSILVAN) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }

        try {
            int updatedCount = automaticGenerationService.setAuxiliarForTransitiveVerbs();
            return ResponseEntity.ok().body(Map.of("updatedCount", updatedCount));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error during auxiliar setting");
        }
    }
}
