package ch.pledarigrond.dictionary.services;

import ch.pledarigrond.common.data.common.DictionaryLanguage;
import ch.pledarigrond.common.data.dictionary.EditorQuery;
import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.common.data.dictionary.NormalizedEntryVersionsDto;
import ch.pledarigrond.common.exception.dictionary.InvalidReviewLaterException;
import ch.pledarigrond.common.exception.dictionary.SuggestionNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Stream;

public interface DictionaryService {
    Page<NormalizedEntryVersionsDto> queryForEntries(EditorQuery queryData, int pageSize, int page, boolean excludeAutomaticChanges);
    EntryDto getEntry(String entryId);
    EntryDto addEntry(EntryVersionDto version, boolean asSuggestion, String reviewer);
    EntryDto addSuggestion(String entryId, EntryVersionDto version);
    EntryDto accept(String entryId, String versionIdToAccept, String reviewer) throws SuggestionNotFoundException;
    EntryDto reject(String entryId, String versionIdToReject, String reviewer) throws SuggestionNotFoundException;
    void delete(String entryId);
    EntryDto updateVersion(String entryId, EntryVersionDto version) throws SuggestionNotFoundException;
    EntryDto updateAndAcceptVersion(String entryId, EntryVersionDto modified, String reviewer) throws SuggestionNotFoundException;
    EntryDto reviewSuggestionLater(String entryId, String versionId) throws SuggestionNotFoundException, InvalidReviewLaterException;
    EntryDto dropOutdatedHistory(String entryId);
    List<EntryDto> updateOrder(DictionaryLanguage dictionaryLanguage, List<EntryVersionDto> ordered);

    EntryDto updatePronunciationForEntry(String entryId, String pronunciation);
    EntryDto removePronunciationForEntry(String entryId);

    Stream<EntryDto> getStreamForEntries();
}
