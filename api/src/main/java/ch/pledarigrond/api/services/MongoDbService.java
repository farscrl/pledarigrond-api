package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;

import java.util.List;

public interface MongoDbService {
    void insert(LexEntry entry, String locale) throws Exception;

    void suggestNewEntry(LexEntry entry, String locale) throws Exception;

    void suggestUpdate(LexEntry oldEntry, LemmaVersion newEntry, String locale) throws Exception;

    void update(LexEntry oldEntry, LemmaVersion newEntry, String locale) throws Exception;

    void delete(LexEntry entry, String locale) throws Exception;

    void restore(LemmaVersion invalid, LemmaVersion valid, String locale) throws Exception;

    void accept(LexEntry entry, LemmaVersion version, String locale) throws Exception;

    void reject(LexEntry entry, LemmaVersion version, String locale) throws Exception;

    void acceptAfterUpdate(LexEntry entry, LemmaVersion suggested, LemmaVersion modified, String locale) throws Exception;

    void dropOutdatedHistory(LexEntry entry, String locale) throws Exception;

    List<LexEntry> updateOrder(boolean firstLang, List<LemmaVersion> ordered, String locale) throws Exception;
}
