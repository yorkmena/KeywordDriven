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
        //System.out.println("ABle to access Excel file");
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
            	// System.out.println("TOtal value of Rows are:- "+iRowNum);
             }  
        }
        return iRowNum-1;
}

public static int setTestDataFilePath(String PathofTS,String SheetNameofTestData) throws Exception {
    FileInputStream ExcelFile = new FileInputStream(PathofTS);
    int colCount=0;
    //System.out.println("ABle to access Excel file");
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
       System.out.println("number of cells with data"+colCount);
 
    return colCount-1;

}


//This method is to read the test data from the Excel cell
//In this we are passing parameters/arguments as Row Num and Col Num
public static String getCellData(int RowNum, int ColNum){
	
	String CellData=" ";
	int cell_type;
	
      try{
    	  
      Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
      cell_type=Cell.getCellType();
      
      if (cell_type==1)
      {
    	  CellData = Cell.getStringCellValue();
      }
      else if(cell_type==0)
      {
	  
	  CellData=Cell.getNumericCellValue()+"";
      }
      
      }
      catch(Exception e)
      {
    	  CellData="";
    	  e.printStackTrace();
      }
	  
    /* if (CellData.equals(null))
      {
    	 CellData="";
      }*/
     
      return CellData;
	}




}
