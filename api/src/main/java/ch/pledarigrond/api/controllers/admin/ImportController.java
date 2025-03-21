package ch.pledarigrond.api.controllers.admin;

import ch.pledarigrond.api.services.ImportService;
import ch.pledarigrond.common.data.common.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("{language}/admin/import")
public class ImportController {

    private final Logger logger = LoggerFactory.getLogger(ImportController.class);

    @Autowired
    private ImportService importService;

    /**
     * Allows to import Excel files with the LRC data for sursilvan
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/import_excel_sursilvan")
    ResponseEntity<?> importExcelSursilvan(@PathVariable("language") Language language, HttpServletRequest request) {
        if (language != Language.SURSILVAN) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }

        try {
            boolean success = importService.importXlsSursilvan(language, request);
            if (!success) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during XLSX import");
            }

            return ResponseEntity.ok().build();
        } catch (IOException e) {
            logger.error("Error while importing excel file", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Allows to import Zip file with all the LRC data for sursilvan
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/import_zip_sursilvan")
    ResponseEntity<?> importZipSursilvan(@PathVariable("language") Language language, HttpServletRequest request) {
        if (language != Language.SURSILVAN) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }

        try {
            boolean success = importService.importZipSursilvan(language, request);
            if (!success) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during Zip import");
            }

            return ResponseEntity.ok().build();
        } catch (DatabaseException | IOException e) {
            logger.error("Error while importing zip file", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
