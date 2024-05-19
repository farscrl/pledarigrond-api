package ch.pledarigrond.api.services;

import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.pronunciation.dto.ListFilter;
import ch.pledarigrond.pronunciation.dto.RegistrationStatistics;
import ch.pledarigrond.pronunciation.entities.Registration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.UnknownHostException;

public interface RegistrationService {
    public Page<Registration> getRegistrations(ListFilter filter, Pageable pageable);

    public Registration getNextRegistration();

    public Registration postponeRegistration(Registration registration);

    public Registration acceptRegistration(Registration registration);

    public Registration rejectRegistration(Registration registration);

    public Registration postponeReviewRegistration(Registration registration);

    public Registration uploadRegistration(Registration registration, MultipartFile wavFile) throws IOException;

    public boolean extractSingleWords() throws DatabaseException, UnknownHostException;

    public ByteArrayResource extractListOfWordsByEnding() throws DatabaseException, UnknownHostException;

    public RegistrationStatistics getStatistics();
}
