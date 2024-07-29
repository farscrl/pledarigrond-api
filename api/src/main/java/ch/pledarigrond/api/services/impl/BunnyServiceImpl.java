package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.BunnyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class BunnyServiceImpl implements BunnyService {

    private static final Logger logger = LoggerFactory.getLogger(BunnyServiceImpl.class);

    @Value("${bunny.zoneName}")
    private String zoneName;

    @Value("${bunny.apiKey}")
    private String apiKey;

    @Override
    public void uploadFile(String fileName, File file) {
        try {
            String urlStr = "https://storage.bunnycdn.com/" + zoneName + "/" + fileName;

            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("AccessKey", apiKey);
            connection.setRequestProperty("Content-Type", "application/octet-stream");
            connection.setDoOutput(true);
            
            try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                 BufferedOutputStream outputStream = new BufferedOutputStream(connection.getOutputStream())) {

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            int responseCode = connection.getResponseCode();
            String responseMsg = connection.getResponseMessage();
            System.out.println("Response: " + responseCode + " " + responseMsg);

            if (responseCode != 201) {
                throw new RuntimeException("Failed to upload file: " + responseCode + " " + responseMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFile(String fileName) {
        try {
            String urlStr = "https://storage.bunnycdn.com/" + zoneName + "/" + fileName;

            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("AccessKey", apiKey);

            int responseCode = connection.getResponseCode();

            if (responseCode != 200) {
                if (responseCode == 404) {
                    logger.info("File does not exist: {}", fileName);
                } else {
                    throw new RuntimeException("Failed to delete file: " + responseCode + " " + connection.getResponseMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
