package ch.pledarigrond.inflection.generation.vallader;

import ch.pledarigrond.inflection.model.InflectionSubType;
import ch.pledarigrond.inflection.model.InflectionType;

import java.util.HashMap;

public class ValladerAdjectiveStructure {
    public static final String baseForm = "baseForm";
    public static final String root = "root";
    public static final String ending = "ending";
    public static final String adjectiveClass = "adjectiveClass";

    public static final String mSingular = "mSingular";
    public static final String fSingular = "fSingular";
    public static final String mPlural = "mPlural";
    public static final String fPlural = "fPlural";
    public static final String adverbialForm = "adverbialForm";


    public String[] msi = new String[] {
            baseForm, mSingular, fSingular,
            mPlural, fPlural, adverbialForm
    };

    private HashMap<String, String> values;

    private InflectionSubType inflectionSubType;

    public HashMap<String, String> getValues() {
        return values;
    }

    public HashMap<String, String> getAllFormValues() {
        HashMap<String, String> returnValue = new HashMap<>();
        for (String s : msi) {
            returnValue.put(s, getValue(s));
        }
        returnValue.put("RInflectionSubtype", inflectionSubType.id);
        returnValue.put("RInflectionType", InflectionType.ADJECTIVE.toString());
        returnValue.put("RRegularInflection", "true");

        return returnValue;
    }

    public void setValues(HashMap<String, String> values) {
        this.values = values;

    }

    public ValladerAdjectiveStructure() {
        this.values = new HashMap<String, String>();

    }

    public String getBaseForm() {
        return values.get(baseForm);
    }

    public String getRoot() {
        return values.get(root);
    }

    public String getEnding() {
        return values.get(ending);
    }

    public String getAdjectiveClass() {
        return values.get(adjectiveClass);
    }

    public String getMSingular() {
        return values.get(mSingular);
    }

    public String getMPlural() {
        return values.get(mPlural);
    }

    public String getFSingular() {
        return values.get(fSingular);
    }

    public String getFPlural() {
        return values.get(fPlural);
    }

    public String getAdverbialForm() {
        return values.get(adverbialForm);
    }

    public void setBaseForm(String b) {
        values.put(baseForm, b);
    }

    public void setRoot(String r) {
        values.put(root, r);
    }

    public void setEnding(String end) {
        values.put(ending, end);
    }


    public void setAdjectiveClass(String nc) {
        values.put(adjectiveClass, nc);
    }

    public void setMSingular(String ms) {
        values.put(mSingular, ms);
    }

    public void setMPlural(String mp) {
        values.put(mPlural, mp);
    }

    public void setFSingular(String fs) {
        values.put(fSingular, fs);
    }

    public void setFPlural(String fp) {
        values.put(fPlural, fp);
    }

    public void setAdverbialForm(String fp) {
        values.put(adverbialForm, fp);
    }

    public InflectionSubType getInflectionSubType() {
        return inflectionSubType;
    }

    public void setInflectionSubType(InflectionSubType inflectionSubType) {
        this.inflectionSubType = inflectionSubType;
    }

    public String getValue(String key) {
        switch (key) {
            case baseForm:
                return getBaseForm();
            case root:
                return getRoot();
            case ending:
                return getEnding();
            case adjectiveClass:
                return getAdjectiveClass();
            case mSingular:
                return getMSingular();
            case fSingular:
                return getFSingular();
            case mPlural:
                return getMPlural();
            case fPlural:
                return getFPlural();
            case adverbialForm:
                return getAdverbialForm();

            default:
                break;
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(values.get(baseForm));
        buffer.append("\n");

        buffer.append(values.get(mSingular));
        buffer.append("\t");
        buffer.append(values.get(fSingular));
        buffer.append("\t");
        buffer.append(values.get(mPlural));
        buffer.append("\t");
        buffer.append(values.get(fPlural));
        buffer.append("\n");

        return buffer.toString();
    }
}
