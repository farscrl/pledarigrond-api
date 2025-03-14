package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.DictionaryLanguage;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.SearchSuggestions;
import ch.pledarigrond.common.data.dictionary.EditorQuery;
import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.common.data.dictionary.NormalizedEntryVersionsDto;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.common.exception.dictionary.InvalidReviewLaterException;
import ch.pledarigrond.common.exception.dictionary.SuggestionNotFoundException;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface EditorService {
    Page<NormalizedEntryVersionsDto> getDictionaryVersions(EditorQuery query, Pagination pagination);

    EntryDto getEntry(String entryId) ;

    EntryDto addEntry(EntryVersionDto version, boolean asSuggestion) throws IOException;

    EntryDto addSuggestion(String entryId, EntryVersionDto version) throws IOException;

    EntryDto accept(String entryId, String versionIdToAccept) throws SuggestionNotFoundException, IOException;

    EntryDto reject(String entryId, String versionIdToReject) throws SuggestionNotFoundException, IOException;

    void delete(String entryId) throws IOException;

    EntryDto acceptAfterUpdateSuggestion(String entryId, EntryVersionDto modified) throws SuggestionNotFoundException, IOException;

    EntryDto reviewSuggestionLater(String entryId, String versionId) throws InvalidReviewLaterException, SuggestionNotFoundException, IOException;

    EntryDto dropOutdatedHistory(String entryId) throws IOException;

    Page<EntryVersionDto> search(SearchCriteria searchCriteria, Pagination pagination) throws BrokenIndexException, NoIndexAvailableException, IOException, InvalidQueryException;

    EntryDto updateSuggestion(String entryId, EntryVersionDto newVersion) throws SuggestionNotFoundException, IOException;

    ArrayList<EntryVersionDto> getOrder(String lemma, DictionaryLanguage dictionaryLanguage) throws BrokenIndexException, NoIndexAvailableException, IOException, InvalidQueryException;

    List<EntryDto> updateOrder(DictionaryLanguage dictionaryLanguage, List<EntryVersionDto> ordered) throws IOException;

    String export(Set<String> fields, EditorQuery query) throws IOException;

    String export(Set<String> selected, SearchCriteria query) throws BrokenIndexException, NoIndexAvailableException, IOException, InvalidQueryException;

    SearchSuggestions getSuggestionsForFields(Language language) throws NoIndexAvailableException, IOException;
}
