package ch.pledarigrond.inflection.utils;

import ch.pledarigrond.inflection.generation.puter.PuterConjugationStructure;
import ch.pledarigrond.inflection.generation.surmiran.SurmiranConjugationStructure;
import ch.pledarigrond.inflection.generation.vallader.ValladerConjugation;
import ch.pledarigrond.inflection.generation.vallader.ValladerConjugationStructure;

import java.util.HashMap;

public class ConjugationStructureGenerator {

    public static SurmiranConjugationStructure generateSurmiranConjugationStructure(HashMap<String, String> conjugation) {
        SurmiranConjugationStructure cs = new SurmiranConjugationStructure();

        cs.setInfinitiv(conjugation.get(SurmiranConjugationStructure.infinitiv));

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

    public static PuterConjugationStructure generatePuterConjugationStructure(HashMap<String, String> conjugation) {
        PuterConjugationStructure cs = new PuterConjugationStructure();

        cs.setInfinitiv(conjugation.get(PuterConjugationStructure.infinitiv));

        cs.setPreschentsing1(conjugation.get(PuterConjugationStructure.preschentsing1));
        cs.setPreschentsing2(conjugation.get(PuterConjugationStructure.preschentsing2));
        cs.setPreschentsing3(conjugation.get(PuterConjugationStructure.preschentsing3));
        cs.setPreschentplural1(conjugation.get(PuterConjugationStructure.preschentplural1));
        cs.setPreschentplural2(conjugation.get(PuterConjugationStructure.preschentplural2));
        cs.setPreschentplural3(conjugation.get(PuterConjugationStructure.preschentplural3));

        cs.setImperfectsing1(conjugation.get(PuterConjugationStructure.imperfectsing1));
        cs.setImperfectsing2(conjugation.get(PuterConjugationStructure.imperfectsing2));
        cs.setImperfectsing3(conjugation.get(PuterConjugationStructure.imperfectsing3));
        cs.setImperfectplural1(conjugation.get(PuterConjugationStructure.imperfectplural1));
        cs.setImperfectplural2(conjugation.get(PuterConjugationStructure.imperfectplural2));
        cs.setImperfectplural3(conjugation.get(PuterConjugationStructure.imperfectplural3));

        cs.setConjunctivsing1(conjugation.get(PuterConjugationStructure.conjunctivsing1));
        cs.setConjunctivsing2(conjugation.get(PuterConjugationStructure.conjunctivsing2));
        cs.setConjunctivsing3(conjugation.get(PuterConjugationStructure.conjunctivsing3));
        cs.setConjunctivplural1(conjugation.get(PuterConjugationStructure.conjunctivplural1));
        cs.setConjunctivplural2(conjugation.get(PuterConjugationStructure.conjunctivplural2));
        cs.setConjunctivplural3(conjugation.get(PuterConjugationStructure.conjunctivplural3));

        cs.setCundizionalsing1(conjugation.get(PuterConjugationStructure.cundizionalsing1));
        cs.setCundizionalsing2(conjugation.get(PuterConjugationStructure.cundizionalsing2));
        cs.setCundizionalsing3(conjugation.get(PuterConjugationStructure.cundizionalsing3));
        cs.setCundizionalplural1(conjugation.get(PuterConjugationStructure.cundizionalplural1));
        cs.setCundizionalplural2(conjugation.get(PuterConjugationStructure.cundizionalplural2));
        cs.setCundizionalplural3(conjugation.get(PuterConjugationStructure.cundizionalplural3));

        cs.setParticipperfectms(conjugation.get(PuterConjugationStructure.participperfectms));
        cs.setParticipperfectmp(conjugation.get(PuterConjugationStructure.participperfectmp));
        cs.setParticipperfectfs(conjugation.get(PuterConjugationStructure.participperfectfs));
        cs.setParticipperfectfp(conjugation.get(PuterConjugationStructure.participperfectfp));

        cs.setImperativ1(conjugation.get(PuterConjugationStructure.imperativ1));
        cs.setImperativ2(conjugation.get(PuterConjugationStructure.imperativ2));

        cs.setGerundium(conjugation.get(PuterConjugationStructure.gerundium));

        cs.setFutursing1(conjugation.get(PuterConjugationStructure.futursing1));
        cs.setFutursing2(conjugation.get(PuterConjugationStructure.futursing2));
        cs.setFutursing3(conjugation.get(PuterConjugationStructure.futursing3));
        cs.setFuturplural1(conjugation.get(PuterConjugationStructure.futurplural1));
        cs.setFuturplural2(conjugation.get(PuterConjugationStructure.futurplural2));
        cs.setFuturplural3(conjugation.get(PuterConjugationStructure.futurplural3));

        cs.setFuturdubitativsing1(conjugation.get(PuterConjugationStructure.futurdubitativsing1));
        cs.setFuturdubitativsing2(conjugation.get(PuterConjugationStructure.futurdubitativsing2));
        cs.setFuturdubitativsing3(conjugation.get(PuterConjugationStructure.futurdubitativsing3));
        cs.setFuturdubitativplural1(conjugation.get(PuterConjugationStructure.futurdubitativplural1));
        cs.setFuturdubitativplural2(conjugation.get(PuterConjugationStructure.futurdubitativplural2));
        cs.setFuturdubitativplural3(conjugation.get(PuterConjugationStructure.futurdubitativplural3));

        cs.setPreschentsing1Enclitic(conjugation.get(PuterConjugationStructure.preschentsing1enclitic));
        cs.setPreschentsing2Enclitic(conjugation.get(PuterConjugationStructure.preschentsing2enclitic));
        cs.setPreschentsing3EncliticM(conjugation.get(PuterConjugationStructure.preschentsing3encliticm));
        cs.setPreschentsing3EncliticF(conjugation.get(PuterConjugationStructure.preschentsing3encliticf));
        cs.setPreschentplural1Enclitic(conjugation.get(PuterConjugationStructure.preschentplural1enclitic));
        cs.setPreschentplural2Enclitic(conjugation.get(PuterConjugationStructure.preschentplural2enclitic));
        cs.setPreschentplural3Enclitic(conjugation.get(PuterConjugationStructure.preschentplural3enclitic));

        cs.setImperfectsing1Enclitic(conjugation.get(PuterConjugationStructure.imperfectsing1enclitic));
        cs.setImperfectsing2Enclitic(conjugation.get(PuterConjugationStructure.imperfectsing2enclitic));
        cs.setImperfectsing3EncliticM(conjugation.get(PuterConjugationStructure.imperfectsing3encliticm));
        cs.setImperfectsing3EncliticF(conjugation.get(PuterConjugationStructure.imperfectsing3encliticf));
        cs.setImperfectplural1Enclitic(conjugation.get(PuterConjugationStructure.imperfectplural1enclitic));
        cs.setImperfectplural2Enclitic(conjugation.get(PuterConjugationStructure.imperfectplural2enclitic));
        cs.setImperfectplural3Enclitic(conjugation.get(PuterConjugationStructure.imperfectplural3enclitic));

        cs.setCundizionalsing1Enclitic(conjugation.get(PuterConjugationStructure.cundizionalsing1enclitic));
        cs.setCundizionalsing2Enclitic(conjugation.get(PuterConjugationStructure.cundizionalsing2enclitic));
        cs.setCundizionalsing3EncliticM(conjugation.get(PuterConjugationStructure.cundizionalsing3encliticm));
        cs.setCundizionalsing3EncliticF(conjugation.get(PuterConjugationStructure.cundizionalsing3encliticf));
        cs.setCundizionalplural1Enclitic(conjugation.get(PuterConjugationStructure.cundizionalplural1enclitic));
        cs.setCundizionalplural2Enclitic(conjugation.get(PuterConjugationStructure.cundizionalplural2enclitic));
        cs.setCundizionalplural3Enclitic(conjugation.get(PuterConjugationStructure.cundizionalplural3enclitic));

        cs.setFutursing1Enclitic(conjugation.get(PuterConjugationStructure.futursing1enclitic));
        cs.setFutursing2Enclitic(conjugation.get(PuterConjugationStructure.futursing2enclitic));
        cs.setFutursing3EncliticM(conjugation.get(PuterConjugationStructure.futursing3encliticm));
        cs.setFutursing3EncliticF(conjugation.get(PuterConjugationStructure.futursing3encliticf));
        cs.setFuturplural1Enclitic(conjugation.get(PuterConjugationStructure.futurplural1enclitic));
        cs.setFuturplural2Enclitic(conjugation.get(PuterConjugationStructure.futurplural2enclitic));
        cs.setFuturplural3Enclitic(conjugation.get(PuterConjugationStructure.futurplural3enclitic));

        cs.setFuturdubitativsing1Enclitic(conjugation.get(PuterConjugationStructure.futurdubitativsing1enclitic));
        cs.setFuturdubitativsing2Enclitic(conjugation.get(PuterConjugationStructure.futurdubitativsing2enclitic));
        cs.setFuturdubitativsing3EncliticM(conjugation.get(PuterConjugationStructure.futurdubitativsing3encliticm));
        cs.setFuturdubitativsing3EncliticF(conjugation.get(PuterConjugationStructure.futurdubitativsing3encliticf));
        cs.setFuturdubitativplural1Enclitic(conjugation.get(PuterConjugationStructure.futurdubitativplural1enclitic));
        cs.setFuturdubitativplural2Enclitic(conjugation.get(PuterConjugationStructure.futurdubitativplural2enclitic));
        cs.setFuturdubitativplural3Enclitic(conjugation.get(PuterConjugationStructure.futurdubitativplural3enclitic));

        return cs;
    }

    public static ValladerConjugationStructure generateValladerConjugationStructure(HashMap<String, String> conjugation) {
        ValladerConjugationStructure cs = new ValladerConjugationStructure();

        cs.setInfinitiv(conjugation.get(ValladerConjugationStructure.infinitiv));

        cs.setPreschentsing1(conjugation.get(ValladerConjugationStructure.preschentsing1));
        cs.setPreschentsing2(conjugation.get(ValladerConjugationStructure.preschentsing2));
        cs.setPreschentsing3(conjugation.get(ValladerConjugationStructure.preschentsing3));
        cs.setPreschentplural1(conjugation.get(ValladerConjugationStructure.preschentplural1));
        cs.setPreschentplural2(conjugation.get(ValladerConjugationStructure.preschentplural2));
        cs.setPreschentplural3(conjugation.get(ValladerConjugationStructure.preschentplural3));

        cs.setImperfectsing1(conjugation.get(ValladerConjugationStructure.imperfectsing1));
        cs.setImperfectsing2(conjugation.get(ValladerConjugationStructure.imperfectsing2));
        cs.setImperfectsing3(conjugation.get(ValladerConjugationStructure.imperfectsing3));
        cs.setImperfectplural1(conjugation.get(ValladerConjugationStructure.imperfectplural1));
        cs.setImperfectplural2(conjugation.get(ValladerConjugationStructure.imperfectplural2));
        cs.setImperfectplural3(conjugation.get(ValladerConjugationStructure.imperfectplural3));

        cs.setConjunctivsing1(conjugation.get(ValladerConjugationStructure.conjunctivsing1));
        cs.setConjunctivsing2(conjugation.get(ValladerConjugationStructure.conjunctivsing2));
        cs.setConjunctivsing3(conjugation.get(ValladerConjugationStructure.conjunctivsing3));
        cs.setConjunctivplural1(conjugation.get(ValladerConjugationStructure.conjunctivplural1));
        cs.setConjunctivplural2(conjugation.get(ValladerConjugationStructure.conjunctivplural2));
        cs.setConjunctivplural3(conjugation.get(ValladerConjugationStructure.conjunctivplural3));

        cs.setCundizionalsing1(conjugation.get(ValladerConjugationStructure.cundizionalsing1));
        cs.setCundizionalsing2(conjugation.get(ValladerConjugationStructure.cundizionalsing2));
        cs.setCundizionalsing3(conjugation.get(ValladerConjugationStructure.cundizionalsing3));
        cs.setCundizionalplural1(conjugation.get(ValladerConjugationStructure.cundizionalplural1));
        cs.setCundizionalplural2(conjugation.get(ValladerConjugationStructure.cundizionalplural2));
        cs.setCundizionalplural3(conjugation.get(ValladerConjugationStructure.cundizionalplural3));

        cs.setParticipperfectms(conjugation.get(ValladerConjugationStructure.participperfectms));
        cs.setParticipperfectmp(conjugation.get(ValladerConjugationStructure.participperfectmp));
        cs.setParticipperfectfs(conjugation.get(ValladerConjugationStructure.participperfectfs));
        cs.setParticipperfectfp(conjugation.get(ValladerConjugationStructure.participperfectfp));

        cs.setImperativ1(conjugation.get(ValladerConjugationStructure.imperativ1));
        cs.setImperativ2(conjugation.get(ValladerConjugationStructure.imperativ2));

        cs.setGerundium(conjugation.get(ValladerConjugationStructure.gerundium));

        cs.setFutursing1(conjugation.get(ValladerConjugationStructure.futursing1));
        cs.setFutursing2(conjugation.get(ValladerConjugationStructure.futursing2));
        cs.setFutursing3(conjugation.get(ValladerConjugationStructure.futursing3));
        cs.setFuturplural1(conjugation.get(ValladerConjugationStructure.futurplural1));
        cs.setFuturplural2(conjugation.get(ValladerConjugationStructure.futurplural2));
        cs.setFuturplural3(conjugation.get(ValladerConjugationStructure.futurplural3));

        cs.setPreschentsing1Enclitic(conjugation.get(ValladerConjugationStructure.preschentsing1enclitic));
        cs.setPreschentsing2Enclitic(conjugation.get(ValladerConjugationStructure.preschentsing2enclitic));
        cs.setPreschentsing3EncliticM(conjugation.get(ValladerConjugationStructure.preschentsing3encliticm));
        cs.setPreschentsing3EncliticF(conjugation.get(ValladerConjugationStructure.preschentsing3encliticf));
        cs.setPreschentplural1Enclitic(conjugation.get(ValladerConjugationStructure.preschentplural1enclitic));
        cs.setPreschentplural2Enclitic(conjugation.get(ValladerConjugationStructure.preschentplural2enclitic));
        cs.setPreschentplural3Enclitic(conjugation.get(ValladerConjugationStructure.preschentplural3enclitic));

        cs.setImperfectsing1Enclitic(conjugation.get(ValladerConjugationStructure.imperfectsing1enclitic));
        cs.setImperfectsing2Enclitic(conjugation.get(ValladerConjugationStructure.imperfectsing2enclitic));
        cs.setImperfectsing3EncliticM(conjugation.get(ValladerConjugationStructure.imperfectsing3encliticm));
        cs.setImperfectsing3EncliticF(conjugation.get(ValladerConjugationStructure.imperfectsing3encliticf));
        cs.setImperfectplural1Enclitic(conjugation.get(ValladerConjugationStructure.imperfectplural1enclitic));
        cs.setImperfectplural2Enclitic(conjugation.get(ValladerConjugationStructure.imperfectplural2enclitic));
        cs.setImperfectplural3Enclitic(conjugation.get(ValladerConjugationStructure.imperfectplural3enclitic));

        cs.setCundizionalsing1Enclitic(conjugation.get(ValladerConjugationStructure.cundizionalsing1enclitic));
        cs.setCundizionalsing2Enclitic(conjugation.get(ValladerConjugationStructure.cundizionalsing2enclitic));
        cs.setCundizionalsing3EncliticM(conjugation.get(ValladerConjugationStructure.cundizionalsing3encliticm));
        cs.setCundizionalsing3EncliticF(conjugation.get(ValladerConjugationStructure.cundizionalsing3encliticf));
        cs.setCundizionalplural1Enclitic(conjugation.get(ValladerConjugationStructure.cundizionalplural1enclitic));
        cs.setCundizionalplural2Enclitic(conjugation.get(ValladerConjugationStructure.cundizionalplural2enclitic));
        cs.setCundizionalplural3Enclitic(conjugation.get(ValladerConjugationStructure.cundizionalplural3enclitic));

        cs.setFutursing1Enclitic(conjugation.get(ValladerConjugationStructure.futursing1enclitic));
        cs.setFutursing2Enclitic(conjugation.get(ValladerConjugationStructure.futursing2enclitic));
        cs.setFutursing3EncliticM(conjugation.get(ValladerConjugationStructure.futursing3encliticm));
        cs.setFutursing3EncliticF(conjugation.get(ValladerConjugationStructure.futursing3encliticf));
        cs.setFuturplural1Enclitic(conjugation.get(ValladerConjugationStructure.futurplural1enclitic));
        cs.setFuturplural2Enclitic(conjugation.get(ValladerConjugationStructure.futurplural2enclitic));
        cs.setFuturplural3Enclitic(conjugation.get(ValladerConjugationStructure.futurplural3enclitic));

        return cs;
    }
}
