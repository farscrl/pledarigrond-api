package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.EmailService;
import ch.pledarigrond.common.data.common.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${mailgun.api.key}")
    private String apiKey;

    @Value("${mailgun.domain}")
    private String domain;

    private static final String FROM_EMAIL = "Server Pledari Grond <noreply@mailing.pledarigrond.ch>";

    @Override
    @Async
    public void sendPronunciationDownloadLink(String email, String link, Language language) {
        try {
            String url = "https://api.eu.mailgun.net/v3/" + domain + "/messages";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setBasicAuth("api", apiKey);

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("from", FROM_EMAIL);
            body.add("to", email);
            body.add("subject", "Pledari Grond - Download pronunzia");
            body.add("text", buildEmailText(link, language));

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                logger.info("Pronunciation download email sent to: {} for language: {}", email, language);
            } else {
                logger.error("Failed to send email. Status: {}, Response: {}", response.getStatusCode(), response.getBody());
            }
        } catch (Exception e) {
            logger.error("Failed to send pronunciation download email to: {}", email, e);
        }
    }

    private String buildEmailText(String link, Language language) {
        String idiomName = getIdiomDisplayName(language);
        return """
            Stimada utilisadra, prezià utilisader

            Grazia fitg per Voss interess da las datotecas da pronunzia dal Pledari Grond.

            Vus avais dumandà las datotecas per l’idiom: %s

            Qua las pudais Vus telechargiar:
            %s

            Il link è valaivel durant las proximas 60 minutas.

            Cordials salids
            la squadra dal Pledari Grond
            """.formatted(idiomName, link);
    }

    private String getIdiomDisplayName(Language language) {
        return switch (language) {
            case PUTER -> "Puter";
            case RUMANTSCHGRISCHUN -> "Rumantsch Grischun";
            case SURMIRAN -> "Surmiran";
            case SURSILVAN -> "Sursilvan";
            case SUTSILVAN -> "Sutsilvan";
            case VALLADER -> "Vallader";
        };
    }
}
