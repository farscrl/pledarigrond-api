package ch.pledarigrond.api.controllers.admin;

import ch.pledarigrond.api.services.AutomaticGenerationService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
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

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

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
        boolean success = automaticGenerationService.generateNounForms(language);

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
        boolean success = automaticGenerationService.generateAdjectiveForms(language);

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
        boolean success = automaticGenerationService.generateVerbForms(language);

        if (!success) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during form generation");
        }

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/fix_missing_ids")
    ResponseEntity<?> fixMissingIds(@PathVariable("language")Language language) throws DatabaseException, UnknownHostException {
        boolean success = automaticGenerationService.fixMissingIds(language);

        if (!success) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during id fix");
        }

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/list_wrong_next_ids")
    ResponseEntity<?> listWrongNextIds(@PathVariable("language")Language language) throws DatabaseException {
        return ResponseEntity.ok(automaticGenerationService.listWrongNextIds(language));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/fix_wrong_next_ids")
    ResponseEntity<?> fixWrongNextIds(@PathVariable("language")Language language) throws DatabaseException, UnknownHostException {
        boolean success = automaticGenerationService.fixWrongNextIds(language);

        if (!success) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during next ID fix");
        }

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/find_entries_with_wrong_state")
    ResponseEntity<?> findEntriesWithWrongState(@PathVariable("language")Language language) throws NoDatabaseAvailableException {
        List<LexEntry> entries = automaticGenerationService.findEntriesWithWrongState(language);


        List<String> returnValue = new ArrayList<>();
        for(LexEntry lexEntry: entries) {
            returnValue.add(lexEntry.getMostRecent().getEntryValue("DStichwort") + " <-> " + lexEntry.getMostRecent().getEntryValue("RStichwort"));
        }
        return ResponseEntity.ok(returnValue);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/fix_entries_with_wrong_state")
    ResponseEntity<?> fixEntriesWithWrongState(@PathVariable("language")Language language) throws DatabaseException, UnknownHostException {
        boolean success = automaticGenerationService.fixEntriesWithWrongState(language);

        if (!success) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during status fix");
        }

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add_enclitic_forms")
    ResponseEntity<?> addEncliticForms(@PathVariable("language")Language language) throws DatabaseException, UnknownHostException {
        boolean success = automaticGenerationService.addEncliticForms(language);

        if (!success) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during generation of enclitic forms");
        }

        return ResponseEntity.ok().build();
    }

    /**
     * List errors in sutsilvan caused by duplication of lemmas during automatic generation
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/fix_automatic_duplication_errors")
    ResponseEntity<?> fixAutomaticDuplicationErrors(@PathVariable("language")Language language) throws DatabaseException, UnknownHostException {
        if (language != Language.SUTSILVAN) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }
        boolean success = automaticGenerationService.fixAutomaticDuplicationErrors(language);

        if (!success) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during changing grammar indications");
        }

        return ResponseEntity.ok().build();
    }


    /**
     * List errors in sutsilvan caused by duplication of lemmas during automatic generation
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/fix_no_accepted_version")
    ResponseEntity<?> fixNoAcceptedEntries(@PathVariable("language")Language language) throws DatabaseException, UnknownHostException {
        if (language != Language.SUTSILVAN) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }
        boolean success = automaticGenerationService.fixValuesWithNoAcceptedVersion(language);

        if (!success) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during changing grammar indications");
        }

        return ResponseEntity.ok().build();
    }

    /**
     * List errors in sutsilvan caused by duplication of lemmas during automatic generation
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/fix_wrong_parent_id")
    ResponseEntity<?> fixWrongParentId(@PathVariable("language")Language language) throws DatabaseException, UnknownHostException {
        if (language != Language.SUTSILVAN) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }
        boolean success = automaticGenerationService.fixWrongParentId(language);

        if (!success) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during fixing wrong parent id");
        }

        return ResponseEntity.ok().build();
    }

    /**
     * Remove subst indication for puter and vallader
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/remove_subst_indication_for_puter_vallader")
    ResponseEntity<?> removeSubstIndicationForPuterVallader(@PathVariable("language")Language language) throws DatabaseException, UnknownHostException {
        if (language != Language.PUTER && language != Language.VALLADER) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }
        boolean success = automaticGenerationService.removeSubstIndicationIfGenusIsSet(language);

        if (!success) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during removing subst indication");
        }

        return ResponseEntity.ok().build();
    }

    /**
     * Move sutsilvan conjunctivimperfect verbs to conjunctiv2
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/move_conjunctiv_imperfect_to_conjunctiv_2")
    ResponseEntity<?> moveConjunctivImperfectToConjunctiv2(@PathVariable("language")Language language) throws DatabaseException, UnknownHostException {
        if (language != Language.SUTSILVAN) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }
        boolean success = automaticGenerationService.moveConjunctivImperfectToConjunctiv2(language);

        if (!success) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during verb moving from conjunctivimperfect to conjunctiv2");
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
        String data = automaticGenerationService.getVerbListWithConjugationClass(language);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=conjugacziuns_sursilvan.csv");
        headers.add("Content-Type", "text/csv");

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
