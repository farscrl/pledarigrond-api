package ch.pledarigrond.common.data.backup;

import lombok.Data;

import java.util.List;

@Data
public class BackupInfos {

    private List<FileInfo> infos;
    private String backupLocation;
    private String backupNumber;
    private String triggerTime;
}
