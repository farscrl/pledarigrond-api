package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.RegistrationService;
import ch.pledarigrond.pronunciation.entities.Registration;
import ch.pledarigrond.pronunciation.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Override
    public Page<Registration> getRegistrations(int page, int size) {
        return registrationRepository.findAll(PageRequest.of(page, size));
    }
}
