package ch.pledarigrond.lucene.core;

import ch.pledarigrond.common.config.LuceneConfiguration;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.io.File;
import java.util.UUID;

public abstract class BaseLuceneIndexTest {

    protected LuceneConfiguration luceneConfiguration;
    protected LuceneIndexManager luceneIndexManager;

    private File indexDir;

    @Before
    public void beforeTest() throws Exception {
        Language language = Language.SURMIRAN;
        File file = File.createTempFile("pledarigrond", "test");
        indexDir = new File(file.getParentFile(), "pg_test" + UUID.randomUUID() + "_idx");
        Assert.assertFalse(indexDir.exists());
        indexDir.mkdir();
        file.deleteOnExit();
        luceneConfiguration = new LuceneConfiguration(language, indexDir.getAbsolutePath());
        luceneIndexManager = new LuceneIndexManager(luceneConfiguration);
    }

    @After
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

    protected LemmaVersion generateValidLemmaVersion(String DStichwort, String RStichwort) {
        LemmaVersion lv = new LemmaVersion();
        lv.putEntryValue("DStichwort", DStichwort);
        lv.putEntryValue("RStichwort", RStichwort);
        return lv;
    }

    protected LexEntry generateValidEntry() {
        LemmaVersion lv = generateValidLemmaVersion("a" + UUID.randomUUID(), "b" + UUID.randomUUID());
        return generateValidEntryFromLemmaVersion(lv);
    }

    protected LexEntry generateValidEntryFromLemmaVersion(LemmaVersion lv) {
        LexEntry entry = new LexEntry(lv);
        lv.setVerification(LemmaVersion.Verification.ACCEPTED);
        entry.setId(UUID.randomUUID().toString());
        return entry;
    }
}
