/*
 * This Class consists of WebElements, functions related to Welcome Page of Gurukula Applications
 *  */
package com.gurukula.WebPages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.pagefactory.*;
import com.gurukula.Utils.Utilities;
import com.gurukula.WebObjects.*;
import bsh.util.Util;

public class GurukulaWelcomePage {
	WebDriver driver;
	GurukulaWebObjects wcPageObjects = new GurukulaWebObjects();
	public WebElement LinkLogIn;
	private WebElement buttonAuthenticate;
	private WebElement Account;
	private WebElement Register;
	private WebElement MenuItemAuthenticate;
	private WebElement Entities;
	private WebElement Branch;
	private WebElement Staff;
	Utilities newUtil = new Utilities();
	public GurukulaWelcomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	
	public String NavigatetoLoginPage() {
		String Url="";
		newUtil.SleepTime(5);
		LinkLogIn = driver.findElement(wcPageObjects.LinkLogIn);
		if(LinkLogIn.isDisplayed()){
			System.out.println("Login link found");
			newUtil.SleepTime(3);
		}
		LinkLogIn.click();
		buttonAuthenticate = driver.findElement(wcPageObjects.ButtonAuthenticate);
		newUtil.SleepTime(5);
		newUtil.WaitUntilLoad(10, driver, buttonAuthenticate);
		Url=driver.getCurrentUrl();
		return Url;
	}
	public String NavigatetoRegisterUser() {
		newUtil.SleepTime(4);
		String Url = "";
		Account = driver.findElement(wcPageObjects.DropDownAccountMenu);
		Account.click();
		Register = driver.findElement(wcPageObjects.DropDownItemRegister);
		newUtil.WaitUntilLoad(3, driver, Register);
		if(Register.isDisplayed())
		{
			System.out.println("Register Menu Item displayed");
			Register.click();
		}
		return Url;
	}
	public String NavigatetoAuthenticate(){
		newUtil.SleepTime(5);
		String Url="";
		Account = driver.findElement(wcPageObjects.DropDownAccountMenu);
		Account.click();
		MenuItemAuthenticate = driver.findElement(wcPageObjects.DropDownItemAuthenticate);
		newUtil.WaitUntilLoad(3, driver, MenuItemAuthenticate);
		if(MenuItemAuthenticate.isDisplayed()){
			System.out.println("Authenticate Menu Item displayed");
			MenuItemAuthenticate.click();
		}
		return Url;
	}
	public String NavigateEntities(String EntitiesOption) {
		String Url;
		Entities=driver.findElement(By.xpath(".//*[@id='navbar-collapse']/ul/li[2]/a/span"));
		Entities.click();
		//boolean bEntityOptionPresent=false;
		newUtil.SleepTime(3);
		if(EntitiesOption=="Branch"){
			System.out.println("Option Branch is selected");
			Branch=driver.findElement(wcPageObjects.MenuItemBranch);
			Branch.click();
			
		}
		else if (EntitiesOption=="Staff"){
			System.out.println("Option Staff is selected");
			Staff = driver.findElement(wcPageObjects.MenuItemStaff);
			Staff.click();
			
		}
		else {
			System.err.println("Invalid Option");
		}
		Url=driver.getCurrentUrl();
		return Url;
	}
	
}
