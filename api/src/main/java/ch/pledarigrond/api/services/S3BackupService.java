package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;

import java.io.File;

public interface S3BackupService {

    void uploadFile(String dbName, File file);
}
