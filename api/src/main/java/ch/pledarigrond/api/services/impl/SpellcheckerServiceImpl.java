package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.NameService;
import ch.pledarigrond.api.services.SpellcheckerService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.names.entities.Name;
import ch.pledarigrond.spellchecker.generator.SpellcheckerGenerator;
import ch.pledarigrond.spellchecker.generator.SurmiranSpellcheckerGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class SpellcheckerServiceImpl implements SpellcheckerService {

    Logger logger = LoggerFactory.getLogger(SpellcheckerServiceImpl.class);

    @Autowired
    private PgEnvironment pgEnvironment;

    @Autowired
    private NameService nameService;

    SpellcheckerGenerator generator;
    SurmiranSpellcheckerGenerator surmiranGenerator;

    public File exportWordList(Language language) throws NoDatabaseAvailableException, IOException {
        List<Name> names = nameService.getAllNames(new Pagination(100000, 0)).stream().toList();
        return getGeneratorForLanguage(language, names).exportWordList(language);
    }

    public File exportHunspell(Language language) throws NoDatabaseAvailableException, IOException {
        List<Name> names = nameService.getAllNames(new Pagination(100000, 0)).stream().toList();
        return getGeneratorForLanguage(language, names).exportHunspell(language);
    }

    private SpellcheckerGenerator getGeneratorForLanguage(Language language, List<Name> names) {
        switch (language) {
            case SURMIRAN:
                if (surmiranGenerator == null) {
                    surmiranGenerator = new SurmiranSpellcheckerGenerator(pgEnvironment, names);
                }
                return surmiranGenerator;

            default:
                if (generator == null) {
                    generator = new SpellcheckerGenerator(pgEnvironment, names);
                }
                return generator;
        }
    }
}
