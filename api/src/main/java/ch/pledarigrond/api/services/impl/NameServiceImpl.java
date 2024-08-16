package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.NameService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.names.entities.Category;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NameServiceImpl implements NameService {

    @Autowired
    private NameRepository nameRepository;

    @Autowired
    private XlsxHandler xlsxHandler;

    @Override
    public Page<Name> getAllNames(Pagination pagination, String nameFilter, Category categoryFilter) {
        Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getPageSize());

        String nameDefault = "(.*?)";
        String categoryDefault = "(.*?)";

        if (nameFilter != null) {
            nameDefault = nameFilter;
        }

        if (categoryFilter != null) {
            categoryDefault = categoryFilter.name();
        }

        return nameRepository.getByNameAndCategory(pageable, nameDefault, categoryDefault);
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
        nameRepository.deleteAll();
        return xlsxHandler.importNames(inputStream);
    }

    @Override
    public List<String> getWordsForLanguage(Language language) {
        List<Name> names = nameRepository.findAll();
        List<String> list = new ArrayList<>();
        for (Name name : names) {
            extractName(list, language, name);
        }
        return list;
    }

    private void extractName(List<String> list, Language language, Name name) {
        String rm = getRomanshNameForLanguage(language, name);
        if (rm != null) {
            list.add(rm);
        }

        String de = getGermanNameForLanguage(language, name);
        if (de != null) {
            list.add(de);
        }
    }

    private static String getRomanshNameForLanguage(Language language, Name name) {
        String languageLemma = getLanguageLemma(language, name);
        if (languageLemma != null && !languageLemma.isEmpty()) {
            return languageLemma;
        } else {
            return name.getNameRumantschGrischun();
        }

    }

    public static String getGermanNameForLanguage(Language language, Name name) {
        if (name.getNameGerman() != null && !name.getNameGerman().isEmpty()) {
            return name.getNameGerman();
        }

        return null;
    }

    private static String getLanguageLemma(Language language, Name name) {
        return switch (language) {
            case SURSILVAN -> name.getNameSursilvan();
            case SUTSILVAN -> name.getNameSutsilvan();
            case SURMIRAN -> name.getNameSurmiran();
            case PUTER -> name.getNamePuter();
            case VALLADER -> name.getNameVallader();
            case RUMANTSCHGRISCHUN -> name.getNameRumantschGrischun();
        };
    }
}
