package ch.pledarigrond.inflection.generation.sursilvan;

import java.util.HashMap;
import java.util.Map;

public class SursilvanPronouns {

	// Infinitiv
	public static final String infinitiv = "infinitiv";

	// Standard Conjugation
	public static final String first_ps = "1ps";
	public static final String second_ps = "2ps";
	public static final String third_ps = "3ps";
	public static final String first_pp = "1pp";
	public static final String second_pp = "2pp";
	public static final String third_pp = "3pp";

	// Conjunctiv
	public static final String first_ps_c = "1ps_c";
	public static final String second_ps_c = "2ps_c";
	public static final String third_ps_c = "3ps_c";
	public static final String first_pp_c = "1pp_c";
	public static final String second_pp_c = "2pp_c";
	public static final String third_pp_c = "3pp_c";

	// Particip perfect
	public static final String pp_1 = "pp_1";
	public static final String pp_2 = "pp_2";
	public static final String pp_3 = "pp_3";
	public static final String pp_4 = "pp_4";
	public static final String pp_5 = "pp_5";

	// Imperativ
	public static final String imperat_1 = "imperat_1";
	public static final String imperat_2 = "imperat_2";

	// Gerundium
	public static final String gerund = "gerund";

	// Personal pronouns
	public static final String pron_1ps = "jeu ";
	public static final String pron_2ps = "ti ";
	public static final String pron_3ps = "el/ella ";
	public static final String pron_3ps_impers = "i ";
	public static final String pron_1pp = "nus ";
	public static final String pron_2pp = "vus ";
	public static final String pron_3pp = "els/ellas ";
	// Reflexive pronouns for verbs with a consonant as first letter
	public static final String pron_r = "se";
	// Reflexive pronouns for verbs with a vowel as first letter
	public static final String pron_r_v = "s'";

	// Conjunctiv
	public static final String pron_conjunctiv_c = "che ";
	public static final String pron_conjunctiv_v = "ch'";



	public SursilvanPronouns() {
		this.values = new HashMap<>();
	}

	private Map<String, String> values;

	public Map<String, String> getValues() {
		return values;
	}

	public void setValues(Map<String, String> values) {
		this.values = values;
	}

	public String getInfinitiv() {
		return values.get(infinitiv);
	}

	public String getFirstPs() {
		return values.get(first_ps);
	}

	public String getSecondPs() {
		return values.get(second_ps);
	}

	public String getThirdPs() {
		return values.get(third_ps);
	}

	public String getFirstPp() {
		return values.get(first_pp);
	}

	public String getSecondPp() {
		return values.get(second_pp);
	}

	public String getThirdPp() {
		return values.get(third_pp);
	}

	public String getFirstPsC() {
		return values.get(first_ps_c);
	}

	public String getSecondPsC() {
		return values.get(second_ps_c);
	}

	public String getThirdPsC() {
		return values.get(third_ps_c);
	}

	public String getFirstPpC() {
		return values.get(first_pp_c);
	}

	public String getSecondPpC() {
		return values.get(second_pp_c);
	}

	public String getThirdPpC() {
		return values.get(third_pp_c);
	}

	public String getPp_1() {
		return values.get(pp_1);
	}

	public String getPp_2() {
		return values.get(pp_2);
	}

	public String getPp_3() {
		return values.get(pp_3);
	}

	public String getPp_4() {
		return values.get(pp_4);
	}

	public String getPp_5() {
		return values.get(pp_5);
	}

	public String getImperat1() {
		return values.get(imperat_1);
	}

	public String getImperat2() {
		return values.get(imperat_2);
	}

	public String getGer() {
		return values.get(gerund);
	}

	public void setInfinitiv(String inf) {
		values.put(infinitiv, inf);
	}

	public void setFirstPs(String fps) {
		values.put(first_ps, fps);
	}

	public void setSecondPs(String sps) {
		values.put(second_ps, sps);
	}

	public void setThirdPs(String tps) {
		values.put(third_ps, tps);
	}

	public void setFirstPp(String fpp) {
		values.put(first_pp, fpp);
	}

	public void setSecondPp(String spp) {
		values.put(second_pp, spp);
	}

	public void setThirdPp(String tpp) {
		values.put(third_pp, tpp);
	}

	public void setFirstPsC(String fps_c) {
		values.put(first_ps_c, fps_c);
	}

	public void setSecondPsC(String sps_c) {
		values.put(second_ps_c, sps_c);
	}

	public void setThirdPsC(String tps_c) {
		values.put(third_ps_c, tps_c);
	}

	public void setFirstPpC(String fpp_c) {
		values.put(first_pp_c, fpp_c);
	}

	public void setSecondPpC(String spp_c) {
		values.put(second_pp_c, spp_c);
	}

	public void setThirdPpC(String tpp_c) {
		values.put(third_pp_c, tpp_c);
	}

	public void setPp_1(String ms) {
		values.put(pp_1, ms);
	}

	public void setPp_2(String fs) {
		values.put(pp_2, fs);
	}

	public void setPp_3(String mp) {
		values.put(pp_3, mp);
	}

	public void setPp_4(String fp) {
		values.put(pp_4, fp);
	}

	public void setPp_5(String fp) {
		values.put(pp_5, fp);
	}

	public void setImperat1(String imp1) {
		values.put(imperat_1, imp1);

	}

	public void setImperat2(String imp2) {
		values.put(imperat_2, imp2);

	}

	public void setGer(String ger) {
		values.put(gerund, ger);

	}

	public String getValue(String key) {
		switch (key) {
			case infinitiv:
				return getInfinitiv();
			case first_ps:
				return getFirstPs();
			case second_ps:
				return getSecondPs();
			case third_ps:
				return getThirdPs();
			case first_pp:
				return getFirstPp();
			case second_pp:
				return getSecondPp();
			case third_pp:
				return getThirdPp();
			case first_ps_c:
				return getFirstPsC();
			case second_ps_c:
				return getSecondPsC();
			case third_ps_c:
				return getThirdPsC();
			case first_pp_c:
				return getFirstPpC();
			case second_pp_c:
				return getSecondPpC();
			case third_pp_c:
				return getThirdPpC();
			case pp_1:
				return getPp_1();
			case pp_2:
				return getPp_2();
			case pp_3:
				return getPp_3();
			case pp_4:
				return getPp_4();
			case pp_5:
				return getPp_5();
			case imperat_1:
				return getImperat1();
			case imperat_2:
				return getImperat2();
			case gerund:
				return getGer();
			default:
				break;
		}
		return values.get(key);
	}
}
