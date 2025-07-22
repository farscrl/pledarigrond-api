package ch.pledarigrond.inflection.generation.verb.puter;

import ch.pledarigrond.common.data.dictionary.inflection.InflectionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.common.data.dictionary.inflection.VerbDto;
import ch.pledarigrond.common.util.PronunciationNormalizer;
import ch.pledarigrond.inflection.generation.verb.LanguageConjugation;
import ch.pledarigrond.inflection.model.InflectionSubType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PuterConjugation extends LanguageConjugation {

    private static final Logger logger = LoggerFactory.getLogger(PuterConjugation.class);

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

        if (conjugationClass.equals("1a") || conjugationClass.equals("4a")) {
            modRoot = root + "esch";
        }

        if (infinitiv.endsWith("ger")) {
            modRoot = infinitiv.substring(0, infinitiv.length() - 2) + "i";
        }

        if (infinitiv.endsWith("glier")) {
            modRoot = infinitiv.substring(0, infinitiv.length() - 2);
        }

        if (infinitiv.endsWith("cker")) {
            modRoot = infinitiv.substring(0, infinitiv.length() - 3) + "c";
        }

        if (modRoot == null) {
            modRoot = root;
        }

        InflectionSubType subType = PuterConjugationClasses.getConjugationClass(conjugationClass);
        if (subType == null) {
            throw new RuntimeException(conjugationClass + " is not a valid conjugation class.");
        } else if (ending == null) {
            throw new RuntimeException(infinitivForm + "  is not a valid verb. Please enter a verb in its infinitive form.");
        }

        verbClass = subType;
        inflection.setInflectionSubtype(verbClass.id);
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


        if (lastTwo.equals("er")) {
            if (flex != null && flex.contains("-esch")) {
                return generateConjugation("1a", baseForm);
            }

            return generateConjugation("1", baseForm);
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
                root = infinitivForm;
                return infinitivForm;
            }

            if (infinitivForm.length() < 3) {
                throw new RuntimeException("'" + infinitivForm + "' is not a valid verb. Please enter a verb in its infinitive form.");
            }

            root = infinitivForm;
            infinitivForm = extractInfinitiv(infinitivForm);
        }
        return infinitivForm;
    }

    private String extractInfinitiv(String infinitivForm) {
        String l2 = infinitivForm.substring(infinitivForm.length() - 2);
        String l3 = infinitivForm.substring(infinitivForm.length() - 3);

        switch (l2) {
            case "er":
            case "ir":
                if (!l3.equals("air")) {
                    ending = l2;
                    return infinitivForm.substring(0, infinitivForm.length() - 2);
                }
                break;
        }

        if (l3.equals("air")) {
            ending = l3;
            infinitivForm = infinitivForm.substring(0, infinitivForm.length() - 3);
            return infinitivForm;
        }

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
        verb.setReflexive(isReflexive);

        setPreschent();
        setImperfect();
        setConjunctiv();
        setCundizional();
        setConjunctiv2(); // ATTENTION: has to be after cundiziunal, as it uses the forms from cundziunal
        setParticipPerfect();
        setImperativ();
        setGerundium();
        setFutur();
        setFuturDubitativ();

        setPreschentEnclitic();
        setImperfectEnclitic();
        setCundizionalEnclitic();
        setFuturEnclitic();
        setFuturDubitativEnclitic();
    }

    private void setPreschent() {
        VerbDto.PersonalVerbDto preschent = new VerbDto.PersonalVerbDto();

        switch (verbClass.id) {
            case "1":
            case "1a":
            case "2":
            case "3":
                if (infinitiv.endsWith("ger") || infinitiv.endsWith("glier") || infinitiv.endsWith("cker")) {
                    preschent.setSing1(modRoot.substring(0, modRoot.length() - 1));
                } else if (
                        infinitiv.endsWith("ffer") ||
                        infinitiv.endsWith("ller") ||
                        infinitiv.endsWith("mmer") ||
                        infinitiv.endsWith("nner") ||
                        infinitiv.endsWith("pper") ||
                        infinitiv.endsWith("rrer") ||
                        infinitiv.endsWith("tter") ||
                        infinitiv.endsWith("zzer")
                ) {
                    preschent.setSing1(modRoot.substring(0, modRoot.length() - 1));
                } else {
                    preschent.setSing1(modRoot);
                }
                preschent.setSing2(modRoot + "ast");
                preschent.setSing3(modRoot + "a");

                if (infinitiv.endsWith("ger") || infinitiv.endsWith("glier") || infinitiv.endsWith("cker")) {
                    preschent.setPlural1(modRoot + "ains");
                    preschent.setPlural2(modRoot + "ais");
                } else {
                    preschent.setPlural1(root + "ains");
                    preschent.setPlural2(root + "ais");
                }
                preschent.setPlural3(modRoot + "an");
                break;


            case "4":
            case "4a":
                preschent.setSing1(modRoot);
                preschent.setSing2(modRoot + "ast");
                preschent.setSing3(modRoot + "a");
                preschent.setPlural1(root + "ins");
                preschent.setPlural2(root + "is");
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
                if (infinitiv.endsWith("ger")) {
                    imperfect.setSing1(modRoot + "aiva");
                    imperfect.setSing2(modRoot + "aivast");
                    imperfect.setSing3(modRoot + "aiva");
                    imperfect.setPlural1(modRoot + "aivans");
                    imperfect.setPlural2(modRoot + "aivas");
                    imperfect.setPlural3(modRoot + "aivan");
                } else {
                    imperfect.setSing1(root + "aiva");
                    imperfect.setSing2(root + "aivast");
                    imperfect.setSing3(root + "aiva");
                    imperfect.setPlural1(root + "aivans");
                    imperfect.setPlural2(root + "aivas");
                    imperfect.setPlural3(root + "aivan");
                }
                break;

            case "4":
                imperfect.setSing1(root + "iva");
                imperfect.setSing2(root + "ivast");
                imperfect.setSing3(root + "iva");
                imperfect.setPlural1(root + "ivans");
                imperfect.setPlural2(root + "ivas");
                imperfect.setPlural3(root + "ivan");
                break;
        }
        verb.setImperfect(imperfect);
    }

    private void setConjunctiv() {
        VerbDto.PersonalVerbDto conjunctiv = new VerbDto.PersonalVerbDto();

        conjunctiv.setSing1(modRoot + "a");
        conjunctiv.setSing2(modRoot + "ast");
        conjunctiv.setSing3(modRoot + "a");
        conjunctiv.setPlural1(modRoot + "ans");
        conjunctiv.setPlural2(modRoot + "as");
        conjunctiv.setPlural3(modRoot + "an");

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
                cundizional.setPlural1(root + "issans");
                cundizional.setPlural2(root + "issas");
                cundizional.setPlural3(root + "issan");
                break;

            default:
                cundizional.setSing1(root + "ess");
                cundizional.setSing2(root + "essast");
                cundizional.setSing3(root + "ess");
                cundizional.setPlural1(root + "essans");
                cundizional.setPlural2(root + "essas");
                cundizional.setPlural3(root + "essan");
                break;
        }

        verb.setCundiziunal(cundizional);
    }

    private void setParticipPerfect() {
        VerbDto.ParticipPerfectDto participperfect = new VerbDto.ParticipPerfectDto();

        switch (verbClass.id.substring(0, 1)) {
            case "1":
                participperfect.setMs(root + "o");
                participperfect.setMp(root + "os");
                participperfect.setFs(root + "eda");
                participperfect.setFp(root + "edas");
                break;

            default:
                participperfect.setMs(root + "ieu");
                participperfect.setMp(root + "ieus");
                participperfect.setFs(root + "ida");
                participperfect.setFp(root + "idas");
                break;
        }
        
        verb.setParticipPerfect(participperfect);
    }

    private void setGerundium() {

        switch (verbClass.id.substring(0, 1)) {
            case "4":
                verb.setGerundium(root + "ind");
                break;
            default:
                verb.setGerundium(root + "and");
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
                imperativ.setForm4(root + "i");
                imperativ.setForm5(modRoot + "a");
                imperativ.setForm6(modRoot + "an");
                break;


            default:
                imperativ.setSingular(modRoot + "a");
                imperativ.setPlural(root + "è");
                imperativ.setForm3(infinitiv);
                imperativ.setForm4(root + "è");
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
                futur.setSing1(root + "iro");
                futur.setSing2(root + "irost");
                futur.setSing3(root + "iro");
                futur.setPlural1(root + "irons");
                futur.setPlural2(root + "iros");
                futur.setPlural3(root + "iron");
                break;

            default:
                if (infinitiv.endsWith("ger")) {
                    futur.setSing1(modRoot + "aro");
                    futur.setSing2(modRoot + "arost");
                    futur.setSing3(modRoot + "aro");
                    futur.setPlural1(modRoot + "arons");
                    futur.setPlural2(modRoot + "aros");
                    futur.setPlural3(modRoot + "aron");
                } else {
                    futur.setSing1(root + "aro");
                    futur.setSing2(root + "arost");
                    futur.setSing3(root + "aro");
                    futur.setPlural1(root + "arons");
                    futur.setPlural2(root + "aros");
                    futur.setPlural3(root + "aron");
                }
                break;
        }
        
        verb.setFutur(futur);
    }

    private void setFuturDubitativ() {
        VerbDto.PersonalVerbDto futurdubitativ = new VerbDto.PersonalVerbDto();
        
        switch (verbClass.id.substring(0, 1)) {
            case "4":
                futurdubitativ.setSing1(root + "iregia");
                futurdubitativ.setSing2(root + "iregiast");
                futurdubitativ.setSing3(root + "iregia");
                futurdubitativ.setPlural1(root + "iregians");
                futurdubitativ.setPlural2(root + "iregias");
                futurdubitativ.setPlural3(root + "iregian");
                break;

            default:
                if (infinitiv.endsWith("ger")) {
                    futurdubitativ.setSing1(modRoot + "aregia");
                    futurdubitativ.setSing2(modRoot + "aregiast");
                    futurdubitativ.setSing3(modRoot + "aregia");
                    futurdubitativ.setPlural1(modRoot + "aregians");
                    futurdubitativ.setPlural2(modRoot + "aregias");
                    futurdubitativ.setPlural3(modRoot + "aregian");
                } else {
                    futurdubitativ.setSing1(root + "aregia");
                    futurdubitativ.setSing2(root + "aregiast");
                    futurdubitativ.setSing3(root + "aregia");
                    futurdubitativ.setPlural1(root + "aregians");
                    futurdubitativ.setPlural2(root + "aregias");
                    futurdubitativ.setPlural3(root + "aregian");
                }
                break;
        }
        
        verb.setFuturDubitativ(futurdubitativ);
    }


    private void setPreschentEnclitic() {
        VerbDto.PersonalVerbEncliticDto preschentEnclitic = new VerbDto.PersonalVerbEncliticDto();
        
        if (infinitiv.endsWith("cker")) {
            preschentEnclitic.setSing1(verb.getPreschent().getSing1() + "ki");
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
            preschentEnclitic.setSing1(verb.getPreschent().getSing1() + consonant + "i");
        } else {
            preschentEnclitic.setSing1(verb.getPreschent().getSing1() + "i");
        }
        preschentEnclitic.setSing2(verb.getPreschent().getSing2());
        preschentEnclitic.setSing3m(verb.getPreschent().getSing3() + "'l");
        if (infinitiv.endsWith("ger") ||
                infinitiv.endsWith("glier")) {
            preschentEnclitic.setSing3f(verb.getPreschent().getSing3().substring(0, verb.getPreschent().getSing3().length() - 2) + "'la");
        } else if (infinitiv.endsWith("ffar") ||
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
                infinitiv.endsWith("zzer") ||
                infinitiv.endsWith("cker")) {
            preschentEnclitic.setSing3f(verb.getPreschent().getSing3().substring(0, verb.getPreschent().getSing3().length() - 1) + "'la");
        } else {
            preschentEnclitic.setSing3f(verb.getPreschent().getSing3().substring(0, verb.getPreschent().getSing3().length() - 1) + "'la");
        }
        preschentEnclitic.setPlural1(verb.getPreschent().getPlural1() + "a");
        preschentEnclitic.setPlural2(verb.getPreschent().getPlural2());
        preschentEnclitic.setPlural3(verb.getPreschent().getPlural3() + "e");
        
        verb.setPreschentEnclitic(preschentEnclitic);
    }

    private void setImperfectEnclitic() {
        VerbDto.PersonalVerbEncliticDto imperfectEnclitic = new VerbDto.PersonalVerbEncliticDto();
        
        imperfectEnclitic.setSing1(verb.getImperfect().getSing1().substring(0, verb.getImperfect().getSing1().length() - 1) + "i");
        imperfectEnclitic.setSing2(verb.getImperfect().getSing2());
        imperfectEnclitic.setSing3m(verb.getImperfect().getSing3() + "'l");
        imperfectEnclitic.setSing3f(verb.getImperfect().getSing3().substring(0, verb.getImperfect().getSing3().length() - 1) + "'la");
        imperfectEnclitic.setPlural1(verb.getImperfect().getPlural1());
        imperfectEnclitic.setPlural2(verb.getImperfect().getPlural2());
        imperfectEnclitic.setPlural3(verb.getImperfect().getPlural3() + "e");
        
        verb.setImperfectEnclitic(imperfectEnclitic);
    }

    private void setCundizionalEnclitic() {
        VerbDto.PersonalVerbEncliticDto cundizionalEnclitic = new VerbDto.PersonalVerbEncliticDto();
        
        cundizionalEnclitic.setSing1(verb.getCundiziunal().getSing1() + "i");
        cundizionalEnclitic.setSing2(verb.getCundiziunal().getSing2());
        cundizionalEnclitic.setSing3m(verb.getCundiziunal().getSing3() + "a'l");
        cundizionalEnclitic.setSing3f(verb.getCundiziunal().getSing3() + "'la");
        cundizionalEnclitic.setPlural1(verb.getCundiziunal().getPlural1());
        cundizionalEnclitic.setPlural2(verb.getCundiziunal().getPlural2());
        cundizionalEnclitic.setPlural3(verb.getCundiziunal().getPlural3() + "e");
        
        verb.setCundizionalEnclitic(cundizionalEnclitic);
    }

    private void setFuturEnclitic() {
        VerbDto.PersonalVerbEncliticDto futurEnclitic = new VerbDto.PersonalVerbEncliticDto();
        
        futurEnclitic.setSing1(verb.getFutur().getSing1() + " eau");
        futurEnclitic.setSing2(verb.getFutur().getSing2());
        futurEnclitic.setSing3m(verb.getFutur().getSing3() + "'l");
        futurEnclitic.setSing3f(verb.getFutur().getSing3() + "'la");
        futurEnclitic.setPlural1(verb.getFutur().getPlural1() + "a");
        futurEnclitic.setPlural2(verb.getFutur().getPlural2());
        futurEnclitic.setPlural3(verb.getFutur().getPlural3() + "e");
        
        verb.setFuturEnclitic(futurEnclitic);
    }

    private void setFuturDubitativEnclitic() {
        VerbDto.PersonalVerbEncliticDto futurdubitativEnclitic = new VerbDto.PersonalVerbEncliticDto();
        
        futurdubitativEnclitic.setSing1(verb.getFuturDubitativ().getSing1().substring(0, verb.getFuturDubitativ().getSing1().length() - 1));
        futurdubitativEnclitic.setSing2(verb.getFuturDubitativ().getSing2());
        futurdubitativEnclitic.setSing3m(verb.getFuturDubitativ().getSing3() + "'l");
        futurdubitativEnclitic.setSing3f(verb.getFuturDubitativ().getSing3().substring(0, verb.getFuturDubitativ().getSing3().length() - 2) + "'la");
        futurdubitativEnclitic.setPlural1(verb.getFuturDubitativ().getPlural1());
        futurdubitativEnclitic.setPlural2(verb.getFuturDubitativ().getPlural2());
        futurdubitativEnclitic.setPlural3(verb.getFuturDubitativ().getPlural3() + "e");
        
        verb.setFuturDubitativEnclitic(futurdubitativEnclitic);
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
        verb.getPreschent().setSing1(setPronoun(PuterPronouns.pron_1ps + PuterPronouns.pron_r_1ps, verb.getPreschent().getSing1()));
        verb.getPreschent().setSing2(setPronoun(PuterPronouns.pron_2ps + PuterPronouns.pron_r_2ps, verb.getPreschent().getSing2()));
        verb.getPreschent().setSing3(setPronoun(PuterPronouns.pron_3ps + PuterPronouns.pron_r_3ps, verb.getPreschent().getSing3()));
        verb.getPreschent().setPlural1(setPronoun(PuterPronouns.pron_1pp + PuterPronouns.pron_r_1pp, verb.getPreschent().getPlural1()));
        verb.getPreschent().setPlural2(setPronoun(PuterPronouns.pron_2pp + PuterPronouns.pron_r_2pp,verb.getPreschent().getPlural2()));
        verb.getPreschent().setPlural3(setPronoun(PuterPronouns.pron_3pp + PuterPronouns.pron_r_3pp, verb.getPreschent().getPlural3()));

        // IMPERFECT
        verb.getImperfect().setSing1(setPronoun(PuterPronouns.pron_1ps + PuterPronouns.pron_r_1ps, verb.getImperfect().getSing1()));
        verb.getImperfect().setSing2(setPronoun(PuterPronouns.pron_2ps + PuterPronouns.pron_r_2ps, verb.getImperfect().getSing2()));
        verb.getImperfect().setSing3(setPronoun(PuterPronouns.pron_3ps + PuterPronouns.pron_r_3ps, verb.getImperfect().getSing3()));
        verb.getImperfect().setPlural1(setPronoun(PuterPronouns.pron_1pp + PuterPronouns.pron_r_1pp, verb.getImperfect().getPlural1()));
        verb.getImperfect().setPlural2(setPronoun(PuterPronouns.pron_2pp + PuterPronouns.pron_r_2pp, verb.getImperfect().getPlural2()));
        verb.getImperfect().setPlural3(setPronoun(PuterPronouns.pron_3pp + PuterPronouns.pron_r_3pp, verb.getImperfect().getPlural3()));

        // CONJUNCTIV
        verb.getConjunctiv().setSing1(setPronoun(PuterPronouns.pron_conjunctiv_v + PuterPronouns.pron_1ps + PuterPronouns.pron_r_1ps, verb.getConjunctiv().getSing1()));
        verb.getConjunctiv().setSing2(setPronoun(PuterPronouns.pron_conjunctiv_c + PuterPronouns.pron_2ps + PuterPronouns.pron_r_2ps, verb.getConjunctiv().getSing2()));
        verb.getConjunctiv().setSing3(setPronoun(PuterPronouns.pron_conjunctiv_v + PuterPronouns.pron_3ps + PuterPronouns.pron_r_3ps, verb.getConjunctiv().getSing3()));
        verb.getConjunctiv().setPlural1(setPronoun(PuterPronouns.pron_conjunctiv_c + PuterPronouns.pron_1pp + PuterPronouns.pron_r_1pp, verb.getConjunctiv().getPlural1()));
        verb.getConjunctiv().setPlural2(setPronoun(PuterPronouns.pron_conjunctiv_c + PuterPronouns.pron_2pp + PuterPronouns.pron_r_2pp, verb.getConjunctiv().getPlural2()));
        verb.getConjunctiv().setPlural3(setPronoun(PuterPronouns.pron_conjunctiv_v + PuterPronouns.pron_3pp + PuterPronouns.pron_r_3pp, verb.getConjunctiv().getPlural3()));

        // CONJUNCTIV imperfect
        verb.getConjunctivImperfect().setSing1(setPronoun(PuterPronouns.pron_conjunctiv_v + PuterPronouns.pron_1ps + PuterPronouns.pron_r_1ps, verb.getConjunctivImperfect().getSing1()));
        verb.getConjunctivImperfect().setSing2(setPronoun(PuterPronouns.pron_conjunctiv_c + PuterPronouns.pron_2ps + PuterPronouns.pron_r_2ps, verb.getConjunctivImperfect().getSing2()));
        verb.getConjunctivImperfect().setSing3(setPronoun(PuterPronouns.pron_conjunctiv_v + PuterPronouns.pron_3ps + PuterPronouns.pron_r_3ps, verb.getConjunctivImperfect().getSing3()));
        verb.getConjunctivImperfect().setPlural1(setPronoun(PuterPronouns.pron_conjunctiv_c + PuterPronouns.pron_1pp + PuterPronouns.pron_r_1pp, verb.getConjunctivImperfect().getPlural1()));
        verb.getConjunctivImperfect().setPlural2(setPronoun(PuterPronouns.pron_conjunctiv_c + PuterPronouns.pron_2pp + PuterPronouns.pron_r_2pp, verb.getConjunctivImperfect().getPlural2()));
        verb.getConjunctivImperfect().setPlural3(setPronoun(PuterPronouns.pron_conjunctiv_v + PuterPronouns.pron_3pp + PuterPronouns.pron_r_3pp, verb.getConjunctivImperfect().getPlural3()));

        // CUNDIZIONAL
        verb.getCundiziunal().setSing1(setPronoun(PuterPronouns.pron_1ps + PuterPronouns.pron_r_1ps, verb.getCundiziunal().getSing1()));
        verb.getCundiziunal().setSing2(setPronoun(PuterPronouns.pron_2ps + PuterPronouns.pron_r_2ps, verb.getCundiziunal().getSing2()));
        verb.getCundiziunal().setSing3(setPronoun(PuterPronouns.pron_3ps + PuterPronouns.pron_r_3ps, verb.getCundiziunal().getSing3()));
        verb.getCundiziunal().setPlural1(setPronoun(PuterPronouns.pron_1pp + PuterPronouns.pron_r_1pp, verb.getCundiziunal().getPlural1()));
        verb.getCundiziunal().setPlural2(setPronoun(PuterPronouns.pron_2pp + PuterPronouns.pron_r_2pp, verb.getCundiziunal().getPlural2()));
        verb.getCundiziunal().setPlural3(setPronoun(PuterPronouns.pron_3pp + PuterPronouns.pron_r_3pp, verb.getCundiziunal().getPlural3()));

        // IMPERATIV
        verb.getImperativ().setSingular(setPronoun(verb.getImperativ().getSingular(), PuterPronouns.imperativ_refl_sg, "!"));
        verb.getImperativ().setPlural(setPronoun(verb.getImperativ().getPlural(), PuterPronouns.imperativ_refl_pl, "!"));
        verb.getImperativ().setForm3(setPronoun(PuterPronouns.imperativ_not_refl_sg, verb.getImperativ().getForm3(), "!"));
        verb.getImperativ().setForm4(setPronoun(PuterPronouns.imperativ_not_refl_pl, verb.getImperativ().getForm4(), "!"));
        verb.getImperativ().setForm5(setPronoun(PuterPronouns.imperativ_polite_sg, PuterPronouns.pron_r_3ps + verb.getImperativ().getForm5(), "!"));
        verb.getImperativ().setForm6(setPronoun(PuterPronouns.imperativ_polite_pl, PuterPronouns.pron_r_3pp + verb.getImperativ().getForm6(), "!"));

        // PARTICIP_PERFECT
        verb.getParticipPerfect().setMs(verb.getParticipPerfect().getMs());
        verb.getParticipPerfect().setFs(verb.getParticipPerfect().getFs());
        verb.getParticipPerfect().setMp(verb.getParticipPerfect().getMp());
        verb.getParticipPerfect().setFp(verb.getParticipPerfect().getFp());

        // GERUNDIUM
        verb.setGerundium(setPronoun(PuterPronouns.pron_r_2pp, verb.getGerundium()));

        // FUTUR
        verb.getFutur().setSing1(setPronoun(PuterPronouns.pron_1ps + PuterPronouns.pron_r_1ps, verb.getFutur().getSing1()));
        verb.getFutur().setSing2(setPronoun(PuterPronouns.pron_2ps + PuterPronouns.pron_r_2ps, verb.getFutur().getSing2()));
        verb.getFutur().setSing3(setPronoun(PuterPronouns.pron_3ps + PuterPronouns.pron_r_3ps, verb.getFutur().getSing3()));
        verb.getFutur().setPlural1(setPronoun(PuterPronouns.pron_1pp + PuterPronouns.pron_r_1pp, verb.getFutur().getPlural1()));
        verb.getFutur().setPlural2(setPronoun(PuterPronouns.pron_2pp + PuterPronouns.pron_r_2pp, verb.getFutur().getPlural2()));
        verb.getFutur().setPlural3(setPronoun(PuterPronouns.pron_3pp + PuterPronouns.pron_r_3pp, verb.getFutur().getPlural3()));

        // FUTUR DUBITATIV
        verb.getFuturDubitativ().setSing1(setPronoun(PuterPronouns.pron_1ps + PuterPronouns.pron_r_1ps, verb.getFuturDubitativ().getSing1()));
        verb.getFuturDubitativ().setSing2(setPronoun(PuterPronouns.pron_2ps + PuterPronouns.pron_r_2ps, verb.getFuturDubitativ().getSing2()));
        verb.getFuturDubitativ().setSing3(setPronoun(PuterPronouns.pron_3ps + PuterPronouns.pron_r_3ps, verb.getFuturDubitativ().getSing3()));
        verb.getFuturDubitativ().setPlural1(setPronoun(PuterPronouns.pron_1pp + PuterPronouns.pron_r_1pp, verb.getFuturDubitativ().getPlural1()));
        verb.getFuturDubitativ().setPlural2(setPronoun(PuterPronouns.pron_2pp + PuterPronouns.pron_r_2pp, verb.getFuturDubitativ().getPlural2()));
        verb.getFuturDubitativ().setPlural3(setPronoun(PuterPronouns.pron_3pp + PuterPronouns.pron_r_3pp, verb.getFuturDubitativ().getPlural3()));

        // PRESCHENT
        verb.getPreschentEnclitic().setSing1(setPronoun(PuterPronouns.pron_r_1ps, verb.getPreschentEnclitic().getSing1()));
        verb.getPreschentEnclitic().setSing2(setPronoun(PuterPronouns.pron_r_2ps, verb.getPreschentEnclitic().getSing2()));
        verb.getPreschentEnclitic().setSing3m(setPronoun(PuterPronouns.pron_r_3ps, verb.getPreschentEnclitic().getSing3m()));
        verb.getPreschentEnclitic().setSing3f(setPronoun(PuterPronouns.pron_r_3ps, verb.getPreschentEnclitic().getSing3f()));
        verb.getPreschentEnclitic().setPlural1(setPronoun(PuterPronouns.pron_r_1pp, verb.getPreschentEnclitic().getPlural1()));
        verb.getPreschentEnclitic().setPlural2(setPronoun(PuterPronouns.pron_r_2pp,verb.getPreschentEnclitic().getPlural2()));
        verb.getPreschentEnclitic().setPlural3(setPronoun(PuterPronouns.pron_r_3pp, verb.getPreschentEnclitic().getPlural3()));

        // IMPERFECT
        verb.getImperfectEnclitic().setSing1(setPronoun(PuterPronouns.pron_r_1ps, verb.getImperfectEnclitic().getSing1()));
        verb.getImperfectEnclitic().setSing2(setPronoun(PuterPronouns.pron_r_2ps, verb.getImperfectEnclitic().getSing2()));
        verb.getImperfectEnclitic().setSing3m(setPronoun(PuterPronouns.pron_r_3ps, verb.getImperfectEnclitic().getSing3m()));
        verb.getImperfectEnclitic().setSing3f(setPronoun(PuterPronouns.pron_r_3ps, verb.getImperfectEnclitic().getSing3f()));
        verb.getImperfectEnclitic().setPlural1(setPronoun(PuterPronouns.pron_r_1pp, verb.getImperfectEnclitic().getPlural1()));
        verb.getImperfectEnclitic().setPlural2(setPronoun(PuterPronouns.pron_r_2pp, verb.getImperfectEnclitic().getPlural2()));
        verb.getImperfectEnclitic().setPlural3(setPronoun(PuterPronouns.pron_r_3pp, verb.getImperfectEnclitic().getPlural3()));

        // CUNDIZIONAL
        verb.getCundizionalEnclitic().setSing1(setPronoun(PuterPronouns.pron_r_1ps, verb.getCundizionalEnclitic().getSing1()));
        verb.getCundizionalEnclitic().setSing2(setPronoun(PuterPronouns.pron_r_2ps, verb.getCundizionalEnclitic().getSing2()));
        verb.getCundizionalEnclitic().setSing3m(setPronoun(PuterPronouns.pron_r_3ps, verb.getCundizionalEnclitic().getSing3m()));
        verb.getCundizionalEnclitic().setSing3f(setPronoun(PuterPronouns.pron_r_3ps, verb.getCundizionalEnclitic().getSing3f()));
        verb.getCundizionalEnclitic().setPlural1(setPronoun(PuterPronouns.pron_r_1pp, verb.getCundizionalEnclitic().getPlural1()));
        verb.getCundizionalEnclitic().setPlural2(setPronoun(PuterPronouns.pron_r_2pp, verb.getCundizionalEnclitic().getPlural2()));
        verb.getCundizionalEnclitic().setPlural3(setPronoun(PuterPronouns.pron_r_3pp, verb.getCundizionalEnclitic().getPlural3()));

        // FUTUR
        verb.getFuturEnclitic().setSing1(setPronoun(PuterPronouns.pron_r_1ps, verb.getFuturEnclitic().getSing1()));
        verb.getFuturEnclitic().setSing2(setPronoun(PuterPronouns.pron_r_2ps, verb.getFuturEnclitic().getSing2()));
        verb.getFuturEnclitic().setSing3m(setPronoun(PuterPronouns.pron_r_3ps, verb.getFuturEnclitic().getSing3m()));
        verb.getFuturEnclitic().setSing3f(setPronoun(PuterPronouns.pron_r_3ps, verb.getFuturEnclitic().getSing3f()));
        verb.getFuturEnclitic().setPlural1(setPronoun(PuterPronouns.pron_r_1pp, verb.getFuturEnclitic().getPlural1()));
        verb.getFuturEnclitic().setPlural2(setPronoun(PuterPronouns.pron_r_2pp, verb.getFuturEnclitic().getPlural2()));
        verb.getFuturEnclitic().setPlural3(setPronoun(PuterPronouns.pron_r_3pp, verb.getFuturEnclitic().getPlural3()));

        // FUTUR DUBITATIV
        verb.getFuturDubitativEnclitic().setSing1(setPronoun(PuterPronouns.pron_r_1ps, verb.getFuturDubitativEnclitic().getSing1()));
        verb.getFuturDubitativEnclitic().setSing2(setPronoun(PuterPronouns.pron_r_2ps, verb.getFuturDubitativEnclitic().getSing2()));
        verb.getFuturDubitativEnclitic().setSing3m(setPronoun(PuterPronouns.pron_r_3ps, verb.getFuturDubitativEnclitic().getSing3m()));
        verb.getFuturDubitativEnclitic().setSing3f(setPronoun(PuterPronouns.pron_r_3ps, verb.getFuturDubitativEnclitic().getSing3f()));
        verb.getFuturDubitativEnclitic().setPlural1(setPronoun(PuterPronouns.pron_r_1pp, verb.getFuturDubitativEnclitic().getPlural1()));
        verb.getFuturDubitativEnclitic().setPlural2(setPronoun(PuterPronouns.pron_r_2pp, verb.getFuturDubitativEnclitic().getPlural2()));
        verb.getFuturDubitativEnclitic().setPlural3(setPronoun(PuterPronouns.pron_r_3pp, verb.getFuturDubitativEnclitic().getPlural3()));
    }

    private void addReflexivePronounsVowel() {
        // PRESCHENT
        verb.getPreschent().setSing1(setPronoun(PuterPronouns.pron_1ps + PuterPronouns.pron_r_v_1ps, verb.getPreschent().getSing1()));
        verb.getPreschent().setSing2(setPronoun(PuterPronouns.pron_2ps + PuterPronouns.pron_r_v_2ps, verb.getPreschent().getSing2()));
        verb.getPreschent().setSing3(setPronoun(PuterPronouns.pron_3ps + PuterPronouns.pron_r_v_3ps, verb.getPreschent().getSing3()));
        verb.getPreschent().setPlural1(setPronoun(PuterPronouns.pron_1pp + PuterPronouns.pron_r_v_1pp, verb.getPreschent().getPlural1()));
        verb.getPreschent().setPlural2(setPronoun(PuterPronouns.pron_2pp + PuterPronouns.pron_r_v_2pp, verb.getPreschent().getPlural2()));
        verb.getPreschent().setPlural3(setPronoun(PuterPronouns.pron_3pp + PuterPronouns.pron_r_v_3pp, verb.getPreschent().getPlural3()));

        // IMPERFECT
        verb.getImperfect().setSing1(setPronoun(PuterPronouns.pron_1ps + PuterPronouns.pron_r_v_1ps, verb.getImperfect().getSing1()));
        verb.getImperfect().setSing2(setPronoun(PuterPronouns.pron_2ps + PuterPronouns.pron_r_v_2ps, verb.getImperfect().getSing2()));
        verb.getImperfect().setSing3(setPronoun(PuterPronouns.pron_3ps + PuterPronouns.pron_r_v_3ps, verb.getImperfect().getSing3()));
        verb.getImperfect().setPlural1(setPronoun(PuterPronouns.pron_1pp + PuterPronouns.pron_r_v_1pp, verb.getImperfect().getPlural1()));
        verb.getImperfect().setPlural2(setPronoun(PuterPronouns.pron_2pp + PuterPronouns.pron_r_v_2pp, verb.getImperfect().getPlural2()));
        verb.getImperfect().setPlural3(setPronoun(PuterPronouns.pron_3pp + PuterPronouns.pron_r_v_3pp, verb.getImperfect().getPlural3()));

        // CONJUNCTIV
        verb.getConjunctiv().setSing1(setPronoun(PuterPronouns.pron_conjunctiv_v + PuterPronouns.pron_1ps + PuterPronouns.pron_r_v_1ps, verb.getConjunctiv().getSing1()));
        verb.getConjunctiv().setSing2(setPronoun(PuterPronouns.pron_conjunctiv_c + PuterPronouns.pron_2ps + PuterPronouns.pron_r_v_2ps, verb.getConjunctiv().getSing2()));
        verb.getConjunctiv().setSing3(setPronoun(PuterPronouns.pron_conjunctiv_v + PuterPronouns.pron_3ps + PuterPronouns.pron_r_v_3ps, verb.getConjunctiv().getSing3()));
        verb.getConjunctiv().setPlural1(setPronoun(PuterPronouns.pron_conjunctiv_c + PuterPronouns.pron_1pp + PuterPronouns.pron_r_v_1pp, verb.getConjunctiv().getPlural1()));
        verb.getConjunctiv().setPlural2(setPronoun(PuterPronouns.pron_conjunctiv_c + PuterPronouns.pron_2pp + PuterPronouns.pron_r_v_2pp, verb.getConjunctiv().getPlural2()));
        verb.getConjunctiv().setPlural3(setPronoun(PuterPronouns.pron_conjunctiv_v + PuterPronouns.pron_3pp + PuterPronouns.pron_r_v_3pp, verb.getConjunctiv().getPlural3()));

        // CONJUNCTIV imperfect
        verb.getConjunctivImperfect().setSing1(setPronoun(PuterPronouns.pron_conjunctiv_v + PuterPronouns.pron_1ps + PuterPronouns.pron_r_v_1ps, verb.getConjunctivImperfect().getSing1()));
        verb.getConjunctivImperfect().setSing2(setPronoun(PuterPronouns.pron_conjunctiv_c + PuterPronouns.pron_2ps + PuterPronouns.pron_r_v_2ps, verb.getConjunctivImperfect().getSing2()));
        verb.getConjunctivImperfect().setSing3(setPronoun(PuterPronouns.pron_conjunctiv_v + PuterPronouns.pron_3ps + PuterPronouns.pron_r_v_3ps, verb.getConjunctivImperfect().getSing3()));
        verb.getConjunctivImperfect().setPlural1(setPronoun(PuterPronouns.pron_conjunctiv_c + PuterPronouns.pron_1pp + PuterPronouns.pron_r_v_1pp, verb.getConjunctivImperfect().getPlural1()));
        verb.getConjunctivImperfect().setPlural2(setPronoun(PuterPronouns.pron_conjunctiv_c + PuterPronouns.pron_2pp + PuterPronouns.pron_r_v_2pp, verb.getConjunctivImperfect().getPlural2()));
        verb.getConjunctivImperfect().setPlural3(setPronoun(PuterPronouns.pron_conjunctiv_v + PuterPronouns.pron_3pp + PuterPronouns.pron_r_v_3pp, verb.getConjunctivImperfect().getPlural3()));

        // CUNDIZIONAL
        verb.getCundiziunal().setSing1(setPronoun(PuterPronouns.pron_1ps + PuterPronouns.pron_r_v_1ps, verb.getCundiziunal().getSing1()));
        verb.getCundiziunal().setSing2(setPronoun(PuterPronouns.pron_2ps + PuterPronouns.pron_r_v_2ps, verb.getCundiziunal().getSing2()));
        verb.getCundiziunal().setSing3(setPronoun(PuterPronouns.pron_3ps + PuterPronouns.pron_r_v_3ps, verb.getCundiziunal().getSing3()));
        verb.getCundiziunal().setPlural1(setPronoun(PuterPronouns.pron_1pp + PuterPronouns.pron_r_v_1pp, verb.getCundiziunal().getPlural1()));
        verb.getCundiziunal().setPlural2(setPronoun(PuterPronouns.pron_2pp + PuterPronouns.pron_r_v_2pp, verb.getCundiziunal().getPlural2()));
        verb.getCundiziunal().setPlural3(setPronoun(PuterPronouns.pron_3pp + PuterPronouns.pron_r_v_3pp, verb.getCundiziunal().getPlural3()));

        // IMPERATIV
        verb.getImperativ().setSingular(setPronoun(verb.getImperativ().getSingular(), PuterPronouns.imperativ_refl_sg, "!"));
        verb.getImperativ().setPlural(setPronoun(verb.getImperativ().getPlural(), PuterPronouns.imperativ_refl_pl, "!"));
        verb.getImperativ().setForm3(setPronoun(PuterPronouns.imperativ_not + PuterPronouns.pron_r_v_2ps, verb.getImperativ().getForm3(), "!"));
        verb.getImperativ().setForm4(setPronoun(PuterPronouns.imperativ_not + PuterPronouns.pron_r_v_2pp, verb.getImperativ().getForm4(), "!"));
        verb.getImperativ().setForm5(setPronoun(PuterPronouns.imperativ_polite_sg + PuterPronouns.pron_r_v_3ps, verb.getImperativ().getForm5(), "!"));
        verb.getImperativ().setForm6(setPronoun(PuterPronouns.imperativ_polite_pl + PuterPronouns.pron_r_v_3pp, verb.getImperativ().getForm6(), "!"));

        // PARTICIP_PERFECT
        verb.getParticipPerfect().setMs(verb.getParticipPerfect().getMs());
        verb.getParticipPerfect().setFs(verb.getParticipPerfect().getFs());
        verb.getParticipPerfect().setMp(verb.getParticipPerfect().getMp());
        verb.getParticipPerfect().setFp(verb.getParticipPerfect().getFp());

        // GERUNDIUM
        verb.setGerundium(setPronoun(PuterPronouns.pron_r_v_3pp, verb.getGerundium()));

        // FUTUR
        verb.getFutur().setSing1(setPronoun(PuterPronouns.pron_1ps + PuterPronouns.pron_r_v_1ps, verb.getFutur().getSing1()));
        verb.getFutur().setSing2(setPronoun(PuterPronouns.pron_2ps + PuterPronouns.pron_r_v_2ps, verb.getFutur().getSing2()));
        verb.getFutur().setSing3(setPronoun(PuterPronouns.pron_3ps + PuterPronouns.pron_r_v_3ps, verb.getFutur().getSing3()));
        verb.getFutur().setPlural1(setPronoun(PuterPronouns.pron_1pp + PuterPronouns.pron_r_v_1pp, verb.getFutur().getPlural1()));
        verb.getFutur().setPlural2(setPronoun(PuterPronouns.pron_2pp + PuterPronouns.pron_r_v_2pp, verb.getFutur().getPlural2()));
        verb.getFutur().setPlural3(setPronoun(PuterPronouns.pron_3pp + PuterPronouns.pron_r_v_3pp, verb.getFutur().getPlural3()));

        // FUTUR DUBITATIV
        verb.getFuturDubitativ().setSing1(setPronoun(PuterPronouns.pron_1ps + PuterPronouns.pron_r_v_1ps, verb.getFuturDubitativ().getSing1()));
        verb.getFuturDubitativ().setSing2(setPronoun(PuterPronouns.pron_2ps + PuterPronouns.pron_r_v_2ps, verb.getFuturDubitativ().getSing2()));
        verb.getFuturDubitativ().setSing3(setPronoun(PuterPronouns.pron_3ps + PuterPronouns.pron_r_v_3ps, verb.getFuturDubitativ().getSing3()));
        verb.getFuturDubitativ().setPlural1(setPronoun(PuterPronouns.pron_1pp + PuterPronouns.pron_r_v_1pp, verb.getFuturDubitativ().getPlural1()));
        verb.getFuturDubitativ().setPlural2(setPronoun(PuterPronouns.pron_2pp + PuterPronouns.pron_r_v_2pp, verb.getFuturDubitativ().getPlural2()));
        verb.getFuturDubitativ().setPlural3(setPronoun(PuterPronouns.pron_3pp + PuterPronouns.pron_r_v_3pp, verb.getFuturDubitativ().getPlural3()));


        // PRESCHENT
        verb.getPreschentEnclitic().setSing1(setPronoun(PuterPronouns.pron_r_v_1ps, verb.getPreschentEnclitic().getSing1()));
        verb.getPreschentEnclitic().setSing2(setPronoun(PuterPronouns.pron_r_v_2ps, verb.getPreschentEnclitic().getSing2()));
        verb.getPreschentEnclitic().setSing3m(setPronoun(PuterPronouns.pron_r_v_3ps, verb.getPreschentEnclitic().getSing3m()));
        verb.getPreschentEnclitic().setSing3f(setPronoun(PuterPronouns.pron_r_v_3ps, verb.getPreschentEnclitic().getSing3f()));
        verb.getPreschentEnclitic().setPlural1(setPronoun(PuterPronouns.pron_r_v_1pp, verb.getPreschentEnclitic().getPlural1()));
        verb.getPreschentEnclitic().setPlural2(setPronoun(PuterPronouns.pron_r_v_2pp,verb.getPreschentEnclitic().getPlural2()));
        verb.getPreschentEnclitic().setPlural3(setPronoun(PuterPronouns.pron_r_v_3pp, verb.getPreschentEnclitic().getPlural3()));

        // IMPERFECT
        verb.getImperfectEnclitic().setSing1(setPronoun(PuterPronouns.pron_r_v_1ps, verb.getImperfectEnclitic().getSing1()));
        verb.getImperfectEnclitic().setSing2(setPronoun(PuterPronouns.pron_r_v_2ps, verb.getImperfectEnclitic().getSing2()));
        verb.getImperfectEnclitic().setSing3m(setPronoun(PuterPronouns.pron_r_v_3ps, verb.getImperfectEnclitic().getSing3m()));
        verb.getImperfectEnclitic().setSing3f(setPronoun(PuterPronouns.pron_r_v_3ps, verb.getImperfectEnclitic().getSing3f()));
        verb.getImperfectEnclitic().setPlural1(setPronoun(PuterPronouns.pron_r_v_1pp, verb.getImperfectEnclitic().getPlural1()));
        verb.getImperfectEnclitic().setPlural2(setPronoun(PuterPronouns.pron_r_v_2pp, verb.getImperfectEnclitic().getPlural2()));
        verb.getImperfectEnclitic().setPlural3(setPronoun(PuterPronouns.pron_r_v_3pp, verb.getImperfectEnclitic().getPlural3()));

        // CUNDIZIONAL
        verb.getCundizionalEnclitic().setSing1(setPronoun(PuterPronouns.pron_r_v_1ps, verb.getCundizionalEnclitic().getSing1()));
        verb.getCundizionalEnclitic().setSing2(setPronoun(PuterPronouns.pron_r_v_2ps, verb.getCundizionalEnclitic().getSing2()));
        verb.getCundizionalEnclitic().setSing3m(setPronoun(PuterPronouns.pron_r_v_3ps, verb.getCundizionalEnclitic().getSing3m()));
        verb.getCundizionalEnclitic().setSing3f(setPronoun(PuterPronouns.pron_r_v_3ps, verb.getCundizionalEnclitic().getSing3f()));
        verb.getCundizionalEnclitic().setPlural1(setPronoun(PuterPronouns.pron_r_v_1pp, verb.getCundizionalEnclitic().getPlural1()));
        verb.getCundizionalEnclitic().setPlural2(setPronoun(PuterPronouns.pron_r_v_2pp, verb.getCundizionalEnclitic().getPlural2()));
        verb.getCundizionalEnclitic().setPlural3(setPronoun(PuterPronouns.pron_r_v_3pp, verb.getCundizionalEnclitic().getPlural3()));

        // FUTUR
        verb.getFuturEnclitic().setSing1(setPronoun(PuterPronouns.pron_r_v_1ps, verb.getFuturEnclitic().getSing1()));
        verb.getFuturEnclitic().setSing2(setPronoun(PuterPronouns.pron_r_v_2ps, verb.getFuturEnclitic().getSing2()));
        verb.getFuturEnclitic().setSing3m(setPronoun(PuterPronouns.pron_r_v_3ps, verb.getFuturEnclitic().getSing3m()));
        verb.getFuturEnclitic().setSing3f(setPronoun(PuterPronouns.pron_r_v_3ps, verb.getFuturEnclitic().getSing3f()));
        verb.getFuturEnclitic().setPlural1(setPronoun(PuterPronouns.pron_r_v_1pp, verb.getFuturEnclitic().getPlural1()));
        verb.getFuturEnclitic().setPlural2(setPronoun(PuterPronouns.pron_r_v_2pp, verb.getFuturEnclitic().getPlural2()));
        verb.getFuturEnclitic().setPlural3(setPronoun(PuterPronouns.pron_r_v_3pp, verb.getFuturEnclitic().getPlural3()));

        // FUTUR DUBITATIV
        verb.getFuturDubitativEnclitic().setSing1(setPronoun(PuterPronouns.pron_r_v_1ps, verb.getFuturDubitativEnclitic().getSing1()));
        verb.getFuturDubitativEnclitic().setSing2(setPronoun(PuterPronouns.pron_r_v_2ps, verb.getFuturDubitativEnclitic().getSing2()));
        verb.getFuturDubitativEnclitic().setSing3m(setPronoun(PuterPronouns.pron_r_v_3ps, verb.getFuturDubitativEnclitic().getSing3m()));
        verb.getFuturDubitativEnclitic().setSing3f(setPronoun(PuterPronouns.pron_r_v_3ps, verb.getFuturDubitativEnclitic().getSing3f()));
        verb.getFuturDubitativEnclitic().setPlural1(setPronoun(PuterPronouns.pron_r_v_1pp, verb.getFuturDubitativEnclitic().getPlural1()));
        verb.getFuturDubitativEnclitic().setPlural2(setPronoun(PuterPronouns.pron_r_v_2pp, verb.getFuturDubitativEnclitic().getPlural2()));
        verb.getFuturDubitativEnclitic().setPlural3(setPronoun(PuterPronouns.pron_r_v_3pp, verb.getFuturDubitativEnclitic().getPlural3()));
    }

    private void addStandardPronouns() {
        // PRESCHENT
        verb.getPreschent().setSing1(setPronoun(PuterPronouns.pron_1ps, verb.getPreschent().getSing1()));
        verb.getPreschent().setSing2(setPronoun(PuterPronouns.pron_2ps, verb.getPreschent().getSing2()));
        verb.getPreschent().setSing3(setPronoun(PuterPronouns.pron_3ps, verb.getPreschent().getSing3()));
        verb.getPreschent().setPlural1(setPronoun(PuterPronouns.pron_1pp, verb.getPreschent().getPlural1()));
        verb.getPreschent().setPlural2(setPronoun(PuterPronouns.pron_2pp, verb.getPreschent().getPlural2()));
        verb.getPreschent().setPlural3(setPronoun(PuterPronouns.pron_3pp, verb.getPreschent().getPlural3()));

        // IMPERFECT
        verb.getImperfect().setSing1(setPronoun(PuterPronouns.pron_1ps, verb.getImperfect().getSing1()));
        verb.getImperfect().setSing2(setPronoun(PuterPronouns.pron_2ps, verb.getImperfect().getSing2()));
        verb.getImperfect().setSing3(setPronoun(PuterPronouns.pron_3ps, verb.getImperfect().getSing3()));
        verb.getImperfect().setPlural1(setPronoun(PuterPronouns.pron_1pp, verb.getImperfect().getPlural1()));
        verb.getImperfect().setPlural2(setPronoun(PuterPronouns.pron_2pp, verb.getImperfect().getPlural2()));
        verb.getImperfect().setPlural3(setPronoun(PuterPronouns.pron_3pp, verb.getImperfect().getPlural3()));

        // CONJUNCTIV
        verb.getConjunctiv().setSing1(setPronoun(PuterPronouns.pron_conjunctiv_v + PuterPronouns.pron_1ps, verb.getConjunctiv().getSing1()));
        verb.getConjunctiv().setSing2(setPronoun(PuterPronouns.pron_conjunctiv_c + PuterPronouns.pron_2ps, verb.getConjunctiv().getSing2()));
        verb.getConjunctiv().setSing3(setPronoun(PuterPronouns.pron_conjunctiv_v + PuterPronouns.pron_3ps, verb.getConjunctiv().getSing3()));
        verb.getConjunctiv().setPlural1(setPronoun(PuterPronouns.pron_conjunctiv_c + PuterPronouns.pron_1pp, verb.getConjunctiv().getPlural1()));
        verb.getConjunctiv().setPlural2(setPronoun(PuterPronouns.pron_conjunctiv_c + PuterPronouns.pron_2pp, verb.getConjunctiv().getPlural2()));
        verb.getConjunctiv().setPlural3(setPronoun(PuterPronouns.pron_conjunctiv_v + PuterPronouns.pron_3pp, verb.getConjunctiv().getPlural3()));

        // CONJUNCTIV imperfect
        verb.getConjunctivImperfect().setSing1(setPronoun(PuterPronouns.pron_conjunctiv_v + PuterPronouns.pron_1ps, verb.getConjunctivImperfect().getSing1()));
        verb.getConjunctivImperfect().setSing2(setPronoun(PuterPronouns.pron_conjunctiv_c + PuterPronouns.pron_2ps, verb.getConjunctivImperfect().getSing2()));
        verb.getConjunctivImperfect().setSing3(setPronoun(PuterPronouns.pron_conjunctiv_v + PuterPronouns.pron_3ps, verb.getConjunctivImperfect().getSing3()));
        verb.getConjunctivImperfect().setPlural1(setPronoun(PuterPronouns.pron_conjunctiv_c + PuterPronouns.pron_1pp, verb.getConjunctivImperfect().getPlural1()));
        verb.getConjunctivImperfect().setPlural2(setPronoun(PuterPronouns.pron_conjunctiv_c + PuterPronouns.pron_2pp, verb.getConjunctivImperfect().getPlural2()));
        verb.getConjunctivImperfect().setPlural3(setPronoun(PuterPronouns.pron_conjunctiv_v + PuterPronouns.pron_3pp, verb.getConjunctivImperfect().getPlural3()));

        // CUNDIZIONAL
        verb.getCundiziunal().setSing1(setPronoun(PuterPronouns.pron_1ps, verb.getCundiziunal().getSing1()));
        verb.getCundiziunal().setSing2(setPronoun(PuterPronouns.pron_2ps, verb.getCundiziunal().getSing2()));
        verb.getCundiziunal().setSing3(setPronoun(PuterPronouns.pron_3ps, verb.getCundiziunal().getSing3()));
        verb.getCundiziunal().setPlural1(setPronoun(PuterPronouns.pron_1pp, verb.getCundiziunal().getPlural1()));
        verb.getCundiziunal().setPlural2(setPronoun(PuterPronouns.pron_2pp, verb.getCundiziunal().getPlural2()));
        verb.getCundiziunal().setPlural3(setPronoun(PuterPronouns.pron_3pp, verb.getCundiziunal().getPlural3()));

        // IMPERATIV
        verb.getImperativ().setSingular(setPronoun("", verb.getImperativ().getSingular(), "!"));
        verb.getImperativ().setPlural(setPronoun("", verb.getImperativ().getPlural(), "!"));
        if (startsWithVowel(verb.getImperativ().getForm3())) {
            verb.getImperativ().setForm3(setPronoun(PuterPronouns.imperativ_not_vowel, verb.getImperativ().getForm3(), "!"));
            verb.getImperativ().setForm4(setPronoun(PuterPronouns.imperativ_not_vowel, verb.getImperativ().getForm4(), "!"));
        } else {
            verb.getImperativ().setForm3(setPronoun(PuterPronouns.imperativ_not, verb.getImperativ().getForm3(), "!"));
            verb.getImperativ().setForm4(setPronoun(PuterPronouns.imperativ_not, verb.getImperativ().getForm4(), "!"));
        }
        verb.getImperativ().setForm5(setPronoun(PuterPronouns.imperativ_polite_sg, verb.getImperativ().getForm5(), "!"));
        verb.getImperativ().setForm6(setPronoun(PuterPronouns.imperativ_polite_pl, verb.getImperativ().getForm6(), "!"));

        // PARTICIP_PERFECT
        verb.getParticipPerfect().setMs(verb.getParticipPerfect().getMs());
        verb.getParticipPerfect().setFs(verb.getParticipPerfect().getFs());
        verb.getParticipPerfect().setMp(verb.getParticipPerfect().getMp());
        verb.getParticipPerfect().setFp(verb.getParticipPerfect().getFp());

        // GERUNDIUM
        verb.setGerundium(verb.getGerundium());

        // FUTUR
        verb.getFutur().setSing1(setPronoun(PuterPronouns.pron_1ps, verb.getFutur().getSing1()));
        verb.getFutur().setSing2(setPronoun(PuterPronouns.pron_2ps, verb.getFutur().getSing2()));
        verb.getFutur().setSing3(setPronoun(PuterPronouns.pron_3ps, verb.getFutur().getSing3()));
        verb.getFutur().setPlural1(setPronoun(PuterPronouns.pron_1pp, verb.getFutur().getPlural1()));
        verb.getFutur().setPlural2(setPronoun(PuterPronouns.pron_2pp, verb.getFutur().getPlural2()));
        verb.getFutur().setPlural3(setPronoun(PuterPronouns.pron_3pp, verb.getFutur().getPlural3()));

        // FUTUR DUBITATIV
        verb.getFuturDubitativ().setSing1(setPronoun(PuterPronouns.pron_1ps, verb.getFuturDubitativ().getSing1()));
        verb.getFuturDubitativ().setSing2(setPronoun(PuterPronouns.pron_2ps, verb.getFuturDubitativ().getSing2()));
        verb.getFuturDubitativ().setSing3(setPronoun(PuterPronouns.pron_3ps, verb.getFuturDubitativ().getSing3()));
        verb.getFuturDubitativ().setPlural1(setPronoun(PuterPronouns.pron_1pp, verb.getFuturDubitativ().getPlural1()));
        verb.getFuturDubitativ().setPlural2(setPronoun(PuterPronouns.pron_2pp, verb.getFuturDubitativ().getPlural2()));
        verb.getFuturDubitativ().setPlural3(setPronoun(PuterPronouns.pron_3pp, verb.getFuturDubitativ().getPlural3()));
    }
}
