package ch.pledarigrond.api.interceptors;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.RequestContext;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RequestDataInterceptor implements HandlerInterceptor {

    private static final Pattern languagePattern = Pattern.compile("/(puter|rumantschgrischun|surmiran|sursilvan|sutsilvan|vallader)(/|$)");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String uri = request.getRequestURI();

        // Check for language code in the URI
        Matcher matcher = languagePattern.matcher(uri);
        if (matcher.find()) {
            String language = matcher.group(1);
            RequestContext.setLanguage(Language.valueOf(language.toUpperCase()));
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        // Clean up the thread local to avoid memory leaks
        RequestContext.clear();
    }
}
