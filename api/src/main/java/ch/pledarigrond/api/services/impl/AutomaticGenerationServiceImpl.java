package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.dtos.VerbDto;
import ch.pledarigrond.api.services.AutomaticGenerationService;
import ch.pledarigrond.api.services.EditorService;
import ch.pledarigrond.api.services.InflectionService;
import ch.pledarigrond.api.services.SursilvanVerbService;
import ch.pledarigrond.api.utils.InflectionResultDto;
import ch.pledarigrond.api.utils.SursilvanInflectionComparatorUtil;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.database.dictionary.entities.Entry;
import ch.pledarigrond.database.dictionary.mappers.LexEntryToEntryMapper;
import ch.pledarigrond.database.dictionary.repositories.EntryRepository;
import ch.pledarigrond.database.services.DictionaryService;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.mongodb.util.MongoHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
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
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AutomaticGenerationServiceImpl implements AutomaticGenerationService {

    private static final Logger logger = LoggerFactory.getLogger(AutomaticGenerationServiceImpl.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // Configure pretty printing for readability in the JSON file
        OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Autowired
    private PgEnvironment pgEnvironment;

    @Autowired
    private SursilvanInflectionComparatorUtil sursilvanInflectionComparatorUtil;

    @Autowired
    private SursilvanVerbService sursilvanVerbService;

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private EditorService editorService;

    @Autowired
    private InflectionService inflectionService;

    @Override
    public boolean generateNounForms() {
        StopWatch watch = new StopWatch();
        watch.start();

        List<String[]> noInflectionList = new ArrayList<>();

        List<String> genders = getGenderValues();
        for (String gender : genders) {
            boolean success = updateNounsByGender(gender, noInflectionList);
            if (!success) {
                return false;
            }
        }

        try {
            Path exportPath = Paths.get(pgEnvironment.getExportLocation()).resolve(RequestContext.getLanguage().getName());
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

        return true;
    }

    @Override
    public boolean generateAdjectiveForms() {
        StopWatch watch = new StopWatch();
        watch.start();

        List<String[]> noInflectionList = new ArrayList<>();

        List<String> grammarValuesForAdjective = getGrammarValuesForAdjective();
        for (String grammarValue : grammarValuesForAdjective) {
            boolean success = updateAdjectivesByGrammar(grammarValue, noInflectionList);
            if (!success) {
                return false;
            }
        }


        try {
            Path exportPath = Paths.get(pgEnvironment.getExportLocation()).resolve(RequestContext.getLanguage().getName());
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

        return true;
    }

    @Override
    public boolean generateVerbForms() {
        StopWatch watch = new StopWatch();
        watch.start();

        List<String> grammarValuesForVerbs = getGrammarValuesForVerbs();
        for (String grammarValue : grammarValuesForVerbs) {
            boolean success = updateVerbsByGrammar(grammarValue);
            if (!success) {
                return false;
            }
        }

        watch.stop();
        logger.info("Elapsed time: {}s", watch.getTotalTimeMillis()/1000);

        return true;
    }

    @Override
    public String getVerbListWithConjugationClass() {
        List<VerbDto> sursilvanVerbs = sursilvanVerbService.getAllVerbs();

        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"Verb", "Class", "Accurracy"});

        for (VerbDto verb : sursilvanVerbs) {
            InflectionResultDto result = sursilvanInflectionComparatorUtil.getInflection(verb.getRmStichwort());
            InflectionDto response = result.getInflectionResponse();
            Integer errorCount = result.getErrorCount();
            data.add(new String[]{verb.getRmStichwort(), response != null ? response.getVerb().getInflectionSubtype() : "-", errorCount != null ? errorCount.toString() : "222"});
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
    public boolean migrateDb() throws IOException {
        if (entryRepository.count() != 0) {
            logger.error("Dictionary is not empty. Aborting.");
            return false;
        }

        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, RequestContext.getLanguage());
        MongoCursor<Document> cursor = Database.getInstance(dbName, pgEnvironment).getAll();
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, RequestContext.getLanguage().getName()).getCollection("entries");
        long entries = entryCollection.countDocuments();

        int counter = 0;
        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry lexEntry = Converter.convertToLexEntry(object);
            // uncomment the following line to write the LexEntry to a file (to use in tests)
            serializeLexEntryAndSave(lexEntry, RequestContext.getLanguage());

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
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setGender(gender);
        searchCriteria.setExcludeAutomaticChanged(true);
        searchCriteria.setSearchDirection(SearchDirection.ROMANSH);

        Pagination pagination = new Pagination();
        pagination.setPageSize(1000000);

        Page<EntryVersionDto> versions;
        try {
            versions = editorService.search(searchCriteria, pagination);
        } catch (Exception e) {
            logger.error("Error while searching for versions", e);
            return false;
        }

        for (int i = 0; i < versions.getContent().size(); i++) {
            EntryVersionDto version = versions.getContent().get(i);
            // logger.debug(version.toString());

            String id = version.getEntryId();
            String RStichwort = version.getRmStichwort();
            String DStichwort = version.getDeStichwort();

            EntryDto entry;
            try {
                entry = editorService.getEntry(version.getEntryId());
            } catch (Exception e) {
                logger.error("Error while searching for entry", e);
                return false;
            }

            // ignore if version has already new version
            if (!entry.getSuggestions().isEmpty() || entry.getCurrent().isAutomaticChange()) {
                continue;
            }

            EntryVersionDto newVersion = cloneVersion(entry.getCurrent());

            InflectionDto inflectionResponse;
            try {
                inflectionResponse = inflectionService.guessInflection(RequestContext.getLanguage(), InflectionType.NOUN, newVersion.getRmStichwort(), newVersion.getRmGenus(), newVersion.getRmFlex());
            } catch (StringIndexOutOfBoundsException | NullPointerException ex) {
                noInflectionList.add(new String[]{ id, RStichwort, DStichwort, "exception" });
                continue;
            }
            if (inflectionResponse == null) {
                noInflectionList.add(new String[]{ id, RStichwort, DStichwort, "null" });
                continue;
            }

            newVersion.setInflection(inflectionResponse);
            newVersion.setAutomaticChange(true);
            dictionaryService.addVersion(entry.getEntryId(), newVersion, true, UserInfoDto.getSystemDto());
        }

        return true;
    }

    private boolean updateAdjectivesByGrammar(String grammarValue, List<String[]> noInflectionList) {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setGrammar(grammarValue);
        searchCriteria.setExcludeAutomaticChanged(true);
        searchCriteria.setSearchDirection(SearchDirection.ROMANSH);

        Pagination pagination = new Pagination();
        pagination.setPageSize(1000000);

        Page<EntryVersionDto> versions;
        try {
            versions = editorService.search(searchCriteria, pagination);
        } catch (Exception e) {
            logger.error("Error while searching for versions", e);
            return false;
        }

        for (int i = 0; i < versions.getContent().size(); i++) {
            EntryVersionDto version = versions.getContent().get(i);

            String id = version.getEntryId();
            String RStichwort = version.getRmStichwort();
            String DStichwort = version.getDeStichwort();

            EntryDto entry;
            try {
                entry = editorService.getEntry(version.getEntryId());
            } catch (Exception e) {
                logger.error("Error while searching for entry", e);
                return false;
            }

            // ignore if version has already new version
            if (!entry.getSuggestions().isEmpty() || entry.getCurrent().isAutomaticChange()) {
                continue;
            }

            EntryVersionDto newVersion = cloneVersion(entry.getCurrent());

            InflectionDto inflectionResponse;
            try {
                inflectionResponse = inflectionService.guessInflection(RequestContext.getLanguage(), InflectionType.ADJECTIVE, newVersion.getRmStichwort(), newVersion.getRmGenus(), newVersion.getRmFlex());
            } catch (StringIndexOutOfBoundsException | NullPointerException ex) {
                noInflectionList.add(new String[]{ id, RStichwort, DStichwort, "exception" });
                continue;
            }
            if (inflectionResponse == null) {
                noInflectionList.add(new String[]{ id, RStichwort, DStichwort, "null" });
                continue;
            }

            newVersion.setInflection(inflectionResponse);
            newVersion.setAutomaticChange(true);
            dictionaryService.addVersion(entry.getEntryId(), newVersion, true, UserInfoDto.getSystemDto());
        }

        return true;
    }

    private boolean updateVerbsByGrammar(String grammarValue) {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setGrammar(grammarValue);
        searchCriteria.setExcludeAutomaticChanged(true);
        searchCriteria.setSearchDirection(SearchDirection.ROMANSH);

        Pagination pagination = new Pagination();
        pagination.setPageSize(1000000);

        Page<EntryVersionDto> versions;
        try {
            versions = editorService.search(searchCriteria, pagination);
        } catch (Exception e) {
            logger.error("Error while searching for versions", e);
            return false;
        }

        int correct = 0;
        int nuncorrect = 0;
        for (int i = 0; i < versions.getContent().size(); i++) {
            EntryVersionDto version = versions.getContent().get(i);
            logger.debug(version.toString());

            EntryDto entry;
            try {
                entry = editorService.getEntry(version.getEntryId());
            } catch (Exception e) {
                logger.error("Error while searching for entry", e);
                return false;
            }

            // ignore if version has already new version
            if (!entry.getSuggestions().isEmpty() || entry.getCurrent().isAutomaticChange()) {
                continue;
            }

            EntryVersionDto newVersion = cloneVersion(entry.getCurrent());

            InflectionResultDto inflection;
            try {
                inflection = sursilvanInflectionComparatorUtil.getInflection(newVersion.getRmStichwort());
            } catch (StringIndexOutOfBoundsException | NullPointerException ex) {
                continue;
            }
            if (inflection.getInflectionResponse() == null) {
                continue;
            }

            newVersion.setInflection(inflection.getInflectionResponse());
            newVersion.setAutomaticChange(true);
            dictionaryService.addVersion(entry.getEntryId(), newVersion, true, UserInfoDto.getSystemDto());
        }

        logger.warn("Generated verbs for grammar value: {}. Correct: {} / Not correct: {}", grammarValue, correct, nuncorrect);

        return true;
    }

    public static List<String> getGenderValues() {
        return Stream.of(
                "m",
                "f",
                "m.pl",
                "f.pl",
                "pl",
                "f.coll"
        ).collect(Collectors.toList());
    }
    
    public static List<String> getGrammarValuesForAdjective() {
        return Stream.of(
                "adj"
        ).collect(Collectors.toList());
    }

    public static List<String> getGrammarValuesForVerbs() {
        return Stream.of(
                "tr",
                "intr"
                ).collect(Collectors.toList());
    }

    private EntryVersionDto cloneVersion(EntryVersionDto version) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(version);
            return mapper.readValue(json, EntryVersionDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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

    public static void serializeLexEntryAndSave(LexEntry lexEntry, Language language) throws IOException {
        if (lexEntry == null) {
            throw new IllegalArgumentException("LexEntry object cannot be null.");
        }
        if (lexEntry.getId() == null || lexEntry.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("LexEntry object must have a non-empty objectId for filename.");
        }
        if (language == null) {
            throw new IllegalArgumentException("language cannot be null or empty.");
        }

        String fileName = lexEntry.getId() + ".json";

        Path projectRoot = Paths.get("").toAbsolutePath();

        Path outputDirectory = projectRoot
                .resolve("api")
                .resolve("src")
                .resolve("test")
                .resolve("resources")
                .resolve("testdata")
                .resolve(language.getSubtag());

        File directory = outputDirectory.toFile();
        if (!directory.exists()) {
            directory.mkdirs();
            System.out.println("Created directory: " + directory.getAbsolutePath());
        }

        File outputFile = outputDirectory.resolve(fileName).toFile();

        OBJECT_MAPPER.writeValue(outputFile, lexEntry);
    }
}
