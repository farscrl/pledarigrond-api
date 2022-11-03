package ch.pledarigrond.names.repositories;

import ch.pledarigrond.names.entities.Name;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NameRepository extends MongoRepository<Name, String> {
    @Override
    Page<Name> findAll(Pageable pageable);

    @Query("{'$or':[ {'nameRumantschGrischun':{$regex:?0,$options:'i'}}, {'nameGerman':{$regex:?0,$options:'i'}}, {'nameSursilvan':{$regex:?0,$options:'i'}}, {'nameSutsilvan':{$regex:?0,$options:'i'}}, {'nameSurmiran':{$regex:?0,$options:'i'}}, {'namePuter':{$regex:?0,$options:'i'}}, {'nameVallader':{$regex:?0,$options:'i'}}], 'category': {$regex: ?1, $options:'i'}}")
    Page<Name> getByNameAndCategory(Pageable pageable, String name, String category);
}
