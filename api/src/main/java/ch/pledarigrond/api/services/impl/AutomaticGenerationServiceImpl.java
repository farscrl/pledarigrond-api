package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.AutomaticGenerationService;
import ch.pledarigrond.api.services.EditorService;
import ch.pledarigrond.api.services.InflectionService;
import ch.pledarigrond.api.services.MongoDbService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.inflection.generation.surmiran.SurmiranConjugation;
import ch.pledarigrond.inflection.generation.surmiran.SurmiranConjugationClasses;
import ch.pledarigrond.inflection.generation.surmiran.SurmiranConjugationStructure;
import ch.pledarigrond.inflection.generation.surmiran.SurmiranInflection;
import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.model.InflectionSubType;
import ch.pledarigrond.inflection.model.InflectionType;
import ch.pledarigrond.inflection.utils.ConjugationStructureGenerator;
import ch.pledarigrond.inflection.utils.PronounRemover;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.mongodb.util.MongoHelper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.ReplaceOptions;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ch.pledarigrond.common.data.common.LemmaVersion.RM_INFLECTION_SUBTYPE;
import static ch.pledarigrond.common.data.common.LemmaVersion.RM_INFLECTION_TYPE;
import static com.mongodb.client.model.Filters.eq;

@Service
public class AutomaticGenerationServiceImpl implements AutomaticGenerationService {

    private static final Logger logger = LoggerFactory.getLogger(AutomaticGenerationServiceImpl.class);

    @Autowired
    private EditorService editorService;

    @Autowired
    private InflectionService inflectionService;

    @Autowired
    private MongoDbService mongoDbService;

    @Autowired
    private PgEnvironment pgEnvironment;

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

    public boolean fixMissingIds(Language language) throws DatabaseException, UnknownHostException {
        if (true) {
            return false;
        }
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, language.getName()).getCollection("entries");

        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            boolean didChange = false;

            for (LemmaVersion lemmaVersion : entry.getVersionHistory()) {
                if(lemmaVersion.getLexEntryId() == null) {
                    lemmaVersion.setLexEntryId(entry.getId());
                    didChange = true;
                    //System.out.println(lemmaVersion.toString());
                }
            }

            if (didChange) {
                BasicDBObject newObject = Converter.convertLexEntry(entry);
                entryCollection.replaceOne(eq("_id", newObject.get("_id")),  new Document(newObject), new ReplaceOptions().upsert(true));

            }
        }

        return true;
    }

    public HashMap<String, String> listWrongNextIds(Language language) throws DatabaseException {
        HashMap<String, String> wrong = new HashMap<>();

        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();

        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            if (entry.getNextInternalId() != entry.getVersionHistory().size()) {
                wrong.put(entry.getId(), entry.getNextInternalId()+"");
            }
        }

        return wrong;
    }

    public boolean fixWrongNextIds(Language language) throws DatabaseException, UnknownHostException {
        if (true) {
            return false;
        }
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, language.getName()).getCollection("entries");

        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            if (entry.getNextInternalId() != entry.getVersionHistory().size()) {
                entry.setNextInternalId(entry.getVersionHistory().size());
                int index = entry.getVersionHistory().size() - 1;
                for (LemmaVersion lemmaVersion: entry.getVersionHistory()) {
                    if (lemmaVersion.getInternalId() != index) {
                        lemmaVersion.setInternalId(index);
                    }
                    if (lemmaVersion.getVerification() == LemmaVersion.Verification.ACCEPTED) {
                        entry.setCurrent(lemmaVersion);
                    }

                    index--;
                }

                if (index != -1) {
                    throw new RuntimeException("Index not on -1");
                }

                BasicDBObject newObject = Converter.convertLexEntry(entry);
                entryCollection.replaceOne(eq("_id", newObject.get("_id")),  new Document(newObject), new ReplaceOptions().upsert(true));
            }

        }

        return true;
    }

    public List<LexEntry> findEntriesWithWrongState(Language language) throws NoDatabaseAvailableException {
        List<LexEntry> wrong = new ArrayList<>();

        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();

        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            if (entry.getVersionHistory().get(0).getVerification() == LemmaVersion.Verification.OUTDATED) {
                wrong.add(entry);
            }
        }

        return wrong;
    }

    public boolean fixEntriesWithWrongState(Language language) throws DatabaseException, UnknownHostException {
        if (true) {
            return false;
        }
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, language.getName()).getCollection("entries");

        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            if (entry.getVersionHistory().get(0).getVerification() == LemmaVersion.Verification.OUTDATED) {
                for (LemmaVersion lemmaVersion : entry.getVersionHistory()) {
                    if (lemmaVersion.getVerification() == LemmaVersion.Verification.ACCEPTED) {
                        lemmaVersion.setVerification(LemmaVersion.Verification.OUTDATED);
                    }
                }
                entry.getVersionHistory().get(0).setVerification(LemmaVersion.Verification.ACCEPTED);
                entry.setCurrent(entry.getVersionHistory().get(0));

                BasicDBObject newObject = Converter.convertLexEntry(entry);
                entryCollection.replaceOne(eq("_id", newObject.get("_id")),  new Document(newObject), new ReplaceOptions().upsert(true));
            }
        }

        return true;
    }

    public boolean addEncliticForms(Language language) throws DatabaseException, UnknownHostException {
        if (true) {
            return false;
        }
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, language.getName()).getCollection("entries");

        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            if (entry.getCurrent() != null && "V".equals(entry.getCurrent().getEntryValue(RM_INFLECTION_TYPE)) && entry.getCurrent().getLemmaValues().get("preschentsing3") != null) {
                if (language == Language.SURMIRAN) {
                    SurmiranConjugationStructure cs = ConjugationStructureGenerator.generateSurmiranConjugationStructure(entry.getCurrent().getLemmaValues());
                    cs.setConjugationClass(SurmiranConjugationClasses.getConjugationClass(entry.getCurrent().getEntryValue(RM_INFLECTION_SUBTYPE)));

                    if (cs.getConjugationclass() == null) {
                        SurmiranConjugation conj = new SurmiranConjugation();
                        InflectionResponse ir = conj.guessInflection(entry.getCurrent().getEntryValue("RStichwort"), "", "");
                        if (ir != null) {
                            cs.setConjugationClass(ir.getInflectionSubType());
                        } else {
                            cs.setConjugationClass(SurmiranConjugationClasses.getConjugationClass("1"));
                        }
                    }

                    PronounRemover pr = new PronounRemover();
                    cs.setPreschentsing1(pr.removePronouns(language, cs.getPreschentsing1()));
                    cs.setPreschentsing2(pr.removePronouns(language, cs.getPreschentsing2()));
                    cs.setPreschentsing3(pr.removePronouns(language, cs.getPreschentsing3()));
                    cs.setPreschentplural1(pr.removePronouns(language, cs.getPreschentplural1()));
                    cs.setPreschentplural2(pr.removePronouns(language, cs.getPreschentplural2()));
                    cs.setPreschentplural3(pr.removePronouns(language, cs.getPreschentplural3()));

                    cs.setImperfectsing1(pr.removePronouns(language, cs.getImperfectsing1()));
                    cs.setImperfectsing2(pr.removePronouns(language, cs.getImperfectsing2()));
                    cs.setImperfectsing3(pr.removePronouns(language, cs.getImperfectsing3()));
                    cs.setImperfectplural1(pr.removePronouns(language, cs.getImperfectplural1()));
                    cs.setImperfectplural2(pr.removePronouns(language, cs.getImperfectplural2()));
                    cs.setImperfectplural3(pr.removePronouns(language, cs.getImperfectplural3()));

                    cs.setCundizionalsing1(pr.removePronouns(language, cs.getCundizionalsing1()));
                    cs.setCundizionalsing2(pr.removePronouns(language, cs.getCundizionalsing2()));
                    cs.setCundizionalsing3(pr.removePronouns(language, cs.getCundizionalsing3()));
                    cs.setCundizionalplural1(pr.removePronouns(language, cs.getCundizionalplural1()));
                    cs.setCundizionalplural2(pr.removePronouns(language, cs.getCundizionalplural2()));
                    cs.setCundizionalplural3(pr.removePronouns(language, cs.getCundizionalplural3()));

                    cs.setFutursing1(pr.removePronouns(language, cs.getFutursing1()));
                    cs.setFutursing2(pr.removePronouns(language, cs.getFutursing2()));
                    cs.setFutursing3(pr.removePronouns(language, cs.getFutursing3()));
                    cs.setFuturplural1(pr.removePronouns(language, cs.getFuturplural1()));
                    cs.setFuturplural2(pr.removePronouns(language, cs.getFuturplural2()));
                    cs.setFuturplural3(pr.removePronouns(language, cs.getFuturplural3()));

                    if (
                            cs.getPreschentsing1() == null || cs.getPreschentsing1().isEmpty() ||
                            cs.getPreschentsing2() == null || cs.getPreschentsing2().isEmpty() ||
                            cs.getPreschentsing3() == null || cs.getPreschentsing3().isEmpty() ||
                            cs.getPreschentplural1() == null || cs.getPreschentplural1().isEmpty() ||
                            cs.getPreschentplural1() == null || cs.getPreschentplural1().isEmpty() ||
                            cs.getPreschentplural1() == null || cs.getPreschentplural1().isEmpty() ||

                            cs.getImperfectsing1() == null || cs.getImperfectsing1().isEmpty() ||
                            cs.getImperfectsing2() == null || cs.getImperfectsing2().isEmpty() ||
                            cs.getImperfectsing3() == null || cs.getImperfectsing3().isEmpty() ||
                            cs.getImperfectplural1() == null || cs.getImperfectplural1().isEmpty() ||
                            cs.getImperfectplural1() == null || cs.getImperfectplural1().isEmpty() ||
                            cs.getImperfectplural1() == null || cs.getImperfectplural1().isEmpty() ||

                            cs.getCundizionalsing1() == null || cs.getCundizionalsing1().isEmpty() ||
                            cs.getCundizionalsing2() == null || cs.getCundizionalsing2().isEmpty() ||
                            cs.getCundizionalsing3() == null || cs.getCundizionalsing3().isEmpty() ||
                            cs.getCundizionalplural1() == null || cs.getCundizionalplural1().isEmpty() ||
                            cs.getCundizionalplural1() == null || cs.getCundizionalplural1().isEmpty() ||
                            cs.getCundizionalplural1() == null || cs.getCundizionalplural1().isEmpty() ||

                            cs.getFutursing1() == null || cs.getFutursing1().isEmpty() ||
                            cs.getFutursing2() == null || cs.getFutursing2().isEmpty() ||
                            cs.getFutursing3() == null || cs.getFutursing3().isEmpty() ||
                            cs.getFuturplural1() == null || cs.getFuturplural1().isEmpty() ||
                            cs.getFuturplural1() == null || cs.getFuturplural1().isEmpty() ||
                            cs.getFuturplural1() == null || cs.getFuturplural1().isEmpty()
                    ) {
                        logger.debug("Form missing for: " + entry.getCurrent().getLemmaValues().get("RStichwort"));
                        continue;
                    }

                    SurmiranConjugation.addEncliticForms(cs);

                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.preschentsing1enclitic, cs.getPreschentsing1Enclitic());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.preschentsing2enclitic, cs.getPreschentsing2Enclitic());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.preschentsing3encliticm, cs.getPreschentsing3EncliticM());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.preschentsing3encliticf, cs.getPreschentsing3EncliticF());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.preschentplural1enclitic, cs.getPreschentplural1Enclitic());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.preschentplural2enclitic, cs.getPreschentplural2Enclitic());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.preschentplural3enclitic, cs.getPreschentplural3Enclitic());

                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.imperfectsing1enclitic, cs.getImperfectsing1Enclitic());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.imperfectsing2enclitic, cs.getImperfectsing2Enclitic());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.imperfectsing3encliticm, cs.getImperfectsing3EncliticM());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.imperfectsing3encliticf, cs.getImperfectsing3EncliticF());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.imperfectplural1enclitic, cs.getImperfectplural1Enclitic());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.imperfectplural2enclitic, cs.getImperfectplural2Enclitic());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.imperfectplural3enclitic, cs.getImperfectplural3Enclitic());

                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.cundizionalsing1enclitic, cs.getCundizionalsing1Enclitic());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.cundizionalsing2enclitic, cs.getCundizionalsing2Enclitic());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.cundizionalsing3encliticm, cs.getCundizionalsing3EncliticM());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.cundizionalsing3encliticf, cs.getCundizionalsing3EncliticF());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.cundizionalplural1enclitic, cs.getCundizionalplural1Enclitic());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.cundizionalplural2enclitic, cs.getCundizionalplural2Enclitic());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.cundizionalplural3enclitic, cs.getCundizionalplural3Enclitic());

                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.futursing1enclitic, cs.getFutursing1Enclitic());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.futursing2enclitic, cs.getFutursing2Enclitic());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.futursing3encliticm, cs.getFutursing3EncliticM());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.futursing3encliticf, cs.getFutursing3EncliticF());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.futurplural1enclitic, cs.getFuturplural1Enclitic());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.futurplural2enclitic, cs.getFuturplural2Enclitic());
                    entry.getCurrent().getLemmaValues().put(SurmiranConjugationStructure.futurplural3enclitic, cs.getFuturplural3Enclitic());

                    BasicDBObject newObject = Converter.convertLexEntry(entry);
                    entryCollection.replaceOne(eq("_id", newObject.get("_id")),  new Document(newObject), new ReplaceOptions().upsert(true));
                }
            }
        }

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
