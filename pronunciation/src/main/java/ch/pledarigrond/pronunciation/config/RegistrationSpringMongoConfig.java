package ch.pledarigrond.pronunciation.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "ch.pledarigrond.pronunciation")
public class RegistrationSpringMongoConfig extends AbstractMongoClientConfiguration {


    @Value("${pg.db.host}")
    private String mongoHost;

    @Value("${pg.db.port}")
    private String mongoPort;

    @Value("${pg.db.username}")
    private String mongoUser;

    @Value("${pg.db.password}")
    private String mongoPassword;

    @Value("${pg.db.registrations}")
    private String mongoDbName;

    @Value("${pg.db.authentication-database}")
    private String mongoAuthDb;

    @Override
    protected String getDatabaseName() {
        return mongoDbName;
    }

    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {
        String connectionString = String.format("mongodb://%s:%s@%s:%s/%s?authSource=%s", mongoUser, mongoPassword, mongoHost, mongoPort, mongoDbName, mongoAuthDb);
        builder.applyConnectionString(new ConnectionString(connectionString));
    }

}
