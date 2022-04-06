package ch.pledarigrond.common.data.common;

import lombok.Data;

import java.util.List;

@Data
public class SearchSuggestions {
    private List<String> grammar;
    private List<String> genus;
}
