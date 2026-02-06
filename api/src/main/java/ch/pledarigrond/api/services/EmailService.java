package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;

public interface EmailService {
    void sendPronunciationDownloadLink(String email, String link, Language language);
}
