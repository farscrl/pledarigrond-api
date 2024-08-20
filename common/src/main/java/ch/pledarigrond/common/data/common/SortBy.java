package ch.pledarigrond.common.data.common;

public enum SortBy {
    GERMAN("german"),
    ROMANSH("romansh");

    private String sortBy;

    SortBy(String sortBy) {
        this.sortBy = sortBy;
    }


    public String getSortBy() {
        return sortBy;
    }
}
