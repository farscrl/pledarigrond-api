package ch.pledarigrond.database.config;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.RequestContext;
import ch.pledarigrond.database.dictionary.entities.Entry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DictionaryIndexInitializer {

    @Value("${pg.activeLanguages}")
    private String[] activeLanguagesArray;
    private List<Language> activeLanguages;

    private final MongoTemplate template;

    public DictionaryIndexInitializer(@Qualifier("dictionaryMongoTemplate") MongoTemplate template) {
        this.template = template;
    }

    @PostConstruct
    public void init() {
        this.activeLanguages = Arrays.stream(activeLanguagesArray)
                .map(Language::valueOf)
                .collect(Collectors.toList());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void ensureIndexes() {
        for (Language language : activeLanguages) {
            generateIndexes(language);
        }
    }

    private void generateIndexes(Language language) {
        RequestContext.setLanguage(language);

        IndexOperations ops = template.indexOps(Entry.class);

        // index on publicationStatus, current.timestamp, _id
        ops.ensureIndex(new Index()
                .on("publicationStatus", Sort.Direction.ASC)
                .on("current.timestamp", Sort.Direction.ASC)
                .on("_id", Sort.Direction.ASC)
                .named("pubStatus_current_timestamp__id"));

        // index on publicationStatus, suggestions.timestamp (multikey), _id
        ops.ensureIndex(new Index()
                .on("publicationStatus", Sort.Direction.ASC)
                .on("suggestions.timestamp", Sort.Direction.ASC) // multikey
                .on("_id", Sort.Direction.ASC)
                .named("pubStatus_suggestions_timestamp__id"));

        ops.ensureIndex(new Index()
                .on("publicationStatus", Sort.Direction.ASC)
                .on("current.creatorRole", Sort.Direction.ASC)
                .on("_id", Sort.Direction.ASC)
                .named("pubStatus_current_creatorRole__id"));

        ops.ensureIndex(new Index()
                .on("publicationStatus", Sort.Direction.ASC)
                .on("suggestions.creatorRole", Sort.Direction.ASC)
                .on("_id", Sort.Direction.ASC)
                .named("pubStatus_suggestions_creatorRole__id"));

        ops.ensureIndex(new Index()
                .on("publicationStatus", Sort.Direction.ASC)
                .on("current.inflection.inflectionType", Sort.Direction.ASC)
                .on("_id", Sort.Direction.ASC)
                .named("pubStatus_current_inflectionType__id"));

        ops.ensureIndex(new Index()
                .on("publicationStatus", Sort.Direction.ASC)
                .on("suggestions.inflection.inflectionType", Sort.Direction.ASC)
                .on("_id", Sort.Direction.ASC)
                .named("pubStatus_suggestions_inflectionType__id"));

        ops.ensureIndex(new Index()
                .on("publicationStatus", Sort.Direction.ASC)
                .on("current.automaticChange", Sort.Direction.ASC)
                .on("_id", Sort.Direction.ASC)
                .named("pubStatus_current_automaticChange__id"));

        ops.ensureIndex(new Index()
                .on("publicationStatus", Sort.Direction.ASC)
                .on("suggestions.automaticChange", Sort.Direction.ASC)
                .on("_id", Sort.Direction.ASC)
                .named("pubStatus_suggestions_automaticChange__id"));

        ops.ensureIndex(new Index()
                .on("publicationStatus", Sort.Direction.ASC)
                .on("current.inflection.reviewLater", Sort.Direction.ASC)
                .on("_id", Sort.Direction.ASC)
                .named("pubStatus_current_reviewLater__id"));

        ops.ensureIndex(new Index()
                .on("publicationStatus", Sort.Direction.ASC)
                .on("suggestions.inflection.reviewLater", Sort.Direction.ASC)
                .on("_id", Sort.Direction.ASC)
                .named("pubStatus_suggestions_reviewLater__id"));
        
        ops.ensureIndex(new Index()
                .on("publicationStatus", Sort.Direction.ASC)
                .on("current.deStichwort", Sort.Direction.ASC)
                .on("_id", Sort.Direction.ASC)
                .named("pubStatus_current_deStichwort__id"));

        ops.ensureIndex(new Index()
                .on("publicationStatus", Sort.Direction.ASC)
                .on("suggestions.deStichwort", Sort.Direction.ASC)
                .on("_id", Sort.Direction.ASC)
                .named("pubStatus_suggestions_deStichwort__id"));

        ops.ensureIndex(new Index()
                .on("publicationStatus", Sort.Direction.ASC)
                .on("current.rmStichwort", Sort.Direction.ASC)
                .on("_id", Sort.Direction.ASC)
                .named("pubStatus_current_rmStichwort__id"));

        ops.ensureIndex(new Index()
                .on("publicationStatus", Sort.Direction.ASC)
                .on("suggestions.rmStichwort", Sort.Direction.ASC)
                .on("_id", Sort.Direction.ASC)
                .named("pubStatus_suggestions_rmStichwort__id"));
    }
}
