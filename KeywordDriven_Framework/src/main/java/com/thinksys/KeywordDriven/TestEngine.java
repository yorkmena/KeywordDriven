package com.thinksys.KeywordDriven;

import com.thinksys.Utilities.ExcelUtilities;
import com.thinksys.configuration.Action_Keywords;

public class TestEngine {
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("Welcome to Krypton 2.0");
		int countofTestScriptRows=0;
		int countofORRows=0;
		
		String scriptPath = ".\\ExcelTestFiles\\TestScript.xlsx";
		String repositoryPath= ".\\ExcelTestFiles\\Object Repository.xlsx";
		
		String sPageName = null;
		String sLocatorName = null;
		String sActionKeyword = null;
		String sData= null;
		String s_reusable_Iteration=null;
		String orlocatorType="";
		String orlocatorValue="";
		
		int countofTestDataRows;
		int countofTestDataColumns;
		
		//Here we are passing the Excel path and SheetName to connect with the Excel file
		countofTestScriptRows=ExcelUtilities.setExcelFilePath(scriptPath, "TestSteps");
		System.out.println("Count of rows in TestScript Excel: " +countofTestScriptRows);
		
		int globalIterationCount=ExcelUtilities.getNumericCellData(0, 1);
		System.out.println("Global Iteration Count : " +globalIterationCount);
		
		  for(int itr=1;itr<=globalIterationCount;itr++){
		  //It means this loop will execute all the steps mentioned for the test case in Test Steps sheet
		    for(int sRow=2; sRow<countofTestScriptRows; sRow++){
			    countofTestScriptRows=ExcelUtilities.setExcelFilePath(scriptPath, "TestSteps");
			    sPageName = ExcelUtilities.getCellData(sRow, 1);
			    sLocatorName = ExcelUtilities.getCellData(sRow, 2);
			    sActionKeyword = ExcelUtilities.getCellData(sRow, 3);
			    sData= ExcelUtilities.getCellData(sRow, 4);
			    s_reusable_Iteration=ExcelUtilities.getCellData(sRow,5);
			
			    System.out.println(s_reusable_Iteration);
			
			  //Reusable Execution
					if(sActionKeyword.equalsIgnoreCase("Reusable")){
						System.out.println("In TestEngine, Reusable Action is: "+ sData+" and Reuable Iteration value is:"+ s_reusable_Iteration );
						new ResuableExecutor(sData,s_reusable_Iteration);
						continue;
					}
			
			
			  //TestData Reader
			  if(sData.startsWith("DP_")){
				
				countofTestDataRows=ExcelUtilities.setExcelFilePath(scriptPath, "TestData");
				countofTestDataColumns=ExcelUtilities.setTestDataFilePath(scriptPath, "TestData");
					for(int i=0;i<countofTestDataColumns;i++){
					
						String DataColmn = ExcelUtilities.getCellData(0, i);
						if(sData.equals(DataColmn)){
						   sData=ExcelUtilities.getCellData(itr,i);
						}
						else{
							//System.out.println("No such Column Name exists in TestData");
						}
					}
			  }
			
			  // Reading OR
			 countofORRows=ExcelUtilities.setExcelFilePath(repositoryPath, sPageName);
						
			  		for(int orRow=1; orRow<countofORRows; orRow++){
			  			String orLocatorName= ExcelUtilities.getCellData(orRow,1);
				
			  			if(sLocatorName.equals(orLocatorName)){
			  				orlocatorType=ExcelUtilities.getCellData(orRow, 2);
			  				orlocatorValue=ExcelUtilities.getCellData(orRow, 3);
			  				break;
			  			}
			}
			
			 //A method of class Action_Keywords
			 execute_Actions(sActionKeyword,orlocatorType,orlocatorValue,sData);
		}
	}
	}
	
	//This method contains the code to perform some action
	//This is to execute test step (Action)

	private static void execute_Actions(String sActionKeyword,String orlocatorType,String orlocatorValue, String data) throws Exception {
		Action_Keywords keywords=new Action_Keywords();
		keywords.performAction(sActionKeyword,orlocatorType,orlocatorValue,data);
	}
}

