package ch.pledarigrond.database.user.repositories;

import ch.pledarigrond.database.user.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserDal {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
