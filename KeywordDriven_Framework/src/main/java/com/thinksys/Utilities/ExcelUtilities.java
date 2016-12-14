package com.thinksys.Utilities;

import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities extends Constants {
	
	private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;

//This method is to set the File path and to open the Excel file
//Pass Excel Path and SheetName as Arguments to this method
public static int setExcelFilePath(String Path,String SheetName) throws Exception {
        FileInputStream ExcelFile = new FileInputStream(Path);
        int iRowNum=0;
        System.out.println("ABle to acess Excel file");
        ExcelWBook = new XSSFWorkbook(ExcelFile);
        ExcelWSheet = ExcelWBook.getSheet(SheetName);
        
        for(iRowNum=0;iRowNum<=ExcelWSheet.getLastRowNum();iRowNum++)
        {
        	 Row r = ExcelWSheet.getRow(iRowNum);
             if (r != null) {
            	// System.out.println(r.toString());
            	 System.out.println(iRowNum);
             }
             else
             {
            	 System.out.println("TOtal value of Rows are:- "+iRowNum);
             }
             
        }
        return iRowNum-1;
        
}

//This method is to read the test data from the Excel cell
//In this we are passing parameters/arguments as Row Num and Col Num
public static String getCellData(int RowNum, int ColNum) throws Exception{
	  Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
      String CellData = Cell.getStringCellValue();
      return CellData;
	}

}
