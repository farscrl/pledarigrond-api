package ch.pledarigrond.database.services;

import ch.pledarigrond.common.data.common.UserInfoDto;
import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.database.AbstractBaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DictionaryServiceTest extends AbstractBaseIntegrationTest {

    @Autowired
    private DictionaryService dictionaryService;

    @Test
    public void testGettingEntries() {
        assertNull(dictionaryService.getEntry("notexisting"));

        EntryVersionDto version = new EntryVersionDto();
        version.setRmStichwort("rumantsch");
        version.setDeStichwort("deutsch");

        EntryDto entry = dictionaryService.addEntry(version, false, UserInfoDto.getSystemDto());

        assertNotNull(entry);

        assertNotNull(dictionaryService.getEntry(entry.getEntryId()));
    }
}
