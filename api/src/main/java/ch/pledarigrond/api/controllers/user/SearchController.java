package ch.pledarigrond.api.controllers.user;

import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.QueryResult;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private LuceneService luceneService;

    @GetMapping("")
    ResponseEntity<?> search(
            @PathVariable("language") Language language,
            @Validated SearchCriteria searchCriteria,
            @Validated Pagination pagination
    ) {
        try {
            QueryResult result = luceneService.query(language, searchCriteria, pagination, true);
             return ResponseEntity.ok(result);
        } catch (InvalidQueryException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid query.");
        } catch (NoIndexAvailableException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No index.");
        } catch (BrokenIndexException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Broken index.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "IO Exception.");
        } catch (InvalidTokenOffsetsException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid token offset.");
        }
    }
}
