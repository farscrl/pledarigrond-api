package ch.pledarigrond.api.services;

import java.io.File;

public interface S3BackupService {

    void uploadFile(File file);
}
