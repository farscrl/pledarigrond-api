package ch.pledarigrond.pronunciation.repositories;

import ch.pledarigrond.pronunciation.entities.Registration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegistrationRepository extends MongoRepository<Registration, String> {
    boolean existsByRStichwortAndRGenusAndRGrammatik(String RStichwort, String RGenus, String RGrammatik);
}
