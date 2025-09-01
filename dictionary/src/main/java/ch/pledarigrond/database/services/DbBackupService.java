package ch.pledarigrond.database.services;

import ch.pledarigrond.common.data.backup.BackupInfos;
import ch.pledarigrond.common.data.common.Language;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface DbBackupService {
    void backupLanguage(Language language, OutputStream output, String fileName);
    void restoreLanguage(Language language, InputStream input);
    void backupUsers(OutputStream output, String fileName);
    void restoreUsers(InputStream input);
    void backupRegistrations(Language language, OutputStream output, String fileName);
    void restoreRegistrations(Language language, InputStream input);
    void backupNames(OutputStream output, String fileName);
    void restoreNames(InputStream input);

    BackupInfos getBackupInfos(Language language);
    File getBackupFile(Language language, String fileName);

    List<File> listBackupFilesAsc(String backupDir);
}
