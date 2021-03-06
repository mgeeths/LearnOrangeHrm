package com.hrm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hrm.qa.base.BaseClass;
import com.hrm.qa.pages.AssignLeavePage;
import com.hrm.qa.pages.DashboardPage;
import com.hrm.qa.pages.LoginPage;

public class DashboardPageTest extends BaseClass{
	LoginPage loginPage;
	DashboardPage dashboardPage;
	AssignLeavePage assignLeavePage;
	
	DashboardPageTest(){
		super();
	}
	
	
	
	@BeforeClass
	public void setUp() {
		launchBrowser();
		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
		
		loginPage.goToWebsite();
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		}
	
	@BeforeMethod
	public void goToDashboardPage() {
		dashboardPage.goToDashboardPage();
	}
	
	@Test(priority=1)
	public void validateUser() {
		String currentUserName = dashboardPage.currentUser();
		Assert.assertEquals(currentUserName, "Welcome Admin");
	}
	
	@Test(priority=4)
	public void verifyEmpListPage() {
		dashboardPage.goToEmpListPage();
		String currentURL = driver.getCurrentUrl();
		Assert.assertEquals(currentURL, "https://opensource-demo.orangehrmlive.com/index.php/pim/viewEmployeeList");
	}
	@Test(priority=2)
	public void verifyAssignLeaveIconPage() {
		dashboardPage.goToAssignLeave();
		String currentURL = driver.getCurrentUrl();
		Assert.assertEquals(currentURL, "https://opensource-demo.orangehrmlive.com/index.php/leave/assignLeave");
	}
	
	@Test(priority=3)
	public void verifyLeaveListIconPage() {
		dashboardPage.goToLeaveList();
		String currentURL = driver.getCurrentUrl();
		Assert.assertEquals(currentURL, "https://opensource-demo.orangehrmlive.com/index.php/leave/viewLeaveList");
	}
	
	@Test(priority=5)
	public void verifyUsersPage() {
		dashboardPage.goToUsersPage();
		String currentURL = driver.getCurrentUrl();
		Assert.assertEquals(currentURL, "https://opensource-demo.orangehrmlive.com/index.php/admin/viewSystemUsers");
	}
	
	@Test(priority=6)
	public void verifyLeaveListPage() {
		dashboardPage.goToLeaveTab();;
		String currentURL = driver.getCurrentUrl();
		Assert.assertEquals(currentURL, "https://opensource-demo.orangehrmlive.com/index.php/leave/viewLeaveList");
	}
	@Test(priority=7)
	public void verifyHolidaysPage() {
		dashboardPage.goToHoildaysPage();
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://opensource-demo.orangehrmlive.com/index.php/leave/viewHolidayList");
 	}
	
	@Test(priority=8)
	public void verifyKPIPage() {
		dashboardPage.goToKPIPage();
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://opensource-demo.orangehrmlive.com/index.php/performance/searchKpi");
 	}
	
	
	//@AfterMethod
	public void goBackToDashboardPage() {
		dashboardPage.goToDashboardPage();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
