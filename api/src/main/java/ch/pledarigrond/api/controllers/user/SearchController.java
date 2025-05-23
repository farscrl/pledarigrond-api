package ch.pledarigrond.api.controllers.user;

import ch.pledarigrond.api.dtos.PageDto;
import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.SearchDirection;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("{language}/user/search")
public class SearchController {

    private final Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private LuceneService luceneService;

    @GetMapping("")
    ResponseEntity<?> search(
            @PathVariable("language") Language language,
            @Validated SearchCriteria searchCriteria,
            @Validated Pagination pagination
    ) {
        try {
            Page<LemmaVersion> result = luceneService.query(language, searchCriteria, pagination, true);
            
            String[] suggestionsRm = luceneService.getSuggestionForWord(language, searchCriteria.getSearchPhrase(), 5, SearchDirection.ROMANSH);
            String[] suggestionsDe = luceneService.getSuggestionForWord(language, searchCriteria.getSearchPhrase(), 5, SearchDirection.GERMAN);
            PageDto<LemmaVersion> suggestionPage = new PageDto<>(
                    result.getContent(),
                    result.getPageable(),
                    result.getTotalElements(),
                    suggestionsRm,
                    suggestionsDe
            );
            return ResponseEntity.ok(suggestionPage);

        } catch (InvalidQueryException | BrokenIndexException | NoIndexAvailableException | IOException e) {
            logger.error("Error while searching", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("Error while generating suggestions", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
