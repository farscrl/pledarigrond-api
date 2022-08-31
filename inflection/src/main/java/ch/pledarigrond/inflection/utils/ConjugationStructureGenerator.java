package ch.pledarigrond.inflection.utils;

import ch.pledarigrond.inflection.generation.surmiran.SurmiranConjugationStructure;

import java.util.HashMap;

public class ConjugationStructureGenerator {

    public static SurmiranConjugationStructure generateSurmiranConjugationStructure(HashMap<String, String> conjugation) {
        SurmiranConjugationStructure cs = new SurmiranConjugationStructure();

        cs.setPreschentsing1(conjugation.get(SurmiranConjugationStructure.preschentsing1));
        cs.setPreschentsing2(conjugation.get(SurmiranConjugationStructure.preschentsing2));
        cs.setPreschentsing3(conjugation.get(SurmiranConjugationStructure.preschentsing3));
        cs.setPreschentplural1(conjugation.get(SurmiranConjugationStructure.preschentplural1));
        cs.setPreschentplural2(conjugation.get(SurmiranConjugationStructure.preschentplural2));
        cs.setPreschentplural3(conjugation.get(SurmiranConjugationStructure.preschentplural3));

        cs.setImperfectsing1(conjugation.get(SurmiranConjugationStructure.imperfectsing1));
        cs.setImperfectsing2(conjugation.get(SurmiranConjugationStructure.imperfectsing2));
        cs.setImperfectsing3(conjugation.get(SurmiranConjugationStructure.imperfectsing3));
        cs.setImperfectplural1(conjugation.get(SurmiranConjugationStructure.imperfectplural1));
        cs.setImperfectplural2(conjugation.get(SurmiranConjugationStructure.imperfectplural2));
        cs.setImperfectplural3(conjugation.get(SurmiranConjugationStructure.imperfectplural3));

        cs.setConjunctivsing1(conjugation.get(SurmiranConjugationStructure.conjunctivsing1));
        cs.setConjunctivsing2(conjugation.get(SurmiranConjugationStructure.conjunctivsing2));
        cs.setConjunctivsing3(conjugation.get(SurmiranConjugationStructure.conjunctivsing3));
        cs.setConjunctivplural1(conjugation.get(SurmiranConjugationStructure.conjunctivplural1));
        cs.setConjunctivplural2(conjugation.get(SurmiranConjugationStructure.conjunctivplural2));
        cs.setConjunctivplural3(conjugation.get(SurmiranConjugationStructure.conjunctivplural3));

        cs.setCundizionalsing1(conjugation.get(SurmiranConjugationStructure.cundizionalsing1));
        cs.setCundizionalsing2(conjugation.get(SurmiranConjugationStructure.cundizionalsing2));
        cs.setCundizionalsing3(conjugation.get(SurmiranConjugationStructure.cundizionalsing3));
        cs.setCundizionalplural1(conjugation.get(SurmiranConjugationStructure.cundizionalplural1));
        cs.setCundizionalplural2(conjugation.get(SurmiranConjugationStructure.cundizionalplural2));
        cs.setCundizionalplural3(conjugation.get(SurmiranConjugationStructure.cundizionalplural3));

        cs.setParticipperfectms(conjugation.get(SurmiranConjugationStructure.participperfectms));
        cs.setParticipperfectmp(conjugation.get(SurmiranConjugationStructure.participperfectmp));
        cs.setParticipperfectfs(conjugation.get(SurmiranConjugationStructure.participperfectfs));
        cs.setParticipperfectfp(conjugation.get(SurmiranConjugationStructure.participperfectfp));

        cs.setImperativ1(conjugation.get(SurmiranConjugationStructure.imperativ1));
        cs.setImperativ2(conjugation.get(SurmiranConjugationStructure.imperativ2));

        cs.setGerundium(conjugation.get(SurmiranConjugationStructure.gerundium));

        cs.setFutursing1(conjugation.get(SurmiranConjugationStructure.futursing1));
        cs.setFutursing2(conjugation.get(SurmiranConjugationStructure.futursing2));
        cs.setFutursing3(conjugation.get(SurmiranConjugationStructure.futursing3));
        cs.setFuturplural1(conjugation.get(SurmiranConjugationStructure.futurplural1));
        cs.setFuturplural2(conjugation.get(SurmiranConjugationStructure.futurplural2));
        cs.setFuturplural3(conjugation.get(SurmiranConjugationStructure.futurplural3));

        cs.setPreschentsing1Enclitic(conjugation.get(SurmiranConjugationStructure.preschentsing1enclitic));
        cs.setPreschentsing2Enclitic(conjugation.get(SurmiranConjugationStructure.preschentsing2enclitic));
        cs.setPreschentsing3EncliticM(conjugation.get(SurmiranConjugationStructure.preschentsing3encliticm));
        cs.setPreschentsing3EncliticF(conjugation.get(SurmiranConjugationStructure.preschentsing3encliticf));
        cs.setPreschentplural1Enclitic(conjugation.get(SurmiranConjugationStructure.preschentplural1enclitic));
        cs.setPreschentplural2Enclitic(conjugation.get(SurmiranConjugationStructure.preschentplural2enclitic));
        cs.setPreschentplural3Enclitic(conjugation.get(SurmiranConjugationStructure.preschentplural3enclitic));

        cs.setImperfectsing1Enclitic(conjugation.get(SurmiranConjugationStructure.imperfectsing1enclitic));
        cs.setImperfectsing2Enclitic(conjugation.get(SurmiranConjugationStructure.imperfectsing2enclitic));
        cs.setImperfectsing3EncliticM(conjugation.get(SurmiranConjugationStructure.imperfectsing3encliticm));
        cs.setImperfectsing3EncliticF(conjugation.get(SurmiranConjugationStructure.imperfectsing3encliticf));
        cs.setImperfectplural1Enclitic(conjugation.get(SurmiranConjugationStructure.imperfectplural1enclitic));
        cs.setImperfectplural2Enclitic(conjugation.get(SurmiranConjugationStructure.imperfectplural2enclitic));
        cs.setImperfectplural3Enclitic(conjugation.get(SurmiranConjugationStructure.imperfectplural3enclitic));

        cs.setCundizionalsing1Enclitic(conjugation.get(SurmiranConjugationStructure.cundizionalsing1enclitic));
        cs.setCundizionalsing2Enclitic(conjugation.get(SurmiranConjugationStructure.cundizionalsing2enclitic));
        cs.setCundizionalsing3EncliticM(conjugation.get(SurmiranConjugationStructure.cundizionalsing3encliticm));
        cs.setCundizionalsing3EncliticF(conjugation.get(SurmiranConjugationStructure.cundizionalsing3encliticf));
        cs.setCundizionalplural1Enclitic(conjugation.get(SurmiranConjugationStructure.cundizionalplural1enclitic));
        cs.setCundizionalplural2Enclitic(conjugation.get(SurmiranConjugationStructure.cundizionalplural2enclitic));
        cs.setCundizionalplural3Enclitic(conjugation.get(SurmiranConjugationStructure.cundizionalplural3enclitic));

        cs.setFutursing1Enclitic(conjugation.get(SurmiranConjugationStructure.futursing1enclitic));
        cs.setFutursing2Enclitic(conjugation.get(SurmiranConjugationStructure.futursing2enclitic));
        cs.setFutursing3EncliticM(conjugation.get(SurmiranConjugationStructure.futursing3encliticm));
        cs.setFutursing3EncliticF(conjugation.get(SurmiranConjugationStructure.futursing3encliticf));
        cs.setFuturplural1Enclitic(conjugation.get(SurmiranConjugationStructure.futurplural1enclitic));
        cs.setFuturplural2Enclitic(conjugation.get(SurmiranConjugationStructure.futurplural2enclitic));
        cs.setFuturplural3Enclitic(conjugation.get(SurmiranConjugationStructure.futurplural3enclitic));

        return cs;
    }
}
