package ch.pledarigrond.mongodb.util.backup;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.mongodb.exceptions.ScheduledBackupException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.mongodb.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service("scheduledBackupHelper")
public class ScheduledBackupHelper extends AbstractBackupHelper {

	private static final Logger LOG = LoggerFactory.getLogger(ScheduledBackupHelper.class);

	@Autowired
	private PgEnvironment pgEnvironment;

	@Scheduled(cron = "${pg.backup.cron.rumantschgrischun}")
	public void backupRumantschgrischun() {
		backup(Language.RUMANTSCHGRISCHUN);
	}

	@Scheduled(cron = "${pg.backup.cron.surmiran}")
	public void backupSurmiran() {
		backup(Language.SURMIRAN);
	}

	@Scheduled(cron = "${pg.backup.cron.sutsilvan}")
	public void backupSutsilvan() {
		backup(Language.SUTSILVAN);
	}

	private void backup(Language language) {
		LOG.info("started scheduled backup for: " + language);

		String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);

		String backupFileName = buildName(dbName);
		LOG.info("backup file name created: {}", backupFileName);

		File backupFile = buildBackup(pgEnvironment.getBackupLocation(), backupFileName, dbName);
		backupFile.mkdirs();
		LOG.info("backup file created: {}", backupFile.getAbsolutePath());

		if (valid(backupFile, dbName)) {
			LOG.info("backup file valid");
			try {
				cleanup();
			} catch (ScheduledBackupException e) {
				LOG.error("back up file is not valid.");
			}
		} else {
			LOG.error("back up file is not valid.");
		}

		LOG.info("finished scheduled backup");
	}

	private File buildBackup(String dir, String backupFileName, String locale) {
		File backupDir = new File(dir);
		backupDir.mkdirs();
		File backupFile = new File(dir, backupFileName);
		try {
			Database.getInstance(locale).exportData(true, false, new FileOutputStream(backupFile), backupFileName);
		} catch (Exception e) {
			LOG.error("error occured...", e);
		}
		return backupFile;
	}

	private void cleanup() throws ScheduledBackupException {
		List<File> backupFiles = listBackupFilesAsc(pgEnvironment.getBackupLocation());
		if (backupFiles.size() >= Integer.parseInt(pgEnvironment.getBackupNumber())) {
			boolean delete = backupFiles.get(0).delete();
			if (!delete) {
				throw new ScheduledBackupException(String.format(
						"could not delete obsolete backup file: %s",
						backupFiles.get(0).getAbsolutePath()));
			}
		}

		LOG.info("list of backupFiles...");
		for (File file : backupFiles) {
			LOG.info("{}", file);
		}
	}

	private boolean valid(File backupFile, String dbName) {
		if (null == backupFile) {
			return false;
		}

		if (!filter().accept(backupFile)) {
			return false;
		}

		try (FileInputStream input = new FileInputStream(backupFile)) {
			Iterator<LexEntry> data = Database.getInstance(dbName).getExportedData(input);
			while (data.hasNext()) {
				Validator.validate(data.next());
			}
			return true;
		} catch (Exception e) {
			LOG.error("error occured: ", e);
			return false;
		}
	}

	private String buildName(String dbName) {
		return String.format("%s/%s_db_dump_%s.zip", dbName, dbName, new SimpleDateFormat(
				"yyyy-MM-dd_HH-mm-ss").format(new Date()));
	}
}
