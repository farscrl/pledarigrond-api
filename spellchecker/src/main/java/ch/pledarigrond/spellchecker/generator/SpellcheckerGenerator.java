package ch.pledarigrond.spellchecker.generator;

import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
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
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SpellcheckerGenerator {

    String version;

    PgEnvironment pgEnvironment;

    public SpellcheckerGenerator(PgEnvironment pgEnvironment) {
        this.pgEnvironment = pgEnvironment;
    }

    public File exportWordList(Language language) throws NoDatabaseAvailableException, IOException {
        File dir = new File(pgEnvironment.getTempExportLocation());
        dir.mkdirs();

        // create wordlist
        Set<String> words = getAllValidWords(language);
        File wordlist = new File(dir, "wordlist_" + language + ".txt");
        writeSetTo(wordlist, words);

        // write Zip
        List<File> files = new ArrayList<>();
        files.add(wordlist);
        File zipFile = new File(dir, "wordlist_" + language + "_" + UUID.randomUUID() + ".zip");
        //zipFile.createNewFile();
        writeFilesToZip(zipFile, files);

        return zipFile;
    }

    public File exportHunspell(Language language) throws NoDatabaseAvailableException, IOException {
        File dir = new File(pgEnvironment.getTempExportLocation());
        dir.mkdirs();

        // create dicFile
        Set<String> words = getAllValidWords(language);
        File dicFile = new File(dir, "rm-" + language.getName() + ".dic");
        writeSetToHunspell(dicFile, words);

        // load aff file
        ClassPathResource resource = new ClassPathResource(language.getName() + "/rm-" + language.getName() + ".aff");
        File aff = resource.getFile();

        // create version file
        File versionFile = new File(dir, "version.txt");
        try {
            SimpleDateFormat buildDateFormat = new SimpleDateFormat("yyMMdd.HHmmss");
            Date now = new Date();

            Map<String, Object> versionTemplateData = new HashMap<>();
            versionTemplateData.put("version",getVersion());
            versionTemplateData.put("build", buildDateFormat.format(now));
            Writer versionFileWriter = new FileWriter(versionFile);
            Template versionTemplate = FreemarkerConfigSpellchecker.getConfig().getTemplate("version.ftlh");
            versionTemplate.process(versionTemplateData, versionFileWriter);
            versionFileWriter.flush();
            versionFileWriter.close();
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }

        // create licence file
        File licenceFile = new File(dir, "LICENSE.txt");
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
        File zipFile = new File(dir, "hunspell_" + language + "_" + UUID.randomUUID() + ".zip");
        //zipFile.createNewFile();
        writeFilesToZip(zipFile, files);

        return zipFile;
    }


    private void writeFilesToZip(File zipFile, List<File> filesToZip) {
        ZipOutputStream zos = null;
        try {

            zos = new ZipOutputStream(new FileOutputStream(zipFile));

            for(File f: filesToZip) {
                String name = f.getName();
                ZipEntry entry = new ZipEntry(name);
                zos.putNextEntry(entry);

                FileInputStream fis = null;
                fis = new FileInputStream(f);
                byte[] byteBuffer = new byte[1024];
                int bytesRead = -1;
                while ((bytesRead = fis.read(byteBuffer)) != -1) {
                    zos.write(byteBuffer, 0, bytesRead);
                }
                fis.close();
                zos.closeEntry();
                zos.flush();
            }


            zos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeSetTo(File wordListFile, Set<String> set) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(wordListFile))) {
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

    private void writeSetToHunspell(File wordListFile, Set<String> set) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(wordListFile))) {
            bf.write(String.valueOf(set.size() + 1));
            bf.newLine();

            for (String value : set) {
                if (value == null) continue;
                bf.write(value);
                bf.write("/T"); // rule for apostrophe words
                bf.newLine();
            }

            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Set<String> getAllValidWords(Language language) throws NoDatabaseAvailableException, IOException {
        Set<String> words = new TreeSet<>();
        loadWordsToAdd(language, words);

        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();

        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            LemmaVersion current = entry.getCurrent();
            String inflectionType = current.getEntryValue(LemmaVersion.RM_INFLECTION_TYPE);
            if (inflectionType == null) {
                extractDefault(words, current);
            } else if (inflectionType.equals("V")) {
                extractVerbs(language, words, current);
            } else if (inflectionType.equals("NOUN")) {
                extractNouns(words, current);
            } else if (inflectionType.equals("ADJECTIVE")) {
                extractAdjectives(words, current);
            } else {
                throw new RuntimeException("Unexpected inflection type: " + inflectionType);
            }
        }

        removeWordsFromBlocklist(language, words);

        return words;
    }

    protected void extractNouns(Set<String> words, LemmaVersion lemmaVersion) {
        addIfNotNull(words, lemmaVersion.getEntryValue("baseForm"));
        addIfNotNull(words, lemmaVersion.getEntryValue("mSingular"));
        addIfNotNull(words, lemmaVersion.getEntryValue("fSingular"));
        addIfNotNull(words, lemmaVersion.getEntryValue("mPlural"));
        addIfNotNull(words, lemmaVersion.getEntryValue("fPlural"));
        addIfNotNull(words, lemmaVersion.getEntryValue("pluralCollectiv"));
    }

    protected void extractAdjectives(Set<String> words, LemmaVersion lemmaVersion) {
        addIfNotNull(words, lemmaVersion.getEntryValue("baseForm"));
        addIfNotNull(words, lemmaVersion.getEntryValue("mSingular"));
        addIfNotNull(words, lemmaVersion.getEntryValue("fSingular"));
        addIfNotNull(words, lemmaVersion.getEntryValue("mPlural"));
        addIfNotNull(words, lemmaVersion.getEntryValue("fPlural"));
        addIfNotNull(words, lemmaVersion.getEntryValue("adverbialForm"));
    }

    protected void extractVerbs(Language language, Set<String> words, LemmaVersion lemmaVersion) {
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("infinitiv")));

        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("preschentsing1")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("preschentsing2")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("preschentsing3")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("preschentplural1")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("preschentplural2")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("preschentplural3")));

        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("imperfectsing1")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("imperfectsing2")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("imperfectsing3")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("imperfectplural1")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("imperfectplural2")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("imperfectplural3")));

        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("conjunctivsing1")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("conjunctivsing2")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("conjunctivsing3")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("conjunctivplural1")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("conjunctivplural2")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("conjunctivplural3")));

        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("cundizionalsing1")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("cundizionalsing2")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("cundizionalsing3")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("cundizionalplural1")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("cundizionalplural2")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("cundizionalplural3")));

        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("participperfectms")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("participperfectfs")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("participperfectmp")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("participperfectfp")));

        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("imperativ1")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("imperativ2")));

        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("gerundium")));

        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("futursing1")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("futursing2")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("futursing3")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("futurplural1")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("futurplural2")));
        addIfNotNull(words, removePronouns(language, lemmaVersion.getEntryValue("futurplural3")));
    }

    protected void extractDefault(Set<String> words, LemmaVersion lemmaVersion) {
        addIfNotNull(words, lemmaVersion.getEntryValue("RStichwort"));
    }

    protected String removePronouns(Language language, String value) {
        return value;
    }

    private void addIfNotNull(Set<String> words, String value) {
        if (value != null) {
            words.add(value);
        }
    }

    private void loadWordsToAdd(Language language, Set<String> words) throws IOException {
        ClassPathResource resource = new ClassPathResource(language.getName() + "/missing_words.txt");
        InputStream is = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        while(reader.ready()) {
            String line = reader.readLine();

            if (!line.startsWith("#")) {
                words.add(line);
            }
        }
    }

    private void removeWordsFromBlocklist(Language language, Set<String> words) throws IOException {
        ClassPathResource resource = new ClassPathResource(language.getName() + "/words_to_ignore.txt");
        InputStream is = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        while(reader.ready()) {
            String line = reader.readLine();

            if (!line.startsWith("#")) {
                words.remove(line);
            }
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
