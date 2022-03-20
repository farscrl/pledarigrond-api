package ch.pledarigrond.lucene.config;

import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.lucene.IndexedColumn;
import ch.pledarigrond.common.config.SurmiranLanguageConfig;
import ch.pledarigrond.lucene.config.querybuilder.BuiltInQueryBuilders;
import ch.pledarigrond.lucene.config.querybuilder.FieldFactory;
import ch.pledarigrond.lucene.config.querybuilder.PgQueryBuilder;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

import java.util.*;

public class IndexManagerSurmiran extends IndexManager {

    private static IndexManagerSurmiran indexManagerInstance;

    private SurmiranLanguageConfig languageConfig;

    /**
     * Returns the singleton instance of this class.
     * @return IndexManagerSurmiran
     */
    public static synchronized IndexManagerSurmiran getInstance() {
        if(indexManagerInstance == null) {
            try {
                indexManagerInstance = new IndexManagerSurmiran();
            } catch (Exception e) {
                logger.error("Failed to initialize IndexManager", e);
                if(errors.size() > 0) {
                    logger.error("The following errors were detected in the configuration:");
                    for (String error : errors) {
                        logger.error("   " + error);
                    }
                }
                throw new RuntimeException(e);
            }
        }
        return indexManagerInstance;
    }

    public IndexManagerSurmiran() {
        logger.info("Creating lucene index for surmiran"); // TODO: correct?
        languageConfig = new SurmiranLanguageConfig();

        setDefaultValues(languageConfig.getDatabaseColumns(), finalColumnSet);

        // add sort fields
        String firstLanguageMain = "DStichwort";
        String secondLanguageMain = "RStichwort";
        // TODO: probably unused, as DStichwort_sort and RStichwort_sort are already defined (as TEXT)
        // finalColumnSet.add(generateSortColumn(firstLanguageMain));
        // finalColumnSet.add(generateSortColumn(secondLanguageMain));

        finalColumnSet.addAll(getDictionaryItem(firstLanguageMain));
        finalColumnSet.addAll(getDictionaryItem(secondLanguageMain));

        // Create fields required for oracles
        autoGenerateOracleFields(finalColumnSet, getEditorFields());

        extractListOfAllColumns(finalColumnSet, firstLanguageMain, secondLanguageMain);





        Set<String> uniqueNames = new HashSet<>();
        uniqueNames.add("gramChoice");
        uniqueNames.add("language");
        uniqueNames.add("subSemChoice");
        uniqueNames.add("genChoice");
        uniqueNames.add("sigChoice");


        Set<String> queryKeys = Set.of("searchPhrase", "category", "subSemantics", "gender", "grammar");
        for (String key : queryKeys) {
            if(!uniqueNames.add(key)) {
                errors.add("Query Key '" + key + "' does not have a unique id in the configuration");
            }
        }

        // Lookup-Table to find column selectors by their id

/*
        choiceIdsToFields = new HashMap<String, Set<String>>();


        // queryModifier/bilderConfiguration: genMod, gramMod, sigMod, subSemMod, method


        // Generate all required lucene fields, by analyzing all combinations of
        // query modifier options and field choices. For each combination, one or more
        // individual PgQueryBuilder will be instantiated and stored in the
        // builder registry.
        Map<String, QueryBuilder> queryBuilderById = new HashMap<String, QueryBuilder>();
        if(configuration.getQueryModifier().isEmpty()) {
            logger.error("No query builders are defined!");
        }
        for (QueryBuilder queryBuilder : configuration.getQueryModifier()) {

            ColumnSelector selector = columnSelectorsById.get(queryBuilder.getColumnSelectorId());
//            if(selector == null) {
//                errors.add("Illegal query builder configuration '" + queryBuilder.getId() + ": The referenced column selector does not exist.");
//            }
//            if(!queryKeys.contains(queryBuilder.getQueryKeyId())) {
//                errors.add("Illegal query builder configuration '" + queryBuilder.getId() + ": The referenced query key does not exist.");
//            }
            QueryBuilder old = queryBuilderById.put(queryBuilder.getId(), queryBuilder);
//            if(old != null) {
//                errors.add("Query builder id '" + old.getId() + "' is defined more than once.");
//            }
            String columnSelectorId = queryBuilder.getColumnSelectorId();
            ColumnSelector columnSelector = columnSelectorsById.get(columnSelectorId);
//            if(columnSelector == null) {
//                errors.add("Illegal query builder '" + queryBuilder.getId() + "': Referenced column selector '" + columnSelectorId + "' does not exist.");
//            }
            logger.info("Processing query builder '" + queryBuilder.getId() + "', related to field choice '" + columnSelector.getId() + "' and input field '" + queryBuilder.getQueryKeyId()+"'");
            List<QueryBuilderOption> options = queryBuilder.getOptions();
            int defaultQueryOptionCounter = 0;
//            if(options.isEmpty()) {
//                errors.add("Query builder '" + queryBuilder + "' does not define any options!");
//            }
            for (QueryBuilderOption qmOption : options) {
                if(qmOption.isDefault()) {
                    defaultQueryOptionCounter++;
                }
                String builderClass = qmOption.getBuilderClass();
                // Find the PgQueryBuilder to use for the given builder option
                Class<?> clazz = null;
//                if(builderClass != null && qmOption.getPreset() != null) {
//                    errors.add("Invalid query builder option: You cannot define both builderClass and preset.");
//                }
                if(builderClass == null) {
                    // Use a default builder
                    String preset = qmOption.getPreset();
                    BuiltInQueryBuilders builtInBuilder = BuiltInQueryBuilders.valueOf(preset);
                    clazz = builtInBuilder.getClazz();
                } else {
                    // Use a custom builder
                    logger.info("Processing query modifier option '" + qmOption.getId() + "', custom transformer " + builderClass);
                    clazz = Thread.currentThread().getContextClassLoader().loadClass(builderClass);
                }
                Set<String> mapping = choiceIdsToFields.get(queryBuilder.getQueryKeyId());
                if(mapping == null) {
                    mapping = new HashSet<String>();
                    choiceIdsToFields.put(queryBuilder.getQueryKeyId(), mapping);
                }
                String columnSelectorDependency = columnSelector.getDepends();
                if(columnSelectorDependency != null) {
//                    if(!columnSelectorsById.containsKey(columnSelector.getId())) {
//                        errors.add("Illegal selector dependency: '" + columnSelector.getId() + "' refers to non existing '" + columnSelectorDependency+"'");
//                    }
                    ColumnSelector reference = columnSelectorsById.get(columnSelectorDependency);
//                    if(reference.getDepends() != null) {
//                        errors.add("Illegal selector configuration related to selector '" + columnSelectorDependency + "': You cannot chain column selector dependencies.");
//                    }
                    builderRegistry.setDependency(columnSelector.getId(), columnSelectorDependency);
                }
                List<ColumnSelectorOption> selectorOptions = columnSelector.getOptions();
                int defaultColumnSelectorCounter = 0;
//                if(selectorOptions.isEmpty()) {
//                    errors.add("Column selector '" + columnSelector.getId() + "' does not define any options!");
//                }
                for (ColumnSelectorOption selectorOption : selectorOptions) {
                    if(selectorOption.isDefault()) defaultColumnSelectorCounter++;
                    List<ColumnReference> columnReferences = selectorOption.getColumnReferences();
//                    if(columnReferences.isEmpty()) {
//                        errors.add("Column selector option '" + columnSelector.getId() + ":" + selectorOption.getId() + "' does not refer to any columns!");
//                    }
                    for (ColumnReference reference : columnReferences) {
                        // Create a new query builder for each referenced column
                        PgQueryBuilder builder = (PgQueryBuilder) clazz.getDeclaredConstructor().newInstance();
                        builder.setColumn(reference.getReference());
                        // Get the indexed columns required by the builder, and add them to
                        // the set of all required columns. This ensures that all columns
                        // required for querying will be generated.
                        Set<IndexedColumn> columns = builder.getRegisteredColumns();
                        for (IndexedColumn column : columns) {
                            finalColumnSet.add(column);
                            mapping.add(reference.getReference());
                        }
                        // Register the builder for the current query configuration
                        builderRegistry.registerBuilder(queryBuilder.getId(), qmOption.getId(), qmOption.isDefault(), columnSelectorId, selectorOption.getId(), selectorOption.isDefault(), queryBuilder.getQueryKeyId(), builder);
//                        if(!columnNames.contains(reference.getReference())) {
//                            errors.add("Invalid column selector option '" + columnSelector.getId() + ":" + selectorOption.getId() + "': Column '" + reference.getReference() + "' does not exist!");
//                        }
                    }
                }
//                if(defaultColumnSelectorCounter != 1) {
//                    errors.add("Illegal column selector '" + columnSelector.getId() + "': There must be exactly one default option, but there were " + defaultColumnSelectorCounter);
//                }
            }
//            if(defaultQueryOptionCounter != 1) {
//                errors.add("Illegal query builder '" + queryBuilder.getId() + "': There must be exactly one default option, but there were " + defaultQueryOptionCounter);
//            }
        }


 */
        // Got all lucene fields, now create field factories to ensure that they will
        // be created and filled when a new entry is inserted.
        logger.info("Field analysis completed, index will contain the following fields:");
        for (IndexedColumn item : finalColumnSet) {
            logger.info("   " + item.toString());
            FieldFactory factory = new FieldFactory(item);
            List<FieldFactory> factoriesBySource = fieldFactories.get(item.getColumnName());
            if(factoriesBySource == null) {
                factoriesBySource = new ArrayList<FieldFactory>();
                fieldFactories.put(item.getColumnName(), factoriesBySource);
            }
            factoriesBySource.add(factory);
        }
        // Override the explicitly defined indexed columns in the configuration with the implicit ones detected above.
        //Configuration.getInstance().getDictionaryConfig().getIndexedColumns().clear();
        //Configuration.getInstance().getDictionaryConfig().getIndexedColumns().addAll(finalColumnSet);
/*
        //UiConfigurations uiConfigs = Configuration.getInstance().getDictionaryConfig().getUiConfigurations();
        if(uiConfigs == null) {
            errors.add("No UI fields have been defined for querying!");
        } else {
//            validate(uiConfigs.getEditorAdvancedUiConfiguration(), "advanced backend", uniqueNames);
//            validate(uiConfigs.getEditorDefaultUiConfiguration(), "default backend", uniqueNames);
//            validate(uiConfigs.getUserAdvancedUiConfiguration(), "advanced frontend", uniqueNames);
//            validate(uiConfigs.getUserDefaultUiConfiguration(), "default frontend", uniqueNames);
        }
*/
        if(errors.size() > 0) {
            logger.error(errors.size() + " errors have been detected in the configuration:");
            for (String error : errors) {
                logger.error("   " + error);
            }
        } else {
            logger.info("No errors have been detected in the configuration.");
        }

    }



    @Override
    protected List<String> getEditorFields() {
        return Arrays.asList(
            "DGrammatik", "DGenus", "DSubsemantik", "Bearbeitungshinweis",
            "RGrammatik", "RGenus", "RSubsemantik"
        );
    }
}
