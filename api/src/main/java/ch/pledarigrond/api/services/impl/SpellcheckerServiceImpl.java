package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.SpellcheckerService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.spellchecker.generator.SpellcheckerGenerator;
import ch.pledarigrond.spellchecker.generator.SurmiranSpellcheckerGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class SpellcheckerServiceImpl implements SpellcheckerService {

    Logger logger = LoggerFactory.getLogger(SpellcheckerServiceImpl.class);

    @Autowired
    private PgEnvironment pgEnvironment;

    SpellcheckerGenerator generator;
    SurmiranSpellcheckerGenerator surmiranGenerator;

    public File exportWordList(Language language) throws NoDatabaseAvailableException, IOException {
        return getGeneratorForLanguage(language).exportWordList(language);
    }

    public File exportHunspell(Language language) throws NoDatabaseAvailableException, IOException {
        return getGeneratorForLanguage(language).exportHunspell(language);
    }

    private SpellcheckerGenerator getGeneratorForLanguage(Language language) {
        switch (language) {
            case SURMIRAN:
                if (surmiranGenerator == null) {
                    surmiranGenerator = new SurmiranSpellcheckerGenerator(pgEnvironment);
                }
                return surmiranGenerator;

            default:
                if (generator == null) {
                    generator = new SpellcheckerGenerator(pgEnvironment);
                }
                return generator;
        }
    }
}
