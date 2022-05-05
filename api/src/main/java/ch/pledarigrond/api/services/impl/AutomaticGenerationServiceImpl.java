package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.AutomaticGenerationService;
import ch.pledarigrond.api.services.EditorService;
import ch.pledarigrond.api.services.InflectionService;
import ch.pledarigrond.api.services.MongoDbService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.model.InflectionType;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.mongodb.exceptions.InvalidEntryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AutomaticGenerationServiceImpl implements AutomaticGenerationService {

    private static final Logger logger = LoggerFactory.getLogger(AutomaticGenerationServiceImpl.class);

    @Autowired
    private EditorService editorService;

    @Autowired
    private InflectionService inflectionService;

    @Autowired
    private PgEnvironment pgEnvironment;

    @Autowired
    private MongoDbService mongoDbService;

    public boolean generateNounForms(Language language) {
        StopWatch watch = new StopWatch();
        watch.start();
        String version = new SimpleDateFormat("yyyyMMdd").format(new Date());

        List<String> genders = getGenderValues();
        for (String gender : genders) {
            boolean success = searchNounByGender(language, gender, version);
            if (!success) {
                return false;
            }
        }

        watch.stop();
        logger.info("Elapsed time: {}s", watch.getTotalTimeMillis()/1000);
        return true;
    }

    private boolean searchNounByGender(Language language, String gender, String version) {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setGender(gender);
        searchCriteria.setExcludeAutomaticChanged(true);
        searchCriteria.setSuggestions(true);
        searchCriteria.setSearchDirection(SearchDirection.ROMANSH);

        Pagination pagination = new Pagination();
        pagination.setPageSize(1000000);

        Page<LemmaVersion> lemmas;
        try {
            lemmas = editorService.search(language, searchCriteria, pagination);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        for (int i = 0; i < lemmas.getContent().size(); i++) {
            LemmaVersion lemma = lemmas.getContent().get(i);
            logger.debug(lemma.toString());

            InflectionResponse inflectionResponse = null;
            try {
                inflectionResponse = inflectionService.guessInflection(language, InflectionType.NOUN, lemma.getLemmaValues().get("RStichwort"), lemma.getLemmaValues().get("RGenus"), lemma.getLemmaValues().get("RFlex"));
            } catch (StringIndexOutOfBoundsException ex) {
                continue;
            }
            if (inflectionResponse == null) {
                continue;
            }

            LexEntry entry = null;
            try {
                entry = editorService.getLexEntry(language, lemma.getLexEntryId());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

            // ignore if lemma has already new version
            if (entry.getUnapprovedVersions().size() > 0 && entry.getMostRecent().getPgValues().get(LemmaVersion.AUTOMATIC_CHANGE) != null) {
                continue;
            }

            LemmaVersion newVersion = new LemmaVersion();
            newVersion.getLemmaValues().putAll(lemma.getLemmaValues());
            newVersion.getPgValues().putAll(lemma.getPgValues());

            // there are entries, that are not valid, as not all data is complete. this data has to be fixed here.
            if (entry.getCurrent().getUserId() == null || entry.getCurrent().getUserId().equals("")) {
                entry.getCurrent().setUserId("admin");
            }

            for(Map.Entry<String, String> el : inflectionResponse.getInflectionValues().entrySet()) {
                newVersion.getLemmaValues().put(el.getKey(), el.getValue());
            }
            newVersion.getLemmaValues().put("RGrammatik", "subst");
            newVersion.getPgValues().put(LemmaVersion.AUTOMATIC_CHANGE, version);
            newVersion.setVerification(LemmaVersion.Verification.UNVERIFIED);

            try {
                mongoDbService.update(language, entry, newVersion);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    public List<String> getGenderValues() {
        return Stream.of(
                "(m)",
                "(n)",
                "coll",
                "f",
                "f (pl Bands)",
                "f (pl Banken)",
                "f (pl Bänke)",
                "f(pl)",
                "f.pl",
                "fcoll",
                "fm",
                "m",
                "m ",
                "m (pl Bände)",
                "m(pl)",
                "m.pl",
                "n",
                "n (pl Bande)",
                "n (pl Bänder)",
                "nf",
                "pl",
                "tr"
        ).collect(Collectors.toList());
    }
}
