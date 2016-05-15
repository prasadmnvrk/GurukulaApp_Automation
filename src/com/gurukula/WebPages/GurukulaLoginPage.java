/*
 * This Class consists of functionality related to login page like login to application, automatic login, logout et al
 *  */
package com.gurukula.WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gurukula.Utils.Utilities;
import com.gurukula.WebObjects.*;
public class GurukulaLoginPage {
	
	WebDriver driver;
	GurukulaWebObjects wObjects = new GurukulaWebObjects();
	Utilities newUtil = new Utilities();
	private WebElement UserName;
	private WebElement Password;
	private WebElement LogIn;
	private WebElement Home;
	private WebElement UName;
	private WebElement UPasswd;
	private WebElement AuthenticationMsg;
	private WebElement ButtonAuthenticate;
	private WebElement LinkLogIn;
	
	public GurukulaLoginPage(WebDriver driver) {
		this.driver=driver;
		// TODO Auto-generated constructor stub
	}
	
	public String NavigateHomePage(){
		String Url = "";
		Home = driver.findElement(wObjects.LinkHome);
		Home.click();
		newUtil.WaitUntilLoad(3, driver, LogIn);
		Url = driver.getCurrentUrl();
		return Url;
	}
	public boolean LoginErrorMessage(){
		boolean bAuthMsgResult = false;
		AuthenticationMsg = driver.findElement(wObjects.AuthenticationMessage);
		if (AuthenticationMsg.isDisplayed()) {
			String AuthMsg = AuthenticationMsg.getText();
			if(AuthMsg.contains("Failed")){
				bAuthMsgResult=true;
			}
		}
		return bAuthMsgResult;
	}
	public String LoginCredentials(String UserName, String Password){
		String Url="";
		newUtil.SleepTime(2);
		UName = driver.findElement(wObjects.TextBoxUserName);
		UPasswd = driver.findElement(wObjects.TextBoxPassword);
		ButtonAuthenticate = driver.findElement(wObjects.ButtonAuthenticate);
		if(UName.isDisplayed() && UPasswd.isDisplayed() && ButtonAuthenticate.isDisplayed()){
			System.out.println("Username and Password fields are identified");
			UName.sendKeys(UserName);
			UPasswd.sendKeys(Password);
			ButtonAuthenticate.click();
		}
		else{
			System.err.println("UserName/Password field not found");
		}
		if(LoginErrorMessage()){
			System.err.println("Error Message displayed. Please check the credentials");
		}
		Home = driver.findElement(wObjects.LinkHome);
		newUtil.WaitUntilLoad(5, driver, Home);
		Url=driver.getCurrentUrl();
		return Url;
	}
	public void userLogOut() {
		newUtil.SleepTime(5);
		WebElement ddAccount=driver.findElement(wObjects.DropdownAccount);
		if(ddAccount.isDisplayed()) {
			ddAccount.click();
			WebElement Logout=driver.findElement(wObjects.LogOut);
			newUtil.WaitUntilLoad(5, driver, Logout);
			newUtil.SleepTime(3);
			Logout.click();
			newUtil.SleepTime(3);
			LinkLogIn = driver.findElement(wObjects.LinkLogIn);
			newUtil.WaitUntilLoad(5, driver, LinkLogIn);
		}
		else {
			System.err.println("ERROR - could not find Account Menu");
		}
		
	}
	
	public void ValidateAutomaticLogin(String UserName){
		newUtil.SleepTime(3);
		UName = driver.findElement(wObjects.TextBoxUserName);
		newUtil.SleepTime(3);
		ButtonAuthenticate = driver.findElement(wObjects.ButtonAuthenticate);
		if(UName.isDisplayed() && ButtonAuthenticate.isDisplayed()){
			System.out.println("Username and Password fields are identified");
			UName.sendKeys(UserName);
			ButtonAuthenticate.click();
		}
		else{
			System.err.println("UserName/Password field not found");
		}
		newUtil.SleepTime(3);
		AuthenticationMsg = driver.findElement(wObjects.AuthenticationMessage);
		if (AuthenticationMsg.isDisplayed()) {
			String AuthMsg = AuthenticationMsg.getText();
			System.err.println("Error Message displayed : "+AuthMsg);
			if(AuthMsg=="Authentication failed*"){
				System.err.println("ERROR - Cannot login with Automatic login");
			}
		}
	}
}
