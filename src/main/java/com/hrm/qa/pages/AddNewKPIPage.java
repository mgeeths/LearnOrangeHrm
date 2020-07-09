package com.hrm.qa.pages;

import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import com.hrm.qa.base.BaseClass;
import com.hrm.qa.util.UtilClass;
//import com.hrm.qa.util.Xls_Reader;
	
	
public class AddNewKPIPage extends BaseClass{
	
	//Page Factory
	@FindBy(id="defineKpi360_jobTitleCode")
	WebElement selectJobTitleField;
	
	@FindBy(id="defineKpi360_keyPerformanceIndicators")
	WebElement kpiField;
	
	@FindBy(id="defineKpi360_minRating")
	WebElement minRatingField;
	
	
	@FindBy(id="defineKpi360_maxRating")
	WebElement maxRatingField;
	
	@FindBy(id="defineKpi360_makeDefault")
	WebElement defaultChkBox;
	
	
	@FindBy(xpath="//input[@name='saveBtn']")
	WebElement saveBtn;
	
	public AddNewKPIPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public void enterallInputFields(String jobTitle,String kpiName, String minRating, String maxRating) throws InterruptedException {
		Select select = new Select(selectJobTitleField);
		select.selectByVisibleText(jobTitle);
		selectJobTitleField.sendKeys(Keys.ENTER);
		kpiField.clear();
		kpiField.click();
		kpiField.sendKeys(kpiName);
		minRatingField.clear();
		minRatingField.click();
		minRatingField.sendKeys(minRating);
		maxRatingField.clear();
		maxRatingField.click();
		maxRatingField.sendKeys(maxRating);
		if(! defaultChkBox.isSelected()) {
			defaultChkBox.click();
		}
		Actions actions = new Actions(driver);
		actions.moveToElement(saveBtn).build().perform();
		saveBtn.click();
		Thread.sleep(500);
		
		Assert.assertTrue(driver.findElement(By.tagName("Body")).getText().contains("Successfully Saved"));
	}
	
	
}
