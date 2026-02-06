package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.BunnyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.Base64;

@Service
public class BunnyServiceImpl implements BunnyService {

    private static final Logger logger = LoggerFactory.getLogger(BunnyServiceImpl.class);

    @Value("${bunny.zoneName}")
    private String zoneName;

    @Value("${bunny.apiKey}")
    private String apiKey;

    @Value("${bunny.secure.zoneName}")
    private String secureZoneName;

    @Value("${bunny.secure.authentication.token}")
    private String secureAuthToken;

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
            logger.error("Failed to upload file", e);
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
            logger.error("Failed to delete file", e);
        }
    }

    @Override
    public String generateSecureUrl(String filePath, long expirationSeconds) {
        try {
            String hostname = "https://" + secureZoneName + ".b-cdn.net";

            String path = filePath.startsWith("/") ? filePath : "/" + filePath;
            long expiresAt = Instant.now().getEpochSecond() + expirationSeconds;

            String signatureBase = secureAuthToken + path + expiresAt;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(signatureBase.getBytes(StandardCharsets.UTF_8));

            String token = Base64.getEncoder().encodeToString(hash)
                    .replace("\n", "")
                    .replace("+", "-")
                    .replace("/", "_")
                    .replace("=", "");

            return String.format("%s%s?token=%s&expires=%d", hostname, path, token, expiresAt);
        } catch (Exception e) {
            throw new RuntimeException("Error generating BunnyCDN link", e);
        }
    }
}
