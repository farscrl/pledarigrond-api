package ch.pledarigrond.api.services;

import ch.pledarigrond.pronunciation.entities.Registration;
import org.springframework.data.domain.Page;

public interface RegistrationService {
    public Page<Registration> getRegistrations(int page, int size);
}
