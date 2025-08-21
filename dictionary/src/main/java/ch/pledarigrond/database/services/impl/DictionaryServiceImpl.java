package ch.pledarigrond.database.services.impl;

import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.dictionary.*;
import ch.pledarigrond.common.exception.dictionary.EntityNotFoundException;
import ch.pledarigrond.common.exception.dictionary.InvalidReviewLaterException;
import ch.pledarigrond.common.exception.dictionary.SuggestionNotFoundException;
import ch.pledarigrond.database.dictionary.entities.Entry;
import ch.pledarigrond.database.dictionary.entities.EntryVersion;
import ch.pledarigrond.database.dictionary.mappers.EntryMapper;
import ch.pledarigrond.database.dictionary.repositories.EntryDal;
import ch.pledarigrond.database.dictionary.repositories.EntryRepository;
import ch.pledarigrond.database.services.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    protected static final Logger logger = LoggerFactory.getLogger(DictionaryServiceImpl.class);

    @Autowired
    private EntryDal entryDal;

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private EntryMapper entryMapper;

    @Override
    public boolean isEmpty() {
        return entryRepository.count() == 0;
    }


    @Override
    public Page<NormalizedEntryVersionsDto> queryForEntries(DbSearchCriteria queryData, int pageSize, int page) {
        return entryDal.queryForEntries(queryData, pageSize, page);
    }

    @Override
    public EntryDto getEntry(String entryId) {
        return entryMapper.toEntryDto(entryRepository.getFirstByEntryId(entryId));
    }

    @Override
    public EntryDto addEntry(EntryVersionDto versionDto, boolean asSuggestion, UserInfoDto userInfo) {
        EntryVersion version = entryMapper.toEntryVersion(versionDto);
        version.setTimestamp(Instant.now());
        Entry entry = Entry.createEntry(version, asSuggestion, userInfo);
        return entryMapper.toEntryDto(entryRepository.save(entry));
    }

    @Override
    public EntryDto addVersion(String entryId, EntryVersionDto versionDto, boolean asSuggestion, UserInfoDto userInfo) {
        Entry entry = entryRepository.findByEntryId(entryId).orElseThrow(() -> new EntityNotFoundException(entryId));
        return addVersionAndSave(entryId, versionDto, asSuggestion, userInfo, entry);
    }

    @Override
    public EntryDto replaceSuggestion(String entryId, String versionToReplaceId, EntryVersionDto versionDto, boolean asSuggestion, UserInfoDto userInfo) throws SuggestionNotFoundException {
        Entry entry = entryRepository.findByEntryId(entryId).orElseThrow(() -> new EntityNotFoundException(entryId));
        EntryVersion versionToReplace = entry.getSuggestions().stream().filter(v -> v.getVersionId().equals(versionToReplaceId)).findFirst().orElseThrow(() -> new SuggestionNotFoundException(versionToReplaceId));
        entry.getSuggestions().remove(versionToReplace);

        return addVersionAndSave(entryId, versionDto, asSuggestion, userInfo, entry);
    }

    @Override
    public EntryDto accept(String entryId, String versionIdToAccept, UserInfoDto userInfo) throws SuggestionNotFoundException {
        Entry entry = entryRepository.findByEntryId(entryId).orElseThrow(() -> new EntityNotFoundException(entryId));
        return acceptAndSave(versionIdToAccept, userInfo, entry);
    }

    @Override
    public EntryDto reject(String entryId, String versionIdToReject, UserInfoDto userInfo) throws SuggestionNotFoundException {
        Entry entry = entryRepository.findByEntryId(entryId).orElseThrow(() -> new EntityNotFoundException(entryId));

        entry.refuseVersion(versionIdToReject, userInfo);

        if (entry.getCurrent() == null && entry.getSuggestions().isEmpty()) {
            entryRepository.delete(entry);
            return null;
        }
        return entryMapper.toEntryDto(entryRepository.save(entry));
    }

    @Override
    public void delete(String entryId) {
        Entry entry = entryRepository.findByEntryId(entryId).orElseThrow(() -> new EntityNotFoundException(entryId));
        entryRepository.delete(entry);
    }

    @Override
    public EntryDto reviewSuggestionLater(String entryId, String versionId) throws SuggestionNotFoundException, InvalidReviewLaterException {
        Entry entry = entryRepository.findByEntryId(entryId).orElseThrow(() -> new EntityNotFoundException(entryId));

        EntryVersion versionToPostpone = entry.getSuggestions().stream()
                .filter(v -> Objects.equals(v.getVersionId(), versionId))
                .findFirst()
                .orElseThrow(() -> new SuggestionNotFoundException(versionId));

        if (versionToPostpone.getInflection() == null) {
            throw new InvalidReviewLaterException(versionId);
        }

        versionToPostpone.getInflection().setReviewLater(true);
        replaceSuggestion(entry, versionToPostpone);
        return entryMapper.toEntryDto(entryRepository.save(entry));
    }

    @Override
    public EntryDto dropOutdatedHistory(String entryId) {
        Entry entry = entryRepository.findByEntryId(entryId).orElseThrow(() -> new EntityNotFoundException(entryId));
        entry.removeHistory();
        return entryMapper.toEntryDto(entryRepository.save(entry));
    }

    @Override
    public List<EntryDto> updateOrder(DictionaryLanguage dictionaryLanguage, List<EntryVersionDto> ordered) {
        List<EntryDto> entries = new ArrayList<>();
        for(int i = 0; i < ordered.size(); i++) {
            EntryVersionDto versionDto = ordered.get(i);
            Entry entry = entryRepository.findByEntryId(versionDto.getEntryId()).orElseThrow(() -> new EntityNotFoundException(versionDto.getEntryId()));
            assert entry.getCurrent() != null;

            EntryVersion updatedVersion = clone(entry.getCurrent());
            String sortValOld = "";
            String sortValNew = "";
            if (dictionaryLanguage == DictionaryLanguage.GERMAN) {
                updatedVersion.setDeStichwortSort(i + "");
                sortValOld = "deStichwort: " + entry.getCurrent().getDeStichwortSort();
                sortValNew = "deStichwort: " + updatedVersion.getDeStichwortSort();
            } else {
                updatedVersion.setRmStichwortSort(i + "");
                sortValOld = "rmStichwort: " + entry.getCurrent().getRmStichwortSort();
                sortValNew = "rmStichwort: " + updatedVersion.getRmStichwortSort();
            }
            logger.info("Entry {} <-> {} had sortVal «{}», now is «{}»", updatedVersion.getRmStichwort(), updatedVersion.getDeStichwort(), sortValOld, sortValNew);

            updatedVersion.setTimestamp(Instant.now());
            entry.setCurrent(updatedVersion);
            entry.getVersions().add(updatedVersion);
            entry = entryRepository.save(entry);

            entries.add(entryMapper.toEntryDto(entry));
        }

        return entries;
    }

    @Override
    public EntryDto updatePronunciationForEntry(String entryId, String pronunciation) {
        Entry entry = entryRepository.findByEntryId(entryId).orElseThrow(() -> new EntityNotFoundException(entryId));
        EntryVersion updatedVersion = clone(entry.getCurrent());
        updatedVersion.setRmPronunciation(pronunciation);

        updatedVersion.setTimestamp(Instant.now());
        entry.setCurrent(updatedVersion);
        entry.getVersions().add(updatedVersion);

        return entryMapper.toEntryDto(entryRepository.save(entry));
    }

    @Override
    public EntryDto removePronunciationForEntry(String entryId) {
        Entry entry = entryRepository.findByEntryId(entryId).orElseThrow(() -> new EntityNotFoundException(entryId));
        EntryVersion updatedVersion = clone(entry.getCurrent());
        updatedVersion.setRmPronunciation(null);

        updatedVersion.setTimestamp(Instant.now());
        entry.setCurrent(updatedVersion);
        entry.getVersions().add(updatedVersion);

        return entryMapper.toEntryDto(entryRepository.save(entry));
    }

    public <R> R withAllEntries(Function<Stream<EntryDto>, R> fn) {
        try (Stream<Entry> s = entryRepository.findAllBy()) {
            return fn.apply(s.map(entryMapper::toEntryDto));
        }
    }
    public <R, E extends Exception> R withAllEntries(ThrowingFunction<Stream<EntryDto>, R, E> fn) throws E {
        try (Stream<Entry> s = entryRepository.findAllBy()) {
            return fn.apply(s.map(entryMapper::toEntryDto));
        }
    }

    // overload needed for functions that return void
    public <E extends Exception> void withAllEntries(ThrowingConsumer<Stream<EntryDto>, E> consumer) throws E {
        try (Stream<Entry> s = entryRepository.findAllBy()) {
            consumer.accept(s.map(entryMapper::toEntryDto));
        }
    }

    private void replaceSuggestion(Entry entry, EntryVersion versionToReplace) {
        for (int i = 0; i < entry.getSuggestions().size(); i++) {
            if (Objects.equals(entry.getSuggestions().get(i).getVersionId(), versionToReplace.getVersionId())) {
                entry.getSuggestions().set(i, versionToReplace);
                break;
            }
        }

        for (int i = 0; i < entry.getVersions().size(); i++) {
            if (Objects.equals(entry.getVersions().get(i).getVersionId(), versionToReplace.getVersionId())) {
                entry.getVersions().set(i, versionToReplace);
                break;
            }
        }
    }

    @Override
    public DictionaryStatisticsDto getStatistics() {
        return entryDal.getStatistics();
    }

    @Override
    public void deleteAllEntries() {
        entryRepository.deleteAll();
    }

    private EntryVersion clone(EntryVersion version) {
        EntryVersion clone = entryMapper.toEntryVersion(entryMapper.toEntryVersionDto(version));
        clone.setVersionId(UUID.randomUUID().toString());
        return clone;
    }

    private EntryDto addVersionAndSave(String entryId, EntryVersionDto versionDto, boolean asSuggestion, UserInfoDto userInfo, Entry entry) {
        EntryVersion version = entryMapper.toEntryVersion(versionDto);
        version.setTimestamp(Instant.now());

        entry.addVersion(version, userInfo, Action.SUGGESTED_MODIFICATION);

        if (asSuggestion) {
            entry = entryRepository.save(entry);
            return entryMapper.toEntryDto(entry);
        } else {
            try {
                return acceptAndSave(version.getVersionId(), userInfo, entry);
            } catch (SuggestionNotFoundException e) {
                // this should not happen, as we just created the version
                logger.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    private EntryDto acceptAndSave(String versionIdToAccept, UserInfoDto userInfo, Entry entry) throws SuggestionNotFoundException {
        entry.acceptVersion(versionIdToAccept, userInfo);
        return entryMapper.toEntryDto(entryRepository.save(entry));
    }
}
