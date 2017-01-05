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
//Pass Excel Path and SheetName as Arguments to this method. It returns the number of rows in the sheet.
    
public static int setExcelFilePath(String Path,String SheetName) throws Exception {
        FileInputStream ExcelFile = new FileInputStream(Path);
        int iRowNum=0;
        //System.out.println("Able to access Excel file");
        ExcelWBook = new XSSFWorkbook(ExcelFile);
       try {
    	   ExcelWSheet = ExcelWBook.getSheet(SheetName);
    	   ExcelWSheet.getLastRowNum();
	} catch (NullPointerException e) {
		return 0;		
	}
        for(iRowNum=0;iRowNum<=ExcelWSheet.getLastRowNum();iRowNum++)
        {
        	 Row r = ExcelWSheet.getRow(iRowNum);
             if (r != null) {
            	// System.out.println(r.toString());
            	 //System.out.println(iRowNum);
             }
             else
             {
            	// System.out.println("Total value of Rows are:- "+iRowNum);
             }
        }
        return iRowNum;
}

public static int setTestDataFilePath(String PathofTS,String SheetNameofTestData) throws Exception {
    FileInputStream ExcelFile = new FileInputStream(PathofTS);
    int colCount=0;
    //System.out.println("Able to access Excel file");
    ExcelWBook = new XSSFWorkbook(ExcelFile);
  // try {
    ExcelWSheet = ExcelWBook.getSheet(SheetNameofTestData);
    Iterator<Row> rowIterator = ExcelWSheet.rowIterator();
    if (rowIterator.hasNext())
       {
           Row headerRow = (Row) rowIterator.next();
           //get the number of cells in the header row
           colCount = headerRow.getPhysicalNumberOfCells();
       }
       
    return colCount;
}


public static String getCellData(int RowNum, int ColNum){
	
	String CellData="";
	Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
      try{
    	  
    	  if(Cell==null)
    	  {
    	  //Cell.setCellType(Cell.CELL_TYPE_STRING);
    	  CellData="Empty";
    	  }
    	  
    	  else
    	  {
    	  Cell.setCellType(Cell.CELL_TYPE_STRING);
    	  CellData = Cell.getStringCellValue();
    	  }
    	  
      }
      catch(Exception e)
      {
    	  CellData="Empty";
    	  e.printStackTrace();
      }
	  
      return CellData;
	}

public static int getNumericCellData(int RowNum, int ColNum){
	
	int CellData=1;
	Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
	
	if(Cell==null)
	{
		CellData=1;
	}
	else
	{
	
      try{
    	  Cell.setCellType(Cell.CELL_TYPE_NUMERIC);
    	  CellData = (int) Cell.getNumericCellValue();
      }
      catch(Exception e)
      {
    	  CellData=1;
    	  e.printStackTrace();
      }
      
	}
	return CellData;
	}

}
