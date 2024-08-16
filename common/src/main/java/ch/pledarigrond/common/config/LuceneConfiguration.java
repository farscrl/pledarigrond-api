package ch.pledarigrond.common.config;

import ch.pledarigrond.common.data.common.Language;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;

/**
 * This class gives access to some base information of the currently used lucene instance.
 */
public class LuceneConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(LuceneConfiguration.class);

	private Path lucenePath;

	private File indexDir;

	private File suggestionIndexDir;

	@Getter
    private final Language language;

	public LuceneConfiguration(Language language, String baseDirectory) {
		this.language = language;
		setBaseDirectory(baseDirectory);
	}

	public File getLuceneTimestampFile() {
		return new File(indexDir + "/" +  ".stamp");
	}

	public File getLuceneIndexDir() {
		return indexDir;
	}

	public File getLuceneSuggestionIndexDir() {
		return suggestionIndexDir;
	}

	private void setBaseDirectory(String luceneDir) {
		lucenePath = new File(luceneDir).toPath().toAbsolutePath();
		this.indexDir = new File(lucenePath + "/" + language.getName() + "/");
		boolean success = this.indexDir.mkdirs();
		if (!success) {
            logger.warn("Could not create index directory: {}", this.indexDir);
		}

		this.suggestionIndexDir = new File(lucenePath + "/" + language.getName() + "_suggestions/");
		success = this.suggestionIndexDir.mkdirs();
		if (!success) {
            logger.warn("Could not create suggestion index directory: {}", this.suggestionIndexDir);
		}
	}
}
