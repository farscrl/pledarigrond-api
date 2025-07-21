package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.common.SearchMethod;
import ch.pledarigrond.common.data.lucene.IndexedColumn;
import ch.pledarigrond.lucene.querybuilder.*;
import org.apache.lucene.search.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Querying the index is done by using prepared query builders. This
 * registry contains all query builders which are used to generate
 * the queries.
 */
public class BuilderRegistry {
    private static final Logger logger = LoggerFactory.getLogger(BuilderRegistry.class);

    private final Map<String, AbstractQueryBuilder> builderMap = new HashMap<>();

    private static BuilderRegistry instance;

    private BuilderRegistry() {
        this.init();
    }

    public static BuilderRegistry getInstance() {
        if (instance == null) {
            instance = new BuilderRegistry();
        }
        return instance;
    }

    public AbstractQueryBuilder getBuilder(SearchDirection direction, SearchMethod method) {
        if (direction == SearchDirection.ROMANSH) {
            return switch (method) {
                case NORMAL -> builderMap.get("rmStichwort_normal");
                case INTERN -> builderMap.get("rmStichwort_intern");
                case PREFIX -> builderMap.get("rmStichwort_prefix");
                case SUFFIX -> builderMap.get("rmStichwort_suffix");
                case EXACT -> builderMap.get("rmStichwort_exact");
            };
        }
        if (direction == SearchDirection.GERMAN) {
            return switch (method) {
                case NORMAL -> builderMap.get("deStichwort_normal");
                case INTERN -> builderMap.get("deStichwort_intern");
                case PREFIX -> builderMap.get("deStichwort_prefix");
                case SUFFIX -> builderMap.get("deStichwort_suffix");
                case EXACT -> builderMap.get("deStichwort_exact");
            };
        }
        return null;
    }

    public List<Query> getTagQueries(SearchDirection direction, SearchMethod method, String field) {
        if (method != SearchMethod.NORMAL && method != SearchMethod.INTERN) {
            return new ArrayList<>();
        }
        if (direction == SearchDirection.ROMANSH) {
            return builderMap.get("rmTags_normal").transform(field);
        }
        if (direction == SearchDirection.GERMAN) {
            return builderMap.get("deTags_normal").transform(field);
        }
        return new ArrayList<>();
    }

    public List<Query> getEtymologyQueries(SearchMethod method, String field) {
        if (method != SearchMethod.INTERN) {
            return new ArrayList<>();
        }
        return builderMap.get("rmEtymologie_intern").transform(field);
    }

    public List<Query> getSuggestionQueries(String field, String searchTerm) {
        return switch (field) {
            case "deGrammatik" -> builderMap.get("deGrammatik_default").transform(searchTerm);
            case "deGenus" -> builderMap.get("deGenus_default").transform(searchTerm);
            case "deSubsemantik" -> builderMap.get("deSubsemantik_default").transform(searchTerm);
            case "categories" -> builderMap.get("categories_default").transform(searchTerm);
            case "rmGrammatik" -> builderMap.get("rmGrammatik_default").transform(searchTerm);
            case "rmGenus" -> builderMap.get("rmGenus_default").transform(searchTerm);
            case "rmSubsemantik" -> builderMap.get("rmSubsemantik_default").transform(searchTerm);
            default -> List.of();
        };
    }

    public AbstractQueryBuilder getStartsWithBuilder(SearchDirection direction) {
        if (direction == SearchDirection.ROMANSH) {
            return builderMap.get("rmStichwort_simple_prefix");
        }
        if (direction == SearchDirection.GERMAN) {
            return builderMap.get("deStichwort_simple_prefix");
        }
        return null;
    }

    public AbstractQueryBuilder getGenderBuilder(SearchDirection direction) {
        if (direction == SearchDirection.ROMANSH) {
            return builderMap.get("rmGenus_simple_prefix");
        }
        if (direction == SearchDirection.GERMAN) {
            return builderMap.get("deGenus_simple_prefix");
        }
        return null;
    }

    public AbstractQueryBuilder getGrammarBuilder(SearchDirection direction) {
        if (direction == SearchDirection.ROMANSH) {
            return builderMap.get("rmGrammatik_simple_prefix");
        }
        if (direction == SearchDirection.GERMAN) {
            return builderMap.get("deGrammatik_simple_prefix");
        }
        return null;
    }

    public AbstractQueryBuilder getSubSemanticsBuilder(SearchDirection direction) {
        if (direction == SearchDirection.ROMANSH) {
            return builderMap.get("deSubsemantik_infix");
        }
        if (direction == SearchDirection.GERMAN) {
            return builderMap.get("rmSubsemantik_infix");
        }
        return null;
    }

    public AbstractQueryBuilder getCategoryBuilder() {
        return builderMap.get("categories_infix");
    }

    public List<IndexedColumn> getAllRegisteredColumns() {
        List<IndexedColumn> returnValue = new ArrayList<>();
        for(AbstractQueryBuilder builder : builderMap.values()) {
            returnValue.addAll(builder.getRegisteredColumns());
        }

        return returnValue;
    }

    private void init() {
        // Search rmStichwort
        builderMap.put("rmStichwort_normal", new DefaultQueryBuilderSubsemanticsWeighted().setColumn("rmStichwort"));
        builderMap.put("rmStichwort_intern", new InfixQueryBuilder().setColumn("rmStichwort"));
        builderMap.put("rmStichwort_prefix", new PrefixQueryBuilder().setColumn("rmStichwort"));
        builderMap.put("rmStichwort_suffix", new SuffixQueryBuilder().setColumn("rmStichwort"));
        builderMap.put("rmStichwort_exact", new ExactMatchQueryBuilder().setColumn("rmStichwort"));

        // Search deStichwort
        builderMap.put("deStichwort_normal", new DefaultQueryBuilderSubsemanticsWeighted().setColumn("deStichwort"));
        builderMap.put("deStichwort_intern", new InfixQueryBuilder().setColumn("deStichwort"));
        builderMap.put("deStichwort_prefix", new PrefixQueryBuilder().setColumn("deStichwort"));
        builderMap.put("deStichwort_suffix", new SuffixQueryBuilder().setColumn("deStichwort"));
        builderMap.put("deStichwort_exact", new ExactMatchQueryBuilder().setColumn("deStichwort"));

        // Search Tags
        builderMap.put("rmTags_normal", new DefaultQueryBuilderSplittingWhitespaces().setColumn("rmTags"));
        builderMap.put("deTags_normal", new DefaultQueryBuilderSplittingWhitespaces().setColumn("deTags"));

        // Search words starting with
        builderMap.put("deStichwort_simple_prefix", new SimplePrefixQueryBuilder().setColumn("deStichwort"));
        builderMap.put("rmStichwort_simple_prefix", new SimplePrefixQueryBuilder().setColumn("rmStichwort"));

        // Search Gender
        builderMap.put("deGenus_simple_prefix", new SimplePrefixQueryBuilder().setColumn("deGenus"));
        builderMap.put("rmGenus_simple_prefix", new SimplePrefixQueryBuilder().setColumn("rmGenus"));

        // Search Grammar
        builderMap.put("deGrammatik_simple_prefix", new SimplePrefixQueryBuilder().setColumn("deGrammatik"));
        builderMap.put("rmGrammatik_simple_prefix", new SimplePrefixQueryBuilder().setColumn("rmGrammatik"));
        
        // Search SubSemantics
        builderMap.put("deSubsemantik_infix", new InfixQueryBuilder().setColumn("deSubsemantik"));
        builderMap.put("rmSubsemantik_infix", new InfixQueryBuilder().setColumn("rmSubsemantik"));

        // Category
        builderMap.put("categories_infix", new InfixQueryBuilder().setColumn("categories"));

        // Etymology
        builderMap.put("rmEtymologie_intern", new InfixQueryBuilder().setColumn("rmEtymologie"));

        // Suggestion Builders
        builderMap.put("deGrammatik_default", new DefaultQueryBuilder().setColumn("deGrammatik"));
        builderMap.put("deGenus_default", new DefaultQueryBuilder().setColumn("deGenus"));
        builderMap.put("deSubsemantik_default", new DefaultQueryBuilder().setColumn("deSubsemantik"));
        builderMap.put("categories_default", new DefaultQueryBuilder().setColumn("categories"));
        builderMap.put("rmGrammatik_default", new DefaultQueryBuilder().setColumn("rmGrammatik"));
        builderMap.put("rmGenus_default", new DefaultQueryBuilder().setColumn("rmGenus"));
        builderMap.put("rmSubsemantik_default", new DefaultQueryBuilder().setColumn("rmSubsemantik"));
    }
}
