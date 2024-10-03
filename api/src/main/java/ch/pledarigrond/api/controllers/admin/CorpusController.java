package ch.pledarigrond.api.controllers.admin;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.corpus.services.CorpusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("{language}/admin/corpus")
public class CorpusController {

    @Autowired
    CorpusService corpusService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/import_laquotidiana")
    ResponseEntity<?> laquotidiana() {
        if (corpusService.importQuotidianaCorpusFiles()) {
            return ResponseEntity.ok().build();
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Import error.");
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @GetMapping("/find")
    ResponseEntity<?> findInCorpus(@PathVariable("language") Language language, @RequestParam String s) {
        return ResponseEntity.ok(corpusService.findInCorpus(language, s));
    }
}
