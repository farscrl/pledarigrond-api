package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.SpellcheckerService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.inflection.generation.surmiran.SurmiranPronouns;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class SpellcheckerServiceImpl implements SpellcheckerService {

    Logger logger = LoggerFactory.getLogger(SpellcheckerServiceImpl.class);

    @Autowired
    private PgEnvironment pgEnvironment;

    public File exportWordList(Language language) throws NoDatabaseAvailableException, IOException {
        File dir = new File(pgEnvironment.getTempExportLocation());
        dir.mkdirs();

        // create wordlist
        Set<String> words = getAllValidWords(language);
        File wordlist = new File(dir, "wordlist_" + language + ".txt");
        writeSetTo(wordlist, words, false);

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
        writeSetTo(dicFile, words, true);

        // load aff file
        ClassPathResource resource = new ClassPathResource("spellchecker/" + language.getName() + "/rm-" + language.getName() + ".aff");
        File aff = resource.getFile();

        // write Zip
        List<File> files = new ArrayList<>();
        files.add(aff);
        files.add(dicFile);
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

    private void writeSetTo(File wordListFile, Set<String> set, boolean addNumberOfEntries) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(wordListFile))) {
            if (addNumberOfEntries) {
                bf.write(String.valueOf(set.size() + 1));
                bf.newLine();
            }

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
        Set<String> words = new TreeSet<>((a, b) -> {
            int insensitive = String.CASE_INSENSITIVE_ORDER.compare(a, b);
            return insensitive==0 ? a.compareTo(b) : insensitive;
        });
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

    private void extractNouns(Set<String> words, LemmaVersion lemmaVersion) {
        addIfNotNull(words, lemmaVersion.getEntryValue("baseForm"));
        addIfNotNull(words, lemmaVersion.getEntryValue("mSingular"));
        addIfNotNull(words, lemmaVersion.getEntryValue("fSingular"));
        addIfNotNull(words, lemmaVersion.getEntryValue("mPlural"));
        addIfNotNull(words, lemmaVersion.getEntryValue("fPlural"));
        addIfNotNull(words, lemmaVersion.getEntryValue("pluralCollectiv"));
    }

    private void extractAdjectives(Set<String> words, LemmaVersion lemmaVersion) {
        addIfNotNull(words, lemmaVersion.getEntryValue("baseForm"));
        addIfNotNull(words, lemmaVersion.getEntryValue("mSingular"));
        addIfNotNull(words, lemmaVersion.getEntryValue("fSingular"));
        addIfNotNull(words, lemmaVersion.getEntryValue("mPlural"));
        addIfNotNull(words, lemmaVersion.getEntryValue("fPlural"));
        addIfNotNull(words, lemmaVersion.getEntryValue("adverbialForm"));
    }

    private void extractVerbs(Language language, Set<String> words, LemmaVersion lemmaVersion) {
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

    private void extractDefault(Set<String> words, LemmaVersion lemmaVersion) {
        addIfNotNull(words, lemmaVersion.getEntryValue("RStichwort"));
    }
    
    private String removePronouns(Language language, String value) {
        if (value == null) {
            return null;
        }

        List<String> prefixes = new ArrayList<>();
        if (language == Language.SURMIRAN) {
            Collections.addAll(prefixes,
                    // has to be upfront, as other pronouns may follow
                    SurmiranPronouns.pron_conjunctiv_c,
                    SurmiranPronouns.pron_conjunctiv_v,

                    SurmiranPronouns.pron_1ps,
                    SurmiranPronouns.pron_2ps,
                    SurmiranPronouns.pron_3ps,
                    SurmiranPronouns.pron_1pp,
                    SurmiranPronouns.pron_2pp,
                    SurmiranPronouns.pron_3pp,
                    SurmiranPronouns.pron_r_1ps,
                    SurmiranPronouns.pron_r_2ps,
                    SurmiranPronouns.pron_r_3ps,
                    SurmiranPronouns.pron_r_1pp,
                    SurmiranPronouns.pron_r_2pp,
                    SurmiranPronouns.pron_r_3pp,
                    SurmiranPronouns.pron_r_v_1ps,
                    SurmiranPronouns.pron_r_v_2ps,
                    SurmiranPronouns.pron_r_v_3ps,
                    SurmiranPronouns.pron_r_v_1pp,
                    SurmiranPronouns.pron_r_v_2pp,
                    SurmiranPronouns.pron_r_v_3pp
            );
        }

        for(String p: prefixes) {
            if (value.startsWith(p)) {
                return value.substring(p.length());
            }
        }
        return value;
    }

    private void addIfNotNull(Set<String> words, String value) {
        if (value != null) {
            words.add(value);
        }
    }

    private void loadWordsToAdd(Language language, Set<String> words) throws IOException {
        ClassPathResource resource = new ClassPathResource("spellchecker/" + language.getName() + "/missing_words.txt");
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
        ClassPathResource resource = new ClassPathResource("spellchecker/" + language.getName() + "/words_to_ignore.txt");
        InputStream is = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        while(reader.ready()) {
            String line = reader.readLine();

            if (!line.startsWith("#")) {
                words.remove(line);
            }
        }
    }
}
