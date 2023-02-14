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
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class WordListGenerator {
    private static final Logger logger = LoggerFactory.getLogger(WordListGenerator.class);

    protected PgEnvironment pgEnvironment;
    protected Map<String, PartOfSpeechTag[]> posLookupTable = new HashMap<>();

    private Set<String> blocklist = new TreeSet<>();
    private Map<PartOfSpeechTag, Set<String>> baseForms = new HashMap<>();
    private Map<PartOfSpeechTag, Set<String>> inflections = new HashMap<>();

    private Set<String> foundWords;

    private Set<String> noGrammar;

    private final List<Name> names;

    public WordListGenerator(PgEnvironment pgEnvironment, List<Name> names) {
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
        noGrammar = new TreeSet<>();
        foundWords = new TreeSet<>();

        File dir = new File(pgEnvironment.getTempExportLocation());
        dir.mkdirs();

        loadValidWords(language);

        Set<String> notFound = new TreeSet<>();
        for(String word: noGrammar) {
            if (!foundWords.contains(word) && !word.contains(" ") && !word.contains("'")) {
                notFound.add(word);
            }
        }
        logger.error("Missing words: " + String.join(", ", notFound));

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

    abstract protected String normalizeString(String input);

    abstract protected String removePronouns(String value);

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
            if (RStichwort == null || RStichwort.equals("") || blocklist.contains(RStichwort)) {
                continue;
            }
            if (RGrammatik == null) {
                String RGenus = current.getEntryValue("RGenus");
                if (RGenus != null && !RGenus.equals("")) {
                    RGrammatik = "nomen";
                } else if (DGrammatik != null && !"".equals(DGrammatik)) {
                    // using german grammar
                    RGrammatik = DGrammatik;
                } else {
                    // logger.error("No grammar defined for word: {}", RStichwort);
                    noGrammar.add(RStichwort);
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
                }

                String de = WordListUtils.getGermanNameForLanguage(language, name);
                if (de != null) {
                    baseForms.get(PartOfSpeechTag.NOUN).add(de);
                }
            });
        }
    }

    private void addForms(Set<String> baseForms, Set<String> inflections, LemmaVersion current, String RStichwort) {
        String inflectionType = current.getEntryValue(LemmaVersion.RM_INFLECTION_TYPE);
        if (inflectionType == null || inflectionType.equals("")) {
            addFormToSet(baseForms, RStichwort);
            addFormToSet(inflections, RStichwort);
        } else if (inflectionType.equals("V")) {
            extractVerbs(baseForms, inflections, current, RStichwort);
        } else if (inflectionType.equals("NOUN")) {
            extractNouns(baseForms, inflections, current, RStichwort);
        } else if (inflectionType.equals("ADJECTIVE")) {
            extractAdjectives(baseForms, inflections, current, RStichwort);
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

    protected void extractNouns(Set<String> baseForms, Set<String> inflections, LemmaVersion lemmaVersion, String RStichwort) {
        String baseForm = lemmaVersion.getEntryValue("baseForm");
        if (baseForm == null || baseForm.equals("")) {
            baseForm = RStichwort;
        }
        addFormToSet(baseForms, baseForm);
        addFormToSet(inflections, baseForm);
        
        addFormToSet(inflections, lemmaVersion.getEntryValue("mSingular"));
        addFormToSet(inflections, lemmaVersion.getEntryValue("fSingular"));
        addFormToSet(inflections, lemmaVersion.getEntryValue("mPlural"));
        addFormToSet(inflections, lemmaVersion.getEntryValue("fPlural"));
        addFormToSet(inflections, lemmaVersion.getEntryValue("pluralCollectiv"));
    }

    protected void extractAdjectives(Set<String> baseForms, Set<String> inflections, LemmaVersion lemmaVersion, String RStichwort) {
        String baseForm = lemmaVersion.getEntryValue("baseForm");
        if (baseForm == null || baseForm.equals("")) {
            baseForm = RStichwort;
        }
        addFormToSet(baseForms, baseForm);
        addFormToSet(inflections, baseForm);
        
        addFormToSet(inflections, lemmaVersion.getEntryValue("mSingular"));
        addFormToSet(inflections, lemmaVersion.getEntryValue("fSingular"));
        addFormToSet(inflections, lemmaVersion.getEntryValue("mPlural"));
        addFormToSet(inflections, lemmaVersion.getEntryValue("fPlural"));
        addFormToSet(this.baseForms.get(PartOfSpeechTag.ADV), lemmaVersion.getEntryValue("adverbialForm"));
    }

    protected void extractPronouns(Set<String> baseForms, Set<String> inflections, LemmaVersion lemmaVersion, String RStichwort) {
        String baseForm = lemmaVersion.getEntryValue("baseForm");
        if (baseForm == null || baseForm.equals("")) {
            baseForm = RStichwort;
        }
        addFormToSet(baseForms, baseForm);
        addFormToSet(inflections, baseForm);

        addFormToSet(inflections, lemmaVersion.getEntryValue("mSingular"));
        addFormToSet(inflections, lemmaVersion.getEntryValue("fSingular"));
        addFormToSet(inflections, lemmaVersion.getEntryValue("mPlural"));
        addFormToSet(inflections, lemmaVersion.getEntryValue("fPlural"));
    }

    protected void extractVerbs(Set<String> baseForms, Set<String> inflections, LemmaVersion lemmaVersion, String RStichwort) {
        String infinitiv = lemmaVersion.getEntryValue("infinitiv");
        if (infinitiv == null || infinitiv.equals("")) {
            infinitiv = RStichwort;
        }
        addFormToSet(baseForms, removePronouns(infinitiv));
        addFormToSet(inflections, removePronouns(infinitiv));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentsing1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentsing2")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentsing3")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentplural1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentplural2")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentplural3")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectsing1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectsing2")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectsing3")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectplural1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectplural2")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectplural3")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("conjunctivsing1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("conjunctivsing2")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("conjunctivsing3")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("conjunctivplural1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("conjunctivplural2")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("conjunctivplural3")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalsing1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalsing2")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalsing3")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalplural1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalplural2")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalplural3")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("participperfectms")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("participperfectfs")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("participperfectmp")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("participperfectfp")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperativ1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperativ2")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("gerundium")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futursing1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futursing2")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futursing3")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futurplural1")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futurplural2")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futurplural3")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentsing1enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentsing2enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentsing3encliticm")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentsing3encliticf")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentplural1enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentplural2enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("preschentplural3enclitic")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectsing1enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectsing2enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectsing3encliticm")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectsing3encliticf")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectplural1enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectplural2enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("imperfectplural3enclitic")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalsing1enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalsing2enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalsing3encliticm")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalsing3encliticf")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalplural1enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalplural2enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("cundizionalplural3enclitic")));

        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futursing1enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futursing2enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futursing3encliticm")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futursing3encliticf")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futurplural1enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futurplural2enclitic")));
        addFormToSet(inflections, removePronouns(lemmaVersion.getEntryValue("futurplural3enclitic")));
    }

    private void addFormToSet(Set<String> set, String word) {
        word = WordListUtils.normalizeStringSurmiran(word);
        word = WordListUtils.normalizeWord(word);
        if (word == null) {
            return;
        }

        // ignore composed words
        if (word.contains(" ")) {
            return;
        }

        // ignore words with an apostroph
        if (word.contains("'")) {
            return;
        }

        set.add(word);
    }
}
