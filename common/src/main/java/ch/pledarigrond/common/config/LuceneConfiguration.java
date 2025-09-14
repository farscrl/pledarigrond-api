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

	private File suggestionIndexDirRm;
	private File suggestionIndexDirDe;

	@Getter
    private final Language language;

	public LuceneConfiguration(Language language, String baseDirectory) {
		this.language = language;
		setBaseDirectory(baseDirectory);
	}

	public File getLuceneTimestampFile() {
		return new File(indexDir + "/PG_" + language.getSubtag() + ".timestamp");
	}

	public File getLuceneIndexDir() {
		return indexDir;
	}

	public File getLuceneSuggestionIndexDirRm() {
		return suggestionIndexDirRm;
	}

	public File getLuceneSuggestionIndexDirDe() {
		return suggestionIndexDirDe;
	}

	private void setBaseDirectory(String luceneDir) {
		lucenePath = new File(luceneDir).toPath().toAbsolutePath();
		this.indexDir = new File(lucenePath + "/" + language.getName() + "/");
		boolean success = this.indexDir.mkdirs();
		if (!success) {
            logger.warn("Could not create index directory: {}", this.indexDir);
		}

		this.suggestionIndexDirRm = new File(lucenePath + "/" + language.getName() + "_suggestions_rm/");
		success = this.suggestionIndexDirRm.mkdirs();
		if (!success) {
            logger.warn("Could not create suggestion index directory RM: {}", this.suggestionIndexDirRm);
		}

		this.suggestionIndexDirDe = new File(lucenePath + "/" + language.getName() + "_suggestions_de/");
		success = this.suggestionIndexDirDe.mkdirs();
		if (!success) {
			logger.warn("Could not create suggestion index directory DE: {}", this.suggestionIndexDirDe);
		}
	}
}
