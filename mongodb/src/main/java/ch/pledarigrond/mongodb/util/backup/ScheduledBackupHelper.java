package ch.pledarigrond.mongodb.util.backup;

import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.mongodb.exceptions.ScheduledBackupException;
import ch.pledarigrond.mongodb.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

// TODO: make backups of all DBs
@Service("scheduledBackupHelper")
public class ScheduledBackupHelper extends AbstractBackupHelper {

	private static final Logger LOG = LoggerFactory.getLogger(ScheduledBackupHelper.class);

	@Value("${backup.location:backup}")
	private String backupDir;

	@Value("${db.name:surmiran}")
	private String dbName;

	@Scheduled(initialDelayString = "${backup.initial.delay}", fixedRateString = "${backup.fixed.rate}")
	public void backup() throws ScheduledBackupException {

		LOG.info("started scheduled backup");

		String backupFileName = buildName();
		LOG.info("backup file name created: {}", backupFileName);

		File backupFile = buidlBackup(backupDir, backupFileName, dbName);
		LOG.info("backup file created: {}", backupFile.getAbsolutePath());

		if (valid(backupFile)) {
			LOG.info("backup file valid");
			cleanup();
		} else {
			throw new ScheduledBackupException("back up file is not valid.");
		}

		LOG.info("finished scheduled backup");
	}

	private File buidlBackup(String dir, String backupFileName, String locale) {
		File backupFile = new File(dir, backupFileName);
		try {
			Database.getInstance(locale).exportData(true, false, new FileOutputStream(backupFile), backupFileName);
		} catch (Exception e) {
			LOG.error("error occured... {}", e);
		}
		return backupFile;
	}

	private void cleanup() throws ScheduledBackupException {
		List<File> backupFiles = listBackupFilesAsc(backupDir);
		if (backupFiles.size() >= 7) {
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

	private boolean valid(File backupFile) {
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
			LOG.error("error occured: {}", e);
			return false;
		}
	}

	private String buildName() {
		return String.format("%s_db_dump_%s.zip", dbName, new SimpleDateFormat(
				"yyyy-MM-dd_HH-mm-ss").format(new Date()));
	}
}
