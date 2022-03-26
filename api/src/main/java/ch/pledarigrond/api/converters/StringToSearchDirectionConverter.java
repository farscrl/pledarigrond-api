package ch.pledarigrond.api.converters;

import ch.pledarigrond.common.data.common.SearchDirection;
import org.springframework.core.convert.converter.Converter;

public class StringToSearchDirectionConverter implements Converter<String, SearchDirection> {
    @Override
    public SearchDirection convert(String source) {
        try {
            return SearchDirection.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return SearchDirection.BOTH;
        }
    }
}
