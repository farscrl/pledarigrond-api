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
import static com.mongodb.client.model.Filters.in;

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
            // boolean success = updateNounsByGenderSutsilvan(language, gender);
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
            // boolean success = updateAdjectivesByGrammarSutsilvan(language, grammarValue);
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
            // boolean success = updateVerbsByGrammarSutsilvan(language, grammarValue);
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

    public boolean changeGrammarIndications(Language language) throws DatabaseException, UnknownHostException {
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, language.getName()).getCollection("entries");

        /*
        List<String> endingAbel = new ArrayList<>();
        List<String> endingIbel = new ArrayList<>();
        List<String> endingO = new ArrayList<>();
        List<String> endingOnt = new ArrayList<>();
        List<String> endingOus = new ArrayList<>();
        List<String> endingEvel = new ArrayList<>();
        List<String> endingAnt = new ArrayList<>();
        List<String> endingIc = new ArrayList<>();
        List<String> endingAl = new ArrayList<>();
        List<String> endingAint = new ArrayList<>();
        List<String> endingIa = new ArrayList<>();
        List<String> endingEa = new ArrayList<>();
        List<String> endingEnt = new ArrayList<>();
        List<String> endingMaintg = new ArrayList<>();
        */

        // Set<String> rGrammatik = new HashSet<>();
        // Set<String> dGrammatik = new HashSet<>();


        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            if (entry.getCurrent() != null) {

                LemmaVersion current = entry.getCurrent();
                String RStichwort = current.getLemmaValues().get("RStichwort");
                String RGrammatik = current.getLemmaValues().get("RGrammatik");
                String DGrammatik = current.getLemmaValues().get("DGrammatik");

                boolean didChange = false;

                if (RGrammatik != null && !"".equals(RGrammatik)) {
                    String newGrammatik = mapGrammar(language, false, RGrammatik);
                    if (newGrammatik == null || !newGrammatik.equals(RGrammatik)) {
                        current.getLemmaValues().put("RGrammatik", newGrammatik);
                        RGrammatik = newGrammatik;
                        didChange = true;
                    }
                }

                if (DGrammatik != null && !"".equals(DGrammatik)) {
                    String newGrammatik = mapGrammar(language, true, DGrammatik);
                    if (newGrammatik == null || !newGrammatik.equals(DGrammatik)) {
                        current.getLemmaValues().put("DGrammatik", newGrammatik);
                        DGrammatik = newGrammatik;
                        didChange = true;
                    }
                }

                /*
                // not using an else clause, as mapping can generate empty values
                if ((RGrammatik == null || "".equals(RGrammatik)) && (RStichwort != null && !RStichwort.equals("") && !RStichwort.contains(" "))) {
                    if (Character.isUpperCase(RStichwort.charAt(0))) {
                        current.getLemmaValues().put("RGrammatik", "nomen");
                        RGrammatik = "nomen";
                        didChange = true;
                    } else if (RStichwort.startsWith("s'")) {
                        current.getLemmaValues().put("RGrammatik", "verb reflexiv");
                        RGrammatik = "verb reflexiv";
                        didChange = true;
                    } else if (RStichwort.endsWith("abel")) {
                        // endingAbel.add(RStichwort);
                        // neginas excepziuns
                        current.getLemmaValues().put("RGrammatik", "adjectiv");
                        RGrammatik = "adjectiv";
                        didChange = true;
                    } else if (RStichwort.endsWith("ibel")) {
                        // endingIbel.add(RStichwort);
                        // neginas excepziuns
                        current.getLemmaValues().put("RGrammatik", "adjectiv");
                        RGrammatik = "adjectiv";
                        didChange = true;
                    } else if (RStichwort.endsWith("o")) {
                        // endingO.add(RStichwort);
                        List<String> excepziunsO = Arrays.asList("abstraho", "apollo", "fallo", "fo", "gio",
                                "incognito", "kilo", "malgro", "no", "passo", "pero", "portavelo", "refurmo", "saldo",
                                "schiglio", " so", "stampro", "tignavelo", "trafo", "unisono", "utro", "zuppo");
                        if (!excepziunsO.contains(RStichwort)) {
                            current.getLemmaValues().put("RGrammatik", "adjectiv");
                            RGrammatik = "adjectiv";
                            didChange = true;
                        }
                    } else if (RStichwort.endsWith("ont")) {
                        // endingOnt.add(RStichwort);
                        List<String> excepziunsOnt = Arrays.asList("cunzont", "nunditgont", "pertutgont", "pront",
                                "schizont", "zont");
                        if (!excepziunsOnt.contains(RStichwort)) {
                            current.getLemmaValues().put("RGrammatik", "adjectiv");
                            RGrammatik = "adjectiv";
                            didChange = true;
                        }
                        current.getLemmaValues().put("RGrammatik", "adjectiv");
                        RGrammatik = "adjectiv";
                        didChange = true;
                    } else if (RStichwort.endsWith("ous")) {
                        // endingOus.add(RStichwort);
                        List<String> excepziunsOus = Arrays.asList("nous", "stgivous", "vous");
                        if (!excepziunsOus.contains(RStichwort)) {
                            current.getLemmaValues().put("RGrammatik", "adjectiv");
                            RGrammatik = "adjectiv";
                            didChange = true;
                        }
                    } else if (RStichwort.endsWith("evel")) {
                        // endingEvel.add(RStichwort);
                        List<String> excepziunsEvel = Arrays.asList("damanevel", "manevel");
                        if (!excepziunsEvel.contains(RStichwort)) {
                            current.getLemmaValues().put("RGrammatik", "adjectiv");
                            RGrammatik = "adjectiv";
                            didChange = true;
                        }
                    } else if (RStichwort.endsWith("ant")) {
                        // endingAnt.add(RStichwort);
                        List<String> excepziunsAnt = Arrays.asList("adegn'anavant", "anavant", "ansant", "avant",
                                "consuprastant", "d'unfant", "dantant", "durant", "gl'unfant", "miglsanavant",
                                "mintgatant", "nunobstant", "nurant", "or(d)avant", "ordavant", "pinavant", "pressant",
                                "protestant", "quant", "schianavant", "tant", "mintgatant");
                        if (!excepziunsAnt.contains(RStichwort)) {
                            current.getLemmaValues().put("RGrammatik", "adjectiv");
                            RGrammatik = "adjectiv";
                            didChange = true;
                        }
                    } else if (RStichwort.endsWith("ic")) {
                        // endingIc.add(RStichwort);
                        List<String> excepziunsIc = Arrays.asList("comic", "elastic", "plastic");
                        if (!excepziunsIc.contains(RStichwort)) {
                            current.getLemmaValues().put("RGrammatik", "adjectiv");
                            RGrammatik = "adjectiv";
                            didChange = true;
                        }
                    } else if (RStichwort.endsWith("al")) {
                        // endingAl.add(RStichwort);
                        current.getLemmaValues().put("RGrammatik", "adjectiv");
                        RGrammatik = "adjectiv";
                        didChange = true;
                    } else if (RStichwort.endsWith("aint")) {
                        // endingAint.add(RStichwort);
                        List<String> excepziunsAint = Arrays.asList("aint", "antgaschamaint", "ardimaint", "fardalaint",
                                "gliunschaint", "liaint", "movimaint", "paraint", "preavertimaint", "ranviamaint",
                                "reconcessiunamaint", "seadaint", "serviaint", "sotaint", "spivaint", "suraint", "tranteraint");
                        if (!excepziunsAint.contains(RStichwort)) {
                            current.getLemmaValues().put("RGrammatik", "adjectiv");
                            RGrammatik = "adjectiv";
                            didChange = true;
                        }
                    } else if (RStichwort.endsWith("ia") && !RStichwort.endsWith("tia")) {
                        // endingIa.add(RStichwort);
                        List<String> excepziunsIa = Arrays.asList("angurgnia", "bagnnia", "buccareia", "cunterviglia",
                                "curegia", "d'arschiglia", "damengia", "ia", "impedia", "malluia", "mengia", "ossuscheia",
                                "pandemia", "plangsia", "staffia", "tottaveia", "uscheia", "ustareia", "via");
                        if (!excepziunsIa.contains(RStichwort)) {
                            current.getLemmaValues().put("RGrammatik", "adjectiv");
                            RGrammatik = "adjectiv";
                            didChange = true;
                        }
                    } else if (RStichwort.endsWith("ea")) {
                        // endingEa.add(RStichwort);
                        List<String> excepziunsEa = Arrays.asList("ea", "geagea", "tschartgea");
                        if (!excepziunsEa.contains(RStichwort)) {
                            current.getLemmaValues().put("RGrammatik", "adjectiv");
                            RGrammatik = "adjectiv";
                            didChange = true;
                        }
                    } else if (RStichwort.endsWith("ent")) {
                        // endingEnt.add(RStichwort);
                        List<String> ExcepziunsEnt = Arrays.asList("absent", "concernent", "correspondent", "d'argient",
                                "davent", "equivalent", "gugent", "impertinent", "independent", "partenent",
                                "transparent", "tschent");
                        if (!ExcepziunsEnt.contains(RStichwort)) {
                            current.getLemmaValues().put("RGrammatik", "adjectiv");
                            RGrammatik = "adjectiv";
                            didChange = true;
                        }
                    } else if (RStichwort.endsWith("maintg")) {
                        // endingMaintg.add(RStichwort);
                        // neginas excepziuns
                        current.getLemmaValues().put("RGrammatik", "adverb");
                        RGrammatik = "adverb";
                        didChange = true;
                    }
                }
                */

                // rGrammatik.add(RGrammatik);
                // dGrammatik.add(DGrammatik);

                if (didChange) {
                    BasicDBObject newObject = Converter.convertLexEntry(entry);
                    entryCollection.replaceOne(eq("_id", newObject.get("_id")),  new Document(newObject), new ReplaceOptions().upsert(true));
                }
            }
        }

        /*
        logger.warn("Ending ABEL: " + endingAbel.toString());
        logger.warn("Ending IBEL: " + endingIbel.toString());
        logger.warn("Ending O: " + endingO.toString());
        logger.warn("Ending ONT: " + endingOnt.toString());
        logger.warn("Ending OUS: " + endingOus.toString());
        logger.warn("Ending EVEL: " + endingEvel.toString());
        logger.warn("Ending ANT: " + endingAnt.toString());
        logger.warn("Ending IC: " + endingIc.toString());
        logger.warn("Ending AL: " + endingAl.toString());
        logger.warn("Ending AINT: " + endingAint.toString());
        logger.warn("Ending IA: " + endingIa.toString());
        logger.warn("Ending EN: " + endingEa.toString());
        logger.warn("Ending ENT: " + endingEnt.toString());
        logger.warn("Ending MAINTG: " + endingMaintg.toString());
        */

        // logger.warn("RGrammatik: " + rGrammatik.toString());
        // logger.warn("DGrammatik: " + dGrammatik.toString());

        return true;
    }

    public boolean changeGenusIndications(Language language) throws DatabaseException, UnknownHostException {
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, language.getName()).getCollection("entries");

        // Set<String> rGenus = new HashSet<>();
        // Set<String> dGenus = new HashSet<>();

        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            if (entry.getCurrent() != null) {

                LemmaVersion current = entry.getCurrent();
                String RStichwort = current.getLemmaValues().get("RStichwort");
                String RGenus = current.getLemmaValues().get("RGenus");
                String DGenus = current.getLemmaValues().get("DGenus");

                boolean didChange = false;

                if (RGenus != null && !"".equals(RGenus)) {
                    String newGenus = mapGenus(language, false, RGenus);
                    if (newGenus == null || !newGenus.equals(RGenus)) {
                        current.getLemmaValues().put("RGenus", newGenus);
                        RGenus = newGenus;
                        didChange = true;
                    }
                }

                if (DGenus != null && !"".equals(DGenus)) {
                    String newGenus = mapGenus(language, true, DGenus);
                    if (newGenus == null || !newGenus.equals(DGenus)) {
                        current.getLemmaValues().put("DGenus", newGenus);
                        DGenus = newGenus;
                        didChange = true;
                    }
                }

                // rGenus.add(RGenus);
                // dGenus.add(DGenus);

                if (didChange) {
                    BasicDBObject newObject = Converter.convertLexEntry(entry);
                    entryCollection.replaceOne(eq("_id", newObject.get("_id")),  new Document(newObject), new ReplaceOptions().upsert(true));
                }
            }
        }

        // logger.warn("RGenus: " + rGenus.toString());
        // logger.warn("DGenus: " + dGenus.toString());

        return true;
    }

    public boolean fixVerbPronounsRg(Language language) throws DatabaseException, UnknownHostException {
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, language.getName()).getCollection("entries");

        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            if (entry.getCurrent() != null) {

                LemmaVersion current = entry.getCurrent();
                String RStichwort = current.getLemmaValues().get("RStichwort");
                String DStichwort = current.getLemmaValues().get("DStichwort");
                String futursing2 = current.getLemmaValues().get("futursing2");
                String futurplural1 = current.getLemmaValues().get("futurplural1");
                String gerundium = current.getLemmaValues().get("gerundium");
                String imperativ2 = current.getLemmaValues().get("imperativ2");

                boolean didChange = false;

                if (futursing2 != null && futursing2.contains("vengs a")) {
                    //logger.warn(">> " + RStichwort + " // " + DStichwort);
                    futursing2 = futursing2.replace("vengs a", "vegns a");
                    current.getLemmaValues().put("futursing2", futursing2);
                    didChange = true;
                }

                if (futurplural1 != null && futurplural1.contains("vengnin a")) {
                    //logger.warn("** " + RStichwort + " // " + DStichwort);
                    futurplural1 = futurplural1.replace("vengnin a", "vegnin a");
                    current.getLemmaValues().put("futurplural1", futurplural1);
                    didChange = true;
                }

                if (didChange) {
                    RumantschGrischunConjugation conj = new RumantschGrischunConjugation();

                    String RStichwortClean = RStichwort.replace(" (per)", "");
                    RStichwortClean = RStichwortClean.replace(" (sin)", "");
                    RStichwortClean = RStichwortClean.replace(" (d'insatge)", "");
                    RStichwortClean = RStichwortClean.replace(" dad insatge", "");
                    RStichwortClean = RStichwortClean.replace(" sin insatge", "");
                    RStichwortClean = RStichwortClean.replace(" (per)", "");
                    RStichwortClean = RStichwortClean.replace(" (en il tren, bus)", "");

                    InflectionResponse ir = conj.guessInflection(RStichwortClean, null, null);
                    if (ir != null) {
                        String gerundium2 = ir.getInflectionValues().get("gerundium");

                        if (!gerundium.equals(gerundium2)) {
                            logger.warn(">> " + RStichwort + " // " + DStichwort + " ::> " + gerundium + " vs " + gerundium2);
                        }

                        String imperativ22 = ir.getInflectionValues().get("imperativ2");

                        if (!imperativ2.equals(imperativ22)) {
                            logger.warn("** " + RStichwort + " // " + DStichwort + " ::> " + imperativ2 + " vs " + imperativ22);
                        }
                    } else {
                        logger.warn(">> " + RStichwort + " // " + DStichwort + " ::> " + gerundium + " vs nagin automatic [" + RStichwortClean + "]");
                        logger.warn("** " + RStichwort + " // " + DStichwort + " ::> " + gerundium + " vs nagin automatic [" + RStichwortClean + "]");
                    }

                    // BasicDBObject newObject = Converter.convertLexEntry(entry);
                    // entryCollection.replaceOne(eq("_id", newObject.get("_id")),  new Document(newObject), new ReplaceOptions().upsert(true));
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

    /**
     * For sutsilvan, there are some edge cases, that will be reviewed after the review-process in the UI.
     * This function allows to export the remaining words into a CSV file, that can be handled later on.
     */
    public boolean exportRemainingWords(Language language) throws IOException, InterruptedException, DatabaseException {
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, language.getName()).getCollection("entries");

        List<String[]> dataLines = new ArrayList<>();

        int counter = 0;
        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            if (entry.getMostRecent() != null) {
                LemmaVersion mostRecent = entry.getMostRecent();

                String id = mostRecent.getLexEntryId();
                String RStichwort = mostRecent.getLemmaValues().get("RStichwort");
                String DStichwort = mostRecent.getLemmaValues().get("DStichwort");

                if ("true".equals(mostRecent.getReviewLater())) {
                    // logger.error(mostRecent.getReviewLater());
                    dataLines.add(new String[]{ id, RStichwort, DStichwort, "pli tard" });
                }

                if (mostRecent.getVerification() == Verification.UNVERIFIED && mostRecent.getAutomaticChange() != null && !"true".equals(mostRecent.getReviewLater())) {
                    // logger.warn(mostRecent.getAutomaticChange());
                    dataLines.add(new String[]{ id, RStichwort, DStichwort, mostRecent.getAutomaticChange() });
                }


            }
        }

        Files.createDirectories(Paths.get("data/export"));
        File csvOutputFile = new File("data/export/" + language.getName() + "/not-reviewed-lemmas.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }

        return true;
    }

    /**
     * During manual review, words with grammar "v" have often been problematic. Thus, this allows to export a list of
     * all the affected words.
     */
    public boolean exportWordsWithGrammarV(Language language) throws IOException, DatabaseException {
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, language.getName()).getCollection("entries");

        List<String[]> dataLines = new ArrayList<>();

        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            if (entry.getMostRecent() != null) {
                LemmaVersion mostRecent = entry.getMostRecent();

                String id = mostRecent.getLexEntryId();
                String RGrammatik = mostRecent.getLemmaValues().get("RGrammatik");
                String RStichwort = mostRecent.getLemmaValues().get("RStichwort");
                String DStichwort = mostRecent.getLemmaValues().get("DStichwort");

                if ("v".equals(RGrammatik.trim())) {
                    // logger.error(mostRecent.getReviewLater());
                    dataLines.add(new String[]{ id, RStichwort, DStichwort, RGrammatik });
                }
            }
        }

        Files.createDirectories(Paths.get("data/export"));
        File csvOutputFile = new File("data/export/" + language.getName() + "/words-with-v_" + language + ".csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }

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

            mostRecent.getLemmaValues().putIfAbsent("RGrammatik", "subst");
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

    private boolean updateNounsByGenderSutsilvan(Language language, String gender) {
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

            String RStichwort = lemma.getLemmaValues().get("RStichwort");

            List<LexEntry> lexEntries = new ArrayList<>();
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

            if (RStichwort.startsWith("(")) {
                // (am)premlitinent
                LemmaVersion newLemmaVersion = createNewLemmaVersion(entry);

                entry.addLemma(newLemmaVersion);
                lexEntries.add(entry);
            } else if (RStichwort.matches("[a-zA-Z\\u00C0-\\u017F\\s]+\\(a\\)")) {
                // vedrer(a)
                LemmaVersion newLemmaVersion = createNewLemmaVersion(entry);

                entry.addLemma(newLemmaVersion);
                lexEntries.add(entry);
            } else if(RStichwort.matches("\\[[a-zA-Z\\u00C0-\\u017F\\s]+\\]")) {
                // [stidar]
                LemmaVersion newLemmaVersion = createNewLemmaVersion(entry);

                entry.addLemma(newLemmaVersion);
                lexEntries.add(entry);
            } else if (RStichwort.matches("[a-zA-Z\\u00C0-\\u017F\\s]+\\[[a-zA-Z\\u00C0-\\u017F\\s]+\\]")) {
                // atatgeader [atacader]
                LemmaVersion newLemmaVersion1 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion2 = createNewLemmaVersion(entry);

                String[] split = RStichwort.split("\\[");
                newLemmaVersion1.getLemmaValues().put("RStichwort", split[0].trim());
                newLemmaVersion2.getLemmaValues().put("RStichwort", split[1].trim().split("\\]")[0]);

                entry.addLemma(newLemmaVersion1);
                LexEntry newLexEntry = createNewLexEntry(newLemmaVersion2);

                lexEntries.add(entry);
                lexEntries.add(newLexEntry);
            } else if (RStichwort.matches("[a-zA-Z\\u00C0-\\u017F\\s]+\\[[a-zA-Z\\u00C0-\\u017F\\s]+, [a-zA-Z\\u00C0-\\u017F\\s]+\\]")) {
                // bietg [bighetg, baghetg]
                String[] split1 = RStichwort.split("\\[");
                String[] split2 = split1[1].split(", ");

                String lemma1 = split1[0].trim();
                String lemma2 = split2[0].trim();
                String lemma3 = split2[1].split("\\]")[0].trim();

                lemma2 = "[" + lemma2 + "]";
                lemma3 = "[" + lemma3 + "]";

                LemmaVersion newLemmaVersion1 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion2 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion3 = createNewLemmaVersion(entry);

                newLemmaVersion1.getLemmaValues().put("RStichwort", lemma1);
                newLemmaVersion2.getLemmaValues().put("RStichwort", lemma2);
                newLemmaVersion3.getLemmaValues().put("RStichwort", lemma3);

                entry.addLemma(newLemmaVersion1);
                LexEntry newLexEntry1 = createNewLexEntry(newLemmaVersion2);
                LexEntry newLexEntry2 = createNewLexEntry(newLemmaVersion3);

                lexEntries.add(entry);
                lexEntries.add(newLexEntry1);
                lexEntries.add(newLexEntry2);
            } else if(RStichwort.matches("\\[[a-zA-Z\\u00C0-\\u017F\\s]+, [a-zA-Z\\u00C0-\\u017F\\s]+\\]")) {
                // [batleni, bacleni]
                LemmaVersion newLemmaVersion1 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion2 = createNewLemmaVersion(entry);

                String[] split = RStichwort.split(", ");
                newLemmaVersion1.getLemmaValues().put("RStichwort", split[0].trim() + "]");
                newLemmaVersion2.getLemmaValues().put("RStichwort", "[" + split[1].trim());

                entry.addLemma(newLemmaVersion1);
                LexEntry newLexEntry = createNewLexEntry(newLemmaVersion2);

                lexEntries.add(entry);
                lexEntries.add(newLexEntry);
            } else if(RStichwort.matches("[a-zA-Z\\u00C0-\\u017F\\s]+ \\([a-zA-Z\\u00C0-\\u017F\\s]+\\)")) {
                // artetgel (artecel)
                String[] split = RStichwort.split("\\(");
                String lemma1 = split[0];
                String lemma2 = split[1].split("\\)")[0];

                LemmaVersion newLemmaVersion1 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion2 = createNewLemmaVersion(entry);

                newLemmaVersion1.getLemmaValues().put("RStichwort", lemma1);
                newLemmaVersion2.getLemmaValues().put("RStichwort", lemma2);

                entry.addLemma(newLemmaVersion1);
                LexEntry newLexEntry = createNewLexEntry(newLemmaVersion2);

                lexEntries.add(entry);
                lexEntries.add(newLexEntry);
            } else {
                LemmaVersion newLemmaVersion = createNewLemmaVersion(entry);

                entry.addLemma(newLemmaVersion);
                lexEntries.add(entry);
            }

            for (LexEntry le : lexEntries) {
                LemmaVersion mostRecent = le.getMostRecent();
                InflectionResponse inflectionResponse = null;
                try {
                    inflectionResponse = inflectionService.guessInflection(language, InflectionType.NOUN, mostRecent.getLemmaValues().get("RStichwort"), mostRecent.getLemmaValues().get("RGenus"), mostRecent.getLemmaValues().get("RFlex"));
                } catch (StringIndexOutOfBoundsException ex) {
                    continue;
                }
                if (inflectionResponse == null) {
                    continue;
                }

                for(Map.Entry<String, String> el : inflectionResponse.getInflectionValues().entrySet()) {
                    mostRecent.getLemmaValues().put(el.getKey(), el.getValue());
                }

                mostRecent.getLemmaValues().putIfAbsent("RGrammatik", "subst");
                mostRecent.getPgValues().put(LemmaVersion.AUTOMATIC_CHANGE, AutomaticChangesType.NOUNS.toString());
                mostRecent.getPgValues().put(LemmaVersion.REVIEW_LATER, "false");
                mostRecent.setVerification(LemmaVersion.Verification.UNVERIFIED);
                mostRecent.setStatus(LemmaVersion.Status.UNDEFINED);

                mostRecent.setTimestamp(0L);
                mostRecent.setUserId(null);

                try {
                    if (le.getId() == null) {
                        mongoDbService.insert(language, le, true);
                    } else {
                        mongoDbService.update(language, entry, mostRecent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
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

    private boolean updateAdjectivesByGrammarSutsilvan(Language language, String grammarValue) {
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

            String RStichwort = lemma.getLemmaValues().get("RStichwort");

            List<LexEntry> lexEntries = new ArrayList<>();
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

            if (RStichwort.startsWith("(")) {
                // (a)bundànt
                LemmaVersion newLemmaVersion = createNewLemmaVersion(entry);

                entry.addLemma(newLemmaVersion);
                lexEntries.add(entry);
            } else if (RStichwort.matches("[a-zA-Z\\u00C0-\\u017F\\s]+\\(a\\)")) {
                // amitg(a)
                LemmaVersion newLemmaVersion = createNewLemmaVersion(entry);

                entry.addLemma(newLemmaVersion);
                lexEntries.add(entry);
            } else if(RStichwort.matches("\\[[a-zA-Z\\u00C0-\\u017F\\s]+\\]")) {
                // [trid]
                LemmaVersion newLemmaVersion = createNewLemmaVersion(entry);

                entry.addLemma(newLemmaVersion);
                lexEntries.add(entry);
            } else if (RStichwort.matches("[a-zA-Z\\u00C0-\\u017F\\s]+\\[[a-zA-Z\\u00C0-\\u017F\\s]+\\]")) {
                // cumod [camod]
                LemmaVersion newLemmaVersion1 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion2 = createNewLemmaVersion(entry);

                String[] split = RStichwort.split("\\[");
                newLemmaVersion1.getLemmaValues().put("RStichwort", split[0].trim());
                newLemmaVersion2.getLemmaValues().put("RStichwort", split[1].trim().split("\\]")[0]);

                entry.addLemma(newLemmaVersion1);
                LexEntry newLexEntry = createNewLexEntry(newLemmaVersion2);

                lexEntries.add(entry);
                lexEntries.add(newLexEntry);
            } else if (RStichwort.matches("[a-zA-Z\\u00C0-\\u017F\\s]+\\[[a-zA-Z\\u00C0-\\u017F\\s]+, [a-zA-Z\\u00C0-\\u017F\\s]+\\]")) {
                // aviert [duviert, duvearta]
                String[] split1 = RStichwort.split("\\[");
                String[] split2 = split1[1].split(", ");

                String lemma1 = split1[0].trim();
                String lemma2 = split2[0].trim();
                String lemma3 = split2[1].split("\\]")[0].trim();

                lemma2 = "[" + lemma2 + "]";
                lemma3 = "[" + lemma3 + "]";

                LemmaVersion newLemmaVersion1 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion2 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion3 = createNewLemmaVersion(entry);

                newLemmaVersion1.getLemmaValues().put("RStichwort", lemma1);
                newLemmaVersion2.getLemmaValues().put("RStichwort", lemma2);
                newLemmaVersion3.getLemmaValues().put("RStichwort", lemma3);

                entry.addLemma(newLemmaVersion1);
                LexEntry newLexEntry1 = createNewLexEntry(newLemmaVersion2);
                LexEntry newLexEntry2 = createNewLexEntry(newLemmaVersion3);

                lexEntries.add(entry);
                lexEntries.add(newLexEntry1);
                lexEntries.add(newLexEntry2);
            } else {
                LemmaVersion newLemmaVersion = createNewLemmaVersion(entry);

                entry.addLemma(newLemmaVersion);
                lexEntries.add(entry);
            }

            for (LexEntry le : lexEntries) {
                LemmaVersion mostRecent = le.getMostRecent();
                InflectionResponse inflectionResponse = null;
                try {
                    inflectionResponse = inflectionService.guessInflection(language, InflectionType.ADJECTIVE, mostRecent.getLemmaValues().get("RStichwort"), mostRecent.getLemmaValues().get("RGenus"), mostRecent.getLemmaValues().get("RFlex"));
                } catch (StringIndexOutOfBoundsException ex) {
                    continue;
                }
                if (inflectionResponse == null) {
                    continue;
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
                    if (le.getId() == null) {
                        mongoDbService.insert(language, le, true);
                    } else {
                        mongoDbService.update(language, entry, mostRecent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
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

    private boolean updateVerbsByGrammarSutsilvan(Language language, String grammarValue) {
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

            List<LexEntry> lexEntries = new ArrayList<>();
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


            if (RStichwort.startsWith("(")) {
                // (an)cugliear -> leave like that, no split
                LemmaVersion newLemmaVersion = createNewLemmaVersion(entry);

                entry.addLemma(newLemmaVersion);
                lexEntries.add(entry);
            } else if(RStichwort.matches("\\[[a-zA-Z\\u00C0-\\u017F\\s]+\\([a-zA-Z\\u00C0-\\u017F\\s]+\\)\\]")) {
                // [runcar (rùnca)]
                LemmaVersion newLemmaVersion = createNewLemmaVersion(entry);

                String[] parts = RStichwort.split("\\(");
                if (entry.getCurrent().getEntryValue("RFlex") == null || entry.getCurrent().getEntryValue("RFlex").equals("")) {
                    newLemmaVersion.getLemmaValues().put("RFlex", parts[1].trim().substring(0, parts[1].trim().length() - 2));
                }
                newLemmaVersion.getLemmaValues().put("RStichwort", parts[0].trim() + "]");

                entry.addLemma(newLemmaVersion);
                lexEntries.add(entry);
            } else if(RStichwort.matches("\\[[a-zA-Z\\u00C0-\\u017F\\s]+\\]")) {
                // [stidar]
                LemmaVersion newLemmaVersion = createNewLemmaVersion(entry);

                entry.addLemma(newLemmaVersion);
                lexEntries.add(entry);
            } else if (RStichwort.matches("[a-zA-Z\\u00C0-\\u017F\\s]+\\([a-zA-Z\\u00C0-\\u017F\\s]+\\)[a-zA-Z\\u00C0-\\u017F\\s]+")) {
                // angi(a)vinar
                LemmaVersion newLemmaVersion = createNewLemmaVersion(entry);

                entry.addLemma(newLemmaVersion);
                lexEntries.add(entry);
            } else if (RStichwort.matches("[a-zA-Z\\u00C0-\\u017F\\s]+\\[[a-zA-Z\\u00C0-\\u017F\\s]+\\]")) {
                // atatgear [atacar]
                LemmaVersion newLemmaVersion1 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion2 = createNewLemmaVersion(entry);

                String[] split = RStichwort.split("\\[");
                newLemmaVersion1.getLemmaValues().put("RStichwort", split[0].trim());
                newLemmaVersion2.getLemmaValues().put("RStichwort", split[1].trim().split("\\]")[0]);

                entry.addLemma(newLemmaVersion1);
                LexEntry newLexEntry = createNewLexEntry(newLemmaVersion2);

                lexEntries.add(entry);
                lexEntries.add(newLexEntry);
            } else if(RStichwort.matches("[a-zA-Z\\u00C0-\\u017F\\s]+\\[[a-zA-Z\\u00C0-\\u017F\\s]+\\([a-zA-Z\\u00C0-\\u017F\\s]+\\)\\]")) {
                // balucar [bulacar (baluca)]
                LemmaVersion newLemmaVersion1 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion2 = createNewLemmaVersion(entry);

                String[] split = RStichwort.split("\\[");
                newLemmaVersion1.getLemmaValues().put("RStichwort", split[0].trim());
                String[] split2 = split[1].trim().split("\\(");
                newLemmaVersion2.getLemmaValues().put("RStichwort", "[" + split2[0].trim() + "]");
                newLemmaVersion2.getLemmaValues().put("RFlex", split2[1].split("\\)")[0].trim());

                entry.addLemma(newLemmaVersion1);
                LexEntry newLexEntry = createNewLexEntry(newLemmaVersion2);

                lexEntries.add(entry);
                lexEntries.add(newLexEntry);
            } else if (RStichwort.matches("[a-zA-Z\\u00C0-\\u017F\\s]+,[a-zA-Z\\u00C0-\\u017F\\s]+")) {
                // briglir, brigliear
                LemmaVersion newLemmaVersion1 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion2 = createNewLemmaVersion(entry);

                String[] split = RStichwort.split(",");
                newLemmaVersion1.getLemmaValues().put("RStichwort", split[0].trim());
                newLemmaVersion2.getLemmaValues().put("RStichwort", split[1].trim());

                entry.addLemma(newLemmaVersion1);
                LexEntry newLexEntry = createNewLexEntry(newLemmaVersion2);

                lexEntries.add(entry);
                lexEntries.add(newLexEntry);
            } else if (RStichwort.matches("[a-zA-Z\\u00C0-\\u017F\\s]+,[a-zA-Z\\u00C0-\\u017F\\s]+,[a-zA-Z\\u00C0-\\u017F\\s]+")) {
                // migear, migir, miger
                LemmaVersion newLemmaVersion1 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion2 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion3 = createNewLemmaVersion(entry);

                String[] split = RStichwort.split(",");
                newLemmaVersion1.getLemmaValues().put("RStichwort", split[0].trim());
                newLemmaVersion2.getLemmaValues().put("RStichwort", split[1].trim());
                newLemmaVersion3.getLemmaValues().put("RStichwort", split[2].trim());

                entry.addLemma(newLemmaVersion1);
                LexEntry newLexEntry1 = createNewLexEntry(newLemmaVersion2);
                LexEntry newLexEntry2 = createNewLexEntry(newLemmaVersion3);

                lexEntries.add(entry);
                lexEntries.add(newLexEntry1);
                lexEntries.add(newLexEntry2);
            } else if (RStichwort.matches("[a-zA-Z\\u00C0-\\u017F\\s]+, -[a-zA-Z\\u00C0-\\u017F\\s]+")) {
                // targlischear, -schar
                String[] split = RStichwort.split(", -");
                int idx = split[0].indexOf(split[1].charAt(0));

                String lemma1 = split[0];
                String lemma2 = split[0].substring(0, idx) + split[1];

                LemmaVersion newLemmaVersion1 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion2 = createNewLemmaVersion(entry);

                newLemmaVersion1.getLemmaValues().put("RStichwort", lemma1);
                newLemmaVersion2.getLemmaValues().put("RStichwort", lemma2);

                entry.addLemma(newLemmaVersion1);
                LexEntry newLexEntry = createNewLexEntry(newLemmaVersion2);

                lexEntries.add(entry);
                lexEntries.add(newLexEntry);
            } else if (RStichwort.matches("[a-zA-Z\\u00C0-\\u017F\\s]+, [a-zA-Z\\u00C0-\\u017F\\s]+\\([a-zA-Z\\u00C0-\\u017F\\s]+\\)[a-zA-Z\\u00C0-\\u017F\\s]+")) {
                // scursalar, sgu(r)salar
                // safigurar, safi(g)irar
                // figurar, fi(g)irar
                String[] split = RStichwort.split(", ");
                String lemma1 = split[0];
                String lemma2 = split[1];

                LemmaVersion newLemmaVersion1 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion2 = createNewLemmaVersion(entry);

                newLemmaVersion1.getLemmaValues().put("RStichwort", lemma1);
                newLemmaVersion2.getLemmaValues().put("RStichwort", lemma2);

                entry.addLemma(newLemmaVersion1);
                LexEntry newLexEntry = createNewLexEntry(newLemmaVersion2);

                lexEntries.add(entry);
                lexEntries.add(newLexEntry);
            } else if (RStichwort.equals("ratgavar [-cavar]")) {
                LemmaVersion newLemmaVersion = createNewLemmaVersion(entry);

                entry.addLemma(newLemmaVersion);
                lexEntries.add(entry);
            } else if(RStichwort.matches("[a-zA-Z\\u00C0-\\u017F\\s]+\\[-[a-zA-Z\\u00C0-\\u017F\\s]+\\]")) {
                // ragischear [-schar]
                // schragischear [-schar]
                String[] split = RStichwort.split("\\[-");
                int idx = split[0].indexOf(split[1].charAt(0));

                String lemma1 = split[0];
                String lemma2 = "[" + split[0].substring(0, idx) + split[1];

                LemmaVersion newLemmaVersion1 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion2 = createNewLemmaVersion(entry);

                newLemmaVersion1.getLemmaValues().put("RStichwort", lemma1);
                newLemmaVersion2.getLemmaValues().put("RStichwort", lemma2);

                entry.addLemma(newLemmaVersion1);
                LexEntry newLexEntry = createNewLexEntry(newLemmaVersion2);

                lexEntries.add(entry);
                lexEntries.add(newLexEntry);
            } else if(RStichwort.matches("[a-zA-Z\\u00C0-\\u017F\\s]+\\([a-zA-Z\\u00C0-\\u017F\\s]+\\)")) {
                // sbischar (sbischear)
                String[] split = RStichwort.split("\\(");
                String lemma1 = split[0];
                String lemma2 = split[1].split("\\)")[0];

                LemmaVersion newLemmaVersion1 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion2 = createNewLemmaVersion(entry);

                newLemmaVersion1.getLemmaValues().put("RStichwort", lemma1);
                newLemmaVersion2.getLemmaValues().put("RStichwort", lemma2);

                entry.addLemma(newLemmaVersion1);
                LexEntry newLexEntry = createNewLexEntry(newLemmaVersion2);

                lexEntries.add(entry);
                lexEntries.add(newLexEntry);
            } else if(RStichwort.matches("[a-zA-Z\\u00C0-\\u017F\\s]+\\[[a-zA-Z\\u00C0-\\u017F\\s]+, [a-zA-Z\\u00C0-\\u017F\\s]+\\-\\]")) {
                // biagear [bighigear, bagh-]
                String[] split1 = RStichwort.split("\\[");
                String[] split2 = split1[1].split(", ");

                String lemma1 = split1[0].trim();
                String lemma2 = split2[0].trim();
                String lemma3 = split2[1].split("-")[0].trim();

                int idx = lemma2.indexOf(lemma3.substring(lemma3.length() - 1));
                lemma3 = lemma3 + lemma2.substring(idx + 1, lemma2.length());

                lemma2 = "[" + lemma2 + "]";
                lemma3 = "[" + lemma3 + "]";

                LemmaVersion newLemmaVersion1 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion2 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion3 = createNewLemmaVersion(entry);

                newLemmaVersion1.getLemmaValues().put("RStichwort", lemma1);
                newLemmaVersion2.getLemmaValues().put("RStichwort", lemma2);
                newLemmaVersion3.getLemmaValues().put("RStichwort", lemma3);

                entry.addLemma(newLemmaVersion1);
                LexEntry newLexEntry1 = createNewLexEntry(newLemmaVersion2);
                LexEntry newLexEntry2 = createNewLexEntry(newLemmaVersion3);

                lexEntries.add(entry);
                lexEntries.add(newLexEntry1);
                lexEntries.add(newLexEntry2);
            } else if (RStichwort.matches("[a-zA-Z\\u00C0-\\u017F\\s]+\\[[a-zA-Z\\u00C0-\\u017F\\s]+, [a-zA-Z\\u00C0-\\u017F\\s]+\\]")) {
                // earver [duvrir, duvierer]
                String[] split1 = RStichwort.split("\\[");
                String[] split2 = split1[1].split(", ");

                String lemma1 = split1[0].trim();
                String lemma2 = split2[0].trim();
                String lemma3 = split2[1].split("\\]")[0].trim();

                lemma2 = "[" + lemma2 + "]";
                lemma3 = "[" + lemma3 + "]";

                LemmaVersion newLemmaVersion1 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion2 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion3 = createNewLemmaVersion(entry);

                newLemmaVersion1.getLemmaValues().put("RStichwort", lemma1);
                newLemmaVersion2.getLemmaValues().put("RStichwort", lemma2);
                newLemmaVersion3.getLemmaValues().put("RStichwort", lemma3);

                entry.addLemma(newLemmaVersion1);
                LexEntry newLexEntry1 = createNewLexEntry(newLemmaVersion2);
                LexEntry newLexEntry2 = createNewLexEntry(newLemmaVersion3);

                lexEntries.add(entry);
                lexEntries.add(newLexEntry1);
                lexEntries.add(newLexEntry2);
            } else if (RStichwort.matches("[a-zA-Z\\u00C0-\\u017F\\s]+ \\[\\([a-zA-Z\\u00C0-\\u017F\\s]+\\)[a-zA-Z\\u00C0-\\u017F\\s]+\\]")) {
                // stger [(d)astger]
                String[] split1 = RStichwort.split("\\[");

                String lemma1 = split1[0].trim();
                String lemma2 = split1[1].split("\\]")[0].trim();

                lemma2 = "[" + lemma2 + "]";

                LemmaVersion newLemmaVersion1 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion2 = createNewLemmaVersion(entry);

                newLemmaVersion1.getLemmaValues().put("RStichwort", lemma1);
                newLemmaVersion2.getLemmaValues().put("RStichwort", lemma2);

                entry.addLemma(newLemmaVersion1);
                LexEntry newLexEntry1 = createNewLexEntry(newLemmaVersion2);

                lexEntries.add(entry);
                lexEntries.add(newLexEntry1);
            } else if (RStichwort.matches("s'\\([a-zA-Z\\u00C0-\\u017F\\s]+\\)[a-zA-Z\\u00C0-\\u017F\\s]+")) {
                // s'(an)canuscher
                LemmaVersion newLemmaVersion = createNewLemmaVersion(entry);

                entry.addLemma(newLemmaVersion);
                lexEntries.add(entry);
            } else if (RStichwort.matches("s'[a-zA-Z\\u00C0-\\u017F\\s]+\\[[a-zA-Z\\u00C0-\\u017F\\s]+\\]")) {
                // s'ulzar [sadulzar]
                String[] split1 = RStichwort.split("\\[");

                String lemma1 = split1[0].trim();
                String lemma2 = split1[1].split("\\]")[0].trim();

                lemma2 = "[" + lemma2 + "]";

                LemmaVersion newLemmaVersion1 = createNewLemmaVersion(entry);
                LemmaVersion newLemmaVersion2 = createNewLemmaVersion(entry);

                newLemmaVersion1.getLemmaValues().put("RStichwort", lemma1);
                newLemmaVersion2.getLemmaValues().put("RStichwort", lemma2);

                entry.addLemma(newLemmaVersion1);
                LexEntry newLexEntry1 = createNewLexEntry(newLemmaVersion2);

                lexEntries.add(entry);
                lexEntries.add(newLexEntry1);
            } else {
                LemmaVersion newLemmaVersion = createNewLemmaVersion(entry);

                entry.addLemma(newLemmaVersion);
                lexEntries.add(entry);
            }

            for (LexEntry le : lexEntries) {
                LemmaVersion mostRecent = le.getMostRecent();
                /*
                InflectionResponse alreadyExistingInflection = null;
                if (lexEntries.size() == 1 && mostRecent.getLemmaValues().get("preschentsing3") != null && !mostRecent.getLemmaValues().get("preschentsing3").equals("")) {
                    alreadyExistingInflection = iterateOverAllInflectionsAndCompareValues(language, mostRecent);
                }
                */

                InflectionResponse inflectionResponse = null;
                try {
                    inflectionResponse = inflectionService.guessInflection(language, InflectionType.V, mostRecent.getLemmaValues().get("RStichwort"), mostRecent.getLemmaValues().get("RGenus"), mostRecent.getLemmaValues().get("RFlex"));
                } catch (StringIndexOutOfBoundsException ex) {
                    continue;
                }
                if (inflectionResponse == null) {
                    continue;
                }

                /*
                // check if verb has already conjugations
                boolean overwriteValues = true;
                if (le.getCurrent().getLemmaValues().get("preschentsing3") != null && !le.getCurrent().getLemmaValues().get("preschentsing3").equals("")) {
                    overwriteValues = areConjugationFormsEqual(inflectionResponse, mostRecent);
                }

                if (alreadyExistingInflection != null) {
                    for(Map.Entry<String, String> el : alreadyExistingInflection.getInflectionValues().entrySet()) {
                        mostRecent.getLemmaValues().put(el.getKey(), el.getValue());
                    }
                    copyMissingForms(inflectionResponse.getInflectionValues(), mostRecent.getLemmaValues());
                } else if (overwriteValues) {
                    for(Map.Entry<String, String> el : inflectionResponse.getInflectionValues().entrySet()) {
                        mostRecent.getLemmaValues().put(el.getKey(), el.getValue());
                    }
                } else {
                    mostRecent.getLemmaValues().put("infinitiv", inflectionResponse.getInflectionValues().get("infinitiv"));
                    mostRecent.getLemmaValues().put("RInflectionSubtype", inflectionResponse.getInflectionValues().get("RInflectionSubtype"));
                    mostRecent.getLemmaValues().put("RInflectionType", inflectionResponse.getInflectionValues().get("RInflectionType"));
                    mostRecent.getLemmaValues().put("RRegularInflection", "false");
                }
                */

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
                    if (le.getId() == null) {
                        mongoDbService.insert(language, le, true);
                    } else {
                        mongoDbService.update(language, entry, mostRecent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
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

    private InflectionResponse iterateOverAllInflectionsAndCompareValues(Language language, LemmaVersion lemma) {
        List<InflectionSubType> inflections = inflectionService.getInflectionTypes(language, InflectionType.V);

        for (InflectionSubType i : inflections) {
            try {
                String RStichwort = lemma.getLemmaValues().get("RStichwort");

                if (RStichwort.charAt(0) == '[') {
                    RStichwort = RStichwort.substring(1, RStichwort.length() - 1);
                }

                InflectionResponse r = inflectionService.generateInflection(language, InflectionType.V, i.id, RStichwort);
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

    private String mapGrammar(Language language, boolean isGerman, String oldGrammar) {
        if (language == Language.SURMIRAN) {
            if (isGerman) {
                return mapSurmiranGrammarD(oldGrammar);
            } else {
                return mapSurmiranGrammar(oldGrammar);
            }
        } else if (language == Language.VALLADER) {
            if (isGerman) {
                return mapValladerGrammarD(oldGrammar);
            } else {
                return mapValladerGrammarR(oldGrammar);
            }
        } else if (language == Language.PUTER) {
            if (isGerman) {
                return mapPuterGrammarD(oldGrammar);
            } else {
                return mapPuterGrammarR(oldGrammar);
            }
        }

        return oldGrammar;
    }

    private String mapGenus(Language language, boolean isGerman, String oldGenus) {
        if (language == Language.VALLADER) {
            if (isGerman) {
                return mapValladerGenusD(oldGenus);
            } else {
                return mapValladerGenusR(oldGenus);
            }
        } else if (language == Language.PUTER) {
            if (isGerman) {
                return mapPuterGenusD(oldGenus);
            } else {
                return mapPuterGenusR(oldGenus);
            }
        }

        return oldGenus;
    }

    private String mapSurmiranGrammar(String oldGrammar) {
        if ("I.".equals(oldGrammar)) return null;
        if ("I. Artikel bestimmt".equals(oldGrammar)) return "artetgel definit";
        if ("I. Artikel unbestimmt".equals(oldGrammar)) return "artetgel indefinit";
        if ("I. Pronomen demonstrativ".equals(oldGrammar)) return "pronom demonstrativ";
        if ("I. adverb".equals(oldGrammar)) return "adverb";
        if ("I. adverb interrogativ".equals(oldGrammar)) return "adverb";
        if ("I. artetgel".equals(oldGrammar)) return "artetgel";
        if ("I. artetgel definit".equals(oldGrammar)) return "artetgel definit";
        if ("I. artetgel indefinit".equals(oldGrammar)) return "artetgel indefinit";
        if ("I. conjuncziun".equals(oldGrammar)) return "conjuncziun";
        if ("I. impersunal".equals(oldGrammar)) return "pronom";
        if ("I. interjecziun".equals(oldGrammar)) return "interjecziun";
        if ("I. prep".equals(oldGrammar)) return "preposiziun";
        if ("I. prep/adverb".equals(oldGrammar)) return "preposiziun / adverb";
        if ("I. pronom".equals(oldGrammar)) return "pronom";
        if ("I. pronom demonstrativ".equals(oldGrammar)) return "pronom demonstrativ";
        if ("I. pronom demonstrativ impersunal".equals(oldGrammar)) return "pronom demonstrativ";
        if ("I. pronom demonstrativ neutr".equals(oldGrammar)) return "pronom demonstrativ";
        if ("I. pronom indefinit".equals(oldGrammar)) return "pronom indefinit";
        if ("I. pronom interrogativ".equals(oldGrammar)) return "pronom interrogativ";
        if ("I. pronom neutr".equals(oldGrammar)) return "pronom";
        if ("I. pronom numeral".equals(oldGrammar)) return null;
        if ("I. pronom persuna / persunal".equals(oldGrammar)) return "pronom persunal";
        if ("II.".equals(oldGrammar)) return null;
        if ("II. Verb modal".equals(oldGrammar)) return "verb modal";
        if ("II. adj".equals(oldGrammar)) return "adjectiv";
        if ("II. impersunal".equals(oldGrammar)) return "pronom";
        if ("II. interjecziun".equals(oldGrammar)) return "interjecziun";
        if ("II. numeral".equals(oldGrammar)) return "numeral";
        if ("II. numeral/pronom".equals(oldGrammar)) return "pronom";
        if ("II. prep".equals(oldGrammar)) return "preposiziun";
        if ("II. prep/adverb".equals(oldGrammar)) return "preposiziun / adverb";
        if ("II. pronom demonstrativ".equals(oldGrammar)) return "pronom demonstrativ";
        if ("II. pronom indefinit".equals(oldGrammar)) return "pronom indefinit";
        if ("II. pronom interrogativ".equals(oldGrammar)) return "pronom interrogativ";
        if ("II. pronom possessiv".equals(oldGrammar)) return "pronom";
        if ("II. pronom neutr".equals(oldGrammar)) return "pronom";
        if ("II. pronom persuna / persunal".equals(oldGrammar)) return "pronom";
        if ("II. pronom reflexiv".equals(oldGrammar)) return "pronom";
        if ("II. pronom relativ".equals(oldGrammar)) return "pronom relativ";
        if ("II.  reflexiv".equals(oldGrammar)) return "verb reflexiv";
        if ("II. tr".equals(oldGrammar)) return "tr";
        if ("II. tr indirect".equals(oldGrammar)) return "tr indirect";
        if ("III.".equals(oldGrammar)) return null;
        if ("III. adj".equals(oldGrammar)) return "adjectiv";
        if ("III. Pronomen unbestimmt".equals(oldGrammar)) return "pronom";
        if ("III. Pronomen/num".equals(oldGrammar)) return "pronom";
        if ("III. adv".equals(oldGrammar)) return "adverb";
        if ("III. adverb".equals(oldGrammar)) return "adverb";
        if ("III. adverb pronom".equals(oldGrammar)) return "adverb / pronom";
        if ("III. conjuncziun".equals(oldGrammar)) return "conjuncziun";
        if ("III. impersunal".equals(oldGrammar)) return "pronom";
        if ("III. int".equals(oldGrammar)) return "int";
        if ("III. int/tr".equals(oldGrammar)) return "int/tr";
        if ("III. interjecziun".equals(oldGrammar)) return "interjecziun";
        if ("III. prep".equals(oldGrammar)) return "preposiziun";
        if ("III. prep/conjuncziun".equals(oldGrammar)) return "preposiziun / conjuncziun";
        if ("III. pronom demonstrativ".equals(oldGrammar)) return "pronom demonstrativ";
        if ("III. pronom indefinit".equals(oldGrammar)) return "pronom indefinit";
        if ("III. pronom neutr".equals(oldGrammar)) return "pronom";
        if ("III. pronom reflexiv".equals(oldGrammar)) return "pronom";
        if ("III. pronom relativ".equals(oldGrammar)) return "pronom relativ";
        if ("III. tr indirect".equals(oldGrammar)) return "transitiv indirect";
        if ("IV.".equals(oldGrammar)) return null;
        if ("IV. adverb".equals(oldGrammar)) return "adverb";
        if ("IV. conjuncziun".equals(oldGrammar)) return "conjuncziun";
        if ("IV. Interjektion".equals(oldGrammar)) return "interjecziun";
        if ("IV. unpersönlich".equals(oldGrammar)) return "pronom";
        if ("V. interjecziun".equals(oldGrammar)) return "interjecziun";
        if ("Konjunktion".equals(oldGrammar)) return "conjuncziun";
        if ("Pronomen interrogativ".equals(oldGrammar)) return "pronom interrogativ";
        if ("Pronomen neutr".equals(oldGrammar)) return "pronom";
        if ("Pronomen possessiv".equals(oldGrammar)) return "pronom";
        if ("Pronomen/adj".equals(oldGrammar)) return "pronom / adjectiv";
        if ("Präposition".equals(oldGrammar)) return "preposiziun";
        if ("adj".equals(oldGrammar)) return "adjectiv";
        if ("adj demonstrativ".equals(oldGrammar)) return "adjectiv";
        if ("adj indefinit".equals(oldGrammar)) return "adjectiv";
        if ("adj interrogativ".equals(oldGrammar)) return "adjectiv";
        if ("adj numeral".equals(oldGrammar)) return "adjectiv";
        if ("adj possessiv".equals(oldGrammar)) return "adjectiv";
        if ("adj/pronom".equals(oldGrammar)) return "adjectiv / pronom";
        if ("adj/Pronomen".equals(oldGrammar)) return "adjectiv / pronom";
        if ("adj/adv".equals(oldGrammar)) return "adjectiv / adverb";
        if ("adj/adverb".equals(oldGrammar)) return "adjectiv / adverb";
        if ("adj/num".equals(oldGrammar)) return "adjectiv";
        if ("adj/numeral".equals(oldGrammar)) return "adjectiv";
        if ("adj/pronom indefinit".equals(oldGrammar)) return "adjectiv";
        if ("adj/pronom interrogativ".equals(oldGrammar)) return "pronom interrogativ";
        if ("adv".equals(oldGrammar)) return "adverb";
        if ("adverb pronom".equals(oldGrammar)) return "adverb";
        if ("adv relativ".equals(oldGrammar)) return "adverb";
        if ("adverb/conjuncziun".equals(oldGrammar)) return "adverb";
        if ("adv/Konjunktion".equals(oldGrammar)) return "adverb";
        if ("adv/Präposition".equals(oldGrammar)) return "adverb";
        if ("adverb".equals(oldGrammar)) return "adverb";
        if ("adverb/prep".equals(oldGrammar)) return "adverb";
        if ("cj".equals(oldGrammar)) return "conjuncziun";
        if ("conjuncziun".equals(oldGrammar)) return "conjuncziun";
        if ("impersunal".equals(oldGrammar)) return "verb";
        if ("int".equals(oldGrammar)) return "int";
        if ("int/impersunal".equals(oldGrammar)) return "int/impersunal";
        if ("int/reflexiv".equals(oldGrammar)) return "int/reflexiv";
        if ("int/tr".equals(oldGrammar)) return "int/tr";
        if ("int/unpersönlich".equals(oldGrammar)) return "int";
        if ("interj".equals(oldGrammar)) return "interjecziun";
        if ("interjecziun".equals(oldGrammar)) return "interjecziun";
        if ("num".equals(oldGrammar)) return "numeral";
        if ("numeral".equals(oldGrammar)) return "numeral";
        if ("num ord".equals(oldGrammar)) return "numeral";
        if ("nomen".equals(oldGrammar)) return "nomen";
        if ("part perfect/adj".equals(oldGrammar)) return null;
        if ("prep".equals(oldGrammar)) return "preposiziun";
        if ("preposiziun".equals(oldGrammar)) return "preposiziun";
        if ("prep+artetgel".equals(oldGrammar)) return "preposiziun / artetgel";
        if ("prep/conjuncziun".equals(oldGrammar)) return "preposiziun / conjuncziun";
        if ("pron".equals(oldGrammar)) return "pronom";
        if ("pronom".equals(oldGrammar)) return "pronom";
        if ("pronom demonstrativ".equals(oldGrammar)) return "pronom demonstrativ";
        if ("pronom indefinit".equals(oldGrammar)) return "pronom indefinit";
        if ("pronom interrogativ".equals(oldGrammar)) return "pronom interrogativ";
        if ("pronom neutr".equals(oldGrammar)) return "pronom";
        if ("pronom persuna / persunal".equals(oldGrammar)) return "pronom persunal";
        if ("pronom persuna / persunal indefinit".equals(oldGrammar)) return "pronom persunal";
        if ("pron Person / personal".equals(oldGrammar)) return "pronom persunal";
        if ("pronom reflexiv".equals(oldGrammar)) return "pronom";
        if ("pron reflexiv".equals(oldGrammar)) return "pronom";
        if ("pronom persuna / persunal 3a pl cumplemaint indirect".equals(oldGrammar)) return "pronom";
        if ("pronom persuna / persunal pl".equals(oldGrammar)) return "pronom persunal";
        if ("pronom possessiv".equals(oldGrammar)) return "pronom";
        if ("reflexiv".equals(oldGrammar)) return "reflexiv";
        if ("refl".equals(oldGrammar)) return "reflexiv";
        if ("subst".equals(oldGrammar)) return "nomen";
        if ("tr".equals(oldGrammar)) return "tr";
        if ("tr indirect".equals(oldGrammar)) return "tr indirect";
        if ("tr indirect/int".equals(oldGrammar)) return "tr";
        if ("tr/impersunal".equals(oldGrammar)) return "tr";
        if ("tr/int".equals(oldGrammar)) return "tr / int";
        if ("tr / int".equals(oldGrammar)) return "tr / int";
        if ("tr/int/Verb modal".equals(oldGrammar)) return "verb modal";
        if ("tr/tr indirect".equals(oldGrammar)) return "tr";
        if ("tr/verb modal".equals(oldGrammar)) return "verb modal";
        if ("".equals(oldGrammar)) return null;
        if (" ".equals(oldGrammar)) return null;

        return oldGrammar;
    }

    private String mapSurmiranGrammarD(String oldGrammar) {
        if ("adj".equals(oldGrammar)) return "adjectiv";
        if ("adj possessiv".equals(oldGrammar)) return "adjectiv";
        if ("adj/adv".equals(oldGrammar)) return "adjectiv / adverb";
        if ("adj/num".equals(oldGrammar)) return "adjectiv";
        if ("adj/Pronomen".equals(oldGrammar)) return "adjectiv / pronom";
        if ("adj/Pronomen possessiv".equals(oldGrammar)) return "adjectiv / pronom possessiv";
        if ("adv".equals(oldGrammar)) return "adverb";
        if ("adv Pronomen".equals(oldGrammar)) return "adverb / pronom";
        if ("adv relativ".equals(oldGrammar)) return "adverb";
        if ("adv/Konjunktion".equals(oldGrammar)) return "adverb";
        if ("adv/Präposition".equals(oldGrammar)) return "adverb";
        if ("Artikel bestimmt".equals(oldGrammar)) return "artetgel definit";
        if ("cj".equals(oldGrammar)) return "conjuncziun";
        if ("I.".equals(oldGrammar)) return null;
        if ("I. Artikel".equals(oldGrammar)) return "artetgel";
        if ("I. Artikel bestimmt".equals(oldGrammar)) return "artetgel definit";
        if ("I. Artikel unbestimmt".equals(oldGrammar)) return "artetgel indefinit";
        if ("I. Interjektion".equals(oldGrammar)) return "interjecziun";
        if ("I. Konjunktion".equals(oldGrammar)) return "conjuncziun";
        if ("I. num".equals(oldGrammar)) return "numeral";
        if ("I. Präposition".equals(oldGrammar)) return "preposiziun";
        if ("I. pron".equals(oldGrammar)) return "pronom";
        if ("I. Pronomen demonstrativ".equals(oldGrammar)) return "pronom demonstrativ";
        if ("I. Pronomen interrogativ".equals(oldGrammar)) return "pronom interrogativ";
        if ("II.".equals(oldGrammar)) return null;
        if ("II. adj".equals(oldGrammar)) return "adjectiv";
        if ("II. adj possessiv".equals(oldGrammar)) return "adjectiv";
        if ("II. adj/num".equals(oldGrammar)) return "adjectiv";
        if ("II. adj/Pronomen possessiv".equals(oldGrammar)) return "adjectiv";
        if ("II. Interjektion".equals(oldGrammar)) return "interjecziun";
        if ("II. Präposition".equals(oldGrammar)) return "preposiziun";
        if ("II. tr".equals(oldGrammar)) return "tr";
        if ("II. tr/int".equals(oldGrammar)) return "tr / int";
        if ("II. unpersönlich".equals(oldGrammar)) return "verb";
        if ("II. Verb modal".equals(oldGrammar)) return "verb modal";
        if ("III.".equals(oldGrammar)) return null;
        if ("III. adj".equals(oldGrammar)) return "adjectiv";
        if ("III. adv".equals(oldGrammar)) return "adverb";
        if ("III. int".equals(oldGrammar)) return "int";
        if ("III. int/tr".equals(oldGrammar)) return "int / tr";
        if ("III. Interjektion".equals(oldGrammar)) return "interjecziun";
        if ("III. Konjunktion".equals(oldGrammar)) return "conjuncziun";
        if ("III. Präposition".equals(oldGrammar)) return "preposiziun";
        if ("III. pron".equals(oldGrammar)) return "pronom";
        if ("III. Pronomen relativ".equals(oldGrammar)) return "pronom relativ";
        if ("III. Pronomen unbestimmt".equals(oldGrammar)) return "pronom indefinit";
        if ("III. Pronomen/num".equals(oldGrammar)) return "pronom";
        if ("III. unpersönlich".equals(oldGrammar)) return "verb";
        if ("int".equals(oldGrammar)) return "int";
        if ("int/reflexiv".equals(oldGrammar)) return "int";
        if ("int/tr".equals(oldGrammar)) return "int / tr";
        if ("int/unpersönlich".equals(oldGrammar)) return "int";
        if ("interj".equals(oldGrammar)) return "interjecziun";
        if ("Interjektion".equals(oldGrammar)) return "interjecziun";
        if ("IV.".equals(oldGrammar)) return null;
        if ("IV. Interjektion".equals(oldGrammar)) return "interjecziun";
        if ("IV. Pronomen unbestimmt".equals(oldGrammar)) return "pronom indefinit";
        if ("IV. unpersönlich".equals(oldGrammar)) return "verb";
        if ("Konjunktion".equals(oldGrammar)) return "conjuncziun";
        if ("num".equals(oldGrammar)) return "numeral";
        if ("num unbestimmt".equals(oldGrammar)) return "numeral";
        if ("Präposition".equals(oldGrammar)) return "preposiziun";
        if ("prep".equals(oldGrammar)) return "preposiziun";
        if ("pron".equals(oldGrammar)) return "pronom";
        if ("pron demonstrativ".equals(oldGrammar)) return "pronom demonstrativ";
        if ("pron Person / personal".equals(oldGrammar)) return "pronom persunal";
        if ("pron possessiv".equals(oldGrammar)) return "pronom";
        if ("pron reflexiv".equals(oldGrammar)) return "pronom";
        if ("pron relativ".equals(oldGrammar)) return "pronom relativ";
        if ("pron unbestimmt".equals(oldGrammar)) return "pronom indefinit";
        if ("Pronomen demonstrativ".equals(oldGrammar)) return "pronom demonstrativ";
        if ("Pronomen interrogativ".equals(oldGrammar)) return "pronom interrogativ";
        if ("Pronomen neutr".equals(oldGrammar)) return "pronom";
        if ("Pronomen possessiv".equals(oldGrammar)) return "pronom";
        if ("Pronomen reflexiv".equals(oldGrammar)) return "pronom";
        if ("Pronomen unbestimmt".equals(oldGrammar)) return "pronom indefinit";
        if ("Pronomen/adj".equals(oldGrammar)) return "pronom";
        if ("refl".equals(oldGrammar)) return "reflexiv";
        if ("tr".equals(oldGrammar)) return "tr";
        if ("tr/int".equals(oldGrammar)) return "tr / int";
        if ("tr/int/Verb modal".equals(oldGrammar)) return "verb modal";

        return oldGrammar;
    }

    private String mapValladerGrammarR(String oldGrammar) {
        if ("1".trim().equals(oldGrammar.trim())) return null;
        if ("2".trim().equals(oldGrammar.trim())) return null;
        if ("  ".trim().equals(oldGrammar.trim())) return null;
        if (" 1. + subst".trim().equals(oldGrammar.trim())) return "subst";
        if (" 1. interr".trim().equals(oldGrammar.trim())) return null;
        if (" 1. invar".trim().equals(oldGrammar.trim())) return null;
        if (" 1. sg".trim().equals(oldGrammar.trim())) return null;
        if (" adj".trim().equals(oldGrammar.trim())) return "adj";
        if (" adj ".trim().equals(oldGrammar.trim())) return "adj";
        if (" adj f".trim().equals(oldGrammar.trim())) return "adj.f";
        if (" adj fpl".trim().equals(oldGrammar.trim())) return "adj";
        if (" adj invar".trim().equals(oldGrammar.trim())) return "adj.inv";
        if (" adj invar/adv".trim().equals(oldGrammar.trim())) return "adj.inv/adv";
        if (" adj mp".trim().equals(oldGrammar.trim())) return "adj";
        if (" adj mpl".trim().equals(oldGrammar.trim())) return "adj";
        if (" adj poss".trim().equals(oldGrammar.trim())) return "adj./pron.poss";
        if (" adj pred invar".trim().equals(oldGrammar.trim())) return "adj.inv";
        if (" adj pred/adv".trim().equals(oldGrammar.trim())) return "adj.inv/adv";
        if (" adj/adv".trim().equals(oldGrammar.trim())) return "adj/adv";
        if (" adj/adv f".trim().equals(oldGrammar.trim())) return "adj/adv";
        if (" adj/pp".trim().equals(oldGrammar.trim())) return "adj";
        if (" adj/prep".trim().equals(oldGrammar.trim())) return "prep";
        if (" adv".trim().equals(oldGrammar.trim())) return "adv";
        if (" adv interr".trim().equals(oldGrammar.trim())) return "adv";
        if (" adv/adj".trim().equals(oldGrammar.trim())) return "adj/adv";
        if (" adv/cj".trim().equals(oldGrammar.trim())) return "adv/cj";
        if (" adv/interj".trim().equals(oldGrammar.trim())) return "adv";
        if (" adv/prep".trim().equals(oldGrammar.trim())) return "adv/prep";
        if (" art poss".trim().equals(oldGrammar.trim())) return "pron.poss";
        if (" art/adj".trim().equals(oldGrammar.trim())) return "pron.indef";
        if (" art/pron".trim().equals(oldGrammar.trim())) return "pron.poss";
        if (" art/pron dem".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" art/pron fpl".trim().equals(oldGrammar.trim())) return "pron.poss";
        if (" art/pron poss".trim().equals(oldGrammar.trim())) return "pron.poss";
        if (" be inf".trim().equals(oldGrammar.trim())) return "be inf";
        if (" cj".trim().equals(oldGrammar.trim())) return "cj";
        if (" cj/adv".trim().equals(oldGrammar.trim())) return "adv/cj";
        if (" ei Heirat)".trim().equals(oldGrammar.trim())) return null;
        if (" f".trim().equals(oldGrammar.trim())) return null;
        if (" gerundi".trim().equals(oldGrammar.trim())) return "grd";
        if (" I.".trim().equals(oldGrammar.trim())) return null;
        if (" I. adj".trim().equals(oldGrammar.trim())) return "adj";
        if (" I. adj indef".trim().equals(oldGrammar.trim())) return "pron.indef";
        if (" I. adj invar".trim().equals(oldGrammar.trim())) return "adj.inv";
        if (" I. adj/art".trim().equals(oldGrammar.trim())) return null;
        if (" I. adj/pron".trim().equals(oldGrammar.trim())) return "adj/pron.indef";
        if (" I. adv".trim().equals(oldGrammar.trim())) return "adv";
        if (" I. art/pron".trim().equals(oldGrammar.trim())) return null;
        if (" I. fsg".trim().equals(oldGrammar.trim())) return null;
        if (" I. interj".trim().equals(oldGrammar.trim())) return "interj";
        if (" I. interr".trim().equals(oldGrammar.trim())) return "pron.interr";
        if (" I. intr".trim().equals(oldGrammar.trim())) return "intr";
        if (" I. intr/tr".trim().equals(oldGrammar.trim())) return "tr/intr";
        if (" I. intr/tr ind".trim().equals(oldGrammar.trim())) return null;
        if (" I. num".trim().equals(oldGrammar.trim())) return "num";
        if (" I. num/adj".trim().equals(oldGrammar.trim())) return "num";
        if (" I. pp".trim().equals(oldGrammar.trim())) return "pp";
        if (" I. pred".trim().equals(oldGrammar.trim())) return "adj.";
        if (" I. prep".trim().equals(oldGrammar.trim())) return "prep";
        if (" I. pron ind".trim().equals(oldGrammar.trim())) return "pron.indef";
        if (" I. sg".trim().equals(oldGrammar.trim())) return null;
        if (" I. tr".trim().equals(oldGrammar.trim())) return "tr";
        if (" I. tr ind".trim().equals(oldGrammar.trim())) return "intr";
        if (" I. tr/intr".trim().equals(oldGrammar.trim())) return "tr/intr";
        if (" II.".trim().equals(oldGrammar.trim())) return null;
        if (" II. ".trim().equals(oldGrammar.trim())) return null;
        if (" II. adj".trim().equals(oldGrammar.trim())) return "adj";
        if (" II. adj f".trim().equals(oldGrammar.trim())) return "adj.f";
        if (" II. adj invar".trim().equals(oldGrammar.trim())) return "adj.inv";
        if (" II. adj pred".trim().equals(oldGrammar.trim())) return "adj.";
        if (" II. adj/adv".trim().equals(oldGrammar.trim())) return "adj/adv";
        if (" II. adv".trim().equals(oldGrammar.trim())) return "adv";
        if (" II. adv/prep".trim().equals(oldGrammar.trim())) return "prep";
        if (" II. cj".trim().equals(oldGrammar.trim())) return "cj";
        if (" II. fpl".trim().equals(oldGrammar.trim())) return null;
        if (" II. impers".trim().equals(oldGrammar.trim())) return "impers";
        if (" II. interj".trim().equals(oldGrammar.trim())) return "interj";
        if (" II. intr".trim().equals(oldGrammar.trim())) return "intr";
        if (" II. num/adj".trim().equals(oldGrammar.trim())) return "num";
        if (" II. o.i.".trim().equals(oldGrammar.trim())) return null;
        if (" II. pl".trim().equals(oldGrammar.trim())) return null;
        if (" II. pp".trim().equals(oldGrammar.trim())) return "pp";
        if (" II. pp mpl".trim().equals(oldGrammar.trim())) return "pp";
        if (" II. prep".trim().equals(oldGrammar.trim())) return "prep";
        if (" II. pron".trim().equals(oldGrammar.trim())) return "pron";
        if (" II. pron rel".trim().equals(oldGrammar.trim())) return "pron.rel";
        if (" II. refl".trim().equals(oldGrammar.trim())) return "refl";
        if (" II. rel".trim().equals(oldGrammar.trim())) return "refl";
        if (" II. tr".trim().equals(oldGrammar.trim())) return "tr";
        if (" II..".trim().equals(oldGrammar.trim())) return null;
        if (" III.".trim().equals(oldGrammar.trim())) return null;
        if (" III. adj".trim().equals(oldGrammar.trim())) return "adj";
        if (" III. adv".trim().equals(oldGrammar.trim())) return "adv";
        if (" impers".trim().equals(oldGrammar.trim())) return "impers";
        if (" impt pl".trim().equals(oldGrammar.trim())) return null;
        if (" impt sg".trim().equals(oldGrammar.trim())) return null;
        if (" int".trim().equals(oldGrammar.trim())) return "intr";
        if (" interj".trim().equals(oldGrammar.trim())) return "interj";
        if (" intr".trim().equals(oldGrammar.trim())) return "intr";
        if (" intr".trim().equals(oldGrammar.trim())) return "intr";
        if (" intr (tr)".trim().equals(oldGrammar.trim())) return "tr/intr";
        if (" intr/tr".trim().equals(oldGrammar.trim())) return "tr/intr";
        if (" intr/tr ind".trim().equals(oldGrammar.trim())) return "intr";
        if (" IV.".trim().equals(oldGrammar.trim())) return null;
        if (" IV. adj".trim().equals(oldGrammar.trim())) return "adj";
        if (" IV. adv".trim().equals(oldGrammar.trim())) return "adv";
        if (" IV. impers".trim().equals(oldGrammar.trim())) return "impers";
        if (" IV. interj".trim().equals(oldGrammar.trim())) return "interj";
        if (" m".trim().equals(oldGrammar.trim())) return null;
        if (" num/adj".trim().equals(oldGrammar.trim())) return "num";
        if (" num/adj ".trim().equals(oldGrammar.trim())) return "num";
        if (" pl".trim().equals(oldGrammar.trim())) return null;
        if (" pp".trim().equals(oldGrammar.trim())) return "pp";
        if (" pp invar".trim().equals(oldGrammar.trim())) return "pp";
        if (" pp mpl".trim().equals(oldGrammar.trim())) return "pp";
        if (" pp/adj".trim().equals(oldGrammar.trim())) return "pp";
        if (" ppf".trim().equals(oldGrammar.trim())) return "pp";
        if (" ppf pl".trim().equals(oldGrammar.trim())) return "pp";
        if (" ppmpl".trim().equals(oldGrammar.trim())) return "pp";
        if (" prep".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep+art".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep/adv".trim().equals(oldGrammar.trim())) return "adv/prep";
        if (" pron".trim().equals(oldGrammar.trim())) return "pron";
        if (" pron encl.".trim().equals(oldGrammar.trim())) return "pron";
        if (" pron f".trim().equals(oldGrammar.trim())) return "pron";
        if (" pron indef".trim().equals(oldGrammar.trim())) return "pron.indef";
        if (" pron invar".trim().equals(oldGrammar.trim())) return "pron.indef";
        if (" pron pers".trim().equals(oldGrammar.trim())) return "pron.pers";
        if (" pron poss".trim().equals(oldGrammar.trim())) return "pron.poss";
        if (" pron refl".trim().equals(oldGrammar.trim())) return "pron.refl";
        if (" pron+prep".trim().equals(oldGrammar.trim())) return "pron";
        if (" pron/adj".trim().equals(oldGrammar.trim())) return "adj/pron.indef";
        if (" pron/adj dem".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" refl".trim().equals(oldGrammar.trim())) return "refl";
        if (" sg".trim().equals(oldGrammar.trim())) return null;
        if (" tr".trim().equals(oldGrammar.trim())) return "tr";
        if (" tr".trim().equals(oldGrammar.trim())) return "tr";
        if (" tr ind".trim().equals(oldGrammar.trim())) return "intr";
        if (" tr/intr".trim().equals(oldGrammar.trim())) return "tr/intr";
        if (" tr/refl".trim().equals(oldGrammar.trim())) return "tr";
        if (" tr/v modal".trim().equals(oldGrammar.trim())) return "tr";
        if (" v defect".trim().equals(oldGrammar.trim())) return "intr";
        if (" v mod".trim().equals(oldGrammar.trim())) return "intr";
        if (" VI.".trim().equals(oldGrammar.trim())) return null;

        return oldGrammar;
    }

    private String mapValladerGrammarD(String oldGrammar) {
        if ("1".trim().equals(oldGrammar.trim())) return null;
        if ("2".trim().equals(oldGrammar.trim())) return null;
        if ("".trim().equals(oldGrammar.trim())) return null;
        if (" aadj".trim().equals(oldGrammar.trim())) return null;
        if (" adf".trim().equals(oldGrammar.trim())) return null;
        if (" adg".trim().equals(oldGrammar.trim())) return null;
        if (" adh".trim().equals(oldGrammar.trim())) return null;
        if (" adj".trim().equals(oldGrammar.trim())) return "adj";
        if (" adj attr".trim().equals(oldGrammar.trim())) return "adj";
        if (" adj invar".trim().equals(oldGrammar.trim())) return "adj.inv";
        if (" adj poss f".trim().equals(oldGrammar.trim())) return null;
        if (" adj poss m".trim().equals(oldGrammar.trim())) return null;
        if (" adj pred".trim().equals(oldGrammar.trim())) return "adj";
        if (" adj/adv".trim().equals(oldGrammar.trim())) return "adj/adv";
        if (" adj/num".trim().equals(oldGrammar.trim())) return "num";
        if (" adj/pp".trim().equals(oldGrammar.trim())) return "adj";
        if (" adj/pron".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" adv".trim().equals(oldGrammar.trim())) return "adv";
        if (" adv/cj".trim().equals(oldGrammar.trim())) return "adv/cj";
        if (" adv/prep".trim().equals(oldGrammar.trim())) return "adv/prep";
        if (" adv/prep gen".trim().equals(oldGrammar.trim())) return null;
        if (" art def".trim().equals(oldGrammar.trim())) return "art";
        if (" cj".trim().equals(oldGrammar.trim())) return "cj";
        if (" I.".trim().equals(oldGrammar.trim())) return null;
        if (" I. adj".trim().equals(oldGrammar.trim())) return "adj";
        if (" I. adj invar".trim().equals(oldGrammar.trim())) return "adj.inv";
        if (" I. adj/adv".trim().equals(oldGrammar.trim())) return "adj/adv";
        if (" I. adj/pron".trim().equals(oldGrammar.trim())) return "adj/pron.indef";
        if (" I. adv".trim().equals(oldGrammar.trim())) return "adv";
        if (" I. adv interr".trim().equals(oldGrammar.trim())) return null;
        if (" I. art/adj".trim().equals(oldGrammar.trim())) return null;
        if (" I. cj".trim().equals(oldGrammar.trim())) return null;
        if (" I. dem".trim().equals(oldGrammar.trim())) return null;
        if (" I. f".trim().equals(oldGrammar.trim())) return null;
        if (" I. interr".trim().equals(oldGrammar.trim())) return "pron.interr";
        if (" I. intr".trim().equals(oldGrammar.trim())) return "intr";
        if (" I. intr (tr)".trim().equals(oldGrammar.trim())) return null;
        if (" I. prep".trim().equals(oldGrammar.trim())) return "prep";
        if (" I. prep acc".trim().equals(oldGrammar.trim())) return null;
        if (" I. prep dat".trim().equals(oldGrammar.trim())) return null;
        if (" I. prep gen".trim().equals(oldGrammar.trim())) return null;
        if (" I. pron".trim().equals(oldGrammar.trim())) return "pron";
        if (" I. pron dem".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" I. pron pers".trim().equals(oldGrammar.trim())) return "pron.pers";
        if (" I. pron poss".trim().equals(oldGrammar.trim())) return "pron.poss";
        if (" I. rel gen".trim().equals(oldGrammar.trim())) return null;
        if (" I. sg f".trim().equals(oldGrammar.trim())) return null;
        if (" I. tr".trim().equals(oldGrammar.trim())) return "tr";
        if (" II.".trim().equals(oldGrammar.trim())) return null;
        if (" II. adj".trim().equals(oldGrammar.trim())) return "adj";
        if (" II. adj attr".trim().equals(oldGrammar.trim())) return "adj";
        if (" II. adj inv.".trim().equals(oldGrammar.trim())) return "adj.inv";
        if (" II. adj/adj".trim().equals(oldGrammar.trim())) return null;
        if (" II. adj/adv".trim().equals(oldGrammar.trim())) return "adj/adv";
        if (" II. adj/pron".trim().equals(oldGrammar.trim())) return "pron";
        if (" II. adv".trim().equals(oldGrammar.trim())) return "adv";
        if (" II. adv (prep)".trim().equals(oldGrammar.trim())) return "prep";
        if (" II. adv rel".trim().equals(oldGrammar.trim())) return "adv";
        if (" II. adv/adj".trim().equals(oldGrammar.trim())) return "adj/adv";
        if (" II. adv/cj".trim().equals(oldGrammar.trim())) return "cj";
        if (" II. adv/pron ind".trim().equals(oldGrammar.trim())) return "adv";
        if (" II. cj".trim().equals(oldGrammar.trim())) return "cj";
        if (" II. fpl".trim().equals(oldGrammar.trim())) return null;
        if (" II. indef".trim().equals(oldGrammar.trim())) return null;
        if (" II. interj".trim().equals(oldGrammar.trim())) return "interj";
        if (" II. intr".trim().equals(oldGrammar.trim())) return "intr";
        if (" II. intr (tr)".trim().equals(oldGrammar.trim())) return null;
        if (" II. m pl".trim().equals(oldGrammar.trim())) return null;
        if (" II. nom pl".trim().equals(oldGrammar.trim())) return null;
        if (" II. pl".trim().equals(oldGrammar.trim())) return null;
        if (" II. pl m e f".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" II. prep".trim().equals(oldGrammar.trim())) return "prep";
        if (" II. prep acc".trim().equals(oldGrammar.trim())) return null;
        if (" II. prep dat".trim().equals(oldGrammar.trim())) return null;
        if (" II. prep gen".trim().equals(oldGrammar.trim())) return null;
        if (" II. pron".trim().equals(oldGrammar.trim())) return "pron";
        if (" II. pron ref".trim().equals(oldGrammar.trim())) return "pron.refl";
        if (" II. ref".trim().equals(oldGrammar.trim())) return "refl";
        if (" II. refl".trim().equals(oldGrammar.trim())) return "refl";
        if (" II. rel".trim().equals(oldGrammar.trim())) return "refl";
        if (" II. tr".trim().equals(oldGrammar.trim())) return "tr";
        if (" III.".trim().equals(oldGrammar.trim())) return null;
        if (" III. adj".trim().equals(oldGrammar.trim())) return "adj";
        if (" III. adv".trim().equals(oldGrammar.trim())) return "adv";
        if (" III. cj".trim().equals(oldGrammar.trim())) return null;
        if (" III. dem".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" III. impers".trim().equals(oldGrammar.trim())) return null;
        if (" III. interj".trim().equals(oldGrammar.trim())) return null;
        if (" III. prep".trim().equals(oldGrammar.trim())) return null;
        if (" III. pron in".trim().equals(oldGrammar.trim())) return null;
        if (" impers".trim().equals(oldGrammar.trim())) return "impers";
        if (" interj".trim().equals(oldGrammar.trim())) return "interj";
        if (" intr".trim().equals(oldGrammar.trim())) return "intr";
        if (" intr (tr)".trim().equals(oldGrammar.trim())) return "tr/intr";
        if (" intr(tr)".trim().equals(oldGrammar.trim())) return "tr/intr";
        if (" intr/tr".trim().equals(oldGrammar.trim())) return "tr/intr";
        if (" IV. interj".trim().equals(oldGrammar.trim())) return "interj";
        if (" num".trim().equals(oldGrammar.trim())) return "num";
        if (" num/adj".trim().equals(oldGrammar.trim())) return "num";
        if (" num/pron".trim().equals(oldGrammar.trim())) return "art.indef";
        if (" nur präd.".trim().equals(oldGrammar.trim())) return "adj.inv";
        if (" pp".trim().equals(oldGrammar.trim())) return "pp";
        if (" pp f".trim().equals(oldGrammar.trim())) return "pp";
        if (" prep acc".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep acc/dat".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep dat".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep dat/acc".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep dat/gen".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep gen".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep gen/cj".trim().equals(oldGrammar.trim())) return "prep/cj";
        if (" prep gen/dat".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep+art".trim().equals(oldGrammar.trim())) return "prep";
        if (" pron".trim().equals(oldGrammar.trim())) return "pron";
        if (" pron dem".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" pron indef".trim().equals(oldGrammar.trim())) return "pron.indef";
        if (" pron interr".trim().equals(oldGrammar.trim())) return "pron.interr";
        if (" pron invar".trim().equals(oldGrammar.trim())) return "pron.indef";
        if (" pron pers".trim().equals(oldGrammar.trim())) return "pron.pers";
        if (" pron poss".trim().equals(oldGrammar.trim())) return "pron.poss";
        if (" pron/adj".trim().equals(oldGrammar.trim())) return "adj/pron.indef";
        if (" refl".trim().equals(oldGrammar.trim())) return "refl";
        if (" refl".trim().equals(oldGrammar.trim())) return "refl";
        if (" tr".trim().equals(oldGrammar.trim())) return "tr";
        if (" tr".trim().equals(oldGrammar.trim())) return "tr";
        if (" tr (refl)".trim().equals(oldGrammar.trim())) return "intr";
        if (" tr ind".trim().equals(oldGrammar.trim())) return "intr";
        if (" tr/intr".trim().equals(oldGrammar.trim())) return "tr/intr";
        if (" tr/refl".trim().equals(oldGrammar.trim())) return "tr";
        if (" V.".trim().equals(oldGrammar.trim())) return null;
        if (" verstossen540".trim().equals(oldGrammar.trim())) return "adj";

        return oldGrammar;
    }

    private String mapPuterGrammarR(String oldGrammar) {
        if ("".trim().equals(oldGrammar.trim())) return null;
        if (" adj".trim().equals(oldGrammar.trim())) return "adj";
        if (" adj f".trim().equals(oldGrammar.trim())) return "adj.f";
        if (" adj indef".trim().equals(oldGrammar.trim())) return "pron.indef";
        if (" adj invar".trim().equals(oldGrammar.trim())) return "adj.inv";
        if (" adj invar / num".trim().equals(oldGrammar.trim())) return "num";
        if (" adj invar/adv".trim().equals(oldGrammar.trim())) return "adj.inv";
        if (" adj m".trim().equals(oldGrammar.trim())) return "adj";
        if (" adj mpl".trim().equals(oldGrammar.trim())) return "adj";
        if (" adj pred f".trim().equals(oldGrammar.trim())) return "adj.f";
        if (" adj pred invar".trim().equals(oldGrammar.trim())) return "adj.inv";
        if (" adj pred invar / adv".trim().equals(oldGrammar.trim())) return "adj.inv";
        if (" adj/adv".trim().equals(oldGrammar.trim())) return "adj/adv";
        if (" adj/pp".trim().equals(oldGrammar.trim())) return "pp";
        if (" adj/pron dem pl f".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" adj/pron f".trim().equals(oldGrammar.trim())) return "adj.f";
        if (" adj/pron indef f".trim().equals(oldGrammar.trim())) return "adj.f";
        if (" adjf".trim().equals(oldGrammar.trim())) return "adj.f";
        if (" adv".trim().equals(oldGrammar.trim())) return "adv";
        if (" adv/cj".trim().equals(oldGrammar.trim())) return "adv/cj";
        if (" adv/interj".trim().equals(oldGrammar.trim())) return "adv";
        if (" adv/prep".trim().equals(oldGrammar.trim())) return "adv/prep";
        if (" art".trim().equals(oldGrammar.trim())) return "art";
        if (" cj".trim().equals(oldGrammar.trim())) return "cj";
        if (" cj/adv".trim().equals(oldGrammar.trim())) return "adv/cj";
        if (" I.".trim().equals(oldGrammar.trim())) return "– ";
        if (" I. adj".trim().equals(oldGrammar.trim())) return "adj";
        if (" I. adj invar".trim().equals(oldGrammar.trim())) return "adj.inv";
        if (" I. adj pred".trim().equals(oldGrammar.trim())) return "adj.inv";
        if (" I. adv".trim().equals(oldGrammar.trim())) return "adv";
        if (" I. cj".trim().equals(oldGrammar.trim())) return "cj";
        if (" I. impers".trim().equals(oldGrammar.trim())) return "impers";
        if (" I. interj".trim().equals(oldGrammar.trim())) return "interj";
        if (" I. interr".trim().equals(oldGrammar.trim())) return "pron.interr";
        if (" I. intr".trim().equals(oldGrammar.trim())) return "intr";
        if (" I. intr (tr)".trim().equals(oldGrammar.trim())) return "tr/intr";
        if (" I. intr / tr".trim().equals(oldGrammar.trim())) return "tr/intr";
        if (" I. num".trim().equals(oldGrammar.trim())) return "num";
        if (" I. num/adj".trim().equals(oldGrammar.trim())) return "num";
        if (" I. o.i.".trim().equals(oldGrammar.trim())) return "pron.pers";
        if (" I. prep".trim().equals(oldGrammar.trim())) return "prep";
        if (" I. pron".trim().equals(oldGrammar.trim())) return "pron";
        if (" I. tr".trim().equals(oldGrammar.trim())) return "tr";
        if (" I. tr ind".trim().equals(oldGrammar.trim())) return "intr";
        if (" II.".trim().equals(oldGrammar.trim())) return null;
        if (" II. adj".trim().equals(oldGrammar.trim())) return "adj";
        if (" II. adv".trim().equals(oldGrammar.trim())) return "adv";
        if (" II. adv/prep".trim().equals(oldGrammar.trim())) return "adv/prep";
        if (" II. cj".trim().equals(oldGrammar.trim())) return "cj";
        if (" II. interj".trim().equals(oldGrammar.trim())) return "interj";
        if (" II. intr".trim().equals(oldGrammar.trim())) return "intr";
        if (" II. num/adj".trim().equals(oldGrammar.trim())) return "num";
        if (" II. o.d.".trim().equals(oldGrammar.trim())) return "pron.pers";
        if (" II. ppf".trim().equals(oldGrammar.trim())) return "pp";
        if (" II. prep".trim().equals(oldGrammar.trim())) return "prep";
        if (" II. pron refl".trim().equals(oldGrammar.trim())) return "pron.refl";
        if (" II. pron rel pl".trim().equals(oldGrammar.trim())) return "pron.rel";
        if (" II. refl".trim().equals(oldGrammar.trim())) return "refl";
        if (" II. rel".trim().equals(oldGrammar.trim())) return "pron.interr";
        if (" II. tr".trim().equals(oldGrammar.trim())) return "tr";
        if (" II. tr ind".trim().equals(oldGrammar.trim())) return "intr";
        if (" III.".trim().equals(oldGrammar.trim())) return null;
        if (" III. adv".trim().equals(oldGrammar.trim())) return "adv";
        if (" III. cj".trim().equals(oldGrammar.trim())) return "cj";
        if (" III. impers".trim().equals(oldGrammar.trim())) return "impers";
        if (" III. interj".trim().equals(oldGrammar.trim())) return "interj";
        if (" III. prep".trim().equals(oldGrammar.trim())) return "prep";
        if (" III. refl".trim().equals(oldGrammar.trim())) return "refl";
        if (" III. tr".trim().equals(oldGrammar.trim())) return "tr";
        if (" impers".trim().equals(oldGrammar.trim())) return "impers";
        if (" interj".trim().equals(oldGrammar.trim())) return "interj";
        if (" intr".trim().equals(oldGrammar.trim())) return "intr";
        if (" intr".trim().equals(oldGrammar.trim())) return "intr";
        if (" intr (tr)".trim().equals(oldGrammar.trim())) return "tr/intr";
        if (" intr/tr".trim().equals(oldGrammar.trim())) return "tr/intr";
        if (" IV. adv".trim().equals(oldGrammar.trim())) return "adv";
        if (" mpl".trim().equals(oldGrammar.trim())) return "pron.poss";
        if (" num".trim().equals(oldGrammar.trim())) return "num";
        if (" num/adj".trim().equals(oldGrammar.trim())) return "num";
        if (" num/adj f".trim().equals(oldGrammar.trim())) return "num";
        if (" num/pron".trim().equals(oldGrammar.trim())) return "num";
        if (" pers 2. p pl".trim().equals(oldGrammar.trim())) return "pron.pers";
        if (" pl".trim().equals(oldGrammar.trim())) return "art";
        if (" pp".trim().equals(oldGrammar.trim())) return "pp";
        if (" ppf".trim().equals(oldGrammar.trim())) return "pp";
        if (" ppm".trim().equals(oldGrammar.trim())) return "pp";
        if (" prep".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep + la".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep/cj".trim().equals(oldGrammar.trim())) return "prep/cj";
        if (" prep/pron".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep+art".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep+art fpl".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep+art fsg".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep+art mpl".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep+art msg".trim().equals(oldGrammar.trim())) return "prep";
        if (" pron".trim().equals(oldGrammar.trim())) return "pron";
        if (" pron card".trim().equals(oldGrammar.trim())) return "num";
        if (" pron dem".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" pron indef".trim().equals(oldGrammar.trim())) return "pron.indef";
        if (" pron indef pl".trim().equals(oldGrammar.trim())) return "pron.indef";
        if (" pron indef pl f".trim().equals(oldGrammar.trim())) return "pron.indef";
        if (" pron interr".trim().equals(oldGrammar.trim())) return "pron.interr";
        if (" pron pers".trim().equals(oldGrammar.trim())) return "pron.pers";
        if (" pron pers / refl".trim().equals(oldGrammar.trim())) return "pron.pers/refl";
        if (" pron pers f".trim().equals(oldGrammar.trim())) return "pron.pers";
        if (" pron pers m sg".trim().equals(oldGrammar.trim())) return "pron.pers";
        if (" pron refl".trim().equals(oldGrammar.trim())) return "pron.pers/refl";
        if (" pron/adj".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" refl".trim().equals(oldGrammar.trim())) return "refl";
        if (" tr".trim().equals(oldGrammar.trim())) return "tr";
        if (" tr".trim().equals(oldGrammar.trim())) return "tr";
        if (" tr ind".trim().equals(oldGrammar.trim())) return "intr";
        if (" tr/refl".trim().equals(oldGrammar.trim())) return "tr";
        if (" v impers".trim().equals(oldGrammar.trim())) return "impers";
        if (" v intr".trim().equals(oldGrammar.trim())) return "intr";
        if (" v mod".trim().equals(oldGrammar.trim())) return "intr";
        if (" v mod defect".trim().equals(oldGrammar.trim())) return "intr";
        if (" V.".trim().equals(oldGrammar.trim())) return null;

        return oldGrammar;
    }

    private String mapPuterGrammarD(String oldGrammar) {
        if ("".trim().equals(oldGrammar.trim())) return null;
        if (" 3. p sg m".trim().equals(oldGrammar.trim())) return null;
        if (" adj".trim().equals(oldGrammar.trim())) return "adj";
        if (" adj (pred)".trim().equals(oldGrammar.trim())) return "adj";
        if (" adj f".trim().equals(oldGrammar.trim())) return "adj.f";
        if (" adj invar".trim().equals(oldGrammar.trim())) return "adj.inv";
        if (" adj pred".trim().equals(oldGrammar.trim())) return "adj";
        if (" adj pred/adv".trim().equals(oldGrammar.trim())) return "adj/adv";
        if (" adj/adv".trim().equals(oldGrammar.trim())) return "adj/adv";
        if (" adj/adv cump".trim().equals(oldGrammar.trim())) return "adj/adv";
        if (" adj/cj".trim().equals(oldGrammar.trim())) return "adv/cj";
        if (" adj/num".trim().equals(oldGrammar.trim())) return "num";
        if (" adj/pp".trim().equals(oldGrammar.trim())) return "pp";
        if (" adj/pron".trim().equals(oldGrammar.trim())) return "pron";
        if (" adj/pron dem".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" adj/pron dem fpl".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" adj/pron dem mpl".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" adj/pron indef".trim().equals(oldGrammar.trim())) return "adj/pron.indef";
        if (" adj/pron poss".trim().equals(oldGrammar.trim())) return "pron.poss";
        if (" adj/pron poss fpl".trim().equals(oldGrammar.trim())) return "pron.poss";
        if (" adv".trim().equals(oldGrammar.trim())) return "adv";
        if (" adv rel".trim().equals(oldGrammar.trim())) return "adv";
        if (" adv/cj".trim().equals(oldGrammar.trim())) return "adv/cj";
        if (" adv/prep gen".trim().equals(oldGrammar.trim())) return "adv/prep";
        if (" art gen sg".trim().equals(oldGrammar.trim())) return "art";
        if (" art indef f".trim().equals(oldGrammar.trim())) return "art.indef";
        if (" cj".trim().equals(oldGrammar.trim())) return "cj";
        if (" I.".trim().equals(oldGrammar.trim())) return null;
        if (" I. adj".trim().equals(oldGrammar.trim())) return "adj";
        if (" I. adj cump".trim().equals(oldGrammar.trim())) return "adj";
        if (" I. adj/adv".trim().equals(oldGrammar.trim())) return "adj/adv";
        if (" I. adj/pron".trim().equals(oldGrammar.trim())) return "adj/pron.indef";
        if (" I. adj/pron poss".trim().equals(oldGrammar.trim())) return "pron.poss";
        if (" I. adv".trim().equals(oldGrammar.trim())) return "adv";
        if (" I. art indef".trim().equals(oldGrammar.trim())) return "art.indef";
        if (" I. cj".trim().equals(oldGrammar.trim())) return "cj";
        if (" I. dem".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" I. dem nom sg".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" I. f".trim().equals(oldGrammar.trim())) return "pron.poss";
        if (" I. interr".trim().equals(oldGrammar.trim())) return "pron.interr";
        if (" I. intr".trim().equals(oldGrammar.trim())) return "intr";
        if (" I. pp".trim().equals(oldGrammar.trim())) return "pp";
        if (" I. prep".trim().equals(oldGrammar.trim())) return "prep";
        if (" I. prep acc".trim().equals(oldGrammar.trim())) return "prep";
        if (" I. prep dat".trim().equals(oldGrammar.trim())) return "prep";
        if (" I. prep gen".trim().equals(oldGrammar.trim())) return "prep";
        if (" I. pron dem".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" I. pron indef".trim().equals(oldGrammar.trim())) return "pron.indef";
        if (" I. pron pers".trim().equals(oldGrammar.trim())) return "pron.poss";
        if (" I. pron pers gen".trim().equals(oldGrammar.trim())) return "pron.poss";
        if (" I. pron/adj indef pl".trim().equals(oldGrammar.trim())) return "adj/pron.indef";
        if (" I. rel gen sg m".trim().equals(oldGrammar.trim())) return "pron.rel";
        if (" I. sg".trim().equals(oldGrammar.trim())) return "pron.rel";
        if (" I. tr".trim().equals(oldGrammar.trim())) return "tr";
        if (" II.".trim().equals(oldGrammar.trim())) return null;
        if (" II. adj".trim().equals(oldGrammar.trim())) return "adj";
        if (" II. adj attr".trim().equals(oldGrammar.trim())) return "adj";
        if (" II. adj pred".trim().equals(oldGrammar.trim())) return "adj";
        if (" II. adj/adv".trim().equals(oldGrammar.trim())) return "adj/adv";
        if (" II. adj/pron dem".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" II. adv".trim().equals(oldGrammar.trim())) return "adv";
        if (" II. adv rel".trim().equals(oldGrammar.trim())) return "adv";
        if (" II. adv/cj".trim().equals(oldGrammar.trim())) return "cj";
        if (" II. adv/pron indef".trim().equals(oldGrammar.trim())) return "adv";
        if (" II. cj".trim().equals(oldGrammar.trim())) return "cj";
        if (" II. indef".trim().equals(oldGrammar.trim())) return "pron.indef";
        if (" II. interj".trim().equals(oldGrammar.trim())) return "interj";
        if (" II. intr".trim().equals(oldGrammar.trim())) return "intr";
        if (" II. pl".trim().equals(oldGrammar.trim())) return "pron";
        if (" II. pl.".trim().equals(oldGrammar.trim())) return "pron.rel";
        if (" II. prep".trim().equals(oldGrammar.trim())) return "prep";
        if (" II. prep acc".trim().equals(oldGrammar.trim())) return "prep";
        if (" II. prep dat".trim().equals(oldGrammar.trim())) return "prep";
        if (" II. prep gen".trim().equals(oldGrammar.trim())) return "prep";
        if (" II. pron indef".trim().equals(oldGrammar.trim())) return "pron.indef";
        if (" II. pron refl".trim().equals(oldGrammar.trim())) return "pron.refl";
        if (" II. refl".trim().equals(oldGrammar.trim())) return "refl";
        if (" II. rel".trim().equals(oldGrammar.trim())) return "pron.interr";
        if (" II. subst".trim().equals(oldGrammar.trim())) return "adj";
        if (" II. tr".trim().equals(oldGrammar.trim())) return "tr";
        if (" III.".trim().equals(oldGrammar.trim())) return null;
        if (" III. adj".trim().equals(oldGrammar.trim())) return "adj";
        if (" III. adv".trim().equals(oldGrammar.trim())) return "adv";
        if (" III. cj".trim().equals(oldGrammar.trim())) return "cj";
        if (" III. dem".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" III. interj".trim().equals(oldGrammar.trim())) return "interj";
        if (" III. intr".trim().equals(oldGrammar.trim())) return "intr";
        if (" III. num/pron".trim().equals(oldGrammar.trim())) return "art.indef";
        if (" III. prep".trim().equals(oldGrammar.trim())) return "prep";
        if (" III. prep gen".trim().equals(oldGrammar.trim())) return "prep";
        if (" III. prep gen/dat".trim().equals(oldGrammar.trim())) return "prep";
        if (" III. pron indef".trim().equals(oldGrammar.trim())) return "pron.indef";
        if (" III. refl".trim().equals(oldGrammar.trim())) return "refl";
        if (" impers".trim().equals(oldGrammar.trim())) return "impers";
        if (" interj".trim().equals(oldGrammar.trim())) return "interj";
        if (" intr".trim().equals(oldGrammar.trim())) return "intr";
        if (" intr (tr)".trim().equals(oldGrammar.trim())) return "tr/intr";
        if (" IV. adv".trim().equals(oldGrammar.trim())) return "adv";
        if (" IV. interj".trim().equals(oldGrammar.trim())) return "adv";
        if (" num".trim().equals(oldGrammar.trim())) return "num";
        if (" pp".trim().equals(oldGrammar.trim())) return "pp";
        if (" prep".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep + art".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep acc".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep acc/dat".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep dat".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep dat/acc".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep gen".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep gen/cj".trim().equals(oldGrammar.trim())) return "prep/cj";
        if (" prep gen/dat".trim().equals(oldGrammar.trim())) return "prep";
        if (" prep/adv".trim().equals(oldGrammar.trim())) return "adv/prep";
        if (" prep+art".trim().equals(oldGrammar.trim())) return "prep";
        if (" pron".trim().equals(oldGrammar.trim())) return "pron";
        if (" pron dem gen pl".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" pron ind".trim().equals(oldGrammar.trim())) return "pron.indef";
        if (" pron indef".trim().equals(oldGrammar.trim())) return "pron.indef";
        if (" pron interr".trim().equals(oldGrammar.trim())) return "pron.interr";
        if (" pron interr gen".trim().equals(oldGrammar.trim())) return "pron.interr";
        if (" pron invar".trim().equals(oldGrammar.trim())) return "pron.indef";
        if (" pron pers".trim().equals(oldGrammar.trim())) return "pron.pers";
        if (" pron pers nom f".trim().equals(oldGrammar.trim())) return "pron.pers";
        if (" pron pers nom m".trim().equals(oldGrammar.trim())) return "pron.pers";
        if (" pron/adj".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" pron/adj dem".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" pron/adj dem dat".trim().equals(oldGrammar.trim())) return "pron.dem";
        if (" refl".trim().equals(oldGrammar.trim())) return "refl";
        if (" tr".trim().equals(oldGrammar.trim())) return "tr";
        if (" tr (refl)".trim().equals(oldGrammar.trim())) return "refl";
        if (" tr/intr".trim().equals(oldGrammar.trim())) return "tr/intr";
        if (" tr/refl".trim().equals(oldGrammar.trim())) return "tr";
        if (" V.".trim().equals(oldGrammar.trim())) return null;
        if ("n".trim().equals(oldGrammar.trim())) return "num";
        
        return oldGrammar;
    }

    private String mapPuterGenusR(String oldGrammar) {
        if (" fpl".trim().equals(oldGrammar.trim())) return "f.pl";
        if (" (m)".trim().equals(oldGrammar.trim())) return "m";
        if (" m/f".trim().equals(oldGrammar.trim())) return "m/f";
        if (" f".trim().equals(oldGrammar.trim())) return "f";
        if (" m,f".trim().equals(oldGrammar.trim())) return "m/f";
        if (" mpl".trim().equals(oldGrammar.trim())) return "m.pl";
        if (" fpl".trim().equals(oldGrammar.trim())) return "f.pl";
        if (" mpl".trim().equals(oldGrammar.trim())) return "m.pl";
        if (" m  ".trim().equals(oldGrammar.trim())) return "m";
        if (" m".trim().equals(oldGrammar.trim())) return "m";
        if (" f (fpl)".trim().equals(oldGrammar.trim())) return "f(pl)";
        if (" m/mpl".trim().equals(oldGrammar.trim())) return "m(pl)";
        if (" mpl,fpl".trim().equals(oldGrammar.trim())) return "m/fpl";
        if (" m,fpl".trim().equals(oldGrammar.trim())) return "m/fpl";
        if (" f ".trim().equals(oldGrammar.trim())) return "f";
        if (" tr".trim().equals(oldGrammar.trim())) return null;

        return oldGrammar;
    }

    private String mapPuterGenusD(String oldGrammar) {
        if (" n/f".trim().equals(oldGrammar.trim())) return "f/n";
        if (" f/n".trim().equals(oldGrammar.trim())) return "f/n";
        if (" m/f".trim().equals(oldGrammar.trim())) return "m/f";
        if (" f/m".trim().equals(oldGrammar.trim())) return "m/f";
        if (" m/n".trim().equals(oldGrammar.trim())) return "m/n";
        if (" n/m".trim().equals(oldGrammar.trim())) return "m/n";
        if (" f/m/n".trim().equals(oldGrammar.trim())) return "m/f/n";
        if (" f(pl)".trim().equals(oldGrammar.trim())) return "f";
        if (" fpl".trim().equals(oldGrammar.trim())) return "pl";
        if (" (n)".trim().equals(oldGrammar.trim())) return "n";
        if (" f".trim().equals(oldGrammar.trim())) return "f";
        if (" m,f".trim().equals(oldGrammar.trim())) return "m/f";
        if (" f,m".trim().equals(oldGrammar.trim())) return "m/f";
        if (" mpl".trim().equals(oldGrammar.trim())) return "pl";
        if (" npl".trim().equals(oldGrammar.trim())) return "pl";
        if (" m/pl".trim().equals(oldGrammar.trim())) return "pl";
        if (" m(f)".trim().equals(oldGrammar.trim())) return "m(f)";
        if (" m".trim().equals(oldGrammar.trim())) return "m";
        if (" n".trim().equals(oldGrammar.trim())) return "n";
        if (" m/n ".trim().equals(oldGrammar.trim())) return "m/n";
        if ("pl".trim().equals(oldGrammar.trim())) return "pl";

        return oldGrammar;
    }

    private String mapValladerGenusR(String oldGrammar) {
        if (" ff".trim().equals(oldGrammar.trim())) return "f";
        if (" ,m".trim().equals(oldGrammar.trim())) return "m";
        if (" (m)".trim().equals(oldGrammar.trim())) return "m";
        if (" m/f".trim().equals(oldGrammar.trim())) return "m/f";
        if (" f/m".trim().equals(oldGrammar.trim())) return "m/f";
        if (" f,".trim().equals(oldGrammar.trim())) return "f";
        if (" fm".trim().equals(oldGrammar.trim())) return "m";
        if (" M".trim().equals(oldGrammar.trim())) return "m";
        if (" m, f pl".trim().equals(oldGrammar.trim())) return "m/fpl";
        if (" m/fpl".trim().equals(oldGrammar.trim())) return "m/fpl";
        if (" m;pl".trim().equals(oldGrammar.trim())) return "m.pl";
        if (" mf".trim().equals(oldGrammar.trim())) return "m/fpl";
        if (" ml".trim().equals(oldGrammar.trim())) return "m";
        if (" m(pl)".trim().equals(oldGrammar.trim())) return "m(pl)";
        if (" fpl".trim().equals(oldGrammar.trim())) return "f.pl";
        if (" m.f".trim().equals(oldGrammar.trim())) return "m/f";
        if (" f".trim().equals(oldGrammar.trim())) return "f";
        if (" m,f".trim().equals(oldGrammar.trim())) return "m/f";
        if (" f,m".trim().equals(oldGrammar.trim())) return "m/f";
        if (" mfpl".trim().equals(oldGrammar.trim())) return "f.pl";
        if (" f  ".trim().equals(oldGrammar.trim())) return "f";
        if (" mpl".trim().equals(oldGrammar.trim())) return "m.pl";
        if (" m,pl".trim().equals(oldGrammar.trim())) return "m.pl";
        if (" (f)".trim().equals(oldGrammar.trim())) return "f";
        if (" m".trim().equals(oldGrammar.trim())) return "m";
        if (" f,fpl".trim().equals(oldGrammar.trim())) return "f(pl)";
        if (" f(m)".trim().equals(oldGrammar.trim())) return "f";
        if (" ,f".trim().equals(oldGrammar.trim())) return "f";
        if (" pl".trim().equals(oldGrammar.trim())) return "m.pl";
        if (" m,mpl".trim().equals(oldGrammar.trim())) return "m(pl)";
        if ("f pl".trim().equals(oldGrammar.trim())) return "f.pl";

        return oldGrammar;
    }

    private String mapValladerGenusD(String oldGrammar) {
        if (" ff".trim().equals(oldGrammar.trim())) return "f";
        if (" plüö".trim().equals(oldGrammar.trim())) return "pl";
        if (" n/f".trim().equals(oldGrammar.trim())) return "f/n";
        if (" f/n".trim().equals(oldGrammar.trim())) return "f/n";
        if (" m/f".trim().equals(oldGrammar.trim())) return "m/f";
        if (" f/m".trim().equals(oldGrammar.trim())) return "m/f";
        if (" m".trim().equals(oldGrammar.trim())) return "m";
        if (" pl".trim().equals(oldGrammar.trim())) return "pl";
        if (" m/n".trim().equals(oldGrammar.trim())) return "m/n";
        if (" n/m".trim().equals(oldGrammar.trim())) return "m/n";
        if (" N".trim().equals(oldGrammar.trim())) return "n";
        if (" m/n,f".trim().equals(oldGrammar.trim())) return "m/f/n";
        if (" m ".trim().equals(oldGrammar.trim())) return "m";
        if (" f;fpl".trim().equals(oldGrammar.trim())) return "m/fpl";
        if (" m/fpl".trim().equals(oldGrammar.trim())) return "m/fpl";
        if (" f/m/n".trim().equals(oldGrammar.trim())) return "m/f/n";
        if (" f coll".trim().equals(oldGrammar.trim())) return "f";
        if (" f (pl)".trim().equals(oldGrammar.trim())) return "f";
        if (" ^".trim().equals(oldGrammar.trim())) return "f";
        if (" m (-)".trim().equals(oldGrammar.trim())) return "m";
        if (" m/npl".trim().equals(oldGrammar.trim())) return "pl";
        if (" fpl".trim().equals(oldGrammar.trim())) return "pl";
        if (" m.f".trim().equals(oldGrammar.trim())) return "m/f";
        if (" d".trim().equals(oldGrammar.trim())) return "f";
        if (" f".trim().equals(oldGrammar.trim())) return "f";
        if (" m,f".trim().equals(oldGrammar.trim())) return "m/f";
        if (" f".trim().equals(oldGrammar.trim())) return "f";
        if ("m".trim().equals(oldGrammar.trim())) return "m";
        if (" h".trim().equals(oldGrammar.trim())) return "f";
        if (" m,pl".trim().equals(oldGrammar.trim())) return "pl";
        if (" npl".trim().equals(oldGrammar.trim())) return "pl";
        if (" (f)".trim().equals(oldGrammar.trim())) return "f";
        if (" m,f".trim().equals(oldGrammar.trim())) return "m/f";
        if (" ,".trim().equals(oldGrammar.trim()))  return null;;
        if (" m,,".trim().equals(oldGrammar.trim())) return "m";
        if (" m".trim().equals(oldGrammar.trim())) return "m";
        if (" f,pl".trim().equals(oldGrammar.trim())) return "pl";
        if (" n".trim().equals(oldGrammar.trim())) return "n";
        if (" m,n".trim().equals(oldGrammar.trim())) return "m/n";
        if (" f,fpl".trim().equals(oldGrammar.trim())) return "m/fpl";
        if (" m(n".trim().equals(oldGrammar.trim())) return "m";
        if (" t".trim().equals(oldGrammar.trim())) return "f";
        if (" m;f".trim().equals(oldGrammar.trim())) return "m/f";
        if (" ;".trim().equals(oldGrammar.trim()))  return null;;
        if (" pl".trim().equals(oldGrammar.trim())) return "pl";
        if (" m,f,n".trim().equals(oldGrammar.trim())) return "m/f/n";
        if (" m,n,f".trim().equals(oldGrammar.trim())) return "m/f/n";
        if ("f pl".trim().equals(oldGrammar.trim())) return "pl";

        return oldGrammar;
    }

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
