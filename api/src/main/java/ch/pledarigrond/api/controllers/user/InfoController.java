package ch.pledarigrond.api.controllers.user;

import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.common.data.common.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("{language}/user/info")
public class InfoController {

    @Autowired
    private LuceneService luceneService;

    @GetMapping("entries")
    public ResponseEntity<?> getNumberOfEntries(@PathVariable("language") Language language) {
        luceneService.getIndexStatistics(language);
        return ResponseEntity.ok(luceneService.getIndexStatistics(language).getApprovedEntries());
    }
}
