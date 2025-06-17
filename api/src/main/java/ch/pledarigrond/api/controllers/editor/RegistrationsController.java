package ch.pledarigrond.api.controllers.editor;

import ch.pledarigrond.api.services.RegistrationService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.database.services.DictionaryService;
import ch.pledarigrond.pronunciation.dto.ListFilter;
import ch.pledarigrond.pronunciation.entities.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("{language}/editor/registrations")
public class RegistrationsController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private DictionaryService dictionaryService;

    @PreAuthorize("hasPermission('registrations', 'editor')")
    @GetMapping("/list")
    ResponseEntity<?> list(ListFilter filter, Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getPageSize());
        Page<Registration> registrations = registrationService.getRegistrations(filter, pageable);
        return ResponseEntity.ok(registrations);
    }

    @PreAuthorize("hasPermission('registrations', 'editor')")
    @GetMapping("/next")
    ResponseEntity<?> getNext() {
        return ResponseEntity.ok(registrationService.getNextRegistration());
    }

    @PreAuthorize("hasPermission('registrations', 'editor')")
    @PostMapping("/postpone")
    ResponseEntity<?> postpone(@Validated @RequestBody Registration registration) {
        return ResponseEntity.ok(registrationService.postponeRegistration(registration));
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @PostMapping("/accept")
    ResponseEntity<?> accept(@PathVariable("language") Language language, @Validated @RequestBody Registration registration) {
        return ResponseEntity.ok(registrationService.acceptRegistration(registration));
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @PostMapping("/reject")
    ResponseEntity<?> reject(@PathVariable("language") Language language, @Validated @RequestBody Registration registration) {
        return ResponseEntity.ok(registrationService.rejectRegistration(registration));
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @PostMapping("/postpone_review")
    ResponseEntity<?> postponeReview(@PathVariable("language") Language language, @Validated @RequestBody Registration registration) {
        return ResponseEntity.ok(registrationService.postponeReviewRegistration(registration));
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @PostMapping("/add_to_lemma/{entryId}")
    ResponseEntity<?> addRegistrationToLemma(@PathVariable("language") Language language, @Validated @RequestBody Registration registration, @PathVariable(value="entryId") String entryId) throws IOException {
        Registration loadedRegistration = registrationService.getRegistration(registration.getId());
        if (loadedRegistration == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registrationService.addRegistrationToLemma(loadedRegistration, entryId));
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @PostMapping("/did_order/{entryId}")
    ResponseEntity<?> didOrder(@PathVariable("language") Language language,  @PathVariable(value="entryId") String entryId) {
        return ResponseEntity.ok(registrationService.getOrderedRegistration(entryId));
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @PostMapping("/order")
    ResponseEntity<?> order(@PathVariable("language") Language language,  @Validated @RequestBody Registration registration) {
        return ResponseEntity.ok(registrationService.order(registration));
    }

    @PreAuthorize("hasPermission('language', 'editor')")
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable("language") Language language, @PathVariable("id") String id) {
        registrationService.deleteRegistration(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasPermission('registrations', 'editor')")
    @GetMapping("/statistics")
    ResponseEntity<?> statistics() {
        return ResponseEntity.ok(registrationService.getStatistics());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/extract_single_words")
    ResponseEntity<?> extractSingleWords() {
        registrationService.extractSingleWords(dictionaryService.getStreamForEntries());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/extract_list_of_words_by_ending")
    ResponseEntity<?> extractListOfWordsByEnding() {
        ByteArrayResource resource = registrationService.extractListOfWordsByEnding(dictionaryService.getStreamForEntries());
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=strings.txt")
                .body(resource);
    }

    @PreAuthorize("hasPermission('registrations', 'editor')")
    @PostMapping("/upload")
    public ResponseEntity<Registration> uploadFile(@RequestPart("registration") Registration registration, @RequestPart("file") MultipartFile file) {
        try {
            registration = registrationService.uploadRegistration(registration, file);
            return ResponseEntity.ok(registration);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
