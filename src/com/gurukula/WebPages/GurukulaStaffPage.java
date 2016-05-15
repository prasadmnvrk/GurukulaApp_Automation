/* 
 * This class consist of functionalities related to Create Staff, Search Staff and Delete the staff
 * */
package com.gurukula.WebPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.gurukula.Utils.*;
import com.gurukula.WebPageUrl.*;
import com.gurukula.WebObjects.*;

public class GurukulaStaffPage extends GurukulaBranchPage {
	Utilities util = new Utilities();
	WebElement UserTable;
	WebElement textBoxName;
	public GurukulaStaffPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public boolean launchCreateStaffForm(){
		System.out.println("Trying to launch Create Staff Form");
		boolean bStaffFormDisplay = false;
		util.SleepTime(8);
		WebElement buttonCreateStaff = driver.findElement(wObjects.CreateStaff);
		if(!buttonCreateStaff.isDisplayed()){
			System.err.println("Could not find Create Staff button");
		}
		buttonCreateStaff.click();
		util.SleepTime(3);
		WebElement StaffLabel = driver.findElement(wObjects.StaffLabel);
		if(StaffLabel.isDisplayed()){
			System.out.println("Create Staff Form is displayed");
			bStaffFormDisplay=true;
		}
		else {
			System.err.println("Create Staff Form not displayed");
		}
		util.SleepTime(3);
		return bStaffFormDisplay;
	}
	public boolean CreateUser(String UserName,String BranchName) {
		
		Select dropdownBranch = new Select(driver.findElement(wObjects.dropdownBranch));
		List<WebElement> BranchOptions = dropdownBranch.getOptions();
		String currentOption = "";
		boolean bCreateStaffResult=false;
		if(launchCreateStaffForm()){
			textBoxName = driver.findElement(wObjects.TextBoxSName);
			textBoxName.sendKeys(UserName);
			util.SleepTime(3);
			for(int iOptionsCount=0;iOptionsCount<BranchOptions.size();iOptionsCount++){
				currentOption=BranchOptions.get(iOptionsCount).getText().trim();
				System.out.println("Current bank name :"+currentOption+" Required Branch :"+BranchName);
				if(currentOption==BranchName){
					System.out.println("BranchName is available under Branch dropdownlist");
					dropdownBranch.selectByVisibleText(BranchName);
					util.SleepTime(4);
					btnSave=driver.findElement(wObjects.ButtonSave);
					btnCancel=driver.findElement(wObjects.btnCancel);
					util.SleepTime(3);
					if(!btnSave.isEnabled()){
						System.err.println("ERROR - Bank name not found");
						btnCancel.click();
					}
					else{
						btnSave.click();
						bCreateStaffResult=true;
						break;
					}
				}
				if(iOptionsCount>=BranchOptions.size()-1){
					System.err.println("Could not find the branch name");
					btnCancel.click();
				}
			}
		}
		else {
			System.err.println("Create Staff Form not launched");
		}
		util.SleepTime(3);
		return bCreateStaffResult;
	}
	public int validateUserDetails(String UserName) {
		UserTable=driver.findElement(wObjects.UserTable);
		String currentUserName="";
		String currentUserCode="";
		String currentCellText="";
		int iUserRow=0;
		if(BranchTable.isDisplayed()) {
			List<WebElement> rows=BranchTable.findElements(By.tagName("tr"));
			System.out.println("Total no. of rows displayed in the table :"+rows.size());
			if(rows.size()==0){
				System.err.print("No Branch details are available");
			}
			if(rows.size()>0) {
				for(int iRowCount=0;iRowCount<rows.size();iRowCount++) {
					List<WebElement> columns=rows.get(iRowCount).findElements(By.tagName("td"));
					System.out.println("Total no. of Colums displayed :"+columns.size());
					for (int iColumnCount=0;iColumnCount<3;iColumnCount++) {
						if (iColumnCount==1){
							currentCellText=columns.get(iColumnCount).getText();
							System.out.println("Current Cell text displayed: "+currentCellText);
							if(currentCellText==UserName){
								System.out.println("Staff name: '"+UserName+"' exists");
								iUserRow=iRowCount;
								break;
							}
						}
					}
					if(iRowCount>=rows.size()){
						System.err.println("Could not find Staff Name: '"+UserName+"'");
					}
					
				}
			}
		}
		else {
			System.out.println("Staff details not displayed, possibly due to unavailability of branch details");
		}
		return iUserRow;
	}
	public void UpdateUser(String UserName, String ModifyUserName) {
		int iUserRow = validateUserDetails(UserName);
		if(iUserRow>0) {
			buttonEdit=driver.findElement(By.xpath("//tbody/tr["+iUserRow+"]/td[4]/button[2]"));
			buttonEdit.click();
			System.out.println("Staff Form launched to modify username");
			btnSave=driver.findElement(wObjects.ButtonSave);
			btnCancel=driver.findElement(wObjects.btnCancel);
			WebElement textBoxName = driver.findElement(wObjects.TextBoxSName);
			textBoxName.sendKeys(ModifyUserName);
			if(btnSave.isEnabled()){
				btnSave.click();
				util.SleepTime(3);
			}
			else {
				System.err.println("ERROR - Could not update user name due to invalid inputs");
				btnCancel.click();
				util.SleepTime(3);
			}
		}
		util.SleepTime(3);
		iUserRow = validateUserDetails(ModifyUserName);
		if(iUserRow>0){
			System.out.println("Username :"+ModifyUserName+" modified successfully");
		}
		else {
			System.err.println("Username :"+ModifyUserName+" not modified from Staff page");
		}
	}
}
