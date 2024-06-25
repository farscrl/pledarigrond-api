package ch.pledarigrond.lucene.config;

import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.lucene.FieldType;
import ch.pledarigrond.common.data.lucene.IndexedColumn;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.lucene.config.querybuilder.FieldFactory;
import ch.pledarigrond.lucene.config.querybuilder.PgQueryBuilder;
import ch.pledarigrond.lucene.config.querybuilder.modifier.DefaultQueryBuilder;
import ch.pledarigrond.lucene.config.querybuilder.modifier.ExactMatchQueryBuilder;
import ch.pledarigrond.lucene.core.BuilderRegistry;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IndexManager {

    protected static final Logger logger = LoggerFactory.getLogger(IndexManager.class);

    protected static final Set<String> errors = new HashSet<>();

    protected String[] allColumns;

    protected BuilderRegistry builderRegistry = new BuilderRegistry();

    // the final set of columns, that should be added to the index
    protected Set<IndexedColumn> finalColumnSet = new TreeSet<>(Comparator.comparing(IndexedColumn::getIndexFieldName));

    /**
     * The {@link FieldFactory}-objects required by the current search configuration.
     * For each {@link IndexedColumn} defined in the configuration, one {@link FieldFactory}
     * will be generated. Key of the map is the source attribute of the {@link IndexedColumn},
     * value is the list of all {@link FieldFactory}-objects which referred to this source.
     */
    protected Map<String, List<FieldFactory>> fieldFactories = new HashMap<String, List<FieldFactory>>();

    /**
     * A mapping from columns to {@link PgQueryBuilder} instances, used for
     * suggestion queries.
     */
    protected Map<String, PgQueryBuilder> suggestionsBuilders = new HashMap<>();

    /**
     * Contains all field names which are ignored when generating the index.
     * A field is ignored if it has not been set up in the indexConfiguration-
     * section of the search configuration. For each ignored field, a warning
     * message will be logged.
     */
    private final Set<String> ignored = new HashSet<>();

    private static IndexManager indexManagerInstance;

    /**
     * Returns the singleton instance of this class.
     * @return IndexManagerSurmiran
     */
    public static synchronized IndexManager getInstance() {
        if(indexManagerInstance == null) {
            try {
                indexManagerInstance = new IndexManager();
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

    public IndexManager() {
        logger.info("Generate list of all index-fields used for surmiran");
        setDefaultValues(getDatabaseColumns(), finalColumnSet);

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

    public static IndexManager getInstance(Language language) {
        return switch (language) {
            case PUTER -> IndexManager.getInstance();
            case RUMANTSCHGRISCHUN -> IndexManager.getInstance();
            case SURMIRAN -> IndexManager.getInstance();
            case SURSILVAN -> IndexManager.getInstance();
            case SUTSILVAN -> IndexManager.getInstance();
            case VALLADER -> IndexManager.getInstance();
        };
    }

    public Query getSuggestionsQuery(String fieldName, String value) {
        PgQueryBuilder builder = suggestionsBuilders.get(fieldName);
        if(builder == null) {
            logger.warn("No query builder found for field " + fieldName);
            return null;
        }
        List<Query> parts = builder.transform(value);
        BooleanQuery bq = new BooleanQuery(true);
        for (Query part : parts) {
            bq.add(part, BooleanClause.Occur.SHOULD);
        }
        BooleanQuery bc = new BooleanQuery();
        bc.add(bq, BooleanClause.Occur.MUST);
        bc.add(new TermQuery(new Term(LemmaVersion.VERIFICATION, LemmaVersion.Verification.ACCEPTED.toString())), BooleanClause.Occur.MUST);
        bq = bc;
        return bq;
    }

    public Query buildQuery(SearchCriteria searchCriteria) {
        long prepareStart = System.nanoTime();
        BooleanQuery finalQuery = new BooleanQuery(true);

        if (searchCriteria.getSearchPhrase() != null && !searchCriteria.getSearchPhrase().equals("")) {
            List<Query> searchPhraseQueries = switch (searchCriteria.getSearchDirection()) {
                case GERMAN -> Stream.of(
                        builderRegistry.getBuilder(SearchDirection.GERMAN, searchCriteria.getSearchMethod()).transform(searchCriteria.getSearchPhrase()),
                        builderRegistry.getTagQueries(SearchDirection.GERMAN, searchCriteria.getSearchMethod(), searchCriteria.getSearchPhrase())
                ).flatMap(Collection::stream).collect(Collectors.toList());
                case ROMANSH -> Stream.of(
                        builderRegistry.getBuilder(SearchDirection.ROMANSH, searchCriteria.getSearchMethod()).transform(searchCriteria.getSearchPhrase()),
                        builderRegistry.getTagQueries(SearchDirection.ROMANSH, searchCriteria.getSearchMethod(), searchCriteria.getSearchPhrase()),
                        builderRegistry.getEtymologyQueries(searchCriteria.getSearchMethod(), searchCriteria.getSearchPhrase())
                ).flatMap(Collection::stream).collect(Collectors.toList());

                case BOTH -> Stream.of(
                        builderRegistry.getBuilder(SearchDirection.GERMAN, searchCriteria.getSearchMethod()).transform(searchCriteria.getSearchPhrase()),
                        builderRegistry.getBuilder(SearchDirection.ROMANSH, searchCriteria.getSearchMethod()).transform(searchCriteria.getSearchPhrase()),
                        builderRegistry.getTagQueries(SearchDirection.GERMAN, searchCriteria.getSearchMethod(), searchCriteria.getSearchPhrase()),
                        builderRegistry.getTagQueries(SearchDirection.ROMANSH, searchCriteria.getSearchMethod(), searchCriteria.getSearchPhrase()),
                        builderRegistry.getEtymologyQueries(searchCriteria.getSearchMethod(), searchCriteria.getSearchPhrase())
                ).flatMap(Collection::stream).collect(Collectors.toList());
            };
            BooleanQuery part = new BooleanQuery(true);
            for (Query tf : searchPhraseQueries) {
                part.add(tf, BooleanClause.Occur.SHOULD);
            }
            finalQuery.add(part, BooleanClause.Occur.MUST);
        }

        if (searchCriteria.getGender() != null) {
            List<Query> genderQueries = switch (searchCriteria.getSearchDirection()) {
                case GERMAN -> builderRegistry.getGenderBuilder(SearchDirection.GERMAN).transform(searchCriteria.getGender());
                case ROMANSH -> builderRegistry.getGenderBuilder(SearchDirection.ROMANSH).transform(searchCriteria.getGender());
                case BOTH -> Stream.of(
                        builderRegistry.getGenderBuilder(SearchDirection.ROMANSH).transform(searchCriteria.getGender()),
                        builderRegistry.getGenderBuilder(SearchDirection.GERMAN).transform(searchCriteria.getGender())
                ).flatMap(Collection::stream).collect(Collectors.toList());
            };
            BooleanQuery part = new BooleanQuery(true);
            for (Query tf : genderQueries) {
                part.add(tf, BooleanClause.Occur.SHOULD);
            }
            finalQuery.add(part, BooleanClause.Occur.MUST);
        }

        if (searchCriteria.getGrammar() != null) {
            List<Query> grammarQueries = switch (searchCriteria.getSearchDirection()) {
                case GERMAN -> builderRegistry.getGrammarBuilder(SearchDirection.GERMAN).transform(searchCriteria.getGrammar());
                case ROMANSH -> builderRegistry.getGrammarBuilder(SearchDirection.ROMANSH).transform(searchCriteria.getGrammar());
                case BOTH -> Stream.of(
                        builderRegistry.getGrammarBuilder(SearchDirection.ROMANSH).transform(searchCriteria.getGrammar()),
                        builderRegistry.getGrammarBuilder(SearchDirection.GERMAN).transform(searchCriteria.getGrammar())
                ).flatMap(Collection::stream).collect(Collectors.toList());
            };
            BooleanQuery part = new BooleanQuery(true);
            for (Query tf : grammarQueries) {
                part.add(tf, BooleanClause.Occur.SHOULD);
            }
            finalQuery.add(part, BooleanClause.Occur.MUST);
        }

        if (searchCriteria.getSubSemantics() != null) {
            List<Query> subSemanticsQueries = switch (searchCriteria.getSearchDirection()) {
                case GERMAN -> builderRegistry.getSubSemanticsBuilder(SearchDirection.GERMAN).transform(searchCriteria.getSubSemantics());
                case ROMANSH -> builderRegistry.getSubSemanticsBuilder(SearchDirection.ROMANSH).transform(searchCriteria.getSubSemantics());
                case BOTH -> Stream.of(
                        builderRegistry.getSubSemanticsBuilder(SearchDirection.ROMANSH).transform(searchCriteria.getSubSemantics()),
                        builderRegistry.getSubSemanticsBuilder(SearchDirection.GERMAN).transform(searchCriteria.getSubSemantics())
                ).flatMap(Collection::stream).collect(Collectors.toList());
            };
            BooleanQuery part = new BooleanQuery(true);
            for (Query tf : subSemanticsQueries) {
                part.add(tf, BooleanClause.Occur.SHOULD);
            }
            finalQuery.add(part, BooleanClause.Occur.MUST);
        }

        if (searchCriteria.getCategory() != null) {
            List<Query> categoryQueries = builderRegistry.getCategoryBuilder().transform(searchCriteria.getCategory());
            BooleanQuery part = new BooleanQuery(true);
            for (Query tf : categoryQueries) {
                part.add(tf, BooleanClause.Occur.SHOULD);
            }
            finalQuery.add(part, BooleanClause.Occur.MUST);
        }

        if (searchCriteria.getVerification() != null) {
            try {
                QueryParser queryParser = new QueryParser(Version.LUCENE_46, LemmaVersion.VERIFICATION + "_analyzed", new StandardAnalyzer(Version.LUCENE_46));
                queryParser.setAllowLeadingWildcard(true);
                finalQuery.add(queryParser.parse(searchCriteria.getVerification().toString()), BooleanClause.Occur.MUST);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (searchCriteria.getShowReviewLater() != null) {
            try {
                QueryParser queryParser = new QueryParser(Version.LUCENE_46, LemmaVersion.REVIEW_LATER, new StandardAnalyzer(Version.LUCENE_46));
                finalQuery.add(queryParser.parse(searchCriteria.getShowReviewLater().toString()), BooleanClause.Occur.MUST);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (searchCriteria.getOnlyAutomaticChanged()) {
            try {
                QueryParser queryParser = new QueryParser(Version.LUCENE_46, LemmaVersion.AUTOMATIC_CHANGE, new StandardAnalyzer(Version.LUCENE_46));
                queryParser.setAllowLeadingWildcard(true);
                finalQuery.add(queryParser.parse("*"), BooleanClause.Occur.MUST);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (searchCriteria.getExcludeAutomaticChanged()) {
            try {
                QueryParser queryParser = new QueryParser(Version.LUCENE_46, LemmaVersion.FIELD_NAMES, new StandardAnalyzer(Version.LUCENE_46));
                queryParser.setAllowLeadingWildcard(true);
                finalQuery.add(queryParser.parse("* AND -" + LemmaVersion.AUTOMATIC_CHANGE), BooleanClause.Occur.MUST);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (searchCriteria.getAutomaticChangesType() != null && searchCriteria.getAutomaticChangesType() != AutomaticChangesType.ALL) {
            try {
                QueryParser queryParser = new QueryParser(Version.LUCENE_46, LemmaVersion.AUTOMATIC_CHANGE, new StandardAnalyzer(Version.LUCENE_46));
                queryParser.setAllowLeadingWildcard(true);
                finalQuery.add(queryParser.parse(searchCriteria.getAutomaticChangesType().toString()), BooleanClause.Occur.MUST);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // Unless a user wants to see unverified suggestions, each item returned must be verified.
        if (!searchCriteria.getSuggestions()) {
            BooleanQuery bc = new BooleanQuery();
            bc.add(finalQuery, BooleanClause.Occur.MUST);
            bc.add(new TermQuery(new Term(LemmaVersion.VERIFICATION, LemmaVersion.Verification.ACCEPTED.toString())), BooleanClause.Occur.MUST);
            finalQuery = bc;
        }

        long prepareEnd = System.nanoTime();
        if(logger.isDebugEnabled()) {
            logger.debug("Final query: " + finalQuery + " created in " + ((prepareEnd-prepareStart)/1000000D) + " ms.");
        }

        return finalQuery;
    }

    public Document getDocument(LexEntry lexEntry, LemmaVersion lemmaVersion) {
        Document doc = new Document();
        Set<Map.Entry<String, String>> entries = lemmaVersion.getLemmaValues().entrySet();
        StringBuilder allFieldsList = new StringBuilder();
        for (Map.Entry<String, String> entry : entries) {
            if(ignored.contains(entry.getKey())) continue;
            List<IndexableField> fields = toField(entry.getKey(), entry.getValue());
            if(fields == null) {
                logger.warn("No mapping for field " + entry.getKey() + " - field will not be indexed!");
                ignored.add(entry.getKey());
                continue;
            }
            for (IndexableField field : fields) {
                doc.add(field);
            }
            allFieldsList.append(entry.getKey());
            allFieldsList.append(' ');
        }
        List<IndexableField> fields = toField(LemmaVersion.FIELD_NAMES, allFieldsList.toString());
        for (IndexableField field : fields) {
            doc.add(field);
        }
        addPgFieldsToDocument(lexEntry, lemmaVersion, doc);
        return doc;
    }

    public LemmaVersion getLemmaVersion(Document document) {
        LemmaVersion lv = new LemmaVersion();
        for (String fieldName : allColumns) {
            IndexableField[] fields = document.getFields(fieldName);
            if(fields.length == 0) continue;
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < fields.length; i++) {
                sb.append(fields[i].stringValue());
                if(i < fields.length-1) {
                    sb.append(",");
                }
            }
            lv.putEntryValue(fieldName, sb.toString());
        }
        addPgFieldsToLemmaVersion(document, lv);
        return lv;
    }

    public Set<IndexedColumn> getFinalColumnSet() {
        return this.finalColumnSet;
    }

    protected List<String> getSuggestionsFields() {
        return Arrays.asList(
                "DGrammatik", "DGenus", "DSubsemantik", "categories",
                "RGrammatik", "RGenus", "RSubsemantik"
        );
    }
    protected void setDefaultValues( List<IndexedColumn> databaseColumns, Set<IndexedColumn> finalColumnSet) {
        // Set default values for all defined columns - they are used to store the values
        // as they are, without any tokenization or modification.
        for (IndexedColumn item : databaseColumns) {
            item.setIndexFieldName(item.getColumnName());
            item.setStored(true);
            item.setAnalyzed(false);
            item.setLowerCase(false);

            if(item.getType() == null) {
                item.setType(FieldType.TEXT);
            } else {
                if(item.getType() != FieldType.SEMICOLON_SEPERATED) {
                    errors.add("Column '" + item.getColumnName() + "' should not explicitly define a 'type' unless it is 'SEMICOLON_SEPERATED'.");
                }
            }
            boolean added = finalColumnSet.add(item);
            if(!added) {
                errors.add("Column '" + item.getColumnName() + "' was found more than once in the list of indexed columns.");
            }
            logger.info("Required default field: " + item);
        }
    }

    protected IndexedColumn generateSortColumn(String mainColumn) {
        IndexedColumn column = new IndexedColumn();
        column.setColumnName(mainColumn + "_sort");
        column.setIndexFieldName(mainColumn + "_sort");
        column.setStored(true);
        column.setAnalyzed(false);
        column.setLowerCase(false);
        column.setType(FieldType.INTEGER);

        logger.info("Added sort field for " + mainColumn + ": " + column);

        return column;
    }

    protected Set<IndexedColumn> getDictionaryItem(String mainColumn) {
        ExactMatchQueryBuilder builder = new ExactMatchQueryBuilder();
        builder.setColumn(mainColumn);
        Set<IndexedColumn> columns = builder.getRegisteredColumns();
        logger.info("Added fields for exact (dictionary) queries: " + columns);
        return columns;
    }

    protected void autoGenerateSuggestionsFields(Set<IndexedColumn> indexedColumns, List<String> editorFields) {
        logger.info("Auto-generating suggestion fields...");

        for (String column : editorFields) {
            DefaultQueryBuilder builder = new DefaultQueryBuilder();
            builder.setColumn(column);
            suggestionsBuilders.put(column, builder);
            Set<IndexedColumn> entries = builder.getRegisteredColumns();
            logger.info("Added fields for suggestion " + column + ": " + entries);
            indexedColumns.addAll(entries);
        }
    }

    protected void extractListOfAllColumns(Set<IndexedColumn> finalColumnSet, String firstLanguageMain, String secondLanguageMain) {
        // Keep track of all column names - required to convert from lucene documents to lemma versions
        Set<String> columnNames = new TreeSet<>();
        for (IndexedColumn item : finalColumnSet) {
            columnNames.add(item.getColumnName());
        }
        if(!columnNames.contains(firstLanguageMain)) {
            errors.add("Main column '" + firstLanguageMain + "' was not found in the set of all columns.");
        }
        if(!columnNames.contains(secondLanguageMain)) {
            errors.add("Main column '" + secondLanguageMain + "' was not found in the set of all columns.");
        }
        allColumns = columnNames.toArray(new String[0]);
    }

    protected List<IndexableField> toField(String key, String value) {
        List<FieldFactory> factories = fieldFactories.get(key);
        if(factories == null || factories.isEmpty()) return null;
        List<IndexableField> fields = new ArrayList<IndexableField>();
        for (FieldFactory factory : factories) {
            List<IndexableField> field = factory.getFields(value);
            if(field != null) {
                fields.addAll(field);
            }
        }
        return fields;
    }

    /**
     * Helper method to add the default pg fields of a {@link LemmaVersion} to a lucene {@link Document}.
     * @param lexEntry
     * @param version
     * @param document
     */
    protected void addPgFieldsToDocument(LexEntry lexEntry, LemmaVersion version, Document document) {
        document.add(new StringField(LexEntry.ID, lexEntry.getId(), Field.Store.YES));
        document.add(new StringField(LemmaVersion.LEXENTRY_ID, lexEntry.getId(), Field.Store.YES));
        document.add(new StringField(LemmaVersion.ID, version.getInternalId()+"", Field.Store.YES));
        document.add(new StringField(LemmaVersion.VERIFICATION, version.getVerification().toString(), Field.Store.YES));
        document.add(new TextField(LemmaVersion.VERIFICATION + "_analyzed", version.getVerification().toString().toLowerCase(), Field.Store.NO));
        if(version.getEntryValue(LemmaVersion.RM_INFLECTION_TYPE) != null) {
            document.add(new StringField(LemmaVersion.RM_INFLECTION_TYPE, version.getEntryValue(LemmaVersion.RM_INFLECTION_TYPE), Field.Store.YES));
        }
        if(version.getEntryValue(LemmaVersion.RM_INFLECTION_SUBTYPE) != null) {
            document.add(new StringField(LemmaVersion.RM_INFLECTION_SUBTYPE, version.getEntryValue(LemmaVersion.RM_INFLECTION_SUBTYPE), Field.Store.YES));
        }
        if (version.getPgValues().get(LemmaVersion.AUTOMATIC_CHANGE) != null) {
            document.add(new StringField(LemmaVersion.AUTOMATIC_CHANGE, version.getPgValues().get(LemmaVersion.AUTOMATIC_CHANGE).toLowerCase(), Field.Store.YES));
        }
        if (version.getPgValues().get(LemmaVersion.REVIEW_LATER) != null) {
            document.add(new StringField(LemmaVersion.REVIEW_LATER, version.getPgValues().get(LemmaVersion.REVIEW_LATER).toLowerCase(), Field.Store.YES));
        }
    }

    /**
     * Helper method to add default PG fields from a lucene {@link Document} to a {@link LemmaVersion}.
     * @param document
     * @param lemmaVersion
     */
    protected void addPgFieldsToLemmaVersion(Document document, LemmaVersion lemmaVersion) {
        lemmaVersion.putPgValue(LexEntry.ID, document.get(LexEntry.ID));
        lemmaVersion.putPgValue(LemmaVersion.LEXENTRY_ID, document.get(LemmaVersion.LEXENTRY_ID));
        lemmaVersion.putPgValue(LemmaVersion.ID, document.get(LemmaVersion.ID));
        lemmaVersion.putPgValue(LemmaVersion.VERIFICATION, document.get(LemmaVersion.VERIFICATION));
        lemmaVersion.putEntryValue(LemmaVersion.RM_INFLECTION_TYPE, document.get(LemmaVersion.RM_INFLECTION_TYPE));
        lemmaVersion.putEntryValue(LemmaVersion.RM_INFLECTION_SUBTYPE, document.get(LemmaVersion.RM_INFLECTION_SUBTYPE));
    }

    private List<IndexedColumn> getDatabaseColumns() {
        return Arrays.asList(
                new IndexedColumn("DStichwort"),
                new IndexedColumn("DStichwort_sort"),
                new IndexedColumn("DSemantik"),
                new IndexedColumn("DSubsemantik"),
                new IndexedColumn("DGrammatik"),
                new IndexedColumn("DGenus"),
                new IndexedColumn("DStatus"),
                new IndexedColumn("DTags"),
                new IndexedColumn("RStichwort"),
                new IndexedColumn("RStichwort_sort"),
                new IndexedColumn("RSemantik"),
                new IndexedColumn("RSubsemantik"),
                new IndexedColumn("RGrammatik"),
                new IndexedColumn("RGenus"),
                new IndexedColumn("RFlex"),
                new IndexedColumn("RTags"),
                new IndexedColumn("RInflectionType"),
                new IndexedColumn("RInflectionSubtype"),
                new IndexedColumn("categories", FieldType.SEMICOLON_SEPERATED),
                new IndexedColumn("DRedirect"),
                new IndexedColumn("RRedirect"),
                new IndexedColumn("user_comment"),
                new IndexedColumn("user_email"),
                new IndexedColumn("REtymologie"),
                new IndexedColumn("examples"),
                new IndexedColumn("RPronunciation"),

                // verbs
                new IndexedColumn("irregular"),
                new IndexedColumn("infinitiv"),
                new IndexedColumn("preschentsing1"),
                new IndexedColumn("preschentsing2"),
                new IndexedColumn("preschentsing3"),
                new IndexedColumn("preschentplural1"),
                new IndexedColumn("preschentplural2"),
                new IndexedColumn("preschentplural3"),
                new IndexedColumn("imperfectsing1"),
                new IndexedColumn("imperfectsing2"),
                new IndexedColumn("imperfectsing3"),
                new IndexedColumn("imperfectplural1"),
                new IndexedColumn("imperfectplural2"),
                new IndexedColumn("imperfectplural3"),
                new IndexedColumn("conjunctivsing1"),
                new IndexedColumn("conjunctivsing2"),
                new IndexedColumn("conjunctivsing3"),
                new IndexedColumn("conjunctivplural1"),
                new IndexedColumn("conjunctivplural2"),
                new IndexedColumn("conjunctivplural3"),
                new IndexedColumn("conjunctiv2sing1"),
                new IndexedColumn("conjunctiv2sing2"),
                new IndexedColumn("conjunctiv2sing3"),
                new IndexedColumn("conjunctiv2plural1"),
                new IndexedColumn("conjunctiv2plural2"),
                new IndexedColumn("conjunctiv2plural3"),
                new IndexedColumn("cundizionalsing1"),
                new IndexedColumn("cundizionalsing2"),
                new IndexedColumn("cundizionalsing3"),
                new IndexedColumn("cundizionalplural1"),
                new IndexedColumn("cundizionalplural2"),
                new IndexedColumn("cundizionalplural3"),
                new IndexedColumn("cundizionalindirectsing1"),
                new IndexedColumn("cundizionalindirectsing2"),
                new IndexedColumn("cundizionalindirectsing3"),
                new IndexedColumn("cundizionalindirectplural1"),
                new IndexedColumn("cundizionalindirectplural2"),
                new IndexedColumn("cundizionalindirectplural3"),
                new IndexedColumn("participperfectms"),
                new IndexedColumn("participperfectfs"),
                new IndexedColumn("participperfectmp"),
                new IndexedColumn("participperfectfp"),
                new IndexedColumn("participperfectmspredicativ"),
                new IndexedColumn("imperativ1"),
                new IndexedColumn("imperativ2"),
                new IndexedColumn("imperativ3"),
                new IndexedColumn("imperativ4"),
                new IndexedColumn("imperativ5"),
                new IndexedColumn("imperativ6"),
                new IndexedColumn("gerundium"),
                new IndexedColumn("futursing1"),
                new IndexedColumn("futursing2"),
                new IndexedColumn("futursing3"),
                new IndexedColumn("futurplural1"),
                new IndexedColumn("futurplural2"),
                new IndexedColumn("futurplural3"),
                new IndexedColumn("futurdubitativsing1"),
                new IndexedColumn("futurdubitativsing2"),
                new IndexedColumn("futurdubitativsing3"),
                new IndexedColumn("futurdubitativplural1"),
                new IndexedColumn("futurdubitativplural2"),
                new IndexedColumn("futurdubitativplural3"),

                new IndexedColumn("preschentsing1enclitic"),
                new IndexedColumn("preschentsing2enclitic"),
                new IndexedColumn("preschentsing3encliticm"),
                new IndexedColumn("preschentsing3encliticf"),
                new IndexedColumn("preschentplural1enclitic"),
                new IndexedColumn("preschentplural2enclitic"),
                new IndexedColumn("preschentplural3enclitic"),
                new IndexedColumn("imperfectsing1enclitic"),
                new IndexedColumn("imperfectsing2enclitic"),
                new IndexedColumn("imperfectsing3encliticm"),
                new IndexedColumn("imperfectsing3encliticf"),
                new IndexedColumn("imperfectplural1enclitic"),
                new IndexedColumn("imperfectplural2enclitic"),
                new IndexedColumn("imperfectplural3enclitic"),
                new IndexedColumn("cundizionalsing1enclitic"),
                new IndexedColumn("cundizionalsing2enclitic"),
                new IndexedColumn("cundizionalsing3encliticm"),
                new IndexedColumn("cundizionalsing3encliticf"),
                new IndexedColumn("cundizionalplural1enclitic"),
                new IndexedColumn("cundizionalplural2enclitic"),
                new IndexedColumn("cundizionalplural3enclitic"),
                new IndexedColumn("futursing1enclitic"),
                new IndexedColumn("futursing2enclitic"),
                new IndexedColumn("futursing3encliticm"),
                new IndexedColumn("futursing3encliticf"),
                new IndexedColumn("futurplural1enclitic"),
                new IndexedColumn("futurplural2enclitic"),
                new IndexedColumn("futurplural3enclitic"),
                new IndexedColumn("futurdubitativsing1enclitic"),
                new IndexedColumn("futurdubitativsing2enclitic"),
                new IndexedColumn("futurdubitativsing3encliticm"),
                new IndexedColumn("futurdubitativsing3encliticf"),
                new IndexedColumn("futurdubitativplural1enclitic"),
                new IndexedColumn("futurdubitativplural2enclitic"),
                new IndexedColumn("futurdubitativplural3enclitic"),
                new IndexedColumn("composedWith"),

                // nouns
                new IndexedColumn("mSingular"),
                new IndexedColumn("fSingular"),
                new IndexedColumn("mPlural"),
                new IndexedColumn("fPlural"),
                new IndexedColumn("pluralCollectiv"),

                // other
                new IndexedColumn("otherForm1"),
                new IndexedColumn("otherForm2"),
                new IndexedColumn("otherForm3"),
                new IndexedColumn("otherForm4"),

                // automatic generation of forms
                new IndexedColumn(LemmaVersion.AUTOMATIC_CHANGE),

                // contains all field-names of the current entry. This is used to search for non-existing fields:
                // https://kuzminva.wordpress.com/2017/04/04/lucene-field-does-not-exist/
                new IndexedColumn(LemmaVersion.FIELD_NAMES)
        );
    }
}
