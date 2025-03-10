package ch.pledarigrond.common.data.common;

public enum VersionStatus {
    REJECTED ("Rejected"),
    ACCEPTED("Accepted"),
    UNVERIFIED ("Unverified");

    private final String name;

    private VersionStatus(String name) {
        this.name = name;
    }

    public String getStateName() {
        return name;
    }
}
