package ch.pledarigrond.spellchecker.generator.hunspell;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.spellchecker.generator.WordListUtils;
import ch.pledarigrond.spellchecker.model.HunspellList;
import ch.pledarigrond.spellchecker.model.HunspellRules;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;

import static ch.pledarigrond.spellchecker.model.HunspellRules.RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI;
import static ch.pledarigrond.spellchecker.model.HunspellRules.SURMIRAN_PLEDS_APOSTROFAI;

abstract public class HunspellGenerator {

    private final Logger logger = LoggerFactory.getLogger(HunspellGenerator.class);

    PgEnvironment pgEnvironment;

    private final List<String> names;

    private final Language language;

    private Path basePath;

    public HunspellGenerator(Language language, PgEnvironment pgEnvironment, List<String> names) throws IOException {
        this.pgEnvironment = pgEnvironment;
        this.names = names;
        this.language = language;
        basePath = Paths.get(pgEnvironment.getHunspellLocation()).resolve(language.getName());
        Files.createDirectories(basePath);
    }

    public String generateHunspell() throws NoDatabaseAvailableException, IOException {
        // create dicFile
        Set<String> words = getAllValidWords(language);
        File dicFile = new File(basePath.toFile(), "rm-" + language.getSubtag() + ".dic");
        writeSetToHunspell(dicFile, words);

        // copy aff file
        ClassPathResource affFileResource = new ClassPathResource(language.getName() + "/rm-" + language.getSubtag() + ".aff");
        try (InputStream inputStream = affFileResource.getInputStream()) {
            Files.copy(inputStream, basePath.resolve("rm-" + language.getSubtag() + ".aff"), StandardCopyOption.REPLACE_EXISTING);
        }

        // create version file
        File versionFile = new File(basePath.toFile(), "rm-" + language.getSubtag()+ "_version.txt");
        String versionAndBuild;
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
        File licenceFile = new File(basePath.toFile(), "rm-" + language.getSubtag()+ "_LICENSE.txt");
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

        return versionAndBuild;
    }

    public File exportHunspell() throws NoDatabaseAvailableException, IOException {
        String versionAndBuild = generateHunspell();

        File dir = new File(pgEnvironment.getTempExportLocation());
        dir.mkdirs();

        File aff = basePath.resolve("rm-" + language.getSubtag() + ".aff").toFile();
        File dicFile = basePath.resolve("rm-" + language.getSubtag() + ".dic").toFile();
        File versionFile = basePath.resolve("rm-" + language.getSubtag() + "_version.txt").toFile();
        File licenceFile = basePath.resolve("rm-" + language.getSubtag() + "_LICENSE.txt").toFile();

        // write Zip
        List<File> files = new ArrayList<>();
        files.add(aff);
        files.add(dicFile);
        files.add(versionFile);
        files.add(licenceFile);
        File zipFile = new File(dir, "hunspell_" + language + "_" + versionAndBuild + ".zip");
        WordListUtils.writeFilesToZip(zipFile, files);

        return zipFile;
    }

    private void writeSetToHunspell(File wordListFile, Set<String> set) {
        // delete existing file
        if (wordListFile.exists()) {
            if (!wordListFile.delete()) {
                logger.warn("Warning: Failed to delete existing dict file {}", wordListFile.getName());
            }
        }

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(wordListFile))) {
            bf.write(String.valueOf(set.size() + 1));
            bf.newLine();

            for (String value : set) {
                if (value == null) continue;
                bf.write(value);
                bf.newLine();
            }

            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Set<String> getAllValidWords(Language language) throws NoDatabaseAvailableException, IOException {
        HunspellList hunspellList = new HunspellList();

        loadWordsToAdd(language, hunspellList);

        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();

        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            LemmaVersion current = entry.getCurrent();

            if (current.getVerification() != LemmaVersion.Verification.ACCEPTED) {
                // ignore lemmas that are not accepted (e.g. new suggestions)
                continue;
            }

            String inflectionType = current.getEntryValue(LemmaVersion.RM_INFLECTION_TYPE);
            if (inflectionType == null || inflectionType.equals("")) {
                extractDefault(hunspellList, current);
            } else if (inflectionType.equals("V")) {
                extractVerbs(hunspellList, current);
            } else if (inflectionType.equals("NOUN")) {
                extractNouns(hunspellList, current);
            } else if (inflectionType.equals("ADJECTIVE")) {
                extractAdjectives(hunspellList, current);
            } else if (inflectionType.equals("PRONOUN")) {
                extractPronouns(hunspellList, current);
            } else if (inflectionType.equals("OTHER")) {
                extractOtherForms(hunspellList, current);
            } else {
                throw new RuntimeException("Unexpected inflection type: " + inflectionType);
            }
        }

        if (names != null) {
            names.forEach(word -> {
                hunspellList.addWord(word, new HunspellRules[]{}, false);
            });
        }

        hunspellList.removeSoftHyphens();
        postProcessHunspellList(hunspellList);

        removeWordsFromBlocklist(language, hunspellList);

        return hunspellList.getListAsSet();
    }

    abstract protected void extractNouns(HunspellList list, LemmaVersion lemmaVersion);

    abstract protected void extractAdjectives(HunspellList list, LemmaVersion lemmaVersion);

    abstract protected void extractPronouns(HunspellList list, LemmaVersion lemmaVersion);

    abstract protected void extractVerbs(HunspellList list, LemmaVersion lemmaVersion);

    abstract protected void extractOtherForms(HunspellList list, LemmaVersion lemmaVersion);

    abstract protected void extractDefault(HunspellList list, LemmaVersion lemmaVersion);

    abstract protected String removePronouns(String value);

    abstract protected void postProcessHunspellList(HunspellList list);

    abstract protected String normalizeString(String input);



    private void loadWordsToAdd(Language language, HunspellList list) throws IOException {
        ClassPathResource resource = new ClassPathResource(language.getName() + "/missing_words.txt");
        InputStream is = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        while(reader.ready()) {
            String line = reader.readLine();

            if (!line.startsWith("#")) {
                if (language == Language.SURMIRAN) {
                    list.addWord(line, new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
                } else if (language == Language.RUMANTSCHGRISCHUN) {
                    list.addWord(line, new HunspellRules[]{RUMANTSCH_GRISCHUN_PLEDS_APOSTROFAI});
                } else if (language == Language.PUTER) {
                    list.addWord(line, new HunspellRules[]{});
                } else if (language == Language.VALLADER) {
                    list.addWord(line, new HunspellRules[]{});
                }
            }
        }
    }

    private void removeWordsFromBlocklist(Language language, HunspellList list) throws IOException {
        ClassPathResource resource = new ClassPathResource(language.getName() + "/words_to_ignore.txt");
        InputStream is = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        while(reader.ready()) {
            String line = reader.readLine();
            if (line.startsWith("#")) {
                continue;
            }

            list.removeWord(line);
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

    protected void addNewlines(String form, ArrayList<String> list) {
        if (form == null) {
            return;
        }
        String[] lines = form.split("\\R");
        list.addAll(Arrays.asList(lines));
    }
}
