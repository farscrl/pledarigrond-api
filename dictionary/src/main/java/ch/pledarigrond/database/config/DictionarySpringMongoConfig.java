package ch.pledarigrond.database.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static java.util.Collections.singletonList;

@Configuration
@EnableMongoRepositories(basePackages = "ch.pledarigrond.database.dictionary.repositories", mongoTemplateRef = "dictionaryMongoTemplate")
public class DictionarySpringMongoConfig {
    private final Logger logger = LoggerFactory.getLogger(DictionarySpringMongoConfig.class);

    @Value("${pg.db.host}")
    private String mongoHost;

    @Value("${pg.db.port}")
    private int mongoPort;

    @Value("${pg.db.username}")
    private String mongoUser;

    @Value("${pg.db.password}")
    private String mongoPassword;

    @Value("${pg.db.authentication-database}")
    private String mongoAuthDb;

    @Bean(name = "dictionaryMongoClient")
    public MongoClient mongoClient() {
        MongoCredential credential = MongoCredential
                .createCredential(mongoUser, mongoAuthDb, mongoPassword.toCharArray());

        return MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder
                        .hosts(singletonList(new ServerAddress(mongoHost, mongoPort))))
                .credential(credential)
                .build());
    }

    @Bean(name = "dictionaryMongoDBFactory")
    public MongoDatabaseFactory mongoDatabaseFactory(@Qualifier("dictionaryMongoClient") MongoClient mongoClient) {
        return new RoutingMongoDatabaseFactory(mongoClient, "dict_default");
    }

    @Bean(name = "dictionaryMongoTemplate")
    public MongoTemplate mongoTemplate(@Qualifier("dictionaryMongoDBFactory") MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTemplate(mongoDatabaseFactory);
    }
}
