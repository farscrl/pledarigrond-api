package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.config.LuceneConfiguration;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;

public abstract class BaseLuceneIndexTest {

    protected LuceneConfiguration luceneConfiguration;
    protected LuceneIndexManager luceneIndexManager;

    private File indexDir;

    @BeforeEach
    public void beforeTest() throws Exception {
        Language language = Language.SURMIRAN;
        File file = File.createTempFile("pledarigrond", "test");
        indexDir = new File(file.getParentFile(), "pg_test" + UUID.randomUUID() + "_idx");
        assertFalse(indexDir.exists());
        indexDir.mkdir();
        file.deleteOnExit();
        luceneConfiguration = new LuceneConfiguration(language, indexDir.getAbsolutePath());
        luceneIndexManager = new LuceneIndexManager(luceneConfiguration);
    }

    @AfterEach
    public void afterTest() {
        deleteRecursive(indexDir);
    }

    private void deleteRecursive(File fileOrDir) {
        if(fileOrDir.isDirectory()) {
            File[] files = fileOrDir.listFiles();
            assert files != null;
            for (File file : files) {
                deleteRecursive(file);
            }
        } else {
            fileOrDir.delete();
        }
    }

    protected EntryVersionDto generateValidEntryVersionDto(String deStichwort, String rmStichwort) {
        EntryVersionDto ev = new EntryVersionDto();
        ev.setDeStichwort(deStichwort);
        ev.setRmStichwort(rmStichwort);
        return ev;
    }

    protected EntryDto generateValidEntry() {
        EntryVersionDto lv = generateValidEntryVersionDto("a" + UUID.randomUUID(), "b" + UUID.randomUUID());
        return generateValidEntryFromEntryVersionDto(lv);
    }

    protected EntryDto generateValidEntryFromEntryVersionDto(EntryVersionDto ev) {
        EntryDto entry = new EntryDto();
        entry.setCurrent(ev);
        entry.setEntryId(UUID.randomUUID().toString());
        return entry;
    }
}
