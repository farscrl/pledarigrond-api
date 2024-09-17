package ch.pledarigrond.api.controllers.admin;

import ch.pledarigrond.api.services.ExportService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.exception.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("{language}/admin/export")
public class ExportController {

    private final Logger logger = LoggerFactory.getLogger(ExportController.class);

    @Autowired
    private ExportService exportService;

    /**
     * Export an Excel table that contains words with the "VERB" conjugation and includes a space character (" "),
     * e.g., «dvanter culpabel, culpabla» or «pretender memma bger».
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/export_composed_verbs")
    ResponseEntity<?> exportComposedVerbs(@PathVariable("language") Language language, HttpServletRequest request) {
        if (language != Language.PUTER && language != Language.VALLADER) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }

        try {
            String filename = language.getName() + "_verbs-che-cuntegnan-spazi.xlsx";
            ByteArrayInputStream in = exportService.ladinComposedVerbs(language, request);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=" + filename);
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(new InputStreamResource(in));
        } catch (DatabaseException | IOException e) {
            logger.error("Error while importing excel file", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Export an Excel table that contains verbs with 1, 2, or 3 consonants before the ending "-er."
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/export_consonants_on_er")
    ResponseEntity<?> exportConsonantsOnEr(@PathVariable("language") Language language, HttpServletRequest request) {
        if (language != Language.PUTER && language != Language.VALLADER) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }

        try {
            String filename = language.getName() + "_verbs-er-cun-1-2-3-consonants.xlsx";
            ByteArrayInputStream in = exportService.ladinConsonantsOnErrors(language, request);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=" + filename);
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(new InputStreamResource(in));
        } catch (DatabaseException | IOException e) {
            logger.error("Error while importing excel file", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Export an Excel table with all entries that contain a comma in the Romansh lemma and a slash ("/") in the
     * gender field.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/export_entries_with_comma_and_slash")
    ResponseEntity<?> exportEntriesWithCommaAndSlash(@PathVariable("language") Language language, HttpServletRequest request) {
        if (language != Language.PUTER && language != Language.VALLADER) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }

        try {
            String filename = language.getName() + "_comma-e-slash.xlsx";
            ByteArrayInputStream in = exportService.ladinEntriesWithCommaAndSlash(language, request);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=" + filename);
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(new InputStreamResource(in));
        } catch (DatabaseException | IOException e) {
            logger.error("Error while importing excel file", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
