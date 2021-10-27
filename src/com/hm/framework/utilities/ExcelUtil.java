package com.hm.framework.utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
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


    // Create Constructor
    public ExcelUtil(String ExcelSheetPath ) throws IOException {

        workbook = new XSSFWorkbook(( new FileInputStream(new File(ExcelSheetPath))));
        wrkSheet = workbook.getSheet("Sheet1");
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





}
