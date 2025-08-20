package ch.pledarigrond.database.dictionary.repositories.impl;

import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.dictionary.DbSearchCriteria;
import ch.pledarigrond.common.data.dictionary.NormalizedEntryVersionsDto;
import ch.pledarigrond.database.dictionary.repositories.EntryDal;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.regex.Pattern;

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


        // Stage 1: $match based on Entry fields
        AggregationOperation matchStage = Aggregation.match(getEntryLevelCriteria(queryData));

        // Stage 2: $addFields version by concatenating arrays
        AggregationOperation setStage2 = switch (queryData.getState()) {
            case HAS_SUGGESTION -> context -> new Document("$addFields",
                    new Document("version", "$suggestions")
            );
            case PUBLISHED -> context -> new Document("$addFields",
                    new Document("version", List.of("$current"))
            );
            default -> context -> new Document("$addFields",
                    new Document("version", Collections.emptyList())
            );
        };


        // Stage 3: $project unwanted fields
        AggregationOperation unsetStage = context -> new Document("$project",
                new Document("current", 0)
                        .append("versions", 0)
                        .append("_class", 0)
                        .append("suggestions", 0)
        );

        // Stage 4: $unwind suggestions
        AggregationOperation unwindStage = context -> new Document("$unwind", "$version");

        // Stage 5: $match publicationStatus "MODIFIED"
        AggregationOperation filterStage = Aggregation.match(getEntryVersionLevelCriteria(queryData));

        // Stage 6: $addFields entryId
        AggregationOperation renameIdStage = context -> new Document("$addFields",
                new Document("entryId", "$_id")
        );

        // Combine all stages into the aggregation pipeline for the result page
        List<AggregationOperation> operationsFilter = Arrays.asList(
                matchStage,
                setStage2,
                unsetStage,
                unwindStage,
                filterStage,
                renameIdStage,

                Aggregation.sort(queryData.isSortAscending() ? Sort.Direction.ASC : Sort.Direction.DESC, "version." + queryData.getSortColumn()),

                Aggregation.skip(skip),
                Aggregation.limit(pageSize)
        );
        Aggregation aggregationFilter = Aggregation.newAggregation(operationsFilter);

        // Combine all stages into the aggregation pipeline for the total count
        List<AggregationOperation> operationsTotal= Arrays.asList(
                matchStage,
                setStage2,
                unsetStage,
                unwindStage,
                filterStage,

                Aggregation.count().as("totalCount")
        );
        Aggregation aggregationTotal = Aggregation.newAggregation(operationsTotal);

        // get results page
        AggregationResults<NormalizedEntryVersionsDto> results = mongoTemplate.aggregate(aggregationFilter, "entries", NormalizedEntryVersionsDto.class);
        List<NormalizedEntryVersionsDto> versionsDtos = results.getMappedResults();

        // get total amount
        AggregationResults<Document> countResults = mongoTemplate.aggregate(aggregationTotal, "entries", Document.class);
        Document countDoc = countResults.getUniqueMappedResult();
        Number totalCount = countDoc != null ? countDoc.get("totalCount", Number.class) : 0;
        long longTotalCount = totalCount.longValue();

        // return page
        return new PageImpl<>(versionsDtos, pageable, longTotalCount);
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

    private Criteria getEntryVersionLevelCriteria(DbSearchCriteria queryData) {
        List<Criteria> criteriaList = new ArrayList<>();

        if (queryData.getSearchPhrase() != null && !queryData.getSearchPhrase().isEmpty()) {
            String sanitizedPhrase = Pattern.quote(queryData.getSearchPhrase());
            String regexPattern = switch (queryData.getSearchMethod()) {
                case SUFFIX -> sanitizedPhrase + "$";
                case INTERN -> sanitizedPhrase;
                default -> "^" + sanitizedPhrase;
            };

            if (queryData.getSearchDirection() == SearchDirection.ROMANSH) {
                criteriaList.add(
                    Criteria.where("version.rmStichwort").regex(regexPattern, "i")
                );
            } else if (queryData.getSearchDirection() == SearchDirection.GERMAN) {
                criteriaList.add(
                    Criteria.where("version.deStichwort").regex(regexPattern, "i")
                );
            } else {
                criteriaList.add(
                    new Criteria().orOperator(
                        Criteria.where("version.rmStichwort").regex(regexPattern, "i"),
                        Criteria.where("version.deStichwort").regex(regexPattern, "i")
                    )
                );
            }
        }

        if (queryData.getUserOrIp() != null && !queryData.getUserOrIp().trim().isEmpty()) {
            criteriaList.add(
                    new Criteria().orOperator(
                            Criteria.where("version.creator").is(queryData.getUserOrIp()),
                            Criteria.where("version.creatorIp").is(queryData.getUserOrIp())
                    )
            );
        }

        if (queryData.getRole() != null) {
            criteriaList.add(
                    Criteria.where("version.creatorRole").is(queryData.getRole().toString())
            );
        }

        if (queryData.getStartTime() > 0 && queryData.getEndTime() > 0) {
            criteriaList.add(
                    Criteria.where("version.timestamp")
                            .gt(new Date(queryData.getStartTime()))
                            .lt(new Date(queryData.getEndTime()))
            );
        } else if (queryData.getStartTime() > 0) {
            criteriaList.add(
                    Criteria.where("version.timestamp")
                            .gt(new Date(queryData.getStartTime()))
            );
        } else if (queryData.getEndTime() > 0) {
            criteriaList.add(
                    Criteria.where("version.timestamp")
                            .lt(new Date(queryData.getEndTime()))
            );
        }

        if (queryData.isExcludeAutomaticChanges()) {
            criteriaList.add(
                    new Criteria().orOperator(
                            Criteria.where("version.automaticChange").is(false),
                            Criteria.where("version.automaticChange").exists(false)
                    )
            );
        }

        if (queryData.getOnlyAutomaticChanged()) {
            criteriaList.add(
                Criteria.where("version.automaticChange").is(true)
            );
        }

        if (queryData.getInflectionType() != null) {
            criteriaList.add(
                Criteria.where("version.inflection.inflectionType").is(queryData.getInflectionType().toString())
            );
        }

        if (queryData.getShowReviewLater() != null) {
            if (queryData.getShowReviewLater()) {
                criteriaList.add(
                    Criteria.where("version.inflection.reviewLater").is(true)
                );
            } else {
                criteriaList.add(
                    new Criteria().orOperator(
                        Criteria.where("version.inflection.reviewLater").is(false),
                        Criteria.where("version.inflection.reviewLater").exists(false)
                    )
                );
            }
        }

        Criteria finalCriteria = new Criteria();
        if (!criteriaList.isEmpty()) {
            finalCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
        }

        return finalCriteria;
    }
}
