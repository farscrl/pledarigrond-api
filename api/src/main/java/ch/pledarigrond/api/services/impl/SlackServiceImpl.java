package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.SlackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SlackServiceImpl implements SlackService {

    private static final Logger logger = LoggerFactory.getLogger(SlackServiceImpl.class);

    @Value("${slack.webhook.url}")
    private String webhookUrl;

    @Value("${spring.profiles.active:unknown}")
    private String activeProfile;

    private final RestTemplate restTemplate = new RestTemplate();

    @Async
    public void sendMessage(String title, String body, SlackMessageType type) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("blocks", List.of(
                Map.of("type", "section", "text", Map.of("type", "mrkdwn", "text", "*" + type.getEmoji() + " " + title + "*")),
                Map.of("type", "context", "elements", List.of(
                        Map.of("type", "mrkdwn", "text", "*Environment:* `" + activeProfile + "` • *Time:* `" + LocalDateTime.now() + "`")
                )),
                Map.of("type", "section", "text", Map.of("type", "mrkdwn", "text", body)),
                Map.of("type", "divider")
        ));

        postToSlack(payload);
    }

    @Async
    public void sendException(Exception ex, String title) {
        // do not send exceptions in dev profile
        if ("dev".equals(activeProfile)) {
            return;
        }

        String header = ex.toString();

        String stackTrace = Arrays.stream(ex.getStackTrace())
                .limit(10)
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n"));

        // add a marker if it was truncated
        if (ex.getStackTrace().length > 10) {
            stackTrace += "\n... (truncated)";
        }

        String body = String.format("*Exception:* `%s`\n```%s\n%s```", ex.getMessage(), header, stackTrace);

        sendMessage(title != null ? title : "Unhandled Exception", body, SlackMessageType.ERROR);
    }

    private void postToSlack(Map<String, Object> payload) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);
            restTemplate.postForEntity(webhookUrl, entity, String.class);
        } catch (Exception e) {
            logger.error("Failed to send Slack message: {}", e.getMessage());
        }
    }
}
