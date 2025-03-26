package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.BunnyService;
import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.api.services.RegistrationService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.common.RequestContext;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.common.util.WordNormalizer;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.mongodb.util.MongoHelper;
import ch.pledarigrond.pronunciation.dto.ListFilter;
import ch.pledarigrond.pronunciation.dto.RegistrationStatistics;
import ch.pledarigrond.pronunciation.dto.RegistrationStatus;
import ch.pledarigrond.pronunciation.entities.Registration;
import ch.pledarigrond.pronunciation.repositories.RegistrationRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.ReplaceOptions;
import org.apache.commons.io.FilenameUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private PgEnvironment pgEnvironment;

    @Autowired
    private BunnyService bunnyService;

    @Autowired
    private LuceneService luceneService;

    @Value("${spring.profiles.active:dev}")
    private String activeProfile;

    @Override
    public Page<Registration> getRegistrations(ListFilter filter, Pageable pageable) {
        return registrationRepository.findByFilter(filter, pageable);
    }

    @Override
    public Registration getRegistration(String id) {
        return registrationRepository.findById(id).orElse(null);
    }

    @Override
    public Registration getNextRegistration() {
        StopWatch stopWatch = new StopWatch("getNextRegistration()");

        stopWatch.start("loading random word");
        Optional<Registration> registration = registrationRepository.findRandomRegistration();
        stopWatch.stop();

        logger.warn("getNextRegistration() performance metrics:\n{}", stopWatch.prettyPrint());

        return registration.orElse(null);
    }

    @Override
    public Registration postponeRegistration(Registration registration) {
        registration.setStatus(RegistrationStatus.POSTPONED_REGISTRATION);
        registrationRepository.save(registration);

        return registrationRepository.findRandomRegistration().orElse(null);
    }

    @Override
    public Registration acceptRegistration(Registration registration) {
        String id = generateRegistrationIdString(registration);
        registration.getLemmaIds().forEach(lemmaId -> {
            try {
                updatePronunciationForLexEntry(RequestContext.getLanguage(), lemmaId, id);
            } catch (DatabaseException | UnknownHostException e) {
                throw new RuntimeException(e);
            }
        });

        registration.setStatus(RegistrationStatus.COMPLETED);
        return registrationRepository.save(registration);
    }

    @Override
    public Registration rejectRegistration(Registration registration) {
        registration.setStatus(RegistrationStatus.REFUSED);
        return registrationRepository.save(registration);
    }

    @Override
    public Registration postponeReviewRegistration(Registration registration) {
        registration.setStatus(RegistrationStatus.POSTPONED_REVIEW);
        return registrationRepository.save(registration);
    }

    @Override
    public Registration addRegistrationToLemma(Registration registration, String lexEntryId) throws UnknownHostException, DatabaseException {
        String id = generateRegistrationIdString(registration);
        if (registration.getStatus() == RegistrationStatus.COMPLETED) {
            updatePronunciationForLexEntry(RequestContext.getLanguage(), lexEntryId, id);
        } else {
            removePronunciationForLexEntry(RequestContext.getLanguage(), lexEntryId);
            registration.getLemmaIds().add(lexEntryId);
            registrationRepository.save(registration);
        }
        return registration;
    }

    @Override
    public Registration getOrderedRegistration(String lexEntryId) {
        List<Registration> registrations = registrationRepository.findByLemmaIdsContaining(lexEntryId);
        return registrations.isEmpty() ? null : registrations.get(0);
    }

    @Override
    public Registration order(Registration registration) {
        registration.setId(null);
        registration.setStatus(RegistrationStatus.TODO);
        return registrationRepository.save(registration);
    }

    @Override
    public void deleteRegistration(String id) {
        bunnyService.deleteFile(generateRegistrationPath(Objects.requireNonNull(registrationRepository.findById(id).orElse(null))) + ".wav");
        bunnyService.deleteFile(generateRegistrationPath(Objects.requireNonNull(registrationRepository.findById(id).orElse(null))) + ".mp3");
        registrationRepository.deleteById(id);
    }

    @Override
    public Registration uploadRegistration(Registration registration, MultipartFile wavFile) throws IOException {
        StopWatch stopWatch = new StopWatch("uploadRegistration()");

        stopWatch.start("save temp file");
        File tempWavFile = File.createTempFile("upload", ".wav");
        wavFile.transferTo(tempWavFile);
        stopWatch.stop();

        stopWatch.start("convert wav to mp3");
        File mp3File = convertWavToMp3(tempWavFile);

        if (mp3File == null) {
            throw new IOException("Failed to convert wav to mp3");
        }
        stopWatch.stop();

        String path = generateRegistrationPath(registration);

        stopWatch.start("upload wav file");
        bunnyService.uploadFile(path + ".wav", tempWavFile);
        stopWatch.stop();

        stopWatch.start("upload mp3 file");
        bunnyService.uploadFile(path + ".mp3", mp3File);
        stopWatch.stop();

        stopWatch.start("delete temp files");
        tempWavFile.delete();
        mp3File.delete();
        stopWatch.stop();

        stopWatch.start("save registration");
        registration.setStatus(RegistrationStatus.IN_REVIEW);
        registrationRepository.save(registration);
        stopWatch.stop();

        logger.warn("uploadRegistration() performance metrics:\n{}", stopWatch.prettyPrint());

        return registration;
    }

    @Override
    public boolean extractSingleWords() throws DatabaseException, UnknownHostException {
        Language language = RequestContext.getLanguage();
        if (language == null) {
            throw new DatabaseException("No language set in request context");
        }

        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, language.getName()).getCollection("entries");

        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            if (entry.getCurrent() != null) {
                LemmaVersion current = entry.getCurrent();
                String RStichwort = current.getLemmaValues().get("RStichwort");
                RStichwort = WordNormalizer.normalizeWord(language, RStichwort, false);
                RStichwort = removeRegularPlural(RStichwort);
                RStichwort = removeSuffixes(RStichwort);
                if (isSingleWord(RStichwort)) {
                    Optional<Registration> existingRegistration = registrationRepository.findFirstByRmStichwortAndRmGenusAndRmGrammatik(RStichwort, current.getLemmaValues().get("RGenus"), current.getLemmaValues().get("RGrammatik"));

                    if (existingRegistration.isEmpty()) {
                        // logger.info("registration added: " + RStichwort);
                        Registration registration = new Registration();
                        registration.getLemmaIds().add(entry.getId());
                        registration.setStatus(RegistrationStatus.TODO);
                        registration.setDeStichwort(current.getLemmaValues().get("DStichwort"));
                        registration.setRmStichwort(RStichwort);
                        registration.setRmSemantik(current.getLemmaValues().get("RSemantik"));
                        registration.setRmSubsemantik(current.getLemmaValues().get("RSubsemantik"));
                        registration.setRmGrammatik(current.getLemmaValues().get("RGrammatik"));
                        registration.setRmGenus(current.getLemmaValues().get("RGenus"));
                        registration.setRmFlex(current.getLemmaValues().get("RFlex"));
                        registration.setRmTags(current.getLemmaValues().get("RTags"));
                        registration.setRmInflectionType(current.getLemmaValues().get("RInflectionType"));
                        registration.setRmInflectionSubtype(current.getLemmaValues().get("RInflectionSubtype"));
                        registrationRepository.save(registration);
                    } else {
                        // logger.info("registration already exists: " + RStichwort);
                        existingRegistration.get().getLemmaIds().add(entry.getId());
                        registrationRepository.save(existingRegistration.get());
                    }
                }
            }
        }

        return true;
    }

    @Override
    public ByteArrayResource extractListOfWordsByEnding() throws DatabaseException, UnknownHostException {
        Language language = RequestContext.getLanguage();
        if (language == null) {
            throw new DatabaseException("No language set in request context");
        }

        List<String> strings = new ArrayList<>();

        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, language.getName()).getCollection("entries");

        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            if (entry.getCurrent() != null) {
                LemmaVersion current = entry.getCurrent();
                String RStichwort = current.getLemmaValues().get("RStichwort");
                RStichwort = WordNormalizer.normalizeWord(language, RStichwort, false);
                RStichwort = removeRegularPlural(RStichwort);
                RStichwort = removeSuffixes(RStichwort);

                if (RStichwort != null && !RStichwort.isEmpty()) {
                    strings.add(RStichwort);
                }
            }
        }

        List<String> sortedStrings = strings.stream()
                .sorted((s1, s2) -> new StringBuilder(s1).reverse().toString()
                        .compareTo(new StringBuilder(s2).reverse().toString()))
                .collect(Collectors.toList());

        String result = String.join("\n", sortedStrings);
        return new ByteArrayResource(result.getBytes());
    }

    @Override
    public RegistrationStatistics getStatistics() {
        RegistrationStatistics statistics = new RegistrationStatistics();
        statistics.setTotalRegistrations((int) registrationRepository.count());
        statistics.setTodoRegistrations(registrationRepository.countByStatus(RegistrationStatus.TODO));
        statistics.setPostponedRegistrationRegistrations(registrationRepository.countByStatus(RegistrationStatus.POSTPONED_REGISTRATION));
        statistics.setInReviewRegistrations(registrationRepository.countByStatus(RegistrationStatus.IN_REVIEW));
        statistics.setPostponedReviewRegistrations(registrationRepository.countByStatus(RegistrationStatus.POSTPONED_REVIEW));
        statistics.setCompletedRegistrations(registrationRepository.countByStatus(RegistrationStatus.COMPLETED));
        statistics.setRefusedRegistrations(registrationRepository.countByStatus(RegistrationStatus.REFUSED));
        return statistics;
    }

    private static boolean isSingleWord(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false; // Consider empty or null input as not a single word
        }
        String[] words = input.trim().split("\\s+");
        return words.length == 1 || (words.length == 2 && words[0].equals("as")) || (words.length == 2 && words[0].equals("sa"));
    }

    private File convertWavToMp3(File wavFile) {
        String mp3FileName = FilenameUtils.removeExtension(wavFile.getName()) + ".mp3";
        File mp3File = new File(wavFile.getParent(), mp3FileName);

        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(128000);
        audio.setChannels(1);
        audio.setSamplingRate(48000);

        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setInputFormat("wav");
        attrs.setOutputFormat("mp3");
        attrs.setAudioAttributes(audio);

        Encoder encoder = new Encoder();
        try {
            encoder.encode(new MultimediaObject(wavFile), mp3File, attrs);
            return mp3File;
        } catch (EncoderException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String removeSuffixes(String input) {
        if (input == null) {
            return null;
        }

        // Regular expressions to find suffixes in parentheses or square brackets
        String regex = "\\s*[\\(\\[].*?[\\)\\]]\\s*$";
        return input.replaceAll(regex, "");
    }

    private void updatePronunciationForLexEntry(Language language, String id, String pronunciation) throws DatabaseException, UnknownHostException {
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, language.getName()).getCollection("entries");

        Document query = new Document("_id", new ObjectId(id));
        try (MongoCursor<Document> cursor = entryCollection.find(query).iterator()) {
            while (cursor.hasNext()) {
                DBObject object = new BasicDBObject(cursor.next());
                LexEntry entry = Converter.convertToLexEntry(object);

                if (entry.getCurrent() != null) {
                    entry.getCurrent().getLemmaValues().put("RPronunciation", pronunciation);
                }
                entry.getUnapprovedVersions().forEach(version -> version.getLemmaValues().put("RPronunciation", pronunciation));

                BasicDBObject newObject = Converter.convertLexEntry(entry);
                entryCollection.replaceOne(eq("_id", newObject.get("_id")), new Document(newObject), new ReplaceOptions().upsert(true));

                luceneService.update(language, entry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void removePronunciationForLexEntry(Language language, String id) throws DatabaseException, UnknownHostException {
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, language.getName()).getCollection("entries");

        Document query = new Document("_id", new ObjectId(id));
        try (MongoCursor<Document> cursor = entryCollection.find(query).iterator()) {
            while (cursor.hasNext()) {
                DBObject object = new BasicDBObject(cursor.next());
                LexEntry entry = Converter.convertToLexEntry(object);

                if (entry.getCurrent() != null) {
                    entry.getCurrent().getLemmaValues().remove("RPronunciation");
                }
                entry.getUnapprovedVersions().forEach(version -> version.getLemmaValues().remove("RPronunciation"));

                BasicDBObject newObject = Converter.convertLexEntry(entry);
                entryCollection.replaceOne(eq("_id", newObject.get("_id")), new Document(newObject), new ReplaceOptions().upsert(true));

                luceneService.update(language, entry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateRegistrationIdString(Registration registration) {
        return activeProfile + "/" + registration.getId();
    }

    private String generateRegistrationPath(Registration registration) {
        return "pronunciation/" + activeProfile + "/" + registration.getId() + "/" + registration.getId();
    }

    private static String removeRegularPlural(String input) {
        if (input == null) {
            return null;
        }

        String[] parts = input.split(",\\s*");

        if (parts.length != 2) {
            return input;
        }

        String pattern = parts[0];
        String possiblePatternWithA = parts[1];

        if (possiblePatternWithA.equals(pattern + "a")) {
            return pattern;
        } else if (possiblePatternWithA.equals(pattern + "ada")) { // (curvato, -ada)
            return pattern;
        } else if (pattern.length() > 1 && possiblePatternWithA.equals(pattern.substring(0, pattern.length() - 1) + "ada")) { //(adato, adatada)
            return pattern;
        } else if (pattern.length() > 3 && possiblePatternWithA.equals(pattern.substring(0, pattern.length() - 3) + "ida")) { // (repetieu, repetida)
            return pattern;
        } else if (pattern.length() > 2 && possiblePatternWithA.equals(pattern.substring(0, pattern.length() - 2) + "la")) { // (duvrevel, duvrevla)
            return pattern;
        } else {
            return input;
        }
    }
}
