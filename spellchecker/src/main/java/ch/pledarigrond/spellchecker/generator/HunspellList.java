package ch.pledarigrond.spellchecker.generator;

import ch.pledarigrond.spellchecker.model.SpellcheckerRules;

import java.util.*;

public class HunspellList {
    private final Map<String, Set<SpellcheckerRules>> wordsList = new TreeMap<>();

    public void addWord(String word, SpellcheckerRules[] rules) {
        if (word == null) {
            return;
        }

        if (word.equals("") || word.equals(" ")) {
            return;
        }

        // Ignore ( ) ...
        if (word.contains("(") || word.contains(")") || word.contains("...")) {
            return;
        }

        if (word.endsWith("!")) {
            word = word.substring(0, word.length() - 1);
        }

        Set<SpellcheckerRules> r = wordsList.get(word);
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

    public String generateHunspellEntry(String word, Set<SpellcheckerRules> rules) {
        StringBuilder sb = new StringBuilder();
        sb.append(word);
        if (rules.size() > 0) {
            sb.append("/");
            rules.forEach(sb::append);
        }
        return sb.toString();
    }
}
