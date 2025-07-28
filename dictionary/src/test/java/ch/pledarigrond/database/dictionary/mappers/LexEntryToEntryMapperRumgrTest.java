package ch.pledarigrond.database.dictionary.mappers;

import ch.pledarigrond.common.data.common.EditorRole;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.dictionary.PublicationStatus;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.database.dictionary.entities.Entry;
import ch.pledarigrond.database.dictionary.entities.EntryVersion;
import ch.pledarigrond.database.dictionary.entities.inflection.Inflection;
import ch.pledarigrond.database.dictionary.entities.inflection.Noun;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LexEntryToEntryMapperRumgrTest extends BaseLexEntryMapperTest {
    public LexEntryToEntryMapperRumgrTest() {
        language = Language.RUMANTSCHGRISCHUN;
    }

    @Test
    void testMap_withValidLexEntry_shouldMapCorrectly() throws Exception {
        LexEntry lexEntry = loadLexEntry("565d74ede4b03fa0e50ef88f");
        Entry entry = LexEntryToEntryMapper.map(lexEntry, language);

        assertNotNull(entry);
        assertEquals(lexEntry.getId(), entry.getEntryId());

        // Entry fields
        assertNotNull(entry.getCurrent());
        assertEquals(25, entry.getVersions().size());
        assertEquals(PublicationStatus.PUBLISHED, entry.getPublicationStatus());
        assertEquals(0, entry.getSuggestions().size());

        // EntryVersion fields (current)
        EntryVersion current = entry.getCurrent();
        assertNotNull(current.getVersionId());
        assertEquals(entry.getEntryId(), current.getEntryId());

        assertEquals("stop", current.getRmStichwort());
        assertEquals("2", current.getRmStichwortSort());
        assertEquals("m", current.getRmGenus());
        assertEquals("subst", current.getRmGrammatik());
        assertEquals("chaun, anat.", current.getRmSubsemantik());

        assertEquals("Stop", current.getDeStichwort());
        assertEquals("1", current.getDeStichwortSort());
        assertEquals("m", current.getDeGenus());
        assertEquals("Hund, anat.", current.getDeSubsemantik());

        assertEquals("anatomia,mamifers", current.getCategories());

        assertNotNull(current.getInflection());
        Inflection inflection = current.getInflection();
        assertEquals(InflectionType.NOUN, inflection.getInflectionType());
        assertNotNull(inflection.getNoun());
        Noun noun = inflection.getNoun();
        assertEquals("1", noun.getInflectionSubtype());
        assertFalse(noun.isIrregular());
        assertEquals("stop", noun.getBaseForm());
        assertEquals("stop", noun.getMSingular());
        assertNull(noun.getFSingular());
        assertEquals("stops", noun.getMPlural());
        assertNull(noun.getFPlural());
        assertNull(noun.getPluralCollectiv());

        assertEquals(1694423676758L, current.getTimestamp().toEpochMilli());
        assertEquals("10.0.0.1", current.getCreatorIp());
        assertEquals("dt@pg.ch", current.getCreator());
        assertEquals(EditorRole.EDITOR, current.getCreatorRole());
    }
}
