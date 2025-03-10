package ch.pledarigrond.dictionary.config;

import ch.pledarigrond.common.data.common.RequestContext;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

public class RoutingMongoDatabaseFactory extends SimpleMongoClientDatabaseFactory {
    private final Logger logger = LoggerFactory.getLogger(RoutingMongoDatabaseFactory.class);


    public RoutingMongoDatabaseFactory(MongoClient mongoClient, String defaultDatabaseName) {
        super(mongoClient, defaultDatabaseName);
    }

    @Override
    protected MongoDatabase doGetMongoDatabase(String dbName) {
        return ((MongoClient)this.getMongoClient()).getDatabase(getMongoDbName());
    }

    public String getMongoDbName() {
        if (RequestContext.getLanguage() != null) {
            return "dict_" + RequestContext.getLanguage().getName();
        }
        logger.error("No language specified. Setting pseudo db.");
        return "dict_undefinedlanguage";
    }
}
