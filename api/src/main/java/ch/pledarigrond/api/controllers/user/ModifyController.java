package ch.pledarigrond.api.controllers.user;

import ch.pledarigrond.api.services.MongoDbService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
import com.mongodb.BasicDBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("{language}/user/modify")
public class ModifyController {

    private final Logger logger = LoggerFactory.getLogger(ModifyController.class);

    @Autowired
    private MongoDbService mongoDbService;

    @Autowired
    private PgEnvironment pgEnvironment;

    @PostMapping("/new")
    public ResponseEntity<?> suggestNewEntry(@PathVariable("language") Language language, @RequestBody LemmaVersion newVersion) {
        LexEntry lexEntry = new LexEntry(newVersion);
        try {
            mongoDbService.suggestNewEntry(language, lexEntry);
        } catch (Exception e) {
            logger.error("Error while suggesting new entry", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(lexEntry);
    }

    @PostMapping("/modify/{id}")
    public ResponseEntity<?> suggestModification(@PathVariable("language") Language language, @PathVariable("id") String id, @RequestBody LemmaVersion newVersion) {
        try {
            BasicDBObject old = Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language)).getById(id);
            LexEntry oldEntry = Converter.convertToLexEntry(old);
            mongoDbService.suggestUpdate(language, oldEntry, newVersion);

            BasicDBObject updated = Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language)).getById(id);
            LexEntry updatedEntry = Converter.convertToLexEntry(updated);
            return ResponseEntity.ok(updatedEntry);
        } catch (Exception e) {
            logger.error("Error while suggesting modification for id: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
