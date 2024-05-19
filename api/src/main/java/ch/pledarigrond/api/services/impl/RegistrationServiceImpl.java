package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.BunnyService;
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
import org.apache.commons.io.FilenameUtils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private PgEnvironment pgEnvironment;

    @Autowired
    private BunnyService bunnyService;

    @Value("${spring.profiles.active:dev}")
    private String activeProfile;

    @Override
    public Page<Registration> getRegistrations(ListFilter filter, Pageable pageable) {
        return registrationRepository.findByFilter(filter, pageable);
    }

    @Override
    public Registration getNextRegistration() {
        Optional<Registration> registration = registrationRepository.findRandomRegistration();
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
        // TODO: Save registration to lemmas

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
    public Registration uploadRegistration(Registration registration, MultipartFile wavFile) throws IOException {
        File tempWavFile = File.createTempFile("upload", ".wav");
        wavFile.transferTo(tempWavFile);

        File mp3File = convertWavToMp3(tempWavFile);

        String path = "pronunciation/" + activeProfile + "/" + registration.getId() + "/" + registration.getId();

        bunnyService.uploadFile(path + ".wav", tempWavFile);
        bunnyService.uploadFile(path + ".mp3", mp3File);

        tempWavFile.delete();
        mp3File.delete();

        registration.setStatus(RegistrationStatus.IN_REVIEW);
        registrationRepository.save(registration);

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
                RStichwort = WordNormalizer.normalizeWord(language, RStichwort);
                if (isSingleWord(RStichwort)) {
                    Optional<Registration> existingRegistration = registrationRepository.findFirstByRmStichwortAndRmGenusAndRmGrammatik(RStichwort, current.getLemmaValues().get("RGenus"), current.getLemmaValues().get("RGrammatik"));

                    if (existingRegistration.isEmpty()) {
                        logger.warn("registration added: " + RStichwort);
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
                        existingRegistration.get().getLemmaIds().add(entry.getId());
                        logger.info("registration already exists: " + RStichwort);
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
                RStichwort = WordNormalizer.normalizeWord(language, RStichwort);

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
        return words.length == 1;
    }

    private File convertWavToMp3(File wavFile) throws IOException {
        // Assuming ffmpeg is installed and added to the system path
        String mp3FileName = FilenameUtils.removeExtension(wavFile.getName()) + ".mp3";
        File mp3File = new File(wavFile.getParent(), mp3FileName);

        ProcessBuilder pb = new ProcessBuilder("ffmpeg", "-i", wavFile.getAbsolutePath(), mp3File.getAbsolutePath());
        pb.redirectErrorStream(true);
        Process process = pb.start();
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!mp3File.exists()) {
            throw new IOException("Failed to convert wav to mp3");
        }

        return mp3File;
    }

    private void uploadFileToBunny(File file, String fileName) {
        // todo: move file if already existing
        bunnyService.uploadFile(fileName, file);
    }
}
