package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	
	private static XSSFWorkbook workbook=null;
	private static XSSFSheet sheet=null;

	public ExcelUtil(String excelPath, String sheetName) {

		try {
			FileInputStream fis=new FileInputStream(excelPath);
			workbook=new XSSFWorkbook(fis);	 
			sheet=workbook.getSheet(sheetName);

		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int getRowCount() {

		return sheet.getPhysicalNumberOfRows();
	}

	public static int getColumnCount(int rowNum) {

		return sheet.getRow(rowNum).getPhysicalNumberOfCells();
	}

	public String getCellDataString(int rownum, int colnum) {

		String cellvalue= sheet.getRow(rownum).getCell(colnum).getStringCellValue();	
		return cellvalue;
	}

	public static double getCellDataNumber(int rownum, int colnum) {

		return sheet.getRow(rownum).getCell(colnum).getNumericCellValue();	
	}
	
	

}
