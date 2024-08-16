package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.NameService;
import ch.pledarigrond.api.services.SpellcheckerService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SpellcheckerServiceImpl implements SpellcheckerService {

    Logger logger = LoggerFactory.getLogger(SpellcheckerServiceImpl.class);

    @Autowired
    private PgEnvironment pgEnvironment;

    @Autowired
    private NameService nameService;


    @Value("${git.hunspell.repo.path}")
    private String repoPath;

    @Value("${git.hunspell.remote.url}")
    private String remoteUrl;

    @Value("${git.hunspell.remote.branch}")
    private String remoteBranch;

    @Value("${git.hunspell.token}")
    private String gitToken;

    @Scheduled(cron = "${pg.hunspell.cron}")
    private void export() throws IOException, NoDatabaseAvailableException {
        logger.info("Scheduled export of Hunspell spellchecker files");

        generateAndCommit();

        logger.info("Export of Hunspell spellchecker files finished");
    }


    @Override
    public File exportHunspell(Language language) throws NoDatabaseAvailableException, IOException {
        List<String> names = nameService.getWordsForLanguage(language);
        return Objects.requireNonNull(getGeneratorForLanguage(language, names)).exportHunspell();
    }

    @Override
    public void generateAndCommit() throws NoDatabaseAvailableException, IOException {
        long startTime = System.currentTimeMillis();

        List<Language> activeLanguages = new ArrayList<>(List.of(new Language[]{
                // Language.PUTER,
                Language.RUMANTSCHGRISCHUN,
                Language.SURMIRAN,
                // Language.SURSILVAN,
                Language.SUTSILVAN,
                // Language.VALLADER
        }));

        for (Language language : activeLanguages) {
            List<String> names = nameService.getWordsForLanguage(language);
            HunspellGenerator generator = getGeneratorForLanguage(language, names);
            generator.generateHunspell();
        }

        GitDataDto gitData = new GitDataDto(repoPath, remoteUrl, remoteBranch, gitToken);

        GitUtil gitUtil = new GitUtil(activeLanguages);
        gitUtil.commit(gitData);

        long endTime = System.currentTimeMillis();
        long executionTime = (endTime - startTime) / 1000;
        logger.info("Execution time: {}s", executionTime);
    }

    @Override
    public File exportMsWordlist(Language language) throws NoDatabaseAvailableException, IOException {
        List<String> names = nameService.getWordsForLanguage(language);
        return Objects.requireNonNull(getMsWordListGenerator(language, names)).exportWordlist(language);
    }

    private HunspellGenerator getGeneratorForLanguage(Language language, List<String> names) {
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
