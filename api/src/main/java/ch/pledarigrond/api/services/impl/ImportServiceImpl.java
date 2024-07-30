package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.dtos.LadinDto;
import ch.pledarigrond.api.dtos.VerbDto;
import ch.pledarigrond.api.services.ImportService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.EditorRole;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.inflection.generation.puter.PuterConjugation;
import ch.pledarigrond.inflection.generation.puter.PuterConjugationClasses;
import ch.pledarigrond.inflection.generation.puter.PuterConjugationPronouns;
import ch.pledarigrond.inflection.generation.puter.PuterConjugationStructure;
import ch.pledarigrond.inflection.generation.vallader.ValladerConjugation;
import ch.pledarigrond.inflection.generation.vallader.ValladerConjugationClasses;
import ch.pledarigrond.inflection.generation.vallader.ValladerConjugationPronouns;
import ch.pledarigrond.inflection.generation.vallader.ValladerConjugationStructure;
import ch.pledarigrond.inflection.model.InflectionResponse;
import ch.pledarigrond.inflection.utils.ConjugationStructureGenerator;
import ch.pledarigrond.inflection.utils.PronounRemover;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.mongodb.exceptions.InvalidEntryException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static ch.pledarigrond.common.data.common.LemmaVersion.RM_INFLECTION_SUBTYPE;
import static org.apache.poi.ss.usermodel.CellType.STRING;
import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;

@Service
public class ImportServiceImpl implements ImportService {

    private final Logger logger = LoggerFactory.getLogger(ImportService.class);

    @Autowired
    private PgEnvironment pgEnvironment;

    private String date = "";

    @Override
    public boolean importXlsSursilvan(Language language, HttpServletRequest request) throws IOException, InvalidEntryException, NoDatabaseAvailableException {
        Database db = Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language));

        StandardMultipartHttpServletRequest dmhsRequest = (StandardMultipartHttpServletRequest) request;
        MultipartFile multipartFile = dmhsRequest.getFile("file");

        assert multipartFile != null;
        Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        // check if format is correct
        int i = 0;
        Row row = sheet.getRow(i);
        if (!row.getCell(0).getRichStringCellValue().getString().equals("RStichwort")) throw new RuntimeException("Invalid format");
        if (!row.getCell(1).getRichStringCellValue().getString().equals("RGenus")) throw new RuntimeException("Invalid format");
        if (!row.getCell(2).getRichStringCellValue().getString().equals("RGrammatik")) throw new RuntimeException("Invalid format");
        if (!row.getCell(3).getRichStringCellValue().getString().equals("RPhonetics")) throw new RuntimeException("Invalid format");
        if (!row.getCell(4).getRichStringCellValue().getString().equals("RSemantik")) throw new RuntimeException("Invalid format");
        if (!row.getCell(5).getRichStringCellValue().getString().equals("REtymologie")) throw new RuntimeException("Invalid format");
        if (!row.getCell(6).getRichStringCellValue().getString().equals("RSynonym")) throw new RuntimeException("Invalid format");
        if (!row.getCell(7).getRichStringCellValue().getString().equals("DStichwort")) throw new RuntimeException("Invalid format");
        if (!row.getCell(8).getRichStringCellValue().getString().equals("DGenus")) throw new RuntimeException("Invalid format");
        if (!row.getCell(9).getRichStringCellValue().getString().equals("DGrammatik")) throw new RuntimeException("Invalid format");
        if (!row.getCell(10).getRichStringCellValue().getString().equals("DSemantik")) throw new RuntimeException("Invalid format");
        if (!row.getCell(11).getRichStringCellValue().getString().equals("categories")) throw new RuntimeException("Invalid format");

        logger.info("File format seems correct, continuing with import");

        LemmaVersion lv = null;

        for (i = 1; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }

            if (isYellowCell(row.getCell(0))) {
                if (row.getCell(0) != null && row.getCell(7) != null) {
                    String exampleRm = row.getCell(0).getRichStringCellValue().getString();
                    String exampleDe = row.getCell(7).getRichStringCellValue().getString();
                    if (lv == null) {
                        throw new RuntimeException("No lemma found for example: " + exampleRm);
                    }

                    String examples = lv.getLemmaValues().get("examples");
                    if (examples == null) {
                        examples = "";
                    } else {
                        examples += "\n";
                    }
                    examples += exampleRm + "###" + exampleDe;
                    lv.getLemmaValues().put("examples", examples);

                    logger.info("Added example: «" + exampleRm + "» TO «" + lv.getLemmaValues().get("RStichwort") + "»");
                }
            } else {
                if (lv != null) {
                    finalizeLemma(db, lv);
                }
                lv = new LemmaVersion();
                if (row.getCell(0) != null) {
                    lv.getLemmaValues().put("RStichwort", row.getCell(0).getRichStringCellValue().getString());
                }
                if (row.getCell(1) != null) {
                    lv.getLemmaValues().put("RGenus", row.getCell(1).getRichStringCellValue().getString());
                }
                if (row.getCell(2) != null) {
                    lv.getLemmaValues().put("RGrammatik", row.getCell(2).getRichStringCellValue().getString());
                }
                if (row.getCell(3) != null) {
                    lv.getLemmaValues().put("RPhonetics", row.getCell(3).getRichStringCellValue().getString());
                }
                if (row.getCell(4) != null) {
                    lv.getLemmaValues().put("RSemantik", row.getCell(4).getRichStringCellValue().getString());
                }
                if (row.getCell(5) != null) {
                    lv.getLemmaValues().put("REtymologie", row.getCell(5).getRichStringCellValue().getString());
                }
                if (row.getCell(6) != null) {
                    lv.getLemmaValues().put("RSynonym", row.getCell(6).getRichStringCellValue().getString());
                }
                if (row.getCell(7) != null) {
                    lv.getLemmaValues().put("DStichwort", row.getCell(7).getRichStringCellValue().getString());
                }
                if (row.getCell(8) != null) {
                    lv.getLemmaValues().put("DGenus", row.getCell(8).getRichStringCellValue().getString());
                }
                if (row.getCell(9) != null) {
                    lv.getLemmaValues().put("DGrammatik", row.getCell(9).getRichStringCellValue().getString());
                }
                if (row.getCell(10) != null) {
                    lv.getLemmaValues().put("DSemantik", row.getCell(10).getRichStringCellValue().getString());
                }
                if (row.getCell(11) != null) {
                    lv.getLemmaValues().put("categories", row.getCell(11).getRichStringCellValue().getString());
                }
            }
        }

        if (lv != null) {
            finalizeLemma(db, lv);
        }

        return true;
    }

    public boolean importLadinData(Language language, HttpServletRequest request) throws IOException, InvalidEntryException, NoDatabaseAvailableException, JAXBException, XMLStreamException {
        Database db = Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language));

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        date = df.format(new Date());

        Map<String, VerbDto> verbs;
        List<LadinDto> lemmas;
        HashMap<Integer, String> backupLemmas;
        boolean isPuter;
        if (language == Language.PUTER) {
            isPuter = true;
            verbs = loadVerbs("puter_verbs.xlsx", isPuter);
            lemmas = loadLemmas("puter.xlsx");
            backupLemmas = new HashMap<>();
        } else {
            isPuter = false;
            verbs = loadVerbs("vallader_verbs.xlsx", isPuter);
            lemmas = loadLemmas("vallader.xlsx");
            backupLemmas = loadBackupLemmas("vallader_2021-12-05.xlsx");
        }

        Set<String> missingVerbs = new TreeSet<>();
        lemmas.forEach(l -> {
            LemmaVersion lemmaVersion = getLemmaVersion(l);

            if (!l.getVerbId().equals("")) {
                if (!verbs.containsKey(l.getVerbId())) {
                    missingVerbs.add(l.getVerbId());
                    logger.error("ID not found: " + l.getVerbId());
                } else {
                    addVerbData(lemmaVersion, verbs.get(l.getVerbId()), isPuter);
                }
            }

            if (backupLemmas.get(Integer.valueOf(l.getId())) != null) {
                lemmaVersion.getLemmaValues().put("RSubsemantik", backupLemmas.get(Integer.valueOf(l.getId())));
            }

            LexEntry le = new LexEntry();
            le.addLemma(lemmaVersion);
            le.setCurrent(lemmaVersion);
            le.setNextInternalId(1);

            try {
                db.insert(le);
            } catch (InvalidEntryException e) {
                throw new RuntimeException(e);
            }
        });

        logger.error(missingVerbs.toString());

        return true;
    }
    private Map<String, VerbDto> loadVerbs(String fileName, boolean isPuter) throws IOException {
        FileInputStream source = new FileInputStream(new File("api/src/main/resources/ladin/" + fileName));

        Map<String, VerbDto> verbs = new HashMap<>();

        Workbook workbook = new XSSFWorkbook(source);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            VerbDto v = new VerbDto();
            Row row = sheet.getRow(i);
            v.setVerbId(row.getCell(0, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));

            v.setInfinitiv(row.getCell(1, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));

            v.setParticipperfectms(row.getCell(2, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setParticipperfectmp(row.getCell(3, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setParticipperfectfs(row.getCell(4, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setParticipperfectfp(row.getCell(5, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setComposedWith(row.getCell(6, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));

            v.setGerundium(row.getCell(7, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(8, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));

            v.setPreschentsing1(row.getCell(9, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(10, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setPreschentsing2(row.getCell(11, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(12, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setPreschentsing3(row.getCell(13, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(14, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setPreschentplural1(row.getCell(15, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(16, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setPreschentplural2(row.getCell(17, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(18, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setPreschentplural3(row.getCell(19, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(20, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));

            v.setImperfectsing1(row.getCell(21, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(22, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperfectsing2(row.getCell(23, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(24, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperfectsing3(row.getCell(25, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(26, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperfectplural1(row.getCell(27, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(28, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperfectplural2(row.getCell(29, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(30, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperfectplural3(row.getCell(31, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(32, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));

            v.setFutursing1(row.getCell(33, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(34, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setFutursing2(row.getCell(35, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(36, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setFutursing3(row.getCell(37, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(38, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setFuturplural1(row.getCell(39, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(40, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setFuturplural2(row.getCell(41, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(42, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setFuturplural3(row.getCell(43, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(44, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));

            v.setConjunctivsing1(row.getCell(45, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(46, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setConjunctivsing2(row.getCell(47, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(48, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setConjunctivsing3(row.getCell(49, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(50, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setConjunctivplural1(row.getCell(51, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(52, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setConjunctivplural2(row.getCell(53, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(54, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setConjunctivplural3(row.getCell(55, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(56, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));

            v.setCundizionalsing1(row.getCell(57, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(58, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setCundizionalsing2(row.getCell(59, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(60, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setCundizionalsing3(row.getCell(61, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(62, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setCundizionalplural1(row.getCell(63, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(64, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setCundizionalplural2(row.getCell(65, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(66, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setCundizionalplural3(row.getCell(67, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(68, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));

            v.setImperativ1(row.getCell(69, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(70, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperativ2(row.getCell(71, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(72, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperativ3(row.getCell(73, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(74, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperativ4(row.getCell(75, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(76, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperativ5(row.getCell(77, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(78, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperativ6(row.getCell(79, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                    + row.getCell(80, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));

            if (isPuter) {
                // import futur dubitativ
                v.setFuturdubitativsing1(row.getCell(81, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                        + row.getCell(82, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
                v.setFuturdubitativsing2(row.getCell(83, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                        + row.getCell(84, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
                v.setFuturdubitativsing3(row.getCell(85, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                        + row.getCell(86, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
                v.setFuturdubitativplural1(row.getCell(87, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                        + row.getCell(88, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
                v.setFuturdubitativplural2(row.getCell(89, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                        + row.getCell(90, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
                v.setFuturdubitativplural3(row.getCell(91, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " ")
                        + row.getCell(92, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));


                // generate conjunctiv 2
                if (v.getCundizionalsing1() != null && !"".equals(v.getCundizionalsing1())) {
                    v.setConjunctiv2sing1("ch'" +v.getCundizionalsing1());
                }
                if (v.getCundizionalsing2() != null && !"".equals(v.getCundizionalsing2())) {
                    v.setConjunctiv2sing2("cha " +v.getCundizionalsing2());
                }
                if (v.getCundizionalsing3() != null && !"".equals(v.getCundizionalsing3())) {
                    v.setConjunctiv2sing3("ch'" +v.getCundizionalsing3());
                }
                if (v.getCundizionalplural1() != null && !"".equals(v.getCundizionalplural1())) {
                    v.setConjunctiv2plural1("cha " +v.getCundizionalplural1());
                }
                if (v.getCundizionalplural2() != null && !"".equals(v.getCundizionalplural2())) {
                    v.setConjunctiv2plural2("cha " +v.getCundizionalplural2());
                }
                if (v.getCundizionalplural3() != null && !"".equals(v.getCundizionalplural3())) {
                    v.setConjunctiv2plural3("ch'" +v.getCundizionalplural3());
                }
            } else {
                // replace pronoun 1 p pl
                v.setPreschentplural1(v.getPreschentplural1().replace("nus/no", "nus"));
                v.setImperfectplural1(v.getImperfectplural1().replace("nus/no", "nus"));
                v.setFuturplural1(v.getFuturplural1().replace("nus/no", "nus"));
                v.setCundizionalplural1(v.getCundizionalplural1().replace("nus/no", "nus"));

                // replace multiple forms with new line
                String preschentplural2 = v.getPreschentplural2();
                if (preschentplural2.startsWith("vus/vo ") && preschentplural2.endsWith("ais/aivat")) {
                    String root = preschentplural2.replace("vus/vo ", "").replace("ais/aivat", "");
                    v.setPreschentplural2("vus " + root + "ais\n(vo " + root + "aivat)");
                } else if (preschentplural2.startsWith("vus s'") && preschentplural2.endsWith("ais/aivat")) {
                    String root = preschentplural2.replace("vus s'", "").replace("ais/aivat", "");
                    v.setPreschentplural2("vus s'" + root + "ais\n(vo s'" + root + "aivat)");
                } else if (preschentplural2.startsWith("vus as ") && preschentplural2.endsWith("ais/aivat")) {
                    String root = preschentplural2.replace("vus as", "").replace("ais/aivat", "");
                    v.setPreschentplural2("vus as " + root + "ais\n(vo as " + root + "aivat)");
                } else if (preschentplural2.startsWith("vus/vo ") && preschentplural2.endsWith("is/ivat")) {
                    String root = preschentplural2.replace("vus/vo ", "").replace("is/ivat", "");
                    v.setPreschentplural2("vus " + root + "is\n(vo " + root + "ivat)");
                } else if (preschentplural2.startsWith("vus s'") && preschentplural2.endsWith("is/ivat")) {
                    String root = preschentplural2.replace("vus s'", "").replace("is/ivat", "");
                    v.setPreschentplural2("vus s'" + root + "is\n(vo s'" + root + "ivat)");
                } else if (preschentplural2.startsWith("vus as") && preschentplural2.endsWith("is/ivat")) {
                    String root = preschentplural2.replace("vus as", "").replace("is/ivat", "");
                    v.setPreschentplural2("vus as " + root + "is\n(vo as " + root + "ivat)");
                } else if (!"".equals(preschentplural2)) {
                    logger.error("verbs senza ais/aivat: " + v.getInfinitiv());
                }

                // replace pronoun 2 p pl
                v.setPreschentplural2(v.getPreschentplural2().replace("vus/vo", "vus"));
                v.setImperfectplural2(v.getImperfectplural2().replace("vus/vo", "vus"));
                v.setFuturplural2(v.getFuturplural2().replace("vus/vo", "vus"));
                v.setCundizionalplural2(v.getCundizionalplural2().replace("vus/vo", "vus"));

                // generate conjunctiv 2
                if (v.getCundizionalsing1() != null && !"".equals(v.getCundizionalsing1())) {
                    v.setConjunctiv2sing1("ch'" +v.getCundizionalsing1());
                }
                if (v.getCundizionalsing2() != null && !"".equals(v.getCundizionalsing2())) {
                    v.setConjunctiv2sing2("cha " +v.getCundizionalsing2());
                }
                if (v.getCundizionalsing3() != null && !"".equals(v.getCundizionalsing3())) {
                    v.setConjunctiv2sing3("ch'" +v.getCundizionalsing3());
                }
                if (v.getCundizionalplural1() != null && !"".equals(v.getCundizionalplural1())) {
                    v.setConjunctiv2plural1("cha " +v.getCundizionalplural1());
                }
                if (v.getCundizionalplural2() != null && !"".equals(v.getCundizionalplural2())) {
                    v.setConjunctiv2plural2("cha " +v.getCundizionalplural2());
                }
                if (v.getCundizionalplural3() != null && !"".equals(v.getCundizionalplural3())) {
                    v.setConjunctiv2plural3("ch'" +v.getCundizionalplural3());
                }
            }

            verbs.put(v.getVerbId(), v);
        }

        return verbs;
    }

    private List<LadinDto> loadLemmas(String fileName) throws IOException {
        FileInputStream source = new FileInputStream(new File("api/src/main/resources/ladin/" + fileName));

        List<LadinDto> lemmas = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(source);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            LadinDto l = new LadinDto();
            Row row = sheet.getRow(i);
            try {
                for (int j = 0; j < 22; j++) {
                    row.getCell(j, CREATE_NULL_AS_BLANK).setCellType(STRING);
                }
                l.setId(row.getCell(0, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setEinschrkR(row.getCell(1, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setFlexD(row.getCell(2, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setFlexR(row.getCell(3, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setGenusD(row.getCell(4, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setGenusR(row.getCell(5, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setGrammKatD(row.getCell(6, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setGrammD(row.getCell(7, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setGrammR(row.getCell(8, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setSemindD(row.getCell(9, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setSemindR(row.getCell(10, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setSempraezD(row.getCell(11, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setSempraezR(row.getCell(12, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setSortD(row.getCell(13, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setSortDohneZiffern(row.getCell(14, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setSortR(row.getCell(15, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setSortRohneZiffern(row.getCell(16, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setStatusD(row.getCell(17, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setStatusR(row.getCell(18, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setStichwortD(row.getCell(19, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setStichwortR(row.getCell(20, CREATE_NULL_AS_BLANK).getStringCellValue());
                l.setVerbId(row.getCell(21, CREATE_NULL_AS_BLANK).getStringCellValue());
            } catch (IllegalStateException e) {
                System.out.println(e);
            }

            lemmas.add(l);
        }

        return lemmas;
    }

    /**
     *  In the newest data we got, the column `einschrkR` is empty. In an earlier backup, it
     *  contains data. So we import an old backup and use it to fill that column.
     */
    private HashMap<Integer, String> loadBackupLemmas(String fileName) throws IOException {
        FileInputStream source = new FileInputStream(new File("api/src/main/resources/ladin/" + fileName));

        HashMap<Integer, String> lemmas = new HashMap<>();

        Workbook workbook = new XSSFWorkbook(source);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            try {
                for (int j = 0; j < 22; j++) {
                    row.getCell(j, CREATE_NULL_AS_BLANK).setCellType(STRING);
                }
                Integer id = Integer.valueOf(row.getCell(0, CREATE_NULL_AS_BLANK).getStringCellValue());
                String semantics = row.getCell(1, CREATE_NULL_AS_BLANK).getStringCellValue().trim();
                if (semantics.equals("")) {
                    continue;
                }
                lemmas.put(id, semantics);

            } catch (IllegalStateException e) {
                System.out.println(e);
            }
        }

        return lemmas;
    }

    private LemmaVersion getLemmaVersion(LadinDto ladinDto) {
        LemmaVersion lv = new LemmaVersion();

        lv.getLemmaValues().put("ImportedId", ladinDto.getId());
        lv.getLemmaValues().put("DFlex",ladinDto.getFlexD());
        lv.getLemmaValues().put("RFlex",ladinDto.getFlexR());
        lv.getLemmaValues().put("DGenus",ladinDto.getGenusD());
        lv.getLemmaValues().put("RGenus",ladinDto.getGenusR());
        lv.getLemmaValues().put("DGrammatik",ladinDto.getGrammD());
        lv.getLemmaValues().put("RGrammatik",ladinDto.getGrammR());
        lv.getLemmaValues().put("DEnum",ladinDto.getSemindD());
        lv.getLemmaValues().put("REnum",ladinDto.getSemindR());
        lv.getLemmaValues().put("Dcategories",ladinDto.getSempraezD());
        lv.getLemmaValues().put("categories",ladinDto.getSempraezR());
        lv.getLemmaValues().put("DStichwort_sort",ladinDto.getSortD());
        lv.getLemmaValues().put("RStichwort_sort",ladinDto.getSortR());
        lv.getLemmaValues().put("DStichwort_sort_alpha",ladinDto.getSortDohneZiffern());
        lv.getLemmaValues().put("RStichwort_sort_alpha",ladinDto.getSortRohneZiffern());
        lv.getLemmaValues().put("DStatus",ladinDto.getStatusD());
        lv.getLemmaValues().put("RStatus",ladinDto.getStatusR());
        lv.getLemmaValues().put("DStichwort", ladinDto.getStichwortD());
        lv.getLemmaValues().put("RStichwort", ladinDto.getStichwortR());
        lv.getLemmaValues().put("verbID", ladinDto.getVerbId());
        lv.getLemmaValues().put("RSubsemantik", ladinDto.getEinschrkR());

        // generate links
        String RStichwort = lv.getLemmaValues().get("RStichwort");
        String DStichwort = lv.getLemmaValues().get("DStichwort");
        if (RStichwort.startsWith("► ")) {
            RStichwort = RStichwort.replace("► ", "cf. ");
            lv.getLemmaValues().put("RStichwort", RStichwort);
            lv.getLemmaValues().put("RRedirect", RStichwort.replaceFirst("cf. ", ""));
        }
        if (DStichwort.startsWith("► ")) {
            DStichwort = DStichwort.replace("► ", "cf. ");
            lv.getLemmaValues().put("DStichwort", DStichwort);
            lv.getLemmaValues().put("DRedirect", DStichwort.replaceFirst("cf. ", ""));
        }

        // static data
        lv.getLemmaValues().put("user_comment", "Import " + date);
        lv.setCreatorRole(EditorRole.ADMIN);
        lv.setStatus(LemmaVersion.Status.NEW_ENTRY);
        lv.setVerification(LemmaVersion.Verification.ACCEPTED);
        lv.setInternalId(0);
        lv.setUserId("import@pledarigrond.ch");
        lv.setTimestamp(System.currentTimeMillis());

        return lv;
    }

    private void addVerbData(LemmaVersion lemmaVersion, VerbDto verbDto, boolean isPuter) {
        lemmaVersion.getLemmaValues().put("infinitiv", verbDto.getInfinitiv());

        lemmaVersion.getLemmaValues().put("preschentsing1", verbDto.getPreschentsing1());
        lemmaVersion.getLemmaValues().put("preschentsing2", verbDto.getPreschentsing2());
        lemmaVersion.getLemmaValues().put("preschentsing3", verbDto.getPreschentsing3());
        lemmaVersion.getLemmaValues().put("preschentplural1", verbDto.getPreschentplural1());
        lemmaVersion.getLemmaValues().put("preschentplural2", verbDto.getPreschentplural2());
        lemmaVersion.getLemmaValues().put("preschentplural3", verbDto.getPreschentplural3());

        lemmaVersion.getLemmaValues().put("imperfectsing1", verbDto.getImperfectsing1());
        lemmaVersion.getLemmaValues().put("imperfectsing2", verbDto.getImperfectsing2());
        lemmaVersion.getLemmaValues().put("imperfectsing3", verbDto.getImperfectsing3());
        lemmaVersion.getLemmaValues().put("imperfectplural1", verbDto.getImperfectplural1());
        lemmaVersion.getLemmaValues().put("imperfectplural2", verbDto.getImperfectplural2());
        lemmaVersion.getLemmaValues().put("imperfectplural3", verbDto.getImperfectplural3());

        lemmaVersion.getLemmaValues().put("conjunctivsing1", verbDto.getConjunctivsing1());
        lemmaVersion.getLemmaValues().put("conjunctivsing2", verbDto.getConjunctivsing2());
        lemmaVersion.getLemmaValues().put("conjunctivsing3", verbDto.getConjunctivsing3());
        lemmaVersion.getLemmaValues().put("conjunctivplural1", verbDto.getConjunctivplural1());
        lemmaVersion.getLemmaValues().put("conjunctivplural2", verbDto.getConjunctivplural2());
        lemmaVersion.getLemmaValues().put("conjunctivplural3", verbDto.getConjunctivplural3());

        lemmaVersion.getLemmaValues().put("conjunctiv2sing1", verbDto.getConjunctiv2sing1());
        lemmaVersion.getLemmaValues().put("conjunctiv2sing2", verbDto.getConjunctiv2sing2());
        lemmaVersion.getLemmaValues().put("conjunctiv2sing3", verbDto.getConjunctiv2sing3());
        lemmaVersion.getLemmaValues().put("conjunctiv2plural1", verbDto.getConjunctiv2plural1());
        lemmaVersion.getLemmaValues().put("conjunctiv2plural2", verbDto.getConjunctiv2plural2());
        lemmaVersion.getLemmaValues().put("conjunctiv2plural3", verbDto.getConjunctiv2plural3());

        lemmaVersion.getLemmaValues().put("cundizionalsing1", verbDto.getCundizionalsing1());
        lemmaVersion.getLemmaValues().put("cundizionalsing2", verbDto.getCundizionalsing2());
        lemmaVersion.getLemmaValues().put("cundizionalsing3", verbDto.getCundizionalsing3());
        lemmaVersion.getLemmaValues().put("cundizionalplural1", verbDto.getCundizionalplural1());
        lemmaVersion.getLemmaValues().put("cundizionalplural2", verbDto.getCundizionalplural2());
        lemmaVersion.getLemmaValues().put("cundizionalplural3", verbDto.getCundizionalplural3());

        lemmaVersion.getLemmaValues().put("participperfectms", verbDto.getParticipperfectms());
        lemmaVersion.getLemmaValues().put("participperfectfs", verbDto.getParticipperfectfs());
        lemmaVersion.getLemmaValues().put("participperfectmp", verbDto.getParticipperfectmp());
        lemmaVersion.getLemmaValues().put("participperfectfp", verbDto.getParticipperfectfp());

        lemmaVersion.getLemmaValues().put("imperativ1", verbDto.getImperativ1());
        lemmaVersion.getLemmaValues().put("imperativ2", verbDto.getImperativ2());
        lemmaVersion.getLemmaValues().put("imperativ3", verbDto.getImperativ3());
        lemmaVersion.getLemmaValues().put("imperativ4", verbDto.getImperativ4());
        lemmaVersion.getLemmaValues().put("imperativ5", verbDto.getImperativ5());
        lemmaVersion.getLemmaValues().put("imperativ6", verbDto.getImperativ6());

        lemmaVersion.getLemmaValues().put("gerundium", verbDto.getGerundium());

        lemmaVersion.getLemmaValues().put("futursing1", verbDto.getFutursing1());
        lemmaVersion.getLemmaValues().put("futursing2", verbDto.getFutursing2());
        lemmaVersion.getLemmaValues().put("futursing3", verbDto.getFutursing3());
        lemmaVersion.getLemmaValues().put("futurplural1", verbDto.getFuturplural1());
        lemmaVersion.getLemmaValues().put("futurplural2", verbDto.getFuturplural2());
        lemmaVersion.getLemmaValues().put("futurplural3", verbDto.getFuturplural3());

        if (isPuter) {
            lemmaVersion.getLemmaValues().put("futurdubitativsing1", verbDto.getFuturdubitativsing1());
            lemmaVersion.getLemmaValues().put("futurdubitativsing2", verbDto.getFuturdubitativsing2());
            lemmaVersion.getLemmaValues().put("futurdubitativsing3", verbDto.getFuturdubitativsing3());
            lemmaVersion.getLemmaValues().put("futurdubitativplural1", verbDto.getFuturdubitativplural1());
            lemmaVersion.getLemmaValues().put("futurdubitativplural2", verbDto.getFuturdubitativplural2());
            lemmaVersion.getLemmaValues().put("futurdubitativplural3", verbDto.getFuturdubitativplural3());

            String futur1_2ppl = lemmaVersion.getLemmaValues().get("futurplural2");
            if (lemmaVersion.getLemmaValues().get("infinitiv").endsWith("ir") && futur1_2ppl.endsWith("iro")) {
                lemmaVersion.getLemmaValues().put("futurplural2", futur1_2ppl + "s");
                logger.warn("Changed futur1 2p.pl.: " + lemmaVersion.getLemmaValues().get("infinitiv"));
            }

            generateEncliticFormsPuter(lemmaVersion);
        } else {
            generateEncliticFormsVallader(lemmaVersion);
        }

        lemmaVersion.getLemmaValues().put("RInflectionType", "V");
        lemmaVersion.getLemmaValues().put("composedWith", verbDto.getComposedWith());
    }
    
    private void generateEncliticFormsPuter(LemmaVersion lemmaVersion) {
        PuterConjugationStructure cs = ConjugationStructureGenerator.generatePuterConjugationStructure(lemmaVersion.getLemmaValues());
        cs.setConjugationClass(PuterConjugationClasses.getConjugationClass(lemmaVersion.getEntryValue(RM_INFLECTION_SUBTYPE)));

        if (cs.getConjugationclass() == null) {
            PuterConjugation conj = new PuterConjugation();
            InflectionResponse ir = conj.guessInflection(lemmaVersion.getEntryValue("RStichwort"), "", "");
            if (ir != null) {
                cs.setConjugationClass(ir.getInflectionSubType());
            } else {
                cs.setConjugationClass(PuterConjugationClasses.getConjugationClass("1"));
            }
        }
        PronounRemover pr = new PronounRemover();
        cs.setPreschentsing1(pr.removePronouns(Language.PUTER, cs.getPreschentsing1()));
        cs.setPreschentsing2(pr.removePronouns(Language.PUTER, cs.getPreschentsing2()));
        cs.setPreschentsing3(pr.removePronouns(Language.PUTER, cs.getPreschentsing3()));
        cs.setPreschentplural1(pr.removePronouns(Language.PUTER, cs.getPreschentplural1()));
        cs.setPreschentplural2(pr.removePronouns(Language.PUTER, cs.getPreschentplural2()));
        cs.setPreschentplural3(pr.removePronouns(Language.PUTER, cs.getPreschentplural3()));

        cs.setImperfectsing1(pr.removePronouns(Language.PUTER, cs.getImperfectsing1()));
        cs.setImperfectsing2(pr.removePronouns(Language.PUTER, cs.getImperfectsing2()));
        cs.setImperfectsing3(pr.removePronouns(Language.PUTER, cs.getImperfectsing3()));
        cs.setImperfectplural1(pr.removePronouns(Language.PUTER, cs.getImperfectplural1()));
        cs.setImperfectplural2(pr.removePronouns(Language.PUTER, cs.getImperfectplural2()));
        cs.setImperfectplural3(pr.removePronouns(Language.PUTER, cs.getImperfectplural3()));

        cs.setCundizionalsing1(pr.removePronouns(Language.PUTER, cs.getCundizionalsing1()));
        cs.setCundizionalsing2(pr.removePronouns(Language.PUTER, cs.getCundizionalsing2()));
        cs.setCundizionalsing3(pr.removePronouns(Language.PUTER, cs.getCundizionalsing3()));
        cs.setCundizionalplural1(pr.removePronouns(Language.PUTER, cs.getCundizionalplural1()));
        cs.setCundizionalplural2(pr.removePronouns(Language.PUTER, cs.getCundizionalplural2()));
        cs.setCundizionalplural3(pr.removePronouns(Language.PUTER, cs.getCundizionalplural3()));

        cs.setFutursing1(pr.removePronouns(Language.PUTER, cs.getFutursing1()));
        cs.setFutursing2(pr.removePronouns(Language.PUTER, cs.getFutursing2()));
        cs.setFutursing3(pr.removePronouns(Language.PUTER, cs.getFutursing3()));
        cs.setFuturplural1(pr.removePronouns(Language.PUTER, cs.getFuturplural1()));
        cs.setFuturplural2(pr.removePronouns(Language.PUTER, cs.getFuturplural2()));
        cs.setFuturplural3(pr.removePronouns(Language.PUTER, cs.getFuturplural3()));

        cs.setFuturdubitativsing1(pr.removePronouns(Language.PUTER, cs.getFuturdubitativsing1()));
        cs.setFuturdubitativsing2(pr.removePronouns(Language.PUTER, cs.getFuturdubitativsing2()));
        cs.setFuturdubitativsing3(pr.removePronouns(Language.PUTER, cs.getFuturdubitativsing3()));
        cs.setFuturdubitativplural1(pr.removePronouns(Language.PUTER, cs.getFuturdubitativplural1()));
        cs.setFuturdubitativplural2(pr.removePronouns(Language.PUTER, cs.getFuturdubitativplural2()));
        cs.setFuturdubitativplural3(pr.removePronouns(Language.PUTER, cs.getFuturdubitativplural3()));

        if (
                cs.getPreschentsing1() == null || cs.getPreschentsing1().isEmpty() ||
                        cs.getPreschentsing2() == null || cs.getPreschentsing2().isEmpty() ||
                        cs.getPreschentsing3() == null || cs.getPreschentsing3().isEmpty() ||
                        cs.getPreschentplural1() == null || cs.getPreschentplural1().isEmpty() ||
                        cs.getPreschentplural1() == null || cs.getPreschentplural1().isEmpty() ||
                        cs.getPreschentplural1() == null || cs.getPreschentplural1().isEmpty() ||

                        cs.getImperfectsing1() == null || cs.getImperfectsing1().isEmpty() ||
                        cs.getImperfectsing2() == null || cs.getImperfectsing2().isEmpty() ||
                        cs.getImperfectsing3() == null || cs.getImperfectsing3().isEmpty() ||
                        cs.getImperfectplural1() == null || cs.getImperfectplural1().isEmpty() ||
                        cs.getImperfectplural1() == null || cs.getImperfectplural1().isEmpty() ||
                        cs.getImperfectplural1() == null || cs.getImperfectplural1().isEmpty() ||

                        cs.getCundizionalsing1() == null || cs.getCundizionalsing1().isEmpty() ||
                        cs.getCundizionalsing2() == null || cs.getCundizionalsing2().isEmpty() ||
                        cs.getCundizionalsing3() == null || cs.getCundizionalsing3().isEmpty() ||
                        cs.getCundizionalplural1() == null || cs.getCundizionalplural1().isEmpty() ||
                        cs.getCundizionalplural1() == null || cs.getCundizionalplural1().isEmpty() ||
                        cs.getCundizionalplural1() == null || cs.getCundizionalplural1().isEmpty() ||

                        cs.getFutursing1() == null || cs.getFutursing1().isEmpty() ||
                        cs.getFutursing2() == null || cs.getFutursing2().isEmpty() ||
                        cs.getFutursing3() == null || cs.getFutursing3().isEmpty() ||
                        cs.getFuturplural1() == null || cs.getFuturplural1().isEmpty() ||
                        cs.getFuturplural1() == null || cs.getFuturplural1().isEmpty() ||
                        cs.getFuturplural1() == null || cs.getFuturplural1().isEmpty() ||

                        cs.getFuturdubitativsing1() == null || cs.getFuturdubitativsing1().isEmpty() ||
                        cs.getFuturdubitativsing2() == null || cs.getFuturdubitativsing2().isEmpty() ||
                        cs.getFuturdubitativsing3() == null || cs.getFuturdubitativsing3().isEmpty() ||
                        cs.getFuturdubitativplural1() == null || cs.getFuturdubitativplural1().isEmpty() ||
                        cs.getFuturdubitativplural1() == null || cs.getFuturdubitativplural1().isEmpty() ||
                        cs.getFuturdubitativplural1() == null || cs.getFuturdubitativplural1().isEmpty()
        ) {
            logger.debug("Form missing for: " + lemmaVersion.getLemmaValues().get("RStichwort"));
            return;
        }

        PuterConjugation.addEncliticForms(cs);

        addEncliticPronounsPuter(cs);

        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.preschentsing1enclitic, cs.getPreschentsing1Enclitic());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.preschentsing2enclitic, cs.getPreschentsing2Enclitic());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.preschentsing3encliticm, cs.getPreschentsing3EncliticM());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.preschentsing3encliticf, cs.getPreschentsing3EncliticF());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.preschentplural1enclitic, cs.getPreschentplural1Enclitic());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.preschentplural2enclitic, cs.getPreschentplural2Enclitic());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.preschentplural3enclitic, cs.getPreschentplural3Enclitic());

        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.imperfectsing1enclitic, cs.getImperfectsing1Enclitic());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.imperfectsing2enclitic, cs.getImperfectsing2Enclitic());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.imperfectsing3encliticm, cs.getImperfectsing3EncliticM());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.imperfectsing3encliticf, cs.getImperfectsing3EncliticF());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.imperfectplural1enclitic, cs.getImperfectplural1Enclitic());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.imperfectplural2enclitic, cs.getImperfectplural2Enclitic());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.imperfectplural3enclitic, cs.getImperfectplural3Enclitic());

        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.cundizionalsing1enclitic, cs.getCundizionalsing1Enclitic());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.cundizionalsing2enclitic, cs.getCundizionalsing2Enclitic());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.cundizionalsing3encliticm, cs.getCundizionalsing3EncliticM());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.cundizionalsing3encliticf, cs.getCundizionalsing3EncliticF());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.cundizionalplural1enclitic, cs.getCundizionalplural1Enclitic());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.cundizionalplural2enclitic, cs.getCundizionalplural2Enclitic());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.cundizionalplural3enclitic, cs.getCundizionalplural3Enclitic());

        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.futursing1enclitic, cs.getFutursing1Enclitic());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.futursing2enclitic, cs.getFutursing2Enclitic());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.futursing3encliticm, cs.getFutursing3EncliticM());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.futursing3encliticf, cs.getFutursing3EncliticF());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.futurplural1enclitic, cs.getFuturplural1Enclitic());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.futurplural2enclitic, cs.getFuturplural2Enclitic());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.futurplural3enclitic, cs.getFuturplural3Enclitic());

        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.futurdubitativsing1enclitic, cs.getFuturdubitativsing1Enclitic());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.futurdubitativsing2enclitic, cs.getFuturdubitativsing2Enclitic());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.futurdubitativsing3encliticm, cs.getFuturdubitativsing3EncliticM());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.futurdubitativsing3encliticf, cs.getFuturdubitativsing3EncliticF());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.futurdubitativplural1enclitic, cs.getFuturdubitativplural1Enclitic());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.futurdubitativplural2enclitic, cs.getFuturdubitativplural2Enclitic());
        lemmaVersion.getLemmaValues().put(PuterConjugationStructure.futurdubitativplural3enclitic, cs.getFuturdubitativplural3Enclitic());
    }


    private void addEncliticPronounsPuter(PuterConjugationStructure cs) {
        String verb = cs.getInfinitiv();

        if (verb.startsWith("as ")) {
            // Reflexive Verbs that start with consonants
            addReflexivePronounsPuter(cs);

        } else if (verb.startsWith("s'")) {
            // Reflexive Verbs that start with vowels
            addReflexivePronounsVowelPuter(cs);
        }
    }

    private void addReflexivePronounsPuter(PuterConjugationStructure cs) {
        // PRESCHENT
        cs.setPreschentsing1Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_1ps, cs.getPreschentsing1Enclitic()));
        cs.setPreschentsing2Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_2ps, cs.getPreschentsing2Enclitic()));
        cs.setPreschentsing3EncliticM(setPronounPuter(PuterConjugationPronouns.pron_r_3ps, cs.getPreschentsing3EncliticM()));
        cs.setPreschentsing3EncliticF(setPronounPuter(PuterConjugationPronouns.pron_r_3ps, cs.getPreschentsing3EncliticF()));
        cs.setPreschentplural1Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_1pp, cs.getPreschentplural1Enclitic()));
        cs.setPreschentplural2Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_2pp, cs.getPreschentplural2Enclitic()));
        cs.setPreschentplural3Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_3pp, cs.getPreschentplural3Enclitic()));

        // IMPERFECT
        cs.setImperfectsing1Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_1ps, cs.getImperfectsing1Enclitic()));
        cs.setImperfectsing2Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_2ps, cs.getImperfectsing2Enclitic()));
        cs.setImperfectsing3EncliticM(setPronounPuter(PuterConjugationPronouns.pron_r_3ps, cs.getImperfectsing3EncliticM()));
        cs.setImperfectsing3EncliticF(setPronounPuter(PuterConjugationPronouns.pron_r_3ps, cs.getImperfectsing3EncliticF()));
        cs.setImperfectplural1Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_1pp, cs.getImperfectplural1Enclitic()));
        cs.setImperfectplural2Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_2pp, cs.getImperfectplural2Enclitic()));
        cs.setImperfectplural3Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_3pp, cs.getImperfectplural3Enclitic()));

        // CUNDIZIONAL
        cs.setCundizionalsing1Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_1ps, cs.getCundizionalsing1Enclitic()));
        cs.setCundizionalsing2Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_2ps, cs.getCundizionalsing2Enclitic()));
        cs.setCundizionalsing3EncliticM(setPronounPuter(PuterConjugationPronouns.pron_r_3ps, cs.getCundizionalsing3EncliticM()));
        cs.setCundizionalsing3EncliticF(setPronounPuter(PuterConjugationPronouns.pron_r_3ps, cs.getCundizionalsing3EncliticF()));
        cs.setCundizionalplural1Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_1pp, cs.getCundizionalplural1Enclitic()));
        cs.setCundizionalplural2Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_2pp, cs.getCundizionalplural2Enclitic()));
        cs.setCundizionalplural3Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_3pp, cs.getCundizionalplural3Enclitic()));

        // FUTUR
        cs.setFutursing1Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_1ps, cs.getFutursing1Enclitic()));
        cs.setFutursing2Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_2ps, cs.getFutursing2Enclitic()));
        cs.setFutursing3EncliticM(setPronounPuter(PuterConjugationPronouns.pron_r_3ps, cs.getFutursing3EncliticM()));
        cs.setFutursing3EncliticF(setPronounPuter(PuterConjugationPronouns.pron_r_3ps, cs.getFutursing3EncliticF()));
        cs.setFuturplural1Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_1pp, cs.getFuturplural1Enclitic()));
        cs.setFuturplural2Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_2pp, cs.getFuturplural2Enclitic()));
        cs.setFuturplural3Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_3pp, cs.getFuturplural3Enclitic()));

        // FUTUR DUBITATIV
        cs.setFuturdubitativsing1Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_1ps, cs.getFuturdubitativsing1Enclitic()));
        cs.setFuturdubitativsing2Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_2ps, cs.getFuturdubitativsing2Enclitic()));
        cs.setFuturdubitativsing3EncliticM(setPronounPuter(PuterConjugationPronouns.pron_r_3ps, cs.getFuturdubitativsing3EncliticM()));
        cs.setFuturdubitativsing3EncliticF(setPronounPuter(PuterConjugationPronouns.pron_r_3ps, cs.getFuturdubitativsing3EncliticF()));
        cs.setFuturdubitativplural1Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_1pp, cs.getFuturdubitativplural1Enclitic()));
        cs.setFuturdubitativplural2Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_2pp, cs.getFuturdubitativplural2Enclitic()));
        cs.setFuturdubitativplural3Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_3pp, cs.getFuturdubitativplural3Enclitic()));
    }

    private void addReflexivePronounsVowelPuter(PuterConjugationStructure cs) {
        // PRESCHENT
        cs.setPreschentsing1Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_1ps, cs.getPreschentsing1Enclitic()));
        cs.setPreschentsing2Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_2ps, cs.getPreschentsing2Enclitic()));
        cs.setPreschentsing3EncliticM(setPronounPuter(PuterConjugationPronouns.pron_r_v_3ps, cs.getPreschentsing3EncliticM()));
        cs.setPreschentsing3EncliticF(setPronounPuter(PuterConjugationPronouns.pron_r_v_3ps, cs.getPreschentsing3EncliticF()));
        cs.setPreschentplural1Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_1pp, cs.getPreschentplural1Enclitic()));
        cs.setPreschentplural2Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_2pp, cs.getPreschentplural2Enclitic()));
        cs.setPreschentplural3Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_3pp, cs.getPreschentplural3Enclitic()));

        // IMPERFECT
        cs.setImperfectsing1Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_1ps, cs.getImperfectsing1Enclitic()));
        cs.setImperfectsing2Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_2ps, cs.getImperfectsing2Enclitic()));
        cs.setImperfectsing3EncliticM(setPronounPuter(PuterConjugationPronouns.pron_r_v_3ps, cs.getImperfectsing3EncliticM()));
        cs.setImperfectsing3EncliticF(setPronounPuter(PuterConjugationPronouns.pron_r_v_3ps, cs.getImperfectsing3EncliticF()));
        cs.setImperfectplural1Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_1pp, cs.getImperfectplural1Enclitic()));
        cs.setImperfectplural2Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_2pp, cs.getImperfectplural2Enclitic()));
        cs.setImperfectplural3Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_3pp, cs.getImperfectplural3Enclitic()));

        // CUNDIZIONAL
        cs.setCundizionalsing1Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_1ps, cs.getCundizionalsing1Enclitic()));
        cs.setCundizionalsing2Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_2ps, cs.getCundizionalsing2Enclitic()));
        cs.setCundizionalsing3EncliticM(setPronounPuter(PuterConjugationPronouns.pron_r_v_3ps, cs.getCundizionalsing3EncliticM()));
        cs.setCundizionalsing3EncliticF(setPronounPuter(PuterConjugationPronouns.pron_r_v_3ps, cs.getCundizionalsing3EncliticF()));
        cs.setCundizionalplural1Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_1pp, cs.getCundizionalplural1Enclitic()));
        cs.setCundizionalplural2Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_2pp, cs.getCundizionalplural2Enclitic()));
        cs.setCundizionalplural3Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_3pp, cs.getCundizionalplural3Enclitic()));

        // FUTUR
        cs.setFutursing1Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_1ps, cs.getFutursing1Enclitic()));
        cs.setFutursing2Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_2ps, cs.getFutursing2Enclitic()));
        cs.setFutursing3EncliticM(setPronounPuter(PuterConjugationPronouns.pron_r_v_3ps, cs.getFutursing3EncliticM()));
        cs.setFutursing3EncliticF(setPronounPuter(PuterConjugationPronouns.pron_r_v_3ps, cs.getFutursing3EncliticF()));
        cs.setFuturplural1Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_1pp, cs.getFuturplural1Enclitic()));
        cs.setFuturplural2Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_2pp, cs.getFuturplural2Enclitic()));
        cs.setFuturplural3Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_3pp, cs.getFuturplural3Enclitic()));

        // FUTUR DUBITATIV
        cs.setFuturdubitativsing1Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_1ps, cs.getFuturdubitativsing1Enclitic()));
        cs.setFuturdubitativsing2Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_2ps, cs.getFuturdubitativsing2Enclitic()));
        cs.setFuturdubitativsing3EncliticM(setPronounPuter(PuterConjugationPronouns.pron_r_v_3ps, cs.getFuturdubitativsing3EncliticM()));
        cs.setFuturdubitativsing3EncliticF(setPronounPuter(PuterConjugationPronouns.pron_r_v_3ps, cs.getFuturdubitativsing3EncliticF()));
        cs.setFuturdubitativplural1Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_1pp, cs.getFuturdubitativplural1Enclitic()));
        cs.setFuturdubitativplural2Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_2pp, cs.getFuturdubitativplural2Enclitic()));
        cs.setFuturdubitativplural3Enclitic(setPronounPuter(PuterConjugationPronouns.pron_r_v_3pp, cs.getFuturdubitativplural3Enclitic()));
    }

    protected String setPronounPuter(String pronoun, String forms) {
        return setPronounPuter(pronoun, forms, "");
    }

    protected String setPronounPuter(String pronoun, String forms, String suffix) {
        String[] singleForms = forms.split("\\R");
        for (int i = 0; i < singleForms.length; i++) {
            if ("".equals(singleForms[i])) {
                continue;
            }
            singleForms[i] = pronoun + singleForms[i] + suffix;
        }
        return String.join("\n", singleForms);
    }

    private void generateEncliticFormsVallader(LemmaVersion lemmaVersion) {
        ValladerConjugationStructure cs = ConjugationStructureGenerator.generateValladerConjugationStructure(lemmaVersion.getLemmaValues());
        cs.setConjugationClass(ValladerConjugationClasses.getConjugationClass(lemmaVersion.getEntryValue(RM_INFLECTION_SUBTYPE)));

        if (cs.getConjugationclass() == null) {
            PuterConjugation conj = new PuterConjugation();
            InflectionResponse ir = conj.guessInflection(lemmaVersion.getEntryValue("RStichwort"), "", "");
            if (ir != null) {
                cs.setConjugationClass(ir.getInflectionSubType());
            } else {
                cs.setConjugationClass(ValladerConjugationClasses.getConjugationClass("1"));
            }
        }
        PronounRemover pr = new PronounRemover();
        cs.setPreschentsing1(pr.removePronouns(Language.VALLADER, cs.getPreschentsing1()));
        cs.setPreschentsing2(pr.removePronouns(Language.VALLADER, cs.getPreschentsing2()));
        cs.setPreschentsing3(pr.removePronouns(Language.VALLADER, cs.getPreschentsing3()));
        cs.setPreschentplural1(pr.removePronouns(Language.VALLADER, cs.getPreschentplural1()));
        cs.setPreschentplural2(pr.removePronouns(Language.VALLADER, cs.getPreschentplural2()));
        cs.setPreschentplural3(pr.removePronouns(Language.VALLADER, cs.getPreschentplural3()));

        cs.setImperfectsing1(pr.removePronouns(Language.VALLADER, cs.getImperfectsing1()));
        cs.setImperfectsing2(pr.removePronouns(Language.VALLADER, cs.getImperfectsing2()));
        cs.setImperfectsing3(pr.removePronouns(Language.VALLADER, cs.getImperfectsing3()));
        cs.setImperfectplural1(pr.removePronouns(Language.VALLADER, cs.getImperfectplural1()));
        cs.setImperfectplural2(pr.removePronouns(Language.VALLADER, cs.getImperfectplural2()));
        cs.setImperfectplural3(pr.removePronouns(Language.VALLADER, cs.getImperfectplural3()));

        cs.setCundizionalsing1(pr.removePronouns(Language.VALLADER, cs.getCundizionalsing1()));
        cs.setCundizionalsing2(pr.removePronouns(Language.VALLADER, cs.getCundizionalsing2()));
        cs.setCundizionalsing3(pr.removePronouns(Language.VALLADER, cs.getCundizionalsing3()));
        cs.setCundizionalplural1(pr.removePronouns(Language.VALLADER, cs.getCundizionalplural1()));
        cs.setCundizionalplural2(pr.removePronouns(Language.VALLADER, cs.getCundizionalplural2()));
        cs.setCundizionalplural3(pr.removePronouns(Language.VALLADER, cs.getCundizionalplural3()));

        cs.setFutursing1(pr.removePronouns(Language.VALLADER, cs.getFutursing1()));
        cs.setFutursing2(pr.removePronouns(Language.VALLADER, cs.getFutursing2()));
        cs.setFutursing3(pr.removePronouns(Language.VALLADER, cs.getFutursing3()));
        cs.setFuturplural1(pr.removePronouns(Language.VALLADER, cs.getFuturplural1()));
        cs.setFuturplural2(pr.removePronouns(Language.VALLADER, cs.getFuturplural2()));
        cs.setFuturplural3(pr.removePronouns(Language.VALLADER, cs.getFuturplural3()));

        if (
                cs.getPreschentsing1() == null || cs.getPreschentsing1().isEmpty() ||
                        cs.getPreschentsing2() == null || cs.getPreschentsing2().isEmpty() ||
                        cs.getPreschentsing3() == null || cs.getPreschentsing3().isEmpty() ||
                        cs.getPreschentplural1() == null || cs.getPreschentplural1().isEmpty() ||
                        cs.getPreschentplural1() == null || cs.getPreschentplural1().isEmpty() ||
                        cs.getPreschentplural1() == null || cs.getPreschentplural1().isEmpty() ||

                        cs.getImperfectsing1() == null || cs.getImperfectsing1().isEmpty() ||
                        cs.getImperfectsing2() == null || cs.getImperfectsing2().isEmpty() ||
                        cs.getImperfectsing3() == null || cs.getImperfectsing3().isEmpty() ||
                        cs.getImperfectplural1() == null || cs.getImperfectplural1().isEmpty() ||
                        cs.getImperfectplural1() == null || cs.getImperfectplural1().isEmpty() ||
                        cs.getImperfectplural1() == null || cs.getImperfectplural1().isEmpty() ||

                        cs.getCundizionalsing1() == null || cs.getCundizionalsing1().isEmpty() ||
                        cs.getCundizionalsing2() == null || cs.getCundizionalsing2().isEmpty() ||
                        cs.getCundizionalsing3() == null || cs.getCundizionalsing3().isEmpty() ||
                        cs.getCundizionalplural1() == null || cs.getCundizionalplural1().isEmpty() ||
                        cs.getCundizionalplural1() == null || cs.getCundizionalplural1().isEmpty() ||
                        cs.getCundizionalplural1() == null || cs.getCundizionalplural1().isEmpty() ||

                        cs.getFutursing1() == null || cs.getFutursing1().isEmpty() ||
                        cs.getFutursing2() == null || cs.getFutursing2().isEmpty() ||
                        cs.getFutursing3() == null || cs.getFutursing3().isEmpty() ||
                        cs.getFuturplural1() == null || cs.getFuturplural1().isEmpty() ||
                        cs.getFuturplural1() == null || cs.getFuturplural1().isEmpty() ||
                        cs.getFuturplural1() == null || cs.getFuturplural1().isEmpty()
        ) {
            logger.debug("Form missing for: " + lemmaVersion.getLemmaValues().get("RStichwort"));
            return;
        }

        ValladerConjugation.addEncliticForms(cs);

        addEncliticPronounsVallader(cs);

        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.preschentsing1enclitic, cs.getPreschentsing1Enclitic());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.preschentsing2enclitic, cs.getPreschentsing2Enclitic());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.preschentsing3encliticm, cs.getPreschentsing3EncliticM());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.preschentsing3encliticf, cs.getPreschentsing3EncliticF());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.preschentplural1enclitic, cs.getPreschentplural1Enclitic());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.preschentplural2enclitic, cs.getPreschentplural2Enclitic());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.preschentplural3enclitic, cs.getPreschentplural3Enclitic());

        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.imperfectsing1enclitic, cs.getImperfectsing1Enclitic());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.imperfectsing2enclitic, cs.getImperfectsing2Enclitic());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.imperfectsing3encliticm, cs.getImperfectsing3EncliticM());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.imperfectsing3encliticf, cs.getImperfectsing3EncliticF());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.imperfectplural1enclitic, cs.getImperfectplural1Enclitic());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.imperfectplural2enclitic, cs.getImperfectplural2Enclitic());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.imperfectplural3enclitic, cs.getImperfectplural3Enclitic());

        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.cundizionalsing1enclitic, cs.getCundizionalsing1Enclitic());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.cundizionalsing2enclitic, cs.getCundizionalsing2Enclitic());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.cundizionalsing3encliticm, cs.getCundizionalsing3EncliticM());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.cundizionalsing3encliticf, cs.getCundizionalsing3EncliticF());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.cundizionalplural1enclitic, cs.getCundizionalplural1Enclitic());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.cundizionalplural2enclitic, cs.getCundizionalplural2Enclitic());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.cundizionalplural3enclitic, cs.getCundizionalplural3Enclitic());

        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.futursing1enclitic, cs.getFutursing1Enclitic());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.futursing2enclitic, cs.getFutursing2Enclitic());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.futursing3encliticm, cs.getFutursing3EncliticM());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.futursing3encliticf, cs.getFutursing3EncliticF());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.futurplural1enclitic, cs.getFuturplural1Enclitic());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.futurplural2enclitic, cs.getFuturplural2Enclitic());
        lemmaVersion.getLemmaValues().put(ValladerConjugationStructure.futurplural3enclitic, cs.getFuturplural3Enclitic());
    }

    private void addEncliticPronounsVallader(ValladerConjugationStructure cs) {
        String verb = cs.getInfinitiv();

        if (verb.startsWith("as ")) {
            // Reflexive Verbs that start with consonants
            addReflexivePronounsVallader(cs);

        } else if (verb.startsWith("s'")) {
            // Reflexive Verbs that start with vowels
            addReflexivePronounsVowelVallader(cs);
        }
    }

    private void addReflexivePronounsVallader(ValladerConjugationStructure cs) {
        // ENCLITIC PRESCHENT
        cs.setPreschentsing1Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_1ps, cs.getPreschentsing1Enclitic()));
        cs.setPreschentsing2Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_2ps, cs.getPreschentsing2Enclitic()));
        cs.setPreschentsing3EncliticM(setPronounVallader(ValladerConjugationPronouns.pron_r_3ps, cs.getPreschentsing3EncliticM()));
        cs.setPreschentsing3EncliticF(setPronounVallader(ValladerConjugationPronouns.pron_r_3ps, cs.getPreschentsing3EncliticF()));
        cs.setPreschentplural1Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_1pp, cs.getPreschentplural1Enclitic()));
        cs.setPreschentplural2Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_2pp, cs.getPreschentplural2Enclitic()));
        cs.setPreschentplural3Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_3pp, cs.getPreschentplural3Enclitic()));

        // ENCLITIC IMPERFECT
        cs.setImperfectsing1Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_1ps, cs.getImperfectsing1Enclitic()));
        cs.setImperfectsing2Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_2ps, cs.getImperfectsing2Enclitic()));
        cs.setImperfectsing3EncliticM(setPronounVallader(ValladerConjugationPronouns.pron_r_3ps, cs.getImperfectsing3EncliticM()));
        cs.setImperfectsing3EncliticF(setPronounVallader(ValladerConjugationPronouns.pron_r_3ps, cs.getImperfectsing3EncliticF()));
        cs.setImperfectplural1Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_1pp, cs.getImperfectplural1Enclitic()));
        cs.setImperfectplural2Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_2pp, cs.getImperfectplural2Enclitic()));
        cs.setImperfectplural3Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_3pp, cs.getImperfectplural3Enclitic()));

        // ENCLITIC CUNDIZIONAL
        cs.setCundizionalsing1Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_1ps, cs.getCundizionalsing1Enclitic()));
        cs.setCundizionalsing2Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_2ps, cs.getCundizionalsing2Enclitic()));
        cs.setCundizionalsing3EncliticM(setPronounVallader(ValladerConjugationPronouns.pron_r_3ps, cs.getCundizionalsing3EncliticM()));
        cs.setCundizionalsing3EncliticF(setPronounVallader(ValladerConjugationPronouns.pron_r_3ps, cs.getCundizionalsing3EncliticF()));
        cs.setCundizionalplural1Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_1pp, cs.getCundizionalplural1Enclitic()));
        cs.setCundizionalplural2Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_2pp, cs.getCundizionalplural2Enclitic()));
        cs.setCundizionalplural3Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_3pp, cs.getCundizionalplural3Enclitic()));

        // ENCLITIC FUTUR
        cs.setFutursing1Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_1ps, cs.getFutursing1Enclitic()));
        cs.setFutursing2Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_2ps, cs.getFutursing2Enclitic()));
        cs.setFutursing3EncliticM(setPronounVallader(ValladerConjugationPronouns.pron_r_3ps, cs.getFutursing3EncliticM()));
        cs.setFutursing3EncliticF(setPronounVallader(ValladerConjugationPronouns.pron_r_3ps, cs.getFutursing3EncliticF()));
        cs.setFuturplural1Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_1pp, cs.getFuturplural1Enclitic()));
        cs.setFuturplural2Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_2pp, cs.getFuturplural2Enclitic()));
        cs.setFuturplural3Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_3pp, cs.getFuturplural3Enclitic()));
    }

    private void addReflexivePronounsVowelVallader(ValladerConjugationStructure cs) {
        // ENCLITIC PRESCHENT
        cs.setPreschentsing1Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_v_1ps, cs.getPreschentsing1Enclitic()));
        cs.setPreschentsing2Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_v_2ps, cs.getPreschentsing2Enclitic()));
        cs.setPreschentsing3EncliticM(setPronounVallader(ValladerConjugationPronouns.pron_r_v_3ps, cs.getPreschentsing3EncliticM()));
        cs.setPreschentsing3EncliticF(setPronounVallader(ValladerConjugationPronouns.pron_r_v_3ps, cs.getPreschentsing3EncliticF()));
        cs.setPreschentplural1Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_v_1pp, cs.getPreschentplural1Enclitic()));
        cs.setPreschentplural2Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_v_2pp, cs.getPreschentplural2Enclitic()));
        cs.setPreschentplural3Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_v_3pp, cs.getPreschentplural3Enclitic()));

        // ENCLITIC IMPERFECT
        cs.setImperfectsing1Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_v_1ps, cs.getImperfectsing1Enclitic()));
        cs.setImperfectsing2Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_v_2ps, cs.getImperfectsing2Enclitic()));
        cs.setImperfectsing3EncliticM(setPronounVallader(ValladerConjugationPronouns.pron_r_v_3ps, cs.getImperfectsing3EncliticM()));
        cs.setImperfectsing3EncliticF(setPronounVallader(ValladerConjugationPronouns.pron_r_v_3ps, cs.getImperfectsing3EncliticF()));
        cs.setImperfectplural1Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_v_1pp, cs.getImperfectplural1Enclitic()));
        cs.setImperfectplural2Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_v_2pp, cs.getImperfectplural2Enclitic()));
        cs.setImperfectplural3Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_v_3pp, cs.getImperfectplural3Enclitic()));

        // ENCLITIC CUNDIZIONAL
        cs.setCundizionalsing1Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_v_1ps, cs.getCundizionalsing1Enclitic()));
        cs.setCundizionalsing2Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_v_2ps, cs.getCundizionalsing2Enclitic()));
        cs.setCundizionalsing3EncliticM(setPronounVallader(ValladerConjugationPronouns.pron_r_v_3ps, cs.getCundizionalsing3EncliticM()));
        cs.setCundizionalsing3EncliticF(setPronounVallader(ValladerConjugationPronouns.pron_r_v_3ps, cs.getCundizionalsing3EncliticF()));
        cs.setCundizionalplural1Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_v_1pp, cs.getCundizionalplural1Enclitic()));
        cs.setCundizionalplural2Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_v_2pp, cs.getCundizionalplural2Enclitic()));
        cs.setCundizionalplural3Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_v_3pp, cs.getCundizionalplural3Enclitic()));

        // ENCLITIC FUTUR
        cs.setFutursing1Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_v_1ps, cs.getFutursing1Enclitic()));
        cs.setFutursing2Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_v_2ps, cs.getFutursing2Enclitic()));
        cs.setFutursing3EncliticM(setPronounVallader(ValladerConjugationPronouns.pron_r_v_3ps, cs.getFutursing3EncliticM()));
        cs.setFutursing3EncliticF(setPronounVallader(ValladerConjugationPronouns.pron_r_v_3ps, cs.getFutursing3EncliticF()));
        cs.setFuturplural1Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_v_1pp, cs.getFuturplural1Enclitic()));
        cs.setFuturplural2Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_v_2pp, cs.getFuturplural2Enclitic()));
        cs.setFuturplural3Enclitic(setPronounVallader(ValladerConjugationPronouns.pron_r_v_3pp, cs.getFuturplural3Enclitic()));
    }

    protected String setPronounVallader(String pronoun, String forms) {
        return setPronounVallader(pronoun, forms, "");
    }


    protected String setPronounVallader(String pronoun, String forms, String suffix) {
        String[] singleForms = forms.split("\\R");
        for (int i = 0; i < singleForms.length; i++) {

            boolean enclosedInBrackets = false;
            if (singleForms[i].charAt(0) == '(' && singleForms[i].charAt(singleForms[i].length()-1) == ')') {
                enclosedInBrackets = true;
                singleForms[i] = singleForms[i].replace("(", "").replace(")", "");
            }

            // 'vus' u 'vus as'
            if (pronoun.startsWith(ValladerConjugationPronouns.pron_2pp) && singleForms[i].endsWith("ivat") && singleForms.length > 1) {
                singleForms[i] = pronoun.replace(ValladerConjugationPronouns.pron_2pp, ValladerConjugationPronouns.pron_2pp_alt) + singleForms[i] + suffix;
            } else {
                singleForms[i] = pronoun + singleForms[i] + suffix;
            }

            if (enclosedInBrackets) {
                singleForms[i] = "(" + singleForms[i] + ")";
            }
        }
        return String.join("\n", singleForms);
    }

    private boolean isYellowCell(Cell cell) {
        CellStyle cellStyle = cell.getCellStyle();
        if (cellStyle == null) {
            return false;
        }

        Color color = cellStyle.getFillForegroundColorColor();
        if (color != null) {
            XSSFColor xssfColor = (XSSFColor) color;
            byte[] rgb = xssfColor.getRGB();

            // Check if the RGB value matches yellow (255, 255, 0)
            if (rgb != null && rgb.length == 3) {
                return rgb[0] == (byte) 255 && rgb[1] == (byte) 255 && rgb[2] == 0;
            }
        }
        return false;
    }

    private void finalizeLemma(Database db, LemmaVersion lv) throws InvalidEntryException {
        lv.setCreatorRole(EditorRole.ADMIN);
        lv.setStatus(LemmaVersion.Status.NEW_ENTRY);
        lv.setVerification(LemmaVersion.Verification.ACCEPTED);
        lv.setInternalId(0);
        lv.setUserId("import@pledarigrond.ch");

        LexEntry le = new LexEntry();
        le.addLemma(lv);
        le.setCurrent(lv);
        le.setNextInternalId(1);

        db.insert(le);
        logger.info("Inserted: " + lv.getLemmaValues().get("RStichwort"));
    }
}
