package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledSuggestionIndexService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledSuggestionIndexService.class);

    private final LuceneService luceneService;

    public ScheduledSuggestionIndexService(LuceneService luceneService) {
        this.luceneService = luceneService;
    }

    @Scheduled(cron = "${pg.index.suggestion_generation}")
    private void exportJSON() throws Exception {
        logger.info("Regenerating suggestion indexes");

        luceneService.regenerateSuggestionIndex(Language.PUTER);
        luceneService.regenerateSuggestionIndex(Language.RUMANTSCHGRISCHUN);
        luceneService.regenerateSuggestionIndex(Language.SURMIRAN);
        luceneService.regenerateSuggestionIndex(Language.SURSILVAN);
        luceneService.regenerateSuggestionIndex(Language.SUTSILVAN);
        luceneService.regenerateSuggestionIndex(Language.VALLADER);

        logger.info("Suggestion indexes regenerated");
    }
}
