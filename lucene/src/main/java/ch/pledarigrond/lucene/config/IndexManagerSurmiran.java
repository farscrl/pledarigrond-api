package ch.pledarigrond.lucene.config;

import ch.pledarigrond.common.data.lucene.IndexedColumn;
import ch.pledarigrond.common.config.SurmiranLanguageConfig;
import ch.pledarigrond.lucene.config.querybuilder.FieldFactory;

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
        logger.info("Generate list of all index-fields used for surmiran");
        languageConfig = new SurmiranLanguageConfig();

        setDefaultValues(languageConfig.getDatabaseColumns(), finalColumnSet);

        // add sort fields
        String firstLanguageMain = "DStichwort";
        String secondLanguageMain = "RStichwort";

        finalColumnSet.addAll(getDictionaryItem(firstLanguageMain));
        finalColumnSet.addAll(getDictionaryItem(secondLanguageMain));

        // Create fields required for suggestions
        autoGenerateSuggestionsFields(finalColumnSet, getSuggestionsFields());

        extractListOfAllColumns(finalColumnSet, firstLanguageMain, secondLanguageMain);

        finalColumnSet.addAll(builderRegistry.getAllRegisteredColumns());

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
    protected List<String> getSuggestionsFields() {
        return Arrays.asList(
            "DGrammatik", "DGenus", "DSubsemantik", "categories",
            "RGrammatik", "RGenus", "RSubsemantik"
        );
    }
}
