package ch.pledarigrond.api.controllers.admin;

import ch.pledarigrond.api.services.BackupService;
import ch.pledarigrond.common.exception.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("admin/backup")
public class BackupController {

    @Autowired
    private BackupService backupService;

    /**
     * Dump a db.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{db}/dump")
    void dumbDb(@PathVariable("db") String db, HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setContentType("application/zip");
            String pattern = "yyyy-MM-dd_HH-mm-ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String fileName = "pledarigrond_db_dump-" +
                    db +
                    "-" +
                    simpleDateFormat.format(new Date());
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".zip");
            boolean success = backupService.dumbDb(db, response.getOutputStream());
            if (!success) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during import of ladin data");
            }
        } catch (IOException | InterruptedException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * Restore a db.
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{db}/restore")
    ResponseEntity<?> restoreDb(@PathVariable("db") String db, HttpServletRequest request) throws DatabaseException, UnknownHostException {

        try {
            StandardMultipartHttpServletRequest dmhsRequest = (StandardMultipartHttpServletRequest) request;
            MultipartFile multipartFile = (MultipartFile) dmhsRequest.getFile("file");
            assert multipartFile != null;
            InputStream in = multipartFile.getInputStream();

            backupService.restoreDb(db, in);
        } catch (IOException | InterruptedException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return ResponseEntity.ok().build();
    }
}
