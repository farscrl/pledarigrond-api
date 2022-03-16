package ch.pledarigrond.common.data.common;

public enum SearchDirection {
    BOTH("both"),
    ROMANSH("romansh"),
    GERMAN("german");

    private String direction;

    SearchDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
