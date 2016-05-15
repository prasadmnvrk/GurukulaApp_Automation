/*
 * This test case class is used to validate if the user is able to login automatically just with 
 * the user name and with out giving the password.
 * */
package com.gurukula.TestCases;

import org.testng.annotations.Test;

import com.gurukula.Utils.Utilities;
import com.gurukula.WebObjects.GurukulaWebObjects;
import com.gurukula.WebPageUrl.PageUrlDetails;
import com.gurukula.WebPages.GurukulaLoginPage;
import com.gurukula.WebPages.GurukulaWelcomePage;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class TC_ValidateAutomaticLogin {
	WebDriver driver;
	Utilities Objwait = new Utilities();
	GurukulaWebObjects welcomePageObjects;
	PageUrlDetails currentUrl;
	String currentUserName= "admin";
	String currentUserPassword = "admin";
	String currentPageUrl="";
	WebElement login;
	@BeforeSuite
	  public void beforeSuite() {
		driver = new FirefoxDriver();
		currentUrl = new PageUrlDetails();
		String HomePageUrl = currentUrl.HomePageUrl;
		System.out.println(HomePageUrl);
		driver.get(HomePageUrl);
		driver.manage().window().maximize();
	  }
	@Test
	@Parameters({"loginname"})
  public void ValidateAutomaticLogin(String loginname) {
		GurukulaWelcomePage wcPage=PageFactory.initElements(driver, GurukulaWelcomePage.class);
		GurukulaLoginPage loginPage=PageFactory.initElements(driver, GurukulaLoginPage.class);
		String Url = wcPage.NavigatetoLoginPage();
		currentPageUrl = currentUrl.LoginUrl;
		System.out.println("Expected :" +currentPageUrl+" &current Url is :" +Url);
		Assert.assertEquals(Url, currentPageUrl);
		loginPage.ValidateAutomaticLogin(loginname);
  }
	
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

  

}
