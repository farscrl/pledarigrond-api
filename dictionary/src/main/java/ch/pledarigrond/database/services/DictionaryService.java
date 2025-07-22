package ch.pledarigrond.database.services;

import ch.pledarigrond.common.data.common.DictionaryLanguage;
import ch.pledarigrond.common.data.common.UserInfoDto;
import ch.pledarigrond.common.data.dictionary.*;
import ch.pledarigrond.common.exception.dictionary.InvalidReviewLaterException;
import ch.pledarigrond.common.exception.dictionary.SuggestionNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Stream;

public interface DictionaryService {
    Page<NormalizedEntryVersionsDto> queryForEntries(EditorQuery queryData, int pageSize, int page, boolean excludeAutomaticChanges);
    EntryDto getEntry(String entryId);
    EntryDto addEntry(EntryVersionDto version, boolean asSuggestion, UserInfoDto userInfo);
    EntryDto addVersion(String entryId, EntryVersionDto version, boolean asSuggestion, UserInfoDto userInfo);
    EntryDto replaceSuggestion(String entryId, String versionToReplaceId, EntryVersionDto version, boolean asSuggestion, UserInfoDto userInfo) throws SuggestionNotFoundException;
    EntryDto accept(String entryId, String versionIdToAccept, UserInfoDto userInfo) throws SuggestionNotFoundException;
    EntryDto reject(String entryId, String versionIdToReject, UserInfoDto userInfo) throws SuggestionNotFoundException;
    void delete(String entryId);
    EntryDto reviewSuggestionLater(String entryId, String versionId) throws SuggestionNotFoundException, InvalidReviewLaterException;
    EntryDto dropOutdatedHistory(String entryId);
    List<EntryDto> updateOrder(DictionaryLanguage dictionaryLanguage, List<EntryVersionDto> ordered);

    EntryDto updatePronunciationForEntry(String entryId, String pronunciation);
    EntryDto removePronunciationForEntry(String entryId);

    Stream<EntryDto> getStreamForEntries();

    DictionaryStatisticsDto getStatistics();
    void deleteAllEntries();
}
