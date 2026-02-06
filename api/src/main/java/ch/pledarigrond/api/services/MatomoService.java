package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;

public interface MatomoService {
    void trackPronunciationDownload(String email, Language language, String ipAddress);
}
