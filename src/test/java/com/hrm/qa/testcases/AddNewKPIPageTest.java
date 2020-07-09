package com.hrm.qa.testcases;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.hrm.qa.base.BaseClass;
import com.hrm.qa.pages.AddNewKPIPage;
import com.hrm.qa.pages.DashboardPage;
import com.hrm.qa.pages.KPIPage;
import com.hrm.qa.pages.LoginPage;
import com.hrm.qa.util.UtilClass;

public class AddNewKPIPageTest extends BaseClass{
	
	LoginPage loginPage;
	DashboardPage dashboardPage;
	KPIPage kpiPage;
	AddNewKPIPage addNewKPIPage;

	public AddNewKPIPageTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		launchBrowser();

		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
		kpiPage = new KPIPage();
		addNewKPIPage = new AddNewKPIPage();
		loginPage.goToWebsite();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@BeforeMethod
	public void goToAddNewKPIPage() throws InterruptedException {
		dashboardPage.goToKPIPage();
		kpiPage.goToAddKPIPage();
	}

	@Test(priority = 1)
	public void verifyNewKPIPageUrl(){
		String pageUrl = driver.getCurrentUrl();
		Assert.assertEquals(pageUrl, "https://opensource-demo.orangehrmlive.com/index.php/performance/saveKpi");
	}
	
	
	@DataProvider(name = "getTestData")
	public Iterator<Object[]> getTestData() {
		ArrayList<Object[]> myTestData = UtilClass.getDataFromExcelFile();
		return myTestData.iterator();
	}
	
	
	@Test(priority = 2, dataProvider ="getTestData")
	public void createNewKPI(String jobTitle, String kpiName, String MinRating, String MaxRating) throws InterruptedException {
		addNewKPIPage.enterallInputFields(jobTitle, kpiName, MinRating, MaxRating);
	}

	@AfterMethod
	public void goBackToDashboardPage() throws InterruptedException {
		Thread.sleep(2000);
		dashboardPage.goToDashboardPage();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
