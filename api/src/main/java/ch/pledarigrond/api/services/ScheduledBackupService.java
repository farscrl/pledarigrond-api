package ch.pledarigrond.api.services;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.database.services.DbBackupService;
import jakarta.annotation.PostConstruct;
import jakarta.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service()
public class ScheduledBackupService {

	private static final Logger LOG = LoggerFactory.getLogger(ScheduledBackupService.class);

	@Autowired
	private PgEnvironment pgEnvironment;

	@Autowired
	private S3BackupService s3BackupService;

	@Autowired
	private DbBackupService dbBackupService;

    @Autowired
    private SlackService slackService;

    @Value("${pg.activeLanguages}")
    private String[] activeLanguagesArray;
    private List<Language> activeLanguages;

    @PostConstruct
    public void init() {
        this.activeLanguages = Arrays.stream(activeLanguagesArray)
                .map(Language::valueOf)
                .collect(Collectors.toList());
    }

	@Scheduled(cron = "${pg.backup.cron.rumantschgrischun}")
	public void backupRumantschgrischun() {
		backup(Language.RUMANTSCHGRISCHUN);
	}

	@Scheduled(cron = "${pg.backup.cron.surmiran}")
	public void backupSurmiran() {
		backup(Language.SURMIRAN);
	}

    @Scheduled(cron = "${pg.backup.cron.sursilvan}")
    public void backupSursilvan() {
        backup(Language.SURSILVAN);
    }

	@Scheduled(cron = "${pg.backup.cron.sutsilvan}")
	public void backupSutsilvan() {
		backup(Language.SUTSILVAN);
	}

	@Scheduled(cron = "${pg.backup.cron.puter}")
	public void backupPuter() {
		backup(Language.PUTER);
	}

	@Scheduled(cron = "${pg.backup.cron.vallader}")
	public void backupVallader() {
		backup(Language.VALLADER);
	}

	@Scheduled(cron = "${pg.backup.cron.users}")
	public void backupUsers() throws JAXBException, IOException, NoSuchAlgorithmException {
		backupUserDb();
	}

    @Scheduled(cron = "${pg.backup.cron.names}")
    public void backupNames() throws JAXBException, IOException, NoSuchAlgorithmException {
        backupNamesDb();
    }

    @Scheduled(cron = "${pg.backup.cron.registrations}")
    public void backupRegistrations() throws JAXBException, IOException, NoSuchAlgorithmException {
        backupRegistrationsDb();
    }

	private void backup(Language language) {
		LOG.info("started scheduled backup for: " + language);

		String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);

		String backupFileName = buildName(dbName);
		LOG.info("backup file name created: {}", backupFileName);

		File backupFile = buildBackup(pgEnvironment.getBackupLocation(), backupFileName, language);
		backupFile.mkdirs();
		LOG.info("backup file created: {}", backupFile.getAbsolutePath());

		s3BackupService.uploadFile(dbName, backupFile);

		cleanup(dbName);

		LOG.info("finished scheduled backup");
	}

	private void backupUserDb() throws IOException {
		LOG.info("started backup for users table");
		String backupFileName = buildName("users");
		LOG.info("backup file name created: {}", backupFileName);


		File backupDir = new File(pgEnvironment.getBackupLocation());
		backupDir.mkdirs();
		File backupFile = new File(backupDir, backupFileName);
		backupFile.getParentFile().mkdirs();
		dbBackupService.backupUsers(new FileOutputStream(backupFile), backupFileName);

		LOG.info("user backup created: {}", backupFile.getAbsolutePath());

		s3BackupService.uploadFile("users", backupFile);

        cleanup("users");

        LOG.info("finished scheduled backup for users table");
	}

    private void backupNamesDb() throws IOException {
        LOG.info("started backup for names table");
        String backupFileName = buildName("names");
        LOG.info("backup file name created: {}", backupFileName);


        File backupDir = new File(pgEnvironment.getBackupLocation());
        backupDir.mkdirs();
        File backupFile = new File(backupDir, backupFileName);
        backupFile.getParentFile().mkdirs();
        dbBackupService.backupNames(new FileOutputStream(backupFile), backupFileName);

        LOG.info("names backup created: {}", backupFile.getAbsolutePath());

        s3BackupService.uploadFile("names", backupFile);

        cleanup("names");

        LOG.info("finished scheduled backup for names table");
    }

    private void backupRegistrationsDb() throws IOException {
        LOG.info("started backup for registrations table");

        activeLanguages.forEach(language -> {
            String fileName = "registrations_" + language.name().toLowerCase();
            String backupFileName = buildName(fileName);
            LOG.info("backup file name created: {}", backupFileName);

            File backupDir = new File(pgEnvironment.getBackupLocation());
            backupDir.mkdirs();
            File backupFile = new File(backupDir, backupFileName);
            backupFile.getParentFile().mkdirs();
            try {
                dbBackupService.backupRegistrations(language, new FileOutputStream(backupFile), backupFileName);
            } catch (FileNotFoundException e) {
                LOG.error("backup file not found: {}", backupFile.getAbsolutePath());
                slackService.sendMessage(String.format("Backup file not found: %s", backupFile.getAbsolutePath()), "Impossible to backup registrations", SlackService.SlackMessageType.ERROR);
            }

            LOG.info("registrations backup created: {}", backupFile.getAbsolutePath());

            s3BackupService.uploadFile(fileName, backupFile);

            cleanup(fileName);
        });

        LOG.info("finished scheduled backup");
    }

	private File buildBackup(String dir, String backupFileName, Language language) {
		File backupDir = new File(dir);
		backupDir.mkdirs();
		File backupFile = new File(dir, backupFileName);
		try {
			dbBackupService.backupLanguage(language, new FileOutputStream(backupFile), backupFileName);
		} catch (Exception e) {
			LOG.error("error occured...", e);
		}
		return backupFile;
	}

	private void cleanup(String dbName) {
		List<File> backupFiles = dbBackupService.listBackupFilesAsc(pgEnvironment.getBackupLocation() + "/" + dbName);
		while (backupFiles.size() > Integer.parseInt(pgEnvironment.getBackupNumber())) {
			boolean delete = backupFiles.get(backupFiles.size() - 1).delete();
			if (!delete) {
				throw new RuntimeException(String.format("could not delete obsolete backup file: %s", backupFiles.get(0).getAbsolutePath()));
			}
			backupFiles = dbBackupService.listBackupFilesAsc(pgEnvironment.getBackupLocation() + "/" + dbName);
		}

		LOG.info("list of backupFiles...");
		for (File file : backupFiles) {
			LOG.info("{}", file);
		}
	}

	private String buildName(String dbName) {
		return String.format("%s/%s_db_dump_%s.zip", dbName, dbName, new SimpleDateFormat(
				"yyyy-MM-dd_HH-mm-ss").format(new Date()));
	}
}
