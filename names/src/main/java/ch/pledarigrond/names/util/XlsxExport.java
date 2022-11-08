package ch.pledarigrond.names.util;

import ch.pledarigrond.names.entities.Name;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XlsxExport {

    File tempFile;
    XSSFWorkbook workbook;
    XSSFSheet sheet;

    List<Name> names;

    Integer currentRow = 0;

    public XlsxExport(List<Name> names) throws IOException {
        tempFile = File.createTempFile("name-export-", ".xlsx");
        tempFile.deleteOnExit();

        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Nums");
        this.names = names;
    }

    public File export() throws IOException {
        currentRow = 0;

        createStructure();
        fillInData();
        writeFile();

        return tempFile;
    }

    private void createStructure() {
        Row row = sheet.createRow(currentRow);
        CellStyle titleStyle = workbook.createCellStyle();
        XSSFFont titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeight(16);
        titleStyle.setFont(titleFont);

        CellStyle headerStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        headerStyle.setFont(font);

        Cell cell = row.createCell(0);
        cell.setCellValue("Nums");
        cell.setCellStyle(titleStyle);

        currentRow +=2;

        row = sheet.createRow(currentRow);
        writeCell(row, 0, "ID", headerStyle);
        writeCell(row, 1, "Rumantsch Grischun", headerStyle);
        writeCell(row, 2, "Tudestg", headerStyle);
        writeCell(row, 3, "Categoria", headerStyle);
        writeCell(row, 4, "Sursilvan", headerStyle);
        writeCell(row, 5, "Sutsilvan", headerStyle);
        writeCell(row, 6, "Surmiran", headerStyle);
        writeCell(row, 7, "Puter", headerStyle);
        writeCell(row, 8, "Vallader", headerStyle);
    }

    private void writeCell(Row row, int columnNumber, String value, CellStyle cellStyle) {
        Cell cell = row.createCell(columnNumber);
        cell.setCellValue(value);
        if (cellStyle != null) {
            cell.setCellStyle(cellStyle);
        }
    }

    private void fillInData() {
        for(Name name: names) {
            currentRow++;
            Row row = sheet.createRow(currentRow);

            writeCell(row, 0, name.getId(), null);
            writeCell(row, 1, name.getNameRumantschGrischun(), null);
            writeCell(row, 2, name.getNameGerman(), null);
            writeCell(row, 3, name.getCategory().toString(), null);
            writeCell(row, 4, name.getNameSursilvan(), null);
            writeCell(row, 5, name.getNameSutsilvan(), null);
            writeCell(row, 6, name.getNameSurmiran(), null);
            writeCell(row, 7, name.getNamePuter(), null);
            writeCell(row, 8, name.getNameVallader(), null);
        }
    }

    private void writeFile() throws IOException {
        FileOutputStream out = new FileOutputStream(tempFile);
        workbook.write(out);
        out.close();
    }
}
