package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	
	private static XSSFWorkbook workbook=null;
	private static XSSFSheet sheet=null;
	private static List<Map<String,String>> testDataAllRows=null;
	private static Map<String,String> testData=null;

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

	public static List<Map<String, String>> getTestDataInMap(){

		int lastRowNumber=sheet.getLastRowNum();

		int lastColNumber=sheet.getRow(0).getLastCellNum();

		List list=new ArrayList();

		for(int i=0;i<lastColNumber;i++){
			Row row=sheet.getRow(0);
			Cell cell=row.getCell(i);
			String rowHeader=cell.getStringCellValue().trim();
			list.add(rowHeader);

		}
		testDataAllRows=new ArrayList<Map<String, String>>();
		for(int j=1;j<=lastRowNumber;j++){

			Row row=sheet.getRow(j);
			testData=new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
			for(int k=0;k<lastColNumber;k++){
				Cell cell=row.getCell(k);
				String colValue=cell.getStringCellValue().trim();
				testData.put((String) list.get(k),colValue);
			}
			testDataAllRows.add(testData);
		}
		return testDataAllRows;
	}
	

}
