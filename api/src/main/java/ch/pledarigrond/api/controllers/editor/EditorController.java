package ch.pledarigrond.api.controllers.editor;

import ch.pledarigrond.api.services.EditorService;
import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("{language}/editor")
public class EditorController {

    @Autowired
    private EditorService editorService;

    @Autowired
    private LuceneService luceneService;

    @PreAuthorize("hasPermission(#language, 'editor')")
    @GetMapping("/lex_entries")
    ResponseEntity<?> getLexEntries(@PathVariable("language") Language language, EditorQuery editorQuery, Pagination pagination) {
        try {
            Page<LexEntry> list = editorService.getLexEntries(language, editorQuery, pagination);
            return ResponseEntity.ok(list);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @GetMapping("/lex_entries/{id}")
    ResponseEntity<?> getLexEntry(@PathVariable("language") Language language, @PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(editorService.getLexEntry(language, id));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @PostMapping("/lex_entries")
    ResponseEntity<?> insertLexEntry(@PathVariable("language") Language language, LexEntry lexEntry) {
        try {
            return ResponseEntity.ok(editorService.insert(language, lexEntry));

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @PostMapping("/accept_version")
    ResponseEntity<?> acceptVersion(@PathVariable("language") Language language, LexEntry entry, LemmaVersion version) {
        try {
            return ResponseEntity.ok(editorService.accept(language, entry, version));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @PostMapping("/reject_version")
    ResponseEntity<?> rejectVersion(@PathVariable("language") Language language, LexEntry entry, LemmaVersion version) {
        try {
            return ResponseEntity.ok(editorService.reject(language, entry, version));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @PostMapping("/drop_entry")
    ResponseEntity<?> dropEntry(@PathVariable("language") Language language, LexEntry entry) {
        try {
            return ResponseEntity.ok(editorService.drop(language, entry));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @PostMapping("/modify_and_accept_version")
    ResponseEntity<?> modifyAndAcceptVersion(@PathVariable("language") Language language, LexEntry entry, LemmaVersion baseVersion, LemmaVersion modifiedVersion) {
        try {
            return ResponseEntity.ok(editorService.acceptAfterUpdate(language, entry, baseVersion, modifiedVersion));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @PostMapping("/modify_version")
    ResponseEntity<?> modifyVersion(@PathVariable("language") Language language, LexEntry entry, LemmaVersion modifiedVersion) {
        try {
            return ResponseEntity.ok(editorService.update(language, entry, modifiedVersion));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @PostMapping("/drop_outdated_history")
    ResponseEntity<?> dropOutdatedHistory(@PathVariable("language") Language language, LexEntry entry) {
        try {
            return ResponseEntity.ok(editorService.dropOutdatedHistory(language, entry));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @PostMapping("/search")
    ResponseEntity<?> searchLexEntries(@PathVariable("language") Language language, SearchCriteria searchCriteria, Pagination pagination) {
        try {
            return ResponseEntity.ok(editorService.search(language, searchCriteria, pagination));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @PostMapping("/update_order")
    ResponseEntity<?> reorderLexEntries(@PathVariable("language") Language language, DictionaryLanguage dictionaryLanguage, List<LemmaVersion> ordered) {
        try {
            return ResponseEntity.ok(editorService.updateOrder(language, dictionaryLanguage, ordered));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @GetMapping("/get_order")
    ResponseEntity<?> getLexEntriesOrder(@PathVariable("language") Language language, String lemma, DictionaryLanguage dictionaryLanguage) {
        try {
            return ResponseEntity.ok(editorService.getOrder(language, lemma, dictionaryLanguage));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @GetMapping("/search_export")
    public void exportBySearchQuery(@PathVariable("language") Language language, Set<String> fields, SearchCriteria query) {
        try {
            editorService.export(language, fields, query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @GetMapping("/lex_entries_export")
    public void exportByEditorQuery(@PathVariable("language") Language language, Set<String> fields, EditorQuery query) {
        try {
            editorService.export(language, fields, query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @GetMapping("/search_suggestions_choice")
    public ResponseEntity<?> getSearchSuggestionsChoice(@PathVariable("language") Language language) {
        try {
            return ResponseEntity.ok(editorService.getSuggestionsForFields(language));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @GetMapping("/search_suggestions")
    public ResponseEntity<?> getSearchSuggestions(@PathVariable("language") Language language, String field, String searchTerm) {
        if (searchTerm == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no value sent");
        }
        try {
            return ResponseEntity.ok(luceneService.getSuggestionsForField(language, field, searchTerm, 10));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
