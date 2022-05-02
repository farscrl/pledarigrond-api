package ch.pledarigrond.inflection.generation.surmiran;

import java.util.HashMap;

public class SurmiranNounStructure {
    public static final String base = "base";
    public static final String root = "root";
    public static final String ending = "ending";
    public static final String nounClass = "nounClass";

    public static final String mSingular = "mSingular";
    public static final String fSingular = "fSingular";
    public static final String mPlural = "mPlural";
    public static final String fPlural = "fPlural";
    public static final String pluralCollectiv = "pluralCollectiv";


    public String[] msi = new String[] {
            mSingular, fSingular,
            mPlural, fPlural, pluralCollectiv
    };

    private HashMap<String, String> values;

    public HashMap<String, String> getValues() {
        return values;
    }

    public HashMap<String, String> getAllFormValues() {
        HashMap<String, String> returnValue = new HashMap<>();
        for (String s : msi) {
            returnValue.put(s, getValue(s));
        }

        return returnValue;
    }

    public void setValues(HashMap<String, String> values) {
        this.values = values;

    }

    public SurmiranNounStructure() {
        this.values = new HashMap<String, String>();

    }

    public String getBase() {
        return values.get(base);
    }

    public String getRoot() {
        return values.get(root);
    }

    public String getEnding() {
        return values.get(ending);
    }

    public String getNounClass() {
        return values.get(nounClass);
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

    public String getPluralCollectiv() {
        return values.get(pluralCollectiv);
    }

    public void setBase(String b) {
        values.put(base, b);
    }

    public void setRoot(String r) {
        values.put(root, r);
    }

    public void setEnding(String end) {
        values.put(ending, end);
    }


    public void setNounClass(String nc) {
        values.put(nounClass, nc);
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

    public void setPluralCollectiv(String coll) {
        values.put(pluralCollectiv, coll);
    }


    public String getValue(String key) {
        switch (key) {
            case base:
                return getBase();
            case root:
                return getRoot();
            case ending:
                return getEnding();
            case nounClass:
                return getNounClass();
            case mSingular:
                return getMSingular();
            case fSingular:
                return getFSingular();
            case mPlural:
                return getMPlural();
            case fPlural:
                return getFPlural();
            case pluralCollectiv:
                return getPluralCollectiv();

            default:
                break;
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(values.get(base));
        buffer.append("\n");

        buffer.append(values.get(mSingular));
        buffer.append("\t");
        buffer.append(values.get(fSingular));
        buffer.append("\t");
        buffer.append(values.get(mPlural));
        buffer.append("\t");
        buffer.append(values.get(fPlural));
        buffer.append("\t");
        buffer.append(values.get(pluralCollectiv));
        buffer.append("\n");

        return buffer.toString();
    }
}
