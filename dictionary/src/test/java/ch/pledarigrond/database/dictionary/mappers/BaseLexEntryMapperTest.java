package ch.pledarigrond.database.dictionary.mappers;

import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LexEntry;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public abstract class BaseLexEntryMapperTest {
    protected Language language;

    protected LexEntry loadLexEntry(String id) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String resourcePath = String.format("migration/%s/%s.json", language.getSubtag(), id);
        InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath);
        assertNotNull(is, String.format("%s should be present in resources/migration/%s", id + ".json", language.getSubtag()));
        return objectMapper.readValue(is, LexEntry.class);
    }
}

