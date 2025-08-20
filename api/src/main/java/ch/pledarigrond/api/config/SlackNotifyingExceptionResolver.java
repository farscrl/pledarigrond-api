package ch.pledarigrond.api.config;

import ch.pledarigrond.api.services.SlackService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SlackNotifyingExceptionResolver implements HandlerExceptionResolver {

    private final SlackService slack;

    public SlackNotifyingExceptionResolver(SlackService slack) {
        this.slack = slack;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {
        slack.sendException(ex, "Unhandled exception");
        return null;
    }
}
