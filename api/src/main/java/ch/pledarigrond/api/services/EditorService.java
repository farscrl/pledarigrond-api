package ch.pledarigrond.api.services;

import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface EditorService {
    Page<LexEntry> getLexEntries(Language language, EditorQuery query, Pagination pagination) throws Exception;

    LexEntry accept(Language language, LexEntry entry, LemmaVersion version) throws Exception;

    LexEntry reject(Language language, LexEntry entry, LemmaVersion rejected) throws Exception;

    LexEntry drop(Language language, LexEntry entry) throws Exception;

    LexEntry acceptAfterUpdate(Language language, LexEntry entry, LemmaVersion suggested, LemmaVersion modified) throws Exception;

    LexEntry dropOutdatedHistory(Language language, LexEntry entry) throws Exception;

    QueryResult search(Language language, SearchCriteria maalrQuery, Pagination pagination) throws Exception;

    LexEntry getLexEntry(Language language, String entryId) throws Exception;

    LexEntry insert(Language language, LexEntry entry) throws Exception;

    LexEntry update(Language language, LexEntry entry, LemmaVersion fromEditor) throws Exception;

    List<LexEntry> updateOrder(Language language, boolean firstLang, List<LemmaVersion> ordered) throws Exception;

    ArrayList<LemmaVersion> getOrder(Language language, String lemma, boolean firstLanguage) throws Exception;

    String export(Language language, Set<String> fields, EditorQuery query) throws Exception;

    String export(Language language, Set<String> selected, SearchCriteria query) throws Exception;

    // HashMap<String, String> getOverlayEditorPreset(Language language, String overlayId, String presetId, String base) throws GenerationFailedException;

}
