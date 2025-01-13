package ch.pledarigrond.pronunciation.repositories;

import ch.pledarigrond.pronunciation.dto.RegistrationStatus;
import ch.pledarigrond.pronunciation.entities.Registration;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends MongoRepository<Registration, String>, RegistrationRepositoryCustom {
    Optional<Registration> findFirstByRmStichwortAndRmGenusAndRmGrammatik(String RStichwort, String RGenus, String RGrammatik);

    @Aggregation(pipeline = {
            "{ $match: { status: 'TODO' } }",
            "{ $sample: { size: 1 } }"
    })
    Optional<Registration> findRandomRegistration();

    int countByStatus(RegistrationStatus status);

    List<Registration> findByLemmaIdsContaining(String lemmaId);
}
