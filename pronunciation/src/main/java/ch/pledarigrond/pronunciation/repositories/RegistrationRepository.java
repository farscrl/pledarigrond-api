package ch.pledarigrond.pronunciation.repositories;

import ch.pledarigrond.pronunciation.entities.Registration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegistrationRepository extends MongoRepository<Registration, String> {
    @Override
    <S extends Registration> Page<S> findAll(Example<S> example, Pageable pageable);
}
