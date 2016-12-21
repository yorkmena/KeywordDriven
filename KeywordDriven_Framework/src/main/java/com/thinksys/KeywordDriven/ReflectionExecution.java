package com.thinksys.KeywordDriven;

import com.thinksys.Utilities.ExcelUtilities;
import com.thinksys.configuration.Action_Keywords;

public class ReflectionExecution {
	
	public static void main(String[] args) throws Exception 
	{
		int countofTestScriptRows=0;
		int countofORRows=0;
		
		
		
		//Declaring the path of the Excel file with the name of the Test Script..We will remove the hard coded value afterwards
		String scriptPath = ".\\ExcelTestFiles\\TestScript.xlsx";
		String repositoryPath= ".\\ExcelTestFiles\\Object Repository.xlsx";
		
		
		
		String orlocatorType="";
		String orlocatorValue="";

		
		//Here we are passing the Excel path and SheetName to connect with the Excel file
		countofTestScriptRows=ExcelUtilities.setExcelFilePath(scriptPath, "TestSteps");
		System.out.println("Count of rows in TestScript Excel: " +countofTestScriptRows);
		
		int interationCount=Integer.parseInt(ExcelUtilities.getCellData(0, 1));
		System.out.println("Ineration Count : " +interationCount);
		for(int itr=0;itr<interationCount;itr++)
		{
		//It means this loop will execute all the steps mentioned for the test case in Test Steps sheet
		for(int sRow=2; sRow<=countofTestScriptRows; sRow++)
		{
			countofTestScriptRows=ExcelUtilities.setExcelFilePath(scriptPath, "TestSteps");
			String sPageName = ExcelUtilities.getCellData(sRow, 1);
			String sLocatorName = ExcelUtilities.getCellData(sRow, 2);
			String sActionKeyword = ExcelUtilities.getCellData(sRow, 3);
			String sData= ExcelUtilities.getCellData(sRow, 4);

			if(sData.startsWith("DP_"))
			{
				//sData=sData.substring(3); System.out.println(sData);
				
				int countofTestDataRows;
				int countofTestDataColumns;
				
				countofTestDataRows=ExcelUtilities.setExcelFilePath(scriptPath, "TestData");
				countofTestDataColumns=ExcelUtilities.setTestDataFilePath(scriptPath, "TestData");
				
				for(int i=0;i<=countofTestDataColumns;i++)
				{
					
					String DataColmn = ExcelUtilities.getCellData(0, i);
					if(sData.equals(DataColmn))
					{
						sData=ExcelUtilities.getCellData(1,i);
					}
					
					else
					{
						System.out.println("Data not matching");
					}	
				}
			}
			
			
		// Reading OR
			countofORRows=ExcelUtilities.setExcelFilePath(repositoryPath, sPageName);
			//System.out.println("Count of rows in OR Excel: " +countofORRows);
						
			for(int orRow=1; orRow<=countofORRows; orRow++)
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

/*	private static void execute_Actions(String keyword_used,String locatorVal,String data) throws Exception {		
		if(locatorVal.equals(null))
		{
			locatorVal="";
		}
		Method method[];
		Action_Keywords actionKeywords = new Action_Keywords();
		//This will load all the methods of the class 'ActionKeywords' in it.
		//It will be like array of method, use the break point here and do the watch
		method = actionKeywords.getClass().getMethods();
		//This is a loop which will run for the number of actions in the Action Keyword class
		//method variable contain all the method and method.length returns the total number of methods
		for(int i = 0;i < method.length;i++){
			System.out.println("Method name is:- "+ method[i]);
			//This is now comparing the method name with the ActionKeyword value got from excel
			if(method[i].getName().equals(keyword_used)){
				//In case of match found, it will execute the matched method
				System.out.println("Method name is:- "+ method[i]);
				method[i].invoke(actionKeywords,locatorVal,data);
				//Once any method is executed, this break statement will take the flow outside of for loop
				break;
			}
		}
	}*/
	
	private static void execute_Actions(String sActionKeyword,String orlocatorType,String orlocatorValue, String data) throws Exception 
	{
		Action_Keywords keywords=new Action_Keywords();
		keywords.performAction(sActionKeyword,orlocatorType,orlocatorValue,data);
	}
}

