package ch.pledarigrond.dictionary.services;

import ch.pledarigrond.common.data.backup.BackupInfos;
import ch.pledarigrond.common.data.common.Language;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface DbBackupService {
    void backup(Language language, OutputStream output, String fileName);
    void restore(Language language, InputStream input);

    BackupInfos getBackupInfos(Language language);
    File getBackupFile(Language language, String fileName);
}
