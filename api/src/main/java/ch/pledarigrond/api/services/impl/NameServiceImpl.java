package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.NameService;
import ch.pledarigrond.names.entities.Name;
import ch.pledarigrond.names.repositories.NameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NameServiceImpl implements NameService {

    @Autowired
    private NameRepository nameRepository;

    @Override
    public Page<Name> getAllNames() {
        Pageable pageable = PageRequest.of(0, 20);
        return nameRepository.findAll(pageable);
    }
}
