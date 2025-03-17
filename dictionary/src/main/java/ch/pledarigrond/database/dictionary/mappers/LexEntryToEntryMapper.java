package ch.pledarigrond.database.dictionary.mappers;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.dictionary.VersionStatus;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.database.dictionary.entities.Entry;
import ch.pledarigrond.database.dictionary.entities.EntryVersion;
import ch.pledarigrond.database.dictionary.entities.inflection.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

public class LexEntryToEntryMapper {

    private static final Logger logger = LoggerFactory.getLogger(LexEntryToEntryMapper.class);

    public static Entry map(LexEntry lexEntry, Language language) {
        Entry entry = new Entry();
        entry.setEntryId(lexEntry.getId());

        ArrayList<EntryVersion> suggestions = new ArrayList<>();
        int[] suggestionIds = new int[lexEntry.getUnapprovedVersions().size()];
        for (LemmaVersion lv : lexEntry.getUnapprovedVersions()) {
            suggestions.add(map(lv, language));
            suggestionIds[suggestions.size() - 1] = lv.getInternalId();
        }

        ArrayList<EntryVersion> versions = new ArrayList<>();
        for (LemmaVersion lv: lexEntry.getVersionHistory()) {
            boolean contains = IntStream.of(suggestionIds).anyMatch(x -> x == lv.getInternalId());
            if (!contains) {
                versions.add(map(lv, language));
            }
        }

        entry.setCurrent(map(lexEntry.getCurrent(), language));
        entry.setVersions(versions);
        entry.setSuggestions(suggestions);

        return entry;
    }

    public static EntryVersion map(LemmaVersion lv, Language language) {
        EntryVersion dv = new EntryVersion();

        dv.setTimestamp(Instant.ofEpochMilli(lv.getTimestamp()));
        dv.setVersionStatus(lemmaVersionVerificationToDictionaryVersionStatusMapper(lv.getVerification()));

        dv.setUserComment(lv.getLemmaValues().get("contact_comment")); // TODO: or user_comment ?
        dv.setUserEmail(lv.getLemmaValues().get("contact_email")); // TODO: or user_email ?

        dv.setCreator(lv.getUserId());
        dv.setCreatorIp(lv.getIP());
        dv.setCreatorRole(lv.getCreatorRole());
        dv.setVerifier(lv.getVerifierId());

        dv.setRmStichwort(lv.getLemmaValues().get("RStichwort"));
        dv.setRmStichwortSort(lv.getLemmaValues().get("RStichwort_sort"));
        dv.setRmSemantik(lv.getLemmaValues().get("RSemantik"));
        dv.setRmSubsemantik(lv.getLemmaValues().get("RSubsemantik"));
        dv.setRmGrammatik(lv.getLemmaValues().get("RGrammatik"));
        dv.setRmGenus(lv.getLemmaValues().get("RGenus"));
        dv.setRmFlex(lv.getLemmaValues().get("RFlex"));
        dv.setRmTags(lv.getLemmaValues().get("RTags"));
        dv.setRmRedirect(lv.getLemmaValues().get("RRedirect"));
        dv.setRmEtymologie(lv.getLemmaValues().get("REtymologie"));
        dv.setRmPronunciation(lv.getLemmaValues().get("RPronunciation"));

        dv.setDeStichwort(lv.getLemmaValues().get("DStichwort"));
        dv.setDeStichwortSort(lv.getLemmaValues().get("DStichwort_sort"));
        dv.setDeSemantik(lv.getLemmaValues().get("DSemantik"));
        dv.setDeSubsemantik(lv.getLemmaValues().get("DSubsemantik"));
        dv.setDeGrammatik(lv.getLemmaValues().get("DGrammatik"));
        dv.setDeGenus(lv.getLemmaValues().get("DGenus"));
        dv.setDeTags(lv.getLemmaValues().get("DTags"));
        dv.setDeRedirect(lv.getLemmaValues().get("DRedirect"));

        dv.setCategories(lv.getLemmaValues().get("categories"));
        // dv.setExamples(lv.getLemmaValues().get("examples")); // TODO: import examples

        String inflectionType = lv.getLemmaValues().get("RInflectionType");
        if (inflectionType != null) {
            Inflection inflection = new Inflection();
            inflection.setInflectionType(inflectionTypeMapper(inflectionType));
            inflection.setInflectionSubtype(lv.getLemmaValues().get("RInflectionSubtype"));

            if (inflection.getInflectionType() == InflectionType.VERB) {
                inflection.setVerb(verbMapper(lv.getLemmaValues(), language));
            } else if (inflection.getInflectionType() == InflectionType.NOUN) {
                inflection.setNoun(nounMapper(lv.getLemmaValues()));
            } else if (inflection.getInflectionType() == InflectionType.ADJECTIVE) {
                inflection.setAdjective(adjectiveMapper(lv.getLemmaValues()));
            } else if (inflection.getInflectionType() == InflectionType.PRONOUN) {
                inflection.setPronoun(pronounMapper(lv.getLemmaValues()));
            } else if (inflection.getInflectionType() == InflectionType.OTHER) {
                inflection.setOther(otherMapper(lv.getLemmaValues()));
            }

            dv.setInflection(inflection);
        }

        return dv;
    }

    private static VersionStatus lemmaVersionVerificationToDictionaryVersionStatusMapper(LemmaVersion.Verification verification) {
        return switch (verification) {
            case REJECTED, OUTDATED -> VersionStatus.REJECTED;
            case ACCEPTED -> VersionStatus.ACCEPTED;
            case UNVERIFIED -> VersionStatus.UNVERIFIED;
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
                    logger.warn("Unexpected value: «{}»", inflectionType);
                }
                yield null;
            }
        };
    }

    private static Verb verbMapper(HashMap<String, String> lv, Language language) {
        Verb verb = new Verb();

        verb.setInfinitiv(lv.get("infinitiv"));
        // verb.setIrregular(lv.get("irregular")); // TODO check mapping
        verb.setComposedWith(lv.get("composedWith"));

        Verb.PersonalVerb preschent = new Verb.PersonalVerb();
        preschent.setSing1(lv.get("preschentsing1"));
        preschent.setSing2(lv.get("preschentsing2"));
        preschent.setSing3(lv.get("preschentsing3"));
        preschent.setPlural1(lv.get("preschentplural1"));
        preschent.setPlural2(lv.get("preschentplural2"));
        preschent.setPlural3(lv.get("preschentplural3"));
        verb.setPreschent(preschent);

        Verb.PersonalVerb imperfect = new Verb.PersonalVerb();
        imperfect.setSing1(lv.get("imperfectsing1"));
        imperfect.setSing2(lv.get("imperfectsing2"));
        imperfect.setSing3(lv.get("imperfectsing3"));
        imperfect.setPlural1(lv.get("imperfectplural1"));
        imperfect.setPlural2(lv.get("imperfectplural2"));
        imperfect.setPlural3(lv.get("imperfectplural3"));
        verb.setImperfect(imperfect);

        Verb.PersonalVerb conjunctiv = new Verb.PersonalVerb();
        conjunctiv.setSing1(lv.get("conjunctivsing1"));
        conjunctiv.setSing2(lv.get("conjunctivsing2"));
        conjunctiv.setSing3(lv.get("conjunctivsing3"));
        conjunctiv.setPlural1(lv.get("conjunctivplural1"));
        conjunctiv.setPlural2(lv.get("conjunctivplural2"));
        conjunctiv.setPlural3(lv.get("conjunctivplural3"));
        verb.setConjunctiv(conjunctiv);

        if (language.name().equals("sursilvan")|| language.name().equals("sutsilvan") || language.name().equals("puter") || language.name().equals("vallader")) {
            Verb.PersonalVerb conjunctiv2 = new Verb.PersonalVerb();
            conjunctiv2.setSing1(lv.get("conjunctiv2sing1"));
            conjunctiv2.setSing2(lv.get("conjunctiv2sing2"));
            conjunctiv2.setSing3(lv.get("conjunctiv2sing3"));
            conjunctiv2.setPlural1(lv.get("conjunctiv2plural1"));
            conjunctiv2.setPlural2(lv.get("conjunctiv2plural2"));
            conjunctiv2.setPlural3(lv.get("conjunctiv2plural3"));
            verb.setConjunctiv2(conjunctiv2);
        }

        Verb.PersonalVerb cundizional = new Verb.PersonalVerb();
        cundizional.setSing1(lv.get("cundizionalsing1"));
        cundizional.setSing2(lv.get("cundizionalsing2"));
        cundizional.setSing3(lv.get("cundizionalsing3"));
        cundizional.setPlural1(lv.get("cundizionalplural1"));
        cundizional.setPlural2(lv.get("cundizionalplural2"));
        cundizional.setPlural3(lv.get("cundizionalplural3"));
        verb.setCundiziunal(cundizional);

        if (language.name().equals("sursilvan")|| language.name().equals("sutsilvan")) {
            Verb.PersonalVerb condiizionalindirect = new Verb.PersonalVerb();
            condiizionalindirect.setSing1(lv.get("cundizionalindirectsing1"));
            condiizionalindirect.setSing2(lv.get("cundizionalindirectsing2"));
            condiizionalindirect.setSing3(lv.get("cundizionalindirectsing3"));
            condiizionalindirect.setPlural1(lv.get("cundizionalindirectplural1"));
            condiizionalindirect.setPlural2(lv.get("cundizionalindirectplural2"));
            condiizionalindirect.setPlural3(lv.get("cundizionalindirectplural3"));
            verb.setCundiziunalIndirect(condiizionalindirect);
        }

        Verb.ParticipPerfect participperfect = new Verb.ParticipPerfect();
        participperfect.setMs(lv.get("participperfectms"));
        participperfect.setFs(lv.get("participperfectfs"));
        participperfect.setMp(lv.get("participperfectmp"));
        participperfect.setFp(lv.get("participperfectfp"));
        if (language.name().equals("sursilvan")) {
            participperfect.setMsPredicativ(lv.get("participperfectmspredicativ"));
        }
        verb.setParticipPerfect(participperfect);

        Verb.Imperativ imperativ = new Verb.Imperativ();
        imperativ.setSingular(lv.get("imperativ1"));
        imperativ.setPlural(lv.get("imperativ2"));
        if (language.name().equals("puter") || language.name().equals("vallader")) {
            imperativ.setForm1(lv.get("imperativ3"));
            imperativ.setForm1(lv.get("imperativ4"));
            imperativ.setForm3(lv.get("imperativ5"));
            imperativ.setForm4(lv.get("imperativ6"));
        }
        verb.setImperativ(imperativ);

        verb.setGerundium(lv.get("gerundium"));

        Verb.PersonalVerb futur = new Verb.PersonalVerb();
        futur.setSing1(lv.get("futursing1"));
        futur.setSing2(lv.get("futursing2"));
        futur.setSing3(lv.get("futursing3"));
        futur.setPlural1(lv.get("futurplural1"));
        futur.setPlural2(lv.get("futurplural2"));
        futur.setPlural3(lv.get("futurplural3"));
        verb.setFutur(futur);

        if (language.name().equals("puter")) {
            Verb.PersonalVerb futurdubitativ = new Verb.PersonalVerb();
            futurdubitativ.setSing1(lv.get("futurdubitativsing1"));
            futurdubitativ.setSing2(lv.get("futurdubitativsing2"));
            futurdubitativ.setSing3(lv.get("futurdubitativsing3"));
            futurdubitativ.setPlural1(lv.get("futurdubitativplural1"));
            futurdubitativ.setPlural2(lv.get("futurdubitativplural2"));
            futurdubitativ.setPlural3(lv.get("futurdubitativplural3"));
            verb.setFuturDubitativ(futurdubitativ);
        }

        // enclitic

        if (language.name().equals("puter")|| language.name().equals("surmiran") || language.name().equals("sutsilvan") || language.name().equals("vallader")) {
            Verb.PersonalVerbEnclitic preschentEnclitic = new Verb.PersonalVerbEnclitic();
            preschentEnclitic.setSing1(lv.get("preschentsing1enclitic"));
            preschentEnclitic.setSing2(lv.get("preschentsing2enclitic"));
            preschentEnclitic.setSing3m(lv.get("preschentsing3encliticm"));
            preschentEnclitic.setSing3f(lv.get("preschentsing3encliticf"));
            preschentEnclitic.setPlural1(lv.get("preschentplural1enclitic"));
            preschentEnclitic.setPlural2(lv.get("preschentplural2enclitic"));
            preschentEnclitic.setPlural3(lv.get("preschentplural3enclitic"));
            verb.setPreschentEnclitic(preschentEnclitic);

            Verb.PersonalVerbEnclitic imperfectEnclitic = new Verb.PersonalVerbEnclitic();
            imperfectEnclitic.setSing1(lv.get("imperfectsing1enclitic"));
            imperfectEnclitic.setSing2(lv.get("imperfectsing2enclitic"));
            imperfectEnclitic.setSing3m(lv.get("imperfectsing3encliticm"));
            imperfectEnclitic.setSing3f(lv.get("imperfectsing3encliticf"));
            imperfectEnclitic.setPlural1(lv.get("imperfectplural1enclitic"));
            imperfectEnclitic.setPlural2(lv.get("imperfectplural2enclitic"));
            imperfectEnclitic.setPlural3(lv.get("imperfectplural3enclitic"));
            verb.setImperfectEnclitic(imperfectEnclitic);

            Verb.PersonalVerbEnclitic cundizionalEnclitic = new Verb.PersonalVerbEnclitic();
            cundizionalEnclitic.setSing1(lv.get("cundizionalsing1enclitic"));
            cundizionalEnclitic.setSing2(lv.get("cundizionalsing2enclitic"));
            cundizionalEnclitic.setSing3m(lv.get("cundizionalsing3encliticm"));
            cundizionalEnclitic.setSing3f(lv.get("cundizionalsing3encliticf"));
            cundizionalEnclitic.setPlural1(lv.get("cundizionalplural1enclitic"));
            cundizionalEnclitic.setPlural2(lv.get("cundizionalplural2enclitic"));
            cundizionalEnclitic.setPlural3(lv.get("cundizionalplural3enclitic"));
            verb.setCundizionalEnclitic(cundizionalEnclitic);
        }

        if (language.name().equals("puter")|| language.name().equals("surmiran") || language.name().equals("vallader")) {
            Verb.PersonalVerbEnclitic futurEnclitic = new Verb.PersonalVerbEnclitic();
            futurEnclitic.setSing1(lv.get("futursing1enclitic"));
            futurEnclitic.setSing2(lv.get("futursing2enclitic"));
            futurEnclitic.setSing3m(lv.get("futursing3encliticm"));
            futurEnclitic.setSing3f(lv.get("futursing3encliticf"));
            futurEnclitic.setPlural1(lv.get("futurplural1enclitic"));
            futurEnclitic.setPlural2(lv.get("futurplural2enclitic"));
            futurEnclitic.setPlural3(lv.get("futurplural3enclitic"));
            verb.setFuturEnclitic(futurEnclitic);
        }

        if (language.name().equals("puter")) {
            Verb.PersonalVerbEnclitic futurdubitativEnclitic = new Verb.PersonalVerbEnclitic();
            futurdubitativEnclitic.setSing1(lv.get("futurdubitativsing1enclitic"));
            futurdubitativEnclitic.setSing2(lv.get("futurdubitativsing2enclitic"));
            futurdubitativEnclitic.setSing3m(lv.get("futurdubitativsing3encliticm"));
            futurdubitativEnclitic.setSing3f(lv.get("futurdubitativsing3encliticf"));
            futurdubitativEnclitic.setPlural1(lv.get("futurdubitativplural1enclitic"));
            futurdubitativEnclitic.setPlural2(lv.get("futurdubitativplural2enclitic"));
            futurdubitativEnclitic.setPlural3(lv.get("futurdubitativplural3enclitic"));
            verb.setFuturDubitativEnclitic(futurdubitativEnclitic);
        }

        return verb;
    }

    private static Noun nounMapper(HashMap<String, String> lv) {
        Noun noun = new Noun();

        noun.setBaseForm(lv.get("baseform"));
        noun.setMSingular(lv.get("mSingular"));
        noun.setFSingular(lv.get("fSingular"));
        noun.setMPlural(lv.get("mPlural"));
        noun.setFPlural(lv.get("fPlural"));
        noun.setPluralCollectiv(lv.get("pluralCollectiv"));

        return noun;
    }

    private static Adjective adjectiveMapper(HashMap<String, String> lv) {
        Adjective adjective = new Adjective();

        adjective.setBaseForm(lv.get("baseform"));
        adjective.setMSingular(lv.get("mSingular"));
        adjective.setFSingular(lv.get("fSingular"));
        adjective.setMPlural(lv.get("mPlural"));
        adjective.setFPlural(lv.get("fPlural"));
        adjective.setAdverbialForm(lv.get("adverbialForm"));

        return adjective;
    }

    private static Pronoun pronounMapper(HashMap<String, String> lv) {
        Pronoun pronoun = new Pronoun();

        pronoun.setBaseForm(lv.get("baseform"));
        pronoun.setMSingular(lv.get("mSingular"));
        pronoun.setFSingular(lv.get("fSingular"));
        pronoun.setMPlural(lv.get("mPlural"));
        pronoun.setFPlural(lv.get("fPlural"));

        return pronoun;
    }

    private static Other otherMapper(HashMap<String, String> lv) {
        Other other = new Other();

        other.setBaseForm(lv.get("baseform"));
        other.setOtherForm1(lv.get("otherForm1"));
        other.setOtherForm2(lv.get("otherForm2"));
        other.setOtherForm3(lv.get("otherForm3"));
        other.setOtherForm4(lv.get("otherForm4"));

        return other;
    }
}
