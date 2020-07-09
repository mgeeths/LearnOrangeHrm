package com.hrm.qa.util;

import java.util.ArrayList;

public class UtilClass {
	
	static Xls_Reader xlsReader;
	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 20;
	
	public static ArrayList<Object[]> getDataFromExcelFile() {
		ArrayList<Object[]> testData = new ArrayList<Object[]>();
		
		xlsReader = new Xls_Reader("C:\\Users\\browse\\Automation\\TrialOrangeHrm"
				+ "\\src\\main\\java\\com\\hrm\\qa\\testdata\\Test Data.xlsx");

		for(int rowNum=2; rowNum<=xlsReader.getRowCount("NewKPIDetails"); rowNum++){
			String jobTitle = xlsReader.getCellData("NewKPIDetails", "JobTitle", rowNum);
			String kpiName = xlsReader.getCellData("NewKPIDetails", "KPIName", rowNum);
			//String minRating = xlsReader.getCellData("NewKPIDetails", "MinRating", rowNum);
			int minRating = (int) Double. parseDouble(xlsReader.getCellData("NewKPIDetails", "MinRating", rowNum));
			//System.out.println(minRating);
			//String maxRating = xlsReader.getCellData("NewKPIDetails", "MaxRating", rowNum);
			int maxRating = (int) Double.parseDouble(xlsReader.getCellData("NewKPIDetails", "MaxRating", rowNum));
			testData.add(new Object[] {jobTitle,kpiName,String.valueOf(minRating),String.valueOf(maxRating)});

		}return testData;
	}
}
