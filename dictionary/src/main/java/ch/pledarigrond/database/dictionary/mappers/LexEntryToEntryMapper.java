package ch.pledarigrond.database.dictionary.mappers;

import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.database.dictionary.entities.Entry;
import ch.pledarigrond.database.dictionary.entities.EntryVersion;
import ch.pledarigrond.database.dictionary.entities.inflection.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LexEntryToEntryMapper {

    private static final Logger logger = LoggerFactory.getLogger(LexEntryToEntryMapper.class);

    public static Entry map(LexEntry lexEntry, Language language) {
        logger.warn(" ");
        logger.warn(" ");
        logger.warn("***§***********§***********§***********§***********§***********§***********§");
        logger.warn("LexEntryToEntryMapper map entry. ID: {}", lexEntry.getId());

        Entry entry = new Entry();
        entry.setEntryId(lexEntry.getId());

        Map<String, String> internalToNewIdMap = new HashMap<>();
        ArrayList<EntryVersion> versions = new ArrayList<>();
        for (LemmaVersion lv: lexEntry.getVersionHistory()) {
            String internalId = String.valueOf(lv.getInternalId());
            if (internalToNewIdMap.containsKey(internalId)) {
                logger.error("    >> Version history contains duplicate entry: {}", lv.getInternalId());
            } else {
                EntryVersion ev = map(lv, language);
                versions.add(ev);
                internalToNewIdMap.put(internalId, ev.getVersionId());
            }
        }
        entry.setVersions(versions);

        ArrayList<EntryVersion> suggestions = new ArrayList<>();
        for (LemmaVersion lv : lexEntry.getUnapprovedVersions()) {
            String internalId = String.valueOf(lv.getInternalId());
            String transformedId = internalToNewIdMap.get(internalId);
            versions.stream()
                    .filter(version -> version.getVersionId().equals(transformedId))
                    .findFirst().ifPresent(suggestions::add);
        }
        entry.setSuggestions(suggestions);

        String transformedId = internalToNewIdMap.get(String.valueOf(lexEntry.getCurrentId()));
        versions.stream()
                        .filter(version -> version.getVersionId().equals(transformedId))
                        .findFirst().ifPresent(entry::setCurrent);

        return entry;
    }

    public static EntryVersion map(LemmaVersion lv, Language language) {
        logger.warn("    > LexEntryToEntryMapper map entry version. ID {}: {} / {}", lv.getInternalId(), lv.getLemmaValues().get("RStichwort"), lv.getLemmaValues().get("DStichwort"));

        EntryVersion dv = new EntryVersion();

        String timestamp = getPgField(lv, "timestamp", false);
        dv.setTimestamp(Instant.ofEpochMilli(timestamp == null ? 0L : Long.parseLong(timestamp)));

        dv.setUserComment(getField(lv, "contact_comment", false));
        dv.setUserEmail(getField(lv, "contact_email", false));
        dv.setCreatorIp(getPgField(lv, "user_ip", false));

        String verifier = getPgField(lv, "verifier", false);
        if (verifier != null) {
            dv.setCreator(verifier);
            getPgField(lv, "creator", false);
        } else {
            dv.setCreator(getPgField(lv, "creator", false));
        }
        dv.setCreatorRole(getCreatorRole(getPgField(lv, "creator_role", false)));
        dv.setCreatorRole(lv.getCreatorRole());
        dv.setAction(getAction(getPgField(lv, "status", false)));

        dv.setRmStichwort(getField(lv, "RStichwort", false));
        dv.setRmStichwortSort(getField(lv, "RStichwort_sort", false));
        dv.setRmSemantik(getField(lv, "RSemantik", false));
        dv.setRmSubsemantik(getField(lv, "RSubsemantik", false));
        dv.setRmGrammatik(getField(lv, "RGrammatik", false));
        dv.setRmGenus(getField(lv, "RGenus", false));
        dv.setRmFlex(getField(lv, "RFlex", false));
        dv.setRmTags(getField(lv, "RTags", false));
        dv.setRmRedirect(getField(lv, "RRedirect", false));
        dv.setRmEtymologie(getField(lv, "REtymologie", false));
        dv.setRmPronunciation(getField(lv, "RPronunciation", false));

        dv.setDeStichwort(getField(lv, "DStichwort", false));
        dv.setDeStichwortSort(getField(lv, "DStichwort_sort", false));
        dv.setDeSemantik(getField(lv, "DSemantik", false));
        dv.setDeSubsemantik(getField(lv, "DSubsemantik", false));
        dv.setDeGrammatik(getField(lv, "DGrammatik", false));
        dv.setDeGenus(getField(lv, "DGenus", false));
        dv.setDeTags(getField(lv, "DTags", false));
        dv.setDeRedirect(getField(lv, "DRedirect", false));

        dv.setCategories(getField(lv, "categories", false));
        // dv.setExamples(getField(lv, "examples", true)); // TODO: import examples

        String inflectionType = getField(lv, "RInflectionType", false);
        if (inflectionType != null) {
            Inflection inflection = new Inflection();
            inflection.setInflectionType(inflectionTypeMapper(inflectionType));
            inflection.setInflectionSubtype(getField(lv, "RInflectionSubtype", true));
            inflection.setReviewLater(Objects.equals(getPgField(lv, "review_later", false), "true"));

            if (inflection.getInflectionType() == InflectionType.VERB) {
                inflection.setVerb(verbMapper(lv, language));
            } else if (inflection.getInflectionType() == InflectionType.NOUN) {
                inflection.setNoun(nounMapper(lv));
            } else if (inflection.getInflectionType() == InflectionType.ADJECTIVE) {
                inflection.setAdjective(adjectiveMapper(lv));
            } else if (inflection.getInflectionType() == InflectionType.PRONOUN) {
                inflection.setPronoun(pronounMapper(lv));
            } else if (inflection.getInflectionType() == InflectionType.OTHER) {
                inflection.setOther(otherMapper(lv));
            }

            dv.setInflection(inflection);
        } else {
            // logger.warn("    >> RInflectionType is null, not considering inflection");
        }

        String recId = getField(lv, "RecID", false);
        if (recId != null) {
            dv.getUnusedData().setRecId(recId);
        }

        String deStatus = getField(lv, "DStatus", false);
        if (deStatus != null) {
            dv.getUnusedData().setDeStatus(deStatus);
        }
        String rmStatus = getField(lv, "RStatus", false);
        if (rmStatus != null) {
            dv.getUnusedData().setRmStatus(rmStatus);
        }
        String deSemind = getField(lv, "DSemind", false);
        if (deSemind != null) {
            dv.getUnusedData().setDeSemind(deSemind);
        }
        String rmSemind = getField(lv, "RSemind", false);
        if (rmSemind != null) {
            dv.getUnusedData().setRmSemind(rmSemind);
        }

        lv.getLemmaValues().forEach((k, v) -> {
            if (v != null && !v.isEmpty()) {
                if (k.equals("_id") || k.equals("field_names")) {
                    return;
                }
                logger.warn("    >> Field {} is not mapped: {}", k, v);
            }
        });
        lv.getPgValues().forEach((k, v) -> {
            if (k.equals("internal_id") || k.equals("entry_id") || k.equals("automatic_change") || k.equals("verification")) {
                return;
            }
            if (v != null && !v.isEmpty()) {
                logger.warn("    >> PG Field {} is not mapped: {}", k, v);
            }
        });

        return dv;
    }

    private static Action getAction(String s) {
        if(s == null) return Action.UNKNOWN;
        LemmaVersion.Status status = LemmaVersion.Status.valueOf(s);

        return switch (status) {
            case DELETED, UNDEFINED -> Action.UNKNOWN;
            case NEW_ENTRY -> Action.CREATED_ENTRY;
            case NEW_MODIFICATION -> Action.CREATED_MODIFICATION;
        };
    }

    private static InflectionType inflectionTypeMapper(String inflectionType) {
        return switch (inflectionType) {
            case "V" -> InflectionType.VERB;
            case "NOUN" -> InflectionType.NOUN;
            case "ADJECTIVE" -> InflectionType.ADJECTIVE;
            case "PRONOUN" -> InflectionType.PRONOUN;
            case "OTHER" -> InflectionType.OTHER;
            default ->  {
                if (!inflectionType.isEmpty()) {
                    logger.warn("    >> Unexpected value: «{}»", inflectionType);
                }
                yield null;
            }
        };
    }

    private static Verb verbMapper(LemmaVersion lv, Language language) {
        Verb verb = new Verb();

        verb.setInfinitiv(getField(lv, "infinitiv", true));
        verb.setIrregular(Objects.equals(getField(lv, "RRegularInflection", true), "true"));
        verb.setComposedWith(getField(lv, "composedWith", false));

        Verb.PersonalVerb preschent = new Verb.PersonalVerb();
        preschent.setSing1(getField(lv, "preschentsing1", false));
        preschent.setSing2(getField(lv, "preschentsing2", false));
        preschent.setSing3(getField(lv, "preschentsing3", false));
        preschent.setPlural1(getField(lv, "preschentplural1", false));
        preschent.setPlural2(getField(lv, "preschentplural2", false));
        preschent.setPlural3(getField(lv, "preschentplural3", false));
        verb.setPreschent(preschent);

        Verb.PersonalVerb imperfect = new Verb.PersonalVerb();
        imperfect.setSing1(getField(lv, "imperfectsing1", false));
        imperfect.setSing2(getField(lv, "imperfectsing2", false));
        imperfect.setSing3(getField(lv, "imperfectsing3", false));
        imperfect.setPlural1(getField(lv, "imperfectplural1", false));
        imperfect.setPlural2(getField(lv, "imperfectplural2", false));
        imperfect.setPlural3(getField(lv, "imperfectplural3", false));
        verb.setImperfect(imperfect);

        Verb.PersonalVerb conjunctiv = new Verb.PersonalVerb();
        conjunctiv.setSing1(getField(lv, "conjunctivsing1", false));
        conjunctiv.setSing2(getField(lv, "conjunctivsing2", false));
        conjunctiv.setSing3(getField(lv, "conjunctivsing3", false));
        conjunctiv.setPlural1(getField(lv, "conjunctivplural1", false));
        conjunctiv.setPlural2(getField(lv, "conjunctivplural2", false));
        conjunctiv.setPlural3(getField(lv, "conjunctivplural3", false));
        verb.setConjunctiv(conjunctiv);

        if (language.getName().equals("sursilvan")|| language.getName().equals("sutsilvan") || language.getName().equals("puter") || language.getName().equals("vallader")) {
            Verb.PersonalVerb conjunctiv2 = new Verb.PersonalVerb();
            conjunctiv2.setSing1(getField(lv, "conjunctiv2sing1", false));
            conjunctiv2.setSing2(getField(lv, "conjunctiv2sing2", false));
            conjunctiv2.setSing3(getField(lv, "conjunctiv2sing3", false));
            conjunctiv2.setPlural1(getField(lv, "conjunctiv2plural1", false));
            conjunctiv2.setPlural2(getField(lv, "conjunctiv2plural2", false));
            conjunctiv2.setPlural3(getField(lv, "conjunctiv2plural3", false));
            verb.setConjunctiv2(conjunctiv2);
        }

        Verb.PersonalVerb cundizional = new Verb.PersonalVerb();
        cundizional.setSing1(getField(lv, "cundizionalsing1", false));
        cundizional.setSing2(getField(lv, "cundizionalsing2", false));
        cundizional.setSing3(getField(lv, "cundizionalsing3", false));
        cundizional.setPlural1(getField(lv, "cundizionalplural1", false));
        cundizional.setPlural2(getField(lv, "cundizionalplural2", false));
        cundizional.setPlural3(getField(lv, "cundizionalplural3", false));
        verb.setCundiziunal(cundizional);

        if (language.getName().equals("sursilvan")|| language.getName().equals("sutsilvan")) {
            Verb.PersonalVerb condiizionalindirect = new Verb.PersonalVerb();
            condiizionalindirect.setSing1(getField(lv, "cundizionalindirectsing1", false));
            condiizionalindirect.setSing2(getField(lv, "cundizionalindirectsing2", false));
            condiizionalindirect.setSing3(getField(lv, "cundizionalindirectsing3", false));
            condiizionalindirect.setPlural1(getField(lv, "cundizionalindirectplural1", false));
            condiizionalindirect.setPlural2(getField(lv, "cundizionalindirectplural2", false));
            condiizionalindirect.setPlural3(getField(lv, "cundizionalindirectplural3", false));
            verb.setCundiziunalIndirect(condiizionalindirect);
        }

        Verb.ParticipPerfect participperfect = new Verb.ParticipPerfect();
        participperfect.setMs(getField(lv, "participperfectms", false));
        participperfect.setFs(getField(lv, "participperfectfs", false));
        participperfect.setMp(getField(lv, "participperfectmp", false));
        participperfect.setFp(getField(lv, "participperfectfp", false));
        if (language.getName().equals("sursilvan")) {
            participperfect.setMsPredicativ(getField(lv, "participperfectmspredicativ", false));
        }
        verb.setParticipPerfect(participperfect);

        Verb.Imperativ imperativ = new Verb.Imperativ();
        imperativ.setSingular(getField(lv, "imperativ1", false));
        imperativ.setPlural(getField(lv, "imperativ2", false));
        if (language.getName().equals("puter") || language.getName().equals("vallader")) {
            imperativ.setForm3(getField(lv, "imperativ3", false));
            imperativ.setForm4(getField(lv, "imperativ4", false));
            imperativ.setForm5(getField(lv, "imperativ5", false));
            imperativ.setForm6(getField(lv, "imperativ6", false));
        }
        verb.setImperativ(imperativ);

        verb.setGerundium(getField(lv, "gerundium", false));

        Verb.PersonalVerb futur = new Verb.PersonalVerb();
        futur.setSing1(getField(lv, "futursing1", false));
        futur.setSing2(getField(lv, "futursing2", false));
        futur.setSing3(getField(lv, "futursing3", false));
        futur.setPlural1(getField(lv, "futurplural1", false));
        futur.setPlural2(getField(lv, "futurplural2", false));
        futur.setPlural3(getField(lv, "futurplural3", false));
        verb.setFutur(futur);

        if (language.getName().equals("puter")) {
            Verb.PersonalVerb futurdubitativ = new Verb.PersonalVerb();
            futurdubitativ.setSing1(getField(lv, "futurdubitativsing1", false));
            futurdubitativ.setSing2(getField(lv, "futurdubitativsing2", false));
            futurdubitativ.setSing3(getField(lv, "futurdubitativsing3", false));
            futurdubitativ.setPlural1(getField(lv, "futurdubitativplural1", false));
            futurdubitativ.setPlural2(getField(lv, "futurdubitativplural2", false));
            futurdubitativ.setPlural3(getField(lv, "futurdubitativplural3", false));
            verb.setFuturDubitativ(futurdubitativ);
        }

        // enclitic

        if (language.getName().equals("puter")|| language.getName().equals("surmiran") || language.getName().equals("sutsilvan") || language.getName().equals("vallader")) {
            Verb.PersonalVerbEnclitic preschentEnclitic = new Verb.PersonalVerbEnclitic();
            preschentEnclitic.setSing1(getField(lv, "preschentsing1enclitic", false));
            preschentEnclitic.setSing2(getField(lv, "preschentsing2enclitic", false));
            preschentEnclitic.setSing3m(getField(lv, "preschentsing3encliticm", false));
            preschentEnclitic.setSing3f(getField(lv, "preschentsing3encliticf", false));
            preschentEnclitic.setPlural1(getField(lv, "preschentplural1enclitic", false));
            preschentEnclitic.setPlural2(getField(lv, "preschentplural2enclitic", false));
            preschentEnclitic.setPlural3(getField(lv, "preschentplural3enclitic", false));
            verb.setPreschentEnclitic(preschentEnclitic);

            Verb.PersonalVerbEnclitic imperfectEnclitic = new Verb.PersonalVerbEnclitic();
            imperfectEnclitic.setSing1(getField(lv, "imperfectsing1enclitic", false));
            imperfectEnclitic.setSing2(getField(lv, "imperfectsing2enclitic", false));
            imperfectEnclitic.setSing3m(getField(lv, "imperfectsing3encliticm", false));
            imperfectEnclitic.setSing3f(getField(lv, "imperfectsing3encliticf", false));
            imperfectEnclitic.setPlural1(getField(lv, "imperfectplural1enclitic", false));
            imperfectEnclitic.setPlural2(getField(lv, "imperfectplural2enclitic", false));
            imperfectEnclitic.setPlural3(getField(lv, "imperfectplural3enclitic", false));
            verb.setImperfectEnclitic(imperfectEnclitic);

            Verb.PersonalVerbEnclitic cundizionalEnclitic = new Verb.PersonalVerbEnclitic();
            cundizionalEnclitic.setSing1(getField(lv, "cundizionalsing1enclitic", false));
            cundizionalEnclitic.setSing2(getField(lv, "cundizionalsing2enclitic", false));
            cundizionalEnclitic.setSing3m(getField(lv, "cundizionalsing3encliticm", false));
            cundizionalEnclitic.setSing3f(getField(lv, "cundizionalsing3encliticf", false));
            cundizionalEnclitic.setPlural1(getField(lv, "cundizionalplural1enclitic", false));
            cundizionalEnclitic.setPlural2(getField(lv, "cundizionalplural2enclitic", false));
            cundizionalEnclitic.setPlural3(getField(lv, "cundizionalplural3enclitic", false));
            verb.setCundizionalEnclitic(cundizionalEnclitic);
        }

        if (language.getName().equals("puter")|| language.getName().equals("surmiran") || language.getName().equals("vallader")) {
            Verb.PersonalVerbEnclitic futurEnclitic = new Verb.PersonalVerbEnclitic();
            futurEnclitic.setSing1(getField(lv, "futursing1enclitic", false));
            futurEnclitic.setSing2(getField(lv, "futursing2enclitic", false));
            futurEnclitic.setSing3m(getField(lv, "futursing3encliticm", false));
            futurEnclitic.setSing3f(getField(lv, "futursing3encliticf", false));
            futurEnclitic.setPlural1(getField(lv, "futurplural1enclitic", false));
            futurEnclitic.setPlural2(getField(lv, "futurplural2enclitic", false));
            futurEnclitic.setPlural3(getField(lv, "futurplural3enclitic", false));
            verb.setFuturEnclitic(futurEnclitic);
        }

        if (language.getName().equals("puter")) {
            Verb.PersonalVerbEnclitic futurdubitativEnclitic = new Verb.PersonalVerbEnclitic();
            futurdubitativEnclitic.setSing1(getField(lv, "futurdubitativsing1enclitic", false));
            futurdubitativEnclitic.setSing2(getField(lv, "futurdubitativsing2enclitic", false));
            futurdubitativEnclitic.setSing3m(getField(lv, "futurdubitativsing3encliticm", false));
            futurdubitativEnclitic.setSing3f(getField(lv, "futurdubitativsing3encliticf", false));
            futurdubitativEnclitic.setPlural1(getField(lv, "futurdubitativplural1enclitic", false));
            futurdubitativEnclitic.setPlural2(getField(lv, "futurdubitativplural2enclitic", false));
            futurdubitativEnclitic.setPlural3(getField(lv, "futurdubitativplural3enclitic", false));
            verb.setFuturDubitativEnclitic(futurdubitativEnclitic);
        }

        return verb;
    }

    private static Noun nounMapper(LemmaVersion lv) {
        Noun noun = new Noun();

        noun.setBaseForm(getField(lv, "baseForm", true));
        noun.setIrregular(Objects.equals(getField(lv, "RRegularInflection", true), "true"));
        noun.setMSingular(getField(lv, "mSingular", false));
        noun.setFSingular(getField(lv, "fSingular", false));
        noun.setMPlural(getField(lv, "mPlural", false));
        noun.setFPlural(getField(lv, "fPlural", false));
        noun.setPluralCollectiv(getField(lv, "pluralCollectiv", false));

        return noun;
    }

    private static Adjective adjectiveMapper(LemmaVersion lv) {
        Adjective adjective = new Adjective();

        adjective.setBaseForm(getField(lv, "baseForm", true));
        adjective.setIrregular(Objects.equals(getField(lv, "RRegularInflection", true), "true"));
        adjective.setMSingular(getField(lv, "mSingular", false));
        adjective.setFSingular(getField(lv, "fSingular", false));
        adjective.setMPlural(getField(lv, "mPlural", false));
        adjective.setFPlural(getField(lv, "fPlural", false));
        adjective.setAdverbialForm(getField(lv, "adverbialForm", false));

        return adjective;
    }

    private static Pronoun pronounMapper(LemmaVersion lv) {
        Pronoun pronoun = new Pronoun();

        pronoun.setBaseForm(getField(lv, "baseForm", true));
        pronoun.setMSingular(getField(lv, "mSingular", false));
        pronoun.setFSingular(getField(lv, "fSingular", false));
        pronoun.setMPlural(getField(lv, "mPlural", false));
        pronoun.setFPlural(getField(lv, "fPlural", false));

        return pronoun;
    }

    private static Other otherMapper(LemmaVersion lv) {
        Other other = new Other();

        other.setBaseForm(getField(lv, "baseForm", true));
        other.setOtherForm1(getField(lv, "otherForm1", false));
        other.setOtherForm2(getField(lv, "otherForm2", false));
        other.setOtherForm3(getField(lv, "otherForm3", false));
        other.setOtherForm4(getField(lv, "otherForm4", false));

        return other;
    }

    private static EditorRole getCreatorRole(String role) {
        if(role == null) return EditorRole.GUEST;
        try {
            return EditorRole.valueOf(role);
        } catch (Exception e) {
            if (role.equals("ADMIN_5")) { return EditorRole.ADMIN; }
            if (role.equals("TRUSTED_IN_4")) { return EditorRole.EDITOR; }
            if (role.equals("TRUSTED_EX_3")) { return EditorRole.GUEST; }
            if (role.equals("GUEST_1")) { return EditorRole.GUEST; }
            return EditorRole.NONE;
        }
    }

    private static String getField(LemmaVersion lv, String field, boolean required) {
        String value = lv.getLemmaValues().get(field);
        if (value == null || value.isEmpty()) {
            if (required) {
                logger.warn("    >> Field {} is null or empty", field);
            }            return null;
        }
        lv.getLemmaValues().remove(field);
        return value;

    }

    private static String getPgField(LemmaVersion lv, String field, boolean required) {
        String value = lv.getPgValues().get(field);
        if (value == null || value.isEmpty()) {
            if (required) {
                logger.warn("    >> PG Field {} is null or empty", field);
            }
            return null;
        }
        lv.getPgValues().remove(field);
        return value;
    }
}
