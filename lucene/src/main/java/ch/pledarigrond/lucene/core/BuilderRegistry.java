/*******************************************************************************
 * Copyright 2013 Sprachliche Informationsverarbeitung, University of Cologne
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package ch.pledarigrond.lucene.core;

import ch.pledarigrond.lucene.config.IndexManager;
import ch.pledarigrond.lucene.config.querybuilder.PgQueryBuilder;
import org.apache.lucene.search.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class BuilderRegistry {

    private static final Logger logger = LoggerFactory.getLogger(IndexManager.class);

    /**
     * Helper class to keep track of references between
     * {@link QueryBuilder}, {@link ColumnSelector} and {@link QueryKey}.
     */
    class QueryBuilderMetaData {

        private String queryBuilderId;
        private String columnSelectorId;
        private String queryKey;

        public QueryBuilderMetaData(String queryBuilderId, String columnSelectorId, String queryKey) {
            this.queryBuilderId = queryBuilderId;
            this.columnSelectorId = columnSelectorId;
            this.queryKey = queryKey;
        }
    }

    /**
     * Mapping from query builder ids to {@link QueryBuilderMetaData}
     */
    private final Map<String, QueryBuilderMetaData> queryBuilderConfigurations = new HashMap<String, QueryBuilderMetaData>();

    /**
     * Mapping from {@link ColumnSelector} ids to default {@link ColumnSelectorOption} ids
     */
    private final Map<String, String> defaultColumnSelectorOptions = new HashMap<String, String>();

    /**
     * Mapping from {@link QueryBuilder} ids to default {@link QueryBuilderOption} ids
     */
    private final Map<String, String> defaultQueryBuilderOptions = new HashMap<String, String>();

    /**
     * Contains {@link PgQueryBuilder} to generate queries for any registered combination
     * of {@link QueryBuilder}s and {@link ColumnSelector}s.
     * First key: ID of the query modifier
     * Second key: ID of the query modifier option
     * Third key: ID of the field choice option
     */
    private final Map<String, Map<String, Map<String, List<PgQueryBuilder>>>> registeredBuilder = new HashMap<>();

    /**
     * A collection of all query builder ids
     */
    public final Set<String> allQueryBuilderIds = new HashSet<String>();

    /**
     * Reverse mapping from column selectors to query builders
     */
    private final Map<String, String> selectorsToBuilders = new HashMap<String, String>();

    /**
     * Mapping from column selectors to depdencencies
     */
    private final Map<String, String> dependencies = new HashMap<String, String>();


    public void registerBuilder(
            String queryBuilderId,
            String queryBuilderOptionId,
            boolean isDefaultQueryBuilderOption,
            String columnSelectorId,
            String columnSelectorOptionId,
            boolean isDefaultColumnSelectorOption,
            String queryKey,
            PgQueryBuilder builder,
            Set<String> errors
    ) {
        // add the id of the set of all builder ids
        allQueryBuilderIds.add(queryBuilderId);
        // store the query builder meta data
        QueryBuilderMetaData config = new QueryBuilderMetaData(queryBuilderId, columnSelectorId, queryKey);
        queryBuilderConfigurations.put(queryBuilderId, config);
        // prepare reverse lookup from column selector to query builder
        selectorsToBuilders.put(columnSelectorId, queryBuilderId);
        // Register the query builder in the lookup map
        // First key: query builder id
        Map<String, Map<String, List<PgQueryBuilder>>> modifiersAndChoices = registeredBuilder.get(queryBuilderId);
        if(modifiersAndChoices == null) {
            modifiersAndChoices = new HashMap<String, Map<String,List<PgQueryBuilder>>>();
            registeredBuilder.put(queryBuilderId, modifiersAndChoices);
        }
        // Second key: query builder option id
        Map<String, List<PgQueryBuilder>> choicesAndBuilders = modifiersAndChoices.get(queryBuilderOptionId);
        if(choicesAndBuilders == null) {
            choicesAndBuilders = new HashMap<String, List<PgQueryBuilder>>();
            modifiersAndChoices.put(queryBuilderOptionId, choicesAndBuilders);
        }
        // Third key: column selector option id
        List<PgQueryBuilder> builders = choicesAndBuilders.get(columnSelectorOptionId);
        if(builders == null) {
            builders = new ArrayList<PgQueryBuilder>();
            choicesAndBuilders.put(columnSelectorOptionId, builders);
        }
        logger.info("Registered query builder for " + queryBuilderId + ":" + queryBuilderOptionId + ", " + columnSelectorId + ":" + columnSelectorOptionId);
        builders.add(builder);
        // Register default options
        if(isDefaultQueryBuilderOption) {
            String old = defaultQueryBuilderOptions.put(queryBuilderId, queryBuilderOptionId);
            if(old != null && !old.equals(queryBuilderOptionId)) {
                String msg = "Invalid configuration - Two or more query modifier are defined as default: '" + old + "' and '" + queryBuilderOptionId + "' for id '" + queryBuilderId + "'";
                logger.error(msg);
                errors.add(msg);
            }
        }
        if(isDefaultColumnSelectorOption) {
            String old = defaultColumnSelectorOptions.put(columnSelectorId, columnSelectorOptionId);
            if(old != null && !old.equals(columnSelectorOptionId)) {
                String msg = "Invalid configuration - Two or more field choices are defined as default: '" + old + "' and '" + columnSelectorOptionId + "' for id '" + columnSelectorId+"'";
                logger.error(msg);
                errors.add(msg);
            }
        }
    }

    /**
     * Generate a set of sub-queries of of the query map for the given modifier.
     * @param maalrQuery
     * @param queryBuilderId
     * @return the set of sub-queries, or null, of the query builder wasn't referenced in the query map
     */
    private Set<Query> getQuery(Map<String, String> maalrQuery, String queryBuilderId) {
        QueryBuilderMetaData config = queryBuilderConfigurations.get(queryBuilderId);
        String columnSelectorOption = maalrQuery.get(config.columnSelectorId);
        String dependsOn = dependencies.get(config.columnSelectorId);
        // Resolve dependency - if the column selector depends on another selector,
        // the column selector option has to be overridden.
        if(dependsOn != null) {
            String referencedQueryBuilderId = selectorsToBuilders.get(dependsOn);
            QueryBuilderMetaData dependsOnMod = queryBuilderConfigurations.get(referencedQueryBuilderId);
            String selected = maalrQuery.get(dependsOnMod.columnSelectorId);
            if(selected == null) {
                selected = defaultColumnSelectorOptions.get(dependsOnMod.columnSelectorId);
            }
            columnSelectorOption = selected;
        }
        String queryString = maalrQuery.get(config.queryKey);
        if(queryString == null) return null;
        if(columnSelectorOption == null) {
            columnSelectorOption = defaultColumnSelectorOptions.get(config.columnSelectorId);
        }
        if(columnSelectorOption == null) {
            return null;
        }
        String queryBuilderOption = maalrQuery.get(config.queryBuilderId);
        if(queryBuilderOption == null) {
            queryBuilderOption = defaultQueryBuilderOptions.get(config.queryBuilderId);
        }
        if(queryBuilderOption == null) {
            return null;
        }
        Set<Query> toReturn = new HashSet<Query>();
        // Find the list of builders for the given query, and apply each of them on
        // the query string.
        List<PgQueryBuilder> builder = registeredBuilder.get(queryBuilderId).get(queryBuilderOption).get(columnSelectorOption);
        for (PgQueryBuilder mqb : builder) {
            toReturn.addAll(mqb.transform(queryString));
        }
        return toReturn;
    }

    public void setDependency(String selectorId, String dependsOnSelector) {
        dependencies.put(selectorId, dependsOnSelector);
    }
}
