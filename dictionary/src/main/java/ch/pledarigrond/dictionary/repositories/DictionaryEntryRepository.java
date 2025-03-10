package ch.pledarigrond.dictionary.repositories;

import ch.pledarigrond.dictionary.entities.DictionaryEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryEntryRepository extends MongoRepository<DictionaryEntry, String> {
}
