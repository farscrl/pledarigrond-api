package ch.pledarigrond.api.controllers.admin;

import ch.pledarigrond.api.services.AdminService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import ch.pledarigrond.mongodb.exceptions.InvalidEntryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("{language}/admin/db")
public class DbController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/import_db")
    void importDemoDb(@PathVariable("language")Language language) throws IndexException, InvalidEntryException, NoDatabaseAvailableException, IOException {
        adminService.importDemoDatabase(language);
    }

    @PostMapping(value="/export_db", produces="application/zip")
    void exportDb(
            @PathVariable("language")Language language,
            @RequestParam(value = "includeVersionHistory", defaultValue = "true") boolean includeVersionHistory,
            @RequestParam(value = "anonymizeData", defaultValue = "false") boolean anonymizeData,
            HttpServletResponse response
    ) {
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
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            adminService.exportData(language, includeVersionHistory, anonymizeData, out, fileName.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error writing.");
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Jaxb exception.");
        } catch (NoDatabaseAvailableException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database not found.");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No such algorithm.");
        }
    }

    @GetMapping("/db_stats")
    ResponseEntity<?> getDbStats(@PathVariable("language")Language language) {
        try {
            return ResponseEntity.ok(adminService.getDatabaseStats(language));
        } catch (NoDatabaseAvailableException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database not found.");
        }
    }

    @GetMapping("/backup_infos")
    ResponseEntity<?> getBackupInfos(@PathVariable("language")Language language) {
        return ResponseEntity.ok(adminService.getBackupInfos(language));
    }

    @GetMapping("/index_stats")
    ResponseEntity<?> getIndexStats(@PathVariable("language")Language language) {
        try {
            return ResponseEntity.ok(adminService.getIndexStats(language));
        } catch (NoIndexAvailableException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Index not found.");
        }
    }

    @PostMapping("/rebuild_index")
    ResponseEntity<?> rebuildIndex(@PathVariable("language")Language language) {
        try {
            adminService.rebuildIndex(language);
            return ResponseEntity.ok().build();
        } catch (NoIndexAvailableException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Index not found.");
        } catch (IndexException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Index error.");
        } catch (NoDatabaseAvailableException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database not avaliable.");
        }
    }
}