package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.MatomoService;
import ch.pledarigrond.common.data.common.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class MatomoServiceImpl implements MatomoService {

    private static final Logger logger = LoggerFactory.getLogger(MatomoServiceImpl.class);

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${matomo.url}")
    private String matomoUrl;

    @Value("${matomo.siteId}")
    private String siteId;

    @Value("${matomo.token}")
    private String token;

    @Override
    @Async
    public void trackPronunciationDownload(String email, Language language, String ipAddress) {
        trackEvent(
                "pronunciation-download",
                language.getName(),
                email,
                "Pronunciation Download / " + language.getName(),
                ipAddress
        );
    }

    private void trackEvent(String category, String action, String name, String actionName, String ipAddress) {
        try {
            String url = matomoUrl + "/matomo.php";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("idsite", siteId);
            body.add("rec", "1");
            body.add("token_auth", token);
            body.add("url", "https://pledarigrond.ch/api-events/" + category + "/" + action);
            body.add("e_c", category);
            body.add("e_a", action);
            body.add("e_n", name);
            body.add("action_name", actionName);

            if (ipAddress != null && !ipAddress.isEmpty()) {
                body.add("cip", ipAddress);
            }

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
            restTemplate.postForEntity(url, request, String.class);

            logger.info("Matomo event tracked: {}/{}/{} (IP: {})", category, action, name, ipAddress);
        } catch (Exception e) {
            logger.error("Failed to track Matomo event {}/{}/{}", category, action, name, e);
        }
    }
}
