package ch.pledarigrond.corpus.repositories;

import ch.pledarigrond.corpus.entities.CorpusText;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorpusTextRepository extends MongoRepository<CorpusText, String> {

    @Query("{ 'lang': ?0, 'text': { $regex: ?1, $options: 'i' } }")
    List<CorpusText> findByLangAndTextContaining(String lang, String text, Pageable pageable);
}
