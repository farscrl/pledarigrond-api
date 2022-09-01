package ch.pledarigrond.names.util;

import ch.pledarigrond.names.entities.Name;
import ch.pledarigrond.names.repositories.NameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class XlsxHandler {

    private static final Logger LOG = LoggerFactory.getLogger(XlsxHandler.class);

    @Autowired
    private NameRepository nameRepository;

    public File exportNames() {
        List<Name> names = nameRepository.findAll();

        try {
            XlsxExport export = new XlsxExport(names);
            return export.export();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean importNames(InputStream inputStream) {
        try {
            XlsxImport importer = new XlsxImport(inputStream);
            List<Name> names = importer.importData();
            nameRepository.saveAll(names);
        } catch (IOException e) {
            return false;
        }

        return true;
    }
}
