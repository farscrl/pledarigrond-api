package ch.pledarigrond.inflection.generation.verb.vallader;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.common.data.dictionary.inflection.VerbDto;
import ch.pledarigrond.common.util.PronunciationNormalizer;
import ch.pledarigrond.inflection.generation.verb.LanguageConjugation;
import ch.pledarigrond.inflection.model.InflectionSubType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValladerConjugation extends LanguageConjugation {

    private static final Logger logger = LoggerFactory.getLogger(ValladerConjugation.class);

    private InflectionDto inflection;
    private VerbDto verb;
    private InflectionSubType verbClass;

    private String infinitiv;
    private boolean isReflexive;

    private String root;
    private String modRoot;
    private String ending;

    @Override
    public void reset() {
        inflection = new InflectionDto();
        verb = new VerbDto();
        verbClass = null;
        inflection.setVerb(verb);
        inflection.setInflectionType(InflectionType.VERB);

        infinitiv = null;
        isReflexive = false;

        root = null;
        modRoot = null;
        ending = null;
    }

    @Override
    public InflectionDto generateConjugation(String conjugationClass, String infinitivForm) {
        reset();

        verb.setInfinitiv(infinitivForm);
        infinitivForm = PronunciationNormalizer.normalizePronunciation(infinitivForm);
        infinitiv = checkReflexiveness(infinitivForm);
        root = getRoot(infinitiv);

        if (conjugationClass.equals("1a")) {
            modRoot = root + "esch";
        }

        if (conjugationClass.equals("4a")) {
            modRoot = root + "isch";
        }

        if (infinitiv.endsWith("ger") || infinitiv.endsWith("gir") || infinitiv.endsWith("glir")) {
            modRoot = infinitiv.substring(0, infinitiv.length() - 2) + "i";
        }

        if (modRoot == null) {
            modRoot = root;
        }

        InflectionSubType subType = ValladerConjugationClasses.getConjugationClass(conjugationClass);
        if (subType == null) {
            throw new RuntimeException(conjugationClass + " is not a valid conjugation class.");
        } else if (ending == null) {
            throw new RuntimeException(infinitivForm + "  is not a valid verb. Please enter a verb in its infinitive form.");
        }

        verbClass = subType;
        verb.setInflectionSubtype(verbClass.id);
        conjugate();
        addPronouns();
        return inflection;
    }

    @Override
    public InflectionDto guessInflection(String baseForm, String genus, String flex) {
        reset();
        
        baseForm = PronunciationNormalizer.normalizePronunciation(baseForm);
        if (baseForm.length() < 3) {
            return null;
        }

        String lastTwo = baseForm.substring(baseForm.length() - 2);
        String lastThree = baseForm.substring(baseForm.length() - 3);

        if (lastThree.equals("air")) {
            return generateConjugation("2", baseForm);
        }

        if (lastTwo.equals("ar")) {
            if (flex != null && flex.contains("-esch")) {
                return generateConjugation("1a", baseForm);
            }

            return generateConjugation("1", baseForm);
        }

        if (lastTwo.equals("er")) {
            return generateConjugation("3", baseForm);
        }

        if (lastTwo.equals("ir")) {
            if (flex != null && flex.contains("-esch")) {
                return generateConjugation("4a", baseForm);
            }

            return generateConjugation("4", baseForm);
        }

        return null;
    }

    private String getRoot(String infinitivForm) {
        if (infinitivForm != null) {
            infinitivForm = normalizeString(infinitivForm);

            if (infinitivForm.equals("ir")) {
                ending = infinitivForm;
                return infinitivForm;
            }

            if (infinitivForm.length() < 3) {
                    throw new RuntimeException("'" + infinitivForm + "' is not a valid verb. Please enter a verb in its infinitive form.");
            }

            infinitivForm = extractInfinitiv(infinitivForm);
        }
        return infinitivForm;
    }

    private String extractInfinitiv(String infinitivForm) {
        String l2 = infinitivForm.substring(infinitivForm.length() - 2);
        String l3 = infinitivForm.substring(infinitivForm.length() - 3);

        if (l3.equals("air")) {
            ending = l3;
            infinitivForm = infinitivForm.substring(0, infinitivForm.length() - 3);

            return infinitivForm;
        }

        ending = l2;
        infinitivForm = infinitivForm.substring(0, infinitivForm.length() - 2);

        return infinitivForm;
    }

    private String checkReflexiveness(String query) {
        if (query.startsWith("as ")) {
            query = query.length() > 3 ? query.substring(3) : query;
            isReflexive = true;

        } else if (query.startsWith("s'")) {
            query = query.length() > 2 ? query.substring(2) : query;
            isReflexive = true;
        } else {
            isReflexive = false;
        }

        return query;
    }

    private void conjugate() {
        verb.setIsReflexive(isReflexive);

        setPreschent();
        setImperfect();
        setConjunctiv();
        setCundizional();
        setConjunctiv2(); // ATTENTION: has to be after cundiziunal, as it uses the forms from cundziunal
        setParticipPerfect();
        setImperativ();
        setGerundium();
        setFutur();

        setPreschentEnclitic();
        setImperfectEnclitic();
        setCundizionalEnclitic();
        setFuturEnclitic();
    }

    private void setPreschent() {
        VerbDto.PersonalVerbDto preschent = new VerbDto.PersonalVerbDto();
        
        switch (verbClass.id) {
            case "1":
            case "1a":
            case "2":
            case "3":
                if(infinitiv.endsWith("giar") || infinitiv.endsWith("gliar") || infinitiv.endsWith("ger")) {
                    preschent.setSing1(modRoot.substring(0, modRoot.length() - 1));
                } else if (
                        infinitiv.endsWith("ffar") ||
                                infinitiv.endsWith("ffer") ||
                                infinitiv.endsWith("llar") ||
                                infinitiv.endsWith("ller") ||
                                infinitiv.endsWith("mmar") ||
                                infinitiv.endsWith("mmer") ||
                                infinitiv.endsWith("nnar") ||
                                infinitiv.endsWith("nner") ||
                                infinitiv.endsWith("ppar") ||
                                infinitiv.endsWith("pper") ||
                                infinitiv.endsWith("rrar") ||
                                infinitiv.endsWith("rrer") ||
                                infinitiv.endsWith("ttar") ||
                                infinitiv.endsWith("tter") ||
                                infinitiv.endsWith("zzar") ||
                                infinitiv.endsWith("zzer")
                ) {
                    preschent.setSing1(modRoot.substring(0, modRoot.length() - 1));
                } else {
                    preschent.setSing1(modRoot);
                }
                preschent.setSing2(modRoot + "ast");
                preschent.setSing3(modRoot + "a");
                if (infinitiv.endsWith("ger")) {
                    preschent.setPlural1(modRoot + "ain");
                    preschent.setPlural2(modRoot + "ais\n(" + modRoot + "aivat)");
                } else {
                    preschent.setPlural1(root + "ain");
                    preschent.setPlural2(root + "ais\n(" + root + "aivat)");
                }
                preschent.setPlural3(modRoot + "an");
                break;


            case "4":
            case "4a":
                if(infinitiv.endsWith("gir") || infinitiv.endsWith("glir")) {
                    preschent.setSing1(modRoot.substring(0, modRoot.length() - 1));
                } else {
                    preschent.setSing1(modRoot);
                }
                preschent.setSing2(modRoot + "ast");
                preschent.setSing3(modRoot + "a");
                preschent.setPlural1(root + "in");
                preschent.setPlural2(root + "is\n(" + root + "ivat)");
                preschent.setPlural3(modRoot + "an");
                break;
        }

        verb.setPreschent(preschent);
    }

    private void setImperfect() {
        VerbDto.PersonalVerbDto imperfect = new VerbDto.PersonalVerbDto();

        switch (verbClass.id.substring(0, 1)) {

            case "1":
            case "2":
            case "3":
                imperfect.setSing1(root + "aiva");
                imperfect.setSing2(root + "aivast");
                imperfect.setSing3(root + "aiva");
                imperfect.setPlural1(root + "aivan");
                imperfect.setPlural2(root + "aivat");
                imperfect.setPlural3(root + "aivan");
                break;

            case "4":
                imperfect.setSing1(root + "iva");
                imperfect.setSing2(root + "ivast");
                imperfect.setSing3(root + "iva");
                imperfect.setPlural1(root + "ivan");
                imperfect.setPlural2(root + "ivat");
                imperfect.setPlural3(root + "ivan");
                break;
        }

        verb.setImperfect(imperfect);
    }

    private void setConjunctiv() {
        VerbDto.PersonalVerbDto conjunctiv = new VerbDto.PersonalVerbDto();

        switch (verbClass.id) {
            default:
                conjunctiv.setSing1(modRoot + "a");
                conjunctiv.setSing2(modRoot + "ast");
                conjunctiv.setSing3(modRoot + "a");
                conjunctiv.setPlural1(modRoot + "an");
                conjunctiv.setPlural2(modRoot + "at");
                conjunctiv.setPlural3(modRoot + "an");
                break;
        }

        verb.setConjunctiv(conjunctiv);
    }

    private void setConjunctiv2() {
        VerbDto.PersonalVerbDto conjunctiv2 = new VerbDto.PersonalVerbDto();

        conjunctiv2.setSing1(verb.getCundiziunal().getSing1());
        conjunctiv2.setSing2(verb.getCundiziunal().getSing2());
        conjunctiv2.setSing3(verb.getCundiziunal().getSing3());
        conjunctiv2.setPlural1(verb.getCundiziunal().getPlural1());
        conjunctiv2.setPlural2(verb.getCundiziunal().getPlural2());
        conjunctiv2.setPlural3(verb.getCundiziunal().getPlural3());

        verb.setConjunctivImperfect(conjunctiv2);
    }

    private void setCundizional() {
        VerbDto.PersonalVerbDto cundizional = new VerbDto.PersonalVerbDto();

        switch (verbClass.id.substring(0, 1)) {
            case "4":
                cundizional.setSing1(root + "iss");
                cundizional.setSing2(root + "issast");
                cundizional.setSing3(root + "iss");
                cundizional.setPlural1(root + "issan");
                cundizional.setPlural2(root + "issat");
                cundizional.setPlural3(root + "issan");
                break;

            default:
                cundizional.setSing1(root + "ess");
                cundizional.setSing2(root + "essast");
                cundizional.setSing3(root + "ess");
                cundizional.setPlural1(root + "essan");
                cundizional.setPlural2(root + "essat");
                cundizional.setPlural3(root + "essan");
                break;
        }

        verb.setCundiziunal(cundizional);
    }

    private void setParticipPerfect() {
        VerbDto.ParticipPerfectDto participPerfect = new VerbDto.ParticipPerfectDto();

        switch (verbClass.id.substring(0, 1)) {
            case "1":
                participPerfect.setMs(root + "à");
                participPerfect.setMp(root + "ats");
                participPerfect.setFs(root + "ada");
                participPerfect.setFp(root + "adas");
                break;

            case "4":
                participPerfect.setMs(root + "i");
                participPerfect.setMp(root + "its");
                participPerfect.setFs(root + "ida");
                participPerfect.setFp(root + "idas");
                break;

            default:
                participPerfect.setMs(root + "ü");
                participPerfect.setMp(root + "üts");
                participPerfect.setFs(root + "üda");
                participPerfect.setFp(root + "üdas");
                break;
        }

        verb.setParticipPerfect(participPerfect);
    }

    private void setGerundium() {

        switch (verbClass.id.substring(0, 1)) {
            case "4":
                verb.setGerundium(root + "ind");
                break;
            default:
                verb.setGerundium(root + "ond");
                break;
        }

    }

    private void setImperativ() {
        VerbDto.ImperativDto imperativ = new VerbDto.ImperativDto();

        switch (verbClass.id.substring(0, 1)) {
            case "4":
                imperativ.setSingular(modRoot + "a");
                imperativ.setPlural(root + "i");
                imperativ.setForm3(infinitiv);
                imperativ.setForm4(root + "irai");
                imperativ.setForm5(modRoot + "a");
                imperativ.setForm6(modRoot + "an");
                break;


            default:
                imperativ.setSingular(modRoot + "a");
                imperativ.setPlural(root + "ai");
                imperativ.setForm3(infinitiv);
                imperativ.setForm4(root + "arai");
                imperativ.setForm5(modRoot + "a");
                imperativ.setForm6(modRoot + "an");
                break;
        }

        verb.setImperativ(imperativ);
    }

    private void setFutur() {
        VerbDto.PersonalVerbDto futur = new VerbDto.PersonalVerbDto();

        switch (verbClass.id.substring(0, 1)) {
            case "4":
                futur.setSing1(root + "irà");
                futur.setSing2(root + "irast");
                futur.setSing3(root + "irà");
                futur.setPlural1(root + "iran");
                futur.setPlural2(root + "irat");
                futur.setPlural3(root + "iran");
                break;

            default:
                futur.setSing1(root + "arà");
                futur.setSing2(root + "arast");
                futur.setSing3(root + "arà");
                futur.setPlural1(root + "aran");
                futur.setPlural2(root + "arat");
                futur.setPlural3(root + "aran");
                break;
        }

        verb.setFutur(futur);
    }


    private void setPreschentEnclitic() {
        VerbDto.PersonalVerbEncliticDto preschentEnclitic = new VerbDto.PersonalVerbEncliticDto();

        if (infinitiv.endsWith("giar") ||
            infinitiv.endsWith("gliar") ||
            infinitiv.endsWith("ger") ||
            infinitiv.endsWith("gir") ||
            infinitiv.endsWith("glir")) {
            preschentEnclitic.setSing1(verb.getPreschent().getSing1() + "ia");
            preschentEnclitic.setSing2(verb.getPreschent().getSing2());
            preschentEnclitic.setSing3m(verb.getPreschent().getSing3() + "'l");
            preschentEnclitic.setSing3f(verb.getPreschent().getSing3().substring(0, verb.getPreschent().getSing3().length() - 2) + "'la");
            preschentEnclitic.setPlural1(verb.getPreschent().getPlural1() + "a");
            preschentEnclitic.setPlural2(verb.getPreschent().getPlural2());
            preschentEnclitic.setPlural3(verb.getPreschent().getPlural3().substring(0, verb.getPreschent().getPlural3().length() - 3) + "na");
        } else if (
                infinitiv.endsWith("ffar") ||
                infinitiv.endsWith("ffer") ||
                infinitiv.endsWith("llar") ||
                infinitiv.endsWith("ller") ||
                infinitiv.endsWith("mmar") ||
                infinitiv.endsWith("mmer") ||
                infinitiv.endsWith("nnar") ||
                infinitiv.endsWith("nner") ||
                infinitiv.endsWith("ppar") ||
                infinitiv.endsWith("pper") ||
                infinitiv.endsWith("rrar") ||
                infinitiv.endsWith("rrer") ||
                infinitiv.endsWith("ttar") ||
                infinitiv.endsWith("tter") ||
                infinitiv.endsWith("zzar") ||
                infinitiv.endsWith("zzer")
        ) {
            String consonant = verb.getPreschent().getSing1().substring(verb.getPreschent().getSing1().length() - 1);

            preschentEnclitic.setSing1(verb.getPreschent().getSing1() + consonant + "a");
            preschentEnclitic.setSing2(verb.getPreschent().getSing2());
            preschentEnclitic.setSing3m(verb.getPreschent().getSing3() + "'l");
            preschentEnclitic.setSing3f(verb.getPreschent().getSing3().substring(0, verb.getPreschent().getSing3().length() - 1) + "'la");
            preschentEnclitic.setPlural1(verb.getPreschent().getPlural1() + "a");
            preschentEnclitic.setPlural2(verb.getPreschent().getPlural2());
            preschentEnclitic.setPlural3(verb.getPreschent().getPlural3().substring(0, verb.getPreschent().getPlural3().length() - 3) + "na");
        } else {
            preschentEnclitic.setSing1(verb.getPreschent().getSing1() + "a");
            preschentEnclitic.setSing2(verb.getPreschent().getSing2());
            preschentEnclitic.setSing3m(verb.getPreschent().getSing3() + "'l");
            preschentEnclitic.setSing3f(verb.getPreschent().getSing3().substring(0, verb.getPreschent().getSing3().length() - 1) + "'la");
            preschentEnclitic.setPlural1(verb.getPreschent().getPlural1() + "a");
            preschentEnclitic.setPlural2(verb.getPreschent().getPlural2());
            preschentEnclitic.setPlural3(verb.getPreschent().getPlural3().substring(0, verb.getPreschent().getPlural3().length() - 2) + "na");
        }

        verb.setPreschentEnclitic(preschentEnclitic);
    }

    private void setImperfectEnclitic() {
        VerbDto.PersonalVerbEncliticDto imperfectEnclitic = new VerbDto.PersonalVerbEncliticDto();

        imperfectEnclitic.setSing1(verb.getImperfect().getSing1());
        imperfectEnclitic.setSing2(verb.getImperfect().getSing2());
        imperfectEnclitic.setSing3m(verb.getImperfect().getSing3() + "'l");
        imperfectEnclitic.setSing3f(verb.getImperfect().getSing3().substring(0, verb.getImperfect().getSing3().length() - 1) + "'la");
        imperfectEnclitic.setPlural1(verb.getImperfect().getPlural1().substring(0, verb.getImperfect().getPlural1().length() - 2) + "na");
        imperfectEnclitic.setPlural2(verb.getImperfect().getPlural2());
        imperfectEnclitic.setPlural3(verb.getImperfect().getPlural3().substring(0, verb.getImperfect().getPlural3().length() - 2) + "na");

        verb.setImperfectEnclitic(imperfectEnclitic);
    }

    private void setCundizionalEnclitic() {
        VerbDto.PersonalVerbEncliticDto cundizionalEnclitic = new VerbDto.PersonalVerbEncliticDto();

        cundizionalEnclitic.setSing1(verb.getCundiziunal().getSing1() + "a");
        cundizionalEnclitic.setSing2(verb.getCundiziunal().getSing2());
        cundizionalEnclitic.setSing3m(verb.getCundiziunal().getSing3() + "a'l");
        cundizionalEnclitic.setSing3f(verb.getCundiziunal().getSing3() + "'la");
        cundizionalEnclitic.setPlural1(verb.getCundiziunal().getPlural1().substring(0, verb.getCundiziunal().getPlural1().length() - 2) + "na");
        cundizionalEnclitic.setPlural2(verb.getCundiziunal().getPlural2());
        cundizionalEnclitic.setPlural3(verb.getCundiziunal().getPlural3().substring(0, verb.getCundiziunal().getPlural3().length() - 2) + "na");

        verb.setCundiziunalEnclitic(cundizionalEnclitic);
    }

    private void setFuturEnclitic() {
        VerbDto.PersonalVerbEncliticDto futurEnclitic = new VerbDto.PersonalVerbEncliticDto();

        futurEnclitic.setSing1(verb.getFutur().getSing1().substring(0, verb.getFutur().getSing1().length() - 1) + "aja");
        futurEnclitic.setSing2(verb.getFutur().getSing2());
        futurEnclitic.setSing3m(verb.getFutur().getSing3() + "'l");
        futurEnclitic.setSing3f(verb.getFutur().getSing3() + "'la");
        futurEnclitic.setPlural1(verb.getFutur().getPlural1() + "a");
        futurEnclitic.setPlural2(verb.getFutur().getPlural2());
        futurEnclitic.setPlural3(verb.getFutur().getPlural3() + "a");

        verb.setFuturEnclitic(futurEnclitic);
    }

    private void addPronouns() {

        if (verb.getInfinitiv().startsWith("as ")) {
            // Reflexive Verbs that start with consonants
            addReflexivePronouns();

        } else if (verb.getInfinitiv().startsWith("s'")) {
            // Reflexive Verbs that start with vowels
            addReflexivePronounsVowel();

        } else {
            // Standard Verbs
            addStandardPronouns();
        }
    }

    private void addReflexivePronouns() {

        // PRESCHENT
        verb.getPreschent().setSing1(setPronoun(ValladerPronouns.pron_1ps + ValladerPronouns.pron_r_1ps, verb.getPreschent().getSing1()));
        verb.getPreschent().setSing2(setPronoun(ValladerPronouns.pron_2ps + ValladerPronouns.pron_r_2ps, verb.getPreschent().getSing2()));
        verb.getPreschent().setSing3(setPronoun(ValladerPronouns.pron_3ps + ValladerPronouns.pron_r_3ps, verb.getPreschent().getSing3()));
        verb.getPreschent().setPlural1(setPronoun(ValladerPronouns.pron_1pp + ValladerPronouns.pron_r_1pp, verb.getPreschent().getPlural1()));
        verb.getPreschent().setPlural2(setPronoun(ValladerPronouns.pron_2pp + ValladerPronouns.pron_r_2pp, verb.getPreschent().getPlural2()));
        verb.getPreschent().setPlural3(setPronoun(ValladerPronouns.pron_3pp + ValladerPronouns.pron_r_3pp, verb.getPreschent().getPlural3()));

        // IMPERFECT
        verb.getImperfect().setSing1(setPronoun(ValladerPronouns.pron_1ps + ValladerPronouns.pron_r_1ps, verb.getImperfect().getSing1()));
        verb.getImperfect().setSing2(setPronoun(ValladerPronouns.pron_2ps + ValladerPronouns.pron_r_2ps, verb.getImperfect().getSing2()));
        verb.getImperfect().setSing3(setPronoun(ValladerPronouns.pron_3ps + ValladerPronouns.pron_r_3ps, verb.getImperfect().getSing3()));
        verb.getImperfect().setPlural1(setPronoun(ValladerPronouns.pron_1pp + ValladerPronouns.pron_r_1pp, verb.getImperfect().getPlural1()));
        verb.getImperfect().setPlural2(setPronoun(ValladerPronouns.pron_2pp + ValladerPronouns.pron_r_2pp, verb.getImperfect().getPlural2()));
        verb.getImperfect().setPlural3(setPronoun(ValladerPronouns.pron_3pp + ValladerPronouns.pron_r_3pp, verb.getImperfect().getPlural3()));

        // CONJUNCTIV
        verb.getConjunctiv().setSing1(setPronoun(ValladerPronouns.pron_conjunctiv_v + ValladerPronouns.pron_1ps + ValladerPronouns.pron_r_1ps, verb.getConjunctiv().getSing1()));
        verb.getConjunctiv().setSing2(setPronoun(ValladerPronouns.pron_conjunctiv_c + ValladerPronouns.pron_2ps + ValladerPronouns.pron_r_2ps, verb.getConjunctiv().getSing2()));
        verb.getConjunctiv().setSing3(setPronoun(ValladerPronouns.pron_conjunctiv_v + ValladerPronouns.pron_3ps + ValladerPronouns.pron_r_3ps, verb.getConjunctiv().getSing3()));
        verb.getConjunctiv().setPlural1(setPronoun(ValladerPronouns.pron_conjunctiv_c + ValladerPronouns.pron_1pp + ValladerPronouns.pron_r_1pp, verb.getConjunctiv().getPlural1()));
        verb.getConjunctiv().setPlural2(setPronoun(ValladerPronouns.pron_conjunctiv_c + ValladerPronouns.pron_2pp + ValladerPronouns.pron_r_2pp, verb.getConjunctiv().getPlural2()));
        verb.getConjunctiv().setPlural3(setPronoun(ValladerPronouns.pron_conjunctiv_v + ValladerPronouns.pron_3pp + ValladerPronouns.pron_r_3pp, verb.getConjunctiv().getPlural3()));

        // CONJUNCTIV imperfect
        verb.getConjunctivImperfect().setSing1(setPronoun(ValladerPronouns.pron_conjunctiv_v + ValladerPronouns.pron_1ps + ValladerPronouns.pron_r_1ps, verb.getConjunctivImperfect().getSing1()));
        verb.getConjunctivImperfect().setSing2(setPronoun(ValladerPronouns.pron_conjunctiv_c + ValladerPronouns.pron_2ps + ValladerPronouns.pron_r_2ps, verb.getConjunctivImperfect().getSing2()));
        verb.getConjunctivImperfect().setSing3(setPronoun(ValladerPronouns.pron_conjunctiv_v + ValladerPronouns.pron_3ps + ValladerPronouns.pron_r_3ps, verb.getConjunctivImperfect().getSing3()));
        verb.getConjunctivImperfect().setPlural1(setPronoun(ValladerPronouns.pron_conjunctiv_c + ValladerPronouns.pron_1pp + ValladerPronouns.pron_r_1pp, verb.getConjunctivImperfect().getPlural1()));
        verb.getConjunctivImperfect().setPlural2(setPronoun(ValladerPronouns.pron_conjunctiv_c + ValladerPronouns.pron_2pp + ValladerPronouns.pron_r_2pp, verb.getConjunctivImperfect().getPlural2()));
        verb.getConjunctivImperfect().setPlural3(setPronoun(ValladerPronouns.pron_conjunctiv_v + ValladerPronouns.pron_3pp + ValladerPronouns.pron_r_3pp, verb.getConjunctivImperfect().getPlural3()));

        // CUNDIZIONAL
        verb.getCundiziunal().setSing1(setPronoun(ValladerPronouns.pron_1ps + ValladerPronouns.pron_r_1ps, verb.getCundiziunal().getSing1()));
        verb.getCundiziunal().setSing2(setPronoun(ValladerPronouns.pron_2ps + ValladerPronouns.pron_r_2ps, verb.getCundiziunal().getSing2()));
        verb.getCundiziunal().setSing3(setPronoun(ValladerPronouns.pron_3ps + ValladerPronouns.pron_r_3ps, verb.getCundiziunal().getSing3()));
        verb.getCundiziunal().setPlural1(setPronoun(ValladerPronouns.pron_1pp + ValladerPronouns.pron_r_1pp, verb.getCundiziunal().getPlural1()));
        verb.getCundiziunal().setPlural2(setPronoun(ValladerPronouns.pron_2pp + ValladerPronouns.pron_r_2pp, verb.getCundiziunal().getPlural2()));
        verb.getCundiziunal().setPlural3(setPronoun(ValladerPronouns.pron_3pp + ValladerPronouns.pron_r_3pp, verb.getCundiziunal().getPlural3()));

        // IMPERATIV
        verb.getImperativ().setSingular(setPronoun(ValladerPronouns.imperativ_refl_sg, verb.getImperativ().getSingular(), "!"));
        verb.getImperativ().setPlural(setPronoun(ValladerPronouns.imperativ_refl_pl, verb.getImperativ().getPlural(), "!"));
        verb.getImperativ().setForm3(setPronoun(ValladerPronouns.imperativ_not_refl_sg, verb.getImperativ().getForm3(), "!"));
        verb.getImperativ().setForm4(setPronoun(ValladerPronouns.imperativ_not_refl_pl, verb.getImperativ().getForm4(), "!"));
        verb.getImperativ().setForm5(setPronoun(ValladerPronouns.imperativ_polite_sg + ValladerPronouns.pron_r_3ps, verb.getImperativ().getForm5(), "!"));
        verb.getImperativ().setForm6(setPronoun(ValladerPronouns.imperativ_polite_pl + ValladerPronouns.pron_r_3pp, verb.getImperativ().getForm6(), "!"));

        // PARTICIP_PERFECT
        verb.getParticipPerfect().setMs(verb.getParticipPerfect().getMs());
        verb.getParticipPerfect().setFs(verb.getParticipPerfect().getFs());
        verb.getParticipPerfect().setMp(verb.getParticipPerfect().getMp());
        verb.getParticipPerfect().setFp(verb.getParticipPerfect().getFp());

        // GERUNDIUM
        verb.setGerundium(setPronoun(ValladerPronouns.pron_r_2pp, verb.getGerundium()));

        // FUTUR
        verb.getFutur().setSing1(setPronoun(ValladerPronouns.pron_1ps + ValladerPronouns.pron_r_1ps, verb.getFutur().getSing1()));
        verb.getFutur().setSing2(setPronoun(ValladerPronouns.pron_2ps + ValladerPronouns.pron_r_2ps, verb.getFutur().getSing2()));
        verb.getFutur().setSing3(setPronoun(ValladerPronouns.pron_3ps + ValladerPronouns.pron_r_3ps, verb.getFutur().getSing3()));
        verb.getFutur().setPlural1(setPronoun(ValladerPronouns.pron_1pp + ValladerPronouns.pron_r_1pp, verb.getFutur().getPlural1()));
        verb.getFutur().setPlural2(setPronoun(ValladerPronouns.pron_2pp + ValladerPronouns.pron_r_2pp, verb.getFutur().getPlural2()));
        verb.getFutur().setPlural3(setPronoun(ValladerPronouns.pron_3pp + ValladerPronouns.pron_r_3pp, verb.getFutur().getPlural3()));

        // ENCLITIC PRESCHENT
        verb.getPreschentEnclitic().setSing1(setPronoun(ValladerPronouns.pron_r_1ps, verb.getPreschentEnclitic().getSing1()));
        verb.getPreschentEnclitic().setSing2(setPronoun(ValladerPronouns.pron_r_2ps, verb.getPreschentEnclitic().getSing2()));
        verb.getPreschentEnclitic().setSing3m(setPronoun(ValladerPronouns.pron_r_3ps, verb.getPreschentEnclitic().getSing3m()));
        verb.getPreschentEnclitic().setSing3f(setPronoun(ValladerPronouns.pron_r_3ps, verb.getPreschentEnclitic().getSing3f()));
        verb.getPreschentEnclitic().setPlural1(setPronoun(ValladerPronouns.pron_r_1pp, verb.getPreschentEnclitic().getPlural1()));
        verb.getPreschentEnclitic().setPlural2(setPronoun(ValladerPronouns.pron_r_2pp, verb.getPreschentEnclitic().getPlural2()));
        verb.getPreschentEnclitic().setPlural3(setPronoun(ValladerPronouns.pron_r_3pp, verb.getPreschentEnclitic().getPlural3()));

        // ENCLITIC IMPERFECT
        verb.getImperfectEnclitic().setSing1(setPronoun(ValladerPronouns.pron_r_1ps, verb.getImperfectEnclitic().getSing1()));
        verb.getImperfectEnclitic().setSing2(setPronoun(ValladerPronouns.pron_r_2ps, verb.getImperfectEnclitic().getSing2()));
        verb.getImperfectEnclitic().setSing3m(setPronoun(ValladerPronouns.pron_r_3ps, verb.getImperfectEnclitic().getSing3m()));
        verb.getImperfectEnclitic().setSing3f(setPronoun(ValladerPronouns.pron_r_3ps, verb.getImperfectEnclitic().getSing3f()));
        verb.getImperfectEnclitic().setPlural1(setPronoun(ValladerPronouns.pron_r_1pp, verb.getImperfectEnclitic().getPlural1()));
        verb.getImperfectEnclitic().setPlural2(setPronoun(ValladerPronouns.pron_r_2pp, verb.getImperfectEnclitic().getPlural2()));
        verb.getImperfectEnclitic().setPlural3(setPronoun(ValladerPronouns.pron_r_3pp, verb.getImperfectEnclitic().getPlural3()));

        // ENCLITIC CUNDIZIONAL
        verb.getCundiziunalEnclitic().setSing1(setPronoun(ValladerPronouns.pron_r_1ps, verb.getCundiziunalEnclitic().getSing1()));
        verb.getCundiziunalEnclitic().setSing2(setPronoun(ValladerPronouns.pron_r_2ps, verb.getCundiziunalEnclitic().getSing2()));
        verb.getCundiziunalEnclitic().setSing3m(setPronoun(ValladerPronouns.pron_r_3ps, verb.getCundiziunalEnclitic().getSing3m()));
        verb.getCundiziunalEnclitic().setSing3f(setPronoun(ValladerPronouns.pron_r_3ps, verb.getCundiziunalEnclitic().getSing3f()));
        verb.getCundiziunalEnclitic().setPlural1(setPronoun(ValladerPronouns.pron_r_1pp, verb.getCundiziunalEnclitic().getPlural1()));
        verb.getCundiziunalEnclitic().setPlural2(setPronoun(ValladerPronouns.pron_r_2pp, verb.getCundiziunalEnclitic().getPlural2()));
        verb.getCundiziunalEnclitic().setPlural3(setPronoun(ValladerPronouns.pron_r_3pp, verb.getCundiziunalEnclitic().getPlural3()));

        // ENCLITIC FUTUR
        verb.getFuturEnclitic().setSing1(setPronoun(ValladerPronouns.pron_r_1ps, verb.getFuturEnclitic().getSing1()));
        verb.getFuturEnclitic().setSing2(setPronoun(ValladerPronouns.pron_r_2ps, verb.getFuturEnclitic().getSing2()));
        verb.getFuturEnclitic().setSing3m(setPronoun(ValladerPronouns.pron_r_3ps, verb.getFuturEnclitic().getSing3m()));
        verb.getFuturEnclitic().setSing3f(setPronoun(ValladerPronouns.pron_r_3ps, verb.getFuturEnclitic().getSing3f()));
        verb.getFuturEnclitic().setPlural1(setPronoun(ValladerPronouns.pron_r_1pp, verb.getFuturEnclitic().getPlural1()));
        verb.getFuturEnclitic().setPlural2(setPronoun(ValladerPronouns.pron_r_2pp, verb.getFuturEnclitic().getPlural2()));
        verb.getFuturEnclitic().setPlural3(setPronoun(ValladerPronouns.pron_r_3pp, verb.getFuturEnclitic().getPlural3()));
    }

    private void addReflexivePronounsVowel() {

        // PRESCHENT
        verb.getPreschent().setSing1(setPronoun(ValladerPronouns.pron_1ps + ValladerPronouns.pron_r_v_1ps, verb.getPreschent().getSing1()));
        verb.getPreschent().setSing2(setPronoun(ValladerPronouns.pron_2ps + ValladerPronouns.pron_r_v_2ps, verb.getPreschent().getSing2()));
        verb.getPreschent().setSing3(setPronoun(ValladerPronouns.pron_3ps + ValladerPronouns.pron_r_v_3ps, verb.getPreschent().getSing3()));
        verb.getPreschent().setPlural1(setPronoun(ValladerPronouns.pron_1pp + ValladerPronouns.pron_r_v_1pp, verb.getPreschent().getPlural1()));
        verb.getPreschent().setPlural2(setPronoun(ValladerPronouns.pron_2pp + ValladerPronouns.pron_r_v_2pp, verb.getPreschent().getPlural2()));
        verb.getPreschent().setPlural3(setPronoun(ValladerPronouns.pron_3pp + ValladerPronouns.pron_r_v_3pp, verb.getPreschent().getPlural3()));

        // IMPERFECT
        verb.getImperfect().setSing1(setPronoun(ValladerPronouns.pron_1ps + ValladerPronouns.pron_r_v_1ps, verb.getImperfect().getSing1()));
        verb.getImperfect().setSing2(setPronoun(ValladerPronouns.pron_2ps + ValladerPronouns.pron_r_v_2ps, verb.getImperfect().getSing2()));
        verb.getImperfect().setSing3(setPronoun(ValladerPronouns.pron_3ps + ValladerPronouns.pron_r_v_3ps, verb.getImperfect().getSing3()));
        verb.getImperfect().setPlural1(setPronoun(ValladerPronouns.pron_1pp + ValladerPronouns.pron_r_v_1pp, verb.getImperfect().getPlural1()));
        verb.getImperfect().setPlural2(setPronoun(ValladerPronouns.pron_2pp + ValladerPronouns.pron_r_v_2pp, verb.getImperfect().getPlural2()));
        verb.getImperfect().setPlural3(setPronoun(ValladerPronouns.pron_3pp + ValladerPronouns.pron_r_v_3pp, verb.getImperfect().getPlural3()));

        // CONJUNCTIV
        verb.getConjunctiv().setSing1(setPronoun(ValladerPronouns.pron_conjunctiv_v + ValladerPronouns.pron_1ps + ValladerPronouns.pron_r_v_1ps, verb.getConjunctiv().getSing1()));
        verb.getConjunctiv().setSing2(setPronoun(ValladerPronouns.pron_conjunctiv_c + ValladerPronouns.pron_2ps + ValladerPronouns.pron_r_v_2ps, verb.getConjunctiv().getSing2()));
        verb.getConjunctiv().setSing3(setPronoun(ValladerPronouns.pron_conjunctiv_v + ValladerPronouns.pron_3ps + ValladerPronouns.pron_r_v_3ps, verb.getConjunctiv().getSing3()));
        verb.getConjunctiv().setPlural1(setPronoun(ValladerPronouns.pron_conjunctiv_c + ValladerPronouns.pron_1pp + ValladerPronouns.pron_r_v_1pp, verb.getConjunctiv().getPlural1()));
        verb.getConjunctiv().setPlural2(setPronoun(ValladerPronouns.pron_conjunctiv_c + ValladerPronouns.pron_2pp + ValladerPronouns.pron_r_v_2pp, verb.getConjunctiv().getPlural2()));
        verb.getConjunctiv().setPlural3(setPronoun(ValladerPronouns.pron_conjunctiv_v + ValladerPronouns.pron_3pp + ValladerPronouns.pron_r_v_3pp, verb.getConjunctiv().getPlural3()));

        // CONJUNCTIV imperfect
        verb.getConjunctivImperfect().setSing1(setPronoun(ValladerPronouns.pron_conjunctiv_v + ValladerPronouns.pron_1ps + ValladerPronouns.pron_r_v_1ps, verb.getConjunctivImperfect().getSing1()));
        verb.getConjunctivImperfect().setSing2(setPronoun(ValladerPronouns.pron_conjunctiv_c + ValladerPronouns.pron_2ps + ValladerPronouns.pron_r_v_2ps, verb.getConjunctivImperfect().getSing2()));
        verb.getConjunctivImperfect().setSing3(setPronoun(ValladerPronouns.pron_conjunctiv_v + ValladerPronouns.pron_3ps + ValladerPronouns.pron_r_v_3ps, verb.getConjunctivImperfect().getSing3()));
        verb.getConjunctivImperfect().setPlural1(setPronoun(ValladerPronouns.pron_conjunctiv_c + ValladerPronouns.pron_1pp + ValladerPronouns.pron_r_v_1pp, verb.getConjunctivImperfect().getPlural1()));
        verb.getConjunctivImperfect().setPlural2(setPronoun(ValladerPronouns.pron_conjunctiv_c + ValladerPronouns.pron_2pp + ValladerPronouns.pron_r_v_2pp, verb.getConjunctivImperfect().getPlural2()));
        verb.getConjunctivImperfect().setPlural3(setPronoun(ValladerPronouns.pron_conjunctiv_v + ValladerPronouns.pron_3pp + ValladerPronouns.pron_r_v_3pp, verb.getConjunctivImperfect().getPlural3()));

        // CUNDIZIONAL
        verb.getCundiziunal().setSing1(setPronoun(ValladerPronouns.pron_1ps + ValladerPronouns.pron_r_v_1ps, verb.getCundiziunal().getSing1()));
        verb.getCundiziunal().setSing2(setPronoun(ValladerPronouns.pron_2ps + ValladerPronouns.pron_r_v_2ps, verb.getCundiziunal().getSing2()));
        verb.getCundiziunal().setSing3(setPronoun(ValladerPronouns.pron_3ps + ValladerPronouns.pron_r_v_3ps, verb.getCundiziunal().getSing3()));
        verb.getCundiziunal().setPlural1(setPronoun(ValladerPronouns.pron_1pp + ValladerPronouns.pron_r_v_1pp, verb.getCundiziunal().getPlural1()));
        verb.getCundiziunal().setPlural2(setPronoun(ValladerPronouns.pron_2pp + ValladerPronouns.pron_r_v_2pp, verb.getCundiziunal().getPlural2()));
        verb.getCundiziunal().setPlural3(setPronoun(ValladerPronouns.pron_3pp + ValladerPronouns.pron_r_v_3pp, verb.getCundiziunal().getPlural3()));

        // IMPERATIV
        verb.getImperativ().setSingular(setPronoun(ValladerPronouns.imperativ_refl_sg_vowel, verb.getImperativ().getSingular(), "!"));
        verb.getImperativ().setPlural(setPronoun(ValladerPronouns.imperativ_refl_pl_vowel, verb.getImperativ().getPlural(), "!"));
        verb.getImperativ().setForm3(setPronoun(ValladerPronouns.imperativ_not + ValladerPronouns.pron_r_v_2ps, verb.getImperativ().getForm3(), "!"));
        verb.getImperativ().setForm4(setPronoun(ValladerPronouns.imperativ_not + ValladerPronouns.pron_r_v_2pp, verb.getImperativ().getForm4(), "!"));
        verb.getImperativ().setForm5(setPronoun(ValladerPronouns.imperativ_polite_sg + ValladerPronouns.pron_r_v_3ps, verb.getImperativ().getForm5(), "!"));
        verb.getImperativ().setForm6(setPronoun(ValladerPronouns.imperativ_polite_pl + ValladerPronouns.pron_r_v_3pp, verb.getImperativ().getForm6(), "!"));

        // PARTICIP_PERFECT
        verb.getParticipPerfect().setMs(verb.getParticipPerfect().getMs());
        verb.getParticipPerfect().setFs(verb.getParticipPerfect().getFs());
        verb.getParticipPerfect().setMp(verb.getParticipPerfect().getMp());
        verb.getParticipPerfect().setFp(verb.getParticipPerfect().getFp());

        // GERUNDIUM
        verb.setGerundium(setPronoun(ValladerPronouns.pron_r_v_3pp, verb.getGerundium()));

        // FUTUR
        verb.getFutur().setSing1(setPronoun(ValladerPronouns.pron_1ps, ValladerPronouns.pron_r_v_1ps + verb.getFutur().getSing1()));
        verb.getFutur().setSing2(setPronoun(ValladerPronouns.pron_2ps, ValladerPronouns.pron_r_v_2ps + verb.getFutur().getSing2()));
        verb.getFutur().setSing3(setPronoun(ValladerPronouns.pron_3ps, ValladerPronouns.pron_r_v_3ps + verb.getFutur().getSing3()));
        verb.getFutur().setPlural1(setPronoun(ValladerPronouns.pron_1pp, ValladerPronouns.pron_r_v_1pp + verb.getFutur().getPlural1()));
        verb.getFutur().setPlural2(setPronoun(ValladerPronouns.pron_2pp, ValladerPronouns.pron_r_v_2pp + verb.getFutur().getPlural2()));
        verb.getFutur().setPlural3(setPronoun(ValladerPronouns.pron_3pp, ValladerPronouns.pron_r_v_3pp + verb.getFutur().getPlural3()));

        // ENCLITIC PRESCHENT
        verb.getPreschentEnclitic().setSing1(setPronoun(ValladerPronouns.pron_r_v_1ps, verb.getPreschentEnclitic().getSing1()));
        verb.getPreschentEnclitic().setSing2(setPronoun(ValladerPronouns.pron_r_v_2ps, verb.getPreschentEnclitic().getSing2()));
        verb.getPreschentEnclitic().setSing3m(setPronoun(ValladerPronouns.pron_r_v_3ps, verb.getPreschentEnclitic().getSing3m()));
        verb.getPreschentEnclitic().setSing3f(setPronoun(ValladerPronouns.pron_r_v_3ps, verb.getPreschentEnclitic().getSing3f()));
        verb.getPreschentEnclitic().setPlural1(setPronoun(ValladerPronouns.pron_r_v_1pp, verb.getPreschentEnclitic().getPlural1()));
        verb.getPreschentEnclitic().setPlural2(setPronoun(ValladerPronouns.pron_r_v_2pp, verb.getPreschentEnclitic().getPlural2()));
        verb.getPreschentEnclitic().setPlural3(setPronoun(ValladerPronouns.pron_r_v_3pp, verb.getPreschentEnclitic().getPlural3()));

        // ENCLITIC IMPERFECT
        verb.getImperfectEnclitic().setSing1(setPronoun(ValladerPronouns.pron_r_v_1ps, verb.getImperfectEnclitic().getSing1()));
        verb.getImperfectEnclitic().setSing2(setPronoun(ValladerPronouns.pron_r_v_2ps, verb.getImperfectEnclitic().getSing2()));
        verb.getImperfectEnclitic().setSing3m(setPronoun(ValladerPronouns.pron_r_v_3ps, verb.getImperfectEnclitic().getSing3m()));
        verb.getImperfectEnclitic().setSing3f(setPronoun(ValladerPronouns.pron_r_v_3ps, verb.getImperfectEnclitic().getSing3f()));
        verb.getImperfectEnclitic().setPlural1(setPronoun(ValladerPronouns.pron_r_v_1pp, verb.getImperfectEnclitic().getPlural1()));
        verb.getImperfectEnclitic().setPlural2(setPronoun(ValladerPronouns.pron_r_v_2pp, verb.getImperfectEnclitic().getPlural2()));
        verb.getImperfectEnclitic().setPlural3(setPronoun(ValladerPronouns.pron_r_v_3pp, verb.getImperfectEnclitic().getPlural3()));

        // ENCLITIC CUNDIZIONAL
        verb.getCundiziunalEnclitic().setSing1(setPronoun(ValladerPronouns.pron_r_v_1ps, verb.getCundiziunalEnclitic().getSing1()));
        verb.getCundiziunalEnclitic().setSing2(setPronoun(ValladerPronouns.pron_r_v_2ps, verb.getCundiziunalEnclitic().getSing2()));
        verb.getCundiziunalEnclitic().setSing3m(setPronoun(ValladerPronouns.pron_r_v_3ps, verb.getCundiziunalEnclitic().getSing3m()));
        verb.getCundiziunalEnclitic().setSing3f(setPronoun(ValladerPronouns.pron_r_v_3ps, verb.getCundiziunalEnclitic().getSing3f()));
        verb.getCundiziunalEnclitic().setPlural1(setPronoun(ValladerPronouns.pron_r_v_1pp, verb.getCundiziunalEnclitic().getPlural1()));
        verb.getCundiziunalEnclitic().setPlural2(setPronoun(ValladerPronouns.pron_r_v_2pp, verb.getCundiziunalEnclitic().getPlural2()));
        verb.getCundiziunalEnclitic().setPlural3(setPronoun(ValladerPronouns.pron_r_v_3pp, verb.getCundiziunalEnclitic().getPlural3()));

        // ENCLITIC FUTUR
        verb.getFuturEnclitic().setSing1(setPronoun(ValladerPronouns.pron_r_v_1ps, verb.getFuturEnclitic().getSing1()));
        verb.getFuturEnclitic().setSing2(setPronoun(ValladerPronouns.pron_r_v_2ps, verb.getFuturEnclitic().getSing2()));
        verb.getFuturEnclitic().setSing3m(setPronoun(ValladerPronouns.pron_r_v_3ps, verb.getFuturEnclitic().getSing3m()));
        verb.getFuturEnclitic().setSing3f(setPronoun(ValladerPronouns.pron_r_v_3ps, verb.getFuturEnclitic().getSing3f()));
        verb.getFuturEnclitic().setPlural1(setPronoun(ValladerPronouns.pron_r_v_1pp, verb.getFuturEnclitic().getPlural1()));
        verb.getFuturEnclitic().setPlural2(setPronoun(ValladerPronouns.pron_r_v_2pp, verb.getFuturEnclitic().getPlural2()));
        verb.getFuturEnclitic().setPlural3(setPronoun(ValladerPronouns.pron_r_v_3pp, verb.getFuturEnclitic().getPlural3()));
    }

    private void addStandardPronouns() {

        // PRESCHENT
        verb.getPreschent().setSing1(setPronoun(ValladerPronouns.pron_1ps, verb.getPreschent().getSing1()));
        verb.getPreschent().setSing2(setPronoun(ValladerPronouns.pron_2ps, verb.getPreschent().getSing2()));
        verb.getPreschent().setSing3(setPronoun(ValladerPronouns.pron_3ps, verb.getPreschent().getSing3()));
        verb.getPreschent().setPlural1(setPronoun(ValladerPronouns.pron_1pp, verb.getPreschent().getPlural1()));
        verb.getPreschent().setPlural2(setPronoun(ValladerPronouns.pron_2pp, verb.getPreschent().getPlural2()));
        verb.getPreschent().setPlural3(setPronoun(ValladerPronouns.pron_3pp, verb.getPreschent().getPlural3()));

        // IMPERFECT
        verb.getImperfect().setSing1(setPronoun(ValladerPronouns.pron_1ps, verb.getImperfect().getSing1()));
        verb.getImperfect().setSing2(setPronoun(ValladerPronouns.pron_2ps, verb.getImperfect().getSing2()));
        verb.getImperfect().setSing3(setPronoun(ValladerPronouns.pron_3ps, verb.getImperfect().getSing3()));
        verb.getImperfect().setPlural1(setPronoun(ValladerPronouns.pron_1pp, verb.getImperfect().getPlural1()));
        verb.getImperfect().setPlural2(setPronoun(ValladerPronouns.pron_2pp, verb.getImperfect().getPlural2()));
        verb.getImperfect().setPlural3(setPronoun(ValladerPronouns.pron_3pp, verb.getImperfect().getPlural3()));

        // CONJUNCTIV
        verb.getConjunctiv().setSing1(setPronoun(ValladerPronouns.pron_conjunctiv_v + ValladerPronouns.pron_1ps, verb.getConjunctiv().getSing1()));
        verb.getConjunctiv().setSing2(setPronoun(ValladerPronouns.pron_conjunctiv_c + ValladerPronouns.pron_2ps, verb.getConjunctiv().getSing2()));
        verb.getConjunctiv().setSing3(setPronoun(ValladerPronouns.pron_conjunctiv_v + ValladerPronouns.pron_3ps, verb.getConjunctiv().getSing3()));
        verb.getConjunctiv().setPlural1(setPronoun(ValladerPronouns.pron_conjunctiv_c + ValladerPronouns.pron_1pp, verb.getConjunctiv().getPlural1()));
        verb.getConjunctiv().setPlural2(setPronoun(ValladerPronouns.pron_conjunctiv_c + ValladerPronouns.pron_2pp, verb.getConjunctiv().getPlural2()));
        verb.getConjunctiv().setPlural3(setPronoun(ValladerPronouns.pron_conjunctiv_v + ValladerPronouns.pron_3pp, verb.getConjunctiv().getPlural3()));

        // CONJUNCTIV imperfect
        verb.getConjunctivImperfect().setSing1(setPronoun(ValladerPronouns.pron_conjunctiv_v + ValladerPronouns.pron_1ps, verb.getConjunctivImperfect().getSing1()));
        verb.getConjunctivImperfect().setSing2(setPronoun(ValladerPronouns.pron_conjunctiv_c + ValladerPronouns.pron_2ps, verb.getConjunctivImperfect().getSing2()));
        verb.getConjunctivImperfect().setSing3(setPronoun(ValladerPronouns.pron_conjunctiv_v + ValladerPronouns.pron_3ps, verb.getConjunctivImperfect().getSing3()));
        verb.getConjunctivImperfect().setPlural1(setPronoun(ValladerPronouns.pron_conjunctiv_c + ValladerPronouns.pron_1pp, verb.getConjunctivImperfect().getPlural1()));
        verb.getConjunctivImperfect().setPlural2(setPronoun(ValladerPronouns.pron_conjunctiv_c + ValladerPronouns.pron_2pp, verb.getConjunctivImperfect().getPlural2()));
        verb.getConjunctivImperfect().setPlural3(setPronoun(ValladerPronouns.pron_conjunctiv_v + ValladerPronouns.pron_3pp, verb.getConjunctivImperfect().getPlural3()));

        // CUNDIZIONAL
        verb.getCundiziunal().setSing1(setPronoun(ValladerPronouns.pron_1ps, verb.getCundiziunal().getSing1()));
        verb.getCundiziunal().setSing2(setPronoun(ValladerPronouns.pron_2ps, verb.getCundiziunal().getSing2()));
        verb.getCundiziunal().setSing3(setPronoun(ValladerPronouns.pron_3ps, verb.getCundiziunal().getSing3()));
        verb.getCundiziunal().setPlural1(setPronoun(ValladerPronouns.pron_1pp, verb.getCundiziunal().getPlural1()));
        verb.getCundiziunal().setPlural2(setPronoun(ValladerPronouns.pron_2pp, verb.getCundiziunal().getPlural2()));
        verb.getCundiziunal().setPlural3(setPronoun(ValladerPronouns.pron_3pp, verb.getCundiziunal().getPlural3()));

        // IMPERATIV
        verb.getImperativ().setSingular(setPronoun("", verb.getImperativ().getSingular(), "!"));
        verb.getImperativ().setPlural(setPronoun("", verb.getImperativ().getPlural(), "!"));
        if (startsWithVowel(verb.getImperativ().getForm3())) {
            verb.getImperativ().setForm3(setPronoun(ValladerPronouns.imperativ_not_vowel, verb.getImperativ().getForm3(), "!"));
            verb.getImperativ().setForm4(setPronoun(ValladerPronouns.imperativ_not_vowel, verb.getImperativ().getForm4(), "!"));
        } else {
            verb.getImperativ().setForm3(setPronoun(ValladerPronouns.imperativ_not, verb.getImperativ().getForm3(), "!"));
            verb.getImperativ().setForm4(setPronoun(ValladerPronouns.imperativ_not, verb.getImperativ().getForm4(), "!"));
        }
        verb.getImperativ().setForm5(setPronoun(ValladerPronouns.imperativ_polite_sg, verb.getImperativ().getForm5(), "!"));
        verb.getImperativ().setForm6(setPronoun(ValladerPronouns.imperativ_polite_pl, verb.getImperativ().getForm6(), "!"));

        // PARTICIP_PERFECT
        verb.getParticipPerfect().setMs(verb.getParticipPerfect().getMs());
        verb.getParticipPerfect().setFs(verb.getParticipPerfect().getFs());
        verb.getParticipPerfect().setMp(verb.getParticipPerfect().getMp());
        verb.getParticipPerfect().setFp(verb.getParticipPerfect().getFp());

        // GERUNDIUM
        verb.setGerundium(verb.getGerundium());

        // FUTUR
        verb.getFutur().setSing1(setPronoun(ValladerPronouns.pron_1ps, verb.getFutur().getSing1()));
        verb.getFutur().setSing2(setPronoun(ValladerPronouns.pron_2ps, verb.getFutur().getSing2()));
        verb.getFutur().setSing3(setPronoun(ValladerPronouns.pron_3ps, verb.getFutur().getSing3()));
        verb.getFutur().setPlural1(setPronoun(ValladerPronouns.pron_1pp, verb.getFutur().getPlural1()));
        verb.getFutur().setPlural2(setPronoun(ValladerPronouns.pron_2pp, verb.getFutur().getPlural2()));
        verb.getFutur().setPlural3(setPronoun(ValladerPronouns.pron_3pp, verb.getFutur().getPlural3()));
    }

    // special case for 'vo' pronoun
    protected String setPronoun(String pronoun, String forms, String suffix) {
        String[] singleForms = forms.split("\\R");
        for (int i = 0; i < singleForms.length; i++) {

            boolean enclosedInBrackets = false;
            if (singleForms[i].charAt(0) == '(' && singleForms[i].charAt(singleForms[i].length()-1) == ')') {
                enclosedInBrackets = true;
                singleForms[i] = singleForms[i].replace("(", "").replace(")", "");
            }

            // 'vus' u 'vus as'
            if (pronoun.startsWith(ValladerPronouns.pron_2pp) && singleForms[i].endsWith("ivat") && singleForms.length > 1) {
                singleForms[i] = pronoun.replace(ValladerPronouns.pron_2pp, ValladerPronouns.pron_2pp_alt) + singleForms[i] + suffix;
            } else {
                singleForms[i] = pronoun + singleForms[i] + suffix;
            }

            if (enclosedInBrackets) {
                singleForms[i] = "(" + singleForms[i] + ")";
            }
        }
        return String.join("\n", singleForms);
    }
}
