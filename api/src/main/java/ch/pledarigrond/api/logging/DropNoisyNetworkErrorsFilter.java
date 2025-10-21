package ch.pledarigrond.api.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class DropNoisyNetworkErrorsFilter extends Filter<ILoggingEvent> {

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (event == null) return FilterReply.NEUTRAL;
        String msg = event.getFormattedMessage();
        if (msg == null) return FilterReply.NEUTRAL;

        if (msg.contains("Connection reset by peer") || msg.contains("AsyncRequestNotUsableException")) {
            return FilterReply.DENY;
        }
        return FilterReply.NEUTRAL;
    }
}
