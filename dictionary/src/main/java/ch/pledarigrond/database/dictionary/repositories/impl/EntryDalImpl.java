package ch.pledarigrond.database.dictionary.repositories.impl;

import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.dictionary.DbSearchCriteria;
import ch.pledarigrond.common.data.dictionary.DictionaryStatisticsDto;
import ch.pledarigrond.common.data.dictionary.NormalizedEntryVersionsDto;
import ch.pledarigrond.database.dictionary.repositories.EntryDal;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class EntryDalImpl implements EntryDal {

    @Autowired
    @Qualifier("dictionaryMongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public Page<NormalizedEntryVersionsDto> queryForEntries(
            DbSearchCriteria queryData,
            int pageSize,
            int page
    ) {
        Pageable pageable = PageRequest.of(page, pageSize);
        long skip = pageable.getOffset();

        // criteria on entry level
        AggregationOperation entryMatch = Aggregation.match(getEntryLevelCriteria(queryData));

        // sorting
        final String sortField = queryData.getSortColumn();
        final boolean asc = queryData.isSortAscending();
        final Sort.Direction dir = asc ? Sort.Direction.ASC : Sort.Direction.DESC;

        AggregationOptions options = AggregationOptions.builder()
                .allowDiskUse(true) // safe for large sorts
                .build();

        Aggregation agg;

        switch (queryData.getState()) {
            case HAS_SUGGESTION: {
                // prefilter suggestions (this can use the index and speed things up)
                Criteria elemCriteria = getEntryVersionLevelCriteria(queryData, "");
                Document elemDoc = elemCriteria.getCriteriaObject();
                List<AggregationOperation> ops = new ArrayList<>();
                ops.add(entryMatch);
                if (!elemDoc.isEmpty()) {
                    ops.add(ctx -> new Document("$match",
                            new Document("suggestions", new Document("$elemMatch", elemDoc))));
                }

                // unwind suggestions and filter again
                ops.add(unwind("suggestions"));
                Criteria afterUnwind = getEntryVersionLevelCriteria(queryData, "suggestions.");
                Document afterDoc = afterUnwind.getCriteriaObject();
                if (!afterDoc.isEmpty()) {
                    ops.add(ctx -> new Document("$match", afterDoc));
                }

                // facet for pagination
                ProjectionOperation toDto = project()
                        .and("_id").as("entryId")
                        .and("suggestions").as("version")
                        .andExclude("_id"); // OK to exclude only _id in inclusion projection

                SortOperation sortOp = sort(Sort.by(
                        new Sort.Order(dir, "suggestions." + sortField),
                        new Sort.Order(dir, "_id")
                ));

                FacetOperation facet = facet(sortOp, skip(skip), limit(pageSize), toDto).as("data")
                        .and(count().as("totalCount")).as("meta");

                ops.add(facet);

                agg = newAggregation(ops).withOptions(options);
                break;
            }

            case PUBLISHED: {
                List<AggregationOperation> ops = new ArrayList<>();
                ops.add(entryMatch);

                Criteria currentCrit = getEntryVersionLevelCriteria(queryData, "current.");
                Document currentDoc = currentCrit.getCriteriaObject();
                if (!currentDoc.isEmpty()) {
                    ops.add(ctx -> new Document("$match", currentDoc));
                }

                ProjectionOperation toDto = project()
                        .and("_id").as("entryId")
                        .and("current").as("version")
                        .andExclude("_id");

                SortOperation sortOp = sort(Sort.by(
                        new Sort.Order(dir, "current." + sortField),
                        new Sort.Order(dir, "_id")
                ));

                FacetOperation facet = facet(sortOp, skip(skip), limit(pageSize), toDto).as("data")
                        .and(count().as("totalCount")).as("meta");

                ops.add(facet);

                agg = newAggregation(ops).withOptions(options);
                break;
            }

            default:
                return new PageImpl<>(Collections.emptyList(), pageable, 0L);
        }


        AggregationResults<Document> raw = mongoTemplate.aggregate(agg, "entries", Document.class);
        Document root = raw.getUniqueMappedResult();
        if (root == null) return new PageImpl<>(List.of(), pageable, 0L);

        @SuppressWarnings("unchecked")
        List<Document> dataDocs = (List<Document>) root.getOrDefault("data", List.of());
        @SuppressWarnings("unchecked")
        List<Document> metaDocs = (List<Document>) root.getOrDefault("meta", List.of());

        long total = 0L;
        if (!metaDocs.isEmpty()) {
            Number n = (Number) metaDocs.get(0).getOrDefault("totalCount", 0);
            total = n.longValue();
        }

        List<NormalizedEntryVersionsDto> content = dataDocs.stream()
                .map(d -> mongoTemplate.getConverter().read(NormalizedEntryVersionsDto.class, d))
                .toList();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public DictionaryStatisticsDto getStatistics() {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.group()
                        .count().as("numberOfEntries")

                        .sum( ArrayOperators.Size.lengthOfArray(
                                ConditionalOperators.ifNull("versions").then(Collections.emptyList())
                        )).as("numberOfVersions")

                        .sum( ArrayOperators.Size.lengthOfArray(
                                ConditionalOperators.ifNull("suggestions").then(Collections.emptyList())
                        )).as("numberOfSuggestions")

                        .sum(
                                ConditionalOperators.when(
                                        ConditionalOperators.ifNull("current").then(false)
                                ).then(1).otherwise(0)
                        ).as("numberOfApproved"),
                project("numberOfEntries","numberOfVersions","numberOfSuggestions","numberOfApproved")
        );

        return mongoTemplate.aggregate(agg, "entries", DictionaryStatisticsDto.class)
                        .getUniqueMappedResult();
    }

    private Criteria getEntryLevelCriteria(DbSearchCriteria queryData) {
        List<Criteria> criteriaList = new ArrayList<>();

        if (queryData.getState() != null) {
            criteriaList.add(Criteria.where("publicationStatus").is(queryData.getState()));
        }

        Criteria finalCriteria = new Criteria();
        if (!criteriaList.isEmpty()) {
            finalCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
        }

        return finalCriteria;
    }

    private Criteria getEntryVersionLevelCriteria(DbSearchCriteria queryData, String prefix) {
        List<Criteria> criteriaList = new ArrayList<>();

        if (queryData.getSearchPhrase() != null && !queryData.getSearchPhrase().isEmpty()) {
            String sanitizedPhrase = Pattern.quote(queryData.getSearchPhrase());
            String regexPattern = switch (queryData.getSearchMethod()) {
                case SUFFIX -> sanitizedPhrase + "$";
                case INTERN -> sanitizedPhrase;
                default -> "^" + sanitizedPhrase;
            };

            if (queryData.getSearchDirection() == SearchDirection.ROMANSH) {
                criteriaList.add(Criteria.where(path(prefix, "rmStichwort")).regex(regexPattern, "i"));
            } else if (queryData.getSearchDirection() == SearchDirection.GERMAN) {
                criteriaList.add(Criteria.where(path(prefix, "deStichwort")).regex(regexPattern, "i"));
            } else {
                criteriaList.add(new Criteria().orOperator(
                        Criteria.where(path(prefix, "rmStichwort")).regex(regexPattern, "i"),
                        Criteria.where(path(prefix, "deStichwort")).regex(regexPattern, "i")
                ));
            }
        }

        if (queryData.getUserOrIp() != null && !queryData.getUserOrIp().trim().isEmpty()) {
            criteriaList.add(new Criteria().orOperator(
                    Criteria.where(path(prefix, "creator")).is(queryData.getUserOrIp()),
                    Criteria.where(path(prefix, "creatorIp")).is(queryData.getUserOrIp())
            ));
        }

        if (queryData.getRole() != null) {
            criteriaList.add(Criteria.where(path(prefix, "creatorRole")).is(queryData.getRole().toString()));
        }

        if (queryData.getStartTime() > 0 && queryData.getEndTime() > 0) {
            criteriaList.add(Criteria.where(path(prefix, "timestamp"))
                    .gt(new Date(queryData.getStartTime()))
                    .lt(new Date(queryData.getEndTime())));
        } else if (queryData.getStartTime() > 0) {
            criteriaList.add(Criteria.where(path(prefix, "timestamp"))
                    .gt(new Date(queryData.getStartTime())));
        } else if (queryData.getEndTime() > 0) {
            criteriaList.add(Criteria.where(path(prefix, "timestamp"))
                    .lt(new Date(queryData.getEndTime())));
        }

        if (queryData.isExcludeAutomaticChanges()) {
            criteriaList.add(new Criteria().orOperator(
                    Criteria.where(path(prefix, "automaticChange")).is(false),
                    Criteria.where(path(prefix, "automaticChange")).exists(false)
            ));
        }

        if (queryData.getOnlyAutomaticChanged()) {
            criteriaList.add(Criteria.where(path(prefix, "automaticChange")).is(true));
        }

        if (queryData.getInflectionType() != null) {
            criteriaList.add(Criteria.where(path(prefix, "inflection.inflectionType"))
                    .is(queryData.getInflectionType().toString()));
        }

        if (queryData.getShowReviewLater() != null) {
            if (queryData.getShowReviewLater()) {
                criteriaList.add(Criteria.where(path(prefix, "inflection.reviewLater")).is(true));
            } else {
                criteriaList.add(new Criteria().orOperator(
                        Criteria.where(path(prefix, "inflection.reviewLater")).is(false),
                        Criteria.where(path(prefix, "inflection.reviewLater")).exists(false)
                ));
            }
        }

        return criteriaList.isEmpty()
                ? new Criteria() // matches everything
                : new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
    }

    private static String path(String prefix, String field) {
        return (prefix == null || prefix.isEmpty()) ? field : prefix + field;
    }
}
