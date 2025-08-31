package util;

import java.io.FileInputStream;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ControlSheetUtil {
	public static HashMap<String, String> getControlData(String filePath, String sheetName) throws Exception {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheet(sheetName);

        HashMap<String,String> controlMap = new HashMap<String,String>();

        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rows; i++) {
            Row row = sheet.getRow(i);
            String testCase = row.getCell(0).toString();
            String runmode = row.getCell(1).toString();
            controlMap.put(testCase, runmode);
        }
        wb.close();
        return controlMap;
    }

}
