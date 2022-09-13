package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class to load an external .xlsx data file,
 * read the data and pass to the StepDefs code
 */
public class ExcelFileReader {

    public static Map<Integer, List<Object>> readExcelFile(String filePath, String sheetName) {

        Map<Integer, List<Object>> allRows = new HashMap<>();
        File file = new File(filePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            int noOFRows = sheet.getLastRowNum() - sheet.getFirstRowNum();
            Cell cell;
            CellType cellType;
            for (int i = 0; i <= noOFRows; i++) {
                Row row = sheet.getRow(i);
                List<Object> rowData = new ArrayList<>();
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    cellType = determineCellTypes(cell);
                    if (cellType.equals(CellType.STRING))
                        rowData.add(cell.getStringCellValue());
                    else if (cellType.equals(CellType.NUMERIC)) {
                        rowData.add((int) cell.getNumericCellValue());
                    }
                }
                allRows.put(i, rowData);
            }
            fileInputStream.close();
            return allRows;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static CellType determineCellTypes(Cell cell) {
        return cell.getCellType().equals(CellType.FORMULA)
                ? cell.getCachedFormulaResultType() : cell.getCellType();

    }
}
