/*
 * This Test case class is used to check if user is able to login to the application with 
 * with valid credentials or not
 * */
package com.gurukula.TestCases;

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

public class TC_WelcomePage {
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
			currentPageUrl=currentUrl.HomePageUrl;
			System.out.println("Expected :" +currentPageUrl+" &current Url is :" +Url);
			Assert.assertEquals(Url, currentPageUrl);
			Objwait.SleepTime(4);
			loginPage.userLogOut();
	}
	 	
	 @AfterTest	
	 	public void CloseSession(){
	 		
	 		driver.quit();
	 	}
}
