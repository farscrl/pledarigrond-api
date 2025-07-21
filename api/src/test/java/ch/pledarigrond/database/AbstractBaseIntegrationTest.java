package ch.pledarigrond.database;

import ch.pledarigrond.api.ApiApplication;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(classes = ApiApplication.class)
@ActiveProfiles("testing")
@Import(ch.pledarigrond.database.TestMongoConfig.class)
public abstract class AbstractBaseIntegrationTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0").withExposedPorts(27017);

    @Autowired
    @Qualifier("dictionaryMongoTemplate")
    private MongoTemplate dictionaryMongoTemplate;

    @DynamicPropertySource
    static void containersProperties(DynamicPropertyRegistry registry) {
        mongoDBContainer.start();
        registry.add("pg.db.uri", mongoDBContainer::getReplicaSetUrl);
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
        registry.add("spring.data.mongodb.database", () -> "testdb");
    }

    @BeforeEach
    void cleanUp() {
        dictionaryMongoTemplate.getDb().listCollectionNames().forEach(collectionName -> {
            dictionaryMongoTemplate.dropCollection(collectionName);
        });
    }
}
