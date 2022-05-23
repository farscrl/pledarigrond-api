package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.DictionaryLanguage;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;

import java.util.List;

public interface MongoDbService {
    void insert(Language language, LexEntry entry, boolean asSuggestion) throws Exception;

    void suggestNewEntry(Language language, LexEntry entry) throws Exception;

    void suggestUpdate(Language language, LexEntry oldEntry, LemmaVersion newEntry) throws Exception;

    void update(Language language, LexEntry oldEntry, LemmaVersion newEntry) throws Exception;

    void reviewLater(Language language, LexEntry lexEntry) throws  Exception;

    void delete(Language language, LexEntry entry) throws Exception;

    void restoreOldVersion(Language language, LemmaVersion invalid, LemmaVersion valid) throws Exception;

    void accept(Language language, LexEntry entry, LemmaVersion version) throws Exception;

    void reject(Language language, LexEntry entry, LemmaVersion version) throws Exception;

    void acceptAfterUpdate(Language language, LexEntry entry, LemmaVersion suggested, LemmaVersion modified) throws Exception;

    void dropOutdatedHistory(Language language, LexEntry entry) throws Exception;

    List<LexEntry> updateOrder(Language language, DictionaryLanguage dictionaryLanguage, List<LemmaVersion> ordered) throws Exception;
}
