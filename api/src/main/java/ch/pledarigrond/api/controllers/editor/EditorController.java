package ch.pledarigrond.api.controllers.editor;

import ch.pledarigrond.api.services.EditorService;
import ch.pledarigrond.common.data.common.EditorQuery;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.user.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("{language}/editor")
public class EditorController {

    @Autowired
    private EditorService editorService;

    // TODO: authorization
    @PostMapping("/search")
    ResponseEntity<?> getLexEntries(@PathVariable("language") Language language, EditorQuery editorQuery, Pagination pagination) {
        try {
            Page<LexEntry> list = editorService.getLexEntries(language, editorQuery, pagination);
            return ResponseEntity.ok(list);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
