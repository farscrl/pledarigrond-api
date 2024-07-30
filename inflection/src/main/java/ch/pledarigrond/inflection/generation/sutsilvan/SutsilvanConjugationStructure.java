package ch.pledarigrond.inflection.generation.sutsilvan;

/*******************************************************************************
 * Copyright 2013-2016 Sprachliche Informationsverarbeitung, University of Cologne
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/


import ch.pledarigrond.inflection.model.InflectionSubType;
import ch.pledarigrond.inflection.model.InflectionType;

import java.util.HashMap;

public class SutsilvanConjugationStructure {

	public static final String verb = "verb";

	public static final String infinitiv = "infinitiv";
	public static final String root = "root";
	public static final String ending = "ending";
	public static final String reflexive = "reflexive";

	public static final String preschentsing1 = "preschentsing1";
	public static final String preschentsing2 = "preschentsing2";
	public static final String preschentsing3 = "preschentsing3";
	public static final String preschentplural1 = "preschentplural1";
	public static final String preschentplural2 = "preschentplural2";
	public static final String preschentplural3 = "preschentplural3";

	public static final String imperfectsing1 = "imperfectsing1";
	public static final String imperfectsing2 = "imperfectsing2";
	public static final String imperfectsing3 = "imperfectsing3";
	public static final String imperfectplural1 = "imperfectplural1";
	public static final String imperfectplural2 = "imperfectplural2";
	public static final String imperfectplural3 = "imperfectplural3";

	public static final String conjunctivsing1 = "conjunctivsing1";
	public static final String conjunctivsing2 = "conjunctivsing2";
	public static final String conjunctivsing3 = "conjunctivsing3";
	public static final String conjunctivplural1 = "conjunctivplural1";
	public static final String conjunctivplural2 = "conjunctivplural2";
	public static final String conjunctivplural3 = "conjunctivplural3";

	public static final String conjunctiv2sing1 = "conjunctiv2sing1";
	public static final String conjunctiv2sing2 = "conjunctiv2sing2";
	public static final String conjunctiv2sing3 = "conjunctiv2sing3";
	public static final String conjunctiv2plural1 = "conjunctiv2plural1";
	public static final String conjunctiv2plural2 = "conjunctiv2plural2";
	public static final String conjunctiv2plural3 = "conjunctiv2plural3";

	public static final String cundizionalsing1 = "cundizionalsing1";
	public static final String cundizionalsing2 = "cundizionalsing2";
	public static final String cundizionalsing3 = "cundizionalsing3";
	public static final String cundizionalplural1 = "cundizionalplural1";
	public static final String cundizionalplural2 = "cundizionalplural2";
	public static final String cundizionalplural3 = "cundizionalplural3";

	public static final String cundizionalindirectsing1 = "cundizionalindirectsing1";
	public static final String cundizionalindirectsing2 = "cundizionalindirectsing2";
	public static final String cundizionalindirectsing3 = "cundizionalindirectsing3";
	public static final String cundizionalindirectplural1 = "cundizionalindirectplural1";
	public static final String cundizionalindirectplural2 = "cundizionalindirectplural2";
	public static final String cundizionalindirectplural3 = "cundizionalindirectplural3";

	public static final String participperfectms = "participperfectms";
	public static final String participperfectfs = "participperfectfs";
	public static final String participperfectmp = "participperfectmp";
	public static final String participperfectfp = "participperfectfp";

	public static final String imperativ1 = "imperativ1";
	public static final String imperativ2 = "imperativ2";

	public static final String gerundium = "gerundium";

	public static final String futursing1 = "futursing1";
	public static final String futursing2 = "futursing2";
	public static final String futursing3 = "futursing3";
	public static final String futurplural1 = "futurplural1";
	public static final String futurplural2 = "futurplural2";
	public static final String futurplural3 = "futurplural3";

	public static final String preschentsing1enclitic = "preschentsing1enclitic";
	public static final String preschentsing2enclitic = "preschentsing2enclitic";
	public static final String preschentsing3encliticm = "preschentsing3encliticm";
	public static final String preschentsing3encliticf = "preschentsing3encliticf";
	public static final String preschentplural1enclitic = "preschentplural1enclitic";
	public static final String preschentplural2enclitic = "preschentplural2enclitic";
	public static final String preschentplural3enclitic = "preschentplural3enclitic";

	public static final String imperfectsing1enclitic = "imperfectsing1enclitic";
	public static final String imperfectsing2enclitic = "imperfectsing2enclitic";
	public static final String imperfectsing3encliticm = "imperfectsing3encliticm";
	public static final String imperfectsing3encliticf = "imperfectsing3encliticf";
	public static final String imperfectplural1enclitic = "imperfectplural1enclitic";
	public static final String imperfectplural2enclitic = "imperfectplural2enclitic";
	public static final String imperfectplural3enclitic = "imperfectplural3enclitic";

	public static final String cundizionalsing1enclitic = "cundizionalsing1enclitic";
	public static final String cundizionalsing2enclitic = "cundizionalsing2enclitic";
	public static final String cundizionalsing3encliticm = "cundizionalsing3encliticm";
	public static final String cundizionalsing3encliticf = "cundizionalsing3encliticf";
	public static final String cundizionalplural1enclitic = "cundizionalplural1enclitic";
	public static final String cundizionalplural2enclitic = "cundizionalplural2enclitic";
	public static final String cundizionalplural3enclitic = "cundizionalplural3enclitic";

	public String[] msi = new String[] { preschentsing1, preschentsing2,
			preschentsing3, preschentplural1, preschentplural2,
			preschentplural3, imperfectsing1, imperfectsing2, imperfectsing3,
			imperfectplural1, imperfectplural2, imperfectplural3,
			conjunctivsing1, conjunctivsing2, conjunctivsing3,
			conjunctivplural1, conjunctivplural2, conjunctivplural3,
			conjunctiv2sing1, conjunctiv2sing2, conjunctiv2sing3,
			conjunctiv2plural1, conjunctiv2plural2, conjunctiv2plural3,
			cundizionalsing1, cundizionalsing2, cundizionalsing3,
			cundizionalplural1, cundizionalplural2, cundizionalplural3,
			cundizionalindirectsing1, cundizionalindirectsing2, cundizionalindirectsing3,
			cundizionalindirectplural1, cundizionalindirectplural2, cundizionalindirectplural3,
			participperfectms, participperfectfs, participperfectmp,
			participperfectfp, imperativ1, imperativ2, gerundium, futursing1,
			futursing2, futursing3, futurplural1, futurplural2, futurplural3,
			infinitiv, preschentsing1enclitic, preschentsing2enclitic, preschentsing3encliticm,
			preschentsing3encliticf, preschentplural1enclitic, preschentplural2enclitic,
			preschentplural3enclitic, imperfectsing1enclitic, imperfectsing2enclitic,
			imperfectsing3encliticm, imperfectsing3encliticf, imperfectplural1enclitic,
			imperfectplural2enclitic, imperfectplural3enclitic, cundizionalsing1enclitic,
			cundizionalsing2enclitic, cundizionalsing3encliticm, cundizionalsing3encliticf,
			cundizionalplural1enclitic, cundizionalplural2enclitic, cundizionalplural3enclitic,
	};

	private HashMap<String, String> values;

	private InflectionSubType conjugationClass;

	private String isReflexive;

	public HashMap<String, String> getValues() {
		return values;
	}

	public HashMap<String, String> getAllFormValues() {
		HashMap<String, String> returnValue = new HashMap<>();
		for (String s : msi) {
			returnValue.put(s, getValue(s));
		}
		returnValue.put("RInflectionSubtype", conjugationClass.id);
		returnValue.put("RInflectionType", InflectionType.V.toString());
		returnValue.put("RRegularInflection", "true");

		return returnValue;
	}

	public void setValues(HashMap<String, String> values) {
		this.values = values;
	}

	public SutsilvanConjugationStructure() {
		this.values = new HashMap<String, String>();
	}

	public String getVerb() {
		return values.get(verb);
	}

	public String getInfinitiv() {
		return values.get(infinitiv);
	}

	public String getRoot() {
		return values.get(root);
	}

	public String getEnding() {
		return values.get(ending);
	}

	public String getReflexive() {
		return values.get(reflexive);
	}

	public InflectionSubType getConjugationclass() {
		return conjugationClass;
	}

	public InflectionSubType getConjugationClass() {
		return conjugationClass;
	}

	public String getPreschentsing1() {
		return values.get(preschentsing1);
	}

	public String getPreschentsing2() {
		return values.get(preschentsing2);
	}

	public String getPreschentsing3() {
		return values.get(preschentsing3);
	}

	public String getPreschentplural1() {
		return values.get(preschentplural1);
	}

	public String getPreschentplural2() {
		return values.get(preschentplural2);
	}

	public String getPreschentplural3() {
		return values.get(preschentplural3);
	}

	public String getImperfectsing1() {
		return values.get(imperfectsing1);
	}

	public String getImperfectsing2() {
		return values.get(imperfectsing2);
	}

	public String getImperfectsing3() {
		return values.get(imperfectsing3);
	}

	public String getImperfectplural1() {
		return values.get(imperfectplural1);
	}

	public String getImperfectplural2() {
		return values.get(imperfectplural2);
	}

	public String getImperfectplural3() {
		return values.get(imperfectplural3);
	}

	public String getConjunctivsing1() {
		return values.get(conjunctivsing1);
	}

	public String getConjunctivsing2() {
		return values.get(conjunctivsing2);
	}

	public String getConjunctivsing3() {
		return values.get(conjunctivsing3);
	}

	public String getConjunctivplural1() {
		return values.get(conjunctivplural1);
	}

	public String getConjunctivplural2() {
		return values.get(conjunctivplural2);
	}

	public String getConjunctivplural3() {
		return values.get(conjunctivplural3);
	}

	public String getConjunctivimperfectsing1() {
		return values.get(conjunctiv2sing1);
	}

	public String getConjunctivimperfectsing2() {
		return values.get(conjunctiv2sing2);
	}

	public String getConjunctivimperfectsing3() {
		return values.get(conjunctiv2sing3);
	}

	public String getConjunctivimperfectplural1() {
		return values.get(conjunctiv2plural1);
	}

	public String getConjunctivimperfectplural2() {
		return values.get(conjunctiv2plural2);
	}

	public String getConjunctivimperfectplural3() {
		return values.get(conjunctiv2plural3);
	}



	public String getCundizionalsing1() {
		return values.get(cundizionalsing1);
	}

	public String getCundizionalsing2() {
		return values.get(cundizionalsing2);
	}

	public String getCundizionalsing3() {
		return values.get(cundizionalsing3);
	}

	public String getCundizionalplural1() {
		return values.get(cundizionalplural1);
	}

	public String getCundizionalplural2() {
		return values.get(cundizionalplural2);
	}

	public String getCundizionalplural3() {
		return values.get(cundizionalplural3);
	}

	public String getCundizionalindirectsing1() {
		return values.get(cundizionalindirectsing1);
	}

	public String getCundizionalindirectsing2() {
		return values.get(cundizionalindirectsing2);
	}

	public String getCundizionalindirectsing3() {
		return values.get(cundizionalindirectsing3);
	}

	public String getCundizionalindirectplural1() {
		return values.get(cundizionalindirectplural1);
	}

	public String getCundizionalindirectplural2() {
		return values.get(cundizionalindirectplural2);
	}

	public String getCundizionalindirectplural3() {
		return values.get(cundizionalindirectplural3);
	}

	public String getParticipperfectms() {
		return values.get(participperfectms);
	}

	public String getParticipperfectfs() {
		return values.get(participperfectfs);
	}

	public String getParticipperfectmp() {
		return values.get(participperfectmp);
	}

	public String getParticipperfectfp() {
		return values.get(participperfectfp);
	}

	public String getImperativ1() {
		return values.get(imperativ1);
	}

	public String getImperativ2() {
		return values.get(imperativ2);
	}

	public String getGerundium() {
		return values.get(gerundium);
	}

	public String getFutursing1() {
		return values.get(futursing1);
	}

	public String getFutursing2() {
		return values.get(futursing2);
	}

	public String getFutursing3() {
		return values.get(futursing3);
	}

	public String getFuturplural1() {
		return values.get(futurplural1);
	}

	public String getFuturplural2() {
		return values.get(futurplural2);
	}

	public String getFuturplural3() {
		return values.get(futurplural3);
	}

	public String getPreschentsing1Enclitic() {
		return values.get(preschentsing1enclitic);
	}

	public String getPreschentsing2Enclitic() {
		return values.get(preschentsing2enclitic);
	}

	public String getPreschentsing3EncliticM() {
		return values.get(preschentsing3encliticm);
	}

	public String getPreschentsing3EncliticF() {
		return values.get(preschentsing3encliticf);
	}

	public String getPreschentplural1Enclitic() {
		return values.get(preschentplural1enclitic);
	}

	public String getPreschentplural2Enclitic() {
		return values.get(preschentplural2enclitic);
	}

	public String getPreschentplural3Enclitic() {
		return values.get(preschentplural3enclitic);
	}

	public String getImperfectsing1Enclitic() {
		return values.get(imperfectsing1enclitic);
	}

	public String getImperfectsing2Enclitic() {
		return values.get(imperfectsing2enclitic);
	}

	public String getImperfectsing3EncliticM() {
		return values.get(imperfectsing3encliticm);
	}

	public String getImperfectsing3EncliticF() {
		return values.get(imperfectsing3encliticf);
	}

	public String getImperfectplural1Enclitic() {
		return values.get(imperfectplural1enclitic);
	}

	public String getImperfectplural2Enclitic() {
		return values.get(imperfectplural2enclitic);
	}

	public String getImperfectplural3Enclitic() {
		return values.get(imperfectplural3enclitic);
	}

	public String getCundizionalsing1Enclitic() {
		return values.get(cundizionalsing1enclitic);
	}

	public String getCundizionalsing2Enclitic() {
		return values.get(cundizionalsing2enclitic);
	}

	public String getCundizionalsing3EncliticM() {
		return values.get(cundizionalsing3encliticm);
	}

	public String getCundizionalsing3EncliticF() {
		return values.get(cundizionalsing3encliticf);
	}

	public String getCundizionalplural1Enclitic() {
		return values.get(cundizionalplural1enclitic);
	}

	public String getCundizionalplural2Enclitic() {
		return values.get(cundizionalplural2enclitic);
	}

	public String getCundizionalplural3Enclitic() {
		return values.get(cundizionalplural3enclitic);
	}

	public void setVerb(String v) {
		values.put(verb, v);
	}

	public void setInfinitiv(String inf) {
		values.put(infinitiv, inf);
	}

	public void setRoot(String r) {
		values.put(root, r);
	}

	public void setEnding(String end) {
		values.put(ending, end);
	}

	public void setReflexive(String ref) {
		values.put(reflexive, ref);
	}

	public void setConjugationClass(InflectionSubType inflectionSubType) {
		conjugationClass = inflectionSubType;
	}

	public void setPreschentsing1(String ps1) {
		values.put(preschentsing1, ps1);
	}

	public void setPreschentsing2(String ps2) {
		values.put(preschentsing2, ps2);
	}

	public void setPreschentsing3(String ps3) {
		values.put(preschentsing3, ps3);
	}

	public void setPreschentplural1(String pp1) {
		values.put(preschentplural1, pp1);
	}

	public void setPreschentplural2(String pp2) {
		values.put(preschentplural2, pp2);
	}

	public void setPreschentplural3(String pp3) {
		values.put(preschentplural3, pp3);
	}

	public void setImperfectsing1(String is1) {
		values.put(imperfectsing1, is1);
	}

	public void setImperfectsing2(String is2) {
		values.put(imperfectsing2, is2);
	}

	public void setImperfectsing3(String is3) {
		values.put(imperfectsing3, is3);
	}

	public void setImperfectplural1(String ip1) {
		values.put(imperfectplural1, ip1);
	}

	public void setImperfectplural2(String ip2) {
		values.put(imperfectplural2, ip2);
	}

	public void setImperfectplural3(String ip3) {
		values.put(imperfectplural3, ip3);
	}

	public void setConjunctivsing1(String cs1) {
		values.put(conjunctivsing1, cs1);
	}

	public void setConjunctivsing2(String cs2) {
		values.put(conjunctivsing2, cs2);
	}

	public void setConjunctivsing3(String cs3) {
		values.put(conjunctivsing3, cs3);
	}

	public void setConjunctivplural1(String cp1) {
		values.put(conjunctivplural1, cp1);
	}

	public void setConjunctivplural2(String cp2) {
		values.put(conjunctivplural2, cp2);
	}

	public void setConjunctivplural3(String cp3) {
		values.put(conjunctivplural3, cp3);
	}

	public void setConjunctivimperfectsing1(String cs1) {
		values.put(conjunctiv2sing1, cs1);
	}

	public void setConjunctivimperfectsing2(String cs2) {
		values.put(conjunctiv2sing2, cs2);
	}

	public void setConjunctivimperfectsing3(String cs3) {
		values.put(conjunctiv2sing3, cs3);
	}

	public void setConjunctivimperfectplural1(String cp1) {
		values.put(conjunctiv2plural1, cp1);
	}

	public void setConjunctivimperfectplural2(String cp2) {
		values.put(conjunctiv2plural2, cp2);
	}

	public void setConjunctivimperfectplural3(String cp3) {
		values.put(conjunctiv2plural3, cp3);
	}

	public void setCundizionalsing1(String cus1) {
		values.put(cundizionalsing1, cus1);
	}

	public void setCundizionalsing2(String cus2) {
		values.put(cundizionalsing2, cus2);
	}

	public void setCundizionalsing3(String cus3) {
		values.put(cundizionalsing3, cus3);
	}

	public void setCundizionalplural1(String cup1) {
		values.put(cundizionalplural1, cup1);
	}

	public void setCundizionalplural2(String cup2) {
		values.put(cundizionalplural2, cup2);
	}

	public void setCundizionalplural3(String cup3) {
		values.put(cundizionalplural3, cup3);
	}

	public void setCundizionalindirectsing1(String cus1) {
		values.put(cundizionalindirectsing1, cus1);
	}

	public void setCundizionalindirectsing2(String cus2) {
		values.put(cundizionalindirectsing2, cus2);
	}

	public void setCundizionalindirectsing3(String cus3) {
		values.put(cundizionalindirectsing3, cus3);
	}

	public void setCundizionalindirectplural1(String cup1) {
		values.put(cundizionalindirectplural1, cup1);
	}

	public void setCundizionalindirectplural2(String cup2) {
		values.put(cundizionalindirectplural2, cup2);
	}

	public void setCundizionalindirectplural3(String cup3) {
		values.put(cundizionalindirectplural3, cup3);
	}

	public void setParticipperfectms(String ppms) {
		values.put(participperfectms, ppms);
	}

	public void setParticipperfectfs(String ppfs) {
		values.put(participperfectfs, ppfs);
	}

	public void setParticipperfectmp(String ppmp) {
		values.put(participperfectmp, ppmp);
	}

	public void setParticipperfectfp(String ppfp) {
		values.put(participperfectfp, ppfp);
	}

	public void setImperativ1(String imp1) {
		values.put(imperativ1, imp1);
	}

	public void setImperativ2(String imp2) {
		values.put(imperativ2, imp2);
	}

	public void setGerundium(String ger) {
		values.put(gerundium, ger);
	}

	public void setFutursing1(String futs1) {
		values.put(futursing1, futs1);
	}

	public void setFutursing2(String futs2) {
		values.put(futursing2, futs2);
	}

	public void setFutursing3(String futs3) {
		values.put(futursing3, futs3);
	}

	public void setFuturplural1(String futp1) {
		values.put(futurplural1, futp1);
	}

	public void setFuturplural2(String futp2) {
		values.put(futurplural2, futp2);
	}

	public void setFuturplural3(String futp3) {
		values.put(futurplural3, futp3);
	}

	public void setPreschentsing1Enclitic(String ps1) {
		values.put(preschentsing1enclitic, ps1);
	}

	public void setPreschentsing2Enclitic(String ps2) {
		values.put(preschentsing2enclitic, ps2);
	}

	public void setPreschentsing3EncliticM(String ps3) {
		values.put(preschentsing3encliticm, ps3);
	}

	public void setPreschentsing3EncliticF(String ps3) {
		values.put(preschentsing3encliticf, ps3);
	}

	public void setPreschentplural1Enclitic(String pp1) {
		values.put(preschentplural1enclitic, pp1);
	}

	public void setPreschentplural2Enclitic(String pp2) {
		values.put(preschentplural2enclitic, pp2);
	}

	public void setPreschentplural3Enclitic(String pp3) {
		values.put(preschentplural3enclitic, pp3);
	}

	public void setImperfectsing1Enclitic(String is1) {
		values.put(imperfectsing1enclitic, is1);
	}

	public void setImperfectsing2Enclitic(String is2) {
		values.put(imperfectsing2enclitic, is2);
	}

	public void setImperfectsing3EncliticM(String is3) {
		values.put(imperfectsing3encliticm, is3);
	}

	public void setImperfectsing3EncliticF(String is3) {
		values.put(imperfectsing3encliticf, is3);
	}

	public void setImperfectplural1Enclitic(String ip1) {
		values.put(imperfectplural1enclitic, ip1);
	}

	public void setImperfectplural2Enclitic(String ip2) {
		values.put(imperfectplural2enclitic, ip2);
	}

	public void setImperfectplural3Enclitic(String ip3) {
		values.put(imperfectplural3enclitic, ip3);
	}

	public void setCundizionalsing1Enclitic(String cus1) {
		values.put(cundizionalsing1enclitic, cus1);
	}

	public void setCundizionalsing2Enclitic(String cus2) {
		values.put(cundizionalsing2enclitic, cus2);
	}

	public void setCundizionalsing3EncliticM(String cus3) {
		values.put(cundizionalsing3encliticm, cus3);
	}

	public void setCundizionalsing3EncliticF(String cus3) {
		values.put(cundizionalsing3encliticf, cus3);
	}

	public void setCundizionalplural1Enclitic(String cup1) {
		values.put(cundizionalplural1enclitic, cup1);
	}

	public void setCundizionalplural2Enclitic(String cup2) {
		values.put(cundizionalplural2enclitic, cup2);
	}

	public void setCundizionalplural3Enclitic(String cup3) {
		values.put(cundizionalplural3enclitic, cup3);
	}

	public String getIsReflexive() {
		return isReflexive;
	}

	public void setIsReflexive(String isReflexive) {
		this.isReflexive = isReflexive;
	}

	public String getValue(String key) {
		switch (key) {

			case verb:
				return getVerb();
			case infinitiv:
				return getInfinitiv();
			case root:
				return getRoot();
			case ending:
				return getEnding();
			case reflexive:
				return getReflexive();
			case preschentsing1:
				return getPreschentsing1();
			case preschentsing2:
				return getPreschentsing2();
			case preschentsing3:
				return getPreschentsing3();
			case preschentplural1:
				return getPreschentplural1();
			case preschentplural2:
				return getPreschentplural2();
			case preschentplural3:
				return getPreschentplural3();
			case imperfectsing1:
				return getImperfectsing1();
			case imperfectsing2:
				return getImperfectsing2();
			case imperfectsing3:
				return getImperfectsing3();
			case imperfectplural1:
				return getImperfectplural1();
			case imperfectplural2:
				return getImperfectplural2();
			case imperfectplural3:
				return getImperfectplural3();
			case conjunctivsing1:
				return getConjunctivsing1();
			case conjunctivsing2:
				return getConjunctivsing2();
			case conjunctivsing3:
				return getConjunctivsing3();
			case conjunctivplural1:
				return getConjunctivplural1();
			case conjunctivplural2:
				return getConjunctivplural2();
			case conjunctivplural3:
				return getConjunctivplural3();
			case conjunctiv2sing1:
				return getConjunctivimperfectsing1();
			case conjunctiv2sing2:
				return getConjunctivimperfectsing2();
			case conjunctiv2sing3:
				return getConjunctivimperfectsing3();
			case conjunctiv2plural1:
				return getConjunctivimperfectplural1();
			case conjunctiv2plural2:
				return getConjunctivimperfectplural2();
			case conjunctiv2plural3:
				return getConjunctivimperfectplural3();
			case cundizionalsing1:
				return getCundizionalsing1();
			case cundizionalsing2:
				return getCundizionalsing2();
			case cundizionalsing3:
				return getCundizionalsing3();
			case cundizionalplural1:
				return getCundizionalplural1();
			case cundizionalplural2:
				return getCundizionalplural2();
			case cundizionalplural3:
				return getCundizionalplural3();
			case cundizionalindirectsing1:
				return getCundizionalindirectsing1();
			case cundizionalindirectsing2:
				return getCundizionalindirectsing2();
			case cundizionalindirectsing3:
				return getCundizionalindirectsing3();
			case cundizionalindirectplural1:
				return getCundizionalindirectplural1();
			case cundizionalindirectplural2:
				return getCundizionalindirectplural2();
			case cundizionalindirectplural3:
				return getCundizionalindirectplural3();
			case participperfectms:
				return getParticipperfectms();
			case participperfectfs:
				return getParticipperfectfs();
			case participperfectmp:
				return getParticipperfectmp();
			case participperfectfp:
				return getParticipperfectfp();
			case imperativ1:
				return getImperativ1();
			case imperativ2:
				return getImperativ2();
			case gerundium:
				return getGerundium();
			case futursing1:
				return getFutursing1();
			case futursing2:
				return getFutursing2();
			case futursing3:
				return getFutursing3();
			case futurplural1:
				return getFuturplural1();
			case futurplural2:
				return getFuturplural2();
			case futurplural3:
				return getFuturplural3();
			case preschentsing1enclitic:
				return getPreschentsing1Enclitic();
			case preschentsing2enclitic:
				return getPreschentsing2Enclitic();
			case preschentsing3encliticm:
				return getPreschentsing3EncliticM();
			case preschentsing3encliticf:
				return getPreschentsing3EncliticF();
			case preschentplural1enclitic:
				return getPreschentplural1Enclitic();
			case preschentplural2enclitic:
				return getPreschentplural2Enclitic();
			case preschentplural3enclitic:
				return getPreschentplural3Enclitic();
			case imperfectsing1enclitic:
				return getImperfectsing1Enclitic();
			case imperfectsing2enclitic:
				return getImperfectsing2Enclitic();
			case imperfectsing3encliticm:
				return getImperfectsing3EncliticM();
			case imperfectsing3encliticf:
				return getImperfectsing3EncliticF();
			case imperfectplural1enclitic:
				return getImperfectplural1Enclitic();
			case imperfectplural2enclitic:
				return getImperfectplural2Enclitic();
			case imperfectplural3enclitic:
				return getImperfectplural3Enclitic();
			case cundizionalsing1enclitic:
				return getCundizionalsing1Enclitic();
			case cundizionalsing2enclitic:
				return getCundizionalsing2Enclitic();
			case cundizionalsing3encliticm:
				return getCundizionalsing3EncliticM();
			case cundizionalsing3encliticf:
				return getCundizionalsing3EncliticF();
			case cundizionalplural1enclitic:
				return getCundizionalplural1Enclitic();
			case cundizionalplural2enclitic:
				return getCundizionalplural2Enclitic();
			case cundizionalplural3enclitic:
				return getCundizionalplural3Enclitic();
			default:
				break;
		}
		return values.get(key);
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(values.get(verb));
		buffer.append("\n");

		// Preschaint
		buffer.append(values.get(preschentsing1));
		buffer.append("\t");
		buffer.append(values.get(preschentsing2));
		buffer.append("\t");
		buffer.append(values.get(preschentsing3));
		buffer.append("\t");
		buffer.append(values.get(preschentplural1));
		buffer.append("\t");
		buffer.append(values.get(preschentplural2));
		buffer.append("\t");
		buffer.append(values.get(preschentplural3));
		buffer.append("\n");

		// Imperfect
		buffer.append(values.get(imperfectsing1));
		buffer.append("\t");
		buffer.append(values.get(imperfectsing2));
		buffer.append("\t");
		buffer.append(values.get(imperfectsing3));
		buffer.append("\t");
		buffer.append(values.get(imperfectplural1));
		buffer.append("\t");
		buffer.append(values.get(imperfectplural2));
		buffer.append("\t");
		buffer.append(values.get(imperfectplural3));
		buffer.append("\n");

		// Imperfect
		buffer.append(values.get(futursing1));
		buffer.append("\t");
		buffer.append(values.get(futursing2));
		buffer.append("\t");
		buffer.append(values.get(futursing3));
		buffer.append("\t");
		buffer.append(values.get(futurplural1));
		buffer.append("\t");
		buffer.append(values.get(futurplural2));
		buffer.append("\t");
		buffer.append(values.get(futurplural3));
		buffer.append("\n");

		// Conjunctiv
		buffer.append(values.get(conjunctivsing1));
		buffer.append("\t");
		buffer.append(values.get(conjunctivsing2));
		buffer.append("\t");
		buffer.append(values.get(conjunctivsing3));
		buffer.append("\t");
		buffer.append(values.get(conjunctivplural1));
		buffer.append("\t");
		buffer.append(values.get(conjunctivplural2));
		buffer.append("\t");
		buffer.append(values.get(conjunctivplural3));
		buffer.append("\n");

		// Conjunctiv imperfect
		buffer.append(values.get(conjunctiv2sing1));
		buffer.append("\t");
		buffer.append(values.get(conjunctiv2sing2));
		buffer.append("\t");
		buffer.append(values.get(conjunctiv2sing3));
		buffer.append("\t");
		buffer.append(values.get(conjunctiv2plural1));
		buffer.append("\t");
		buffer.append(values.get(conjunctiv2plural2));
		buffer.append("\t");
		buffer.append(values.get(conjunctiv2plural3));
		buffer.append("\n");

		// Cundizional
		buffer.append(values.get(cundizionalsing1));
		buffer.append("\t");
		buffer.append(values.get(cundizionalsing1));
		buffer.append("\t");
		buffer.append(values.get(cundizionalsing1));
		buffer.append("\t");
		buffer.append(values.get(cundizionalplural1));
		buffer.append("\t");
		buffer.append(values.get(cundizionalplural2));
		buffer.append("\t");
		buffer.append(values.get(cundizionalplural3));
		buffer.append("\n");

		// Cundizional indirect
		buffer.append(values.get(cundizionalindirectsing1));
		buffer.append("\t");
		buffer.append(values.get(cundizionalindirectsing1));
		buffer.append("\t");
		buffer.append(values.get(cundizionalindirectsing1));
		buffer.append("\t");
		buffer.append(values.get(cundizionalindirectplural1));
		buffer.append("\t");
		buffer.append(values.get(cundizionalindirectplural2));
		buffer.append("\t");
		buffer.append(values.get(cundizionalindirectplural3));
		buffer.append("\n");

		// Partizip
		buffer.append(values.get(participperfectms));
		buffer.append("\t");
		buffer.append(values.get(participperfectmp));
		buffer.append("\t");
		buffer.append(values.get(participperfectfs));
		buffer.append("\t");
		buffer.append(values.get(participperfectfp));
		buffer.append("\n");

		// Gerundium
		buffer.append(values.get(gerundium));
		buffer.append("\n");

		// Partizip
		buffer.append(values.get(imperativ1));
		buffer.append("\t");
		buffer.append(values.get(imperativ2));
		buffer.append("\n");

		return buffer.toString();
	}
}