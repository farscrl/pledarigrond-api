package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.EditorService;
import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.api.services.MongoDbService;
import ch.pledarigrond.api.services.UserService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.lucene.SuggestionField;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.dictionary.dto.NormalizedEntryVersionsDto;
import ch.pledarigrond.dictionary.services.DictionaryService;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.mongodb.model.PgUser;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
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
    private MongoDbService db;

    @Autowired
    private UserService userInfos;

    @Autowired
    private LuceneService index;

    @Autowired
    private DictionaryService dictionaryService;

    private final Logger logger = LoggerFactory.getLogger(EditorServiceImpl.class);

    @Override
    public Page<NormalizedEntryVersionsDto> getDictionaryVersions(EditorQuery2 query, Pagination pagination) {
        Page<NormalizedEntryVersionsDto> result = dictionaryService.queryForEntries(query, pagination.getPageSize(), pagination.getPage(), false);

        /* TODO: check if still needed
        for (LexEntry lexEntry : result.getContent()) {
            addUserInfos(lexEntry);
        }*/
        return result;
    }

    @Override
    public LexEntry accept(Language language, LexEntry entry, LemmaVersion version) throws Exception {
        db.accept(language, entry, version);
        return entry;
    }

    @Override
    public LexEntry reject(Language language, LexEntry entry, LemmaVersion rejected) throws Exception {
        db.reject(language, entry, rejected);
        return entry;
    }

    @Override
    public LexEntry drop(Language language, LexEntry entry) throws Exception {
        db.delete(language, entry);
        return entry;
    }

    @Override
    public LexEntry acceptAfterUpdate(Language language, LexEntry entry, LemmaVersion suggested, LemmaVersion modified) throws Exception {
        db.acceptAfterUpdate(language, entry, suggested, modified);
        return entry;
    }

    @Override
    public LexEntry update(Language language, LexEntry lexEntry, LemmaVersion newVersion) throws Exception {
        db.update(language, lexEntry, newVersion);
        return lexEntry;
    }

    @Override
    public LexEntry reviewLater(Language language, LexEntry entry) throws Exception {
        db.reviewLater(language, entry);
        return entry;
    }

    @Override
    public LexEntry dropOutdatedHistory(Language language, LexEntry entry) throws Exception {
        db.dropOutdatedHistory(language, entry);
        return entry;
    }

    @Override
    public Page<LemmaVersion> search(Language language, SearchCriteria searchCriteria, Pagination pagination) throws Exception {
        return index.query(language, searchCriteria, pagination, false);
    }

    @Override
    public LexEntry getLexEntry(Language language, String entryId) throws Exception {
        return Converter.convertToLexEntry(Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language)).getById(entryId));
    }

    @Override
    public LexEntry insert(Language language, LexEntry entry) throws Exception {
        db.insert(language, entry, false);
        return entry;
    }

    @Override
    public LexEntry insertSuggestion(Language language, LexEntry entry) throws Exception {
        db.insert(language, entry, true);
        return entry;
    }

    @Override
    public List<LexEntry> updateOrder(Language language, DictionaryLanguage dictionaryLanguage, List<LemmaVersion> ordered) throws Exception {
        return db.updateOrder(language, dictionaryLanguage, ordered);
    }

    @Override
    public ArrayList<LemmaVersion> getOrder(Language language, String lemma, DictionaryLanguage dictionaryLanguage) throws Exception {
        return new ArrayList<>(index.searchExactMatches(language, lemma, dictionaryLanguage, false).getContent());
    }

    @Override
    public String export(Language language, Set<String> fields, EditorQuery query) throws NoDatabaseAvailableException, IOException {
        File dir = new File(pgEnvironment.getTempExportLocation());
        dir.mkdirs();
        final File tmp = new File(dir, "export_" + UUID.randomUUID() + ".tsv.zip");
        Timer timer = new Timer();
        export(language, fields, query, tmp);
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
    public String export(Language language, Set<String> fields, SearchCriteria query) throws Exception {
        File dir = new File(pgEnvironment.getTempExportLocation());
        dir.mkdirs();
        final File tmp = new File(dir, "export_" + UUID.randomUUID() + ".tsv.zip");
        Timer timer = new Timer();
        export(language, fields, query, tmp);
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
    public void export(Language language, @PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
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
        suggestions.setGrammar(index.getSuggestionsForFieldChoice(language, SuggestionField.GRAMMAR, "", 10000));
        suggestions.setGender(index.getSuggestionsForFieldChoice(language, SuggestionField.GENDER, "", 10000));
        return suggestions;
    }

    private void addUserInfos(LexEntry lexEntry) {
        List<LemmaVersion> lemmata = lexEntry.getVersionHistory();
        for (LemmaVersion lemma : lemmata) {
            addUserInfo(lemma);
        }
    }

    private void addUserInfo(LemmaVersion lemma) {
        String userId = lemma.getUserId();
        PgUser userInfo = userInfos.getByEmail(userId);
        if(userInfo != null) {
            lemma.setUserInfo(userInfo.toLightUser());
        }
    }

    private void export(Language language, Set<String> fields, EditorQuery query, File dest) throws NoDatabaseAvailableException, IOException {
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
            Page<LexEntry> result = Database
                    .getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language))
                    .queryForLexEntries(query.getUserOrIp(), query.getRole(), query.getVerification(), query.getVerifier(), query.getStartTime(), query.getEndTime(), query.getState(), pagination.getPageSize(), pagination.getPage(), query.getSortColumn(), query.isSortAscending(), excludeAutomaticChangesForLanguage(language));
            if(result == null || result.getContent().size() == 0) break;
            for (LexEntry lexEntry : result.getContent()) {
                addUserInfos(lexEntry);
                LemmaVersion version = lexEntry.getCurrent();
                write(writer, version, fields);
                writer.write("\n");
            }
            pagination.setPage(pagination.getPage() + 1);
        }
        writer.flush();
        zout.closeEntry();
        writer.close();
    }

    public void export(Language language, Set<String> fields, SearchCriteria query, File dest) throws IOException, InvalidQueryException, NoIndexAvailableException, BrokenIndexException {
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
            Page<LemmaVersion> result = index.query(language, query, pagination, false);
            if(result == null || result.getContent().size() == 0) break;
            List<LemmaVersion> entries = result.getContent();
            for (LemmaVersion version : entries) {
                write(writer, version, fields);
                writer.write("\n");
            }
            pagination.setPage(pagination.getPage() + 1);
        }
        writer.flush();
        zout.closeEntry();
        writer.close();

    }

    private void write(OutputStreamWriter writer, LemmaVersion version, Set<String> fields) throws IOException {
        for (String field : fields) {
            String value = version.getEntryValue(field);
            if(value != null) writer.write(value.trim());
            writer.write("\t");
        }
    }

    private boolean excludeAutomaticChangesForLanguage(Language language) {
        return false;
    }
}
