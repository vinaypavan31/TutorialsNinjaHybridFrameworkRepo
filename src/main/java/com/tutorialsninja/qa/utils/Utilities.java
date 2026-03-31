package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {

	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_LOAD_TIME = 15;

	public static String generateEmailWithTimeStamp() {
		Date date = new Date();

		String finalDate = date.toString().replaceAll(" ", "").replaceAll(":", "");

		return "venky" + finalDate + "@gmail.com";
	}

	public static Object[][] getTestDataFromExcel(String sheetName) {

	    FileInputStream fisExcel = null;
	    XSSFWorkbook workbook = null;

	    try {
	        fisExcel = new FileInputStream(System.getProperty("user.dir")
	                + "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TutorailsNinjaTestData.xlsx");

	        workbook = new XSSFWorkbook(fisExcel);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    XSSFSheet sheet = workbook.getSheet(sheetName);
	    int rows = sheet.getLastRowNum();
	    int cols = sheet.getRow(0).getLastCellNum();

	    Object[][] data = new Object[rows][cols];

	    for (int i = 0; i < rows; i++) {

	        XSSFRow row = sheet.getRow(i + 1);

	        for (int j = 0; j < cols; j++) {

	            XSSFCell cell = null;

	            if (row != null) {
	                cell = row.getCell(j);
	            }

	            // Safe handling
	            switch (cell.getCellType()) {
	                case STRING:
	                    data[i][j] = cell.getStringCellValue();
	                    break;

	                case NUMERIC:
	                    data[i][j] = String.valueOf(cell.getNumericCellValue());
	                    break;

	                case BOOLEAN:
	                    data[i][j] = String.valueOf(cell.getBooleanCellValue());
	                    break;

	                default:
	                    data[i][j] = "";
	            }
	        }
	    }

	    return data;
	}
	
	
	 // 📸 Base64 method (for Extent Report)
    public static String getBase64Screenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    // 📁 File save method (for project storage)
    public static String saveScreenshot(WebDriver driver, String testName) {

        String folderPath = System.getProperty("user.dir") + "/Screenshots/";
        File folder = new File(folderPath);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        String filePath = folderPath + testName + "_" + System.currentTimeMillis() + ".png";

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileHandler.copy(srcFile, new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }
	
}
