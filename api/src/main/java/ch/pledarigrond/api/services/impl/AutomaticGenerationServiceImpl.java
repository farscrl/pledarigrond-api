package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.dtos.VerbDto;
import ch.pledarigrond.api.services.AutomaticGenerationService;
import ch.pledarigrond.api.services.SursilvanVerbService;
import ch.pledarigrond.api.utils.InflectionResultDto;
import ch.pledarigrond.api.utils.SursilvanInflectionComparatorUtil;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.common.RequestContext;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.database.dictionary.entities.Entry;
import ch.pledarigrond.database.dictionary.mappers.LexEntryToEntryMapper;
import ch.pledarigrond.database.dictionary.repositories.EntryRepository;
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
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.mongodb.client.model.Filters.eq;

@Service
public class AutomaticGenerationServiceImpl implements AutomaticGenerationService {

    private static final Logger logger = LoggerFactory.getLogger(AutomaticGenerationServiceImpl.class);

    @Autowired
    private PgEnvironment pgEnvironment;

    @Autowired
    private SursilvanInflectionComparatorUtil sursilvanInflectionComparatorUtil;

    @Autowired
    private SursilvanVerbService sursilvanVerbService;

    @Autowired
    private EntryRepository entryRepository;

    @Override
    public boolean generateNounForms(Language language) {
        /*StopWatch watch = new StopWatch();
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
            Path exportPath = Paths.get(pgEnvironment.getExportLocation()).resolve(language.getName());
            Files.createDirectories(exportPath);
            Path csvOutputFile = exportPath.resolve("nouns-without-inflection.csv");
            try (PrintWriter pw = new PrintWriter(csvOutputFile.toFile())) {
                noInflectionList.stream()
                        .map(this::convertToCSV)
                        .forEach(pw::println);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        watch.stop();
        logger.info("Elapsed time: {}s", watch.getTotalTimeMillis()/1000);

         */
        return true;
    }

    @Override
    public boolean generateAdjectiveForms(Language language) {
        /* StopWatch watch = new StopWatch();
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
            Path exportPath = Paths.get(pgEnvironment.getExportLocation()).resolve(language.getName());
            Files.createDirectories(exportPath);
            Path csvOutputFile = exportPath.resolve("adjectives-without-inflection.csv");
            try (PrintWriter pw = new PrintWriter(csvOutputFile.toFile())) {
                noInflectionList.stream()
                        .map(this::convertToCSV)
                        .forEach(pw::println);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        watch.stop();
        logger.info("Elapsed time: {}s", watch.getTotalTimeMillis()/1000);

         */
        return true;
    }

    @Override
    public boolean generateVerbForms(Language language) {
        /* StopWatch watch = new StopWatch();
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

         */
        return true;
    }

    @Override
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

    @Override
    public boolean removeSubstIndicationIfGenusIsSet(Language language) throws DatabaseException, UnknownHostException {
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, language.getName()).getCollection("entries");

        int counter = 0;
        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            if ("subst".equals(entry.getCurrent().getLemmaValues().get("RGrammatik"))) {
                String genus = entry.getCurrent().getLemmaValues().get("RGenus");
                if (genus != null && !genus.isEmpty()) {
                    // logger.warn("stizzar: " + entry.getCurrent().getLemmaValues().get("RGrammatik") + " / " + entry.getCurrent().getLemmaValues().get("RGenus") + " (" + entry.getCurrent().getLemmaValues().get("RStichwort") + ")");
                    entry.getCurrent().getLemmaValues().put("RGrammatik", "");

                    BasicDBObject newObject = Converter.convertLexEntry(entry);
                    entryCollection.replaceOne(eq("_id", newObject.get("_id")),  new Document(newObject), new ReplaceOptions().upsert(true));

                    counter++;
                } else {
                    logger.error("na: " + entry.getCurrent().getLemmaValues().get("RStichwort"));
                }
            }

        }

        logger.error("number of cases: " + counter);

        return true;
    }

    @Override
    public String getVerbListWithConjugationClass(Language language) {
        List<VerbDto> sursilvanVerbs = sursilvanVerbService.getAllVerbs();

        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"Verb", "Class", "Accurracy"});

        for (VerbDto verb : sursilvanVerbs) {
            InflectionResultDto result = sursilvanInflectionComparatorUtil.getInflection(verb.getRmStichwort());
            InflectionDto response = result.getInflectionResponse();
            Integer errorCount = result.getErrorCount();
            data.add(new String[]{verb.getRmStichwort(), response != null ? response.getInflectionSubtype() : "-", errorCount != null ? errorCount.toString() : "222"});
        }

        data.sort(Comparator.comparing(o -> o[0]));

        StringWriter writer = new StringWriter();
        PrintWriter csvWriter = new PrintWriter(writer);
        for (String[] row : data) {
            csvWriter.println(String.join(",", row));
        }
        csvWriter.flush();

        return writer.toString();
    }

    @Override
    public boolean migrateDb() throws DatabaseException, UnknownHostException {
        if (entryRepository.count() != 0) {
            logger.error("Dictionary is not empty. Aborting.");
            return false;
        }

        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, RequestContext.getLanguage());
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, RequestContext.getLanguage().getName()).getCollection("entries");
        long entries = entryCollection.countDocuments();

        int counter = 0;
        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry lexEntry = Converter.convertToLexEntry(object);

            Entry entry = LexEntryToEntryMapper.map(lexEntry, RequestContext.getLanguage());
            entry.updateCalculatedFields();
            entryRepository.save(entry);
            counter++;

            if (counter % 1000 == 0) {
                logger.info("migrated {} entries ({}%)", counter, (counter * 100L) / entries);
            }
        }

        if (entries != entryRepository.count()) {
            logger.error("Number of entries in dictionary does not match number of entries in MongoDB. Aborting.");
            return false;
        }

        logger.info("number of LexEntries migrated: {}", counter);
        return true;
    }

    private boolean updateNounsByGender(String gender, List<String[]> noInflectionList) {
        /* SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setGender(gender);
        searchCriteria.setExcludeAutomaticChanged(true);
        searchCriteria.setSearchDirection(SearchDirection.ROMANSH);

        Pagination pagination = new Pagination();
        pagination.setPageSize(1000000);

        Page<EntryVersionDto> lemmas;
        try {
            lemmas = editorService.search(searchCriteria, pagination);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        for (int i = 0; i < lemmas.getContent().size(); i++) {
            EntryVersionDto version = lemmas.getContent().get(i);
            // logger.debug(version.toString());

            String id = version.getLexEntryId();
            String RStichwort = version.getRmStichwort();
            String DStichwort = version.getDeStichwort();

            Entry entry = null;
            try {
                entry = editorService.getEntry(version.getLexEntryId());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

            // ignore if version has already new version
            if (!entry.getUnapprovedVersions().isEmpty() || entry.getMostRecent().getPgValues().get(LemmaVersion.AUTOMATIC_CHANGE) != null) {
                continue;
            }

            // there are entries, that are not valid, as not all data is complete. this data has to be fixed here.
            // adding user ID
            if (entry.getCurrent().getUserId() == null || entry.getCurrent().getUserId().isEmpty()) {
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

            LemmaVersion newVersion = createNewLemmaVersion(entry);
            // entry.addLemma(newVersion);

            InflectionResponse inflectionResponse = null;
            try {
                inflectionResponse = inflectionService.guessInflection(language, InflectionType.NOUN, newVersion.getLemmaValues().get("RStichwort"), newVersion.getLemmaValues().get("RGenus"), newVersion.getLemmaValues().get("RFlex"));
            } catch (StringIndexOutOfBoundsException | NullPointerException ex) {
                noInflectionList.add(new String[]{ id, RStichwort, DStichwort, "exception" });
                continue;
            }
            if (inflectionResponse == null) {
                noInflectionList.add(new String[]{ id, RStichwort, DStichwort, "null" });
                continue;
            }

            for(Map.Entry<String, String> el : inflectionResponse.getInflectionValues().entrySet()) {
                newVersion.getLemmaValues().put(el.getKey(), el.getValue());
            }

            if (newVersion.getLemmaValues().get("RGrammatik") == null || "".equals(newVersion.getLemmaValues().get("RGrammatik"))) {
                newVersion.getLemmaValues().put("RGrammatik", "subst");
            }
            newVersion.getPgValues().put(LemmaVersion.AUTOMATIC_CHANGE, AutomaticChangesType.NOUNS.toString());
            newVersion.getPgValues().put(LemmaVersion.REVIEW_LATER, "false");
            newVersion.setVerification(LemmaVersion.Verification.UNVERIFIED);
            newVersion.setStatus(LemmaVersion.Status.NEW_MODIFICATION);

            newVersion.setTimestamp(0L);
            newVersion.setUserId(null);

            try {
                mongoDbService.update(language, entry, newVersion);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }*/

        return true;
    }

    private boolean updateAdjectivesByGrammar(Language language, String grammarValue, List<String[]> noInflectionList) {
        /* SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setGrammar(grammarValue);
        searchCriteria.setExcludeAutomaticChanged(true);
        searchCriteria.setSearchDirection(SearchDirection.ROMANSH);

        Pagination pagination = new Pagination();
        pagination.setPageSize(1000000);

        Page<EntryVersionDto> lemmas;
        try {
            lemmas = editorService.search(searchCriteria, pagination);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        for (int i = 0; i < lemmas.getContent().size(); i++) {
            EntryVersionDto lemma = lemmas.getContent().get(i);
            // logger.debug(lemma.toString());

            String id = lemma.getLexEntryId();
            String RStichwort = lemma.getLemmaValues().get("RStichwort");
            String DStichwort = lemma.getLemmaValues().get("DStichwort");

            Entry entry = null;
            try {
                entry = editorService.getEntry(lemma.getLexEntryId());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

            // ignore if lemma has already new version
            if (!entry.getUnapprovedVersions().isEmpty() || entry.getMostRecent().getPgValues().get(LemmaVersion.AUTOMATIC_CHANGE) != null) {
                continue;
            }

            // there are entries, that are not valid, as not all data is complete. this data has to be fixed here.
            if (entry.getCurrent().getUserId() == null || entry.getCurrent().getUserId().isEmpty()) {
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

            LemmaVersion newVersion = createNewLemmaVersion(entry);
            // entry.addLemma(newVersion);

            InflectionResponse inflectionResponse = null;
            try {
                inflectionResponse = inflectionService.guessInflection(language, InflectionType.ADJECTIVE, newVersion.getLemmaValues().get("RStichwort"), newVersion.getLemmaValues().get("RGenus"), newVersion.getLemmaValues().get("RFlex"));
            } catch (StringIndexOutOfBoundsException | NullPointerException ex) {
                noInflectionList.add(new String[]{ id, RStichwort, DStichwort, "exception" });
                continue;
            }
            if (inflectionResponse == null) {
                noInflectionList.add(new String[]{ id, RStichwort, DStichwort, "null" });
                continue;
            }

            for(Map.Entry<String, String> el : inflectionResponse.getInflectionValues().entrySet()) {
                newVersion.getLemmaValues().put(el.getKey(), el.getValue());
            }

            newVersion.getPgValues().put(LemmaVersion.AUTOMATIC_CHANGE, AutomaticChangesType.ADJECTIVES.toString());
            newVersion.getPgValues().put(LemmaVersion.REVIEW_LATER, "false");
            newVersion.setVerification(LemmaVersion.Verification.UNVERIFIED);
            newVersion.setStatus(LemmaVersion.Status.NEW_MODIFICATION);

            newVersion.setTimestamp(0L);
            newVersion.setUserId(null);

            try {
                mongoDbService.update(language, entry, newVersion);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } */

        return true;
    }

    private boolean updateVerbsByGrammar(Language language, String grammarValue) {
        /* SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setGrammar(grammarValue);
        searchCriteria.setExcludeAutomaticChanged(true);
        searchCriteria.setSearchDirection(SearchDirection.ROMANSH);

        Pagination pagination = new Pagination();
        pagination.setPageSize(1000000);

        Page<EntryVersionDto> lemmas;
        try {
            lemmas = editorService.search(searchCriteria, pagination);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        int correct = 0;
        int nuncorrect = 0;
        for (int i = 0; i < lemmas.getContent().size(); i++) {
            EntryVersionDto lemma = lemmas.getContent().get(i);
            logger.debug(lemma.toString());

            String RStichwort = lemma.getLemmaValues().get("RStichwort");

            Entry entry = null;
            try {
                entry = editorService.getEntry(lemma.getLexEntryId());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

            // ignore if lemma has already new version
            if (!entry.getUnapprovedVersions().isEmpty() || entry.getMostRecent().getPgValues().get(LemmaVersion.AUTOMATIC_CHANGE) != null) {
                continue;
            }

            // there are entries, that are not valid, as not all data is complete. this data has to be fixed here.
            if (entry.getCurrent().getUserId() == null || entry.getCurrent().getUserId().isEmpty()) {
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

            LemmaVersion newVersion = createNewLemmaVersion(entry);
            // entry.addLemma(newVersion);

            InflectionResultDto inflection = null;
            try {
                // inflectionResponse = inflectionService.guessInflection(language, InflectionType.V, newVersion.getLemmaValues().get("RStichwort"), newVersion.getLemmaValues().get("RGenus"), newVersion.getLemmaValues().get("RFlex"));
                inflection = sursilvanInflectionComparatorUtil.getInflection(newVersion.getLemmaValues().get("RStichwort"));
            } catch (StringIndexOutOfBoundsException | NullPointerException ex) {
                continue;
            }
            if (inflection.getInflectionResponse() == null) {
                continue;
            }


            for(Map.Entry<String, String> el : inflection.getInflectionResponse().getInflectionValues().entrySet()) {
                newVersion.getLemmaValues().put(el.getKey(), el.getValue());
            }

            newVersion.getPgValues().put(LemmaVersion.AUTOMATIC_CHANGE, AutomaticChangesType.VERBS.toString());
            newVersion.getPgValues().put(LemmaVersion.REVIEW_LATER, "false");
            if (inflection.isCorrect()) {
                correct++;
                newVersion.setVerification(LemmaVersion.Verification.ACCEPTED);
            } else {
                nuncorrect++;
                newVersion.setVerification(LemmaVersion.Verification.UNVERIFIED);
            }
            newVersion.setStatus(LemmaVersion.Status.NEW_MODIFICATION);

            newVersion.setTimestamp(0L);
            newVersion.setUserId(null);

            try {
                if (inflection.isCorrect()) {
                    mongoDbService.acceptAfterUpdate(language, entry, entry.getCurrent(), newVersion);
                } else {
                    mongoDbService.update(language, entry, newVersion);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        logger.warn("Generated verbs for grammar value: " + grammarValue + ". Correct: " + correct + " / Not correct: " + nuncorrect);

         */
        return true;
    }

    public static List<String> getGenderValues(Language language) {
        return Stream.of(
                "m",
                "f",
                "m.pl",
                "f.pl",
                "pl",
                "f.coll"
        ).collect(Collectors.toList());
    }
    
    public static List<String> getGrammarValuesForAdjective(Language language) {
        return Stream.of(
                "adj"
        ).collect(Collectors.toList());
    }

    public static List<String> getGrammarValuesForVerbs(Language language) {
        return Stream.of(
                "tr",
                "intr"
                ).collect(Collectors.toList());
    }

    private LemmaVersion createNewLemmaVersion(LexEntry entry) {
        LemmaVersion newVersion = new LemmaVersion();
        newVersion.getLemmaValues().putAll(entry.getCurrent().getLemmaValues());
        newVersion.getPgValues().putAll(entry.getCurrent().getPgValues());
        newVersion.setVerification(LemmaVersion.Verification.UNVERIFIED);
        newVersion.setTimestamp(System.currentTimeMillis());
        return newVersion;
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
