package ch.pledarigrond.api.controllers.user;

import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("search")
public class SearchController {

    Logger logger = LoggerFactory.getLogger(SearchController.class);

    @GetMapping("")
    List<String> search(@Validated SearchCriteria searchCriteria, @Validated Pagination pagination) {
        logger.warn(searchCriteria.getSearchPhrase());
        logger.warn(searchCriteria.getSearchDirection().toString());
        logger.warn(searchCriteria.getSearchMethod().toString());
        logger.warn(searchCriteria.getHighlight().toString());
        logger.warn(searchCriteria.getSuggestions().toString());

        logger.warn(String.valueOf(pagination.getPage()));
        logger.warn(String.valueOf(pagination.getPageSize()));

        return new ArrayList<>();
    }
}
