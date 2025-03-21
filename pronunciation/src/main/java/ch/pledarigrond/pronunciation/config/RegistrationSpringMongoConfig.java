package ch.pledarigrond.pronunciation.config;

import ch.pledarigrond.pronunciation.repositories.RegistrationRepository;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static java.util.Collections.singletonList;

@Configuration
@EnableMongoRepositories(basePackageClasses = {RegistrationRepository.class}, mongoTemplateRef = "registrationsMongoTemplate")
public class RegistrationSpringMongoConfig {


    @Value("${pg.db.host}")
    private String mongoHost;

    @Value("${pg.db.port}")
    private int mongoPort;

    @Value("${pg.db.username}")
    private String mongoUser;

    @Value("${pg.db.password}")
    private String mongoPassword;

    @Value("${pg.db.registrations}")
    private String mongoDbNameRegistrations;

    @Value("${pg.db.authentication-database}")
    private String mongoAuthDb;


    @Bean(name = "registrationsMongoClient")
    @Profile("!testing")
    public MongoClient mongoClient() {
        MongoCredential credential = MongoCredential
                .createCredential(mongoUser, mongoAuthDb, mongoPassword.toCharArray());

        return MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder
                        .hosts(singletonList(new ServerAddress(mongoHost, mongoPort))))
                .credential(credential)
                .build());
    }

    @Bean(name = "registrationsMongoDBFactory")
    public MongoDatabaseFactory mongoDatabaseFactory(
            @Qualifier("registrationsMongoClient") MongoClient mongoClient) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, mongoDbNameRegistrations);
    }

    @Bean(name = "registrationsMongoTemplate")
    public MongoTemplate mongoTemplate(@Qualifier("registrationsMongoDBFactory") MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTemplate(mongoDatabaseFactory);
    }
}
