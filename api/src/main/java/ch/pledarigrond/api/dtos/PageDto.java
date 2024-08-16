package ch.pledarigrond.api.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Setter
@Getter
public class PageDto<T> extends PageImpl<T> {

    private String[] suggestions;

    public PageDto(List<T> content, Pageable pageable, long total, String[] suggestions) {
        super(content, pageable, total);
        this.suggestions = suggestions;
    }

    public PageDto(List<T> content, String[] suggestions) {
        super(content);
        this.suggestions = suggestions;
    }
}
