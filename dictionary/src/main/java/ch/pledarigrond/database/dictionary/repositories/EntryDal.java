package ch.pledarigrond.database.dictionary.repositories;

import ch.pledarigrond.common.data.dictionary.EditorQuery;
import ch.pledarigrond.common.data.dictionary.NormalizedEntryVersionsDto;
import org.springframework.data.domain.Page;

public interface EntryDal {
    Page<NormalizedEntryVersionsDto> queryForEntries(EditorQuery queryData, int pageSize, int page);
}
