package ch.pledarigrond.api.services;

import java.io.File;

public interface BunnyService {
    void uploadFile(String fileName, File file);

    void deleteFile(String fileName);
}
