/*
 * This test case class is used to check whether user is able to create a Branch,
 * search branch or not.
 * */
package com.gurukula.TestCases;

import org.testng.annotations.Test;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.net.NetworkUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.gurukula.Utils.*;
import com.gurukula.WebObjects.*;
import com.gurukula.WebPages.GurukulaLoginPage;
import com.gurukula.WebPages.GurukulaWelcomePage;
import com.gurukula.WebPages.*;
import com.gurukula.WebPageUrl.*;
import org.openqa.selenium.support.pagefactory.*;

public class TC_BranchOperations {
	WebDriver driver;
	Utilities Objwait = new Utilities();
	GurukulaWebObjects welcomePageObjects;
	PageUrlDetails currentUrl;
	String currentUserName= "admin";
	String currentUserPassword = "admin";
	String currentPageUrl="";
	WebElement login;
	@BeforeSuite	
		public void BrowserLaunch() {
			driver = new FirefoxDriver();
			currentUrl = new PageUrlDetails();
			String HomePageUrl = currentUrl.HomePageUrl;
			System.out.println(HomePageUrl);
			driver.get(HomePageUrl);
			driver.manage().window().maximize();
			
	}
	@Parameters({"loginname","loginpassword","NewBranchName","NewBranchCode"})
	@Test
		public void createBranch(String loginname, String loginpassword,String NewBranchName, String NewBranchCode) {
			GurukulaWelcomePage wcPage=PageFactory.initElements(driver, GurukulaWelcomePage.class);
			GurukulaLoginPage loginPage=PageFactory.initElements(driver, GurukulaLoginPage.class);
			GurukulaBranchPage BranchPage = PageFactory.initElements(driver, GurukulaBranchPage.class);
			String Url = wcPage.NavigatetoLoginPage();
			currentPageUrl = currentUrl.LoginUrl;
			System.out.println("Expected :" +currentPageUrl+" &current Url is :" +Url);
			Assert.assertEquals(Url, currentPageUrl);
			Url=loginPage.LoginCredentials(loginname,loginpassword);
			currentPageUrl=currentUrl.HomePageUrl;
			System.out.println("Expected :" +currentPageUrl+" &current Url is :" +Url);
			Assert.assertEquals(Url, currentPageUrl);
			Url=wcPage.NavigateEntities("Branch");
			currentPageUrl=currentUrl.BranchUrl;
			System.out.println("Expected :" +currentPageUrl+" &current Url is :" +Url);
			//BranchPage.CreateNewBranch("HDFC", "HD1");
			BranchPage.CreateNewBranch(NewBranchName, NewBranchCode);
		}
	
	@Parameters({"NewBranchName","NewBranchCode"})
	@Test
	
	public void validateBranch(String NewBranchName, String NewBranchCode){
		GurukulaBranchPage BranchPage = PageFactory.initElements(driver, GurukulaBranchPage.class);
		int iBranchRow;
		iBranchRow=BranchPage.validateBranchDetails(NewBranchName, NewBranchCode);
		if(iBranchRow>0){
			System.out.println("Branch found");
		}
		else {
			System.err.println("Branch not found");
		}
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
