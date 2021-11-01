package com.hm.framework.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExcelUtil {


    // Open Excel Sheet
    //Read Cell data
    //Store In-Memory Collection
    //Get Cell data

    static XSSFSheet wrkSheet;
    static XSSFWorkbook workbook =null;
    static Hashtable dict = new Hashtable();
    private static List<Map<String, String>> testDataAllRows=null;
    private static Map<String, String> testData=null;


    // Create Constructor
    public ExcelUtil(String ExcelSheetPath ) throws IOException {

        workbook = new XSSFWorkbook(( new FileInputStream(new File(ExcelSheetPath))));
        wrkSheet = workbook.getSheet("Sheet2");
        ColumnDictionary();
    }

    //Returns the Number of Sheet present in Workbook
    public static List<String> getSheetNames(String ExcelSheetPath) throws Exception {

        File file =  new File(ExcelSheetPath);

        try (Workbook book = WorkbookFactory.create(file)) {

            return IntStream.range(0, book.getNumberOfSheets())
                    .mapToObj(book::getSheetAt)
                    .map(Sheet::getSheetName)
                    .collect(Collectors.toList());
        }
    }

    //Returns the Number of Rows
    public static int RowCount()
    {

        return wrkSheet.getPhysicalNumberOfRows();
    }
    //Returns the Cell value by taking row and Column values as argument
    private static String ReadCell(int columnIndex,int rowIndex)
    {
       // return wrkSheet.getCell(column,row).getContents();
        return wrkSheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue();
    }

    public static String ReadCell(String columnName, int rowNumber)
    {

        return ReadCell(GetCell(columnName), rowNumber);
    }

    //Create Column Dictionary to hold all the Column Names
    private static void ColumnDictionary()
    {
        //Iterate through all the columns in the Excel sheet and store the value in Hashtable
        for(int col=0;col < wrkSheet.getRow(0).getPhysicalNumberOfCells();col++)
        {
            dict.put(ReadCell(col,0), col);
        }
    }

    //Read Column Names and get Cell value
    private static int GetCell(String colName)
    {
        try {
            int value;
            value = ((Integer) dict.get(colName)).intValue();
            return value;
        } catch (NullPointerException e) {
            return (0);

        }
    }

    public static List<Map<String, String>> getTestDataInMap(){

        int lastRowNumber=wrkSheet.getLastRowNum();

        int lastColNumber=wrkSheet.getRow(0).getLastCellNum();

        List list=new ArrayList();

        for(int i=0;i<lastColNumber;i++){
            Row row=wrkSheet.getRow(0);
            Cell cell=row.getCell(i);
            String rowHeader=cell.getStringCellValue().trim();
            list.add(rowHeader);

        }
        testDataAllRows=new ArrayList<Map<String, String>>();
        for(int j=1;j<=lastRowNumber;j++){

            Row row=wrkSheet.getRow(j);
            testData=new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
            for(int k=0;k<lastColNumber;k++){
                Cell cell=row.getCell(k);
                String colValue=cell.getStringCellValue().trim();
                testData.put((String) list.get(k), colValue);
            }
            testDataAllRows.add(testData);
        }
        return testDataAllRows;
    }





}
