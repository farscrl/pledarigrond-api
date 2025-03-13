package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.dictionary.dto.NormalizedEntryVersionsDto;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface EditorService {
    Page<NormalizedEntryVersionsDto> getDictionaryVersions(EditorQuery2 query, Pagination pagination);

    LexEntry accept(Language language, LexEntry entry, LemmaVersion version) throws Exception;

    LexEntry reject(Language language, LexEntry entry, LemmaVersion rejected) throws Exception;

    LexEntry drop(Language language, LexEntry entry) throws Exception;

    LexEntry acceptAfterUpdate(Language language, LexEntry entry, LemmaVersion suggested, LemmaVersion modified) throws Exception;

    LexEntry dropOutdatedHistory(Language language, LexEntry entry) throws Exception;

    Page<LemmaVersion> search(Language language, SearchCriteria searchCriteria, Pagination pagination) throws Exception;

    LexEntry getLexEntry(Language language, String entryId) throws Exception;

    LexEntry insert(Language language, LexEntry entry) throws Exception;

    LexEntry insertSuggestion(Language language, LexEntry entry) throws Exception;

    LexEntry update(Language language, LexEntry entry, LemmaVersion fromEditor) throws Exception;

    LexEntry reviewLater(Language language, LexEntry entry) throws Exception;

    List<LexEntry> updateOrder(Language language, DictionaryLanguage dictionaryLanguage, List<LemmaVersion> ordered) throws Exception;

    ArrayList<LemmaVersion> getOrder(Language language, String lemma, DictionaryLanguage dictionaryLanguage) throws Exception;

    String export(Language language, Set<String> fields, EditorQuery query) throws Exception;

    String export(Language language, Set<String> selected, SearchCriteria query) throws Exception;

    SearchSuggestions getSuggestionsForFields(Language language) throws NoIndexAvailableException, IOException;
}
