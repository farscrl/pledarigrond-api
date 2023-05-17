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
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.mongodb.exceptions.InvalidEntryException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.poi.ss.usermodel.CellType.STRING;
import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;

@Service
public class ImportServiceImpl implements ImportService {

    private final Logger logger = LoggerFactory.getLogger(ImportService.class);

    @Autowired
    private PgEnvironment pgEnvironment;

    private String date = "";

    @Override
    public boolean importXlsSursilvan(Language language, HttpServletRequest request) throws IOException, InvalidEntryException, NoDatabaseAvailableException, JAXBException, XMLStreamException {
        Database db = Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language));

        StandardMultipartHttpServletRequest dmhsRequest = (StandardMultipartHttpServletRequest) request;
        MultipartFile multipartFile = dmhsRequest.getFile("file");

        Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }

            if (i == 1) {
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
                continue;
            }

            LemmaVersion lv = new LemmaVersion();
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
        }

        return true;
    }

    public boolean importLadinData(Language language, HttpServletRequest request) throws IOException, InvalidEntryException, NoDatabaseAvailableException, JAXBException, XMLStreamException {
        Database db = Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language));

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        date = df.format(new Date());

        Map<String, VerbDto> verbs;
        List<LadinDto> lemmas;
        boolean isPuter;
        if (language == Language.PUTER) {
            isPuter = true;
            verbs = loadVerbs("puter_verbs.xlsx", isPuter);
            lemmas = loadLemmas("puter.xlsx");
        } else {
            isPuter = false;
            verbs = loadVerbs("vallader_verbs.xlsx", isPuter);
            lemmas = loadLemmas("vallader.xlsx");
        }

        Set<String> verbsMancants = new TreeSet<>();
        lemmas.forEach(l -> {
            LemmaVersion lemmaVersion = getLemmaVersion(l);

            if (!l.getVerbId().equals("")) {
                if (!verbs.containsKey(l.getVerbId())) {
                    verbsMancants.add(l.getVerbId());
                    logger.error("ID not found: " + l.getVerbId());
                } else {
                    addVerbData(lemmaVersion, verbs.get(l.getVerbId()), isPuter);
                }
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

        logger.error(verbsMancants.toString());

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

            v.setGerundiumPrep(row.getCell(7, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setGerundium(row.getCell(8, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));

            v.setPreschentsing1Prep(row.getCell(9, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setPreschentsing1(row.getCell(10, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setPreschentsing2Prep(row.getCell(11, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setPreschentsing2(row.getCell(12, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setPreschentsing3Prep(row.getCell(13, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setPreschentsing3(row.getCell(14, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setPreschentplural1Prep(row.getCell(15, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setPreschentplural1(row.getCell(16, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setPreschentplural2Prep(row.getCell(17, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setPreschentplural2(row.getCell(18, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setPreschentplural3Prep(row.getCell(19, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setPreschentplural3(row.getCell(20, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));

            v.setImperfectsing1Prep(row.getCell(21, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperfectsing1(row.getCell(22, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperfectsing2Prep(row.getCell(23, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperfectsing2(row.getCell(24, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperfectsing3Prep(row.getCell(25, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperfectsing3(row.getCell(26, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperfectplural1Prep(row.getCell(27, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperfectplural1(row.getCell(28, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperfectplural2Prep(row.getCell(29, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperfectplural2(row.getCell(30, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperfectplural3Prep(row.getCell(31, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperfectplural3(row.getCell(32, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));

            v.setFutursing1Prep(row.getCell(33, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setFutursing1(row.getCell(34, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setFutursing2Prep(row.getCell(35, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setFutursing2(row.getCell(36, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setFutursing3Prep(row.getCell(37, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setFutursing3(row.getCell(38, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setFuturplural1Prep(row.getCell(39, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setFuturplural1(row.getCell(40, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setFuturplural2Prep(row.getCell(41, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setFuturplural2(row.getCell(42, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setFuturplural3Prep(row.getCell(43, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setFuturplural3(row.getCell(44, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));

            v.setConjunctivsing1Prep(row.getCell(45, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setConjunctivsing1(row.getCell(46, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setConjunctivsing2Prep(row.getCell(47, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setConjunctivsing2(row.getCell(48, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setConjunctivsing3Prep(row.getCell(49, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setConjunctivsing3(row.getCell(50, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setConjunctivplural1Prep(row.getCell(51, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setConjunctivplural1(row.getCell(52, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setConjunctivplural2Prep(row.getCell(53, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setConjunctivplural2(row.getCell(54, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setConjunctivplural3Prep(row.getCell(55, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setConjunctivplural3(row.getCell(56, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));

            v.setCundizionalsing1Prep(row.getCell(57, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setCundizionalsing1(row.getCell(58, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setCundizionalsing2Prep(row.getCell(59, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setCundizionalsing2(row.getCell(60, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setCundizionalsing3Prep(row.getCell(61, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setCundizionalsing3(row.getCell(62, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setCundizionalplural1Prep(row.getCell(63, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setCundizionalplural1(row.getCell(64, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setCundizionalplural2Prep(row.getCell(65, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setCundizionalplural2(row.getCell(66, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setCundizionalplural3Prep(row.getCell(67, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setCundizionalplural3(row.getCell(68, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));

            v.setImperativ1Prep(row.getCell(69, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperativ1(row.getCell(70, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperativ2Prep(row.getCell(71, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperativ2(row.getCell(72, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperativ3Prep(row.getCell(73, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperativ3(row.getCell(74, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperativ4Prep(row.getCell(75, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperativ4(row.getCell(76, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperativ5Prep(row.getCell(77, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperativ5(row.getCell(78, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperativ6Prep(row.getCell(79, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
            v.setImperativ6(row.getCell(80, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));

            if (isPuter) {
                v.setFuturdubitativsing1Prep(row.getCell(81, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
                v.setFuturdubitativsing1(row.getCell(82, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
                v.setFuturdubitativsing2Prep(row.getCell(83, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
                v.setFuturdubitativsing2(row.getCell(84, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
                v.setFuturdubitativsing3Prep(row.getCell(85, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
                v.setFuturdubitativsing3(row.getCell(86, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
                v.setFuturdubitativplural1Prep(row.getCell(87, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
                v.setFuturdubitativplural1(row.getCell(88, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
                v.setFuturdubitativplural2Prep(row.getCell(89, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
                v.setFuturdubitativplural2(row.getCell(90, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
                v.setFuturdubitativplural3Prep(row.getCell(91, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
                v.setFuturdubitativplural3(row.getCell(92, CREATE_NULL_AS_BLANK).getStringCellValue().replace("&nbsp;", " "));
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

    private LemmaVersion getLemmaVersion(LadinDto ladinDto) {
        LemmaVersion lv = new LemmaVersion();

        // TODO: remove?
        lv.getLemmaValues().put("EinschrkR", ladinDto.getEinschrkR());
        lv.getLemmaValues().put("Grammatik_kategorieD", ladinDto.getGrammKatD());

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

        return lv;
    }

    private void addVerbData(LemmaVersion lemmaVersion, VerbDto verbDto, boolean isPuter) {
        lemmaVersion.getLemmaValues().put("infinitiv", verbDto.getInfinitiv());

        lemmaVersion.getLemmaValues().put("preschentsing1", verbDto.getPreschentsing1Prep() + verbDto.getPreschentsing1());
        lemmaVersion.getLemmaValues().put("preschentsing2", verbDto.getPreschentsing2Prep() + verbDto.getPreschentsing2());
        lemmaVersion.getLemmaValues().put("preschentsing3", verbDto.getPreschentsing3Prep() + verbDto.getPreschentsing3());
        lemmaVersion.getLemmaValues().put("preschentplural1", verbDto.getPreschentplural1Prep() + verbDto.getPreschentplural1());
        lemmaVersion.getLemmaValues().put("preschentplural2", verbDto.getPreschentplural2Prep() + verbDto.getPreschentplural2());
        lemmaVersion.getLemmaValues().put("preschentplural3", verbDto.getPreschentplural3Prep() + verbDto.getPreschentplural3());

        lemmaVersion.getLemmaValues().put("imperfectsing1", verbDto.getImperfectsing1Prep() + verbDto.getImperfectsing1());
        lemmaVersion.getLemmaValues().put("imperfectsing2", verbDto.getImperfectsing2Prep() + verbDto.getImperfectsing2());
        lemmaVersion.getLemmaValues().put("imperfectsing3", verbDto.getImperfectsing3Prep() + verbDto.getImperfectsing3());
        lemmaVersion.getLemmaValues().put("imperfectplural1", verbDto.getImperfectplural1Prep() + verbDto.getImperfectplural1());
        lemmaVersion.getLemmaValues().put("imperfectplural2", verbDto.getImperfectplural2Prep() + verbDto.getImperfectplural2());
        lemmaVersion.getLemmaValues().put("imperfectplural3", verbDto.getImperfectplural3Prep() + verbDto.getImperfectplural3());

        lemmaVersion.getLemmaValues().put("conjunctivsing1", verbDto.getConjunctivsing1Prep() + verbDto.getConjunctivsing1());
        lemmaVersion.getLemmaValues().put("conjunctivsing2", verbDto.getConjunctivsing2Prep() + verbDto.getConjunctivsing2());
        lemmaVersion.getLemmaValues().put("conjunctivsing3", verbDto.getConjunctivsing3Prep() + verbDto.getConjunctivsing3());
        lemmaVersion.getLemmaValues().put("conjunctivplural1", verbDto.getConjunctivplural1Prep() + verbDto.getConjunctivplural1());
        lemmaVersion.getLemmaValues().put("conjunctivplural2", verbDto.getConjunctivplural2Prep() + verbDto.getConjunctivplural2());
        lemmaVersion.getLemmaValues().put("conjunctivplural3", verbDto.getConjunctivplural3Prep() + verbDto.getConjunctivplural3());

        lemmaVersion.getLemmaValues().put("cundizionalsing1", verbDto.getCundizionalsing1Prep() + verbDto.getCundizionalsing1());
        lemmaVersion.getLemmaValues().put("cundizionalsing2", verbDto.getCundizionalsing2Prep() + verbDto.getCundizionalsing2());
        lemmaVersion.getLemmaValues().put("cundizionalsing3", verbDto.getCundizionalsing3Prep() + verbDto.getCundizionalsing3());
        lemmaVersion.getLemmaValues().put("cundizionalplural1", verbDto.getCundizionalplural1Prep() + verbDto.getCundizionalplural1());
        lemmaVersion.getLemmaValues().put("cundizionalplural2", verbDto.getCundizionalplural2Prep() + verbDto.getCundizionalplural2());
        lemmaVersion.getLemmaValues().put("cundizionalplural3", verbDto.getCundizionalplural3Prep() + verbDto.getCundizionalplural3());

        lemmaVersion.getLemmaValues().put("participperfectms", verbDto.getParticipperfectms());
        lemmaVersion.getLemmaValues().put("participperfectfs", verbDto.getParticipperfectfs());
        lemmaVersion.getLemmaValues().put("participperfectmp", verbDto.getParticipperfectmp());
        lemmaVersion.getLemmaValues().put("participperfectfp", verbDto.getParticipperfectfp());

        lemmaVersion.getLemmaValues().put("imperativ1", verbDto.getImperativ1Prep() + verbDto.getImperativ1());
        lemmaVersion.getLemmaValues().put("imperativ2", verbDto.getImperativ2Prep() + verbDto.getImperativ2());
        lemmaVersion.getLemmaValues().put("imperativ3", verbDto.getImperativ3Prep() + verbDto.getImperativ3());
        lemmaVersion.getLemmaValues().put("imperativ4", verbDto.getImperativ4Prep() + verbDto.getImperativ4());
        lemmaVersion.getLemmaValues().put("imperativ5", verbDto.getImperativ5Prep() + verbDto.getImperativ5());
        lemmaVersion.getLemmaValues().put("imperativ6", verbDto.getImperativ6Prep() + verbDto.getImperativ6());

        lemmaVersion.getLemmaValues().put("gerundium", verbDto.getGerundiumPrep() + verbDto.getGerundium());

        lemmaVersion.getLemmaValues().put("futursing1", verbDto.getFutursing1Prep() + verbDto.getFutursing1());
        lemmaVersion.getLemmaValues().put("futursing2", verbDto.getFutursing2Prep() + verbDto.getFutursing2());
        lemmaVersion.getLemmaValues().put("futursing3", verbDto.getFutursing3Prep() + verbDto.getFutursing3());
        lemmaVersion.getLemmaValues().put("futurplural1", verbDto.getFuturplural1Prep() + verbDto.getFuturplural1());
        lemmaVersion.getLemmaValues().put("futurplural2", verbDto.getFuturplural2Prep() + verbDto.getFuturplural2());
        lemmaVersion.getLemmaValues().put("futurplural3", verbDto.getFuturplural3Prep() + verbDto.getFuturplural3());

        if (isPuter) {
            lemmaVersion.getLemmaValues().put("futurdubitativsing1", verbDto.getFuturdubitativsing1Prep() + verbDto.getFuturdubitativsing1());
            lemmaVersion.getLemmaValues().put("futurdubitativsing2", verbDto.getFuturdubitativsing2Prep() + verbDto.getFuturdubitativsing2());
            lemmaVersion.getLemmaValues().put("futurdubitativsing3", verbDto.getFuturdubitativsing3Prep() + verbDto.getFuturdubitativsing3());
            lemmaVersion.getLemmaValues().put("futurdubitativplural1", verbDto.getFuturdubitativplural1Prep() + verbDto.getFuturdubitativplural1());
            lemmaVersion.getLemmaValues().put("futurdubitativplural2", verbDto.getFuturdubitativplural2Prep() + verbDto.getFuturdubitativplural2());
            lemmaVersion.getLemmaValues().put("futurdubitativplural3", verbDto.getFuturdubitativplural3Prep() + verbDto.getFuturdubitativplural3());
        }

        lemmaVersion.getLemmaValues().put("RInflectionType", "V");
        lemmaVersion.getLemmaValues().put("composedWith", verbDto.getComposedWith());
    }
}
