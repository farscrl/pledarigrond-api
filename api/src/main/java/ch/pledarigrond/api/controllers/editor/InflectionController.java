package ch.pledarigrond.api.controllers.editor;

import ch.pledarigrond.api.services.InflectionService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("{language}/editor/inflection")
public class InflectionController {

    @Autowired
    private InflectionService inflectionService;

    @PreAuthorize("hasPermission('language', 'editor')")
    @GetMapping("/{inflectionType}/subtypes")
    ResponseEntity<?> getSubtypes(@PathVariable("language") Language language, @PathVariable("inflectionType") InflectionType inflectionType) {
        if (inflectionType == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(inflectionService.getInflectionTypes(language, inflectionType));
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @GetMapping("/{inflectionType}/subtypes/{baseForm}")
    ResponseEntity<?> guessSubtypeAndGenerateInflection(@PathVariable("language") Language language, @PathVariable("inflectionType") InflectionType inflectionType, @PathVariable("baseForm") String baseForm, String genus, String flex) {
        if (inflectionType == null) {
            return ResponseEntity.badRequest().build();
        }
        if (baseForm == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(inflectionService.guessInflection(language, inflectionType, baseForm, genus, flex));
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @GetMapping("/{inflectionType}/{inflectionSubType}/{baseForm}")
    ResponseEntity<?> generateInflection(@PathVariable("language") Language language, @PathVariable("inflectionType") InflectionType inflectionType, @PathVariable("inflectionSubType") String inflectionSubType, @PathVariable("baseForm") String baseForm) {
        if (inflectionType == null) {
            return ResponseEntity.badRequest().build();
        }
        if (inflectionSubType == null) {
            return ResponseEntity.badRequest().build();
        }
        if (baseForm == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(inflectionService.generateInflection(language, inflectionType, inflectionSubType, baseForm));
    }
}
