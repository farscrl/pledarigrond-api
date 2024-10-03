package ch.pledarigrond.common.config;

import ch.pledarigrond.common.data.common.Language;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

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

	@Value( "${pg.corpus.baseFolder}")
	private String corpusBaseFolder;

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

	@Value( "${pg.export.location}")
	private String exportLocation;

	@Value("${pg.export.temp}")
	private String tempExportLocation;

	@Value( "${pg.export.cron}")
	private String exportCron;

	@Value("${pg.s3.accessKey}")
	private String s3AccessKey;

	@Value("${pg.s3.secretKey}")
	private String s3SecretKey;

	@Value("${pg.s3.bucket}")
	private String s3Bucket;

	@Value("${pg.s3.endpoint}")
	private String s3Endpoint;

	@Value("${pg.s3.region}")
	private String s3Region;

	@Value("${pg.hunspell.location}")
	private String hunspellLocation;

	@Value("${pg.export.dump_location}")
	private String dumpLocation;

	@Value("${pg.export.restore_location}")
	private String restoreLocation;

	private LuceneConfiguration luceneConfigPuter;
	private LuceneConfiguration luceneConfigRumantschgrischun;
	private LuceneConfiguration luceneConfigSurmiran;
	private LuceneConfiguration luceneConfigSursilvan;
	private LuceneConfiguration luceneConfigSutsilvan;
	private LuceneConfiguration luceneConfigVallader;

	public PgEnvironment() {

	}

	@PostConstruct
	public void init() {
		luceneConfigPuter = new LuceneConfiguration(Language.PUTER, luceneBaseFolder);
		luceneConfigRumantschgrischun = new LuceneConfiguration(Language.RUMANTSCHGRISCHUN, luceneBaseFolder);
		luceneConfigSurmiran = new LuceneConfiguration(Language.SURMIRAN, luceneBaseFolder);
		luceneConfigSursilvan = new LuceneConfiguration(Language.SURSILVAN, luceneBaseFolder);
		luceneConfigSutsilvan = new LuceneConfiguration(Language.SUTSILVAN, luceneBaseFolder);
		luceneConfigVallader = new LuceneConfiguration(Language.VALLADER, luceneBaseFolder);
		logger.info("**********************************************************************************");
		logger.info("Initializing " + name + " (" + version + ")");
		logger.info("**********************************************************************************");
	}

	public String getMongoDbClientUrl() {
		return String.format("mongodb://%s:%s@%s:%s/?authSource=%s", getDbUsername(), getDbPassword(), getDbHost(), getDbPort(), getDbAuthDb());
	}
}
