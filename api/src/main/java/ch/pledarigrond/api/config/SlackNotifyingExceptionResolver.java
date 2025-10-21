package ch.pledarigrond.api.config;

import ch.pledarigrond.api.services.SlackService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SlackNotifyingExceptionResolver implements HandlerExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(SlackNotifyingExceptionResolver.class);

    private final SlackService slack;

    public SlackNotifyingExceptionResolver(SlackService slack) {
        this.slack = slack;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {

        log.error("Unhandled exception caught by SlackNotifyingExceptionResolver", ex);
        slack.sendException(ex, "Unhandled exception");
        return null;
    }
}
