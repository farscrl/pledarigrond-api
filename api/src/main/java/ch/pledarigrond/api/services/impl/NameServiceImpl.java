package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.NameService;
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

@Service
public class NameServiceImpl implements NameService {

    @Autowired
    private NameRepository nameRepository;

    @Autowired
    private XlsxHandler xlsxHandler;

    @Override
    public Page<Name> getAllNames() {
        Pageable pageable = PageRequest.of(0, 20);
        return nameRepository.findAll(pageable);
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
