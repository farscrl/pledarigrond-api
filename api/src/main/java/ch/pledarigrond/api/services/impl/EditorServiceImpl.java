package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.EditorService;
import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.api.services.UserInfoService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.DictionaryLanguage;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.SearchSuggestions;
import ch.pledarigrond.common.data.dictionary.DbSearchCriteria;
import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.common.data.dictionary.NormalizedEntryVersionsDto;
import ch.pledarigrond.common.data.lucene.SuggestionField;
import ch.pledarigrond.common.data.user.LuceneSearchCriteria;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.exception.dictionary.InvalidReviewLaterException;
import ch.pledarigrond.common.exception.dictionary.SuggestionNotFoundException;
import ch.pledarigrond.database.services.DictionaryService;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import ch.pledarigrond.users.services.UserService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class EditorServiceImpl implements EditorService {

    @Autowired
    private PgEnvironment pgEnvironment;

    @Autowired
    private LuceneService luceneService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    private final Logger logger = LoggerFactory.getLogger(EditorServiceImpl.class);

    @Override
    public Page<NormalizedEntryVersionsDto> getDictionaryVersions(DbSearchCriteria query, Pagination pagination) {
        return dictionaryService.queryForEntries(query, pagination.getPageSize(), pagination.getPage());
    }

    @Override
    public EntryDto getEntry(String entryId) {
        return dictionaryService.getEntry(entryId);
    }

    @Override
    public EntryDto addEntry(EntryVersionDto version, boolean asSuggestion) throws IOException {
        EntryDto entry = dictionaryService.addEntry(version, asSuggestion, userInfoService.getCurrentUserInfo());
        luceneService.update(entry);
        return entry;
    }

    @Override
    public EntryDto addVersion(String entryId, EntryVersionDto version, boolean asSuggestion) throws IOException {
        EntryDto entry = dictionaryService.addVersion(entryId, version, asSuggestion, userInfoService.getCurrentUserInfo());
        luceneService.update(entry);
        return entry;
    }

    @Override
    public EntryDto replaceSuggestion(String entryId, String versionToReplaceId, EntryVersionDto version, boolean asSuggestion) throws IOException, SuggestionNotFoundException {
        EntryDto entry = dictionaryService.replaceSuggestion(entryId, versionToReplaceId, version, asSuggestion, userInfoService.getCurrentUserInfo());
        luceneService.update(entry);
        return entry;
    }

    @Override
    public EntryDto accept(String entryId, String versionIdToAccept) throws SuggestionNotFoundException, IOException {
        EntryDto entry =  dictionaryService.accept(entryId, versionIdToAccept, userInfoService.getCurrentUserInfo());
        luceneService.update(entry);
        return entry;
    }

    @Override
    public EntryDto reject(String entryId, String versionIdToReject) throws SuggestionNotFoundException, IOException {
        EntryDto entry =  dictionaryService.reject(entryId, versionIdToReject, userInfoService.getCurrentUserInfo());
        luceneService.update(entry);
        return entry;
    }

    @Override
    public void delete(String entryId) throws IOException {
        EntryDto entry = dictionaryService.getEntry(entryId);
        luceneService.delete(entry);
        dictionaryService.delete(entryId);
    }

    @Override
    public EntryDto reviewSuggestionLater(String entryId, String versionId) throws InvalidReviewLaterException, SuggestionNotFoundException, IOException {
        EntryDto entry =  dictionaryService.reviewSuggestionLater(entryId, versionId);
        luceneService.update(entry);
        return entry;
    }

    @Override
    public EntryDto dropOutdatedHistory(String entryId) throws IOException {
        EntryDto entry =  dictionaryService.dropOutdatedHistory(entryId);
        luceneService.update(entry);
        return entry;
    }

    @Override
    public Page<EntryVersionDto> search(LuceneSearchCriteria luceneSearchCriteria, Pagination pagination) throws BrokenIndexException, NoIndexAvailableException, IOException, InvalidQueryException {
        return luceneService.query(luceneSearchCriteria, pagination, false);
    }

    @Override
    public ArrayList<EntryVersionDto> getOrder(String lemma, DictionaryLanguage dictionaryLanguage) throws BrokenIndexException, NoIndexAvailableException, IOException, InvalidQueryException {
        return new ArrayList<>(luceneService.searchExactMatches(lemma, dictionaryLanguage, false).getContent());
    }

    @Override
    public List<EntryDto> updateOrder(DictionaryLanguage dictionaryLanguage, List<EntryVersionDto> ordered) throws IOException {
        List<EntryDto> modified = dictionaryService.updateOrder(dictionaryLanguage, ordered, userInfoService.getCurrentUserInfo());
        luceneService.updateAll(modified);
        return modified;
    }

    @Override
    public String export(Set<String> fields, DbSearchCriteria query) throws IOException {
        File dir = new File(pgEnvironment.getTempExportLocation());
        dir.mkdirs();
        final File tmp = new File(dir, "export_" + UUID.randomUUID() + ".tsv.zip");
        Timer timer = new Timer();
        export(fields, query, tmp);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(tmp.exists()) {
                    System.out.println("Deleting file " + tmp);
                    tmp.delete();
                }
            }
        }, 60000*30);
        return tmp.getName();
    }

    @Override
    public String export(Set<String> fields, LuceneSearchCriteria query) throws BrokenIndexException, NoIndexAvailableException, IOException, InvalidQueryException {
        File dir = new File(pgEnvironment.getTempExportLocation());
        dir.mkdirs();
        final File tmp = new File(dir, "export_" + UUID.randomUUID() + ".tsv.zip");
        Timer timer = new Timer();
        export(fields, query, tmp);
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                if(tmp.exists()) {
                    System.out.println("Deleting file " + tmp);
                    tmp.delete();
                }
            }
        }, 60000*30);
        return tmp.getName();
    }

    @RequestMapping("/editor/download/{fileName}.html")
    public void export(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
        File dir = new File(pgEnvironment.getTempExportLocation());
        File file = new File(dir, fileName);
        ServletOutputStream out = response.getOutputStream();
        if(!file.exists()) {
            OutputStreamWriter writer = new OutputStreamWriter(out);
            writer.write("This link has expired. Please re-export the data and try again.");
            writer.flush();
            return;
        }
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        IOUtils.copy(in, out);
        in.close();
        out.close();
        file.delete();
    }

    @Override
    public SearchSuggestions getSuggestionsForFields(Language language) throws NoIndexAvailableException, IOException {
        SearchSuggestions suggestions = new SearchSuggestions();
        suggestions.setGrammar(luceneService.getSuggestionsForFieldChoice(SuggestionField.GRAMMAR, "", 10000));
        suggestions.setGender(luceneService.getSuggestionsForFieldChoice(SuggestionField.GENDER, "", 10000));
        return suggestions;
    }

    private void export(Set<String> fields, DbSearchCriteria query, File dest) throws IOException {
        Pagination pagination = new Pagination();
        pagination.setPageSize(100);

        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(dest));
        zout.putNextEntry(new ZipEntry("exported.tsv"));
        OutputStream out = new BufferedOutputStream(zout);
        OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        for (String field : fields) {
            writer.write(field);
            writer.write("\t");
        }
        writer.write("\n");

        while(true) {
            Page<NormalizedEntryVersionsDto> result = dictionaryService.queryForEntries(query, pagination.getPageSize(), pagination.getPage());
            if(result == null || result.getContent().isEmpty()) break;
            for (NormalizedEntryVersionsDto normalizedVersion : result.getContent()) {
                write(writer, normalizedVersion.getVersion(), fields);
                writer.write("\n");
            }
            pagination.setPage(pagination.getPage() + 1);
        }
        writer.flush();
        zout.closeEntry();
        writer.close();
    }

    public void export(Set<String> fields, LuceneSearchCriteria query, File dest) throws IOException, BrokenIndexException, NoIndexAvailableException, InvalidQueryException {
        Pagination pagination = new Pagination();
        pagination.setPageSize(100);

        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(dest));
        zout.putNextEntry(new ZipEntry("exported.tsv"));
        OutputStream out = new BufferedOutputStream(zout);
        OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
        for (String field : fields) {
            writer.write(field);
            writer.write("\t");
        }
        writer.write("\n");

        while(true) {
            Page<EntryVersionDto> result = luceneService.query(query, pagination, false);
            if(result == null || result.getContent().isEmpty()) break;
            List<EntryVersionDto> entries = result.getContent();
            for (EntryVersionDto version : entries) {
                write(writer, version, fields);
                writer.write("\n");
            }
            pagination.setPage(pagination.getPage() + 1);
        }
        writer.flush();
        zout.closeEntry();
        writer.close();

    }

    private void write(OutputStreamWriter writer, EntryVersionDto version, Set<String> fields) throws IOException {
        for (String field : fields) {
            String value = switch (field) {
                case "deStichwort" -> version.getDeStichwort();
                case "deGrammatik" -> version.getDeGrammatik();
                case "deGenus" -> version.getDeGenus();
                case "deSubsemantik" -> version.getDeSubsemantik();
                case "deRedirect" -> version.getDeRedirect();
                case "deTags" -> version.getDeTags();
                case "rmStichwort" -> version.getRmStichwort();
                case "rmGrammatik" -> version.getRmGrammatik();
                case "rmGenus" -> version.getRmGenus();
                case "rmSubsemantik" -> version.getRmSubsemantik();
                case "rmRedirect" -> version.getRmRedirect();
                case "rmFlex" -> version.getRmFlex();
                case "rmTags" -> version.getRmTags();
                case "categories" -> version.getCategories();
                case "userComment" -> version.getUserComment();
                default -> throw new IllegalStateException("Unexpected value: " + field);
            };
            if(value != null) writer.write(value.trim());
            writer.write("\t");
        }
    }




}
