package ch.pledarigrond.api.services;

import ch.pledarigrond.names.entities.Name;
import org.springframework.data.domain.Page;

public interface NameService {
    Page<Name> getAllNames();
}
