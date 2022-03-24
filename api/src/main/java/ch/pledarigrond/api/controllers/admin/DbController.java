package ch.pledarigrond.api.controllers.admin;

import ch.pledarigrond.api.services.AdminService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.mongodb.exceptions.InvalidEntryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("admin/db")
public class DbController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/import_db")
    void importDemoDb() throws IndexException, InvalidEntryException, NoDatabaseAvailableException, IOException {
        adminService.importDemoDatabase();
    }

    @GetMapping("/backup_infos")
    ResponseEntity<?> getBackupInfos() {
        // TODO: Make generic
        return ResponseEntity.ok(adminService.getBackupInfos(Language.SURMIRAN));
    }
}
