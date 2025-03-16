package ch.pledarigrond.database.dictionary.repositories;

import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.database.dictionary.entities.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface EntryRepository extends MongoRepository<Entry, String> {
    Entry getFirstByEntryId(String entryId);
    Optional<Entry> findByEntryId(String entryId);

    Stream<EntryDto> findAllBy();
}
