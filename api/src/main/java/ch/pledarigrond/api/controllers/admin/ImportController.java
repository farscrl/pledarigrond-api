package ch.pledarigrond.api.controllers.admin;

import ch.pledarigrond.api.services.ImportService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.exception.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.net.UnknownHostException;

@RestController
@RequestMapping("{language}/admin/import")
public class ImportController {

    @Autowired
    private ImportService importService;

    /**
     * Allows to import excel files with the LRC data for sursilvan
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/import_excel_sursilvan")
    ResponseEntity<?> importSursilvan(@PathVariable("language") Language language, HttpServletRequest request) throws DatabaseException, UnknownHostException {
        if (language != Language.SURSILVAN) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }

        try {
            boolean success = importService.importXlsSursilvan(language, request);
            if (!success) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during changing grammar indications");
            }

            return ResponseEntity.ok().build();
        } catch (DatabaseException | IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (XMLStreamException | JAXBException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Allows to import the data for puter or vallader. data has to be in the project folder, it is not uploaded
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/import_ladin")
    ResponseEntity<?> importLadin(@PathVariable("language") Language language, HttpServletRequest request) throws DatabaseException, UnknownHostException {
        if (language != Language.PUTER && language != Language.VALLADER) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid language");
        }

        try {
            boolean success = importService.importLadinData(language, request);
            if (!success) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during import of ladin data");
            }

            return ResponseEntity.ok().build();
        } catch (DatabaseException | IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (XMLStreamException | JAXBException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
