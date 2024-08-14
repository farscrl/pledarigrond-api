package ch.pledarigrond.spellchecker.generator.pos;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.names.entities.Name;
import ch.pledarigrond.spellchecker.generator.WordListUtils;
import ch.pledarigrond.spellchecker.model.PartOfSpeechTag;
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
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class PartOfSpeechListGenerator {
    private static final Logger logger = LoggerFactory.getLogger(PartOfSpeechListGenerator.class);

    protected PgEnvironment pgEnvironment;
    protected Map<String, PartOfSpeechTag[]> posLookupTable = new HashMap<>();

    private Set<String> blocklist = new TreeSet<>();
    private final Map<PartOfSpeechTag, Set<String>> baseForms = new HashMap<>();
    private final Map<PartOfSpeechTag, Set<String>> inflections = new HashMap<>();

    private Set<String> foundWords;

    private Set<String> missingGrammar;

    private final List<Name> names;

    public PartOfSpeechListGenerator(PgEnvironment pgEnvironment, List<Name> names) {
        this.pgEnvironment = pgEnvironment;
        this.names = names;
    }

    public File exportWordlist(Language language) throws NoDatabaseAvailableException, IOException {
        blocklist = new TreeSet<>();
        loadBlockList(language);

        for (PartOfSpeechTag tag: PartOfSpeechTag.values()) {
            baseForms.put(tag, new TreeSet<>());
            inflections.put(tag, new TreeSet<>());
        }
        missingGrammar = new TreeSet<>();
        foundWords = new TreeSet<>();

        File dir = new File(pgEnvironment.getTempExportLocation());
        dir.mkdirs();

        loadValidWords(language);

        Set<String> notFound = new TreeSet<>();
        for(String word: missingGrammar) {
            // check that missing words have not been added later and are not composed words
            if (!foundWords.contains(word) && !word.contains(" ") && !word.contains("'")) {
                notFound.add(word);
            }
        }
        logger.error("Missing words: {}", String.join(", ", notFound));

        List<File> files = new ArrayList<>();
        for (Map.Entry<PartOfSpeechTag, Set<String>> entry : baseForms.entrySet()) {
            String fileName = entry.getKey().getName();
            File file = new File(dir, fileName + "_baseform.txt");
            writeListToFile(file, entry.getValue());
            files.add(file);
        }

        for (Map.Entry<PartOfSpeechTag, Set<String>> entry : inflections.entrySet()) {
            String fileName = entry.getKey().getName();
            File file = new File(dir, fileName + "_inflections.txt");
            writeListToFile(file, entry.getValue());
            files.add(file);
        }

        // create version file
        File versionFile = new File(dir, "_VERSION.txt");
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

        // load list of vulgar words
        ClassPathResource resource = new ClassPathResource(language.getName() + "/vulgar.txt");
        File vulgarWords = resource.getFile();

        // write Zip
        files.add(versionFile);
        files.add(licenceFile);
        files.add(vulgarWords);
        File zipFile = new File(dir, "wordlist_" + language + "_" + versionAndBuild + ".zip");
        WordListUtils.writeFilesToZip(zipFile, files);

        return zipFile;
    }

    abstract protected String normalizeString(String input);

    abstract protected String removePronouns(String value);

    abstract protected void extractNouns(Set<String> baseForms, Set<String> inflections, LemmaVersion lemmaVersion, String RStichwort);

    abstract protected void extractAdjectives(Set<String> adjectiveBaseForms, Set<String> adjectiveInflections, Set<String> adverbialBaseForms, Set<String> adverbialInflections, LemmaVersion lemmaVersion, String RStichwort);

    abstract protected void extractPronouns(Set<String> baseForms, Set<String> inflections, LemmaVersion lemmaVersion, String RStichwort);

    abstract protected void extractVerbs(Set<String> baseForms, Set<String> inflections, LemmaVersion lemmaVersion, String RStichwort);

    abstract protected void extractDefault(Set<String> baseForms, Set<String> inflections, LemmaVersion lemmaVersion, String RStichwort);

    private void loadValidWords(Language language) throws NoDatabaseAvailableException {
        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();

        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);
            LemmaVersion current = entry.getCurrent();

            String RStichwort = current.getEntryValue("RStichwort");
            String RRegularInflection = current.getEntryValue("RRegularInflection");
            String RGrammatik = current.getEntryValue("RGrammatik");
            String DGrammatik = current.getEntryValue("DGrammatik");
            String preschentsing1 = current.getEntryValue("preschentsing1");
            String preschentsing3 = current.getEntryValue("preschentsing3");

            if (current.getVerification() != LemmaVersion.Verification.ACCEPTED) {
                // ignore lemmas that are not accepted (== new suggestions)
                continue;
            }

            RStichwort = normalizeString(RStichwort);
            if (RStichwort == null || RStichwort.isEmpty() || blocklist.contains(RStichwort)) {
                continue;
            }
            if (RGrammatik == null) {
                String RGenus = current.getEntryValue("RGenus");
                if (RGenus != null && !RGenus.isEmpty()) {
                    RGrammatik = "nomen";
                } else if (DGrammatik != null && !DGrammatik.isEmpty()) {
                    // using german grammar
                    RGrammatik = DGrammatik;
                } else {
                    // logger.error("No grammar defined for word: {}", RStichwort);
                    missingGrammar.add(RStichwort);
                    continue;
                }
            }
            PartOfSpeechTag[] tags = posLookupTable.get(RGrammatik);

            if (tags == null) {
                logger.error("No POS entry defined for grammar: {}", RGrammatik);
                continue;
            }

            for(PartOfSpeechTag tag : tags) {
                if (
                    tag == PartOfSpeechTag.VERB &&
                    ( (StringUtils.hasLength(preschentsing1) && !StringUtils.hasLength(preschentsing3)) || (StringUtils.hasLength(preschentsing3) && !StringUtils.hasLength(preschentsing1)) )
                ) {
                    addForms(baseForms.get(PartOfSpeechTag.VERB_DEFECTIV), inflections.get(PartOfSpeechTag.VERB_DEFECTIV), current, RStichwort);
                } else if (tag == PartOfSpeechTag.VERB && RRegularInflection != null && !"true".equals(RRegularInflection)) {
                    addForms(baseForms.get(PartOfSpeechTag.VERB_IRREGULAR), inflections.get(PartOfSpeechTag.VERB_IRREGULAR), current, RStichwort);
                } else {
                    addForms(baseForms.get(tag), inflections.get(tag), current, RStichwort);
                }
                foundWords.add(RStichwort);
            }
        }

        if (names != null && baseForms.get(PartOfSpeechTag.NOUN) != null) {
            names.forEach(name -> {
                String rm = WordListUtils.getRomanshNameForLanguage(language, name);
                if (rm != null) {
                    baseForms.get(PartOfSpeechTag.NOUN).add(rm);
                    inflections.get(PartOfSpeechTag.NOUN).add(rm);
                }

                String de = WordListUtils.getGermanNameForLanguage(language, name);
                if (de != null) {
                    baseForms.get(PartOfSpeechTag.NOUN).add(de);
                    inflections.get(PartOfSpeechTag.NOUN).add(de);
                }
            });
        }
    }

    private void addForms(Set<String> baseForms, Set<String> inflections, LemmaVersion current, String RStichwort) {
        String inflectionType = current.getEntryValue(LemmaVersion.RM_INFLECTION_TYPE);
        if (inflectionType == null || inflectionType.isEmpty()) {
            extractDefault(baseForms, inflections, current, RStichwort);
        } else if (inflectionType.equals("V")) {
            extractVerbs(baseForms, inflections, current, RStichwort);
        } else if (inflectionType.equals("NOUN")) {
            extractNouns(baseForms, inflections, current, RStichwort);
        } else if (inflectionType.equals("ADJECTIVE")) {
            extractAdjectives(baseForms, inflections, this.baseForms.get(PartOfSpeechTag.ADV), this.inflections.get(PartOfSpeechTag.ADV), current, RStichwort);
        } else if (inflectionType.equals("PRONOUN")) {
            extractPronouns(baseForms, inflections, current, RStichwort);
        } else {
            throw new RuntimeException("Unexpected inflection type: " + inflectionType);
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

    private void writeListToFile(File wordListFile, Set<String> list) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(wordListFile))) {
            for (String value : list) {
                if (value == null) continue;
                bf.write(value);
                bf.newLine();
            }

            bf.flush();
        } catch (IOException e) {
            logger.error("Error while writing word list to file", e);
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

    protected void addFormToSet(Set<String> set, String word) {
        word = normalizeString(word);
        word = WordListUtils.normalizeWordListEntry(word);
        if (word == null) {
            return;
        }

        // ignore composed words
        if (word.contains(" ")) {
            return;
        }

        // split words containing a slash into two words
        // TODO: if data is cleaned up, this condition can be removed
        if (word.contains("/")) {
            String[] words = word.split("/");

            addFormToSet(set, words[0]);
            addFormToSet(set, words[1]);
            return;
        }

        // ignore words with an apostroph
        if (word.contains("'")) {
            return;
        }

        set.add(word);
    }
}
