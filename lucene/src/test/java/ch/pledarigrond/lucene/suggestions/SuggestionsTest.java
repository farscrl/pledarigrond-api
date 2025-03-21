package ch.pledarigrond.lucene.suggestions;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static ch.pledarigrond.lucene.suggestions.SuggestionsIndex.prioritizeSuggestions;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuggestionsTest {

    @Test
    public void testPrioritizeSuggestions_SearchWordStartsWithTg_AndSuggestionsStartWithCh() {
        // Given
        String searchWord = "tgaun";
        String[] suggestions = {"Alaun", "Plaun", "braun", "chaun", "graun"};

        // When
        List<String> result = prioritizeSuggestions(searchWord, suggestions);

        // Then
        List<String> expected = Arrays.asList("chaun", "Alaun", "Plaun", "braun", "graun");
        assertEquals(expected, result);
    }

    @Test
    public void testPrioritizeSuggestions_SearchWordDoesNotStartWithTg() {
        // Given
        String searchWord = "chaun";
        String[] suggestions = {"Alaun", "Plaun", "braun", "chaun", "graun"};

        // When
        List<String> result = prioritizeSuggestions(searchWord, suggestions);

        // Then
        List<String> expected = Arrays.asList("Alaun", "Plaun", "braun", "chaun", "graun");
        assertEquals(expected, result);
    }

}
