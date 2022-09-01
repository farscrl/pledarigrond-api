package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.names.entities.Name;
import org.springframework.data.domain.Page;

import java.io.File;
import java.io.InputStream;

public interface NameService {
    Page<Name> getAllNames(Pagination pagination);

    File exportAllNames();

    boolean importAllNames(InputStream inputStream);
}
