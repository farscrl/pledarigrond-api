package ch.pledarigrond.api.controllers.editor;

import ch.pledarigrond.api.dtos.EntryVersionList;
import ch.pledarigrond.api.dtos.FieldsList;
import ch.pledarigrond.api.services.EditorService;
import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.api.services.SursilvanVerbService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.DictionaryLanguage;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.dictionary.EditorQuery;
import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.common.data.dictionary.NormalizedEntryVersionsDto;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("{language}/editor")
public class EditorController {

    private final Logger logger = LoggerFactory.getLogger(EditorController.class);

    @Autowired
    private EditorService editorService;

    @Autowired
    private LuceneService luceneService;

    @Autowired
    private PgEnvironment pgEnvironment;

    @Autowired
    private SursilvanVerbService verbService;

    @PreAuthorize("hasPermission('language', 'editor')")
    @GetMapping("/entries")
    ResponseEntity<?> getDictionaryVersions(@PathVariable("language") Language language, @Validated EditorQuery editorQuery, Pagination pagination) {
        try {
            Page<NormalizedEntryVersionsDto> list = editorService.getDictionaryVersions(editorQuery, pagination);
            return ResponseEntity.ok(list);

        } catch (Exception e) {
            logger.error("Error while fetching dictionary versions", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @GetMapping("/entries/{id}")
    ResponseEntity<EntryDto> getEntry(@PathVariable("language") Language language, @PathVariable("id") @NotNull String id) {
        try {
            return ResponseEntity.ok(editorService.getEntry(id));
        } catch (Exception e) {
            logger.error("Error while fetching entry", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @PostMapping("/entries")
    ResponseEntity<?> insertEntry(@PathVariable("language") Language language, @Validated @RequestBody EntryVersionDto version, boolean asSuggestion) {
        try {
            return ResponseEntity.ok(editorService.addEntry(version, asSuggestion));
        } catch (Exception e) {
            logger.error("Error while inserting entry", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @PostMapping("/entries/{id}/accept_version/{versionId}")
    ResponseEntity<?> acceptVersion(@PathVariable("language") Language language, @PathVariable("id") @NotNull String entryId, @PathVariable("versionId") @NotNull String versionIdToAccept) {
        try {
            return ResponseEntity.ok(editorService.accept(entryId, versionIdToAccept));
        } catch (Exception e) {
            logger.error("Error while accepting version", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @PostMapping("/entries/{id}/reject_version/{versionId}")
    ResponseEntity<?> rejectVersion(@PathVariable("language") Language language, @PathVariable("id") @NotNull String entryId, @PathVariable("versionId") @NotNull String versionIdToReject) {
        try {
            return ResponseEntity.ok(editorService.reject(entryId, versionIdToReject));
        } catch (Exception e) {
            logger.error("Error while rejecting version", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @DeleteMapping("/entries/{id}")
    ResponseEntity<?> deleteEntry(@PathVariable("language") Language language, @PathVariable("id") @NotNull String entryId) {
        try {
            editorService.delete(entryId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error while dropping entry", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @PostMapping("/entries/{id}/add_version")
    ResponseEntity<?> addVersion(@PathVariable("language") Language language, @PathVariable("id") @NotNull String entryId, @Validated @RequestBody EntryVersionDto modifiedVersion) {
        try {
            return ResponseEntity.ok(editorService.addVersion(entryId, modifiedVersion, false));
        } catch (Exception e) {
            logger.error("Error while modifying and accepting version", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @PostMapping("/entries/{id}/suggest_version")
    ResponseEntity<?> suggestVersion(@PathVariable("language") Language language, @PathVariable("id") @NotNull String entryId, @Validated @RequestBody EntryVersionDto modifiedVersion) {
        try {
            return ResponseEntity.ok(editorService.addVersion(entryId, modifiedVersion, true));
        } catch (Exception e) {
            logger.error("Error while modifying version", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @PostMapping("/entries/{id}/review_later_version/{versionId}")
    ResponseEntity<?> reviewLaterVersion(@PathVariable("language") Language language, @PathVariable("id") @NotNull String entryId, @PathVariable("versionId") @NotNull String versionId) {
        try {
            return ResponseEntity.ok(editorService.reviewSuggestionLater(entryId, versionId));
        } catch (Exception e) {
            logger.error("Error while moving version to review later", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @PostMapping("/entries/{id}/drop_outdated_history")
    ResponseEntity<?> dropOutdatedHistory(@PathVariable("language") Language language, @PathVariable("id") @NotNull String entryId) {
        try {
            return ResponseEntity.ok(editorService.dropOutdatedHistory(entryId));
        } catch (Exception e) {
            logger.error("Error while dropping outdated history", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @GetMapping("/search")
    ResponseEntity<?> searchEntryVersions(@PathVariable("language") Language language, @Validated SearchCriteria searchCriteria, Pagination pagination) {
        try {
            return ResponseEntity.ok(editorService.search(searchCriteria, pagination));
        } catch (Exception e) {
            logger.error("Error while searching lemma versions", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @PostMapping("/update_order")
    ResponseEntity<?> reorderEntries(@PathVariable("language") Language language, DictionaryLanguage dictionaryLanguage, @Validated @RequestBody EntryVersionList EntryVersionList) {
        try {
            return ResponseEntity.ok(editorService.updateOrder(dictionaryLanguage, EntryVersionList.entries));
        } catch (Exception e) {
            logger.error("Error while reordering lex entries", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @GetMapping("/get_order")
    ResponseEntity<?> getEntriesOrder(@PathVariable("language") Language language, String lemma, DictionaryLanguage dictionaryLanguage) {
        try {
            return ResponseEntity.ok(editorService.getOrder(lemma, dictionaryLanguage));
        } catch (Exception e) {
            logger.error("Error while fetching lex entries order", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @PostMapping("/search_export")
    void exportBySearchQuery(@PathVariable("language") Language language, @Validated SearchCriteria query, @RequestBody FieldsList fields, HttpServletResponse response) {
        try {
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            String fileName = editorService.export(fields.fields, query);
            File export = new File(pgEnvironment.getTempExportLocation() + fileName);
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=" + export.getName());
            stream(response, export);
        } catch (Exception e) {
            logger.error("Error while exporting lex entries by search query", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @PostMapping("/entries_export")
    void exportByEditorQuery(@PathVariable("language") Language language, @Validated EditorQuery query, @RequestBody FieldsList fields, HttpServletResponse response) {
        try {
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            String fileName = editorService.export(fields.fields, query);
            File export = new File(pgEnvironment.getTempExportLocation() + fileName);
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=" + export.getName());
            stream(response, export);
        } catch (Exception e) {
            logger.error("Error while exporting lex entries by editor query", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @GetMapping("/search_suggestions_choice")
    public ResponseEntity<?> getSearchSuggestionsChoice(@PathVariable("language") Language language) {
        try {
            return ResponseEntity.ok(editorService.getSuggestionsForFields(language));
        } catch (Exception e) {
            logger.error("Error while fetching search suggestions choice", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @GetMapping("/search_suggestions")
    public ResponseEntity<?> getSearchSuggestions(@PathVariable("language") Language language, String field, String searchTerm) {
        if (searchTerm == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no value sent");
        }
        try {
            return ResponseEntity.ok(luceneService.getSuggestionsForField(field, searchTerm, 10));
        } catch (Exception e) {
            logger.error("Error while fetching search suggestions", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @GetMapping("/reference_inflection")
    public ResponseEntity<?> getReferenceInflection(@PathVariable("language") Language language, String searchTerm) {
        if (language != Language.SURSILVAN) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }

        if (searchTerm.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "no value sent");
        }

        return ResponseEntity.ok(verbService.getVerb(searchTerm));
    }

    private void stream(HttpServletResponse response, File export) throws IOException {
        InputStream is = new FileInputStream(export);
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
    }
}
