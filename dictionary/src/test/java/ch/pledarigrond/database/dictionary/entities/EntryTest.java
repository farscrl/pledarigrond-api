package ch.pledarigrond.database.dictionary.entities;

import ch.pledarigrond.common.data.common.Action;
import ch.pledarigrond.common.data.common.EditorRole;
import ch.pledarigrond.common.data.common.UserInfoDto;
import ch.pledarigrond.common.exception.dictionary.InvalidDataException;
import ch.pledarigrond.common.exception.dictionary.SuggestionNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class EntryTest {

    private Entry entry;
    private UserInfoDto userInfo;

    @BeforeEach
    public void setUp() {
        entry = new Entry();
        userInfo = new UserInfoDto();
        userInfo.setEmail("test@example.com");
        userInfo.setIpAddress("127.0.0.1");
        userInfo.setRole(EditorRole.ADMIN);
    }

    @Test
    public void testCreateEntry() throws InvalidDataException {
        EntryVersion version = new EntryVersion();
        version.setRmStichwort("test");
        version.setDeStichwort("Test");

        Entry newEntry = Entry.createEntry(version, false, userInfo);

        assertNotNull(newEntry);
        assertNotNull(newEntry.getCurrent());
        assertEquals(1, newEntry.getVersions().size());
        assertEquals(0, newEntry.getSuggestions().size());
        assertEquals("test", newEntry.getCurrent().getRmStichwort());
        assertEquals("Test", newEntry.getCurrent().getDeStichwort());
        assertEquals(Action.CREATED_ENTRY, newEntry.getCurrent().getAction());
    }

    @Test
    public void testSuggestCreateEntry() throws InvalidDataException {
        EntryVersion version = new EntryVersion();
        version.setRmStichwort("test");
        version.setDeStichwort("Test");

        Entry newEntry = Entry.createEntry(version, true, userInfo);

        assertNotNull(newEntry);
        assertNull(newEntry.getCurrent());
        assertEquals(1, newEntry.getVersions().size());
        assertEquals(1, newEntry.getSuggestions().size());
        assertEquals("test", newEntry.getSuggestions().get(0).getRmStichwort());
        assertEquals("Test", newEntry.getSuggestions().get(0).getDeStichwort());
        assertEquals(Action.SUGGESTED_ENTRY, newEntry.getSuggestions().get(0).getAction());
    }

    @Test
    public void testAddVersionWithAllActions() {
        // SUGGESTED_ENTRY
        EntryVersion version1 = new EntryVersion();
        version1.setRmStichwort("test");
        version1.setDeStichwort("Test");
        entry.addVersion(version1, userInfo, Action.SUGGESTED_ENTRY);
        assertEquals(1, entry.getSuggestions().size());
        assertNull(entry.getCurrent());
        assertTrue(entry.getVersions().contains(version1));
        assertEquals(1, entry.getVersions().size());

        // ACCEPTED_ENTRY
        EntryVersion version2 = new EntryVersion();
        version2.setRmStichwort("test2");
        version2.setDeStichwort("Test2");
        entry.addVersion(version2, userInfo, Action.ACCEPTED_ENTRY);
        assertEquals(1, entry.getSuggestions().size());
        assertEquals(version2, entry.getCurrent());
        assertTrue(entry.getVersions().contains(version2));
        assertEquals(2, entry.getVersions().size());

        // CREATED_ENTRY
        EntryVersion version3 = new EntryVersion();
        version3.setRmStichwort("test3");
        version3.setDeStichwort("Test3");
        entry.addVersion(version3, userInfo, Action.CREATED_ENTRY);
        assertEquals(1, entry.getSuggestions().size());
        assertEquals(version3, entry.getCurrent());
        assertTrue(entry.getVersions().contains(version3));
        assertEquals(3, entry.getVersions().size());

        // SUGGESTED_MODIFICATION
        EntryVersion version4 = new EntryVersion();
        version4.setRmStichwort("test4");
        version4.setDeStichwort("Test4");
        entry.addVersion(version4, userInfo, Action.SUGGESTED_MODIFICATION);
        assertEquals(2, entry.getSuggestions().size());
        assertEquals(version3, entry.getCurrent());
        assertTrue(entry.getVersions().contains(version4));
        assertEquals(4, entry.getVersions().size());

        // ACCEPTED_MODIFICATION
        EntryVersion version5 = new EntryVersion();
        version5.setRmStichwort("test5");
        version5.setDeStichwort("Test5");
        entry.addVersion(version5, userInfo, Action.ACCEPTED_MODIFICATION);
        assertEquals(2, entry.getSuggestions().size());
        assertEquals(version5, entry.getCurrent());
        assertTrue(entry.getVersions().contains(version5));
        assertEquals(5, entry.getVersions().size());

        // REFUSED_MODIFICATION
        EntryVersion version6 = new EntryVersion();
        version6.setRmStichwort("test6");
        version6.setDeStichwort("Test6");
        entry.addVersion(version6, userInfo, Action.REFUSED_MODIFICATION);
        assertEquals(2, entry.getSuggestions().size());
        assertEquals(version5, entry.getCurrent());
        assertTrue(entry.getVersions().contains(version6));
        assertEquals(6, entry.getVersions().size());

        // CREATED_MODIFICATION
        EntryVersion version7 = new EntryVersion();
        version7.setRmStichwort("test7");
        version7.setDeStichwort("Test7");
        entry.addVersion(version7, userInfo, Action.CREATED_MODIFICATION);
        assertEquals(2, entry.getSuggestions().size());
        assertEquals(version7, entry.getCurrent());
        assertTrue(entry.getVersions().contains(version7));
        assertEquals(7, entry.getVersions().size());

        // CHANGED_ORDER
        EntryVersion version8 = new EntryVersion();
        version8.setRmStichwort("test8");
        version8.setDeStichwort("Test8");
        entry.addVersion(version8, userInfo, Action.CHANGED_ORDER);
        assertEquals(2, entry.getSuggestions().size());
        assertEquals(version8, entry.getCurrent());
        assertTrue(entry.getVersions().contains(version8));
        assertEquals(8, entry.getVersions().size());

        // UNKNOWN action should throw
        EntryVersion version9 = new EntryVersion();
        version9.setRmStichwort("test9");
        version9.setDeStichwort("Test9");
        assertThrows(InvalidDataException.class, () -> entry.addVersion(version9, userInfo, Action.UNKNOWN));
    }

    @Test
    public void testAcceptVersion() throws InvalidDataException, SuggestionNotFoundException {
        EntryVersion version = new EntryVersion();
        version.setVersionId(UUID.randomUUID().toString());
        version.setRmStichwort("test");

        entry.addVersion(version, userInfo, Action.SUGGESTED_ENTRY);
        assertNull(entry.getCurrent());
        assertEquals(1, entry.getSuggestions().size());
        assertTrue(entry.getSuggestions().contains(version));
        assertEquals(1, entry.getVersions().size());
        assertTrue(entry.getVersions().contains(version));
        assertEquals(Action.SUGGESTED_ENTRY, entry.getVersions().get(0).getAction());

        entry.acceptVersion(version.getVersionId(), userInfo);
        assertNotEquals(version, entry.getCurrent()); // when accepting, a new version is created
        assertEquals(0, entry.getSuggestions().size());
        assertFalse(entry.getSuggestions().contains(version));
        assertEquals(2, entry.getVersions().size());
        assertTrue(entry.getVersions().contains(version));
        assertEquals(Action.ACCEPTED_MODIFICATION, entry.getVersions().get(1).getAction());
    }

    @Test
    public void testAcceptAndRefuseVersion() throws InvalidDataException, SuggestionNotFoundException {
        EntryVersion suggestion = new EntryVersion();
        suggestion.setRmStichwort("suggestion");
        suggestion.setDeStichwort("Suggestion");
        entry.addVersion(suggestion, userInfo, Action.SUGGESTED_ENTRY);
        assertEquals(1, entry.getSuggestions().size());
        assertEquals(1, entry.getVersions().size());
        String suggestionId = entry.getSuggestions().get(0).getVersionId();

        // Accept
        entry.acceptVersion(suggestionId, userInfo);
        assertNotNull(entry.getCurrent());
        assertNotEquals(suggestionId, entry.getCurrent().getVersionId()); // when approving, a new version is created
        assertEquals(0, entry.getSuggestions().size());
        assertEquals(2, entry.getVersions().size());

        // Add another suggestion
        EntryVersion suggestion2 = new EntryVersion();
        suggestion2.setRmStichwort("suggestion2");
        suggestion2.setDeStichwort("Suggestion2");
        entry.addVersion(suggestion2, userInfo, Action.SUGGESTED_MODIFICATION);
        assertEquals(1, entry.getSuggestions().size());
        assertEquals(3, entry.getVersions().size());
        String suggestionId2 = entry.getSuggestions().get(0).getVersionId();

        // Refuse
        entry.refuseVersion(suggestionId2, userInfo);
        assertEquals(0, entry.getSuggestions().size());
        assertEquals(4, entry.getVersions().size());
        assertEquals("suggestion2", entry.getVersions().get(3).getRmStichwort());
        assertEquals(Action.REFUSED_MODIFICATION, entry.getVersions().get(3).getAction());
    }

    @Test
    public void testRefuseVersion() throws InvalidDataException, SuggestionNotFoundException {
        EntryVersion version = new EntryVersion();
        version.setVersionId(UUID.randomUUID().toString());
        version.setRmStichwort("test");

        entry.addVersion(version, userInfo, Action.SUGGESTED_ENTRY);
        entry.refuseVersion(version.getVersionId(), userInfo);

        assertFalse(entry.getSuggestions().contains(version));
        assertTrue(entry.getVersions().contains(version));
        assertEquals(0, entry.getSuggestions().size());
        assertEquals(2, entry.getVersions().size());
    }

    @Test
    public void testInvalidDataException() {
        EntryVersion version = new EntryVersion();

        assertThrows(InvalidDataException.class, () -> {
            entry.addVersion(version, userInfo, Action.CREATED_ENTRY);
        });
    }

    @Test
    public void testAcceptVersionThrowsException() {
        assertThrows(SuggestionNotFoundException.class, () -> entry.acceptVersion(UUID.randomUUID().toString(), userInfo));
    }

    @Test
    public void testRefuseVersionThrowsException() {
        assertThrows(SuggestionNotFoundException.class, () -> entry.refuseVersion(UUID.randomUUID().toString(), userInfo));
    }

    @Test
    public void testPublicationStatusLogic() throws InvalidDataException, SuggestionNotFoundException {
        EntryVersion version = new EntryVersion();
        version.setRmStichwort("test");
        version.setDeStichwort("Test");

        // No current, no suggestions
        assertEquals(entry.getPublicationStatus(), ch.pledarigrond.common.data.dictionary.PublicationStatus.INVALID);

        // Add suggestion
        entry.addVersion(version, userInfo, Action.SUGGESTED_ENTRY);
        assertEquals(entry.getPublicationStatus(), ch.pledarigrond.common.data.dictionary.PublicationStatus.HAS_SUGGESTION);

        // Accept suggestion
        String suggestionId = entry.getSuggestions().get(0).getVersionId();
        entry.acceptVersion(suggestionId, userInfo);
        assertEquals(entry.getPublicationStatus(), ch.pledarigrond.common.data.dictionary.PublicationStatus.PUBLISHED);
    }

    @Test
    public void testValidationErrors() {
        EntryVersion version = new EntryVersion();
        // Missing both rmStichwort and deStichwort
        assertThrows(InvalidDataException.class, () -> entry.addVersion(version, userInfo, Action.CREATED_ENTRY));
        // Null version
        assertThrows(InvalidDataException.class, () -> entry.addVersion(null, userInfo, Action.CREATED_ENTRY));
    }

    @Test
    public void testRemoveHistory() throws InvalidDataException, SuggestionNotFoundException {
        EntryVersion version = new EntryVersion();
        version.setRmStichwort("test");
        version.setDeStichwort("Test");
        entry.addVersion(version, userInfo, Action.CREATED_ENTRY);

        EntryVersion suggestion = new EntryVersion();
        suggestion.setRmStichwort("suggestion");
        suggestion.setDeStichwort("Suggestion");
        entry.addVersion(suggestion, userInfo, Action.SUGGESTED_ENTRY);
        assertEquals(2, entry.getVersions().size());

        entry.acceptVersion(suggestion.getVersionId(), userInfo);
        assertEquals(3, entry.getVersions().size());

        entry.removeHistory();
        assertEquals(1, entry.getVersions().size());
    }
}
