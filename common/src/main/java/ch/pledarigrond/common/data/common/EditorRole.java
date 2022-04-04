package ch.pledarigrond.common.data.common;

public enum EditorRole {
    ADMIN("admin"),
    EDITOR("editor"),
    GUEST("guest"),
    NONE("none");

    public final String role;

    private EditorRole(String role) {
        this.role = role;
    }
}
