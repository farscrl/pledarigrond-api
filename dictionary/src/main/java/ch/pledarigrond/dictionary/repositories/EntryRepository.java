package ch.pledarigrond.dictionary.repositories;

import ch.pledarigrond.dictionary.entities.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends MongoRepository<Entry, String> {
}
