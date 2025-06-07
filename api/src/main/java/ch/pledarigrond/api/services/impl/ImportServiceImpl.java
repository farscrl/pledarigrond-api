package ch.pledarigrond.api.services.impl;

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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Service
public class ImportServiceImpl implements ImportService {

    private final Logger logger = LoggerFactory.getLogger(ImportServiceImpl.class);

    @Autowired
    private PgEnvironment pgEnvironment;

    @Override
    public boolean importXlsSursilvan(Language language, HttpServletRequest request) throws IOException, InvalidEntryException, NoDatabaseAvailableException {
        Database db = Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language));

        StandardMultipartHttpServletRequest dmhsRequest = (StandardMultipartHttpServletRequest) request;
        MultipartFile multipartFile = dmhsRequest.getFile("file");

        String originalFilename = multipartFile.getOriginalFilename();
        logger.warn("Importing XLSX file: {}", originalFilename);

        return processWorkbook(multipartFile.getInputStream(), db);
    }

    public boolean importZipSursilvan(Language language, HttpServletRequest request) throws IOException, NoDatabaseAvailableException {
        Database db = Database.getInstance(DbSelector.getDbNameByLanguage(pgEnvironment, language));

        StandardMultipartHttpServletRequest dmhsRequest = (StandardMultipartHttpServletRequest) request;
        MultipartFile zipFile = dmhsRequest.getFile("file");

        File tempZipFile = File.createTempFile("zip-upload-", ".zip");
        zipFile.transferTo(tempZipFile);

        try (ZipFile zip = new ZipFile(tempZipFile)) {
            Enumeration<? extends ZipEntry> entries = zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (!entry.isDirectory() && entry.getName().endsWith(".xlsx") && !entry.getName().endsWith("00_Dublettas.xlsx")) {
                    logger.warn("Importing file from ZIP: {}", entry.getName());

                    try (InputStream xlsxInput = zip.getInputStream(entry)) {
                        boolean success = processWorkbook(xlsxInput, db);
                        if (!success) {
                            logger.error("Import failed for file: {}", entry.getName());
                            return false;
                        }
                    } catch (InvalidEntryException e) {
                        logger.error("Import failed for file: {}", entry.getName());
                        return false;
                    }
                }
            }
        } finally {
            tempZipFile.delete();
        }

        return true;
    }

    private boolean processWorkbook(InputStream xlsxInputStream, Database db) throws IOException, InvalidEntryException {
        Workbook workbook = new XSSFWorkbook(xlsxInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        // check if format is correct
        int i = 0;
        Row row = sheet.getRow(i);
        if (!row.getCell(0).getRichStringCellValue().getString().equals("RStichwort")) throw new RuntimeException("Invalid format: RStichwort not found in A1");
        if (!row.getCell(1).getRichStringCellValue().getString().equals("RGenus")) throw new RuntimeException("Invalid format: RGenus not found in B1");
        if (!row.getCell(2).getRichStringCellValue().getString().equals("RGrammatik")) throw new RuntimeException("Invalid format: RGrammatik not found in C1");
        if (!row.getCell(3).getRichStringCellValue().getString().equals("RPhonetics")) throw new RuntimeException("Invalid format: RPhonetics not found in D1");
        if (!row.getCell(4).getRichStringCellValue().getString().equals("RSemantikVis")) throw new RuntimeException("Invalid format: RSemantikVis not found in E1");
        if (!row.getCell(5).getRichStringCellValue().getString().equals("RSemantik")) throw new RuntimeException("Invalid format: RSemantik not found in F1");
        if (!row.getCell(6).getRichStringCellValue().getString().equals("REtymologie")) throw new RuntimeException("Invalid format: REtymologie not found in G1");
        if (!row.getCell(7).getRichStringCellValue().getString().equals("RSynonym")) throw new RuntimeException("Invalid format: RSynonym not found in H1");
        if (!row.getCell(8).getRichStringCellValue().getString().equals("DStichwort")) throw new RuntimeException("Invalid format: DStichwort not found in I1");
        if (!row.getCell(9).getRichStringCellValue().getString().equals("DGenus")) throw new RuntimeException("Invalid format: DGenus not found in J1");
        if (!row.getCell(10).getRichStringCellValue().getString().equals("DGrammatik")) throw new RuntimeException("Invalid format: DGrammatik not found in K1");
        if (!row.getCell(11).getRichStringCellValue().getString().equals("DSemantik")) throw new RuntimeException("Invalid format: DSemantik not found in L1");
        if (!row.getCell(12).getRichStringCellValue().getString().equals("categories")) throw new RuntimeException("Invalid format: categories not found in M1");

        logger.info("File format seems correct, continuing with import");

        LemmaVersion lv = null;

        for (i = 1; i <= sheet.getLastRowNum(); i++) {
            logger.warn("Processing row: {}", i);

            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }

            if (isYellowCell(row.getCell(0))) {
                if (row.getCell(0) != null && row.getCell(8) != null) {
                    String exampleRm = row.getCell(0).getRichStringCellValue().getString();
                    String exampleDe = row.getCell(8).getRichStringCellValue().getString();
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
                    String RStichwort = row.getCell(0).getRichStringCellValue().getString();
                    if (RStichwort.startsWith("# ")) {
                        RStichwort = RStichwort.substring(2);
                        lv.getLemmaValues().put("RRedirect", RStichwort);
                        lv.getLemmaValues().put("RStichwort", "cf. " + RStichwort);
                    } else {
                        lv.getLemmaValues().put("RStichwort", RStichwort);
                    }
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
                    lv.getLemmaValues().put("RSubsemantik", row.getCell(4).getRichStringCellValue().getString());
                }
                if (row.getCell(5) != null) {
                    lv.getLemmaValues().put("RSemantik", row.getCell(5).getRichStringCellValue().getString());
                }
                if (row.getCell(6) != null) {
                    lv.getLemmaValues().put("REtymologie", row.getCell(6).getRichStringCellValue().getString());
                }
                if (row.getCell(7) != null) {
                    lv.getLemmaValues().put("RSynonym", row.getCell(7).getRichStringCellValue().getString());
                }
                if (row.getCell(8) != null) {
                    String DStichwort = row.getCell(8).getRichStringCellValue().getString();
                    if (DStichwort.startsWith("# ")) {
                        DStichwort = DStichwort.substring(2);
                        lv.getLemmaValues().put("DRedirect", DStichwort);
                        lv.getLemmaValues().put("DStichwort", "cf. " + DStichwort);
                    } else {
                        lv.getLemmaValues().put("DStichwort", DStichwort);
                    }
                }
                if (row.getCell(9) != null) {
                    lv.getLemmaValues().put("DGenus", row.getCell(9).getRichStringCellValue().getString());
                }
                if (row.getCell(10) != null) {
                    lv.getLemmaValues().put("DGrammatik", row.getCell(10).getRichStringCellValue().getString());
                }
                if (row.getCell(11) != null) {
                    lv.getLemmaValues().put("DSubsemantik", row.getCell(11).getRichStringCellValue().getString());
                }
                if (row.getCell(12) != null) {
                    lv.getLemmaValues().put("categories", row.getCell(12).getRichStringCellValue().getString());
                }
            }
        }

        if (lv != null) {
            finalizeLemma(db, lv);
        }

        return true;
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
