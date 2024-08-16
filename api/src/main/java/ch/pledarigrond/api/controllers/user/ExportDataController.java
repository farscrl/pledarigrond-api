package ch.pledarigrond.api.controllers.user;

import ch.pledarigrond.api.services.NameService;
import ch.pledarigrond.api.services.ScheduledDumpService;
import ch.pledarigrond.common.data.common.Language;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("{language}/user/export")
public class ExportDataController {

    @Autowired
    ScheduledDumpService scheduledDumpService;

    @Autowired
    private NameService nameService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/json")
    void exportJson(@PathVariable("language") Language language, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // export for Puter/Vallader only possible for admins
        if ((language == Language.PUTER || language == Language.VALLADER) && !request.isUserInRole("ROLE_ADMIN")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
            return;
        }

        File export = scheduledDumpService.getJsonExportFile(language);
        response.setContentType("application/json");
        response.setHeader("Content-Disposition", "attachment; filename=" + export.getName());
        stream(response, export);
    }

    @GetMapping("/names")
    ResponseEntity<?> exportForLanguageAction(@PathVariable Language language) {
        List<String> names = nameService.getWordsForLanguage(language);
        return ResponseEntity.ok(names);
    }

    private void stream(HttpServletResponse response, File export) throws IOException {
        InputStream is = new FileInputStream(export);
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
    }
}
