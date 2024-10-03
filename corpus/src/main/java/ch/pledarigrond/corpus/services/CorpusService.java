package ch.pledarigrond.corpus.services;

import ch.pledarigrond.common.data.common.Language;

import java.util.List;

public interface CorpusService {
    boolean importQuotidianaCorpusFiles();

    List<String> findInCorpus(Language language, String s);
}
