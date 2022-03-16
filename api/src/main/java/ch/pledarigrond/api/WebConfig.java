package ch.pledarigrond.api;

import ch.pledarigrond.api.converters.StringToSearchDirectionConverter;
import ch.pledarigrond.api.converters.StringToSearchMethodConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToSearchDirectionConverter());
        registry.addConverter(new StringToSearchMethodConverter());
        WebMvcConfigurer.super.addFormatters(registry);
    }
}
