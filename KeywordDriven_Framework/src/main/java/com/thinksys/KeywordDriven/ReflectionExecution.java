package com.thinksys.KeywordDriven;

import com.thinksys.Utilities.ExcelUtilities;
import com.thinksys.configuration.Action_Keywords;

public class ReflectionExecution {
	
	public static void main(String[] args) throws Exception 
	{
		System.out.println("Welcome to Krypton 2.0");
		int countofTestScriptRows=0;
		int countofORRows=0;
		
		//Declaring the path of the Excel file with the name of the Test Script..We will remove the hard coded value afterwards
		String scriptPath = ".\\ExcelTestFiles\\TestScript.xlsx";
		String repositoryPath= ".\\ExcelTestFiles\\Object Repository.xlsx";
		
		String sPageName = null;
		String sLocatorName = null;
		String sActionKeyword = null;
		String sData= null;
		String s_reusable_Iteration=null;
		
		String orlocatorType="";
		String orlocatorValue="";
		
		
		//Here we are passing the Excel path and SheetName to connect with the Excel file
		countofTestScriptRows=ExcelUtilities.setExcelFilePath(scriptPath, "TestSteps");
		System.out.println("Count of rows in TestScript Excel: " +countofTestScriptRows);
		
		int global_iteration_Count=ExcelUtilities.getNumericCellData(0, 1);
		System.out.println("Iteration Count : " +global_iteration_Count);
		
		for(int itr=1;itr<=global_iteration_Count;itr++)
		{
		//It means this loop will execute all the steps mentioned for the test case in Test Steps sheet
		for(int sRow=2; sRow<countofTestScriptRows; sRow++)
		{
			countofTestScriptRows=ExcelUtilities.setExcelFilePath(scriptPath, "TestSteps");
			sPageName = ExcelUtilities.getCellData(sRow, 1);
			sLocatorName = ExcelUtilities.getCellData(sRow, 2);
			sActionKeyword = ExcelUtilities.getCellData(sRow, 3);
			sData= ExcelUtilities.getCellData(sRow, 4);
			s_reusable_Iteration=ExcelUtilities.getCellData(sRow,5);
			
			System.out.println(s_reusable_Iteration);
			
		//Reading Reusable
			
			if(sActionKeyword.contains("."))
			{
				System.out.println("In Reflection execution Action keyword contains . ; and Reuable Iteration value is:"+ s_reusable_Iteration );
				new ReadReusable(sActionKeyword,s_reusable_Iteration);
				continue;
			}
			
			//Reading TestData
			if(sData.startsWith("DP_"))
			{
				//sData=sData.substring(3); System.out.println(sData);
				
				int countofTestDataRows;
				int countofTestDataColumns;
				
				countofTestDataRows=ExcelUtilities.setExcelFilePath(scriptPath, "TestData");
			    //System.out.println("countofTestDataRows :"+ countofTestDataRows);
				countofTestDataColumns=ExcelUtilities.setTestDataFilePath(scriptPath, "TestData");
			    //System.out.println("countofTestDataColumns :"+ countofTestDataColumns);
				
				for(int i=0;i<countofTestDataColumns;i++)
				{
					
					String DataColmn = ExcelUtilities.getCellData(0, i);
					if(sData.equals(DataColmn))
					{
						sData=ExcelUtilities.getCellData(itr,i);
					}
					
					else
					{
						//System.out.println("Data not matching");
					}	
				}
			}
			
			
		// Reading OR
			countofORRows=ExcelUtilities.setExcelFilePath(repositoryPath, sPageName);
			//System.out.println("Count of rows in OR Excel: " +countofORRows);
						
			for(int orRow=1; orRow<countofORRows; orRow++)
			{
				String orLocatorName= ExcelUtilities.getCellData(orRow,1);
				//System.out.println(sLocatorName+"------------"+orLocatorName);
				
				
				if(sLocatorName.equals(orLocatorName))
				{
					orlocatorType=ExcelUtilities.getCellData(orRow, 2);
					orlocatorValue=ExcelUtilities.getCellData(orRow, 3);
					break;
				}
			}
			//A new separate method is created with the name 'execute_Actions'
			//So this statement is doing nothing but calling that piece of code to execute
			execute_Actions(sActionKeyword,orlocatorType,orlocatorValue,sData);
		}
	}
	}
	
	//This method contains the code to perform some action
	//As it is completely different set of logic, which revolves around the action only,
	//It makes sense to keep it separate from the main driver script
	//This is to execute test step (Action)

	private static void execute_Actions(String sActionKeyword,String orlocatorType,String orlocatorValue, String data) throws Exception 
	{
		Action_Keywords keywords=new Action_Keywords();
		keywords.performAction(sActionKeyword,orlocatorType,orlocatorValue,data);
	}
}

