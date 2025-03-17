package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.ExportService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.common.data.dictionary.inflection.InflectionType;
import ch.pledarigrond.common.data.dictionary.inflection.VerbDto;
import ch.pledarigrond.common.util.PronunciationNormalizer;
import ch.pledarigrond.database.services.DictionaryService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ExportServiceImpl implements ExportService {

    private final Logger logger = LoggerFactory.getLogger(ExportServiceImpl.class);

    @Autowired
    private DictionaryService dictionaryService;

    @Override
    public ByteArrayInputStream ladinComposedVerbs(Language language, HttpServletRequest request) throws IOException {
        String[] headers = {"ID", "rmStichwort"};
        Workbook workbook = initializeExcel(language.getName() + " - verbs che cuntegnan in segn da spazi", headers);
        Sheet sheet = workbook.getSheetAt(0);

        AtomicInteger rowNum = new AtomicInteger(0);

        dictionaryService.getStreamForEntries().forEach(entry -> {
            if (entry.getCurrent() != null && entry.getCurrent().getInflection() != null && entry.getCurrent().getInflection().getInflectionType().equals(InflectionType.VERB)) {
                String rmStichwortStripped = entry.getCurrent().getRmStichwort().trim();
                if (rmStichwortStripped.startsWith("as ")) {
                    rmStichwortStripped = rmStichwortStripped.substring(3).trim();
                } else if (rmStichwortStripped.startsWith("s'")) {
                    rmStichwortStripped = rmStichwortStripped.substring(2).trim();
                }
                if (rmStichwortStripped.contains(" ")) {
                    Row row = sheet.createRow(rowNum.getAndIncrement());
                    row.createCell(0).setCellValue(entry.getCurrent().getEntryId());
                    row.createCell(1).setCellValue(entry.getCurrent().getRmStichwort());
                }
            }
        });

        return terminateExcel(workbook);
    }

    @Override
    public ByteArrayInputStream ladinConsonantsOnErrors(Language language, HttpServletRequest request) throws IOException {
        String[] headers = {"ID", "RStichwort"};
        Workbook workbook = initializeExcel(language.getName() + " - verbs cun 1, 2 u 3 consonants avant la finiziun «-er»", headers);
        Sheet sheet = workbook.getSheetAt(0);

        AtomicInteger rowNum = new AtomicInteger(0);

        dictionaryService.getStreamForEntries().forEach(entry -> {
            if (entry.getCurrent() != null && entry.getCurrent().getInflection() != null && entry.getCurrent().getInflection().getInflectionType().equals(InflectionType.VERB)) {
                EntryVersionDto current = entry.getCurrent();
                VerbDto verb = current.getInflection().getVerb();

                if (verb == null) {
                    return;
                }

                String infinitiv = verb.getInfinitiv();

                if (infinitiv == null) {
                    return;
                }

                infinitiv = PronunciationNormalizer.normalizePronunciation(infinitiv);

                if ( infinitiv.endsWith("er") && infinitiv.length() > 2) {
                    boolean isCandidate = false;

                    if (isVocal(infinitiv.charAt(infinitiv.length() - 3))) {
                        return;
                    }

                    isCandidate = true;

                    if (infinitiv.length() > 3 && !isVocal(infinitiv.charAt(infinitiv.length() - 4))) {
                        if (infinitiv.length() > 4 && !isVocal(infinitiv.charAt(infinitiv.length() - 5))) {
                            if (infinitiv.length() > 5 && !isVocal(infinitiv.charAt(infinitiv.length() - 6))) {
                                isCandidate = false;
                                logger.info("Verb with more thant 3 consonants before -er: " + current.getEntryId() + " - " + infinitiv);
                            }
                        }
                    }

                    if (isCandidate) {
                        Row row = sheet.createRow(rowNum.getAndIncrement());
                        row.createCell(0).setCellValue(current.getEntryId());
                        row.createCell(1).setCellValue(infinitiv);
                    }
                }
            }
        });

        return terminateExcel(workbook);
    }

    @Override
    public ByteArrayInputStream ladinEntriesWithCommaAndSlash(Language language, HttpServletRequest request) throws IOException {
        String[] headers = {"ID", "RStichwort", "RGenus"};
        Workbook workbook = initializeExcel(language.getName() + " - endataziuns che cuntegnan ina comma en il lemma rumantsch ed in slash («/») en il champ genus", headers);
        Sheet sheet = workbook.getSheetAt(0);

        AtomicInteger rowNum = new AtomicInteger(0);

        dictionaryService.getStreamForEntries().forEach(entry -> {
            EntryVersionDto current = entry.getCurrent();

            if (current != null) {
                String RStichwort = current.getRmStichwort();
                String RGenus = current.getRmGenus();

                if (RStichwort != null && RGenus != null) {
                    if (RStichwort.contains(",") && RGenus.contains("/")) {
                        Row row = sheet.createRow(rowNum.getAndIncrement());
                        row.createCell(0).setCellValue(current.getEntryId());
                        row.createCell(1).setCellValue(RStichwort);
                        row.createCell(2).setCellValue(RGenus);
                    }
                }
            }
        });

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
