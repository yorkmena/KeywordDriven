package com.thinksys.KeywordDriven;

import java.lang.reflect.Method;
import org.openqa.selenium.By;
import com.thinksys.Utilities.ExcelUtilities;
import com.thinksys.configuration.Action_Keywords;

public class ReflectionExecution {
	

	public static void main(String[] args) throws Exception 
	{
		int countofDataRows;
		int countofORRows;
		//Action_Keywords keywords=new Action_Keywords();
		
		//Declaring the path of the Excel file with the name of the Test Script..We will remove the hard coded value afterwards
		//String sPath = "./keywordDriven_Framework/ExcelTestFiles/TestScript.xlsx";
		String sPath = ".\\ExcelTestFiles\\TestScript.xlsx";
		String orPath= ".\\ExcelTestFiles\\Object Repository.xlsx";

		//Here we are passing the Excel path and SheetName to connect with the Excel file
		countofDataRows=ExcelUtilities.setExcelFilePath(sPath, "TestSteps");
		System.out.println("count of rows in TestScript Excel :-" +countofDataRows);
		
		//It means this loop will execute all the steps mentioned for the test case in Test Steps sheet
		for (int iRow = 1;iRow <= countofDataRows;iRow++){
			countofDataRows=ExcelUtilities.setExcelFilePath(sPath, "TestSteps");
			String sPageName = ExcelUtilities.getCellData(iRow, 1);
			String sLocatorName = ExcelUtilities.getCellData(iRow, 2);
			String sActionKeyword = ExcelUtilities.getCellData(iRow, 3);
			String sData= ExcelUtilities.getCellData(iRow, 4);

			/*System.out.println("PageNameIs Value is:- "+ sPageName);
			System.out.println("Locator Value is:- "+ sLocatorName);
			System.out.println("keyword is:- "+ sActionKeyword);
			System.out.println("Data is:-"+ sData);
*/
			//A new separate method is created with the name 'execute_Actions'
			//So this statement is doing nothing but calling that piece of code to execute
			
			
			countofORRows=ExcelUtilities.setExcelFilePath(orPath, "Login");
			System.out.println("count of rows in OR Excel :-" +countofORRows);
			
			String orlocatorType="";
			String orlocatorValue="";
		for(int orRow=1;orRow<=countofORRows;orRow++){
			
		String orLocatorName= ExcelUtilities.getCellData(orRow,1);
		
		System.out.println(sLocatorName+"------------"+orLocatorName);
		if(sLocatorName.equals(orLocatorName))
		{
			orlocatorType=ExcelUtilities.getCellData(orRow, 2);
			orlocatorValue=ExcelUtilities.getCellData(orRow, 3);
			break;
			//locatorValue=Action_Keywords.locatorValue(orlocatorType, orlocatorValue);	
		}
		
		
		//execute_Actions(sActionKeyword,locatorValue,sData);	
		
		}
		execute_Actions(sActionKeyword,orlocatorType,orlocatorValue,sData);
		//a.performAction(sActionKeyword,orlocatorType,orlocatorValue,sData);
		
			
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
		keywords.performAction(sActionKeyword,orlocatorType,orlocatorValue,data );
		
	}

}

