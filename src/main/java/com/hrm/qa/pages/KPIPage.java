package com.hrm.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hrm.qa.base.BaseClass;

public class KPIPage extends BaseClass {

	//Page Factory
	@FindBy(id="btnAdd")
	WebElement addBtn;
	
	@FindBy(xpath = "//table[@id='resultTable']//tr[@class='odd']|//table[@id='resultTable']//tr[@class='even']")
	List<WebElement> allRows;
	
	@FindBy(id="defineKpi360_makeDefault")
	WebElement defaultChkBox;
	
	@FindBy(xpath="//input[@name='saveBtn']")
	WebElement saveBtn;
		
	
	public KPIPage() {
		PageFactory.initElements(driver, this);
	}
	
	public AddNewKPIPage goToAddKPIPage() throws InterruptedException {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", addBtn);
		Thread.sleep(2000);
		//addBtn.click();
		return new AddNewKPIPage();
	}
	
	public void editNewlyAddedKPI(String kpiName) throws InterruptedException {
		
		for (WebElement eachRow:allRows) {
			List<WebElement> allCols = eachRow.findElements(By.xpath("./td"));
			String name = allCols.get(1).getText();
			if(name.equals("Finance KPI")) {
				allCols.get(1).findElement(By.tagName("a")).click();
				Assert.assertTrue(driver.getCurrentUrl().contains("hdnEditId"));
				defaultChkBox.click();
				saveBtn.click();
				Thread.sleep(2000);
				break;
			}
		}
	}
}
