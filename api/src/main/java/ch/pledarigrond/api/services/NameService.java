package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.names.entities.Category;
import ch.pledarigrond.names.entities.Name;
import org.springframework.data.domain.Page;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface NameService {
    Page<Name> getAllNames(Pagination pagination, String nameFilter, Category categoryFilter);

    Name insert(Name name);

    Optional<Name> getById(String id);

    Name updateName(Name name);

    boolean deleteName(Name name);

    File exportAllNames();

    boolean importAllNames(InputStream inputStream);

    List<String> getWordsForLanguage(Language language);
}
