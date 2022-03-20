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

import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.common.SearchMethod;
import ch.pledarigrond.lucene.config.IndexManager;
import ch.pledarigrond.lucene.config.querybuilder.PgQueryBuilder;
import ch.pledarigrond.lucene.config.querybuilder.modifier.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class BuilderRegistry {

    private static final Logger logger = LoggerFactory.getLogger(IndexManager.class);

    private final Map<String, PgQueryBuilder> builderMap = new HashMap<>();

    public BuilderRegistry() {
        this.init();
    }

    public PgQueryBuilder getBuilder(SearchDirection direction, SearchMethod method) {
        if (direction == SearchDirection.ROMANSH) {
            return switch (method) {
                case NORMAL -> builderMap.get("RStichwort_normal");
                case INTERN -> builderMap.get("RStichwort_intern");
                case PREFIX -> builderMap.get("RStichwort_prefix");
                case SUFFIX -> builderMap.get("RStichwort_suffix");
                case EXACT -> builderMap.get("RStichwort_exact");
            };
        }
        if (direction == SearchDirection.GERMAN) {
            return switch (method) {
                case NORMAL -> builderMap.get("DStichwort_normal");
                case INTERN -> builderMap.get("DStichwort_intern");
                case PREFIX -> builderMap.get("DStichwort_prefix");
                case SUFFIX -> builderMap.get("DStichwort_suffix");
                case EXACT -> builderMap.get("DStichwort_exact");
            };
        }
        return null;
    }

    private void init() {
        builderMap.put("RStichwort_normal", new DefaultQueryBuilder().setColumn("RStichwort"));
        builderMap.put("RStichwort_intern", new InfixQueryBuilder().setColumn("RStichwort"));
        builderMap.put("RStichwort_prefix", new PrefixQueryBuilder().setColumn("RStichwort"));
        builderMap.put("RStichwort_suffix", new SuffixQueryBuilder().setColumn("RStichwort"));
        builderMap.put("RStichwort_exact", new ExactMatchQueryBuilder().setColumn("RStichwort"));

        builderMap.put("DStichwort_normal", new DefaultQueryBuilder().setColumn("DStichwort"));
        builderMap.put("DStichwort_intern", new InfixQueryBuilder().setColumn("DStichwort"));
        builderMap.put("DStichwort_prefix", new PrefixQueryBuilder().setColumn("DStichwort"));
        builderMap.put("DStichwort_suffix", new SuffixQueryBuilder().setColumn("DStichwort"));
        builderMap.put("DStichwort_exact", new ExactMatchQueryBuilder().setColumn("DStichwort"));
    }
}
