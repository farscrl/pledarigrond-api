package ch.pledarigrond.common.config;

import ch.pledarigrond.common.data.lucene.FieldType;
import ch.pledarigrond.common.data.lucene.IndexedColumn;

import java.util.Arrays;
import java.util.List;

public class SurmiranLanguageConfig extends LanguageConfig {
    @Override
    public List<IndexedColumn> getDatabaseColumns() {
        return Arrays.asList(
                new IndexedColumn("DStichwort"),
                new IndexedColumn("DStichwort_sort"),
                new IndexedColumn("DSemantik"),
                new IndexedColumn("DSubsemantik"),
                new IndexedColumn("DGrammatik"),
                new IndexedColumn("DGenus"),
                new IndexedColumn("DStatus"),
                new IndexedColumn("RStichwort"),
                new IndexedColumn("RStichwort_sort"),
                new IndexedColumn("RSemantik"),
                new IndexedColumn("RSubsemantik"),
                new IndexedColumn("RGrammatik"),
                new IndexedColumn("RGenus"),
                new IndexedColumn("RFlex"),
                new IndexedColumn("RInflectionType"),
                new IndexedColumn("RInflectionSubtype"),
                new IndexedColumn("categories", FieldType.SEMICOLON_SEPERATED),
                new IndexedColumn("DRedirect"),
                new IndexedColumn("RRedirect"),
                new IndexedColumn("user_comment"),
                new IndexedColumn("user_email"),
                new IndexedColumn("infinitiv"),
                new IndexedColumn("type"),
                new IndexedColumn("subtype"),
                new IndexedColumn("irregular"),
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
                new IndexedColumn("cundizionalsing1"),
                new IndexedColumn("cundizionalsing2"),
                new IndexedColumn("cundizionalsing3"),
                new IndexedColumn("cundizionalplural1"),
                new IndexedColumn("cundizionalplural2"),
                new IndexedColumn("cundizionalplural3"),
                new IndexedColumn("participperfectms"),
                new IndexedColumn("participperfectfs"),
                new IndexedColumn("participperfectmp"),
                new IndexedColumn("participperfectfp"),
                new IndexedColumn("imperativ1"),
                new IndexedColumn("imperativ2"),
                new IndexedColumn("gerundium"),
                new IndexedColumn("futursing1"),
                new IndexedColumn("futursing2"),
                new IndexedColumn("futursing3"),
                new IndexedColumn("futurplural1"),
                new IndexedColumn("futurplural2"),
                new IndexedColumn("futurplural3")
        );
    }
}
