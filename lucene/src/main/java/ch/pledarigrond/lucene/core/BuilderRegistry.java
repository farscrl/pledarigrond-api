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
            return builderMap.get("RTags_normal").transform(field);
        }
        if (direction == SearchDirection.GERMAN) {
            return builderMap.get("DTags_normal").transform(field);
        }
        return new ArrayList<>();
    }

    public List<Query> getEtymologyQueries(SearchMethod method, String field) {
        if (method != SearchMethod.INTERN) {
            return new ArrayList<>();
        }
        return builderMap.get("REtymologie_intern").transform(field);
    }

    public List<Query> getSuggestionQueries(String field, String searchTerm) {
        return switch (field) {
            case "DGrammatik" -> builderMap.get("DGrammatik_default").transform(searchTerm);
            case "DGenus" -> builderMap.get("DGenus_default").transform(searchTerm);
            case "DSubsemantik" -> builderMap.get("DSubsemantik_default").transform(searchTerm);
            case "categories" -> builderMap.get("categories_default").transform(searchTerm);
            case "RGrammatik" -> builderMap.get("RGrammatik_default").transform(searchTerm);
            case "RGenus" -> builderMap.get("RGenus_default").transform(searchTerm);
            case "RSubsemantik" -> builderMap.get("RSubsemantik_default").transform(searchTerm);
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
            return builderMap.get("RGenus_simple_prefix");
        }
        if (direction == SearchDirection.GERMAN) {
            return builderMap.get("DGenus_simple_prefix");
        }
        return null;
    }

    public AbstractQueryBuilder getGrammarBuilder(SearchDirection direction) {
        if (direction == SearchDirection.ROMANSH) {
            return builderMap.get("RGrammatik_simple_prefix");
        }
        if (direction == SearchDirection.GERMAN) {
            return builderMap.get("DGrammatik_simple_prefix");
        }
        return null;
    }

    public AbstractQueryBuilder getSubSemanticsBuilder(SearchDirection direction) {
        if (direction == SearchDirection.ROMANSH) {
            return builderMap.get("DSubsemantik_infix");
        }
        if (direction == SearchDirection.GERMAN) {
            return builderMap.get("RSubsemantik_infix");
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
        builderMap.put("RTags_normal", new DefaultQueryBuilderSplittingWhitespaces().setColumn("RTags"));
        builderMap.put("DTags_normal", new DefaultQueryBuilderSplittingWhitespaces().setColumn("DTags"));

        // Search words starting with
        builderMap.put("deStichwort_simple_prefix", new SimplePrefixQueryBuilder().setColumn("deStichwort"));
        builderMap.put("rmStichwort_simple_prefix", new SimplePrefixQueryBuilder().setColumn("rmStichwort"));

        // Search Gender
        builderMap.put("DGenus_simple_prefix", new SimplePrefixQueryBuilder().setColumn("DGenus"));
        builderMap.put("RGenus_simple_prefix", new SimplePrefixQueryBuilder().setColumn("RGenus"));

        // Search Grammar
        builderMap.put("DGrammatik_simple_prefix", new SimplePrefixQueryBuilder().setColumn("DGrammatik"));
        builderMap.put("RGrammatik_simple_prefix", new SimplePrefixQueryBuilder().setColumn("RGrammatik"));
        
        // Search SubSemantics
        builderMap.put("DSubsemantik_infix", new InfixQueryBuilder().setColumn("DSubsemantik"));
        builderMap.put("RSubsemantik_infix", new InfixQueryBuilder().setColumn("RSubsemantik"));

        // Category
        builderMap.put("categories_infix", new InfixQueryBuilder().setColumn("categories"));

        // Etymology
        builderMap.put("REtymologie_intern", new InfixQueryBuilder().setColumn("REtymologie"));

        // Suggestion Builders
        builderMap.put("DGrammatik_default", new DefaultQueryBuilder().setColumn("DGrammatik"));
        builderMap.put("DGenus_default", new DefaultQueryBuilder().setColumn("DGenus"));
        builderMap.put("DSubsemantik_default", new DefaultQueryBuilder().setColumn("DSubsemantik"));
        builderMap.put("categories_default", new DefaultQueryBuilder().setColumn("categories"));
        builderMap.put("RGrammatik_default", new DefaultQueryBuilder().setColumn("RGrammatik"));
        builderMap.put("RGenus_default", new DefaultQueryBuilder().setColumn("RGenus"));
        builderMap.put("RSubsemantik_default", new DefaultQueryBuilder().setColumn("RSubsemantik"));
    }
}
