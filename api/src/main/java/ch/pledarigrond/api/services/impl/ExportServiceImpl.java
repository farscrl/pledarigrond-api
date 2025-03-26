package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.ExportService;
import ch.pledarigrond.common.config.PgEnvironment;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.common.LemmaVersion;
import ch.pledarigrond.common.data.common.LexEntry;
import ch.pledarigrond.common.exception.DatabaseException;
import ch.pledarigrond.common.util.DbSelector;
import ch.pledarigrond.common.util.PronunciationNormalizer;
import ch.pledarigrond.mongodb.core.Converter;
import ch.pledarigrond.mongodb.core.Database;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static ch.pledarigrond.common.data.common.LemmaVersion.RM_INFLECTION_TYPE;

@Service
public class ExportServiceImpl implements ExportService {

    private final Logger logger = LoggerFactory.getLogger(ExportServiceImpl.class);

    @Autowired
    private PgEnvironment pgEnvironment;

    @Override
    public ByteArrayInputStream ladinComposedVerbs(Language language, HttpServletRequest request) throws IOException, DatabaseException {
        String[] headers = {"ID", "RStichwort"};
        Workbook workbook = initializeExcel(language.getName() + " - verbs che cuntegnan in segn da spazi", headers);
        Sheet sheet = workbook.getSheetAt(0);

        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();

        int rowNum = 1;

        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            if (entry.getCurrent() != null && "V".equals(entry.getCurrent().getEntryValue(RM_INFLECTION_TYPE))) {
                LemmaVersion current = entry.getCurrent();
                String RStichwort = current.getLemmaValues().get("RStichwort");
                String RStichwortStripped = RStichwort.trim();
                if (RStichwortStripped.startsWith("as ")) {
                    RStichwortStripped = RStichwortStripped.substring(3).trim();
                } else if (RStichwortStripped.startsWith("s'")) {
                    RStichwortStripped = RStichwortStripped.substring(2).trim();
                }
                if (RStichwortStripped.contains(" ")) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(current.getLexEntryId());
                    row.createCell(1).setCellValue(RStichwort);
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }

        return terminateExcel(workbook);
    }

    @Override
    public ByteArrayInputStream ladinConsonantsOnErrors(Language language, HttpServletRequest request) throws IOException, DatabaseException {
        String[] headers = {"ID", "RStichwort"};
        Workbook workbook = initializeExcel(language.getName() + " - verbs cun 1, 2 u 3 consonants avant la finiziun «-er»", headers);
        Sheet sheet = workbook.getSheetAt(0);

        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();

        int rowNum = 1;

        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            if (entry.getCurrent() != null && "V".equals(entry.getCurrent().getEntryValue(RM_INFLECTION_TYPE))) {
                LemmaVersion current = entry.getCurrent();

                String infinitiv = current.getLemmaValues().get("infinitiv");

                if (infinitiv == null) {
                    continue;
                }

                infinitiv = PronunciationNormalizer.normalizePronunciation(infinitiv);

                if ( infinitiv.endsWith("er") && infinitiv.length() > 2) {
                    boolean isCandidate = false;

                    if (isVocal(infinitiv.charAt(infinitiv.length() - 3))) {
                        continue;
                    }

                    isCandidate = true;

                    if (infinitiv.length() > 3 && !isVocal(infinitiv.charAt(infinitiv.length() - 4))) {
                        if (infinitiv.length() > 4 && !isVocal(infinitiv.charAt(infinitiv.length() - 5))) {
                            if (infinitiv.length() > 5 && !isVocal(infinitiv.charAt(infinitiv.length() - 6))) {
                                isCandidate = false;
                                logger.info("Verb with more thant 3 consonants before -er: " + current.getLexEntryId() + " - " + infinitiv);
                            }
                        }
                    }

                    if (isCandidate) {
                        Row row = sheet.createRow(rowNum++);
                        row.createCell(0).setCellValue(current.getLexEntryId());
                        row.createCell(1).setCellValue(infinitiv);
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }

        return terminateExcel(workbook);
    }

    @Override
    public ByteArrayInputStream ladinEntriesWithCommaAndSlash(Language language, HttpServletRequest request) throws IOException, DatabaseException {
        String[] headers = {"ID", "RStichwort", "RGenus"};
        Workbook workbook = initializeExcel(language.getName() + " - endataziuns che cuntegnan ina comma en il lemma rumantsch ed in slash («/») en il champ genus", headers);
        Sheet sheet = workbook.getSheetAt(0);

        String dbName = DbSelector.getDbNameByLanguage(pgEnvironment, language);
        MongoCursor<Document> cursor = Database.getInstance(dbName).getAll();

        int rowNum = 1;

        while (cursor.hasNext()) {
            DBObject object = new BasicDBObject(cursor.next());
            LexEntry entry = Converter.convertToLexEntry(object);

            LemmaVersion current = entry.getCurrent();

            if (current != null) {
                String RStichwort = current.getLemmaValues().get("RStichwort");
                String RGenus = current.getLemmaValues().get("RGenus");

                if (RStichwort != null && RGenus != null) {

                    if (RStichwort.contains(",") && RGenus.contains("/")) {
                        Row row = sheet.createRow(rowNum++);
                        row.createCell(0).setCellValue(current.getLexEntryId());
                        row.createCell(1).setCellValue(RStichwort);
                        row.createCell(2).setCellValue(RGenus);
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }

        return terminateExcel(workbook);
    }

    private CellStyle getHeaderStyle(Workbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);
        return headerStyle;
    }

    private Workbook initializeExcel(String title, String[] headers) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(title);

        // Create header row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(getHeaderStyle(workbook));
        }

        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        return workbook;
    }

    private ByteArrayInputStream terminateExcel(Workbook workbook) throws IOException {
        try (workbook; ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }

    private static boolean isVocal(char ch) {
        return switch (ch) {
            case 'a', 'e', 'i', 'o', 'u', 'ä', 'ö', 'ü' -> true;
            default -> false;
        };
    }
}
