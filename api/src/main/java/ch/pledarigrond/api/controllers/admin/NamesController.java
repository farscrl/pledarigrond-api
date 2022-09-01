package ch.pledarigrond.api.controllers.admin;

import ch.pledarigrond.api.services.NameService;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.names.entities.Name;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("admin/names")
public class NamesController {
    @Autowired
    private NameService nameService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("")
    ResponseEntity<?> list(Pagination pagination) {
        Page<Name> names = nameService.getAllNames(pagination);
        return ResponseEntity.ok(names);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/export")
    void exportAction(HttpServletResponse response) throws IOException {
        File xls = nameService.exportAllNames();

        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + xls.getName());
        stream(response, xls);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/import")
    void importAction(HttpServletRequest request) throws IOException {
        StandardMultipartHttpServletRequest dmhsRequest = (StandardMultipartHttpServletRequest) request;
        MultipartFile multipartFile = (MultipartFile) dmhsRequest.getFile("file");
        InputStream in = multipartFile.getInputStream();

        nameService.importAllNames(in);
    }

    private void stream(HttpServletResponse response, File export) throws IOException {
        InputStream is = new FileInputStream(export);
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
    }
}
