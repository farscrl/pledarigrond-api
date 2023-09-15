package ch.pledarigrond.spellchecker.model;

import ch.pledarigrond.common.util.PronunciationNormalizer;
import ch.pledarigrond.spellchecker.generator.WordListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HunspellList {

    private static final Logger LOG = LoggerFactory.getLogger(HunspellList.class);
    private final Map<String, Set<HunspellRules>> wordsList = new TreeMap<>();

    public void addWord(String word, HunspellRules[] rules) {
        addWord(word, rules, true);
    }
    
    public void addWord(String word, HunspellRules[] rules, boolean rejectWordsWithWhitespace) {
        if (word == null) {
            return;
        }

        word = PronunciationNormalizer.normalizePronunciation(word);

        if (word.contains("(") || word.contains(")")) {
            findBrackets(word).forEach(w -> addWord(w, rules));
            return;
        }

        if (word.contains("[") || word.contains("]")) {
            findSquaredBrackets(word).forEach(w -> addWord(w, rules));
            return;
        }

        word = WordListUtils.normalizeWordListEntry(word);
        if (word == null) {
            return;
        }

        // ignore composed words
        if (rejectWordsWithWhitespace && word.contains(" ")) {
            return;
        }

        Set<HunspellRules> r = wordsList.get(word);
        if (r== null) {
            r = new TreeSet<>();
        }
        r.addAll(List.of(rules));
        wordsList.put(word, r);
    }

    public Set<String> getListAsSet() {
        Set<String> set = new TreeSet<>();

        for (var entry : wordsList.entrySet()) {
            set.add(generateHunspellEntry(entry.getKey(), entry.getValue()));
        }

        return set;
    }

    public void removeWord(String word) {
        wordsList.remove(word);
    }

    public String generateHunspellEntry(String word, Set<HunspellRules> rules) {
        StringBuilder sb = new StringBuilder();
        sb.append(word);
        if (rules.size() > 0) {
            sb.append("/");
            rules.forEach(sb::append);
        }
        return sb.toString();
    }

    public static List<String> findBrackets(String str){
        // LOG.info(str);

        String pattern = "(.*)\\((.+)\\)(.*)";

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        if (m.matches())
        {
            List<String> prefix = findBrackets(m.group(1));
            String middle = m.group(2);
            List<String> suffix = findBrackets(m.group(3));

            List<String> result = new ArrayList<>();
            prefix.forEach(pfx -> {
                suffix.forEach(sfx -> {
                    result.add(pfx + middle + sfx);
                    result.add(pfx + sfx);
                });
            });
            return result;
        } else {
            if (str.contains("(") || str.contains(")")) {
                LOG.error("Could not parse: {}", str);
            }
            return List.of(str.replace("(", "").replace(")", ""));
        }
    }

    public static List<String> findSquaredBrackets(String str){
        // LOG.info(str);

        String pattern = "(.*)\\[(.+)\\](.*)";

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        if (m.matches())
        {
            List<String> prefix = findSquaredBrackets(m.group(1));
            String middle = m.group(2);
            List<String> suffix = findSquaredBrackets(m.group(3));

            List<String> result = new ArrayList<>();
            prefix.forEach(pfx -> {
                suffix.forEach(sfx -> {
                    result.add(pfx + middle + sfx);
                    result.add(pfx + sfx);
                });
            });
            return result;
        } else {
            if (str.contains("[") || str.contains("]")) {
                LOG.error("Could not parse: {}", str);
            }
            return List.of(str.replace("[", "").replace("]", ""));
        }
    }

    public void applyRuleOnCondition(CheckCondition condition, HunspellRules rule) {
        wordsList.forEach((word, rules) -> {
            if (condition.check(word)) {
                rules.add(rule);
            }
        });
    }

    public void separateWordsWithSlash() {
        Map<String, HunspellRules[]> newEntries = new TreeMap<>();

        Iterator<Map.Entry<String, Set<HunspellRules>>> itr = wordsList.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, Set<HunspellRules>> entry = itr.next();

            String word = entry.getKey();
            if (word.contains("/")) {
                String[] words = word.split("/");

                HunspellRules[] rules = new HunspellRules[entry.getValue().size()];
                entry.getValue().toArray(rules);

                for (String w : words) {
                    newEntries.put(w, rules);
                }
                itr.remove();
            }
        }

        newEntries.forEach(this::addWord);
    }

    public interface CheckCondition {
        boolean check(String word);
    }
}
