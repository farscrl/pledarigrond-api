package ch.pledarigrond.api.controllers.editor;

import ch.pledarigrond.api.dtos.FieldsList;
import ch.pledarigrond.api.dtos.LemmaVersionList;
import ch.pledarigrond.api.services.EditorService;
import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("{language}/editor")
public class EditorController {

    @Autowired
    private EditorService editorService;

    @Autowired
    private LuceneService luceneService;

    @Autowired
    private PgEnvironment pgEnvironment;

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
    ResponseEntity<?> getLexEntry(@PathVariable("language") Language language, @Validated @PathVariable("id") @NotNull String id) {
        try {
            return ResponseEntity.ok(editorService.getLexEntry(language, id));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @PostMapping("/lex_entries")
    ResponseEntity<?> insertLexEntry(@PathVariable("language") Language language, @RequestBody LexEntry lexEntry) {
        try {
            return ResponseEntity.ok(editorService.insert(language, lexEntry));

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @PostMapping("/lex_entries/{id}/accept_version")
    ResponseEntity<?> acceptVersion(@PathVariable("language") Language language, @Validated @PathVariable("id") @NotNull String lexEntryId, @RequestBody LemmaVersion version) {
        try {
            LexEntry entry = editorService.getLexEntry(language, lexEntryId);
            if (entry == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(editorService.accept(language, entry, version));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @PostMapping("/lex_entries/{id}/reject_version")
    ResponseEntity<?> rejectVersion(@PathVariable("language") Language language, @Validated @PathVariable("id") @NotNull String lexEntryId, @RequestBody LemmaVersion version) {
        try {
            LexEntry entry = editorService.getLexEntry(language, lexEntryId);
            if (entry == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(editorService.reject(language, entry, version));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @DeleteMapping("/lex_entries/{id}")
    ResponseEntity<?> dropEntry(@PathVariable("language") Language language, @Validated @PathVariable("id") @NotNull String lexEntryId) {
        if (lexEntryId == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        try {
            LexEntry entry = editorService.getLexEntry(language, lexEntryId);
            if (entry == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(editorService.drop(language, entry));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @PostMapping("/lex_entries/{id}/modify_and_accept_version")
    ResponseEntity<?> modifyAndAcceptVersion(@PathVariable("language") Language language, @Validated @PathVariable("id") @NotNull String lexEntryId, @RequestBody LemmaVersion modifiedVersion) {
        try {
            LexEntry entry = editorService.getLexEntry(language, lexEntryId);
            if (entry == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            LemmaVersion baseVersion = entry.getMostRecent();
            return ResponseEntity.ok(editorService.acceptAfterUpdate(language, entry, baseVersion, modifiedVersion));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @PostMapping("/lex_entries/{id}/modify_version")
    ResponseEntity<?> modifyVersion(@PathVariable("language") Language language, @Validated @PathVariable("id") @NotNull String lexEntryId, @RequestBody LemmaVersion modifiedVersion) {
        try {
            LexEntry entry = editorService.getLexEntry(language, lexEntryId);
            if (entry == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(editorService.update(language, entry, modifiedVersion));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @PostMapping("/lex_entries/{id}/drop_outdated_history")
    ResponseEntity<?> dropOutdatedHistory(@PathVariable("language") Language language, @Validated @PathVariable("id") @NotNull String lexEntryId) {
        try {
            LexEntry entry = editorService.getLexEntry(language, lexEntryId);
            if (entry == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(editorService.dropOutdatedHistory(language, entry));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @GetMapping("/search")
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
    ResponseEntity<?> reorderLexEntries(@PathVariable("language") Language language, DictionaryLanguage dictionaryLanguage, @RequestBody LemmaVersionList lemmaVersionList) {
        try {
            return ResponseEntity.ok(editorService.updateOrder(language, dictionaryLanguage, lemmaVersionList.lemmas));
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
    @PostMapping("/search_export")
    void exportBySearchQuery(@PathVariable("language") Language language, SearchCriteria query, @RequestBody FieldsList fields, HttpServletResponse response) {
        try {
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            String fileName = editorService.export(language, fields.fields, query);
            File export = new File(pgEnvironment.getTempExportLocation() + fileName);
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=" + export.getName());
            stream(response, export);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasPermission(#language, 'editor')")
    @PostMapping("/lex_entries_export")
    void exportByEditorQuery(@PathVariable("language") Language language, EditorQuery query, @RequestBody FieldsList fields, HttpServletResponse response) {
        try {
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            String fileName = editorService.export(language, fields.fields, query);
            File export = new File(pgEnvironment.getTempExportLocation() + fileName);
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=" + export.getName());
            stream(response, export);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
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

    private void stream(HttpServletResponse response, File export) throws IOException {
        InputStream is = new FileInputStream(export);
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
    }
}
