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
import ch.pledarigrond.inflection.generation.rumantschgrischun.RumantschGrischunAdjectiveGenerator;
import ch.pledarigrond.inflection.generation.rumantschgrischun.RumantschGrischunConjugation;
import ch.pledarigrond.inflection.generation.surmiran.SurmiranConjugation;
import ch.pledarigrond.inflection.generation.surmiran.SurmiranConjugationClasses;
import ch.pledarigrond.inflection.generation.surmiran.SurmiranConjugationStructure;
import ch.pledarigrond.inflection.generation.sutsilvan.SutsilvanConjugationStructure;
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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ch.pledarigrond.common.data.common.LemmaVersion.RM_INFLECTION_SUBTYPE;
import static ch.pledarigrond.common.data.common.LemmaVersion.RM_INFLECTION_TYPE;
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

        List<String> genders = getGenderValues();
        for (String gender : genders) {
            boolean success = updateNounsByGender(language, gender);
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
            boolean success = updateAdjectivesByGrammar(language, grammarValue);
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

        Set<String> rGrammatik = new HashSet<>();
        Set<String> dGrammatik = new HashSet<>();


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
                    String newGrammatik = mapSurmiranGrammar(RGrammatik);
                    if (newGrammatik == null || !newGrammatik.equals(RGrammatik)) {
                        current.getLemmaValues().put("RGrammatik", newGrammatik);
                        RGrammatik = newGrammatik;
                        didChange = true;
                    }
                }

                if (DGrammatik != null && !"".equals(DGrammatik)) {
                    String newGrammatik = mapSurmiranGrammarD(DGrammatik);
                    if (newGrammatik == null || !newGrammatik.equals(DGrammatik)) {
                        current.getLemmaValues().put("DGrammatik", newGrammatik);
                        DGrammatik = newGrammatik;
                        didChange = true;
                    }
                }

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

                rGrammatik.add(RGrammatik);
                dGrammatik.add(DGrammatik);

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

        logger.warn("RGrammatik: " + rGrammatik.toString());
        logger.warn("DGrammatik: " + dGrammatik.toString());

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
                logger.warn(entry.getId() + ": " + entry.getVersionHistory().size() + " entries (" + internalIds.toString() + "). Current ID: " + entry.getCurrentId() + ". " + entry.getVersionHistory().get(0).getLemmaValues().get("RStichwort") + "/" + entry.getVersionHistory().get(0).getLemmaValues().get("DStichwort"));
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

    private boolean updateNounsByGender(Language language, String gender) {
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

    private boolean updateAdjectivesByGrammar(Language language, String grammarValue) {
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

    public static List<String> getGenderValues() {
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
    
    public static List<String> getGrammarValuesForAdjective() {
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

    public static List<String> getGrammarValuesForVerbs() {
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
}
