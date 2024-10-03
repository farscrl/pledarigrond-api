package ch.pledarigrond.corpus.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "corpus-text")
@Data
public class CorpusText {

    @Id
    @Indexed(unique = true)
    private String id;

    @Indexed
    private String originalId;

    @Indexed
    private String lang;

    private String text;

    @Indexed
    private String source;

    public CorpusText(String originalId, String lang, String text, String source) {
        super();
        this.id = UUID.randomUUID().toString();
        this.originalId = originalId;
        this.lang = lang;
        this.text = text;
        this.source = source;
    }
}
