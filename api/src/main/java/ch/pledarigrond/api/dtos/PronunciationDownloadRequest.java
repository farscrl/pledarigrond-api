package ch.pledarigrond.api.dtos;

import ch.pledarigrond.common.data.common.Language;
import lombok.Data;

@Data
public class PronunciationDownloadRequest {
    private String email;
    private boolean acceptedTerms;
}
