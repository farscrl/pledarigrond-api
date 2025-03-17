package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.NameService;
import ch.pledarigrond.api.services.SpellcheckerService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.database.services.DictionaryService;
import ch.pledarigrond.spellchecker.generator.hunspell.*;
import ch.pledarigrond.spellchecker.generator.pos.*;
import ch.pledarigrond.spellchecker.model.GitDataDto;
import ch.pledarigrond.spellchecker.utils.GitUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SpellcheckerServiceImpl implements SpellcheckerService {

    Logger logger = LoggerFactory.getLogger(SpellcheckerServiceImpl.class);

    @Autowired
    private PgEnvironment pgEnvironment;

    @Autowired
    private NameService nameService;

    @Autowired
    private DictionaryService dictionaryService;


    @Value("${pg.hunspell.git.path}")
    private String repoPath;

    @Value("${pg.hunspell.git.url}")
    private String remoteUrl;

    @Value("${pg.hunspell.git.branch}")
    private String remoteBranch;

    @Value("${pg.hunspell.git.token}")
    private String gitToken;

    @Value("${pg.hunspell.location}")
    private String hunspellLocation;

    @Value("${pg.activeLanguages}")
    private String[] activeLanguagesArray;
    private List<Language> activeLanguages;

    @PostConstruct
    public void init() {
        this.activeLanguages = Arrays.stream(activeLanguagesArray)
                .map(Language::valueOf)
                .collect(Collectors.toList());
    }

    @Scheduled(cron = "${pg.hunspell.cron}")
    private void export() throws IOException {
        logger.info("Scheduled export of Hunspell spellchecker files");

        generateAndCommit();

        logger.info("Export of Hunspell spellchecker files finished");
    }


    @Override
    public File exportHunspell(Language language) throws IOException {
        List<String> names = nameService.getWordsForLanguage(language);
        return Objects.requireNonNull(getGeneratorForLanguage(language, names)).exportHunspell(dictionaryService.getStreamForEntries());
    }

    @Override
    public void generateAndCommit() throws IOException {
        long startTime = System.currentTimeMillis();

        for (Language language : activeLanguages) {
            long languageStartTime = System.currentTimeMillis();
            logger.info("Generating Hunspell for language {}", language);
            List<String> names = nameService.getWordsForLanguage(language);
            HunspellGenerator generator = getGeneratorForLanguage(language, names);
            generator.generateHunspell(dictionaryService.getStreamForEntries());
            long executionTime = (System.currentTimeMillis() - languageStartTime) / 1000;
            logger.info("Execution time for language {}: {}s", language, executionTime);
        }

        GitDataDto gitData = new GitDataDto(repoPath, remoteUrl, remoteBranch, gitToken, hunspellLocation);

        GitUtil gitUtil = new GitUtil(activeLanguages);
        gitUtil.commit(gitData);

        long endTime = System.currentTimeMillis();
        long executionTime = (endTime - startTime) / 1000;
        logger.info("Execution time for hunspell generation: {}s", executionTime);
    }

    @Override
    public File exportMsWordlist(Language language) throws IOException {
        List<String> names = nameService.getWordsForLanguage(language);
        return Objects.requireNonNull(getMsWordListGenerator(language, names)).exportWordlist(language, dictionaryService.getStreamForEntries());
    }

    private HunspellGenerator getGeneratorForLanguage(Language language, List<String> names) throws IOException {
        return switch (language) {
            case PUTER -> new PuterHunspellGenerator(pgEnvironment, names);
            case RUMANTSCHGRISCHUN -> new RumantschgrischunHunspellGenerator(pgEnvironment, names);
            case SURMIRAN -> new SurmiranHunspellGenerator(pgEnvironment, names);
            case SURSILVAN -> new SursilvanHunspellGenerator(pgEnvironment, names);
            case SUTSILVAN -> new SutsilvanHunspellGenerator(pgEnvironment, names);
            case VALLADER -> new ValladerHunspellGenerator(pgEnvironment, names);
        };
    }

    private PartOfSpeechListGenerator getMsWordListGenerator(Language language, List<String> names) {
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
