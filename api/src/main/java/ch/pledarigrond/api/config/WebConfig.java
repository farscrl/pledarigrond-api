package ch.pledarigrond.api.config;

import ch.pledarigrond.api.converters.StringToLanguageConverter;
import ch.pledarigrond.api.converters.StringToSearchDirectionConverter;
import ch.pledarigrond.api.converters.StringToSearchMethodConverter;
import ch.pledarigrond.api.interceptors.RequestDataInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableMongoAuditing
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RequestDataInterceptor requestDataInterceptor;

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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestDataInterceptor);
    }
}
