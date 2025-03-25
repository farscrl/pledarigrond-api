package ch.pledarigrond.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@TestConfiguration
@Profile("testing")
public class TestMongoConfig {

    @Value("${pg.db.uri}")
    private String dbUri;

    @Bean(name = "dictionaryMongoClient")
    @Primary
    public MongoClient testDictionaryMongoClient() {
        return MongoClients.create(dbUri);
    }

    @Bean(name = "usersMongoClient")
    @Primary
    public MongoClient testUsersMongoClient() {
        return MongoClients.create(dbUri);
    }

    @Bean(name = "corpusMongoClient")
    @Primary
    public MongoClient testCorpusMongoClient() {
        return MongoClients.create(dbUri);
    }

    @Bean(name = "namesMongoClient")
    @Primary
    public MongoClient testNamesMongoClient() {
        return MongoClients.create(dbUri);
    }

    @Bean(name = "registrationsMongoClient")
    @Primary
    public MongoClient testRegistrationsMongoClient() {
        return MongoClients.create(dbUri);
    }
}
