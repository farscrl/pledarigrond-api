package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.AutomaticGenerationService;
import ch.pledarigrond.api.services.EditorService;
import ch.pledarigrond.api.services.InflectionService;
import ch.pledarigrond.api.services.MongoDbService;
import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.model.InflectionSubType;
import ch.pledarigrond.inflection.model.InflectionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

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
    private MongoDbService mongoDbService;

    public boolean generateNounForms(Language language) {
        StopWatch watch = new StopWatch();
        watch.start();

        List<String> genders = getGenderValues();
        for (String gender : genders) {
            boolean success = searchNounByGender(language, gender, AutomaticChangesType.NOUNS);
            if (!success) {
                return false;
            }
        }

        watch.stop();
        logger.info("Elapsed time: {}s", watch.getTotalTimeMillis()/1000);
        return true;
    }

    public boolean generateAdjectiveForms(Language language) {
        StopWatch watch = new StopWatch();
        watch.start();

        List<String> grammarValuesForAdjective = getGrammarValuesForAdjective();
        for (String grammarValue : grammarValuesForAdjective) {
            boolean success = searchLemmaByGrammar(language, grammarValue, AutomaticChangesType.ADJECTIVES, InflectionType.ADJECTIVE);
            if (!success) {
                return false;
            }
        }

        watch.stop();
        logger.info("Elapsed time: {}s", watch.getTotalTimeMillis()/1000);
        return true;
    }

    public boolean generateVerbForms(Language language) {
        StopWatch watch = new StopWatch();
        watch.start();

        List<String> grammarValuesForVerbs = getGrammarValuesForVerbs();
        for (String grammarValue : grammarValuesForVerbs) {
            boolean success = searchLemmaByGrammarVerb(language, grammarValue, AutomaticChangesType.VERBS, InflectionType.V);
            if (!success) {
                return false;
            }
        }

        watch.stop();
        logger.info("Elapsed time: {}s", watch.getTotalTimeMillis()/1000);
        return true;
    }

    private boolean searchNounByGender(Language language, String gender, AutomaticChangesType automaticChangesType) {
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
            newVersion.getPgValues().put(LemmaVersion.AUTOMATIC_CHANGE, automaticChangesType.toString());
            newVersion.getPgValues().put(LemmaVersion.REVIEW_LATER, "false");
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

    private boolean searchLemmaByGrammar(Language language, String grammarValue, AutomaticChangesType automaticChangesType, InflectionType type) {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setGrammar(grammarValue);
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
                inflectionResponse = inflectionService.guessInflection(language, type, lemma.getLemmaValues().get("RStichwort"), lemma.getLemmaValues().get("RGenus"), lemma.getLemmaValues().get("RFlex"));
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

            newVersion.getPgValues().put(LemmaVersion.AUTOMATIC_CHANGE, automaticChangesType.toString());
            newVersion.getPgValues().put(LemmaVersion.REVIEW_LATER, "false");
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

    private boolean searchLemmaByGrammarVerb(Language language, String grammarValue, AutomaticChangesType automaticChangesType, InflectionType type) {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setGrammar(grammarValue);
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

            InflectionResponse alreadyExistingInflection = null;
            if (lemma.getLemmaValues().get("preschentsing3") != null && !lemma.getLemmaValues().get("preschentsing3").equals("")) {
                alreadyExistingInflection = iterateOverAllInflectionsAndCompareValues(language, lemma);
            }

            InflectionResponse inflectionResponse = null;
            try {
                inflectionResponse = inflectionService.guessInflection(language, type, lemma.getLemmaValues().get("RStichwort"), lemma.getLemmaValues().get("RGenus"), lemma.getLemmaValues().get("RFlex"));
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

            // check if verb has already conjugations
            boolean overwriteValues = true;
            if (entry.getCurrent().getLemmaValues().get("preschentsing3") != null && !entry.getCurrent().getLemmaValues().get("preschentsing3").equals("")) {
                overwriteValues = areConjugationFormsEqual(inflectionResponse, lemma);
            }

            if (alreadyExistingInflection != null) {
                for(Map.Entry<String, String> el : alreadyExistingInflection.getInflectionValues().entrySet()) {
                    newVersion.getLemmaValues().put(el.getKey(), el.getValue());
                }
            } else if (overwriteValues) {
                for(Map.Entry<String, String> el : inflectionResponse.getInflectionValues().entrySet()) {
                    newVersion.getLemmaValues().put(el.getKey(), el.getValue());
                }
            } else {
                newVersion.getLemmaValues().put("infinitiv", inflectionResponse.getInflectionValues().get("infinitiv"));
                newVersion.getLemmaValues().put("RInflectionSubtype", inflectionResponse.getInflectionValues().get("RInflectionSubtype"));
                newVersion.getLemmaValues().put("RInflectionType", inflectionResponse.getInflectionValues().get("RInflectionType"));
                newVersion.getLemmaValues().put("RRegularInflection", "false");
            }

            newVersion.getPgValues().put(LemmaVersion.AUTOMATIC_CHANGE, automaticChangesType.toString());
            newVersion.getPgValues().put(LemmaVersion.REVIEW_LATER, "false");
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

    public static List<String> getGenderValues() {
        return Stream.of(
                "(m)",
                "(n)",
                "coll",
                "f",
                "f (pl Bands)",
                "f (pl Banken)",
                "f (pl Bänke)",
                "f(pl)",
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
    
    public static List<String> getGrammarValuesForAdjective() {
        return Stream.of(
                "I. adi",
                "II. adj",
                "adi",
                "adj",
                "adj numeral",
                "adj/Pronomen",
                "adj/adv",
                "adj/adverb",
                "adj/num",
                "adj/numeral",
                "adj/pronom indefinit",
                "adj/pronom interrogativ"
        ).collect(Collectors.toList());
    }

    public static List<String> getGrammarValuesForVerbs() {
        return Stream.of(
                "II. Verb modal",
                "II. tr indirect",
                "int",
                "int/impersunal",
                "int/reflexiv",
                "int/tr",
                "int/unpersönlich",
                "refl",
                "tr",
                "tr indirect/int",
                "tr/impersunal",
                "tr/int",
                "tr/int/Verb modal",
                "tr/tr indirect",
                "tr/verb modal"
        ).collect(Collectors.toList());
    }

    private InflectionResponse iterateOverAllInflectionsAndCompareValues(Language language, LemmaVersion lemma) {
        List<InflectionSubType> inflections = inflectionService.getInflectionTypes(language, InflectionType.V);

        for (InflectionSubType i : inflections) {
            try {
                InflectionResponse r = inflectionService.generateInflection(language, InflectionType.V, i.id, lemma.getLemmaValues().get("RStichwort"));
                if (areConjugationFormsEqual(r, lemma)) {
                    return r;
                }
            } catch (RuntimeException ignored) {
            }
        }

        return null;
    }

    private boolean areConjugationFormsEqual(InflectionResponse inflectionResponse, LemmaVersion lemma) {
        for (String key : inflectionResponse.getInflectionValues().keySet()) {
            if (key.equals("infinitiv") || key.equals("RInflectionSubtype") || key.equals("RInflectionType") || key.equals("RRegularInflection")) {
                continue;
            }

            if (!inflectionResponse.getInflectionValues().get(key).equals(lemma.getLemmaValues().get(key))) {
                return false;
            }
        }
        return true;
    }
}
