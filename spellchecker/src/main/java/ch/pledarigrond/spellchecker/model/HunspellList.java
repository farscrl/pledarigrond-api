package ch.pledarigrond.spellchecker.model;

import ch.pledarigrond.spellchecker.generator.WordListUtils;
import ch.pledarigrond.spellchecker.model.HunspellRules;

import java.util.*;

public class HunspellList {
    private final Map<String, Set<HunspellRules>> wordsList = new TreeMap<>();

    public void addWord(String word, HunspellRules[] rules) {
        word = WordListUtils.normalizeWord(word);
        if (word == null) {
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
}
