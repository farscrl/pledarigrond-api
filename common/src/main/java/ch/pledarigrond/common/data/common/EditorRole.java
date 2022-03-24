package ch.pledarigrond.common.data.common;

public enum EditorRole {
    INTERNAL("internal"),
    EXTERNAL("external"),
    NONE("none");

    public final String role;

    private EditorRole(String role) {
        this.role = role;
    }
}
