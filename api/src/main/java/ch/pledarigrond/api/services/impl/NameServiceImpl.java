package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.NameService;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.names.entities.Name;
import ch.pledarigrond.names.repositories.NameRepository;
import ch.pledarigrond.names.util.XlsxHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.Optional;

@Service
public class NameServiceImpl implements NameService {

    @Autowired
    private NameRepository nameRepository;

    @Autowired
    private XlsxHandler xlsxHandler;

    @Override
    public Page<Name> getAllNames(Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getPageSize());
        return nameRepository.findAll(pageable);
    }


    @Override
    public Name insert(Name name) {
        return nameRepository.insert(name);
    }

    @Override
    public Optional<Name> getById(String id) {
        return nameRepository.findById(id);
    }

    @Override
    public Name updateName(Name name) {
        return nameRepository.save(name);
    }

    @Override
    public boolean deleteName(Name name) {
        nameRepository.delete(name);
        return true;
    }

    @Override
    public File exportAllNames() {
        return xlsxHandler.exportNames();
    }

    @Override
    public boolean importAllNames(InputStream inputStream) {
        return xlsxHandler.importNames(inputStream);
    }
}
