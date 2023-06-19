package ch.pledarigrond.spellchecker.generator.hunspell;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.names.entities.Name;
import ch.pledarigrond.spellchecker.model.HunspellList;
import ch.pledarigrond.spellchecker.generator.WordListUtils;
import ch.pledarigrond.spellchecker.model.HunspellRules;
import ch.pledarigrond.spellchecker.utils.freemarker.FreemarkerConfigSpellchecker;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.bson.Document;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static ch.pledarigrond.spellchecker.model.HunspellRules.*;

abstract public class HunspellGenerator {

    PgEnvironment pgEnvironment;

    private final List<Name> names;

    public HunspellGenerator(PgEnvironment pgEnvironment, List<Name> names) {
        this.pgEnvironment = pgEnvironment;
        this.names = names;
    }

    public File exportHunspell(Language language) throws NoDatabaseAvailableException, IOException {
        File dir = new File(pgEnvironment.getTempExportLocation());
        dir.mkdirs();

        // create dicFile
        Set<String> words = getAllValidWords(language);
        File dicFile = new File(dir, "rm-" + language.getSubtag() + ".dic");
        writeSetToHunspell(dicFile, words);

        // load aff file
        ClassPathResource resource = new ClassPathResource(language.getName() + "/rm-" + language.getSubtag() + ".aff");
        File aff = resource.getFile();

        // create version file
        File versionFile = new File(dir, "rm-" + language.getSubtag()+ "_version.txt");
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
        File licenceFile = new File(dir, "rm-" + language.getSubtag()+ "_LICENSE.txt");
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

        // TODO: get rid of that external list
        if (language == Language.SURMIRAN) {
            loadWordsToAdd(language, hunspellList);
        }

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
                extractVerbs(language, hunspellList, current);
            } else if (inflectionType.equals("NOUN")) {
                extractNouns(hunspellList, current);
            } else if (inflectionType.equals("ADJECTIVE")) {
                extractAdjectives(hunspellList, current);
            } else if (inflectionType.equals("PRONOUN")) {
                extractPronouns(hunspellList, current);
            } else {
                throw new RuntimeException("Unexpected inflection type: " + inflectionType);
            }
        }

        if (names != null) {
            names.forEach(name -> {
                extractName(hunspellList, language, name);
            });
        }

        removeWordsFromBlocklist(language, hunspellList);

        return hunspellList.getListAsSet();
    }

    abstract protected void extractNouns(HunspellList list, LemmaVersion lemmaVersion);

    abstract protected void extractAdjectives(HunspellList list, LemmaVersion lemmaVersion);

    abstract protected void extractPronouns(HunspellList list, LemmaVersion lemmaVersion);

    abstract protected void extractVerbs(Language language, HunspellList list, LemmaVersion lemmaVersion);

    abstract protected void extractDefault(HunspellList list, LemmaVersion lemmaVersion);

    abstract protected String removePronouns(String value);

    abstract protected String normalizeString(String input);

    private void extractName(HunspellList list, Language language, Name name) {
        String rm = WordListUtils.getRomanshNameForLanguage(language, name);
        if (rm != null) {
            list.addWord(rm, new HunspellRules[]{}, false);
        }

        String de = WordListUtils.getGermanNameForLanguage(language, name);
        if (de != null) {
            list.addWord(de, new HunspellRules[]{}, false);
        }
    }

    private void loadWordsToAdd(Language language, HunspellList list) throws IOException {
        ClassPathResource resource = new ClassPathResource(language.getName() + "/missing_words.txt");
        InputStream is = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        while(reader.ready()) {
            String line = reader.readLine();

            if (!line.startsWith("#")) {
                list.addWord(line, new HunspellRules[]{SURMIRAN_PLEDS_APOSTROFAI});
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
