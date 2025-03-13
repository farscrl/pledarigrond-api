package ch.pledarigrond.dictionary.dal;

import ch.pledarigrond.common.data.common.EditorQuery2;
import ch.pledarigrond.dictionary.dto.NormalizedEntryVersionsDto;
import org.springframework.data.domain.Page;

public interface EntryDal {
    Page<NormalizedEntryVersionsDto> queryForEntries(EditorQuery2 queryData, int pageSize, int page, boolean excludeAutomaticChanges);
}
