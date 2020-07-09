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
import com.hrm.qa.pages.DashboardPage;
import com.hrm.qa.pages.KPIPage;
import com.hrm.qa.pages.LoginPage;
import com.hrm.qa.util.UtilClass;

public class KPIPageTest extends BaseClass{
	
	LoginPage loginPage;
	DashboardPage dashboardPage;
	KPIPage kpiPage;
	

	public KPIPageTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		launchBrowser();

		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
		kpiPage = new KPIPage();
		
		loginPage.goToWebsite();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@BeforeMethod
	public void goToKPITab() throws InterruptedException {
		dashboardPage.goToKPIPage();
		Thread.sleep(2000);

	}

	@Test(priority = 1)
	public void verifyAddKPIPageUrl (){
		String pageUrl = driver.getCurrentUrl();
		Assert.assertTrue(pageUrl.equals("https://opensource-demo.orangehrmlive.com/index.php/performance/searchKpi"));
		
	}
	
	@DataProvider
	public Iterator<Object[]> getDataFromExcelFile() {
		ArrayList<Object[]> myTestData = UtilClass.getDataFromExcelFile();
		ArrayList<Object[]> firstColumnData = new ArrayList<>();
		for (Object[] o : myTestData) {
			firstColumnData.add(new Object[] {o[1]});
		}
		return firstColumnData.iterator();
	}
	
	@Test(priority = 2, dataProvider = "getDataFromExcelFile")
	public void verifyEditKPIAdded(String kpiName) throws InterruptedException {
		kpiPage.editNewlyAddedKPI(kpiName);
		Thread.sleep(2000);
	}

	@AfterMethod
	public void goBackToDashboardPage() throws InterruptedException {
		dashboardPage.goToDashboardPage();
		Thread.sleep(1000);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
