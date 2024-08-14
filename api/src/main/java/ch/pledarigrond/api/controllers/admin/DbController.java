package ch.pledarigrond.api.controllers.admin;

import ch.pledarigrond.api.services.AdminService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("{language}/admin/db")
public class DbController {

    private final Logger logger = LoggerFactory.getLogger(DbController.class);

    @Autowired
    private AdminService adminService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/drop_db")
    void dropDb(@PathVariable("language")Language language) {
        try {
            adminService.dropDatabase(language);
        } catch (DatabaseException e) {
            logger.error("Error while dropping database", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/import_db")
    void importDb(@PathVariable("language")Language language, HttpServletRequest request) {
        try {
            adminService.importDatabase(language, request);
        } catch (DatabaseException | IOException e) {
            logger.error("Error while importing database", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (XMLStreamException | JAXBException e) {
            logger.error("Error while importing database, malformed data", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value="/export_db", produces="application/zip")
    void exportDb(
            @PathVariable("language")Language language,
            @RequestParam(value = "includeVersionHistory", defaultValue = "true") boolean includeVersionHistory,
            @RequestParam(value = "anonymizeData", defaultValue = "false") boolean anonymizeData,
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setContentType("application/zip");
        StringBuilder fileName = new StringBuilder();
        fileName
                .append("pledarigrond_db_dump_")
                .append(language.getName())
                .append("_");
        if(includeVersionHistory) {
            fileName.append("including_version_history_");
        } else {
            fileName.append("only_current_");
        }
        if(anonymizeData) {
            fileName.append("anonymized_");
        }
        String pattern = "yyyy-MM-dd_HH-mm-ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        fileName.append(simpleDateFormat.format(new Date()));
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".zip");
        ServletOutputStream out;
        try {
            out = response.getOutputStream();
            adminService.exportData(language, includeVersionHistory, anonymizeData, out, fileName.toString());
        } catch (IOException e) {
            logger.error("Error while exporting database. Error writing.", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error writing.");
        } catch (JAXBException e) {
            logger.error("Error while exporting database. Jaxb exception.", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Jaxb exception.");
        } catch (NoDatabaseAvailableException e) {
            logger.error("Error while exporting database. No database available.", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database not found.");
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error while exporting database. No such algorithm.", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No such algorithm.");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/db_stats")
    ResponseEntity<?> getDbStats(@PathVariable("language")Language language) {
        try {
            return ResponseEntity.ok(adminService.getDatabaseStats(language));
        } catch (NoDatabaseAvailableException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database not found.");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/backup_infos")
    ResponseEntity<?> getBackupInfos(@PathVariable("language")Language language) {
        return ResponseEntity.ok(adminService.getBackupInfos(language));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/download_backup/{file_name}")
    void downloadBackupFile(@PathVariable("language")Language language, @PathVariable("file_name")String fileName, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        File export = adminService.getBackupFile(language, fileName);
        response.setContentType("application/json");
        response.setHeader("Content-Disposition", "attachment; filename=" + export.getName());
        stream(response, export);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/index_stats")
    ResponseEntity<?> getIndexStats(@PathVariable("language")Language language) {
        try {
            return ResponseEntity.ok(adminService.getIndexStats(language));
        } catch (NoIndexAvailableException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Index not found.");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/rebuild_index")
    ResponseEntity<?> rebuildIndex(@PathVariable("language")Language language) {
        try {
            adminService.rebuildIndex(language);
            return ResponseEntity.ok().build();
        } catch (NoIndexAvailableException e) {
            logger.error("Error while rebuilding index. Index not found.", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Index not found.");
        } catch (IndexException e) {
            logger.error("Error while rebuilding index. Index error.", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Index error.");
        } catch (NoDatabaseAvailableException e) {
            logger.error("Error while rebuilding index. Database not found.", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database not found.");
        }
    }

    private void stream(HttpServletResponse response, File export) throws IOException {
        InputStream is = new FileInputStream(export);
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
    }
}
