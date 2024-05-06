package ch.pledarigrond.api.services;

import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.pronunciation.dto.RegistrationStatistics;
import ch.pledarigrond.pronunciation.entities.Registration;
import org.springframework.data.domain.Page;

import java.net.UnknownHostException;

public interface RegistrationService {
    public Page<Registration> getRegistrations(int page, int size);

    public boolean extractSingleWords() throws DatabaseException, UnknownHostException;

    public RegistrationStatistics getStatistics();
}
