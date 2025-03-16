package ch.pledarigrond.database.user.repositories;

import ch.pledarigrond.database.user.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserDal {
    Page<User> getAllUsers(String text, Pageable pageable);
}
