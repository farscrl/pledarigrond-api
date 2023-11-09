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
import ch.pledarigrond.common.util.PronunciationNormalizer;
import ch.pledarigrond.inflection.generation.rumantschgrischun.RumantschGrischunConjugation;
import ch.pledarigrond.inflection.generation.surmiran.SurmiranConjugation;
import ch.pledarigrond.inflection.generation.surmiran.SurmiranConjugationClasses;
import ch.pledarigrond.inflection.generation.surmiran.SurmiranConjugationStructure;
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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ch.pledarigrond.common.data.common.LemmaVersion.*;
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

        List<String[]> noInflectionList = new ArrayList<>();

        List<String> genders = getGenderValues(language);
        for (String gender : genders) {
            boolean success = updateNounsByGender(language, gender, noInflectionList);
            if (!success) {
                return false;
            }
        }

        try {
            Files.createDirectories(Paths.get("data/export"));
            File csvOutputFile = new File("data/export/" + language.getName() + "/nouns-without-inflection.csv");
            try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
                noInflectionList.stream()
                        .map(this::convertToCSV)
                        .forEach(pw::println);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        watch.stop();
        logger.info("Elapsed time: {}s", watch.getTotalTimeMillis()/1000);
        return true;
    }

    public boolean generateAdjectiveForms(Language language) {
        StopWatch watch = new StopWatch();
        watch.start();

        List<String[]> noInflectionList = new ArrayList<>();

        List<String> grammarValuesForAdjective = getGrammarValuesForAdjective(language);
        for (String grammarValue : grammarValuesForAdjective) {
            boolean success = updateAdjectivesByGrammar(language, grammarValue, noInflectionList);
            if (!success) {
                return false;
            }
        }


        try {
            Files.createDirectories(Paths.get("data/export"));
            File csvOutputFile = new File("data/export/" + language.getName() + "/adjectives-without-inflection.csv");
            try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
                noInflectionList.stream()
                        .map(this::convertToCSV)
                        .forEach(pw::println);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        watch.stop();
        logger.info("Elapsed time: {}s", watch.getTotalTimeMillis()/1000);
        return true;
    }

    public boolean generateVerbForms(Language language) {
        StopWatch watch = new StopWatch();
        watch.start();

        List<String> grammarValuesForVerbs = getGrammarValuesForVerbs(language);
        for (String grammarValue : grammarValuesForVerbs) {
            boolean success = updateVerbsByGrammar(language, grammarValue);
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


    public boolean fixAutomaticDuplicationErrors(Language language) throws DatabaseException, UnknownHostException {
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, language.getName()).getCollection("entries");

        int counter = 0;
        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            if (entry.getCurrent() == null) {
                List<Integer> internalIds = new ArrayList<>();

                int lastIndex = 10000000;
                int duplicateIndex = 10000000;
                int indexToReplace = 10000000;
                if (entry.getVersionHistory().size() == 1) {
                    if (entry.getVersionHistory().get(0).getInternalId() == 0 && entry.getCurrentId() == 1) {
                        duplicateIndex = 1;
                        indexToReplace = 0;
                        entry.setCurrentId(0);

                        logger.error(entry.getVersionHistory().get(0).getLemmaValues().get("RStichwort"));
                    }
                } else {
                    for (int i = 0; i < entry.getVersionHistory().size(); i++) {
                        if (entry.getVersionHistory().get(i).getInternalId() == lastIndex) {
                            duplicateIndex = lastIndex;
                            int indexCandidate = lastIndex - 1;

                            if (entry.getVersionHistory().get(i-1).getInternalId() == indexCandidate) {
                                logger.error("nonono");
                            } else {
                                indexToReplace = indexCandidate;
                                entry.getVersionHistory().get(i-1).setInternalId(indexToReplace);
                            }
                        }
                        lastIndex = entry.getVersionHistory().get(i).getInternalId();
                    }
                }

                entry.getVersionHistory().forEach(lemmaVersion -> {
                    internalIds.add(lemmaVersion.getInternalId());
                });
                logger.warn(entry.getId() + ": " + entry.getVersionHistory().size() + " entries (" + internalIds.toString() + "). Current ID: " + entry.getCurrentId() + ". " + entry.getVersionHistory().get(0).getLemmaValues().get("RStichwort") + "/" + entry.getVersionHistory().get(0).getLemmaValues().get("DStichwort") + " [" + entry.getId() + "]");
                logger.warn("Resolution: duplicateIndex ( " + duplicateIndex + " ) indexToReplace ( " + indexToReplace + " )");
                counter++;

                BasicDBObject newObject = Converter.convertLexEntry(entry);
                entryCollection.replaceOne(eq("_id", newObject.get("_id")),  new Document(newObject), new ReplaceOptions().upsert(true));
            }
        }

        logger.error("number of cases: " + counter);

        return true;
    }

    public boolean fixValuesWithNoAcceptedVersion(Language language) throws DatabaseException, UnknownHostException {
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, language.getName()).getCollection("entries");

        int counter = 0;
        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            boolean noAccepted = true;
            for (int i = 0; i < entry.getVersionHistory().size(); i++) {
                if (entry.getVersionHistory().get(i).getVerification() == LemmaVersion.Verification.ACCEPTED) {
                    noAccepted = false;
                }
            }

            if (noAccepted) {
                logger.warn(entry.getId());
                logger.error(entry.getCurrent().getLemmaValues().get("RStichwort"));
                logger.error(String.valueOf(entry.getCurrentId()));
                logger.error("--------------------------------------------------");
                counter++;

                int currentId = entry.getCurrentId();
                LemmaVersion lastAccepted = entry.getLemmaVersionByInternalId(currentId - 1);
                if (lastAccepted != null) {
                    lastAccepted.setVerification(LemmaVersion.Verification.ACCEPTED);
                }

                entry.getVersionHistory().forEach(lemmaVersion -> {
                    if (lemmaVersion.getInternalId() >= currentId) {
                        lemmaVersion.setVerification(LemmaVersion.Verification.UNVERIFIED);
                    }
                });

                BasicDBObject newObject = Converter.convertLexEntry(entry);
                entryCollection.replaceOne(eq("_id", newObject.get("_id")),  new Document(newObject), new ReplaceOptions().upsert(true));
            }
        }

        logger.error("number of cases: " + counter);

        return true;
    }

    public boolean fixWrongParentId(Language language) throws DatabaseException, UnknownHostException {
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, language.getName()).getCollection("entries");

        int counter = 0;
        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            boolean wrongId = false;
            for (int i = 0; i < entry.getVersionHistory().size(); i++) {
                if (!entry.getVersionHistory().get(i).getLexEntryId().equals(entry.getId())) {
                    logger.warn("aiaiaiai: " + entry.getId() + " / " + entry.getVersionHistory().get(i).getLexEntryId());
                    wrongId = true;
                    entry.getVersionHistory().get(i).setLexEntryId(entry.getId());
                }
            }

            if (wrongId) {
                counter++;

                BasicDBObject newObject = Converter.convertLexEntry(entry);
                entryCollection.replaceOne(eq("_id", newObject.get("_id")),  new Document(newObject), new ReplaceOptions().upsert(true));
            }
        }

        logger.error("number of cases: " + counter);

        return true;
    }



    private boolean updateNounsByGender(Language language, String gender, List<String[]> noInflectionList) {
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
            // logger.debug(lemma.toString());

            String id = lemma.getLexEntryId();
            String RStichwort = lemma.getLemmaValues().get("RStichwort");
            String DStichwort = lemma.getLemmaValues().get("DStichwort");

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

            // there are entries, that are not valid, as not all data is complete. this data has to be fixed here.
            // adding user ID
            if (entry.getCurrent().getUserId() == null || entry.getCurrent().getUserId().equals("")) {
                entry.getCurrent().setUserId("admin");
            }

            // there are entries, that are not valid, as not all data is complete. this data has to be fixed here.
            // adding timestamp
            if (entry.getCurrent().getTimestamp() == 0L) {
                // searching in history for last timestamp and using that
                for (LemmaVersion lv: entry.getVersionHistory()) {
                    if (lv.getTimestamp() != 0L) {
                        entry.getCurrent().setTimestamp(lv.getTimestamp());
                        break;
                    }
                }

                // if timestamp is still not set, setting current
                if (entry.getCurrent().getTimestamp() == 0L) {
                    entry.getCurrent().setTimestamp(System.currentTimeMillis());
                }
            }

            LemmaVersion mostRecent = createNewLemmaVersion(entry);
            entry.addLemma(mostRecent);

            InflectionResponse inflectionResponse = null;
            try {
                inflectionResponse = inflectionService.guessInflection(language, InflectionType.NOUN, mostRecent.getLemmaValues().get("RStichwort"), mostRecent.getLemmaValues().get("RGenus"), mostRecent.getLemmaValues().get("RFlex"));
            } catch (StringIndexOutOfBoundsException | NullPointerException ex) {
                noInflectionList.add(new String[]{ id, RStichwort, DStichwort, "exception" });
                continue;
            }
            if (inflectionResponse == null) {
                if (language == Language.PUTER || language == Language.VALLADER) {

                    // list of patterns, has to generate two match groups: the base form and a inflection form (can be empty string)
                    List<Pattern> patterns = new ArrayList<>();
                    patterns.add(Pattern.compile("^([\\p{L}]+) \\(([\\p{L}]+), pl\\); [\\p{L}]+ \\([\\p{L}]+, pl\\)$")); //impiegà (impiegạts, pl); impiegạda (impiegạdas, pl)
                    patterns.add(Pattern.compile("^([\\p{L}]+) \\(([\\p{L}]+), pl\\)$")); // grà (grads, pl)
                    patterns.add(Pattern.compile("^([\\p{L}]+) \\([\\p{L}. ]+\\)()$")); // duọnna (dna.)
                    patterns.add(Pattern.compile("^([\\p{L}]+), ([\\p{L}]+)$")); // oboịst, oboịsta

                    for (Pattern pattern: patterns) {
                        Matcher matcher = pattern.matcher(RStichwort);
                        if (matcher.matches()) {
                            String base = matcher.group(1);
                            String plural = "".equals(matcher.group(2)) ? null : matcher.group(2);
                            try {
                                inflectionResponse = inflectionService.guessInflection(language, InflectionType.NOUN, base, mostRecent.getLemmaValues().get("RGenus"), plural);
                            } catch (StringIndexOutOfBoundsException | NullPointerException ex) {
                                // do nothing
                            }

                            if (inflectionResponse != null) {
                                break;
                            }
                        }
                    }

                    if (inflectionResponse == null) {
                        noInflectionList.add(new String[]{ id, RStichwort, DStichwort, "null" });
                        continue;
                    }
                } else {
                    noInflectionList.add(new String[]{ id, RStichwort, DStichwort, "null" });
                    continue;
                }
            }

            for(Map.Entry<String, String> el : inflectionResponse.getInflectionValues().entrySet()) {
                mostRecent.getLemmaValues().put(el.getKey(), el.getValue());
            }

            if (mostRecent.getLemmaValues().get("RGrammatik") == null || "".equals(mostRecent.getLemmaValues().get("RGrammatik"))) {
                mostRecent.getLemmaValues().put("RGrammatik", "subst");
            }
            mostRecent.getPgValues().put(LemmaVersion.AUTOMATIC_CHANGE, AutomaticChangesType.NOUNS.toString());
            mostRecent.getPgValues().put(LemmaVersion.REVIEW_LATER, "false");
            mostRecent.setVerification(LemmaVersion.Verification.UNVERIFIED);
            mostRecent.setStatus(LemmaVersion.Status.UNDEFINED);

            mostRecent.setTimestamp(0L);
            mostRecent.setUserId(null);

            try {
                mongoDbService.update(language, entry, mostRecent);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }
    
    private boolean updateAdjectivesByGrammar(Language language, String grammarValue, List<String[]> noInflectionList) {
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
            // logger.debug(lemma.toString());

            String id = lemma.getLexEntryId();
            String RStichwort = lemma.getLemmaValues().get("RStichwort");
            String DStichwort = lemma.getLemmaValues().get("DStichwort");

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

            // there are entries, that are not valid, as not all data is complete. this data has to be fixed here.
            if (entry.getCurrent().getUserId() == null || entry.getCurrent().getUserId().equals("")) {
                entry.getCurrent().setUserId("admin");
            }

            // there are entries, that are not valid, as not all data is complete. this data has to be fixed here.
            // adding timestamp
            if (entry.getCurrent().getTimestamp() == 0L) {
                // searching in history for last timestamp and using that
                for (LemmaVersion lv: entry.getVersionHistory()) {
                    if (lv.getTimestamp() != 0L) {
                        entry.getCurrent().setTimestamp(lv.getTimestamp());
                        break;
                    }
                }

                // if timestamp is still not set, setting current
                if (entry.getCurrent().getTimestamp() == 0L) {
                    entry.getCurrent().setTimestamp(System.currentTimeMillis());
                }
            }

            LemmaVersion mostRecent = createNewLemmaVersion(entry);
            entry.addLemma(mostRecent);

            InflectionResponse inflectionResponse = null;
            try {
                inflectionResponse = inflectionService.guessInflection(language, InflectionType.ADJECTIVE, mostRecent.getLemmaValues().get("RStichwort"), mostRecent.getLemmaValues().get("RGenus"), mostRecent.getLemmaValues().get("RFlex"));
            } catch (StringIndexOutOfBoundsException | NullPointerException ex) {
                noInflectionList.add(new String[]{ id, RStichwort, DStichwort, "exception" });
                continue;
            }
            if (inflectionResponse == null) {
                if (language == Language.PUTER || language == Language.VALLADER) {

                    // list of patterns, has to generate two match groups: the base form and a inflection form (can be empty string)
                    List<Pattern> patterns = new ArrayList<>();
                    patterns.add(Pattern.compile("^([\\p{L}]+), ([\\p{L}]+)$")); // furbạz, furbạzza

                    for (Pattern pattern: patterns) {
                        Matcher matcher = pattern.matcher(RStichwort);
                        if (matcher.matches()) {
                            String base = matcher.group(1);
                            String plural = "".equals(matcher.group(2)) ? null : matcher.group(2);
                            try {
                                inflectionResponse = inflectionService.guessInflection(language, InflectionType.ADJECTIVE, base, mostRecent.getLemmaValues().get("RGenus"), plural);
                            } catch (StringIndexOutOfBoundsException | NullPointerException ex) {
                                // do nothing
                            }

                            if (inflectionResponse != null) {
                                break;
                            }
                        }
                    }

                    if (inflectionResponse == null) {
                        noInflectionList.add(new String[]{ id, RStichwort, DStichwort, "null" });
                        continue;
                    }
                } else {
                    noInflectionList.add(new String[]{ id, RStichwort, DStichwort, "null" });
                    continue;
                }
            }

            for(Map.Entry<String, String> el : inflectionResponse.getInflectionValues().entrySet()) {
                mostRecent.getLemmaValues().put(el.getKey(), el.getValue());
            }

            mostRecent.getPgValues().put(LemmaVersion.AUTOMATIC_CHANGE, AutomaticChangesType.ADJECTIVES.toString());
            mostRecent.getPgValues().put(LemmaVersion.REVIEW_LATER, "false");
            mostRecent.setVerification(LemmaVersion.Verification.UNVERIFIED);
            mostRecent.setStatus(LemmaVersion.Status.UNDEFINED);

            mostRecent.setTimestamp(0L);
            mostRecent.setUserId(null);

            try {
                mongoDbService.update(language, entry, mostRecent);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    private boolean updateVerbsByGrammar(Language language, String grammarValue) {
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

            String RStichwort = lemma.getLemmaValues().get("RStichwort");

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

            // there are entries, that are not valid, as not all data is complete. this data has to be fixed here.
            if (entry.getCurrent().getUserId() == null || entry.getCurrent().getUserId().equals("")) {
                entry.getCurrent().setUserId("admin");
            }

            // there are entries, that are not valid, as not all data is complete. this data has to be fixed here.
            // adding timestamp
            if (entry.getCurrent().getTimestamp() == 0L) {
                // searching in history for last timestamp and using that
                for (LemmaVersion lv: entry.getVersionHistory()) {
                    if (lv.getTimestamp() != 0L) {
                        entry.getCurrent().setTimestamp(lv.getTimestamp());
                        break;
                    }
                }

                // if timestamp is still not set, setting current
                if (entry.getCurrent().getTimestamp() == 0L) {
                    entry.getCurrent().setTimestamp(System.currentTimeMillis());
                }
            }

            LemmaVersion mostRecent = createNewLemmaVersion(entry);
            entry.addLemma(mostRecent);

            InflectionResponse inflectionResponse = null;
            try {
                inflectionResponse = inflectionService.guessInflection(language, InflectionType.V, mostRecent.getLemmaValues().get("RStichwort"), mostRecent.getLemmaValues().get("RGenus"), mostRecent.getLemmaValues().get("RFlex"));
            } catch (StringIndexOutOfBoundsException | NullPointerException ex) {
                continue;
            }
            if (inflectionResponse == null) {
                continue;
            }


            for(Map.Entry<String, String> el : inflectionResponse.getInflectionValues().entrySet()) {
                mostRecent.getLemmaValues().put(el.getKey(), el.getValue());
            }

            mostRecent.getPgValues().put(LemmaVersion.AUTOMATIC_CHANGE, AutomaticChangesType.VERBS.toString());
            mostRecent.getPgValues().put(LemmaVersion.REVIEW_LATER, "false");
            mostRecent.setVerification(LemmaVersion.Verification.UNVERIFIED);
            mostRecent.setStatus(LemmaVersion.Status.UNDEFINED);

            mostRecent.setTimestamp(0L);
            mostRecent.setUserId(null);

            try {
                mongoDbService.update(language, entry, mostRecent);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    public static List<String> getGenderValues(Language language) {
        if (language == Language.PUTER) {
            return Stream.of(
                    "(m)",
                    "f",
                    "f ",
                    "f (fpl)",
                    "f(pl)",
                    "f/m",
                    "f/m/n",
                    "f/n",
                    "ff",
                    "fpl",
                    "m",
                    "m  ",
                    "m(f)",
                    "m/f",
                    "m/mpl",
                    "m/n",
                    "m/n                                                                                                                                       ",
                    "m/pl",
                    "mpl",
                    "n/f",
                    "n/m",
                    "npl",
                    "pl",
                    "pl/npl"
            ).collect(Collectors.toList());
        }

        if (language == Language.VALLADER) {
            return Stream.of(
                    "(f)",
                    "(m)",
                    "f",
                    "f ",
                    "f  ",
                    "f (pl)",
                    "f coll",
                    "f pl",
                    "f(m)",
                    "f/m",
                    "f/m/n",
                    "f/n",
                    "ff",
                    "fm",
                    "fpl",
                    "m",
                    "m ",
                    "m (-)",
                    "m(n",
                    "m(pl)",
                    "m.f",
                    "m/f",
                    "m/fpl",
                    "m/n",
                    "m/npl",
                    "mf",
                    "mfpl",
                    "ml",
                    "mpl",
                    "n",
                    "n/f",
                    "n/m",
                    "npl",
                    "pl"
            ).collect(Collectors.toList());
        }

        return Stream.of(
                "(coll)m",
                "(f)m",
                "(f)pl",
                "(m)f",
                "(pl)f",
                "M",
                "col",
                "coll",
                "colectiv",
                "f",
                "f (m)",
                "f(m)",
                "f(n)",
                "f(pl)",
                "f.(pl)",
                "f.pl",
                "f/m",
                "f/m.pl",
                "f/n",
                "m",
                "m(f)",
                "m(f).pl",
                "m(f)f",
                "m(f)pl",
                "m(n)",
                "m(pl)",
                "m.(pl)",
                "m.n",
                "m.pl",
                "m/f",
                "m/f.pl",
                "m/n",
                "n",
                "n(f)",
                "n(pl)",
                "pl"
        ).collect(Collectors.toList());
    }
    
    public static List<String> getGrammarValuesForAdjective(Language language) {
        if (language == Language.PUTER) {
            return Stream.of(
                    "I. adj",
                    "I. adj cump",
                    "I. adj invar",
                    "I. adj pred",
                    "I. adj/adv",
                    "I. adj/pron",
                    "I. adj/pron poss",
                    "I. num/adj",
                    "II. adj",
                    "II. adj attr",
                    "II. adj pred",
                    "II. adj/adv",
                    "II. adj/pron dem",
                    "II. num/adj",
                    "III. adj",
                    "adj",
                    "adj (pred)",
                    "adj f",
                    "adj indef",
                    "adj invar",
                    "adj invar / num",
                    "adj invar/adv",
                    "adj m",
                    "adj mpl",
                    "adj pred",
                    "adj pred f",
                    "adj pred invar",
                    "adj pred invar / adv",
                    "adj pred/adv",
                    "adj/adv",
                    "adj/adv cump",
                    "adj/cj",
                    "adj/num",
                    "adj/pp",
                    "adj/pron",
                    "adj/pron dem",
                    "adj/pron dem fpl",
                    "adj/pron dem mpl",
                    "adj/pron dem pl f",
                    "adj/pron f",
                    "adj/pron indef",
                    "adj/pron indef f",
                    "adj/pron poss",
                    "adj/pron poss fpl",
                    "adjf",
                    "num/adj",
                    "num/adj f",
                    "pron/adj",
                    "pron/adj dem",
                    "pron/adj dem dat"
            ).collect(Collectors.toList());
        }

        if (language == Language.VALLADER) {
            return Stream.of(
                    "I. adj",
                    "I. adj invar",
                    "I. adj/adv",
                    "I. adj/pron",
                    "II. adj",
                    "II. adj attr",
                    "II. adj inv.",
                    "II. adj/adj",
                    "II. adj/adv",
                    "II. adj/pron",
                    "III. adj",
                    "aadj",
                    "adj",
                    "adj attr",
                    "adj invar",
                    "adj poss f",
                    "adj poss m",
                    "adj pred",
                    "adj.",
                    "adj./pron.poss",
                    "adj.f",
                    "adj.inv",
                    "adj.inv/adv",
                    "adj/adv",
                    "adj/num",
                    "adj/pp",
                    "adj/pron",
                    "adj/pron.indef"
            ).collect(Collectors.toList());
        }

        return Stream.of(
                "adj",
                "adj (nur präd. +adv)",
                "adj inv/adv",
                "adj(adv)",
                "adj.",
                "adj.f",
                "adj.inv",
                "adj.inv/adv",
                "adj.m",
                "adj.poss.f",
                "adj.poss.pl",
                "adj.sup",
                "adj/adv",
                "adj/pron indef"
        ).collect(Collectors.toList());
    }

    public static List<String> getGrammarValuesForVerbs(Language language) {
        return Stream.of(
                "(refl) tr",
                "(refl)tr",
                "(tr) int",
                "3.sg.",
                "int",
                "konj",
                "refl",
                "refl. impers",
                "tr",
                "tr +",
                "tr+",
                "tr/int",
                "tr/int/impers",
                "tr/refl"
                ).collect(Collectors.toList());
    }

    private boolean areConjugationFormsEqual(InflectionResponse inflectionResponse, LemmaVersion lemma) {
        for (String key : inflectionResponse.getInflectionValues().keySet()) {
            if (key.equals("infinitiv") || key.equals("RInflectionSubtype") || key.equals("RInflectionType") || key.equals("RRegularInflection") || key.contains("conjunctivimperfect") || key.contains("cundizionalindirect") || key.contains("enclitic")) {
                continue;
            }

            if (inflectionResponse.getInflectionValues().get(key) != null && !inflectionResponse.getInflectionValues().get(key).equals(lemma.getLemmaValues().get(key))) {
                return false;
            }
        }
        return true;
    }

    private LemmaVersion createNewLemmaVersion(LexEntry entry) {
        LemmaVersion newVersion = new LemmaVersion();
        newVersion.getLemmaValues().putAll(entry.getCurrent().getLemmaValues());
        newVersion.getPgValues().putAll(entry.getCurrent().getPgValues());
        newVersion.setVerification(LemmaVersion.Verification.UNVERIFIED);
        newVersion.setTimestamp(System.currentTimeMillis());
        return newVersion;
    }

    private LexEntry createNewLexEntry(LemmaVersion lemmaVersion) {
        return new LexEntry(lemmaVersion);
    }

    /*
    private void copyMissingForms(HashMap<String, String> inflectionValues, HashMap<String, String> mostRecent) {
        // conjunctiv imperfect
        mostRecent.put(SutsilvanConjugationStructure.conjunctivimperfectsing1, inflectionValues.get(SutsilvanConjugationStructure.conjunctivimperfectsing1));
        mostRecent.put(SutsilvanConjugationStructure.conjunctivimperfectsing2, inflectionValues.get(SutsilvanConjugationStructure.conjunctivimperfectsing2));
        mostRecent.put(SutsilvanConjugationStructure.conjunctivimperfectsing3, inflectionValues.get(SutsilvanConjugationStructure.conjunctivimperfectsing3));
        mostRecent.put(SutsilvanConjugationStructure.conjunctivimperfectplural1, inflectionValues.get(SutsilvanConjugationStructure.conjunctivimperfectplural1));
        mostRecent.put(SutsilvanConjugationStructure.conjunctivimperfectplural2, inflectionValues.get(SutsilvanConjugationStructure.conjunctivimperfectplural2));
        mostRecent.put(SutsilvanConjugationStructure.conjunctivimperfectplural3, inflectionValues.get(SutsilvanConjugationStructure.conjunctivimperfectplural3));

        // cundiziunal indirect
        mostRecent.put(SutsilvanConjugationStructure.cundizionalindirectsing1, inflectionValues.get(SutsilvanConjugationStructure.cundizionalindirectsing1));
        mostRecent.put(SutsilvanConjugationStructure.cundizionalindirectsing2, inflectionValues.get(SutsilvanConjugationStructure.cundizionalindirectsing2));
        mostRecent.put(SutsilvanConjugationStructure.cundizionalindirectsing3, inflectionValues.get(SutsilvanConjugationStructure.cundizionalindirectsing3));
        mostRecent.put(SutsilvanConjugationStructure.cundizionalindirectplural1, inflectionValues.get(SutsilvanConjugationStructure.cundizionalindirectplural1));
        mostRecent.put(SutsilvanConjugationStructure.cundizionalindirectplural2, inflectionValues.get(SutsilvanConjugationStructure.cundizionalindirectplural2));
        mostRecent.put(SutsilvanConjugationStructure.cundizionalindirectplural3, inflectionValues.get(SutsilvanConjugationStructure.cundizionalindirectplural3));

        // preschent enclitic
        mostRecent.put(SutsilvanConjugationStructure.preschentsing1enclitic, inflectionValues.get(SutsilvanConjugationStructure.preschentsing1enclitic));
        mostRecent.put(SutsilvanConjugationStructure.preschentsing2enclitic, inflectionValues.get(SutsilvanConjugationStructure.preschentsing2enclitic));
        mostRecent.put(SutsilvanConjugationStructure.preschentsing3encliticm, inflectionValues.get(SutsilvanConjugationStructure.preschentsing3encliticm));
        mostRecent.put(SutsilvanConjugationStructure.preschentsing3encliticf, inflectionValues.get(SutsilvanConjugationStructure.preschentsing3encliticf));
        mostRecent.put(SutsilvanConjugationStructure.preschentplural1enclitic, inflectionValues.get(SutsilvanConjugationStructure.preschentplural1enclitic));
        mostRecent.put(SutsilvanConjugationStructure.preschentplural2enclitic, inflectionValues.get(SutsilvanConjugationStructure.preschentplural2enclitic));
        mostRecent.put(SutsilvanConjugationStructure.preschentplural3enclitic, inflectionValues.get(SutsilvanConjugationStructure.preschentplural3enclitic));

        // imperfect enclitic
        mostRecent.put(SutsilvanConjugationStructure.imperfectsing1enclitic, inflectionValues.get(SutsilvanConjugationStructure.imperfectsing1enclitic));
        mostRecent.put(SutsilvanConjugationStructure.imperfectsing2enclitic, inflectionValues.get(SutsilvanConjugationStructure.imperfectsing2enclitic));
        mostRecent.put(SutsilvanConjugationStructure.imperfectsing3encliticm, inflectionValues.get(SutsilvanConjugationStructure.imperfectsing3encliticm));
        mostRecent.put(SutsilvanConjugationStructure.imperfectsing3encliticf, inflectionValues.get(SutsilvanConjugationStructure.imperfectsing3encliticf));
        mostRecent.put(SutsilvanConjugationStructure.imperfectplural1enclitic, inflectionValues.get(SutsilvanConjugationStructure.imperfectplural1enclitic));
        mostRecent.put(SutsilvanConjugationStructure.imperfectplural2enclitic, inflectionValues.get(SutsilvanConjugationStructure.imperfectplural2enclitic));
        mostRecent.put(SutsilvanConjugationStructure.imperfectplural3enclitic, inflectionValues.get(SutsilvanConjugationStructure.imperfectplural3enclitic));

        // conjunctiv enclitic
        mostRecent.put(SutsilvanConjugationStructure.cundizionalsing1enclitic, inflectionValues.get(SutsilvanConjugationStructure.cundizionalsing1enclitic));
        mostRecent.put(SutsilvanConjugationStructure.cundizionalsing2enclitic, inflectionValues.get(SutsilvanConjugationStructure.cundizionalsing2enclitic));
        mostRecent.put(SutsilvanConjugationStructure.cundizionalsing3encliticm, inflectionValues.get(SutsilvanConjugationStructure.cundizionalsing3encliticm));
        mostRecent.put(SutsilvanConjugationStructure.cundizionalsing3encliticf, inflectionValues.get(SutsilvanConjugationStructure.cundizionalsing3encliticf));
        mostRecent.put(SutsilvanConjugationStructure.cundizionalplural1enclitic, inflectionValues.get(SutsilvanConjugationStructure.cundizionalplural1enclitic));
        mostRecent.put(SutsilvanConjugationStructure.cundizionalplural2enclitic, inflectionValues.get(SutsilvanConjugationStructure.cundizionalplural2enclitic));
        mostRecent.put(SutsilvanConjugationStructure.cundizionalplural3enclitic, inflectionValues.get(SutsilvanConjugationStructure.cundizionalplural3enclitic));
    }
    */

    private String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    private String escapeSpecialCharacters(String data) {
        if (data == null) {
            return "";
        }

        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
