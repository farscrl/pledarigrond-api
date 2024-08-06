package ch.pledarigrond.api.controllers.admin;

import ch.pledarigrond.api.services.SpellcheckerService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("{language}/admin/spellchecker")
public class SpellcheckerController {

    @Autowired
    private SpellcheckerService spellcheckerService;

    @Autowired
    private PgEnvironment pgEnvironment;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/export_hunspell")
    void exportHunspell(@PathVariable("language")Language language, HttpServletResponse response) {
        try {
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            File export = spellcheckerService.exportHunspell(language);
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=" + export.getName());
            stream(response, export);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/generate_hunspell")
    ResponseEntity<Void> generateHunspell(@PathVariable("language")Language language, HttpServletResponse response) {
        try {
            spellcheckerService.generateAndCommit();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/export_ms_wordlist")
    void exportMsWordlist(@PathVariable("language")Language language, HttpServletResponse response) {
        try {
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            File export = spellcheckerService.exportMsWordlist(language);
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=" + export.getName());
            stream(response, export);
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
