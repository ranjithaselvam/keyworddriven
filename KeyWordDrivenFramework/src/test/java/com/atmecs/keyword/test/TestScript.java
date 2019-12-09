package com.atmecs.keyword.test;

import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.atmecs.keyword.config.Constant;
import com.atmecs.keyword.property.ReadObject;
import com.atmecs.keyword.uioperation.UIOperation;
import com.atmecs.keyword.utils.Utils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestScript {

	Utils util = new Utils();
	ReadObject object = new ReadObject();

	@Test
	public void testLogin() throws Exception {
		WebDriverManager.firefoxdriver().setup();
		WebDriver webdriver = new FirefoxDriver();

		UIOperation operation = new UIOperation(webdriver);
		Properties allObjects = object.getObjectRepository();

//Read keyword sheet
		Sheet sheet = util.excelReader(Constant.testData_file, "keyworddriven", "keyword");
//Find number of rows in excel file
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		// Create a loop over all the rows of excel file to read it
		for (int i = 1; i < rowCount + 1; i++) {
			// Loop over all the rows
			Row row = sheet.getRow(i);
			// Check if the first cell contain a value, if yes, That means it is the new
			// testcase name
			if (row.getCell(0).toString().length() == 0) {
				// Print testcase detail on console
				System.out.println(row.getCell(1).toString() + "----" + row.getCell(2).toString() + "----"
						+ row.getCell(3).toString() + "----" + row.getCell(4).toString());
				// Call perform function to perform operation on UI
				operation.perform(allObjects, row.getCell(1).toString(), row.getCell(2).toString(),
						row.getCell(3).toString(), row.getCell(4).toString());
			} else {
				// Print the new testcase name when it started
				System.out.println("New Testcase->" + row.getCell(0).toString() + " Started");
			}
		}
	}

}
