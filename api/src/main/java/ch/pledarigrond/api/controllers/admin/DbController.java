package ch.pledarigrond.api.controllers.admin;

import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.database.services.DbBackupService;
import ch.pledarigrond.database.services.DictionaryService;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("{language}/admin/db")
public class DbController {

    private final Logger logger = LoggerFactory.getLogger(DbController.class);

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private LuceneService luceneService;

    @Autowired
    private DbBackupService dbBackupService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/drop_db")
    void dropDb(@PathVariable("language")Language language) {
        logger.info("Dropping database for language: {}", language);
        dictionaryService.deleteAllEntries();
        logger.info("Dropped database for language: {}", language);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/import_db")
    void importDb(@PathVariable("language")Language language, HttpServletRequest request) {
        try {
            StandardMultipartHttpServletRequest dmhsRequest = (StandardMultipartHttpServletRequest) request;
            MultipartFile multipartFile = (MultipartFile) dmhsRequest.getFile("file");
            InputStream in = multipartFile.getInputStream();
            logger.info("Importing from backup file... {}", multipartFile.getName());
            dbBackupService.restoreLanguage(language, in);
        } catch (IOException e) {
            logger.error("Error while importing database", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value="/export_db", produces="application/zip")
    void exportDb(
            @PathVariable("language")Language language,
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setContentType("application/zip");
        StringBuilder fileName = new StringBuilder();
        fileName
                .append("pledarigrond_db_dump_")
                .append(language.getName())
                .append("_");
        String pattern = "yyyy-MM-dd_HH-mm-ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        fileName.append(simpleDateFormat.format(new Date()));
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".zip");
        ServletOutputStream out;
        try {
            out = response.getOutputStream();
            dbBackupService.backupLanguage(language, out, fileName.toString());
        } catch (IOException e) {
            logger.error("Error while exporting database. Error writing.", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error writing.");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/db_stats")
    ResponseEntity<?> getDbStats(@PathVariable("language")Language language) {
        return ResponseEntity.ok(dictionaryService.getStatistics());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/backup_infos")
    ResponseEntity<?> getBackupInfos(@PathVariable("language")Language language) {
        return ResponseEntity.ok(dbBackupService.getBackupInfos(language));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/download_backup/{file_name}")
    void downloadBackupFile(@PathVariable("language")Language language, @PathVariable("file_name")String fileName, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        File export = dbBackupService.getBackupFile(language, fileName);
        response.setContentType("application/json");
        response.setHeader("Content-Disposition", "attachment; filename=" + export.getName());
        stream(response, export);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/index_stats")
    ResponseEntity<?> getIndexStats(@PathVariable("language")Language language) {
        return ResponseEntity.ok(luceneService.getIndexStatistics());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/rebuild_index")
    ResponseEntity<?> rebuildIndex(@PathVariable("language")Language language) {
        try {
            logger.info("Rebuilding index...");
            luceneService.dropIndex();
            luceneService.addToIndex(dictionaryService.getStreamForEntries());
            logger.info("Index has been created");
            return ResponseEntity.ok().build();
        } catch (NoIndexAvailableException e) {
            logger.error("Error while rebuilding index. Index not found.", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Index not found.");
        } catch (IndexException e) {
            logger.error("Error while rebuilding index. Index error.", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Index error.");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/rebuild_suggestions_index")
    ResponseEntity<?> rebuildSuggestionsIndex(@PathVariable("language")Language language) {
        try {
            logger.info("Rebuilding suggestions index...");
            luceneService.regenerateSuggestionIndex(language);
            logger.info("Suggestions index has been rebuild");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error while rebuilding suggestion index. Index error.", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private void stream(HttpServletResponse response, File export) throws IOException {
        InputStream is = new FileInputStream(export);
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
    }
}
