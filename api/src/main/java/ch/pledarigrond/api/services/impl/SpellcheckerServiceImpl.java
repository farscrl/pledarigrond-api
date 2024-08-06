package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.NameService;
import ch.pledarigrond.api.services.SpellcheckerService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.names.entities.Name;
import ch.pledarigrond.spellchecker.generator.hunspell.*;
import ch.pledarigrond.spellchecker.generator.pos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class SpellcheckerServiceImpl implements SpellcheckerService {

    Logger logger = LoggerFactory.getLogger(SpellcheckerServiceImpl.class);

    @Autowired
    private PgEnvironment pgEnvironment;

    @Autowired
    private NameService nameService;

    public File exportHunspell(Language language) throws NoDatabaseAvailableException, IOException {
        List<Name> names = nameService.getAllNames(new Pagination(100000, 0), null, null).stream().toList();
        return Objects.requireNonNull(getGeneratorForLanguage(language, names)).exportHunspell();
    }

    public File exportMsWordlist(Language language) throws NoDatabaseAvailableException, IOException {
        List<Name> names = nameService.getAllNames(new Pagination(100000, 0), null, null).stream().toList();
        return Objects.requireNonNull(getMsWordListGenerator(language, names)).exportWordlist(language);
    }

    private HunspellGenerator getGeneratorForLanguage(Language language, List<Name> names) {
        return switch (language) {
            case PUTER -> new PuterHunspellGenerator(pgEnvironment, names);
            case RUMANTSCHGRISCHUN -> new RumantschgrischunHunspellGenerator(pgEnvironment, names);
            case SURMIRAN -> new SurmiranHunspellGenerator(pgEnvironment, names);
            case SURSILVAN -> new SursilvanHunspellGenerator(pgEnvironment, names);
            case SUTSILVAN -> new SutsilvanHunspellGenerator(pgEnvironment, names);
            case VALLADER -> new ValladerHunspellGenerator(pgEnvironment, names);
        };
    }

    private PartOfSpeechListGenerator getMsWordListGenerator(Language language, List<Name> names) {
        return switch (language) {
            case PUTER -> new PuterPartOfSpeechListGenerator(pgEnvironment, names);
            case RUMANTSCHGRISCHUN -> new RumantschgrischunPartOfSpeechListGenerator(pgEnvironment, names);
            case SURMIRAN -> new SurmiranPartOfSpeechListGenerator(pgEnvironment, names);
            case SURSILVAN -> new SursilvanPartOfSpeechListGenerator(pgEnvironment, names);
            case SUTSILVAN -> new SutsilvanPartOfSpeechListGenerator(pgEnvironment, names);
            case VALLADER -> new ValladerPartOfSpeechListGenerator(pgEnvironment, names);
        };
    }
}
