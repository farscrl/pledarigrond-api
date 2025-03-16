package ch.pledarigrond.database.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static java.util.Collections.singletonList;

@Configuration
@EnableMongoRepositories(basePackages = "ch.pledarigrond.database.user.repositories", mongoTemplateRef = "usersMongoTemplate")
public class UserSpringMongoConfig {


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


    @Bean(name = "usersMongoClient")
    public MongoClient mongoClient() {
        MongoCredential credential = MongoCredential
                .createCredential(mongoUser, mongoAuthDb, mongoPassword.toCharArray());

        return MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder
                        .hosts(singletonList(new ServerAddress(mongoHost, mongoPort))))
                .credential(credential)
                .build());
    }

    @Bean(name = "usersMongoDBFactory")
    public MongoDatabaseFactory mongoDatabaseFactory(
            @Qualifier("usersMongoClient") MongoClient mongoClient) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, "users");
    }

    @Bean(name = "usersMongoTemplate")
    public MongoTemplate mongoTemplate(@Qualifier("usersMongoDBFactory") MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTemplate(mongoDatabaseFactory);
    }
}
