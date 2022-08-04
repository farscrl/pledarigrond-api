package ch.pledarigrond.api.utils.freemarker;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public enum FreemarkerConfigSpellchecker {
    INSTANCE;
    private Configuration cfg;

    private FreemarkerConfigSpellchecker() {
        ClassTemplateLoader loader = new ClassTemplateLoader(this.getClass(),"/spellchecker");
        cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setTemplateLoader(loader);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
    }

    public static Configuration getConfig() {
        return INSTANCE.cfg;
    }
}
