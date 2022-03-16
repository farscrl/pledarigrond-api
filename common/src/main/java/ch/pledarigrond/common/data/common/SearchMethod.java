package ch.pledarigrond.common.data.common;

public enum SearchMethod {
    NORMAL("normal"),
    INTERN("intern"),
    PREFIX("prefix"),
    SUFFIX("suffix"),
    EXACT("exact");

    private String method;

    SearchMethod(String method) {
        this.method = method;
    }



    public String getMethod() {
        return method;
    }
}
