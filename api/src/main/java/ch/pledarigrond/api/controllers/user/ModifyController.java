package ch.pledarigrond.api.controllers.user;

import ch.pledarigrond.api.services.EditorService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("{language}/user/modify")
public class ModifyController {

    private final Logger logger = LoggerFactory.getLogger(ModifyController.class);

    @Autowired
    private EditorService editorService;

    @PostMapping("/new")
    public ResponseEntity<?> suggestNewEntry(@PathVariable("language") Language language, @Validated @RequestBody EntryVersionDto newVersion) {
        try {
            validateUserModification(newVersion);
            EntryDto entry = editorService.addEntry(newVersion, true);
            return ResponseEntity.ok(entry);
        } catch (Exception e) {
            logger.error("Error while suggesting new entry", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/modify/{id}")
    public ResponseEntity<?> suggestModification(@PathVariable("language") Language language, @PathVariable("id") String entryId, @Validated @RequestBody EntryVersionDto newVersion) {
        try {
            validateUserModification(newVersion);
            EntryDto entry = editorService.addSuggestion(entryId, newVersion);
            return ResponseEntity.ok(entry);
        } catch (Exception e) {
            logger.error("Error while suggesting modification for entry ID: {}", entryId, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void validateUserModification(EntryVersionDto version) {
        if (
                version.getRmStichwort().isBlank() &&
                version.getDeStichwort().isBlank() &&
                version.getUserComment().isBlank()
        ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
