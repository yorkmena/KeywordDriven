package com.thinksys.KeywordDriven;

import org.apache.poi.ss.usermodel.Cell;

import com.thinksys.Utilities.ExcelUtilities;
import com.thinksys.configuration.Action_Keywords;

public class ReadReusable {
	
	String sActionKeyword;
	String rIteration_range;
	
	public ReadReusable(String ActionKeyword,String rIteration_range)
	{
		this.sActionKeyword=ActionKeyword;
		this.rIteration_range=rIteration_range;
		try {
			readReusable(sActionKeyword,rIteration_range);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	public void readReusable(String sActionKeyword, String rIteration_range) throws Exception
	{
		String orlocatorType="";
		String orlocatorValue="";
		String repositoryPath= ".\\ExcelTestFiles\\Object Repository.xlsx";
		
		
		//Read Reusable Iteration
		int dashIndex=rIteration_range.indexOf("-");
		int range_value1=Integer.parseInt(rIteration_range.trim().substring(0, dashIndex));
		int range_value2=Integer.parseInt(rIteration_range.trim().substring(dashIndex+1));
		int rIteration_count=range_value2-range_value1+1;
		System.out.println("range_value1: "+ range_value1+" "+"range_value2: "+range_value2);
		
		//This Piece of code will Identify reusable name to be executed.
		int dotIndex=sActionKeyword.indexOf(".");
		String reusableFilename=sActionKeyword.substring(0,dotIndex);
		String reusableName=sActionKeyword.substring(dotIndex+1);
		String reusablefilepath=".\\ExcelTestFiles\\Reusable\\".concat(reusableFilename+".xlsx");
		int countofReusableTestStepsrows=ExcelUtilities.setExcelFilePath(reusablefilepath, "TestSteps");
	
		
		//Find out the reusable name in reusable sheet and which rows to execute. 
		//Start and stop are row numbers to execute based on the reusable name.
		int start=0;
		int stop=0;
		
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
				
				//System.out.println("Start: "+start+" Stop: "+stop);
		
	
		//Read the reusable rows from start to stop. 
		//start and stop values are captured using above code.
				
				
	for(int i=range_value1;i<=range_value2;i++){
				
		for(int row=start;row<=stop; row++){
		ExcelUtilities.setExcelFilePath(reusablefilepath, "TestSteps");
		
		String rPageName = ExcelUtilities.getCellData(row, 2);
		String rLocatorName = ExcelUtilities.getCellData(row, 3);
		String rActionKeyword = ExcelUtilities.getCellData(row, 4); System.out.println("rActionKeyword: "+rActionKeyword);
		String rData= ExcelUtilities.getCellData(row, 5);
		String rIteration=ExcelUtilities.getCellData(row, 6);
		if(rActionKeyword.contains("."))
		{
			System.out.println("Action keyword contains . ; reusable used in reusable");
			new ReadReusable(rActionKeyword,rIteration);
			continue;
		}
		
		//Reading Reusable Test Data
		if(rData.startsWith("DP_"))
		{
			//System.out.println("Test Data Starts with DP_");
			int countofTestDataRows;
			int countofTestDataColumns;
			
			countofTestDataRows=ExcelUtilities.setExcelFilePath(reusablefilepath, "TestData");
			//System.out.println("countofReusableTestDataRows :"+ countofTestDataRows);
			countofTestDataColumns=ExcelUtilities.setTestDataFilePath(reusablefilepath, "TestData");
			//System.out.println("countofReusableTestDataColumns :"+ countofTestDataColumns);
			
			for(int y=0;y<countofTestDataColumns;y++)
			{
				
				String DataColmn = ExcelUtilities.getCellData(0, y);
				//System.out.println(rData+"--------------------"+DataColmn);
				if(rData.equals(DataColmn))
				{
					//System.out.println("Row: "+1+"Column: "+i);
					rData=ExcelUtilities.getCellData(i,y);
				}
				
				else
				{
					
				}	
			}
		}
		
		// Reading OR
		int	countofORRows=ExcelUtilities.setExcelFilePath(repositoryPath, rPageName);
			//System.out.println("Count of rows in OR Excel: " +countofORRows);			
			for(int orRow=1; orRow<countofORRows; orRow++)
			{
				String orLocatorName= ExcelUtilities.getCellData(orRow,1);
				if(rLocatorName.equals(orLocatorName)){
					orlocatorType=ExcelUtilities.getCellData(orRow, 2);
					orlocatorValue=ExcelUtilities.getCellData(orRow, 3);
					break;	
				}
			}
		
		Action_Keywords keywords=new Action_Keywords();
		keywords.performAction(rActionKeyword,orlocatorType,orlocatorValue,rData);
		
		}
	}
		
	}
	
}