package ch.pledarigrond.common.config;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;

@Service
@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "pg")
public class PgEnvironment {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value( "${pg.name}")
	private String name;

	@Value( "${pg.version}")
	private String version;

	@Value( "${pg.db.host}")
	private String dbHost;

	@Value( "${pg.db.port}")
	private String dbPort;

	@Value( "${pg.db.username}")
	private String dbUsername;

	@Value( "${pg.db.password}")
	private String dbPassword;

	@Value( "${pg.db.authentication-database}")
	private String dbAuthDb;

	@Value( "${pg.db.puter}")
	private String dbPuter;

	@Value( "${pg.db.rumantschgrischun}")
	private String dbRumantschgrischun;

	@Value( "${pg.db.surmiran}")
	private String dbSurmiran;

	@Value( "${pg.db.sursilvan}")
	private String dbSursilvan;

	@Value( "${pg.db.sutsilvan}")
	private String dbSutsilvan;

	@Value( "${pg.db.vallader}")
	private String dbVallader;

	@Value( "${pg.lucene.baseFolder}")
	private String luceneBaseFolder;

	@Value( "pg.demoData.surmiran}")
	private String demoDataSurmiran;

	@Value( "${pg.admin.user}")
	private String adminUsername;

	@Value( "${pg.admin.password}")
	private String adminPassword;

	@Value( "${pg.backup.location}")
	private String backupLocation;

	@Value( "${pg.backup.number}")
	private String backupNumber;

	@Value( "${pg.backup.cron.puter}")
	private String backupPuterCron;

	@Value( "${pg.backup.cron.rumantschgrischun}")
	private String backupRumantschgrischunCron;

	@Value( "${pg.backup.cron.surmiran}")
	private String backupSurmiranCron;

	@Value( "${pg.backup.cron.sursilvan}")
	private String backupSursilvanCron;

	@Value( "${pg.backup.cron.sutsilvan}")
	private String backupSutsilvanCron;

	@Value( "${pg.backup.cron.vallader}")
	private String backupValladerCron;

	private LuceneConfiguration luceneConfig;

	public PgEnvironment() {

	}

	@PostConstruct
	public void init() {
		luceneConfig = new LuceneConfiguration();
		luceneConfig.setBaseDirectory(luceneBaseFolder);
		logger.info("**********************************************************************************");
		logger.info("Initializing " + name + " (" + version + ")");
		logger.info("**********************************************************************************");
	}

	public File getDemoDataSurmiranFile() {
		return new File(this.getDemoDataSurmiran());
	}

	public String getMongoDbClientUrl() {
		return String.format("mongodb://%s:%s@%s:%s/?authSource=%s", getDbUsername(), getDbPassword(), getDbHost(), getDbPort(), getDbAuthDb());
	}
}
