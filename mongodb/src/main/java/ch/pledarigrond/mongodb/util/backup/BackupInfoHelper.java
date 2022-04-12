package ch.pledarigrond.mongodb.util.backup;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.mongodb.model.BackupInfos;
import ch.pledarigrond.mongodb.model.FileInfo;
import ch.pledarigrond.common.util.DbSelector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("backupInfoHelper")
public class BackupInfoHelper extends AbstractBackupHelper {

	private static final Logger LOG = LoggerFactory.getLogger(BackupInfoHelper.class);

	@Autowired
	private PgEnvironment pgEnvironment;

	/**
	 * Returns a wrapper class for displaying backup information within the gwt
	 * admin-module.
	 */
	public BackupInfos getBackupInfos(Language language) {
		LOG.debug("backup infos called...");

		String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
		List<FileInfo> list = new ArrayList<FileInfo>();
		File backupFile = new File(pgEnvironment.getBackupLocation() + dbName + "/");
		backupFile.mkdirs();

		List<File> backupFiles = listBackupFilesAsc(backupFile.getAbsolutePath());
		try {
			for (File file : backupFiles) {
				list.add(new FileInfo().setAbsolutePath(file.getAbsolutePath())
						.setLastModified(getLastModified(file))
						.setName(file.getName())
						.setParent(file.getParentFile().getAbsolutePath())
						.setCreationDate(getCreationDate(file))
						.setSize(getSize(file)));
			}
		} catch (Exception e) {
			LOG.error("error occured: ", e);
		}

		String cronExpression = DbSelector.getDbCronByLanguage(pgEnvironment, language);

		return new BackupInfos(list)
				.setBackupLocation(backupFile.getPath())
				.setTriggerTime(cronExpression)
				.setBackupNums(pgEnvironment.getBackupNumber());
	}

	private String getSize(File file) {
		return new DecimalFormat("##.##").format((file.length() / 1024) / 1024);
	}

	private String getCreationDate(File file) {
		return new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss")
				.format(new Date(file.lastModified()));
	}

	private String getLastModified(File file) {
		return new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss")
				.format(new Date(file.lastModified()));
	}
}
