package ch.pledarigrond.api.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Setter
@Getter
public class PageDto<T> extends PageImpl<T> {

    private String[] suggestionsRm;
    private String[] suggestionsDe;

    public PageDto(List<T> content, Pageable pageable, long total, String[] suggestionsRm, String[] suggestionsDe) {
        super(content, pageable, total);
        this.suggestionsRm = suggestionsRm;
        this.suggestionsDe = suggestionsDe;
    }

    public PageDto(List<T> content, String[] suggestionsRm, String[] suggestionsDe) {
        super(content);
        this.suggestionsRm = suggestionsRm;
        this.suggestionsDe = suggestionsDe;
    }
}
