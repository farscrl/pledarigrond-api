package ch.pledarigrond.lucene.util;

import ch.pledarigrond.common.data.common.EditorRole;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.common.data.dictionary.VersionStatus;
import ch.pledarigrond.common.data.dictionary.inflection.*;
import ch.pledarigrond.common.data.lucene.IndexedColumn;
import ch.pledarigrond.lucene.core.FieldManager;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexableField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FieldTransformer {

    protected static final Logger logger = LoggerFactory.getLogger(FieldTransformer.class);

    public static Document getDocument(EntryDto entry, EntryVersionDto ev) {
        Document doc = new Document();
        StringBuilder allFieldsList = new StringBuilder();

        toFieldUpdateAllFields(doc, FN.entryId, entry.getEntryId(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.publicationStatus, entry.getPublicationStatus().toString(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.rmStichwort, ev.getRmStichwort(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.rmStichwortSort, ev.getRmStichwortSort(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.rmSemantik, ev.getRmSemantik(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.rmSubsemantik, ev.getRmSubsemantik(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.rmGrammatik, ev.getRmGrammatik(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.rmGenus, ev.getRmGenus(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.rmFlex, ev.getRmFlex(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.rmTags, ev.getRmTags(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.rmRedirect, ev.getRmRedirect(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.rmEtymologie, ev.getRmEtymologie(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.rmPronunciation, ev.getRmPronunciation(), allFieldsList);

        toFieldUpdateAllFields(doc, FN.deStichwort, ev.getDeStichwort(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.deStichwortSort, ev.getDeStichwortSort(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.deSemantik, ev.getDeSemantik(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.deSubsemantik, ev.getDeSubsemantik(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.deGrammatik, ev.getDeGrammatik(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.deGenus, ev.getDeGenus(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.deTags, ev.getDeTags(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.deRedirect, ev.getDeRedirect(), allFieldsList);

        toFieldUpdateAllFields(doc, FN.categories, ev.getCategories(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.examples, ev.getExamples().toString(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.userComment, ev.getUserComment(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.userEmail, ev.getUserEmail(), allFieldsList);

        toFieldUpdateAllFields(doc, FN.timestamp, ev.getTimestamp().toString(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.versionStatus, ev.getVersionStatus().toString(), allFieldsList);

        toFieldUpdateAllFields(doc, FN.creator, ev.getCreator(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.creatorIp, ev.getCreatorIp(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.creatorRole, ev.getCreatorRole().toString(), allFieldsList);
        toFieldUpdateAllFields(doc, FN.verifier, ev.getVerifier(), allFieldsList);

        toFieldUpdateAllFields(doc, FN.automaticChange, ev.isAutomaticChange() + "", allFieldsList);

        if (ev.getInflection() != null) {
            InflectionDto i = ev.getInflection();
            toFieldUpdateAllFields(doc, FN.inflectionType, Optional.ofNullable(i.getInflectionType()).map(InflectionType::getName).orElse(null), allFieldsList);
            toFieldUpdateAllFields(doc, FN.inflectionSubtype, i.getInflectionSubtype(), allFieldsList);
            toFieldUpdateAllFields(doc, FN.reviewLater, i.isReviewLater() ? "true" : "false", allFieldsList);

            if (i.getInflectionType() != null && i.getInflectionType().equals(InflectionType.VERB) && i.getVerb() != null) {
                VerbDto v = i.getVerb();
                toFieldUpdateAllFields(doc, FN.infinitiv, v.getInfinitiv(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.irregular, v.isIrregular() ? "true" : "false", allFieldsList);
                toFieldUpdateAllFields(doc, FN.composedWith, v.getComposedWith(), allFieldsList);

                toFieldUpdateAllFields(doc, FN.preschentsing1, v.getPreschent().getSing1(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.preschentsing2, v.getPreschent().getSing2(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.preschentsing3, v.getPreschent().getSing3(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.preschentplural1, v.getPreschent().getPlural1(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.preschentplural2, v.getPreschent().getPlural2(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.preschentplural3, v.getPreschent().getPlural3(), allFieldsList);

                toFieldUpdateAllFields(doc, FN.imperfectsing1, v.getImperfect().getSing1(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.imperfectsing2, v.getImperfect().getSing2(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.imperfectsing3, v.getImperfect().getSing3(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.imperfectplural1, v.getImperfect().getPlural1(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.imperfectplural2, v.getImperfect().getPlural2(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.imperfectplural3, v.getImperfect().getPlural3(), allFieldsList);

                // TODO: finalize me
            } else if (i.getInflectionType() != null && i.getInflectionType().equals(InflectionType.NOUN) && i.getNoun() != null) {
                NounDto n = i.getNoun();
                toFieldUpdateAllFields(doc, FN.baseForm, n.getBaseForm(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.mSingular, n.getMSingular(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.fSingular, n.getFSingular(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.mPlural, n.getMPlural(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.fPlural, n.getFPlural(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.pluralCollectiv, n.getPluralCollectiv(), allFieldsList);
            } else if (i.getInflectionType() != null && i.getInflectionType().equals(InflectionType.ADJECTIVE) && i.getAdjective() != null) {
                AdjectiveDto a = i.getAdjective();
                toFieldUpdateAllFields(doc, FN.baseForm, a.getBaseForm(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.mSingular, a.getMSingular(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.fSingular, a.getFSingular(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.mPlural, a.getMPlural(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.fPlural, a.getFPlural(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.adverbialForm, a.getAdverbialForm(), allFieldsList);
            } else if (i.getInflectionType() != null && i.getInflectionType().equals(InflectionType.PRONOUN) && i.getPronoun() != null) {
                PronounDto p = i.getPronoun();
                toFieldUpdateAllFields(doc, FN.baseForm, p.getBaseForm(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.mSingular, p.getMSingular(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.fSingular, p.getFSingular(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.mPlural, p.getMPlural(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.fPlural, p.getFPlural(), allFieldsList);
            } else if (i.getInflectionType() != null && i.getInflectionType().equals(InflectionType.OTHER) && i.getOther() != null) {
                OtherDto o = i.getOther();
                toFieldUpdateAllFields(doc, FN.baseForm, o.getBaseForm(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.otherForm1, o.getOtherForm1(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.otherForm2, o.getOtherForm2(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.otherForm3, o.getOtherForm3(), allFieldsList);
                toFieldUpdateAllFields(doc, FN.otherForm4, o.getOtherForm4(), allFieldsList);
            }
        }

        List<IndexableField> fields = toField(LemmaVersion.FIELD_NAMES, allFieldsList.toString());
        assert fields != null;
        for (IndexableField field : fields) {
            doc.add(field);
        }
        // addPgFieldsToDocument(lexEntry, lemmaVersion, doc);
        return doc;
    }

    public static EntryVersionDto getEntryVersion(Document doc) {
        EntryVersionDto ev = new EntryVersionDto();
        for (String fieldName : FieldManager.getInstance().getAllColumns()) {
            ev.setVersionId(toValue(doc, FN.versionId));
            ev.setRmStichwort(toValue(doc, FN.rmStichwort));
            ev.setRmStichwortSort(toValue(doc, FN.rmStichwortSort));
            ev.setRmSemantik(toValue(doc, FN.rmSemantik));
            ev.setRmSubsemantik(toValue(doc, FN.rmSubsemantik));
            ev.setRmGrammatik(toValue(doc, FN.rmGrammatik));
            ev.setRmGenus(toValue(doc, FN.rmGenus));
            ev.setRmFlex(toValue(doc, FN.rmFlex));
            ev.setRmTags(toValue(doc, FN.rmTags));
            ev.setRmRedirect(toValue(doc, FN.rmRedirect));
            ev.setRmEtymologie(toValue(doc, FN.rmEtymologie));
            ev.setRmPronunciation(toValue(doc, FN.rmPronunciation));

            ev.setDeStichwort(toValue(doc, FN.deStichwort));
            ev.setDeStichwortSort(toValue(doc, FN.deStichwortSort));
            ev.setDeSemantik(toValue(doc, FN.deSemantik));
            ev.setDeSubsemantik(toValue(doc, FN.deSubsemantik));
            ev.setDeGrammatik(toValue(doc, FN.deGrammatik));
            ev.setDeGenus(toValue(doc, FN.deGenus));
            ev.setDeTags(toValue(doc, FN.deTags));
            ev.setDeRedirect(toValue(doc, FN.deRedirect));

            ev.setCategories(toValue(doc, FN.categories));
            // ev.setExamples(toValue(doc, FN.examples));
            ev.setUserComment(toValue(doc, FN.userComment));
            ev.setUserEmail(toValue(doc, FN.userEmail));

            // ev.setTimestamp(toValue(doc, FN.timestamp));
            ev.setVersionStatus(VersionStatus.valueOf(toValue(doc, FN.versionStatus)));

            ev.setCreator(toValue(doc, FN.creator));
            ev.setCreatorIp(toValue(doc, FN.creatorIp));
            ev.setCreatorRole(EditorRole.valueOf(toValue(doc, FN.creatorRole)));
            ev.setVerifier(toValue(doc, FN.verifier));

            ev.setAutomaticChange(Boolean.parseBoolean(toValue(doc, FN.automaticChange)));
        }
        // addPgFieldsToLemmaVersion(document, lv);
        return ev;
    }

    protected static void toFieldUpdateAllFields(Document doc, String key, String value, StringBuilder allFieldsList) {
        if (value == null) {
            return;
        }

        if (FieldManager.getInstance().getIgnored().contains(key)) {
            return;
        }

        List<IndexableField> fields = toField(key, value);
        if (fields == null) {
            logger.warn("No mapping for field {} - field will not be indexed!", key);
            FieldManager.getInstance().getIgnored().add(key);
            return;
        }

        for (IndexableField field : fields) {
            doc.add(field);
        }
        allFieldsList.append(key).append(' ');
    }

    private static String toValue(Document document, String fieldName) {
        IndexableField[] fields = document.getFields(fieldName);
        if (fields.length == 0) return null;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            sb.append(fields[i].stringValue());
            if (i < fields.length - 1) {
                sb.append(",");
            }
        }

        return sb.toString();
    }

    protected static List<IndexableField> toField(String key, String value) {
        List<IndexedColumn> indexFields = FieldManager.getInstance().getDbFieldMapping().get(key);
        if (indexFields == null || indexFields.isEmpty()) {
            return null;
        }

        List<IndexableField> fields = new ArrayList<>();
        for (IndexedColumn f : indexFields) {
            List<IndexableField> field = IndexedColumnHelper.getFields(f, value);
            if (field != null) {
                fields.addAll(field);
            }
        }
        return fields;
    }

    /**
     * Helper method to add the default pg fields of a {@link LemmaVersion} to a lucene {@link Document}.
     */
    protected static void addPgFieldsToDocument(LexEntry lexEntry, LemmaVersion version, Document document) {
        // TODO: check if added
        document.add(new StringField(LexEntry.ID, lexEntry.getId(), Field.Store.YES));
        document.add(new StringField(LemmaVersion.LEXENTRY_ID, lexEntry.getId(), Field.Store.YES));
        document.add(new StringField(LemmaVersion.ID, version.getInternalId() + "", Field.Store.YES));
        document.add(new StringField(LemmaVersion.VERIFICATION, version.getVerification().toString(), Field.Store.YES));
        document.add(new TextField(LemmaVersion.VERIFICATION + "_analyzed", version.getVerification().toString().toLowerCase(), Field.Store.NO));
    }

    /**
     * Helper method to add default PG fields from a lucene {@link Document} to a {@link LemmaVersion}.
     */
    protected static void addPgFieldsToLemmaVersion(Document document, LemmaVersion lemmaVersion) {
        lemmaVersion.putPgValue(LexEntry.ID, document.get(LexEntry.ID));
        lemmaVersion.putPgValue(LemmaVersion.LEXENTRY_ID, document.get(LemmaVersion.LEXENTRY_ID));
        lemmaVersion.putPgValue(LemmaVersion.ID, document.get(LemmaVersion.ID));
        lemmaVersion.putPgValue(LemmaVersion.VERIFICATION, document.get(LemmaVersion.VERIFICATION));
    }
}
