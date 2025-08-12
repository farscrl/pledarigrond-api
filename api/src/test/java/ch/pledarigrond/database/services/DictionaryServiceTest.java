package ch.pledarigrond.database.services;

import ch.pledarigrond.common.data.common.UserInfoDto;
import ch.pledarigrond.common.data.dictionary.*;
import ch.pledarigrond.database.AbstractBaseIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.*;

public class DictionaryServiceTest extends AbstractBaseIntegrationTest {

    @Autowired
    private DictionaryService dictionaryService;

    @Test
    public void testCreatingEntries() {
        DictionaryStatisticsDto stats = dictionaryService.getStatistics();
        Assertions.assertEquals(0, stats.getNumberOfEntries());
        Assertions.assertEquals(0, stats.getNumberOfApproved());

        assertNull(dictionaryService.getEntry("notexisting"));

        EntryVersionDto version = new EntryVersionDto();
        version.setRmStichwort("rumantsch");
        version.setDeStichwort("deutsch");

        EntryDto entry = dictionaryService.addEntry(version, false, UserInfoDto.getSystemDto());

        assertNotNull(entry);

        assertNotNull(dictionaryService.getEntry(entry.getEntryId()));

        stats = dictionaryService.getStatistics();
        Assertions.assertEquals(1, stats.getNumberOfEntries());
        Assertions.assertEquals(1, stats.getNumberOfApproved());

        DbSearchCriteria dbSearchCriteria = new DbSearchCriteria();
        dbSearchCriteria.setState(PublicationStatus.PUBLISHED);
        Page<NormalizedEntryVersionsDto> result = dictionaryService.queryForEntries(dbSearchCriteria, 1000, 0);
        assertEquals(1, result.getTotalElements());

        dbSearchCriteria.setState(PublicationStatus.HAS_SUGGESTION);
        result = dictionaryService.queryForEntries(dbSearchCriteria, 1000, 0);
        assertEquals(0, result.getTotalElements());
    }

    @Test
    public void testCreatingEntrySuggestion() {
        DictionaryStatisticsDto stats = dictionaryService.getStatistics();
        Assertions.assertEquals(0, stats.getNumberOfEntries());
        Assertions.assertEquals(0, stats.getNumberOfApproved());

        assertNull(dictionaryService.getEntry("notexisting"));

        EntryVersionDto version = new EntryVersionDto();
        version.setRmStichwort("rumantsch");
        version.setDeStichwort("deutsch");

        EntryDto entry = dictionaryService.addEntry(version, true, UserInfoDto.getSystemDto());

        assertNotNull(entry);

        assertNotNull(dictionaryService.getEntry(entry.getEntryId()));

        stats = dictionaryService.getStatistics();
        Assertions.assertEquals(1, stats.getNumberOfEntries());
        Assertions.assertEquals(0, stats.getNumberOfApproved());

        DbSearchCriteria dbSearchCriteria = new DbSearchCriteria();
        dbSearchCriteria.setState(PublicationStatus.PUBLISHED);
        Page<NormalizedEntryVersionsDto> result = dictionaryService.queryForEntries(dbSearchCriteria, 1000, 0);
        assertEquals(0, result.getTotalElements());

        dbSearchCriteria.setState(PublicationStatus.HAS_SUGGESTION);
        result = dictionaryService.queryForEntries(dbSearchCriteria, 1000, 0);
        assertEquals(1, result.getTotalElements());
    }
}
