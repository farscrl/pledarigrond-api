package ch.pledarigrond.database.dictionary.mappers;

import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.database.dictionary.entities.Entry;
import ch.pledarigrond.database.dictionary.entities.EntryVersion;
import ch.pledarigrond.database.dictionary.entities.Example;
import ch.pledarigrond.database.dictionary.entities.inflection.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.*;

public class LexEntryToEntryMapper {

    private static final Logger logger = LoggerFactory.getLogger(LexEntryToEntryMapper.class);

    public static Entry map(LexEntry lexEntry, Language language) {
        // logger.warn(" ");
        // logger.warn(" ");
        // logger.warn("***§***********§***********§***********§***********§***********§***********§");
        // logger.warn("LexEntryToEntryMapper map entry. ID: {}", lexEntry.getId());

        Entry entry = new Entry();
        entry.setEntryId(lexEntry.getId());

        Map<Integer, String> internalToNewIdMap = new HashMap<>();
        ArrayList<EntryVersion> versions = new ArrayList<>();
        for (LemmaVersion lv: lexEntry.getVersionHistory()) {
            Integer internalId = lv.getInternalId();
            while (internalToNewIdMap.containsKey(internalId)) {
                // logger.warn("Duplicate internal id: " + lexEntry.getCurrent().getEntryValue("RStichwort") + " / ID: " + lexEntry.getId());
                if (lexEntry.getCurrentId() == internalId) {
                    logger.error("Duplicate internal id: " + lexEntry.getCurrent().getEntryValue("RStichwort") + " / " + lexEntry.getCurrent().getEntryValue("DStichwort") + ": " + lexEntry.getId());
                    logger.error("    >> INTERNAL ID IS CURRENT");
                }
                // logger.warn("    >> Version history contains duplicate entry: {}", lv.getInternalId());
                // logger.warn("    >> Setting to: {}", internalId - 1);
                // lv.setInternalId(internalId - 1);
                internalId = internalId - 1;
            }

            EntryVersion ev = map(lv, language);
            ev.setEntryId(entry.getEntryId());
            versions.add(ev);
            internalToNewIdMap.put(internalId, ev.getVersionId());
        }
        Collections.reverse(versions);
        entry.setVersions(versions);

        ArrayList<EntryVersion> suggestions = new ArrayList<>();
        for (LemmaVersion lv : lexEntry.getUnapprovedVersions()) {
            String transformedId = internalToNewIdMap.get(lv.getInternalId());
            versions.stream()
                    .filter(version -> version.getVersionId().equals(transformedId))
                    .findFirst().ifPresent(suggestions::add);
        }
        entry.setSuggestions(suggestions);

        LemmaVersion lv = lexEntry.getCurrent();
        if (lv.getVerification() == LemmaVersion.Verification.ACCEPTED) {
            String transformedId = internalToNewIdMap.get(lexEntry.getCurrentId());
            versions.stream()
                    .filter(version -> version.getVersionId().equals(transformedId))
                    .findFirst().ifPresent(entry::setCurrent);
        } else {
            logger.warn("    >> Current version is not accepted, cannot set current entry version. ID {}: {} / {}", lexEntry.getId(), lv.getLemmaValues().get("RStichwort"), lv.getLemmaValues().get("DStichwort"));
        }

        return entry;
    }

    public static EntryVersion map(LemmaVersion lv, Language language) {
        // logger.warn("    > LexEntryToEntryMapper map entry version. ID {}: {} / {}", lv.getInternalId(), lv.getLemmaValues().get("RStichwort"), lv.getLemmaValues().get("DStichwort"));
        Set<String> mappedFields = new HashSet<>();

        EntryVersion dv = new EntryVersion();

        String timestamp = getPgField(lv, "timestamp", false);
        dv.setTimestamp(Instant.ofEpochMilli(timestamp == null ? 0L : Long.parseLong(timestamp)));

        String contactComment = getField(lv, "contact_comment", false, mappedFields);
        String userComment = getField(lv, "user_comment", false, mappedFields);
        String comment = Objects.toString(contactComment, "") + Objects.toString(userComment, "");
        dv.setUserComment(comment);

        String contactEmail = getField(lv, "contact_email", false, mappedFields);
        String userEmail = getField(lv, "user_email", false, mappedFields);
        String email = Objects.toString(contactEmail, "") + Objects.toString(userEmail, "");
        dv.setUserEmail(email);

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
        dv.setAction(getAction(getPgField(lv, "status", false), getPgField(lv, "verification", false)));

        dv.setRmStichwort(getField(lv, "RStichwort", false, mappedFields));
        dv.setRmStichwortSort(getField(lv, "RStichwort_sort", false, mappedFields));
        dv.setRmSemantik(getField(lv, "RSemantik", false, mappedFields));
        dv.setRmSubsemantik(getField(lv, "RSubsemantik", false, mappedFields));
        dv.setRmGrammatik(getField(lv, "RGrammatik", false, mappedFields));
        dv.setRmGenus(getField(lv, "RGenus", false, mappedFields));
        dv.setRmFlex(getField(lv, "RFlex", false, mappedFields));
        dv.setRmTags(getField(lv, "RTags", false, mappedFields));
        dv.setRmRedirect(getField(lv, "RRedirect", false, mappedFields));
        dv.setRmEtymologie(getField(lv, "REtymologie", false, mappedFields));
        dv.setRmPronunciation(getField(lv, "RPronunciation", false, mappedFields));
        dv.setRmPhonetics(getField(lv, "RPhonetics", false, mappedFields));
        dv.setRmSynonym(getField(lv, "RSynonym", false, mappedFields));

        dv.setDeStichwort(getField(lv, "DStichwort", false, mappedFields));
        dv.setDeStichwortSort(getField(lv, "DStichwort_sort", false, mappedFields));
        dv.setDeSemantik(getField(lv, "DSemantik", false, mappedFields));
        dv.setDeSubsemantik(getField(lv, "DSubsemantik", false, mappedFields));
        dv.setDeGrammatik(getField(lv, "DGrammatik", false, mappedFields));
        dv.setDeGenus(getField(lv, "DGenus", false, mappedFields));
        dv.setDeFlex(getField(lv, "DFlex", false, mappedFields));
        dv.setDeTags(getField(lv, "DTags", false, mappedFields));
        dv.setDeRedirect(getField(lv, "DRedirect", false, mappedFields));

        dv.setCategories(getField(lv, "categories", false, mappedFields));
        dv.setExamples(splitExamples(getField(lv, "examples", false, mappedFields)));

        String inflectionType = getField(lv, "RInflectionType", false, mappedFields);
        if (inflectionType != null) {
            Inflection inflection = new Inflection();
            inflection.setInflectionType(inflectionTypeMapper(inflectionType));
            inflection.setReviewLater(Objects.equals(getPgField(lv, "review_later", false), "true"));

            if (inflection.getInflectionType() == InflectionType.VERB) {
                inflection.setVerb(verbMapper(lv, language, mappedFields));
            } else if (inflection.getInflectionType() == InflectionType.NOUN) {
                inflection.setNoun(nounMapper(lv, mappedFields));
            } else if (inflection.getInflectionType() == InflectionType.ADJECTIVE) {
                inflection.setAdjective(adjectiveMapper(lv, mappedFields));
            } else if (inflection.getInflectionType() == InflectionType.PRONOUN) {
                inflection.setPronoun(pronounMapper(lv, mappedFields));
            } else if (inflection.getInflectionType() == InflectionType.OTHER) {
                inflection.setOther(otherMapper(lv, mappedFields));
            }

            dv.setInflection(inflection);
        } else {
            // logger.warn("    >> RInflectionType is null, not considering inflection");
        }

        String recId = getField(lv, "RecID", false, mappedFields);
        if (recId != null) {
            dv.getUnusedData().setRecId(recId);
        }

        String deStatus = getField(lv, "DStatus", false, mappedFields);
        if (deStatus != null) {
            dv.getUnusedData().setDeStatus(deStatus);
        }
        String rmStatus = getField(lv, "RStatus", false, mappedFields);
        if (rmStatus != null) {
            dv.getUnusedData().setRmStatus(rmStatus);
        }
        String deSemind = getField(lv, "DSemind", false, mappedFields);
        if (deSemind != null) {
            dv.getUnusedData().setDeSemind(deSemind);
        }
        String rmSemind = getField(lv, "RSemind", false, mappedFields);
        if (rmSemind != null) {
            dv.getUnusedData().setRmSemind(rmSemind);
        }
        String importedId = getField(lv, "ImportedId", false, mappedFields);
        if (importedId != null) {
            dv.getUnusedData().setImportedId(Integer.parseInt(importedId));
        }
        String rmStichwortSortAlpha = getField(lv, "RStichwort_sort_alpha", false, mappedFields);
        if (rmStichwortSortAlpha != null) {
            dv.getUnusedData().setRmStichwortSortAlpha(rmStichwortSortAlpha);
        }
        String deStichwortSortAlpha = getField(lv, "DStichwort_sort_alpha", false, mappedFields);
        if (deStichwortSortAlpha != null) {
            dv.getUnusedData().setDeStichwortSortAlpha(deStichwortSortAlpha);
        }
        String verbId = getField(lv, "verbID", false, mappedFields);
        if (verbId != null) {
            dv.getUnusedData().setVerbId(verbId);
        }
        String rmEnum = getField(lv, "REnum", false, mappedFields);
        if (rmEnum != null) {
            dv.getUnusedData().setRmEnum(rmEnum);
        }
        String deEnum = getField(lv, "DEnum", false, mappedFields);
        if (deEnum != null) {
            dv.getUnusedData().setDeEnum(deEnum);
        }
        String deCategories = getField(lv, "Dcategories", false, mappedFields);
        if (deCategories != null) {
            dv.getUnusedData().setDeCategories(deCategories);
        }

        lv.getLemmaValues().forEach((k, v) -> {
            if (v != null && !v.isEmpty() && !mappedFields.contains(k)) {
                if (k.equals("_id") || k.equals("field_names")) {
                    return;
                }
                logger.warn("    >> Field {} is not mapped: {}. [{}/{}: {}]", k, v, dv.getRmStichwort(), dv.getDeStichwort(), lv.getLexEntryId());
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

    private static Action getAction(String statusString, String verificationString) {
        if(statusString == null) return Action.UNKNOWN;
        LemmaVersion.Status status = LemmaVersion.Status.valueOf(statusString);

        LemmaVersion.Verification verification = LemmaVersion.Verification.UNVERIFIED;
        if (verificationString != null) {
            verification = LemmaVersion.Verification.valueOf(verificationString);
        }

        return switch (status) {
            case NEW_ENTRY -> Action.CREATED_ENTRY;
            case NEW_MODIFICATION -> {
                if (verification == LemmaVersion.Verification.UNVERIFIED) {
                    yield Action.SUGGESTED_MODIFICATION;
                } else if (verification == LemmaVersion.Verification.OUTDATED) {
                    yield Action.REFUSED_MODIFICATION;
                }
                yield Action.ACCEPTED_MODIFICATION;
            }
            case DELETED -> Action.REFUSED_MODIFICATION;
            case UNDEFINED -> Action.UNKNOWN;
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

    private static Verb verbMapper(LemmaVersion lv, Language language, Set<String> mappedFields) {
        Verb verb = new Verb();

        verb.setInfinitiv(getField(lv, "infinitiv", true, mappedFields));
        boolean isIrregular = Objects.equals(getField(lv, "RRegularInflection", false, mappedFields), "false");
        verb.setIrregular(isIrregular);
        verb.setInflectionSubtype(getField(lv, "RInflectionSubtype", true, mappedFields));
        verb.setComposedWith(getField(lv, "composedWith", false, mappedFields));

        Verb.PersonalVerb preschent = new Verb.PersonalVerb();
        preschent.setSing1(getField(lv, "preschentsing1", false, mappedFields));
        preschent.setSing2(getField(lv, "preschentsing2", false, mappedFields));
        preschent.setSing3(getField(lv, "preschentsing3", false, mappedFields));
        preschent.setPlural1(getField(lv, "preschentplural1", false, mappedFields));
        preschent.setPlural2(getField(lv, "preschentplural2", false, mappedFields));
        preschent.setPlural3(getField(lv, "preschentplural3", false, mappedFields));
        verb.setPreschent(preschent);

        Verb.PersonalVerb imperfect = new Verb.PersonalVerb();
        imperfect.setSing1(getField(lv, "imperfectsing1", false, mappedFields));
        imperfect.setSing2(getField(lv, "imperfectsing2", false, mappedFields));
        imperfect.setSing3(getField(lv, "imperfectsing3", false, mappedFields));
        imperfect.setPlural1(getField(lv, "imperfectplural1", false, mappedFields));
        imperfect.setPlural2(getField(lv, "imperfectplural2", false, mappedFields));
        imperfect.setPlural3(getField(lv, "imperfectplural3", false, mappedFields));
        verb.setImperfect(imperfect);

        Verb.PersonalVerb conjunctiv = new Verb.PersonalVerb();
        conjunctiv.setSing1(getField(lv, "conjunctivsing1", false, mappedFields));
        conjunctiv.setSing2(getField(lv, "conjunctivsing2", false, mappedFields));
        conjunctiv.setSing3(getField(lv, "conjunctivsing3", false, mappedFields));
        conjunctiv.setPlural1(getField(lv, "conjunctivplural1", false, mappedFields));
        conjunctiv.setPlural2(getField(lv, "conjunctivplural2", false, mappedFields));
        conjunctiv.setPlural3(getField(lv, "conjunctivplural3", false, mappedFields));
        verb.setConjunctiv(conjunctiv);

        if (language.getName().equals("sursilvan")|| language.getName().equals("sutsilvan") || language.getName().equals("puter") || language.getName().equals("vallader")) {
            Verb.PersonalVerb conjunctiv2 = new Verb.PersonalVerb();
            conjunctiv2.setSing1(getField(lv, "conjunctiv2sing1", false, mappedFields));
            conjunctiv2.setSing2(getField(lv, "conjunctiv2sing2", false, mappedFields));
            conjunctiv2.setSing3(getField(lv, "conjunctiv2sing3", false, mappedFields));
            conjunctiv2.setPlural1(getField(lv, "conjunctiv2plural1", false, mappedFields));
            conjunctiv2.setPlural2(getField(lv, "conjunctiv2plural2", false, mappedFields));
            conjunctiv2.setPlural3(getField(lv, "conjunctiv2plural3", false, mappedFields));
            verb.setConjunctivImperfect(conjunctiv2);
        }

        Verb.PersonalVerb cundizional = new Verb.PersonalVerb();
        cundizional.setSing1(getField(lv, "cundizionalsing1", false, mappedFields));
        cundizional.setSing2(getField(lv, "cundizionalsing2", false, mappedFields));
        cundizional.setSing3(getField(lv, "cundizionalsing3", false, mappedFields));
        cundizional.setPlural1(getField(lv, "cundizionalplural1", false, mappedFields));
        cundizional.setPlural2(getField(lv, "cundizionalplural2", false, mappedFields));
        cundizional.setPlural3(getField(lv, "cundizionalplural3", false, mappedFields));
        verb.setCundiziunal(cundizional);

        if (language.getName().equals("sursilvan")|| language.getName().equals("sutsilvan")) {
            Verb.PersonalVerb condiizionalindirect = new Verb.PersonalVerb();
            condiizionalindirect.setSing1(getField(lv, "cundizionalindirectsing1", false, mappedFields));
            condiizionalindirect.setSing2(getField(lv, "cundizionalindirectsing2", false, mappedFields));
            condiizionalindirect.setSing3(getField(lv, "cundizionalindirectsing3", false, mappedFields));
            condiizionalindirect.setPlural1(getField(lv, "cundizionalindirectplural1", false, mappedFields));
            condiizionalindirect.setPlural2(getField(lv, "cundizionalindirectplural2", false, mappedFields));
            condiizionalindirect.setPlural3(getField(lv, "cundizionalindirectplural3", false, mappedFields));
            verb.setCundiziunalIndirect(condiizionalindirect);
        }

        Verb.ParticipPerfect participperfect = new Verb.ParticipPerfect();
        participperfect.setMs(getField(lv, "participperfectms", false, mappedFields));
        participperfect.setFs(getField(lv, "participperfectfs", false, mappedFields));
        participperfect.setMp(getField(lv, "participperfectmp", false, mappedFields));
        participperfect.setFp(getField(lv, "participperfectfp", false, mappedFields));
        if (language.getName().equals("sursilvan")) {
            participperfect.setMsPredicativ(getField(lv, "participperfectmspredicativ", false, mappedFields));
        }
        verb.setParticipPerfect(participperfect);

        Verb.Imperativ imperativ = new Verb.Imperativ();
        imperativ.setSingular(getField(lv, "imperativ1", false, mappedFields));
        imperativ.setPlural(getField(lv, "imperativ2", false, mappedFields));
        if (language.getName().equals("puter") || language.getName().equals("vallader")) {
            imperativ.setForm3(getField(lv, "imperativ3", false, mappedFields));
            imperativ.setForm4(getField(lv, "imperativ4", false, mappedFields));
            imperativ.setForm5(getField(lv, "imperativ5", false, mappedFields));
            imperativ.setForm6(getField(lv, "imperativ6", false, mappedFields));
        }
        verb.setImperativ(imperativ);

        verb.setGerundium(getField(lv, "gerundium", false, mappedFields));

        Verb.PersonalVerb futur = new Verb.PersonalVerb();
        futur.setSing1(getField(lv, "futursing1", false, mappedFields));
        futur.setSing2(getField(lv, "futursing2", false, mappedFields));
        futur.setSing3(getField(lv, "futursing3", false, mappedFields));
        futur.setPlural1(getField(lv, "futurplural1", false, mappedFields));
        futur.setPlural2(getField(lv, "futurplural2", false, mappedFields));
        futur.setPlural3(getField(lv, "futurplural3", false, mappedFields));
        verb.setFutur(futur);

        if (language.getName().equals("puter")) {
            Verb.PersonalVerb futurdubitativ = new Verb.PersonalVerb();
            futurdubitativ.setSing1(getField(lv, "futurdubitativsing1", false, mappedFields));
            futurdubitativ.setSing2(getField(lv, "futurdubitativsing2", false, mappedFields));
            futurdubitativ.setSing3(getField(lv, "futurdubitativsing3", false, mappedFields));
            futurdubitativ.setPlural1(getField(lv, "futurdubitativplural1", false, mappedFields));
            futurdubitativ.setPlural2(getField(lv, "futurdubitativplural2", false, mappedFields));
            futurdubitativ.setPlural3(getField(lv, "futurdubitativplural3", false, mappedFields));
            verb.setFuturDubitativ(futurdubitativ);
        }

        // enclitic

        if (language.getName().equals("puter")|| language.getName().equals("surmiran") || language.getName().equals("sutsilvan") || language.getName().equals("vallader")) {
            Verb.PersonalVerbEnclitic preschentEnclitic = new Verb.PersonalVerbEnclitic();
            preschentEnclitic.setSing1(getField(lv, "preschentsing1enclitic", false, mappedFields));
            preschentEnclitic.setSing2(getField(lv, "preschentsing2enclitic", false, mappedFields));
            preschentEnclitic.setSing3m(getField(lv, "preschentsing3encliticm", false, mappedFields));
            preschentEnclitic.setSing3f(getField(lv, "preschentsing3encliticf", false, mappedFields));
            preschentEnclitic.setPlural1(getField(lv, "preschentplural1enclitic", false, mappedFields));
            preschentEnclitic.setPlural2(getField(lv, "preschentplural2enclitic", false, mappedFields));
            preschentEnclitic.setPlural3(getField(lv, "preschentplural3enclitic", false, mappedFields));
            verb.setPreschentEnclitic(preschentEnclitic);

            Verb.PersonalVerbEnclitic imperfectEnclitic = new Verb.PersonalVerbEnclitic();
            imperfectEnclitic.setSing1(getField(lv, "imperfectsing1enclitic", false, mappedFields));
            imperfectEnclitic.setSing2(getField(lv, "imperfectsing2enclitic", false, mappedFields));
            imperfectEnclitic.setSing3m(getField(lv, "imperfectsing3encliticm", false, mappedFields));
            imperfectEnclitic.setSing3f(getField(lv, "imperfectsing3encliticf", false, mappedFields));
            imperfectEnclitic.setPlural1(getField(lv, "imperfectplural1enclitic", false, mappedFields));
            imperfectEnclitic.setPlural2(getField(lv, "imperfectplural2enclitic", false, mappedFields));
            imperfectEnclitic.setPlural3(getField(lv, "imperfectplural3enclitic", false, mappedFields));
            verb.setImperfectEnclitic(imperfectEnclitic);

            Verb.PersonalVerbEnclitic cundizionalEnclitic = new Verb.PersonalVerbEnclitic();
            cundizionalEnclitic.setSing1(getField(lv, "cundizionalsing1enclitic", false, mappedFields));
            cundizionalEnclitic.setSing2(getField(lv, "cundizionalsing2enclitic", false, mappedFields));
            cundizionalEnclitic.setSing3m(getField(lv, "cundizionalsing3encliticm", false, mappedFields));
            cundizionalEnclitic.setSing3f(getField(lv, "cundizionalsing3encliticf", false, mappedFields));
            cundizionalEnclitic.setPlural1(getField(lv, "cundizionalplural1enclitic", false, mappedFields));
            cundizionalEnclitic.setPlural2(getField(lv, "cundizionalplural2enclitic", false, mappedFields));
            cundizionalEnclitic.setPlural3(getField(lv, "cundizionalplural3enclitic", false, mappedFields));
            verb.setCundiziunalEnclitic(cundizionalEnclitic);
        }

        if (language.getName().equals("puter")|| language.getName().equals("surmiran") || language.getName().equals("vallader")) {
            Verb.PersonalVerbEnclitic futurEnclitic = new Verb.PersonalVerbEnclitic();
            futurEnclitic.setSing1(getField(lv, "futursing1enclitic", false, mappedFields));
            futurEnclitic.setSing2(getField(lv, "futursing2enclitic", false, mappedFields));
            futurEnclitic.setSing3m(getField(lv, "futursing3encliticm", false, mappedFields));
            futurEnclitic.setSing3f(getField(lv, "futursing3encliticf", false, mappedFields));
            futurEnclitic.setPlural1(getField(lv, "futurplural1enclitic", false, mappedFields));
            futurEnclitic.setPlural2(getField(lv, "futurplural2enclitic", false, mappedFields));
            futurEnclitic.setPlural3(getField(lv, "futurplural3enclitic", false, mappedFields));
            verb.setFuturEnclitic(futurEnclitic);
        }

        if (language.getName().equals("puter")) {
            Verb.PersonalVerbEnclitic futurdubitativEnclitic = new Verb.PersonalVerbEnclitic();
            futurdubitativEnclitic.setSing1(getField(lv, "futurdubitativsing1enclitic", false, mappedFields));
            futurdubitativEnclitic.setSing2(getField(lv, "futurdubitativsing2enclitic", false, mappedFields));
            futurdubitativEnclitic.setSing3m(getField(lv, "futurdubitativsing3encliticm", false, mappedFields));
            futurdubitativEnclitic.setSing3f(getField(lv, "futurdubitativsing3encliticf", false, mappedFields));
            futurdubitativEnclitic.setPlural1(getField(lv, "futurdubitativplural1enclitic", false, mappedFields));
            futurdubitativEnclitic.setPlural2(getField(lv, "futurdubitativplural2enclitic", false, mappedFields));
            futurdubitativEnclitic.setPlural3(getField(lv, "futurdubitativplural3enclitic", false, mappedFields));
            verb.setFuturDubitativEnclitic(futurdubitativEnclitic);
        }

        return verb;
    }

    private static Noun nounMapper(LemmaVersion lv, Set<String> mappedFields) {
        Noun noun = new Noun();

        noun.setBaseForm(getField(lv, "baseForm", true, mappedFields));
        boolean isIrregular = Objects.equals(getField(lv, "RRegularInflection", false, mappedFields), "false");
        noun.setIrregular(isIrregular);
        noun.setInflectionSubtype(getField(lv, "RInflectionSubtype", true, mappedFields));
        noun.setMSingular(getField(lv, "mSingular", false, mappedFields));
        noun.setFSingular(getField(lv, "fSingular", false, mappedFields));
        noun.setMPlural(getField(lv, "mPlural", false, mappedFields));
        noun.setFPlural(getField(lv, "fPlural", false, mappedFields));
        noun.setPluralCollectiv(getField(lv, "pluralCollectiv", false, mappedFields));

        return noun;
    }

    private static Adjective adjectiveMapper(LemmaVersion lv, Set<String> mappedFields) {
        Adjective adjective = new Adjective();

        adjective.setBaseForm(getField(lv, "baseForm", true, mappedFields));
        boolean isIrregular = Objects.equals(getField(lv, "RRegularInflection", false, mappedFields), "false");
        adjective.setIrregular(isIrregular);
        adjective.setInflectionSubtype(getField(lv, "RInflectionSubtype", true, mappedFields));
        adjective.setMSingular(getField(lv, "mSingular", false, mappedFields));
        adjective.setFSingular(getField(lv, "fSingular", false, mappedFields));
        adjective.setMPlural(getField(lv, "mPlural", false, mappedFields));
        adjective.setFPlural(getField(lv, "fPlural", false, mappedFields));
        adjective.setAdverbialForm(getField(lv, "adverbialForm", false, mappedFields));

        return adjective;
    }

    private static Pronoun pronounMapper(LemmaVersion lv, Set<String> mappedFields) {
        Pronoun pronoun = new Pronoun();

        pronoun.setBaseForm(getField(lv, "baseForm", true, mappedFields));
        pronoun.setMSingular(getField(lv, "mSingular", false, mappedFields));
        pronoun.setFSingular(getField(lv, "fSingular", false, mappedFields));
        pronoun.setMPlural(getField(lv, "mPlural", false, mappedFields));
        pronoun.setFPlural(getField(lv, "fPlural", false, mappedFields));

        return pronoun;
    }

    private static Other otherMapper(LemmaVersion lv, Set<String> mappedFields) {
        Other other = new Other();

        other.setBaseForm(getField(lv, "baseForm", true, mappedFields));
        other.setOtherForm1(getField(lv, "otherForm1", false, mappedFields));
        other.setOtherForm2(getField(lv, "otherForm2", false, mappedFields));
        other.setOtherForm3(getField(lv, "otherForm3", false, mappedFields));
        other.setOtherForm4(getField(lv, "otherForm4", false, mappedFields));

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

    private static String getField(LemmaVersion lv, String field, boolean required, Set<String> mappedFields) {
        String value = lv.getLemmaValues().get(field);
        if (value == null || value.isEmpty()) {
            if (required) {
                logger.warn("    >> Field {} is null or empty", field);
            }            return null;
        }
        mappedFields.add(field);
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
        // lv.getPgValues().remove(field);
        return value;
    }

    private static List<Example> splitExamples(String examples) {
        if (examples == null || examples.isEmpty()) {
            return null;
        }

        String[] lines = examples.split("\\r?\\n");
        if (lines.length == 0) {
            logger.warn("    >> Examples is empty");
            return null;
        }

        List<Example> examplesList = new ArrayList<>();

        for (String line : lines) {
            Example example = splitExampleString(line);
            if (example != null) {
                examplesList.add(example);
            }
        }

        return examplesList;
    }

    private static Example splitExampleString(String str) {
        Example example = new Example();

        // Split on the literal "###"
        String[] parts = str.split(java.util.regex.Pattern.quote("###"));
        if (parts.length == 0) {
            logger.warn("    >> Example is empty");
            return null;
        }
        String rm = parts[0];
        String de = parts.length > 1
                ? String.join("###", Arrays.copyOfRange(parts, 1, parts.length))
                : "";

        example.setRm(rm);
        example.setDe(de);
        return example;
    }
}
