package ch.pledarigrond.pronunciation.repositories;

import ch.pledarigrond.pronunciation.dto.RegistrationStatus;
import ch.pledarigrond.pronunciation.entities.Registration;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RegistrationRepository extends MongoRepository<Registration, String> {
    Optional<Registration> findFirstByRmStichwortAndRmGenusAndRmGrammatik(String RStichwort, String RGenus, String RGrammatik);

    // TODO: only with status todo
    @Aggregation(pipeline = { "{$sample: {size: 1}}" })
    Optional<Registration> findRandomRegistration();

    int countByStatus(RegistrationStatus status);
}
