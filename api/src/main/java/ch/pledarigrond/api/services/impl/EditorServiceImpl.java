package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.EditorService;
import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.api.services.MongoDbService;
import ch.pledarigrond.api.services.UserService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.*;
import ch.pledarigrond.common.data.user.Pagination;
import ch.pledarigrond.common.data.user.SearchCriteria;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.lucene.exceptions.BrokenIndexException;
import ch.pledarigrond.lucene.exceptions.InvalidQueryException;
import ch.pledarigrond.lucene.exceptions.NoIndexAvailableException;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.mongodb.model.PgUserInfo;
import org.apache.commons.io.IOUtils;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
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

    private final Logger logger = LoggerFactory.getLogger(EditorServiceImpl.class);

    @Override
    public Page<LexEntry> getLexEntries(Language language, EditorQuery query, Pagination pagination) throws Exception {
        Page<LexEntry> result = Database
                .getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language))
                .queryForLexEntries(query.getUserOrIp(), query.getRole(), query.getVerification(), query.getVerifier(), query.getStartTime(), query.getEndTime(), query.getState(), pagination.getPageSize(), pagination.getPage(), query.getSortColumn(), query.isSortAscending());
        for (LexEntry lexEntry : result.getContent()) {
            addUserInfos(lexEntry);
        }
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
    public LexEntry update(Language language, LexEntry entry, LemmaVersion updated) throws Exception {
        db.update(language, entry, updated);
        return entry;
    }

    @Override
    public LexEntry dropOutdatedHistory(Language language, LexEntry entry) throws Exception {
        db.dropOutdatedHistory(language, entry);
        return entry;
    }

    @Override
    public QueryResult search(Language language, SearchCriteria searchCriteria, Pagination pagination) throws Exception {
        return index.query(language, searchCriteria, pagination, false);
    }

    @Override
    public LexEntry getLexEntry(Language language, String entryId) throws Exception {
        return Converter.convertToLexEntry(Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language)).getById(entryId));
    }

    @Override
    public LexEntry insert(Language language, LexEntry entry) throws Exception {
        db.insert(language, entry);
        return entry;
    }

    @Override
    public List<LexEntry> updateOrder(Language language, DictionaryLanguage dictionaryLanguage, List<LemmaVersion> ordered) throws Exception {
        return db.updateOrder(language, dictionaryLanguage, ordered);
    }

    @Override
    public ArrayList<LemmaVersion> getOrder(Language language, String lemma, DictionaryLanguage dictionaryLanguage) throws Exception {
        return new ArrayList<LemmaVersion>(index.queryExact(language, lemma, dictionaryLanguage, false).getEntries());
    }

    @Override
    public String export(Language language, Set<String> fields, EditorQuery query) throws NoDatabaseAvailableException, IOException {
        File dir = new File("exports");
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
    public String export(Language language, Set<String> fields, SearchCriteria query)
            throws Exception {
        File dir = new File("exports");
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
        File dir = new File("exports");
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

    /*
    @Override
    public HashMap<String, String> getOverlayEditorPreset(Language language, String overlayId, String presetId, String base) throws GenerationFailedException {
        try {
            OverlayEditor editor = Overlays.getEditor(overlayType);
            if(editor != null) {
                OverlayPresetChooser chooser = editor.getPresetChooser();
                String presetBuilderClass = chooser.getPresetBuilderClass();
                if(presetBuilderClass != null) {
                    Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(presetBuilderClass);
                    IOverlayGenerator generator = (IOverlayGenerator) clazz.newInstance();
                    return generator.buildPreset(presetId, base);
                }
            }
        } catch (ClassNotFoundException e) {
            logger.error("Failed to find overlay generator class", e);
        } catch (InstantiationException e) {
            logger.error("Failed to instantiate overlay generator class", e);
        } catch (IllegalAccessException e) {
            logger.error("Failed to instantiate overlay generator class", e);
        }
        return null;
    }
    */


    private void addUserInfos(LexEntry lexEntry) {
        List<LemmaVersion> lemmata = lexEntry.getVersionHistory();
        for (LemmaVersion lemma : lemmata) {
            addUserInfo(lemma);
        }
    }

    private void addUserInfo(LemmaVersion lemma) {
        String userId = lemma.getUserId();
        PgUserInfo userInfo = userInfos.getByEmail(userId);
        if(userInfo != null) {
            lemma.setUserInfo(userInfo.toLightUser());
        }
    }

    private void export(Language language, Set<String> fields, EditorQuery query, File dest) throws NoDatabaseAvailableException, IOException {
        Pagination pagination = new Pagination();
        pagination.setPage(1);
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
                    .queryForLexEntries(query.getUserOrIp(), query.getRole(), query.getVerification(), query.getVerifier(), query.getStartTime(), query.getEndTime(), query.getState(), pagination.getPageSize(), pagination.getPage(), query.getSortColumn(), query.isSortAscending());
            if(result == null || result.getContent().size() == 0) break;
            for (LexEntry lexEntry : result.getContent()) {
                addUserInfos(lexEntry);
                LemmaVersion version = lexEntry.getCurrent();
                write(writer, version, fields);
                writer.write("\n");
            }
            pagination.setPage(pagination.getPage() + pagination.getPageSize());
        }
        writer.flush();
        zout.closeEntry();
        writer.close();
    }

    public void export(Language language, Set<String> fields, SearchCriteria query, File dest) throws IOException, InvalidQueryException, NoIndexAvailableException, BrokenIndexException, InvalidTokenOffsetsException {
        Pagination pagination = new Pagination();
        pagination.setPage(0);
        pagination.setPageSize(50);

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
            QueryResult result = index.query(language, query, pagination, false);
            if(result == null ||result.getEntries() == null || result.getEntries().size() == 0) break;
            List<LemmaVersion> entries = result.getEntries();
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
}
