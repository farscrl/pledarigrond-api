package ch.pledarigrond.spellchecker.generator.pos;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.dictionary.EntryDto;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.spellchecker.generator.WordListUtils;
import ch.pledarigrond.spellchecker.model.PartOfSpeechTag;
import ch.pledarigrond.spellchecker.utils.freemarker.FreemarkerConfigSpellchecker;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public abstract class PartOfSpeechListGenerator {
    private static final Logger logger = LoggerFactory.getLogger(PartOfSpeechListGenerator.class);

    protected PgEnvironment pgEnvironment;
    protected Map<String, PartOfSpeechTag[]> posLookupTable = new HashMap<>();

    private Set<String> blocklist = new TreeSet<>();
    private final Map<PartOfSpeechTag, Set<String>> baseForms = new HashMap<>();
    private final Map<PartOfSpeechTag, Set<String>> inflections = new HashMap<>();

    private Set<String> foundWords;

    private Set<String> missingGrammar;

    private final List<String> names;

    public PartOfSpeechListGenerator(PgEnvironment pgEnvironment, List<String> names) {
        this.pgEnvironment = pgEnvironment;
        this.names = names;
    }

    public File exportWordlist(Language language, Stream<EntryDto> stream) throws IOException {
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

        loadValidWords(language, stream);

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

    abstract protected void extractNouns(Set<String> baseForms, Set<String> inflections, EntryVersionDto dto, String RStichwort);

    abstract protected void extractAdjectives(Set<String> adjectiveBaseForms, Set<String> adjectiveInflections, Set<String> adverbialBaseForms, Set<String> adverbialInflections, EntryVersionDto dto, String RStichwort);

    abstract protected void extractPronouns(Set<String> baseForms, Set<String> inflections, EntryVersionDto dto, String RStichwort);

    abstract protected void extractVerbs(Set<String> baseForms, Set<String> inflections, EntryVersionDto dto, String RStichwort);

    abstract protected void extractDefault(Set<String> baseForms, Set<String> inflections, EntryVersionDto dto, String RStichwort);

    private void loadValidWords(Language language, Stream<EntryDto> stream) {
        stream.forEach(entry -> {
            EntryVersionDto current = entry.getCurrent();

            if (current == null) {
                // ignore lemmas that are not accepted (== new suggestions)
                return;
            }

            String rmStichwort = current.getRmStichwort();
            String rmGrammatik = current.getRmGrammatik();
            String deGrammatik = current.getDeGrammatik();
            boolean isIrregularConjugation = false;
            String preschentsing1 = "";
            String preschentsing3 = "";
            if (current.getInflection() != null && current.getInflection().getVerb() != null) {
                isIrregularConjugation = current.getInflection().getVerb().isIrregular();
                preschentsing1 = current.getInflection().getVerb().getPreschent().getSing1();
                preschentsing3 = current.getInflection().getVerb().getPreschent().getSing3();
            }


            rmStichwort = normalizeString(rmStichwort);
            if (rmStichwort == null || rmStichwort.isEmpty() || blocklist.contains(rmStichwort)) {
                return;
            }
            if (rmGrammatik == null) {
                String rmGenus = current.getRmGenus();
                if (rmGenus != null && !rmGenus.isEmpty()) {
                    rmGrammatik = "nomen";
                } else if (deGrammatik != null && !deGrammatik.isEmpty()) {
                    // using german grammar
                    rmGrammatik = deGrammatik;
                } else {
                    // logger.error("No grammar defined for word: {}", RStichwort);
                    missingGrammar.add(rmStichwort);
                    return;
                }
            }
            PartOfSpeechTag[] tags = posLookupTable.get(rmGrammatik);

            if (tags == null) {
                logger.error("No POS entry defined for grammar: {}", rmGrammatik);
                return;
            }

            for(PartOfSpeechTag tag : tags) {
                if (
                    tag == PartOfSpeechTag.VERB &&
                    ( (StringUtils.hasLength(preschentsing1) && !StringUtils.hasLength(preschentsing3)) || (StringUtils.hasLength(preschentsing3) && !StringUtils.hasLength(preschentsing1)) )
                ) {
                    addForms(baseForms.get(PartOfSpeechTag.VERB_DEFECTIV), inflections.get(PartOfSpeechTag.VERB_DEFECTIV), current, rmStichwort);
                } else if (tag == PartOfSpeechTag.VERB && isIrregularConjugation) {
                    addForms(baseForms.get(PartOfSpeechTag.VERB_IRREGULAR), inflections.get(PartOfSpeechTag.VERB_IRREGULAR), current, rmStichwort);
                } else {
                    addForms(baseForms.get(tag), inflections.get(tag), current, rmStichwort);
                }
                foundWords.add(rmStichwort);
            }
        });
    }

    private void addForms(Set<String> baseForms, Set<String> inflections, EntryVersionDto current, String RStichwort) {
        if (current.getInflection() == null || current.getInflection().getInflectionType() == null) {
            extractDefault(baseForms, inflections, current, RStichwort);
        } else {
            InflectionType inflectionType = current.getInflection().getInflectionType();
            if (inflectionType.equals(InflectionType.VERB)) {
                extractVerbs(baseForms, inflections, current, RStichwort);
            } else if (inflectionType.equals(InflectionType.NOUN)) {
                extractNouns(baseForms, inflections, current, RStichwort);
            } else if (inflectionType.equals(InflectionType.ADJECTIVE)) {
                extractAdjectives(baseForms, inflections, this.baseForms.get(PartOfSpeechTag.ADV), this.inflections.get(PartOfSpeechTag.ADV), current, RStichwort);
            } else if (inflectionType.equals(InflectionType.PRONOUN)) {
                extractPronouns(baseForms, inflections, current, RStichwort);
            } else {
                throw new RuntimeException("Unexpected inflection type: " + inflectionType);
            }
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
