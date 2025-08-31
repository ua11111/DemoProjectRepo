package util;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	public static String[][] getSheetData(String filePath, String sheetName) throws Exception {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheet(sheetName);

        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getPhysicalNumberOfCells();

        String[][] data = new String[rows-1][cols];
        for (int i = 1; i < rows; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < cols; j++) {
                Cell cell = row.getCell(j);
                data[i-1][j] = (cell == null) ? "" : cell.toString();
            }
        }
        wb.close();
        return data;
    }
}
