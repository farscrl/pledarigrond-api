package ch.pledarigrond.database.services.impl;

import ch.pledarigrond.common.data.common.DictionaryLanguage;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
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
    public Page<NormalizedEntryVersionsDto> queryForEntries(EditorQuery queryData, int pageSize, int page, boolean excludeAutomaticChanges) {
        return entryDal.queryForEntries(queryData, pageSize, page, excludeAutomaticChanges);
    }

    @Override
    public EntryDto getEntry(String entryId) {
        return entryMapper.toEntryDto(entryRepository.getFirstByEntryId(entryId));
    }

    @Override
    public EntryDto addEntry(EntryVersionDto version, boolean asSuggestion, String reviewer) {
        EntryVersion entryVersion = entryMapper.toEntryVersion(version);
        Entry entry = new Entry();
        if (!asSuggestion) {
            entryVersion.setVerifier(reviewer);
        }
        entry.getVersions().add(entryVersion);
        if (asSuggestion) {
            entry.getSuggestions().add(entryVersion);
        } else {
            entry.setCurrent(entryVersion);
        }
        return entryMapper.toEntryDto(entryRepository.save(entry));
    }

    @Override
    public EntryDto addSuggestion(String entryId, EntryVersionDto version) {
        Entry entry = entryRepository.findByEntryId(entryId).orElseThrow(() -> new EntityNotFoundException(entryId));

        EntryVersion entryVersion = entryMapper.toEntryVersion(version);
        entry.getSuggestions().add(entryVersion);
        entry.getVersions().add(entryVersion);
        return entryMapper.toEntryDto(entryRepository.save(entry));
    }

    @Override
    public EntryDto accept(String entryId, String versionIdToAccept, String reviewer) throws SuggestionNotFoundException {
        Entry entry = entryRepository.findByEntryId(entryId).orElseThrow(() -> new EntityNotFoundException(entryId));

        EntryVersion versionToAccept = entry.getSuggestions().stream()
                .filter(v -> Objects.equals(v.getVersionId(), versionIdToAccept))
                .findFirst()
                .orElseThrow(() -> new SuggestionNotFoundException(versionIdToAccept));

        versionToAccept.setVerifier(reviewer);

        entry.getSuggestions().removeIf(v -> v.getVersionId().equals(versionIdToAccept));
        entry.setCurrent(versionToAccept);
        return entryMapper.toEntryDto(entryRepository.save(entry));
    }

    @Override
    public EntryDto reject(String entryId, String versionIdToReject, String reviewer) throws SuggestionNotFoundException {
        Entry entry = entryRepository.findByEntryId(entryId).orElseThrow(() -> new EntityNotFoundException(entryId));

        EntryVersion versionToReject = entry.getSuggestions().stream()
                .filter(v -> Objects.equals(v.getVersionId(), versionIdToReject))
                .findFirst()
                .orElseThrow(() -> new SuggestionNotFoundException(versionIdToReject));

        versionToReject.setVerifier(reviewer);

        entry.getSuggestions().removeIf(v -> v.getVersionId().equals(versionIdToReject));
        entry.getVersions().removeIf(v -> v.getVersionId().equals(versionIdToReject));

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
    public EntryDto updateVersion(String entryId, EntryVersionDto version) throws SuggestionNotFoundException {
        Entry entry = entryRepository.findByEntryId(entryId).orElseThrow(() -> new EntityNotFoundException(entryId));

        boolean exists = entry.getSuggestions().stream().anyMatch(v -> Objects.equals(v.getVersionId(), version.getVersionId()));
        if (!exists) {
            throw new SuggestionNotFoundException(version.getVersionId());
        }

        EntryVersion entryVersion = entryMapper.toEntryVersion(version);
        replaceSuggestion(entry, entryVersion);

        return entryMapper.toEntryDto(entryRepository.save(entry));
    }

    @Override
    public EntryDto updateAndAcceptVersion(String entryId, EntryVersionDto modified, String reviewer) throws SuggestionNotFoundException {
        updateVersion(entryId, modified);
        return accept(entryId, modified.getVersionId(), reviewer);
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

        entry.setVersions(new ArrayList<>());
        entry.getVersions().add(entry.getCurrent());
        entry.getSuggestions().forEach(s -> entry.getVersions().add(s));
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
                updatedVersion.setDeStichwort(i + "");
                sortValOld = "deStichwort: " + entry.getCurrent().getDeStichwortSort();
                sortValNew = "deStichwort: " + updatedVersion.getDeStichwortSort();
            } else {
                updatedVersion.setRmStichwortSort(i + "");
                sortValOld = "rmStichwort: " + entry.getCurrent().getRmStichwortSort();
                sortValNew = "rmStichwort: " + updatedVersion.getRmStichwortSort();
            }
            logger.info("Entry {} <-> {} had sortVal «{}», now is «{}»", updatedVersion.getRmStichwort(), updatedVersion.getDeStichwort(), sortValOld, sortValNew);

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

        entry.setCurrent(updatedVersion);
        entry.getVersions().add(updatedVersion);

        return entryMapper.toEntryDto(entryRepository.save(entry));
    }

    @Override
    public EntryDto removePronunciationForEntry(String entryId) {
        Entry entry = entryRepository.findByEntryId(entryId).orElseThrow(() -> new EntityNotFoundException(entryId));
        EntryVersion updatedVersion = clone(entry.getCurrent());
        updatedVersion.setRmPronunciation(null);

        entry.setCurrent(updatedVersion);
        entry.getVersions().add(updatedVersion);

        return entryMapper.toEntryDto(entryRepository.save(entry));
    }

    @Override
    public Stream<EntryDto> getStreamForEntries() {
        return entryRepository.findAllBy();
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
        AtomicLong numberOfEntries = new AtomicLong();
        AtomicLong numberOfVersions = new AtomicLong();
        AtomicLong numberOfSuggestions = new AtomicLong();
        AtomicLong numberOfApproved = new AtomicLong();

        entryRepository.findAllBy().forEach(entry -> {
            numberOfEntries.incrementAndGet();
            numberOfVersions.addAndGet(entry.getVersions().size());
            numberOfSuggestions.addAndGet(entry.getSuggestions().size());
            if (entry.getCurrent() != null) {
                numberOfApproved.incrementAndGet();
            }
        });

        DictionaryStatisticsDto stats = new DictionaryStatisticsDto();
        stats.setNumberOfEntries(numberOfEntries.get());
        stats.setNumberOfVersions(numberOfVersions.get());
        stats.setNumberOfSuggestions(numberOfSuggestions.get());
        stats.setNumberOfApproved(numberOfApproved.get());

        return stats;
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
}
