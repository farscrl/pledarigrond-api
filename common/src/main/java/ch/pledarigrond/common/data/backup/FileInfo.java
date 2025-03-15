package ch.pledarigrond.common.data.backup;

import lombok.Data;

@Data
public class FileInfo {
    private String absolutePath;
    private String parent;
    private String name;
    private String creationDate;
    private String size;
    private String lastModified;
}
