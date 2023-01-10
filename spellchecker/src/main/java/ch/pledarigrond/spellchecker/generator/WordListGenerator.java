package ch.pledarigrond.spellchecker.generator;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.names.entities.Name;
import ch.pledarigrond.spellchecker.utils.freemarker.FreemarkerConfigSpellchecker;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.*;

public class WordListGenerator {
    private static final Logger logger = LoggerFactory.getLogger(WordListGenerator.class);

    protected PgEnvironment pgEnvironment;
    protected Map<String, PartOfSpeechTag> posLookupTable = new HashMap<>();

    private Set<String> blocklist = new HashSet<>();
    private Map<PartOfSpeechTag, List<String>> result = new HashMap<>();

    private final List<Name> names;

    public WordListGenerator(PgEnvironment pgEnvironment, List<Name> names) {
        this.pgEnvironment = pgEnvironment;
        this.names = names;
    }

    public File exportWordlist(Language language) throws NoDatabaseAvailableException, IOException {
        blocklist = new HashSet<>();
        loadBlockList(language);

        for (PartOfSpeechTag tag: PartOfSpeechTag.values()) {
            result.put(tag, new ArrayList<>());
        }

        File dir = new File(pgEnvironment.getTempExportLocation());
        dir.mkdirs();

        loadValidWords(language);

        List<File> files = new ArrayList<>();
        for (Map.Entry<PartOfSpeechTag, List<String>> entry : result.entrySet()) {
            String fileName = entry.getKey().getName().replace(".", "-");
            File file = new File(dir, fileName + "_baseform.txt");
            writeListToFile(file, entry.getValue());
            files.add(file);
        }

        // create version file
        File versionFile = new File(dir, "_VERSION.txt");
        String versionAndBuild = "";
        try {
            SimpleDateFormat buildDateFormat = new SimpleDateFormat("yyMMdd.HHmmss");
            Date now = new Date();

            Map<String, Object> versionTemplateData = new HashMap<>();
            String version = getVersion();
            String buildId = buildDateFormat.format(now);
            versionTemplateData.put("version", version);
            versionTemplateData.put("buildid", buildId);
            versionAndBuild = version + "-" + buildId;
            Writer versionFileWriter = new FileWriter(versionFile);
            Template versionTemplate = FreemarkerConfigSpellchecker.getConfig().getTemplate("version.ftlh");
            versionTemplate.process(versionTemplateData, versionFileWriter);
            versionFileWriter.flush();
            versionFileWriter.close();
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }

        // create licence file
        File licenceFile = new File(dir, "_LICENSE.txt");
        try {
            SimpleDateFormat yearDateFormat = new SimpleDateFormat("yyyy");
            Date now = new Date();

            Map<String, Object> licenceTemplateData = new HashMap<>();
            licenceTemplateData.put("year", yearDateFormat.format(now));
            licenceTemplateData.put("publisher", "Lia Rumantscha (www.liarumantscha.ch) / Pro Svizra Rumantscha (www.rumantsch.ch)");
            Writer licenceFileWriter = new FileWriter(licenceFile);
            Template licenceTemplate = FreemarkerConfigSpellchecker.getConfig().getTemplate("licence.ftlh");
            licenceTemplate.process(licenceTemplateData, licenceFileWriter);
            licenceFileWriter.flush();
            licenceFileWriter.close();
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }

        // write Zip
        files.add(versionFile);
        files.add(licenceFile);
        File zipFile = new File(dir, "wordlist_" + language + "_" + versionAndBuild + ".zip");
        WordListUtils.writeFilesToZip(zipFile, files);

        return zipFile;
    }

    protected String normalizeString(String input) {
        return input;
    }

    private void loadValidWords(Language language) throws NoDatabaseAvailableException {
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();

        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);
            LemmaVersion current = entry.getCurrent();

            String RStichwort = current.getEntryValue("RStichwort");
            RStichwort = normalizeString(RStichwort);
            if (blocklist.contains(RStichwort)) {
                continue;
            }

            String RGrammatik = current.getEntryValue("RGrammatik");
            if (RGrammatik == null) {
                String RGenus = current.getEntryValue("RGenus");
                if (RGenus != null && !RGenus.equals("")) {
                    RGrammatik = "subst";
                } else {
                    logger.debug("No grammar defined for word: {}", RStichwort);
                    continue;
                }
            }
            PartOfSpeechTag tag = posLookupTable.get(RGrammatik);

            if (tag == null) {
                logger.debug("No POS entry defined for grammar: {}", RGrammatik);
                continue;
            }

            result.get(tag).add(RStichwort);
        }

        if (names != null && result.get(PartOfSpeechTag.NOUN) != null) {
            names.forEach(name -> {
                String rm = WordListUtils.getRomanshNameForLanguage(language, name);
                if (rm != null) {
                    result.get(PartOfSpeechTag.NOUN).add(rm);
                }

                String de = WordListUtils.getGermanNameForLanguage(language, name);
                if (de != null) {
                    result.get(PartOfSpeechTag.NOUN).add(de);
                }
            });
        }
    }

    private void loadBlockList(Language language) throws IOException {
        ClassPathResource resource = new ClassPathResource(language.getName() + "/words_to_ignore.txt");
        InputStream is = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        while(reader.ready()) {
            String line = reader.readLine();
            if (line.startsWith("#")) {
                continue;
            }

            blocklist.add(line);
        }
    }

    private void writeListToFile(File wordListFile, List<String> list) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(wordListFile))) {
            for (String value : list) {
                if (value == null) continue;
                bf.write(value);
                bf.newLine();
            }

            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getVersion() {
        try {
            final Properties properties = new Properties();
            properties.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
            return properties.getProperty("pg.version");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
