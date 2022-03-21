package ch.pledarigrond.api.controllers.user;

import ch.pledarigrond.api.services.LuceneService;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("user/search")
public class SearchController {

    Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private LuceneService luceneService;

    @GetMapping("")
    QueryResult search(@Validated SearchCriteria searchCriteria, @Validated Pagination pagination) {
        logger.warn(searchCriteria.getSearchPhrase());
        logger.warn(searchCriteria.getSearchDirection().toString());
        logger.warn(searchCriteria.getSearchMethod().toString());
        logger.warn(searchCriteria.getHighlight().toString());
        logger.warn(searchCriteria.getSuggestions().toString());

        logger.warn(String.valueOf(pagination.getPage()));
        logger.warn(String.valueOf(pagination.getPageSize()));

        try {
            QueryResult result = luceneService.query(searchCriteria, pagination, true);
            return result;
        } catch (InvalidQueryException e) {
            e.printStackTrace();
        } catch (NoIndexAvailableException e) {
            e.printStackTrace();
        } catch (BrokenIndexException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidTokenOffsetsException e) {
            e.printStackTrace();
        }

        return null;
    }
}
