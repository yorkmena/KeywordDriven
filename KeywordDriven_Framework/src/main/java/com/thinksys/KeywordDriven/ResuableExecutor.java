package com.thinksys.KeywordDriven;

import org.apache.poi.ss.usermodel.Cell;

import com.thinksys.Utilities.ExcelUtilities;
import com.thinksys.configuration.Action_Keywords;

public class ResuableExecutor {
	
	String sData;
	String rIteration_range;
	
	public ResuableExecutor(String sData,String rIteration_range)
	{
		this.sData=sData;
		this.rIteration_range=rIteration_range;
		try {
			readReusable(sData,rIteration_range);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	String orlocatorType="";
	String orlocatorValue="";
	String repositoryPath= ".\\ExcelTestFiles\\Object Repository.xlsx";
	
	int dashIndex;
	int range_value1;
	int range_value2;
	int rIteration_count;
	
	int start=0;
	int stop=0;
	int dotIndex;
	String reusableFilename;
	String reusableName;
	String reusablefilepath;
	int countofReusableTestStepsrows;
		
	public void readReusable(String sData, String rIteration_range) 
	{
		
		//Read Reusable Iteration
		dashIndex=rIteration_range.indexOf("-");
		range_value1=Integer.parseInt(rIteration_range.trim().substring(0, dashIndex));
		range_value2=Integer.parseInt(rIteration_range.trim().substring(dashIndex+1));
		rIteration_count=range_value2-range_value1+1;
		
		//This Piece of code will Identify reusable name to be executed.
		dotIndex=sData.indexOf(".");
		reusableFilename=sData.substring(0,dotIndex);
		reusableName=sData.substring(dotIndex+1);
		reusablefilepath=".\\ExcelTestFiles\\Reusable\\".concat(reusableFilename+".xlsx");
		
		try {
			countofReusableTestStepsrows=ExcelUtilities.setExcelFilePath(reusablefilepath, "TestSteps");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Find out the reusable name in reusable sheet and which rows to execute. 
		//Start and stop are int variables. Start is the row number from where execution starts, and stop is the row numbers where executions ends.
		
			for(int i=1;i<countofReusableTestStepsrows;i++){
			
				if(reusableName.equals(ExcelUtilities.getCellData(i, 1))){
					start=i;
				}
				else{
				//System.out.println("Reusable Name Not found");
			}
			
		}
		for(int j=start+1;j<countofReusableTestStepsrows;j++){
					
			if(ExcelUtilities.getCellData(j, 1).equals("")||ExcelUtilities.getCellData(j, 1).equals(null)||ExcelUtilities.getCellData(j, 1).equals(Cell.CELL_TYPE_BLANK)){
			
				if(j==countofReusableTestStepsrows-1){
				stop=j;
				}
			}
				 else{
				 stop=j-1;
				 break;
				 }		
		}
	
		//Read the reusable rows from start to stop. 
		//start and stop values are captured using above code.
							
	for(int i=range_value1;i<=range_value2;i++){
		
		for(int row=start;row<=stop; row++){
		try {
			ExcelUtilities.setExcelFilePath(reusablefilepath, "TestSteps");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String rPageName = ExcelUtilities.getCellData(row, 2);
		String rLocatorName = ExcelUtilities.getCellData(row, 3);
		String rActionKeyword = ExcelUtilities.getCellData(row, 4); //System.out.println("rActionKeyword: "+rActionKeyword);
		String rData= ExcelUtilities.getCellData(row, 5);
		String rIteration=ExcelUtilities.getCellData(row, 6);
		
		//If Action Keyword is a reusable, then this if block will be executed and the rest of the loop will not be executed.
		if(rActionKeyword.equalsIgnoreCase("Reusable")){
			System.out.println("In Reusable sheet "+sData+" another reusable found: "+rData+" with iteration "+rIteration);
			new ResuableExecutor(rData,rIteration);
			continue;
		}
		
		//Reading Reusable Test Data
		if(rData.startsWith("DP_")){
			//System.out.println("Test Data Starts with DP_");
			int countofTestDataRows;
			int countofTestDataColumns=0;
			
			try {
				countofTestDataRows=ExcelUtilities.setExcelFilePath(reusablefilepath, "TestData");
				countofTestDataColumns=ExcelUtilities.setTestDataFilePath(reusablefilepath, "TestData");
			} catch (Exception e) {
				System.out.println("Cannot get Count of TestDada Rows or TestData Columns.");
				e.printStackTrace();
			}
			
			for(int y=0;y<countofTestDataColumns;y++){
				
				String DataColumn = ExcelUtilities.getCellData(0, y);
				if(rData.equals(DataColumn)){
					rData=ExcelUtilities.getCellData(i,y);
				}
			}
		}
		
		// Reading OR
		int countofORRows = 0;
		try {
			countofORRows = ExcelUtilities.setExcelFilePath(repositoryPath, rPageName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			//System.out.println("Count of rows in OR Excel: " +countofORRows);			
			for(int orRow=1; orRow<countofORRows; orRow++){
				String orLocatorName= ExcelUtilities.getCellData(orRow,1);
				if(rLocatorName.equals(orLocatorName)){
					orlocatorType=ExcelUtilities.getCellData(orRow, 2);
					orlocatorValue=ExcelUtilities.getCellData(orRow, 3);
					break;	
				}
			}
			
		//calling Action keyword call method to execute the 
		Action_Keywords keywords=new Action_Keywords();
		keywords.performAction(rActionKeyword,orlocatorType,orlocatorValue,rData);
		
		}
	}
		
	}
	
}