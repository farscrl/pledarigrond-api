package ch.pledarigrond.dictionary.services.impl;

import ch.pledarigrond.common.data.common.EditorQuery2;
import ch.pledarigrond.dictionary.dal.EntryDal;
import ch.pledarigrond.dictionary.dto.NormalizedEntryVersionsDto;
import ch.pledarigrond.dictionary.services.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private EntryDal entryDal;

    public Page<NormalizedEntryVersionsDto> queryForEntries(EditorQuery2 queryData, int pageSize, int page, boolean excludeAutomaticChanges) {
        return entryDal.queryForEntries(queryData, pageSize, page, excludeAutomaticChanges);
    }
}
