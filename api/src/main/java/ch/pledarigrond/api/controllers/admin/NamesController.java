package ch.pledarigrond.api.controllers.admin;

import ch.pledarigrond.api.services.NameService;
import ch.pledarigrond.names.entities.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/names")
public class NamesController {
    @Autowired
    private NameService nameService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("")
    ResponseEntity<?> list() {
        Pageable pageable = PageRequest.of(0, 10000);
        Page<Name> names = nameService.getAllNames();
        return ResponseEntity.ok(names);
    }
}
