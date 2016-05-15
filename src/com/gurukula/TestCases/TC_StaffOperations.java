/*
 * This test case class is used to check if user is able to create a new staff, update the staff name
 * and save it.
 * */
package com.gurukula.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.gurukula.Utils.Utilities;
import com.gurukula.WebObjects.GurukulaWebObjects;
import com.gurukula.WebPageUrl.PageUrlDetails;
import com.gurukula.WebPages.GurukulaBranchPage;
import com.gurukula.WebPages.GurukulaLoginPage;
import com.gurukula.WebPages.GurukulaStaffPage;
import com.gurukula.WebPages.GurukulaWelcomePage;

public class TC_StaffOperations {
  
	WebDriver driver;
	Utilities Objwait = new Utilities();
	GurukulaWebObjects welcomePageObjects;
	PageUrlDetails currentUrl;
	String currentUserName= "admin";
	String currentUserPassword = "admin";
	String currentPageUrl="";
	String createStaffName = "Mccham";
	String ModStaffName = "JonDule";
	String BranchName = "ICICI";
	@BeforeSuite	
	public void BrowserLaunch() {
		driver = new FirefoxDriver();
		currentUrl = new PageUrlDetails();
		String HomePageUrl = currentUrl.HomePageUrl;
		System.out.println(HomePageUrl);
		driver.get(HomePageUrl);
		driver.manage().window().maximize();
	}
	@Test
	@Parameters({"loginname","loginpassword","NewBranchName","NewStaff"})	
	  public void CreateStaff(String loginname, String loginpassword, String NewBranchName, String NewStaff) {
		GurukulaWelcomePage wcPage=PageFactory.initElements(driver, GurukulaWelcomePage.class);
		GurukulaLoginPage loginPage=PageFactory.initElements(driver, GurukulaLoginPage.class);
		//GurukulaBranchPage BranchPage = PageFactory.initElements(driver, GurukulaBranchPage.class);
		GurukulaStaffPage StaffPage = PageFactory.initElements(driver, GurukulaStaffPage.class);
		String Url = wcPage.NavigatetoLoginPage();
		Url=loginPage.LoginCredentials(loginname,loginpassword);
		currentPageUrl=currentUrl.HomePageUrl;
		System.out.println("Expected :" +currentPageUrl+" &current Url is :" +Url);
		Assert.assertEquals(Url, currentPageUrl);
		Url=wcPage.NavigateEntities("Staff");
		currentPageUrl=currentUrl.StaffUrl;
		//Assert.assertEquals(Url, currentPageUrl);
		boolean bCreateStaffResult=StaffPage.CreateUser(NewStaff, NewBranchName);
		if(bCreateStaffResult){
			System.out.println("Staff :"+createStaffName+" successfully created");
		}
		else {
			System.err.println("ERROR - Staff :"+createStaffName+" not created");
		}
	}
	
	@Test
		public void validateStaff() {
			GurukulaStaffPage StaffPage = PageFactory.initElements(driver, GurukulaStaffPage.class);
			StaffPage.validateUserDetails(createStaffName);
			StaffPage.validateUserDetails("InvalidUser");
	}
	@Test
		public void UpdateStaff() {
		GurukulaStaffPage StaffPage = PageFactory.initElements(driver, GurukulaStaffPage.class);
		StaffPage.UpdateUser(createStaffName, ModStaffName);
	}
	@Test
	public void UserLogOut() {
		GurukulaLoginPage loginPage = PageFactory.initElements(driver, GurukulaLoginPage.class);
		Objwait.SleepTime(4);
		loginPage.userLogOut();
	}
	@AfterTest	
 		public void CloseSession(){
 		
 			driver.quit();
 		}
}
