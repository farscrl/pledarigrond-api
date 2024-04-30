package ch.pledarigrond.common.data.common;

public class RequestContext {

    private static final ThreadLocal<Language> language = new ThreadLocal<>();

    public static void setLanguage(Language lang) {
        language.set(lang);
    }

    public static Language getLanguage() {
        return language.get();
    }

    public static void clear() {
        language.remove();
    }

}
