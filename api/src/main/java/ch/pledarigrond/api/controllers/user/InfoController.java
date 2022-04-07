package ch.pledarigrond.api.controllers.user;

import ch.pledarigrond.api.services.AdminService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("{language}/user/info")
public class InfoController {

    @Autowired
    private AdminService adminService;

    @GetMapping("entries")
    public ResponseEntity<?> getNumberOfEntries(@PathVariable("language") Language language) {
        try {
            return ResponseEntity.ok(adminService.getDatabaseStats(language).getNumberOfEntries());
        } catch (NoDatabaseAvailableException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No DB available.");
        }
    }
}
