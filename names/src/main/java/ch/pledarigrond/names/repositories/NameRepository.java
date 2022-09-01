package ch.pledarigrond.names.repositories;

import ch.pledarigrond.names.entities.Name;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameRepository extends MongoRepository<Name, String> {
    @Override
    Page<Name> findAll(Pageable pageable);
}
