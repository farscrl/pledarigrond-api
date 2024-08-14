package ch.pledarigrond.api.controllers.user;

import ch.pledarigrond.api.services.AdminService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("{language}/user/info")
public class InfoController {

    private final Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    private AdminService adminService;

    @GetMapping("entries")
    public ResponseEntity<?> getNumberOfEntries(@PathVariable("language") Language language) {
        try {
            return ResponseEntity.ok(adminService.getDatabaseStats(language).getNumberOfEntries());
        } catch (NoDatabaseAvailableException e) {
            logger.error("Error while getting number of entries", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
