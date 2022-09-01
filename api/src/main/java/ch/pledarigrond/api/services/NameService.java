package ch.pledarigrond.api.services;

import ch.pledarigrond.names.entities.Name;
import org.springframework.data.domain.Page;

import java.io.File;
import java.io.InputStream;

public interface NameService {
    Page<Name> getAllNames();

    File exportAllNames();

    boolean importAllNames(InputStream inputStream);
}
