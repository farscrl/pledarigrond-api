package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.RegistrationService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.common.RequestContext;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.mongodb.util.MongoHelper;
import ch.pledarigrond.pronunciation.dto.RegistrationStatus;
import ch.pledarigrond.pronunciation.entities.Registration;
import ch.pledarigrond.pronunciation.repositories.RegistrationRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private PgEnvironment pgEnvironment;

    @Override
    public Page<Registration> getRegistrations(int page, int size) {
        return registrationRepository.findAll(PageRequest.of(page, size));
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
                if (isSingleWord(RStichwort)) {
                    if (!registrationRepository.existsByRStichwortAndRGenusAndRGrammatik(RStichwort, current.getLemmaValues().get("RGenus"), current.getLemmaValues().get("RGrammatik"))) {
                        logger.warn("registration added: " + RStichwort);
                        Registration registration = new Registration();
                        registration.setStatus(RegistrationStatus.TODO);
                        registration.setDStichwort(current.getLemmaValues().get("DStichwort"));
                        registration.setRStichwort(RStichwort);
                        registration.setRSemantik(current.getLemmaValues().get("RSemantik"));
                        registration.setRSubsemantik(current.getLemmaValues().get("RSubsemantik"));
                        registration.setRGrammatik(current.getLemmaValues().get("RGrammatik"));
                        registration.setRGenus(current.getLemmaValues().get("RGenus"));
                        registration.setRFlex(current.getLemmaValues().get("RFlex"));
                        registration.setRTags(current.getLemmaValues().get("RTags"));
                        registration.setRInflectionType(current.getLemmaValues().get("RInflectionType"));
                        registration.setRInflectionSubtype(current.getLemmaValues().get("RInflectionSubtype"));
                        registrationRepository.save(registration);
                    } else {
                        logger.warn("registration already exists: " + RStichwort);
                    }
                }
            }
        }

        return true;
    }

    private static boolean isSingleWord(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false; // Consider empty or null input as not a single word
        }
        String[] words = input.trim().split("\\s+");
        return words.length == 1;
    }
}
