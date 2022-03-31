package ch.pledarigrond.common.data.common;

public enum EditorRole {
    EDITOR("editor"),
    NONE("none");

    public final String role;

    private EditorRole(String role) {
        this.role = role;
    }
}
