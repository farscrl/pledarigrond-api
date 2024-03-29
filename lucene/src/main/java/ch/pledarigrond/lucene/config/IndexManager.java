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

public abstract class IndexManager {

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


    public IndexManager() {
    }

    public static IndexManager getInstance(Language language) {
        return switch (language) {
            case PUTER -> IndexManagerSurmiran.getInstance();
            case RUMANTSCHGRISCHUN -> IndexManagerSurmiran.getInstance();
            case SURMIRAN -> IndexManagerSurmiran.getInstance();
            case SURSILVAN -> IndexManagerSurmiran.getInstance();
            case SUTSILVAN -> IndexManagerSurmiran.getInstance();
            case VALLADER -> IndexManagerSurmiran.getInstance();
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

    protected abstract List<String> getSuggestionsFields();

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
}
