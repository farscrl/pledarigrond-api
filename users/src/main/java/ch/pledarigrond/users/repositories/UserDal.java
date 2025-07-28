package ch.pledarigrond.users.repositories;

import ch.pledarigrond.users.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserDal {
    Page<User> getAllUsers(String text, Pageable pageable);
}
