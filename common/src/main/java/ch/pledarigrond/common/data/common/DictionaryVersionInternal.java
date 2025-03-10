package ch.pledarigrond.common.data.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
public class DictionaryVersionInternal extends DictionaryVersion {
    private Instant timestamp;
    private VersionStatus versionStatus;

    private String userComment;
    private String userEmail;

    private String creator;
    private String creatorIp;
    private EditorRole creatorRole;
    private String verifier;
}
