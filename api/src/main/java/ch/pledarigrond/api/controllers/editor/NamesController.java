package ch.pledarigrond.api.controllers.editor;

import ch.pledarigrond.api.services.NameService;
import ch.pledarigrond.common.data.common.ApiError;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.names.entities.Category;
import ch.pledarigrond.names.entities.Name;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("editor/names")
public class NamesController {

    @Autowired
    private NameService nameService;

    @PreAuthorize("hasPermission('names', 'editor')")
    @GetMapping("")
    ResponseEntity<?> list(@Valid @ModelAttribute Pagination pagination, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "category", required = false) Category category) {
        Page<Name> names = nameService.getAllNames(pagination, name, category);
        return ResponseEntity.ok(names);
    }

    @PreAuthorize("hasPermission('names', 'editor')")
    @PostMapping()
    ResponseEntity<?> create(@Validated @RequestBody Name name) {
        return ResponseEntity.ok(nameService.insert(name));
    }

    @PreAuthorize("hasPermission('names', 'editor')")
    @GetMapping("/{id}")
    ResponseEntity<?> getOne(@PathVariable("id")String id) {
        Optional<Name> name = nameService.getById(id);
        if (name.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(name.get());
    }

    @PreAuthorize("hasPermission('names', 'editor')")
    @PutMapping("/{id}")
    ResponseEntity<?> update(@PathVariable("id")String id, @Validated @RequestBody Name name) {
        if (!id.equals(name.getId())) {
            ApiError error = new ApiError(
                    HttpStatus.BAD_REQUEST.value(),
                    "IDs are not equal.",
                    List.of("IDs are not equal.")
            );
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(nameService.updateName(name));
    }

    @PreAuthorize("hasPermission('names', 'editor')")
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable("id")String id) {
        Optional<Name> name = nameService.getById(id);
        if (name.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (nameService.deleteName(name.get())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
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
    @PostMapping(path = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void importAction(@RequestParam("file") MultipartFile file) throws IOException {
        try (InputStream in = file.getInputStream()) {
            nameService.importAllNames(in);
        }
    }

    private void stream(HttpServletResponse response, File export) throws IOException {
        InputStream is = new FileInputStream(export);
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
    }
}
