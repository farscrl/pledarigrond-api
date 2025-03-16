package ch.pledarigrond.api.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface BackupService {
    boolean dumbDb(String db, OutputStream fileOutputStream) throws IOException, InterruptedException;
    boolean restoreDb(String db, InputStream inputStream) throws IOException, InterruptedException;
}
