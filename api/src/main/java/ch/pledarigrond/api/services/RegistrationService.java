package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.pronunciation.dto.ListFilter;
import ch.pledarigrond.pronunciation.dto.RegistrationStatistics;
import ch.pledarigrond.pronunciation.entities.Registration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface RegistrationService {
    Page<Registration> getRegistrations(ListFilter filter, Pageable pageable);

    Registration getRegistration(String id);

    Registration getNextRegistration();

    Registration postponeRegistration(Registration registration);

    Registration acceptRegistration(Registration registration);

    Registration rejectRegistration(Registration registration);

    Registration postponeReviewRegistration(Registration registration);

    Registration addRegistrationToLemma(Registration registration, String entryId) throws IOException;

    Registration getOrderedRegistration(String entryId);

    Registration order(Registration registration);

    void deleteRegistration(String id);

    Registration uploadRegistration(Registration registration, MultipartFile wavFile) throws IOException;

    void extractSingleWords(Stream<EntryDto> stream);

    ByteArrayResource extractListOfWordsByEnding(Stream<EntryDto> stream);

    RegistrationStatistics getStatistics();
}
