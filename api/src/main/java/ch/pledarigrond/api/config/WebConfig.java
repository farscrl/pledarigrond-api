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

    /** disable CORS for all requests */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        long MAX_AGE_SECS = 3600;
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECS);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToSearchDirectionConverter());
        registry.addConverter(new StringToSearchMethodConverter());
        registry.addConverter(new StringToLanguageConverter());
        WebMvcConfigurer.super.addFormatters(registry);
    }
}
