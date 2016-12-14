package com.thinksys.KeywordDriven;

import java.lang.reflect.Method;

import com.thinksys.Utilities.ExcelUtilities;
import com.thinksys.configuration.Action_Keywords;

public class ReflectionExecution {


	public static void main(String[] args) throws Exception 
	{

		//Declaring the path of the Excel file with the name of the Test Script..We will remove the hard coded value afterwards
		String sPath = "D:/Selenium Projects/keywordDriven_Framework/src/main/java/com/thinksys/TestScripts/TestScript.xlsx";
		String orPath= "D:/Selenium Projects/KeywordDriven_Framework/src/main/java/com/thinksys/TestScripts/Object Repository/Object Repository.xlsx";

		//Here we are passing the Excel path and SheetName to connect with the Excel file
		//This method was created in the last chapter of 'Set up Data Engine' 		
		int countofDataRows=ExcelUtilities.setExcelFilePath(sPath, "TestSteps");
		
		
		System.out.println("count of rows in Excel is:-" +countofDataRows);
		//It means this loop will execute all the steps mentioned for the test case in Test Steps sheet
		
		for (int iRow = 1;iRow <= countofDataRows;iRow++){
			//This to get the value of column Action Keyword from the excel
			String orPageName = ExcelUtilities.getCellData(iRow, 1);
			String sLocatorValue = ExcelUtilities.getCellData(iRow, 2);
			String sActionKeyword = ExcelUtilities.getCellData(iRow, 3);
			String sData= ExcelUtilities.getCellData(iRow, 4);

			System.out.println("ORPageNameIs Value is:- "+ orPageName);
			System.out.println("Locator Value is:- "+ sLocatorValue);
			System.out.println("keyword is:- "+ sActionKeyword);
			System.out.println("Data is:-"+ sData);

			//A new separate method is created with the name 'execute_Actions'
			//So this statement is doing nothing but calling that piece of code to execute
			execute_Actions(sActionKeyword,sLocatorValue,sData);
		}
	}

	//This method contains the code to perform some action
	//As it is completely different set of logic, which revolves around the action only,
	//It makes sense to keep it separate from the main driver script
	//This is to execute test step (Action)

	private static void execute_Actions(String keyword_used,String locatorVal,String data) throws Exception {

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
				method[i].invoke(actionKeywords,locatorVal,data);
				//Once any method is executed, this break statement will take the flow outside of for loop
				break;
			}
		}
	}



	//}

}

