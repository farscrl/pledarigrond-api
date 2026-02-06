package ch.pledarigrond.api.controllers.user;

import ch.pledarigrond.api.dtos.PronunciationDownloadRequest;
import ch.pledarigrond.api.services.BunnyService;
import ch.pledarigrond.api.services.EmailService;
import ch.pledarigrond.api.services.NameService;
import ch.pledarigrond.api.services.ScheduledDumpService;
import ch.pledarigrond.common.data.common.Language;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private BunnyService bunnyService;

    @Autowired
    private EmailService emailService;

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
    ResponseEntity<?> exportForLanguageAction(@PathVariable("language") Language language) {
        List<String> names = nameService.getWordsForLanguage(language);
        return ResponseEntity.ok(names);
    }

    @PostMapping("/pronunciation/request-link")
    public ResponseEntity<String> requestDownload(@PathVariable("language") Language language, @RequestBody PronunciationDownloadRequest request) {

        if (!request.isAcceptedTerms()) {
            return ResponseEntity.badRequest().body("You must accept the terms and conditions.");
        }
        if (request.getEmail() == null || !request.getEmail().contains("@")) {
            return ResponseEntity.badRequest().body("Invalid email address.");
        }

        String fileName;
        switch (language) {
            case PUTER:
                fileName = "ladin-rm-puter-mp3.zip";
                break;
            case RUMANTSCHGRISCHUN:
                fileName = "prod-rm-rumgr-mp3.zip";
                break;
            case SURMIRAN:
                fileName = "prod-rm-surmiran-mp3.zip";
                break;
            case SURSILVAN:
                fileName = "prod-rm-sursilv-mp3.zip";
                break;
            case SUTSILVAN:
                fileName = "prod-rm-sutsilv-mp3.zip";
                break;
            case VALLADER:
                fileName = "ladin-rm-vallader-mp3.zip";
                break;
            default:
                return ResponseEntity.badRequest().body("Invalid language.");
        }

        fileName = "pronunzia/" + fileName;

        String secureLink = bunnyService.generateSecureUrl(fileName, 3600);
        emailService.sendPronunciationDownloadLink(request.getEmail(), secureLink, language);
        return ResponseEntity.ok().build();
    }

    private void stream(HttpServletResponse response, File export) throws IOException {
        InputStream is = new FileInputStream(export);
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
    }
}
