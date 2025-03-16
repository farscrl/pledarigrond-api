package ch.pledarigrond.database.user.repositories.impl;

import ch.pledarigrond.database.user.entities.User;
import ch.pledarigrond.database.user.repositories.UserDal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.regex.Pattern;

@Repository
public class UserDalImpl implements UserDal {
    @Autowired
    @Qualifier("usersMongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public Page<User> getAllUsers(String searchTerm, Pageable pageable) {
        Query query = new Query();

        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            Pattern pattern = Pattern.compile(".*" + searchTerm + ".*", Pattern.CASE_INSENSITIVE);
            Criteria emailCriteria = Criteria.where("email").regex(pattern);
            Criteria firstNameCriteria = Criteria.where("firstName").regex(pattern);
            Criteria lastNameCriteria = Criteria.where("lastName").regex(pattern);
            query.addCriteria(new Criteria().orOperator(emailCriteria, firstNameCriteria, lastNameCriteria));
        }

        query.with(pageable);
        List<User> users = mongoTemplate.find(query, User.class);

        Query countQuery = new Query();
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            Pattern pattern = Pattern.compile(".*" + searchTerm + ".*", Pattern.CASE_INSENSITIVE);
            Criteria emailCriteria = Criteria.where("email").regex(pattern);
            Criteria firstNameCriteria = Criteria.where("firstName").regex(pattern);
            Criteria lastNameCriteria = Criteria.where("lastName").regex(pattern);
            query.addCriteria(new Criteria().orOperator(emailCriteria, firstNameCriteria, lastNameCriteria));
        }
        long total = mongoTemplate.count(countQuery, User.class);

        return new PageImpl<>(users, pageable, total);
    }
}
