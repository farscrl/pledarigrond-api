package ch.pledarigrond.common.config;

import ch.pledarigrond.common.data.lucene.IndexedColumn;

import java.util.List;

public abstract class LanguageConfig {
    public abstract List<IndexedColumn> getDatabaseColumns();
}
