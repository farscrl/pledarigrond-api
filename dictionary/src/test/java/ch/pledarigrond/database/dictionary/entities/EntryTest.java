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
    public void testAddVersion() throws InvalidDataException {
        EntryVersion version = new EntryVersion();
        version.setRmStichwort("test");
        version.setDeStichwort("Test");

        entry.addVersion(version, userInfo, Action.CREATED_MODIFICATION);

        assertEquals(version, entry.getCurrent());
        assertTrue(entry.getVersions().contains(version));
        assertEquals(0, entry.getSuggestions().size());
        assertEquals(1, entry.getVersions().size());
    }

    @Test
    public void testAcceptVersion() throws InvalidDataException, SuggestionNotFoundException {
        EntryVersion version = new EntryVersion();
        version.setVersionId(UUID.randomUUID().toString());
        version.setRmStichwort("test");

        entry.addVersion(version, userInfo, Action.SUGGESTED_ENTRY);
        entry.acceptVersion(version.getVersionId(), userInfo);

        assertEquals(version, entry.getCurrent());
        assertTrue(entry.getVersions().contains(version));
        assertEquals(0, entry.getSuggestions().size());
        assertEquals(2, entry.getVersions().size());
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
}