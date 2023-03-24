package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.dtos.LadinDto;
import ch.pledarigrond.api.dtos.VerbDto;
import ch.pledarigrond.api.services.AdminService;
import ch.pledarigrond.api.services.ImportService;
import ch.pledarigrond.api.services.LuceneService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.EditorRole;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.data.lucene.IndexStatistics;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.common.exception.NoDatabaseAvailableException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.inflection.generation.puter.PuterAdjectiveGenerator;
import ch.pledarigrond.lucene.exceptions.IndexException;
import ch.pledarigrond.mongodb.core.Database;
import ch.pledarigrond.mongodb.exceptions.InvalidEntryException;
import ch.pledarigrond.mongodb.model.BackupInfos;
import ch.pledarigrond.mongodb.model.DatabaseStatistics;
import ch.pledarigrond.mongodb.model.DictionaryStatistics;
import ch.pledarigrond.mongodb.util.backup.BackupInfoHelper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.security.NoSuchAlgorithmException;
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
        if (language == Language.PUTER) {
            verbs = loadVerbs("verbs_puter_total.xlsx");
            lemmas = loadLemmas("puter.xlsx");
        } else {
            verbs = loadVerbs("verbs_vallader_total.xlsx");
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
                    addVerbData(lemmaVersion, verbs.get(l.getVerbId()));
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

    private Map<String, VerbDto> loadVerbs(String fileName) throws IOException {
        FileInputStream source = new FileInputStream(new File("api/src/main/resources/ladin/" + fileName));

        Map<String, VerbDto> verbs = new HashMap<>();

        Workbook workbook = new XSSFWorkbook(source);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 4; i <= sheet.getLastRowNum(); i++) {
            VerbDto v = new VerbDto();
            Row row = sheet.getRow(i);
            String id = row.getCell(0).getStringCellValue()
                    .replace("ö", "o").replace("ü", "u");
            v.setVerbId(id);

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

        // static data
        lv.getLemmaValues().put("user_comment", "Import " + date);
        lv.setCreatorRole(EditorRole.ADMIN);
        lv.setStatus(LemmaVersion.Status.NEW_ENTRY);
        lv.setVerification(LemmaVersion.Verification.ACCEPTED);
        lv.setInternalId(0);
        lv.setUserId("import@pledarigrond.ch");

        return lv;
    }

    private void addVerbData(LemmaVersion lemmaVersion, VerbDto verbDto) {

    }
}
