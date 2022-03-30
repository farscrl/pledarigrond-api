package ch.pledarigrond.api.config;

import ch.pledarigrond.api.converters.StringToLanguageConverter;
import ch.pledarigrond.api.converters.StringToSearchDirectionConverter;
import ch.pledarigrond.api.converters.StringToSearchMethodConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToSearchDirectionConverter());
        registry.addConverter(new StringToSearchMethodConverter());
        registry.addConverter(new StringToLanguageConverter());
        WebMvcConfigurer.super.addFormatters(registry);
    }
}
