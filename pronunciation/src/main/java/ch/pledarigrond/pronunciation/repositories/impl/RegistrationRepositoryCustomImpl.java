package ch.pledarigrond.pronunciation.repositories.impl;

import ch.pledarigrond.pronunciation.dto.ListFilter;
import ch.pledarigrond.pronunciation.entities.Registration;
import ch.pledarigrond.pronunciation.repositories.RegistrationRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class RegistrationRepositoryCustomImpl implements RegistrationRepositoryCustom {
    @Autowired
    @Qualifier("registrationsMongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public Page<Registration> findByFilter(ListFilter filter, Pageable pageable) {
        Query query = new Query();

        if (filter.getStatus() != null) {
            query.addCriteria(Criteria.where("status").is(filter.getStatus()));
        }
        
        if(!filter.getStatuslist().isEmpty()) {
            query.addCriteria(Criteria.where("status").in(filter.getStatuslist()));
        }

        if (filter.getSearchTerm() != null && !filter.getSearchTerm().isEmpty()) {
            String regexPattern = "^" + filter.getSearchTerm().toLowerCase();
            Criteria searchCriteria = new Criteria().orOperator(
                    Criteria.where("rmStichwortNormalized").regex(regexPattern),
                    Criteria.where("deStichwortNormalized").regex(regexPattern)
            );
            query.addCriteria(searchCriteria);
        }

        Sort.Direction direction = filter.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
        query.with(Sort.by(direction, "lastModifiedDate"));

        long total = mongoTemplate.count(query, Registration.class);

        query.with(pageable);

        List<Registration> registrations = mongoTemplate.find(query, Registration.class);

        return new PageImpl<>(registrations, pageable, total);
    }
}
