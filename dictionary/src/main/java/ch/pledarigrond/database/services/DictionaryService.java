package ch.pledarigrond.database.services;

import ch.pledarigrond.common.data.common.DictionaryLanguage;
import ch.pledarigrond.common.data.common.ThrowingConsumer;
import ch.pledarigrond.common.data.common.ThrowingFunction;
import ch.pledarigrond.common.data.common.UserInfoDto;
import ch.pledarigrond.common.data.dictionary.*;
import ch.pledarigrond.common.exception.dictionary.InvalidReviewLaterException;
import ch.pledarigrond.common.exception.dictionary.SuggestionNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Stream;

public interface DictionaryService {
    boolean isEmpty();
    Page<NormalizedEntryVersionsDto> queryForEntries(DbSearchCriteria queryData, int pageSize, int page);
    EntryDto getEntry(String entryId);
    EntryDto addEntry(EntryVersionDto version, boolean asSuggestion, UserInfoDto userInfo);
    EntryDto addVersion(String entryId, EntryVersionDto version, boolean asSuggestion, UserInfoDto userInfo);
    EntryDto replaceSuggestion(String entryId, String versionToReplaceId, EntryVersionDto version, boolean asSuggestion, UserInfoDto userInfo) throws SuggestionNotFoundException;
    EntryDto accept(String entryId, String versionIdToAccept, UserInfoDto userInfo) throws SuggestionNotFoundException;
    EntryDto reject(String entryId, String versionIdToReject, UserInfoDto userInfo) throws SuggestionNotFoundException;
    void delete(String entryId);
    EntryDto reviewSuggestionLater(String entryId, String versionId) throws SuggestionNotFoundException, InvalidReviewLaterException;
    EntryDto dropOutdatedHistory(String entryId);
    List<EntryDto> updateOrder(DictionaryLanguage dictionaryLanguage, List<EntryVersionDto> ordered, UserInfoDto userInfo);

    EntryDto updatePronunciationForEntry(String entryId, String pronunciation, UserInfoDto userInfo);
    EntryDto removePronunciationForEntry(String entryId, UserInfoDto userInfo);

    <R, E extends Exception> R withAllEntries(ThrowingFunction<Stream<EntryDto>, R, E> fn) throws E;
    <E extends Exception> void withAllEntries(ThrowingConsumer<Stream<EntryDto>, E> consumer) throws E;

    DictionaryStatisticsDto getStatistics();
    void deleteAllEntries();
}
