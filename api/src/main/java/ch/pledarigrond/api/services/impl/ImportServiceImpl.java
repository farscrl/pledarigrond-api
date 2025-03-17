package ch.pledarigrond.api.services.impl;

import ch.pledarigrond.api.services.ImportService;
import ch.pledarigrond.common.data.common.Language;
import ch.pledarigrond.common.data.dictionary.EntryVersionDto;
import ch.pledarigrond.common.data.dictionary.ExampleDto;
import ch.pledarigrond.database.services.DictionaryService;
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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Service
public class ImportServiceImpl implements ImportService {

    private final Logger logger = LoggerFactory.getLogger(ImportServiceImpl.class);

    @Autowired
    private DictionaryService dictionaryService;

    @Override
    public boolean importXlsSursilvan(Language language, HttpServletRequest request) throws IOException {
        StandardMultipartHttpServletRequest dmhsRequest = (StandardMultipartHttpServletRequest) request;
        MultipartFile multipartFile = dmhsRequest.getFile("file");

        String originalFilename = multipartFile.getOriginalFilename();
        logger.warn("Importing XLSX file: {}", originalFilename);

        return processWorkbook(multipartFile.getInputStream());
    }

    public boolean importZipSursilvan(Language language, HttpServletRequest request) throws IOException {

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
                        boolean success = processWorkbook(xlsxInput);
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

    private boolean processWorkbook(InputStream xlsxInputStream) throws IOException, InvalidEntryException {
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

        EntryVersionDto ev = null;

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
                    if (ev == null) {
                        throw new RuntimeException("No lemma found for example: " + exampleRm);
                    }

                    if (ev.getExamples() == null) {
                        ev.setExamples(new ArrayList<>());
                    }
                    ExampleDto newExample = new ExampleDto();
                    newExample.setRm(exampleRm);
                    newExample.setDe(exampleDe);
                    ev.getExamples().add(newExample);

                    logger.info("Added example: «" + exampleRm + "» to «" + ev.getRmStichwort() + "»");
                }
            } else {
                if (ev != null) {
                    finalizeVersion(ev);
                }
                ev = new EntryVersionDto();
                if (row.getCell(0) != null) {
                    String rmStichwort = row.getCell(0).getRichStringCellValue().getString();
                    if (rmStichwort.startsWith("# ")) {
                        rmStichwort = rmStichwort.substring(2);
                        ev.setRmRedirect(rmStichwort);
                        ev.setRmStichwort("cf. " + rmStichwort);
                    } else {
                        ev.setRmStichwort(rmStichwort);
                    }
                }
                if (row.getCell(1) != null) {
                    ev.setRmGenus(row.getCell(1).getRichStringCellValue().getString());
                }
                if (row.getCell(2) != null) {
                    ev.setRmGrammatik(row.getCell(2).getRichStringCellValue().getString());
                }
                if (row.getCell(3) != null) {
                    ev.setRmPhonetics(row.getCell(3).getRichStringCellValue().getString());
                }
                if (row.getCell(4) != null) {
                    ev.setRmSubsemantik(row.getCell(4).getRichStringCellValue().getString());
                }
                if (row.getCell(5) != null) {
                    ev.setRmSemantik(row.getCell(5).getRichStringCellValue().getString());
                }
                if (row.getCell(6) != null) {
                    ev.setRmEtymologie(row.getCell(6).getRichStringCellValue().getString());
                }
                if (row.getCell(7) != null) {
                    ev.setRmSynonym(row.getCell(7).getRichStringCellValue().getString());
                }
                if (row.getCell(8) != null) {
                    String deStichwort = row.getCell(8).getRichStringCellValue().getString();
                    if (deStichwort.startsWith("# ")) {
                        deStichwort = deStichwort.substring(2);
                        ev.setDeRedirect(deStichwort);
                        ev.setDeStichwort("cf. " + deStichwort);
                    } else {
                        ev.setDeStichwort(deStichwort);
                    }
                }
                if (row.getCell(9) != null) {
                    ev.setDeGenus(row.getCell(9).getRichStringCellValue().getString());
                }
                if (row.getCell(10) != null) {
                    ev.setDeGrammatik(row.getCell(10).getRichStringCellValue().getString());
                }
                if (row.getCell(11) != null) {
                    ev.setDeSubsemantik(row.getCell(11).getRichStringCellValue().getString());
                }
                if (row.getCell(12) != null) {
                    ev.setCategories(row.getCell(12).getRichStringCellValue().getString());
                }
            }
        }

        if (ev != null) {
            finalizeVersion(ev);
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

    private void finalizeVersion(EntryVersionDto ev) {
        dictionaryService.addEntry(ev, false, "import@pledarigrond.ch");
        logger.info("Inserted: {}", ev.getRmStichwort());
    }
}
