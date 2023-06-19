package ch.pledarigrond.spellchecker.model;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class HunspellListTest {
    @Test
    public void testNormalWord() {
        HunspellList hunspellList = new HunspellList();
        hunspellList.addWord("test", new HunspellRules[]{HunspellRules.SURMIRAN_PRONOMS_REFLEXIVS});
        assertTrue(CollectionUtils.isEqualCollection(hunspellList.getListAsSet(), List.of("test/P")));

        hunspellList = new HunspellList();
        hunspellList.addWord("", new HunspellRules[]{HunspellRules.SURMIRAN_PRONOMS_REFLEXIVS});
        assertTrue(CollectionUtils.isEqualCollection(hunspellList.getListAsSet(), List.of()));

        hunspellList = new HunspellList();
        hunspellList.addWord(" ", new HunspellRules[]{HunspellRules.SURMIRAN_PRONOMS_REFLEXIVS});
        assertTrue(CollectionUtils.isEqualCollection(hunspellList.getListAsSet(), List.of()));

        hunspellList = new HunspellList();
        hunspellList.addWord("test...", new HunspellRules[]{HunspellRules.SURMIRAN_PRONOMS_REFLEXIVS});
        assertTrue(CollectionUtils.isEqualCollection(hunspellList.getListAsSet(), List.of()));

        hunspellList = new HunspellList();
        hunspellList.addWord("test!", new HunspellRules[]{HunspellRules.SURMIRAN_PRONOMS_REFLEXIVS});
        assertTrue(CollectionUtils.isEqualCollection(hunspellList.getListAsSet(), List.of("test/P")));

        hunspellList = new HunspellList();
        hunspellList.addWord("test?", new HunspellRules[]{HunspellRules.SURMIRAN_PRONOMS_REFLEXIVS});
        assertTrue(CollectionUtils.isEqualCollection(hunspellList.getListAsSet(), List.of("test/P")));

        hunspellList = new HunspellList();
        hunspellList.addWord("test,", new HunspellRules[]{HunspellRules.SURMIRAN_PRONOMS_REFLEXIVS});
        assertTrue(CollectionUtils.isEqualCollection(hunspellList.getListAsSet(), List.of("test/P")));
    }

    @Test
    public void testBracesWord() {
        HunspellList hunspellList = new HunspellList();
        hunspellList.addWord("test(er)", new HunspellRules[]{HunspellRules.SURMIRAN_PRONOMS_REFLEXIVS});
        assertTrue(CollectionUtils.isEqualCollection(hunspellList.getListAsSet(), List.of("test/P", "tester/P")));

        hunspellList = new HunspellList();
        hunspellList.addWord("(da)schnu(v)ar", new HunspellRules[]{HunspellRules.SURMIRAN_PRONOMS_REFLEXIVS});
        assertTrue(CollectionUtils.isEqualCollection(hunspellList.getListAsSet(), List.of("daschnuar/P", "schnuar/P", "daschnuvar/P", "schnuvar/P")));

        hunspellList = new HunspellList();
        hunspellList.addWord("(da)schnu(var", new HunspellRules[]{HunspellRules.SURMIRAN_PRONOMS_REFLEXIVS});
        assertTrue(CollectionUtils.isEqualCollection(hunspellList.getListAsSet(), List.of("daschnuvar/P", "schnuvar/P")));
    }
}
