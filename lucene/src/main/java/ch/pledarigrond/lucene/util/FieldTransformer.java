package ch.pledarigrond.lucene.util;

import ch.pledarigrond.common.data.common.EditorRole;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.common.data.dictionary.ExampleDto;
import ch.pledarigrond.common.data.dictionary.inflection.*;
import ch.pledarigrond.common.data.lucene.IndexedColumn;
import ch.pledarigrond.lucene.core.FieldManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexableField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FieldTransformer {

    protected static final Logger logger = LoggerFactory.getLogger(FieldTransformer.class);

    static ObjectMapper objectMapper = new ObjectMapper(); // For JSON serialization

    public static Document getDocument(Language language, EntryDto entry, EntryVersionDto ev) {
        Document doc = new Document();

        toFieldUpdateAllFields(doc, FN.entryId, entry.getEntryId());
        toFieldUpdateAllFields(doc, FN.versionId, ev.getVersionId());
        toFieldUpdateAllFields(doc, FN.publicationStatus, entry.getPublicationStatus() != null ? entry.getPublicationStatus().toString() : "");
        toFieldUpdateAllFields(doc, FN.rmStichwort, ev.getRmStichwort());
        toFieldUpdateAllFields(doc, FN.rmStichwortSort, ev.getRmStichwortSort());
        toFieldUpdateAllFields(doc, FN.rmSemantik, ev.getRmSemantik());
        toFieldUpdateAllFields(doc, FN.rmSubsemantik, ev.getRmSubsemantik());
        toFieldUpdateAllFields(doc, FN.rmGrammatik, ev.getRmGrammatik());
        toFieldUpdateAllFields(doc, FN.rmGenus, ev.getRmGenus());
        toFieldUpdateAllFields(doc, FN.rmFlex, ev.getRmFlex());
        toFieldUpdateAllFields(doc, FN.rmTags, ev.getRmTags());
        toFieldUpdateAllFields(doc, FN.rmRedirect, ev.getRmRedirect());
        toFieldUpdateAllFields(doc, FN.rmEtymologie, ev.getRmEtymologie());
        toFieldUpdateAllFields(doc, FN.rmPronunciation, ev.getRmPronunciation());

        toFieldUpdateAllFields(doc, FN.deStichwort, ev.getDeStichwort());
        toFieldUpdateAllFields(doc, FN.deStichwortSort, ev.getDeStichwortSort());
        toFieldUpdateAllFields(doc, FN.deSemantik, ev.getDeSemantik());
        toFieldUpdateAllFields(doc, FN.deSubsemantik, ev.getDeSubsemantik());
        toFieldUpdateAllFields(doc, FN.deGrammatik, ev.getDeGrammatik());
        toFieldUpdateAllFields(doc, FN.deGenus, ev.getDeGenus());
        toFieldUpdateAllFields(doc, FN.deTags, ev.getDeTags());
        toFieldUpdateAllFields(doc, FN.deRedirect, ev.getDeRedirect());

        toFieldUpdateAllFields(doc, FN.categories, ev.getCategories());
        String examplesJson = "[]";
        try {
            examplesJson = objectMapper.writeValueAsString(ev.getExamples());
        } catch (JsonProcessingException e) {
            logger.error("Error serializing examples to JSON", e);
        }
        toFieldUpdateAllFields(doc, FN.examplesJson, examplesJson);
        for (ExampleDto dto : ev.getExamples()) {
            if (dto.getRm() != null) {
                toFieldUpdateAllFields(doc, FN.examplesRm, dto.getRm());
            }
            if (dto.getDe() != null) {
                toFieldUpdateAllFields(doc, FN.examplesDe, dto.getDe());
            }
        }
        toFieldUpdateAllFields(doc, FN.userComment, ev.getUserComment());
        toFieldUpdateAllFields(doc, FN.userEmail, ev.getUserEmail());

        toFieldUpdateAllFields(doc, FN.timestamp, ev.getTimestamp() != null ? ev.getTimestamp().toString() : "");

        toFieldUpdateAllFields(doc, FN.creator, ev.getCreator());
        toFieldUpdateAllFields(doc, FN.creatorIp, ev.getCreatorIp());
        toFieldUpdateAllFields(doc, FN.creatorRole, ev.getCreatorRole() != null ? ev.getCreatorRole().toString() : "");

        if (ev.getAutomaticChange() != null) {
            toFieldUpdateAllFields(doc, FN.automaticChange, ev.getAutomaticChange() + "");
        }

        if (ev.getInflection() != null) {
            InflectionDto i = ev.getInflection();
            toFieldUpdateAllFields(doc, FN.inflectionType, Optional.ofNullable(i.getInflectionType()).map(InflectionType::name).orElse(null));
            toFieldUpdateAllFields(doc, FN.reviewLater, i.isReviewLater() ? "true" : "false");

            if (i.getInflectionType() != null && i.getInflectionType().equals(InflectionType.VERB) && i.getVerb() != null) {
                VerbDto v = i.getVerb();
                toFieldUpdateAllFields(doc, FN.infinitiv, v.getInfinitiv());
                toFieldUpdateAllFields(doc, FN.irregular, v.isIrregular() ? "true" : "false");
                toFieldUpdateAllFields(doc, FN.inflectionSubtype, v.getInflectionSubtype());
                toFieldUpdateAllFields(doc, FN.composedWith, v.getComposedWith());

                if (v.getPreschent() != null) {
                    toFieldUpdateAllFields(doc, FN.preschentsing1, v.getPreschent().getSing1());
                    toFieldUpdateAllFields(doc, FN.preschentsing2, v.getPreschent().getSing2());
                    toFieldUpdateAllFields(doc, FN.preschentsing3, v.getPreschent().getSing3());
                    toFieldUpdateAllFields(doc, FN.preschentplural1, v.getPreschent().getPlural1());
                    toFieldUpdateAllFields(doc, FN.preschentplural2, v.getPreschent().getPlural2());
                    toFieldUpdateAllFields(doc, FN.preschentplural3, v.getPreschent().getPlural3());
                }

                if (v.getImperfect() != null) {
                    toFieldUpdateAllFields(doc, FN.imperfectsing1, v.getImperfect().getSing1());
                    toFieldUpdateAllFields(doc, FN.imperfectsing2, v.getImperfect().getSing2());
                    toFieldUpdateAllFields(doc, FN.imperfectsing3, v.getImperfect().getSing3());
                    toFieldUpdateAllFields(doc, FN.imperfectplural1, v.getImperfect().getPlural1());
                    toFieldUpdateAllFields(doc, FN.imperfectplural2, v.getImperfect().getPlural2());
                    toFieldUpdateAllFields(doc, FN.imperfectplural3, v.getImperfect().getPlural3());
                }

                if (v.getConjunctiv() != null) {
                    toFieldUpdateAllFields(doc, FN.conjunctivsing1, v.getConjunctiv().getSing1());
                    toFieldUpdateAllFields(doc, FN.conjunctivsing2, v.getConjunctiv().getSing2());
                    toFieldUpdateAllFields(doc, FN.conjunctivsing3, v.getConjunctiv().getSing3());
                    toFieldUpdateAllFields(doc, FN.conjunctivplural1, v.getConjunctiv().getPlural1());
                    toFieldUpdateAllFields(doc, FN.conjunctivplural2, v.getConjunctiv().getPlural2());
                    toFieldUpdateAllFields(doc, FN.conjunctivplural3, v.getConjunctiv().getPlural3());
                }

                if (language != Language.RUMANTSCHGRISCHUN && language != Language.SURMIRAN) {
                    if (v.getConjunctivImperfect() != null) {
                        toFieldUpdateAllFields(doc, FN.conjunctivimperfectsing1, v.getConjunctivImperfect().getSing1());
                        toFieldUpdateAllFields(doc, FN.conjunctivimperfectsing2, v.getConjunctivImperfect().getSing2());
                        toFieldUpdateAllFields(doc, FN.conjunctivimperfectsing3, v.getConjunctivImperfect().getSing3());
                        toFieldUpdateAllFields(doc, FN.conjunctivimperfectplural1, v.getConjunctivImperfect().getPlural1());
                        toFieldUpdateAllFields(doc, FN.conjunctivimperfectplural2, v.getConjunctivImperfect().getPlural2());
                        toFieldUpdateAllFields(doc, FN.conjunctivimperfectplural3, v.getConjunctivImperfect().getPlural3());
                    }
                }

                if (v.getCundiziunal() != null) {
                    toFieldUpdateAllFields(doc, FN.cundizionalsing1, v.getCundiziunal().getSing1());
                    toFieldUpdateAllFields(doc, FN.cundizionalsing2, v.getCundiziunal().getSing2());
                    toFieldUpdateAllFields(doc, FN.cundizionalsing3, v.getCundiziunal().getSing3());
                    toFieldUpdateAllFields(doc, FN.cundizionalplural1, v.getCundiziunal().getPlural1());
                    toFieldUpdateAllFields(doc, FN.cundizionalplural2, v.getCundiziunal().getPlural2());
                    toFieldUpdateAllFields(doc, FN.cundizionalplural3, v.getCundiziunal().getPlural3());
                }

                if (language == Language.SURSILVAN || language == Language.SUTSILVAN) {
                    if (v.getCundiziunalIndirect() != null) {
                        toFieldUpdateAllFields(doc, FN.cundizionalindirectsing1, v.getCundiziunalIndirect().getSing1());
                        toFieldUpdateAllFields(doc, FN.cundizionalindirectsing2, v.getCundiziunalIndirect().getSing2());
                        toFieldUpdateAllFields(doc, FN.cundizionalindirectsing3, v.getCundiziunalIndirect().getSing3());
                        toFieldUpdateAllFields(doc, FN.cundizionalindirectplural1, v.getCundiziunalIndirect().getPlural1());
                        toFieldUpdateAllFields(doc, FN.cundizionalindirectplural2, v.getCundiziunalIndirect().getPlural2());
                        toFieldUpdateAllFields(doc, FN.cundizionalindirectplural3, v.getCundiziunalIndirect().getPlural3());
                    }
                }

                if (v.getParticipPerfect() != null) {
                    toFieldUpdateAllFields(doc, FN.participperfectms, v.getParticipPerfect().getMs());
                    toFieldUpdateAllFields(doc, FN.participperfectfs, v.getParticipPerfect().getFs());
                    toFieldUpdateAllFields(doc, FN.participperfectmp, v.getParticipPerfect().getMp());
                    toFieldUpdateAllFields(doc, FN.participperfectfp, v.getParticipPerfect().getFp());
                    if (language == Language.SURSILVAN) {
                        toFieldUpdateAllFields(doc, FN.participperfectmspredicativ, v.getParticipPerfect().getMsPredicativ());
                    }
                }

                if (v.getImperativ() != null) {
                    toFieldUpdateAllFields(doc, FN.imperativSing, v.getImperativ().getSingular());
                    toFieldUpdateAllFields(doc, FN.imperativPlural, v.getImperativ().getPlural());
                    if (language == Language.PUTER || language == Language.VALLADER) {
                        toFieldUpdateAllFields(doc, FN.imperativ3, v.getImperativ().getForm3());
                        toFieldUpdateAllFields(doc, FN.imperativ4, v.getImperativ().getForm4());
                        toFieldUpdateAllFields(doc, FN.imperativ5, v.getImperativ().getForm5());
                        toFieldUpdateAllFields(doc, FN.imperativ6, v.getImperativ().getForm6());
                    }
                }

                toFieldUpdateAllFields(doc, FN.gerundium, v.getGerundium());

                if (v.getFutur() != null) {
                    toFieldUpdateAllFields(doc, FN.futursing1, v.getFutur().getSing1());
                    toFieldUpdateAllFields(doc, FN.futursing2, v.getFutur().getSing2());
                    toFieldUpdateAllFields(doc, FN.futursing3, v.getFutur().getSing3());
                    toFieldUpdateAllFields(doc, FN.futurplural1, v.getFutur().getPlural1());
                    toFieldUpdateAllFields(doc, FN.futurplural2, v.getFutur().getPlural2());
                    toFieldUpdateAllFields(doc, FN.futurplural3, v.getFutur().getPlural3());
                }

                if (language == Language.PUTER) {
                    if (v.getFuturDubitativ() != null) {
                        toFieldUpdateAllFields(doc, FN.futurdubitativsing1, v.getFuturDubitativ().getSing1());
                        toFieldUpdateAllFields(doc, FN.futurdubitativsing2, v.getFuturDubitativ().getSing2());
                        toFieldUpdateAllFields(doc, FN.futurdubitativsing3, v.getFuturDubitativ().getSing3());
                        toFieldUpdateAllFields(doc, FN.futurdubitativplural1, v.getFuturDubitativ().getPlural1());
                        toFieldUpdateAllFields(doc, FN.futurdubitativplural2, v.getFuturDubitativ().getPlural2());
                        toFieldUpdateAllFields(doc, FN.futurdubitativplural3, v.getFuturDubitativ().getPlural3());
                    }
                }

                // Enclitic forms
                if (language == Language.PUTER || language == Language.SURMIRAN || language == Language.SUTSILVAN || language == Language.VALLADER) {
                    if (v.getPreschentEnclitic() != null) {
                        toFieldUpdateAllFields(doc, FN.preschentsing1enclitic, v.getPreschentEnclitic().getSing1());
                        toFieldUpdateAllFields(doc, FN.preschentsing2enclitic, v.getPreschentEnclitic().getSing2());
                        toFieldUpdateAllFields(doc, FN.preschentsing3encliticm, v.getPreschentEnclitic().getSing3m());
                        toFieldUpdateAllFields(doc, FN.preschentsing3encliticf, v.getPreschentEnclitic().getSing3f());
                        toFieldUpdateAllFields(doc, FN.preschentplural1enclitic, v.getPreschentEnclitic().getPlural1());
                        toFieldUpdateAllFields(doc, FN.preschentplural2enclitic, v.getPreschentEnclitic().getPlural2());
                        toFieldUpdateAllFields(doc, FN.preschentplural3enclitic, v.getPreschentEnclitic().getPlural3());
                    }
                }

                if (language == Language.PUTER || language == Language.SURMIRAN || language == Language.SUTSILVAN || language == Language.VALLADER) {
                    if (v.getImperfectEnclitic() != null) {
                        toFieldUpdateAllFields(doc, FN.imperfectsing1enclitic, v.getImperfectEnclitic().getSing1());
                        toFieldUpdateAllFields(doc, FN.imperfectsing2enclitic, v.getImperfectEnclitic().getSing2());
                        toFieldUpdateAllFields(doc, FN.imperfectsing3encliticm, v.getImperfectEnclitic().getSing3m());
                        toFieldUpdateAllFields(doc, FN.imperfectsing3encliticf, v.getImperfectEnclitic().getSing3f());
                        toFieldUpdateAllFields(doc, FN.imperfectplural1enclitic, v.getImperfectEnclitic().getPlural1());
                        toFieldUpdateAllFields(doc, FN.imperfectplural2enclitic, v.getImperfectEnclitic().getPlural2());
                        toFieldUpdateAllFields(doc, FN.imperfectplural3enclitic, v.getImperfectEnclitic().getPlural3());
                    }
                }

                if (language == Language.PUTER || language == Language.SURMIRAN || language == Language.SUTSILVAN || language == Language.VALLADER) {
                    if (v.getCundiziunalEnclitic() != null) {
                        toFieldUpdateAllFields(doc, FN.cundizionalsing1enclitic, v.getCundiziunalEnclitic().getSing1());
                        toFieldUpdateAllFields(doc, FN.cundizionalsing2enclitic, v.getCundiziunalEnclitic().getSing2());
                        toFieldUpdateAllFields(doc, FN.cundizionalsing3encliticm, v.getCundiziunalEnclitic().getSing3m());
                        toFieldUpdateAllFields(doc, FN.cundizionalsing3encliticf, v.getCundiziunalEnclitic().getSing3f());
                        toFieldUpdateAllFields(doc, FN.cundizionalplural1enclitic, v.getCundiziunalEnclitic().getPlural1());
                        toFieldUpdateAllFields(doc, FN.cundizionalplural2enclitic, v.getCundiziunalEnclitic().getPlural2());
                        toFieldUpdateAllFields(doc, FN.cundizionalplural3enclitic, v.getCundiziunalEnclitic().getPlural3());
                    }
                }

                if (language == Language.PUTER || language == Language.SURMIRAN || language == Language.VALLADER) {
                    if (v.getFuturEnclitic() != null) {
                        toFieldUpdateAllFields(doc, FN.futursing1enclitic, v.getFuturEnclitic().getSing1());
                        toFieldUpdateAllFields(doc, FN.futursing2enclitic, v.getFuturEnclitic().getSing2());
                        toFieldUpdateAllFields(doc, FN.futursing3encliticm, v.getFuturEnclitic().getSing3m());
                        toFieldUpdateAllFields(doc, FN.futursing3encliticf, v.getFuturEnclitic().getSing3f());
                        toFieldUpdateAllFields(doc, FN.futurplural1enclitic, v.getFuturEnclitic().getPlural1());
                        toFieldUpdateAllFields(doc, FN.futurplural2enclitic, v.getFuturEnclitic().getPlural2());
                        toFieldUpdateAllFields(doc, FN.futurplural3enclitic, v.getFuturEnclitic().getPlural3());
                    }
                }

                if (language == Language.PUTER) {
                    if (v.getFuturDubitativEnclitic() != null) {
                        toFieldUpdateAllFields(doc, FN.futurdubitativsing1enclitic, v.getFuturDubitativEnclitic().getSing1());
                        toFieldUpdateAllFields(doc, FN.futurdubitativsing2enclitic, v.getFuturDubitativEnclitic().getSing2());
                        toFieldUpdateAllFields(doc, FN.futurdubitativsing3encliticm, v.getFuturDubitativEnclitic().getSing3m());
                        toFieldUpdateAllFields(doc, FN.futurdubitativsing3encliticf, v.getFuturDubitativEnclitic().getSing3f());
                        toFieldUpdateAllFields(doc, FN.futurdubitativplural1enclitic, v.getFuturDubitativEnclitic().getPlural1());
                        toFieldUpdateAllFields(doc, FN.futurdubitativplural2enclitic, v.getFuturDubitativEnclitic().getPlural2());
                        toFieldUpdateAllFields(doc, FN.futurdubitativplural3enclitic, v.getFuturDubitativEnclitic().getPlural3());
                    }
                }
            } else if (i.getInflectionType() != null && i.getInflectionType().equals(InflectionType.NOUN) && i.getNoun() != null) {
                NounDto n = i.getNoun();
                toFieldUpdateAllFields(doc, FN.baseForm, n.getBaseForm());
                toFieldUpdateAllFields(doc, FN.irregular, n.isIrregular() ? "true" : "false");
                toFieldUpdateAllFields(doc, FN.inflectionSubtype, n.getInflectionSubtype());

                toFieldUpdateAllFields(doc, FN.mSingular, n.getMSingular());
                toFieldUpdateAllFields(doc, FN.fSingular, n.getFSingular());
                toFieldUpdateAllFields(doc, FN.mPlural, n.getMPlural());
                toFieldUpdateAllFields(doc, FN.fPlural, n.getFPlural());
                toFieldUpdateAllFields(doc, FN.pluralCollectiv, n.getPluralCollectiv());
            } else if (i.getInflectionType() != null && i.getInflectionType().equals(InflectionType.ADJECTIVE) && i.getAdjective() != null) {
                AdjectiveDto a = i.getAdjective();
                toFieldUpdateAllFields(doc, FN.baseForm, a.getBaseForm());
                toFieldUpdateAllFields(doc, FN.irregular, a.isIrregular() ? "true" : "false");
                toFieldUpdateAllFields(doc, FN.inflectionSubtype, a.getInflectionSubtype());

                toFieldUpdateAllFields(doc, FN.mSingular, a.getMSingular());
                toFieldUpdateAllFields(doc, FN.fSingular, a.getFSingular());
                toFieldUpdateAllFields(doc, FN.mPlural, a.getMPlural());
                toFieldUpdateAllFields(doc, FN.fPlural, a.getFPlural());
                toFieldUpdateAllFields(doc, FN.adverbialForm, a.getAdverbialForm());
                toFieldUpdateAllFields(doc, FN.predicative, a.getPredicative());
            } else if (i.getInflectionType() != null && i.getInflectionType().equals(InflectionType.PRONOUN) && i.getPronoun() != null) {
                PronounDto p = i.getPronoun();
                toFieldUpdateAllFields(doc, FN.baseForm, p.getBaseForm());
                toFieldUpdateAllFields(doc, FN.mSingular, p.getMSingular());
                toFieldUpdateAllFields(doc, FN.fSingular, p.getFSingular());
                toFieldUpdateAllFields(doc, FN.mPlural, p.getMPlural());
                toFieldUpdateAllFields(doc, FN.fPlural, p.getFPlural());
            } else if (i.getInflectionType() != null && i.getInflectionType().equals(InflectionType.OTHER) && i.getOther() != null) {
                OtherDto o = i.getOther();
                toFieldUpdateAllFields(doc, FN.baseForm, o.getBaseForm());
                toFieldUpdateAllFields(doc, FN.otherForm1, o.getOtherForm1());
                toFieldUpdateAllFields(doc, FN.otherForm2, o.getOtherForm2());
                toFieldUpdateAllFields(doc, FN.otherForm3, o.getOtherForm3());
                toFieldUpdateAllFields(doc, FN.otherForm4, o.getOtherForm4());
            }
        }

        return doc;
    }

    public static EntryVersionDto getEntryVersion(Language language, Document doc) {
        EntryVersionDto ev = new EntryVersionDto();

        ev.setEntryId(toValue(doc, FN.entryId));
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
        String examplesJson = toValue(doc, FN.examplesJson);
        if (examplesJson != null && !examplesJson.isEmpty()) {
            try {
                ev.setExamples(objectMapper.readValue(examplesJson, new TypeReference<List<ExampleDto>>() {}));
            } catch (JsonProcessingException e) {
                logger.error("Error deserializing examples JSON", e);
                ev.setExamples(new ArrayList<>());
            }
        } else {
            ev.setExamples(new ArrayList<>());
        }
        ev.setUserComment(toValue(doc, FN.userComment));
        ev.setUserEmail(toValue(doc, FN.userEmail));

        ev.setTimestamp(createInstantFromString(toValue(doc, FN.timestamp)));

        ev.setCreator(toValue(doc, FN.creator));
        ev.setCreatorIp(toValue(doc, FN.creatorIp));
        String creatorRole = toValue(doc, FN.creatorRole);
        if (creatorRole != null && creatorRole.isEmpty()) {
            ev.setCreatorRole(EditorRole.valueOf(creatorRole));
        }

        ev.setAutomaticChange(Boolean.parseBoolean(toValue(doc, FN.automaticChange)));

        String inflectionType = toValue(doc, FN.inflectionType);
        if (inflectionType != null && !inflectionType.isEmpty()) {
            InflectionDto i = new InflectionDto();
            i.setInflectionType(InflectionType.fromString(inflectionType));
            i.setReviewLater(Boolean.parseBoolean(toValue(doc, FN.reviewLater)));

            if (i.getInflectionType() != null && i.getInflectionType().equals(InflectionType.VERB)) {
                VerbDto v = new VerbDto();
                v.setInfinitiv(toValue(doc, FN.infinitiv));
                v.setIrregular(Boolean.parseBoolean(toValue(doc, FN.irregular)));
                v.setInflectionSubtype(toValue(doc, FN.inflectionSubtype));
                v.setComposedWith(toValue(doc, FN.composedWith));

                v.setPreschent(new VerbDto.PersonalVerbDto());
                v.getPreschent().setSing1(toValue(doc, FN.preschentsing1));
                v.getPreschent().setSing2(toValue(doc, FN.preschentsing2));
                v.getPreschent().setSing3(toValue(doc, FN.preschentsing3));
                v.getPreschent().setPlural1(toValue(doc, FN.preschentplural1));
                v.getPreschent().setPlural2(toValue(doc, FN.preschentplural2));
                v.getPreschent().setPlural3(toValue(doc, FN.preschentplural3));

                v.setImperfect(new VerbDto.PersonalVerbDto());
                v.getImperfect().setSing1(toValue(doc, FN.imperfectsing1));
                v.getImperfect().setSing2(toValue(doc, FN.imperfectsing2));
                v.getImperfect().setSing3(toValue(doc, FN.imperfectsing3));
                v.getImperfect().setPlural1(toValue(doc, FN.imperfectplural1));
                v.getImperfect().setPlural2(toValue(doc, FN.imperfectplural2));
                v.getImperfect().setPlural3(toValue(doc, FN.imperfectplural3));

                v.setConjunctiv(new VerbDto.PersonalVerbDto());
                v.getConjunctiv().setSing1(toValue(doc, FN.conjunctivsing1));
                v.getConjunctiv().setSing2(toValue(doc, FN.conjunctivsing2));
                v.getConjunctiv().setSing3(toValue(doc, FN.conjunctivsing3));
                v.getConjunctiv().setPlural1(toValue(doc, FN.conjunctivplural1));
                v.getConjunctiv().setPlural2(toValue(doc, FN.conjunctivplural2));
                v.getConjunctiv().setPlural3(toValue(doc, FN.conjunctivplural3));

                if (language != Language.RUMANTSCHGRISCHUN && language != Language.SURMIRAN) {
                    v.setConjunctivImperfect(new VerbDto.PersonalVerbDto());
                    v.getConjunctivImperfect().setSing1(toValue(doc, FN.conjunctivimperfectsing1));
                    v.getConjunctivImperfect().setSing2(toValue(doc, FN.conjunctivimperfectsing2));
                    v.getConjunctivImperfect().setSing3(toValue(doc, FN.conjunctivimperfectsing3));
                    v.getConjunctivImperfect().setPlural1(toValue(doc, FN.conjunctivimperfectplural1));
                    v.getConjunctivImperfect().setPlural2(toValue(doc, FN.conjunctivimperfectplural2));
                    v.getConjunctivImperfect().setPlural3(toValue(doc, FN.conjunctivimperfectplural3));
                }

                v.setCundiziunal(new VerbDto.PersonalVerbDto());
                v.getCundiziunal().setSing1(toValue(doc, FN.cundizionalsing1));
                v.getCundiziunal().setSing2(toValue(doc, FN.cundizionalsing2));
                v.getCundiziunal().setSing3(toValue(doc, FN.cundizionalsing3));
                v.getCundiziunal().setPlural1(toValue(doc, FN.cundizionalplural1));
                v.getCundiziunal().setPlural2(toValue(doc, FN.cundizionalplural2));
                v.getCundiziunal().setPlural3(toValue(doc, FN.cundizionalplural3));

                if (language == Language.SURSILVAN || language == Language.SUTSILVAN) {
                    v.setCundiziunalIndirect(new VerbDto.PersonalVerbDto());
                    v.getCundiziunalIndirect().setSing1(toValue(doc, FN.cundizionalindirectsing1));
                    v.getCundiziunalIndirect().setSing2(toValue(doc, FN.cundizionalindirectsing2));
                    v.getCundiziunalIndirect().setSing3(toValue(doc, FN.cundizionalindirectsing3));
                    v.getCundiziunalIndirect().setPlural1(toValue(doc, FN.cundizionalindirectplural1));
                    v.getCundiziunalIndirect().setPlural2(toValue(doc, FN.cundizionalindirectplural2));
                    v.getCundiziunalIndirect().setPlural3(toValue(doc, FN.cundizionalindirectplural3));
                }

                v.setParticipPerfect(new VerbDto.ParticipPerfectDto());
                v.getParticipPerfect().setMs(toValue(doc, FN.participperfectms));
                v.getParticipPerfect().setFs(toValue(doc, FN.participperfectfs));
                v.getParticipPerfect().setMp(toValue(doc, FN.participperfectmp));
                v.getParticipPerfect().setFp(toValue(doc, FN.participperfectfp));
                if (language == Language.SURSILVAN) {
                    v.getParticipPerfect().setMsPredicativ(toValue(doc, FN.participperfectmspredicativ));
                }

                v.setImperativ(new VerbDto.ImperativDto());
                v.getImperativ().setSingular(toValue(doc, FN.imperativSing));
                v.getImperativ().setPlural(toValue(doc, FN.imperativPlural));
                if (language == Language.PUTER || language == Language.VALLADER) {
                    v.getImperativ().setForm3(toValue(doc, FN.imperativ3));
                    v.getImperativ().setForm4(toValue(doc, FN.imperativ4));
                    v.getImperativ().setForm5(toValue(doc, FN.imperativ5));
                    v.getImperativ().setForm6(toValue(doc, FN.imperativ6));
                }

                v.setGerundium(toValue(doc, FN.gerundium));

                v.setFutur(new VerbDto.PersonalVerbDto());
                v.getFutur().setSing1(toValue(doc, FN.futursing1));
                v.getFutur().setSing2(toValue(doc, FN.futursing2));
                v.getFutur().setSing3(toValue(doc, FN.futursing3));
                v.getFutur().setPlural1(toValue(doc, FN.futurplural1));
                v.getFutur().setPlural2(toValue(doc, FN.futurplural2));
                v.getFutur().setPlural3(toValue(doc, FN.futurplural3));

                if (language == Language.PUTER) {
                    v.setFuturDubitativ(new VerbDto.PersonalVerbDto());
                    v.getFuturDubitativ().setSing1(toValue(doc, FN.futurdubitativsing1));
                    v.getFuturDubitativ().setSing2(toValue(doc, FN.futurdubitativsing2));
                    v.getFuturDubitativ().setSing3(toValue(doc, FN.futurdubitativsing3));
                    v.getFuturDubitativ().setPlural1(toValue(doc, FN.futurdubitativplural1));
                    v.getFuturDubitativ().setPlural2(toValue(doc, FN.futurdubitativplural2));
                    v.getFuturDubitativ().setPlural3(toValue(doc, FN.futurdubitativplural3));
                }

                // Enclitic forms
                if (language == Language.PUTER || language == Language.SURMIRAN || language == Language.SUTSILVAN || language == Language.VALLADER) {
                    v.setPreschentEnclitic(new VerbDto.PersonalVerbEncliticDto());
                    v.getPreschentEnclitic().setSing1(toValue(doc, FN.preschentsing1enclitic));
                    v.getPreschentEnclitic().setSing2(toValue(doc, FN.preschentsing2enclitic));
                    v.getPreschentEnclitic().setSing3m(toValue(doc, FN.preschentsing3encliticm));
                    v.getPreschentEnclitic().setSing3f(toValue(doc, FN.preschentsing3encliticf));
                    v.getPreschentEnclitic().setPlural1(toValue(doc, FN.preschentplural1enclitic));
                    v.getPreschentEnclitic().setPlural2(toValue(doc, FN.preschentplural2enclitic));
                    v.getPreschentEnclitic().setPlural3(toValue(doc, FN.preschentplural3enclitic));
                }

                if (language == Language.PUTER || language == Language.SURMIRAN || language == Language.SUTSILVAN || language == Language.VALLADER) {
                    v.setImperfectEnclitic(new VerbDto.PersonalVerbEncliticDto());
                    v.getImperfectEnclitic().setSing1(toValue(doc, FN.imperfectsing1enclitic));
                    v.getImperfectEnclitic().setSing2(toValue(doc, FN.imperfectsing2enclitic));
                    v.getImperfectEnclitic().setSing3m(toValue(doc, FN.imperfectsing3encliticm));
                    v.getImperfectEnclitic().setSing3f(toValue(doc, FN.imperfectsing3encliticf));
                    v.getImperfectEnclitic().setPlural1(toValue(doc, FN.imperfectplural1enclitic));
                    v.getImperfectEnclitic().setPlural2(toValue(doc, FN.imperfectplural2enclitic));
                    v.getImperfectEnclitic().setPlural3(toValue(doc, FN.imperfectplural3enclitic));
                }

                if (language == Language.PUTER || language == Language.SURMIRAN || language == Language.SUTSILVAN || language == Language.VALLADER) {
                    v.setCundiziunalEnclitic(new VerbDto.PersonalVerbEncliticDto());
                    v.getCundiziunalEnclitic().setSing1(toValue(doc, FN.cundizionalsing1enclitic));
                    v.getCundiziunalEnclitic().setSing2(toValue(doc, FN.cundizionalsing2enclitic));
                    v.getCundiziunalEnclitic().setSing3m(toValue(doc, FN.cundizionalsing3encliticm));
                    v.getCundiziunalEnclitic().setSing3f(toValue(doc, FN.cundizionalsing3encliticf));
                    v.getCundiziunalEnclitic().setPlural1(toValue(doc, FN.cundizionalplural1enclitic));
                    v.getCundiziunalEnclitic().setPlural2(toValue(doc, FN.cundizionalplural2enclitic));
                    v.getCundiziunalEnclitic().setPlural3(toValue(doc, FN.cundizionalplural3enclitic));
                }

                if (language == Language.PUTER || language == Language.SURMIRAN || language == Language.VALLADER) {
                    v.setFuturEnclitic(new VerbDto.PersonalVerbEncliticDto());
                    v.getFuturEnclitic().setSing1(toValue(doc, FN.futursing1enclitic));
                    v.getFuturEnclitic().setSing2(toValue(doc, FN.futursing2enclitic));
                    v.getFuturEnclitic().setSing3m(toValue(doc, FN.futursing3encliticm));
                    v.getFuturEnclitic().setSing3f(toValue(doc, FN.futursing3encliticf));
                    v.getFuturEnclitic().setPlural1(toValue(doc, FN.futurplural1enclitic));
                    v.getFuturEnclitic().setPlural2(toValue(doc, FN.futurplural2enclitic));
                    v.getFuturEnclitic().setPlural3(toValue(doc, FN.futurplural3enclitic));
                }

                if (language == Language.PUTER) {
                    v.setFuturDubitativEnclitic(new VerbDto.PersonalVerbEncliticDto());
                    v.getFuturDubitativEnclitic().setSing1(toValue(doc, FN.futurdubitativsing1enclitic));
                    v.getFuturDubitativEnclitic().setSing2(toValue(doc, FN.futurdubitativsing2enclitic));
                    v.getFuturDubitativEnclitic().setSing3m(toValue(doc, FN.futurdubitativsing3encliticm));
                    v.getFuturDubitativEnclitic().setSing3f(toValue(doc, FN.futurdubitativsing3encliticf));
                    v.getFuturDubitativEnclitic().setPlural1(toValue(doc, FN.futurdubitativplural1enclitic));
                    v.getFuturDubitativEnclitic().setPlural2(toValue(doc, FN.futurdubitativplural2enclitic));
                    v.getFuturDubitativEnclitic().setPlural3(toValue(doc, FN.futurdubitativplural3enclitic));
                }

                i.setVerb(v);
            } else if(i.getInflectionType() != null && i.getInflectionType().equals(InflectionType.NOUN)) {
                NounDto n = new NounDto();
                n.setBaseForm(toValue(doc, FN.baseForm));
                n.setIrregular(Boolean.parseBoolean(toValue(doc, FN.irregular)));
                n.setInflectionSubtype(toValue(doc, FN.inflectionSubtype));

                n.setMSingular(toValue(doc, FN.mSingular));
                n.setFSingular(toValue(doc, FN.fSingular));
                n.setMPlural(toValue(doc, FN.mPlural));
                n.setFPlural(toValue(doc, FN.fPlural));
                n.setPluralCollectiv(toValue(doc, FN.pluralCollectiv));
                i.setNoun(n);
            } else if (i.getInflectionType() != null && i.getInflectionType().equals(InflectionType.ADJECTIVE)) {
                AdjectiveDto a = new AdjectiveDto();
                a.setBaseForm(toValue(doc, FN.baseForm));
                a.setIrregular(Boolean.parseBoolean(toValue(doc, FN.irregular)));
                a.setInflectionSubtype(toValue(doc, FN.inflectionSubtype));

                a.setMSingular(toValue(doc, FN.mSingular));
                a.setFSingular(toValue(doc, FN.fSingular));
                a.setMPlural(toValue(doc, FN.mPlural));
                a.setFPlural(toValue(doc, FN.fPlural));
                a.setAdverbialForm(toValue(doc, FN.adverbialForm));
                a.setPredicative(toValue(doc, FN.predicative));
                i.setAdjective(a);
            } else if (i.getInflectionType() != null && i.getInflectionType().equals(InflectionType.PRONOUN)) {
                PronounDto p = new PronounDto();
                p.setBaseForm(toValue(doc, FN.baseForm));
                p.setMSingular(toValue(doc, FN.mSingular));
                p.setFSingular(toValue(doc, FN.fSingular));
                p.setMPlural(toValue(doc, FN.mPlural));
                p.setFPlural(toValue(doc, FN.fPlural));
                i.setPronoun(p);
            } else if (i.getInflectionType() != null && i.getInflectionType().equals(InflectionType.OTHER)) {
                OtherDto o = new OtherDto();
                o.setBaseForm(toValue(doc, FN.baseForm));
                o.setOtherForm1(toValue(doc, FN.otherForm1));
                o.setOtherForm2(toValue(doc, FN.otherForm2));
                o.setOtherForm3(toValue(doc, FN.otherForm3));
                o.setOtherForm4(toValue(doc, FN.otherForm4));
                i.setOther(o);
            }

            ev.setInflection(i);
        }

        return ev;
    }

    protected static void toFieldUpdateAllFields(Document doc, String key, String value) {
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

    public static Instant createInstantFromString(String timestampString) {
        if (timestampString == null || timestampString.isEmpty()) {
            return null;
        }
        try {
            return Instant.parse(timestampString);
        } catch (java.time.format.DateTimeParseException e) {
            logger.error("Error parsing timestamp string: {} - {}", timestampString, e.getMessage());
            return null;
        }
    }
}
