package ch.pledarigrond.names.util;

import ch.pledarigrond.names.entities.Category;
import ch.pledarigrond.names.entities.Name;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XlsxImport {
    XSSFWorkbook workbook;
    XSSFSheet worksheet;

    public XlsxImport(InputStream inputStream) throws IOException {
        workbook = new XSSFWorkbook(inputStream);
        worksheet = workbook.getSheetAt(0);
    }

    public List<Name> importData() {
        List<Name> names = new ArrayList<>();

        for (int index = 3; index <= worksheet.getPhysicalNumberOfRows(); index++) {
            XSSFRow row = worksheet.getRow(index);

            Name name = new Name(
                    getCellValue(row, 0),
                    getCellValue(row, 1),
                    getCellValue(row, 2),
                    Category.findCategory(getCellValue(row, 3)),
                    getCellValue(row, 4),
                    getCellValue(row, 5),
                    getCellValue(row, 6),
                    getCellValue(row, 7),
                    getCellValue(row, 8)
            );

            names.add(name);
        }

        return names;
    }

    private String getCellValue(XSSFRow row, int column) {
        XSSFCell cell = row.getCell(column);

        if (cell == null) {
            return null;
        }
        return cell.getStringCellValue();
    }
}
