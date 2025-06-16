package ch.pledarigrond.api.services;

import lombok.Getter;

public interface SlackService {
    @Getter
    public enum SlackMessageType {
        INFO("ℹ️"),
        SUCCESS("✅"),
        WARNING("⚠️"),
        ERROR("🚨");

        private final String emoji;

        SlackMessageType(String emoji) {
            this.emoji = emoji;
        }
    }

    void sendMessage(String title, String body, SlackMessageType type);

    void sendException(Exception ex, String title);
}
