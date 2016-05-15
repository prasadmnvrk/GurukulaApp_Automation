/*
 * This test case class is used to check if  user is able to delete a branch from the Branch page.
 * 
 * */
package com.gurukula.TestCases;

import org.testng.annotations.Test;

import com.gurukula.Utils.Utilities;
import com.gurukula.WebObjects.GurukulaWebObjects;
import com.gurukula.WebPageUrl.PageUrlDetails;
import com.gurukula.WebPages.GurukulaBranchPage;
import com.gurukula.WebPages.GurukulaLoginPage;
import com.gurukula.WebPages.GurukulaWelcomePage;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class TC_DeleteBranch {
	WebDriver driver;
	Utilities Objwait = new Utilities();
	GurukulaWebObjects welcomePageObjects;
	PageUrlDetails currentUrl;
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
	
	@Parameters({"loginname","loginpassword"})	
	@Test
		public void NavigateLoginPage(String loginname, String loginpassword){
			GurukulaWelcomePage wcPage=PageFactory.initElements(driver, GurukulaWelcomePage.class);
			GurukulaLoginPage loginPage=PageFactory.initElements(driver, GurukulaLoginPage.class);
			String Url = wcPage.NavigatetoLoginPage();
			currentPageUrl = currentUrl.LoginUrl;
			System.out.println("Expected :" +currentPageUrl+" &current Url is :" +Url);
			Assert.assertEquals(Url, currentPageUrl);
			Url=loginPage.LoginCredentials(loginname,loginpassword);
			wcPage.NavigateEntities("Branch");
		}
	@Parameters({"DelBranchName","DelBranchCode"})
	@Test
	public void DeleteBranch(String DelBranchName,String DelBranchCode){
		GurukulaBranchPage BranchPage = PageFactory.initElements(driver, GurukulaBranchPage.class);
		BranchPage.DeleteBranch(DelBranchName, DelBranchCode);
	}
	@Test
	public void userLogOut() {
		GurukulaLoginPage loginPage=PageFactory.initElements(driver, GurukulaLoginPage.class);
		loginPage.userLogOut();
	}
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

  

}
