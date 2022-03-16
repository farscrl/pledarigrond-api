package ch.pledarigrond.api.converters;

import ch.pledarigrond.common.data.common.SearchMethod;
import org.springframework.core.convert.converter.Converter;

public class StringToSearchMethodConverter implements Converter<String, SearchMethod> {
    @Override
    public SearchMethod convert(String source) {
        try {
            return SearchMethod.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return SearchMethod.NORMAL;
        }
    }
}
