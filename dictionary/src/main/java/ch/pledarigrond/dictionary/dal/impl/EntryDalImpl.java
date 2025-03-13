package ch.pledarigrond.dictionary.dal.impl;

import ch.pledarigrond.common.data.common.EditorQuery2;
import ch.pledarigrond.dictionary.dal.EntryDal;
import ch.pledarigrond.dictionary.dto.NormalizedEntryVersionsDto;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class EntryDalImpl implements EntryDal {

    @Autowired
    @Qualifier("dictionaryMongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public Page<NormalizedEntryVersionsDto> queryForEntries(
            EditorQuery2 queryData,
            int pageSize,
            int page,
            boolean excludeAutomaticChanges
    ) {
        Pageable pageable = PageRequest.of(page, pageSize);
        long skip = pageable.getOffset();


        // Stage 1: $match based on Entry fields
        AggregationOperation matchStage = Aggregation.match(getEntryLevelCriteria(queryData));

        // Stage 2: $set version by concatenating arrays
        AggregationOperation setStage2 = context -> new Document("$set",
                new Document("version",
                        new Document("$concatArrays", Arrays.asList(
                                "$suggestions",
                                List.of("$current")
                        ))
                )
        );

        // Stage 3: $unset unwanted fields
        AggregationOperation unsetStage = context -> new Document("$unset",
                Arrays.asList("current", "versions", "_class", "suggestions")
        );

        // Stage 4: $unwind suggestions
        AggregationOperation unwindStage = context -> new Document("$unwind", "$version");

        // Stage 5: $match publicationStatus "MODIFIED"
        AggregationOperation filterStage = Aggregation.match(getEntryVersionLevelCriteria(queryData, excludeAutomaticChanges));

        // Combine all stages into the aggregation pipeline for the result page
        List<AggregationOperation> operationsFilter = Arrays.asList(
                matchStage,
                setStage2,
                unsetStage,
                unwindStage,
                filterStage,

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

    private Criteria getEntryLevelCriteria(EditorQuery2 queryData) {
        List<Criteria> criteriaList = new ArrayList<>();

        if (queryData.getState() != null && queryData.getState().length > 0) {
            criteriaList.add(Criteria.where("publicationStatus").in(Arrays.asList(queryData.getState())));
        }

        Criteria finalCriteria = new Criteria();
        if (!criteriaList.isEmpty()) {
            finalCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
        }

        return finalCriteria;
    }

    private Criteria getEntryVersionLevelCriteria(EditorQuery2 queryData, boolean excludeAutomaticChanges) {
        List<Criteria> criteriaList = new ArrayList<>();

        if (queryData.getUserOrIp() != null && !queryData.getUserOrIp().trim().isEmpty()) {
            criteriaList.add(
                    new Criteria().orOperator(
                            Criteria.where("version.creator").is(queryData.getUserOrIp()),
                            Criteria.where("version.creatorIp").is(queryData.getUserOrIp())
                    )
            );
        }

        if (queryData.getVerifier() != null && !queryData.getVerifier().trim().isEmpty()) {
            criteriaList.add(
                    Criteria.where("version.verifier").is(queryData.getVerifier())
            );
        }

        if (queryData.getRole() != null) {
            criteriaList.add(
                    Criteria.where("version.creatorRole").is(queryData.getRole().toString())
            );
        }

        if (queryData.getVersionStatus() != null) {
            criteriaList.add(
                    Criteria.where("version.versionStatus").is(queryData.getVersionStatus().toString())
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

        if (excludeAutomaticChanges) {
            criteriaList.add(
                    new Criteria().orOperator(
                            Criteria.where("version.automaticChange").is(false),
                            Criteria.where("version.automaticChange").exists(false)
                    )
            );
        }

        Criteria finalCriteria = new Criteria();
        if (!criteriaList.isEmpty()) {
            finalCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
        }

        return finalCriteria;
    }
}
