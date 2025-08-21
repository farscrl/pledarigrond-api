package ch.pledarigrond.database.dictionary.repositories;

import ch.pledarigrond.common.data.dictionary.DbSearchCriteria;
import ch.pledarigrond.common.data.dictionary.DictionaryStatisticsDto;
import ch.pledarigrond.common.data.dictionary.NormalizedEntryVersionsDto;
import org.springframework.data.domain.Page;

public interface EntryDal {
    Page<NormalizedEntryVersionsDto> queryForEntries(DbSearchCriteria queryData, int pageSize, int page);
    DictionaryStatisticsDto getStatistics();
}
