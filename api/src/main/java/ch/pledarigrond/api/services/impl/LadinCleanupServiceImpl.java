package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.*;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.mongodb.util.MongoHelper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.DeleteOptions;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.eq;

@Service
public class LadinCleanupServiceImpl implements LadinCleanupService {

    private static final Logger logger = LoggerFactory.getLogger(LadinCleanupServiceImpl.class);

    @Autowired
    private EditorService editorService;

    @Autowired
    private InflectionService inflectionService;

    @Autowired
    private MongoDbService mongoDbService;

    @Autowired
    private PgEnvironment pgEnvironment;


    @Override
    public boolean removeEtymology(Language language) throws DatabaseException, UnknownHostException {
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, language.getName()).getCollection("entries");

        int counter = 0;
        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            if (entry.getCurrent().getLemmaValues().get("categories").contains("etimologia")
            || entry.getCurrent().getLemmaValues().get("categories").contains("etimolgia")) {

                String RStichwort = entry.getCurrent().getLemmaValues().get("RStichwort");

                String[] parts = RStichwort.split(Pattern.quote("E. <"));

                if (parts.length != 2) {
                    parts = RStichwort.split(Pattern.quote("E."));

                    if (parts.length != 2) {
                        //logger.error(parts[0]);
                        continue;
                    }
                }
                String etymology = parts[1].trim();
                AtomicInteger nbrFound = new AtomicInteger();
                entryCollection.find(eq("versions.RStichwort", parts[0].trim())).forEach(a -> {
                    DBObject o = new BasicDBObject(a);
                    LexEntry ntry = Converter.convertToLexEntry(o);

                    //logger.warn(ntry.getCurrent().getLemmaValues().get("RStichwort"));
                    nbrFound.getAndIncrement();

                    ntry.getCurrent().getLemmaValues().put("REtymologie", etymology);

                    BasicDBObject newObject = Converter.convertLexEntry(ntry);
                    entryCollection.replaceOne(eq("_id", newObject.get("_id")),  new Document(newObject), new ReplaceOptions().upsert(true));
                });
                if (nbrFound.get() == 0) {
                    logger.warn("========> NOT FOUND: " + parts[0].trim());
                    counter++;
                } else {
                    BasicDBObject toDelete = Converter.convertLexEntry(entry);
                    entryCollection.deleteOne(eq("_id", toDelete.get("_id")));
                }
            }
        }

        logger.error("number of cases: " + counter);

        return true;
    }

    @Override
    public boolean removeEmptyWords(Language language) throws DatabaseException, UnknownHostException {
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, language.getName()).getCollection("entries");

        int counter = 0;
        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            if (entry.getCurrent().getLemmaValues().get("RStichwort").equals("")
                    || entry.getCurrent().getLemmaValues().get("DStichwort").equals("")) {

                BasicDBObject toDelete = Converter.convertLexEntry(entry);
                entryCollection.deleteOne(eq("_id", toDelete.get("_id")));
                counter++;
            }
        }

        logger.error("number of cases: " + counter);

        return true;
    }

    @Override
    public boolean removeOldForms(Language language) throws DatabaseException, UnknownHostException {
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();
        MongoCollection<Document> entryCollection = MongoHelper.getDB(pgEnvironment, language.getName()).getCollection("entries");

        int counter = 0;
        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            if (entry.getCurrent().getLemmaValues().get("RStichwort").contains("†")) {

                BasicDBObject toDelete = Converter.convertLexEntry(entry);
                entryCollection.deleteOne(eq("_id", toDelete.get("_id")));
                counter++;
            }
        }

        logger.error("number of cases: " + counter);

        return true;
    }
}
