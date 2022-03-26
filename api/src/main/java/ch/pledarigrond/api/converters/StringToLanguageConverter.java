package ch.pledarigrond.api.converters;

import ch.pledarigrond.common.data.common.Language;
import org.springframework.core.convert.converter.Converter;

public class StringToLanguageConverter implements Converter<String, Language> {

    @Override
    public Language convert(String source) {
        try {
            return Language.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Language.RUMANTSCHGRISCHUN;
        }
    }
}
