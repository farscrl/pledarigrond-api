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
import ch.pledarigrond.spellchecker.model.SpellcheckerRules;
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

import static ch.pledarigrond.spellchecker.model.SpellcheckerRules.*;

public class SpellcheckerGenerator {

    String version;

    PgEnvironment pgEnvironment;

    private List<Name> names;

    public SpellcheckerGenerator(PgEnvironment pgEnvironment, List<Name> names) {
        this.pgEnvironment = pgEnvironment;
        this.names = names;
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
            versionTemplateData.put("buildid", buildDateFormat.format(now));
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
            String inflectionType = current.getEntryValue(LemmaVersion.RM_INFLECTION_TYPE);
            if (inflectionType == null || inflectionType.equals("")) {
                extractDefault(hunspellList, current);
            } else if (inflectionType.equals("V")) {
                extractVerbs(language, hunspellList, current);
            } else if (inflectionType.equals("NOUN")) {
                extractNouns(hunspellList, current);
            } else if (inflectionType.equals("ADJECTIVE")) {
                extractAdjectives(hunspellList, current);
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

    protected void extractNouns(HunspellList list, LemmaVersion lemmaVersion) {
        list.addWord(lemmaVersion.getEntryValue("baseForm"), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("mSingular"), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("fSingular"), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("mPlural"), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("fPlural"), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("pluralCollectiv"), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI});
    }

    protected void extractAdjectives(HunspellList list, LemmaVersion lemmaVersion) {
        list.addWord(lemmaVersion.getEntryValue("baseForm"), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("mSingular"), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("fSingular"), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("mPlural"), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("fPlural"), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI});
        list.addWord(lemmaVersion.getEntryValue("adverbialForm"), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI});
    }

    protected void extractVerbs(Language language, HunspellList list, LemmaVersion lemmaVersion) {
        String infinitiv = lemmaVersion.getEntryValue("infinitiv");
        if (infinitiv == null || infinitiv.equals("")) {
            infinitiv = lemmaVersion.getEntryValue("RStichwort");
        }
        list.addWord(removePronouns(language, infinitiv), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI});

        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("preschentsing1")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("preschentsing2")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("preschentsing3")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("preschentplural1")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("preschentplural2")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("preschentplural3")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("imperfectsing1")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("imperfectsing2")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("imperfectsing3")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("imperfectplural1")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("imperfectplural2")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("imperfectplural3")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("conjunctivsing1")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("conjunctivsing2")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("conjunctivsing3")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("conjunctivplural1")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("conjunctivplural2")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("conjunctivplural3")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("cundizionalsing1")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("cundizionalsing2")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("cundizionalsing3")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("cundizionalplural1")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("cundizionalplural2")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("cundizionalplural3")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("participperfectms")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("participperfectfs")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("participperfectmp")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("participperfectfp")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("imperativ1")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOM_CONGIUNT_IMPERATIV, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("imperativ2")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOM_CONGIUNT_IMPERATIV, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("gerundium")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("futursing1")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("futursing2")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("futursing3")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("futurplural1")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("futurplural2")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("futurplural3")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("preschentsing1enclitic")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("preschentsing2enclitic")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("preschentsing3encliticm")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("preschentsing3encliticf")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("preschentplural1enclitic")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("preschentplural2enclitic")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("preschentplural3enclitic")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("imperfectsing1enclitic")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("imperfectsing2enclitic")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("imperfectsing3encliticm")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("imperfectsing3encliticf")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("imperfectplural1enclitic")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("imperfectplural2enclitic")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("imperfectplural3enclitic")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("cundizionalsing1enclitic")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("cundizionalsing2enclitic")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("cundizionalsing3encliticm")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("cundizionalsing3encliticf")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("cundizionalplural1enclitic")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("cundizionalplural2enclitic")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("cundizionalplural3enclitic")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});

        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("futursing1enclitic")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("futursing2enclitic")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("futursing3encliticm")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("futursing3encliticf")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("futurplural1enclitic")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("futurplural2enclitic")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
        list.addWord(removePronouns(language, lemmaVersion.getEntryValue("futurplural3enclitic")), new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI, SURMIRAN_PRONOMS_REFLEXIVS});
    }

    protected void extractDefault(HunspellList list, LemmaVersion lemmaVersion) {
        String candidate = lemmaVersion.getEntryValue("RStichwort");

        if (candidate == null) {
            return;
        }

        // Ignore cf.
        if (candidate.startsWith("cf. ")) {
            return;
        }

        if (candidate.endsWith(" da")) {
            candidate = candidate.substring(0, candidate.length() - 3);
        }
        if (candidate.endsWith(" a")) {
            candidate = candidate.substring(0, candidate.length() - 2);
        }
        if (candidate.endsWith("!")) {
            candidate = candidate.substring(0, candidate.length() - 1);
        }
        if (candidate.startsWith("far ")) {
            candidate = candidate.substring(4);
        }
        if (candidate.startsWith("l'")) {
            candidate = candidate.substring(2);
        }
        if (candidate.startsWith("la ")) {
            candidate = candidate.substring(3);
        }
        if (candidate.startsWith("en ")) {
            candidate = candidate.substring(3);
        }
        if (candidate.startsWith("en'")) {
            candidate = candidate.substring(3);
        }
        if (candidate.startsWith("ena ")) {
            candidate = candidate.substring(4);
        }
        if (candidate.startsWith("egn ")) {
            candidate = candidate.substring(4);
        }
        if (candidate.startsWith("egna ")) {
            candidate = candidate.substring(5);
        }

        list.addWord(candidate, new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI});
    }

    protected void extractName(HunspellList list, Language language, Name name) {
        String languageLemma = getLanguageLemma(language, name);
        if (languageLemma != null && !languageLemma.equals("")) {
            list.addWord(languageLemma, new SpellcheckerRules[]{});
        } else {
            list.addWord(name.getNameRumantschGrischun(), new SpellcheckerRules[]{});
        }
        if (name.getNameGerman() != null && !name.getNameGerman().equals("")) {
            list.addWord(name.getNameGerman(), new SpellcheckerRules[]{});
        }
    }

    private String getLanguageLemma(Language language, Name name) {
        return switch (language) {
            case SURSILVAN -> name.getNameSursilvan();
            case SUTSILVAN -> name.getNameSutsilvan();
            case SURMIRAN -> name.getNameSurmiran();
            case PUTER -> name.getNamePuter();
            case VALLADER -> name.getNameVallader();
            case RUMANTSCHGRISCHUN -> name.getNameRumantschGrischun();
        };
    }

    protected String removePronouns(Language language, String value) {
        return value;
    }

    private void loadWordsToAdd(Language language, HunspellList list) throws IOException {
        ClassPathResource resource = new ClassPathResource(language.getName() + "/missing_words.txt");
        InputStream is = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        while(reader.ready()) {
            String line = reader.readLine();

            if (!line.startsWith("#")) {
                list.addWord(line, new SpellcheckerRules[]{SURMIRAN_PLEDS_APOSTROFAI});
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
}
