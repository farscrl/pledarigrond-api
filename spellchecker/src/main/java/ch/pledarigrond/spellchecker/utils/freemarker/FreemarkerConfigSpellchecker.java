package ch.pledarigrond.spellchecker.utils.freemarker;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public enum FreemarkerConfigSpellchecker {
    INSTANCE;
    private final Configuration configuration;

    private FreemarkerConfigSpellchecker() {
        ClassTemplateLoader loader = new ClassTemplateLoader(this.getClass(),"/");
        configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setTemplateLoader(loader);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
    }

    public static Configuration getConfig() {
        return INSTANCE.configuration;
    }
}
