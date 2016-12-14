package com.thinksys.KeywordDriven;

import java.lang.reflect.Method;

import com.thinksys.Utilities.ExcelUtilities;
import com.thinksys.configuration.Action_Keywords;

public class MainEngine {

	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String sPath = "D:/Selenium Projects/keywordDriven_Framework/src/main/java/com/thinksys/TestScripts/TestScript.xlsx";
		ExcelUtilities eUtils=new ExcelUtilities();
		Action_Keywords keyword=new Action_Keywords();
		// Declaring the path of the Excel file with the name of the Excel file
    	
 
    	// Here we are passing the Excel path and SheetName as arguments to connect with Excel file 
		eUtils.setExcelFilePath(sPath, "TestSteps");
 
    	//Hard coded values are used for Excel row & columns for now
    	//In later chapters we will replace these hard coded values with varibales
    	//This is the loop for reading the values of the column 3 (Action Keyword) row by row
    	for (int iRow=1;iRow<=2;iRow++){
    	   	
		    //Storing the value of excel cell in sActionKeyword string variable
    		String sActionKeyword = eUtils.getCellData(iRow, 3);
    		String sData= eUtils.getCellData(iRow, 4);
    		
    		System.out.println("keyword is:- "+ sActionKeyword);
    		System.out.println("Data is:-"+ sData);
 
    		//Comparing the value of Excel cell with all the project keywords
    		if(sActionKeyword.equals("Launch")){
                        //This will execute if the excel cell value is 'openBrowser'
    			//Action Keyword is called here to perform action
    			keyword.Launch(sData,"hello");
    			}
    		
    		System.out.println("Action Performed");
    	}
	}

	
	
}
