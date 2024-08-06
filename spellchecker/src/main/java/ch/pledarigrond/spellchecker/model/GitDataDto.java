package ch.pledarigrond.spellchecker.model;

import lombok.Data;

@Data
public class GitDataDto {
    private String repoPath;
    private String remoteUrl;
    private String remoteBranch;
    private String gitToken;

    public GitDataDto(String repoPath, String remoteUrl, String remoteBranch, String gitToken) {
        this.repoPath = repoPath;
        this.remoteUrl = remoteUrl;
        this.remoteBranch = remoteBranch;
        this.gitToken = gitToken;
    }
}
