package ch.pledarigrond.api.services.impl;

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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class ImportServiceImpl implements ImportService {

    @Autowired
    private PgEnvironment pgEnvironment;

    @Autowired
    private LuceneService luceneService;

    @Qualifier("backupInfoHelper")
    @Autowired
    private BackupInfoHelper backupInfoHelper;

    private final Logger logger = LoggerFactory.getLogger(ImportService.class);


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

}
