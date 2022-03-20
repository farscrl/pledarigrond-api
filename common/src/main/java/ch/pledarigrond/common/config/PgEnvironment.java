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

	@Value( "${pg.lucene.baseFolder}")
	private String luceneBaseFolder;

	@Value( "pg.demoData.surmiran}")
	private String demoDataSurmiran;

	private LuceneConfiguration luceneConfig;

	private boolean isInitialized = false;

	public PgEnvironment() {

	}

	@PostConstruct
	public void init() {
		if (isInitialized) {
			return;
		}
		luceneConfig = new LuceneConfiguration();
		luceneConfig.setBaseDirectory(luceneBaseFolder);
		logger.info("**********************************************************************************");
		logger.info("Initializing " + name + " (" + version + ")");
		logger.info("**********************************************************************************");

		isInitialized = true;
	}

	public File getDemoDataSurmiranFile() {
		return new File(this.getDemoDataSurmiran());
	}
}
