package ch.pledarigrond.pronunciation.repositories;

import ch.pledarigrond.pronunciation.dto.ListFilter;
import ch.pledarigrond.pronunciation.entities.Registration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RegistrationRepositoryCustom {
    Page<Registration> findByFilter(ListFilter filter, Pageable pageable);
}
