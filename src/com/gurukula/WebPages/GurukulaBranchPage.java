/*
 * This page consists of Functions related to creation of a branch, Search and Deletion of branch
 *  */
package com.gurukula.WebPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gurukula.Utils.*;
import com.gurukula.WebObjects.*;
public class GurukulaBranchPage extends GurukulaWelcomePage {
	WebElement Settings;
	public WebElement btnCancel;
	public WebElement btnSave;
	public WebElement CreateBranch;
	public WebElement SearchBranch;
	public WebElement buttonDelete;
	public WebElement buttonEdit;
	public WebElement confirmDeleteMsg;
	public WebElement buttonDelete_Msgbox;
	public WebElement buttonCancel_Msgbox;
	public WebElement BranchTable;
	public WebElement TextBoxName;
	public WebElement TextBoxCode;
	GurukulaWebObjects wObjects = new GurukulaWebObjects();
	Utilities util = new Utilities();
	public GurukulaBranchPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public void CreateNewBranch(String Branchname, String BranchCode) {
		WebElement btnCancel=driver.findElement(wObjects.btnCancel);
		util.SleepTime(3);
		//Identifying "Create a New Branch" button
		CreateBranch=driver.findElement(wObjects.ButtonCreateNewBranch);
		CreateBranch.click();
		util.SleepTime(3);
		TextBoxName=driver.findElement(wObjects.TextBoxBName);
		TextBoxCode=driver.findElement(wObjects.TextBoxBCode);
		btnSave=driver.findElement(wObjects.ButtonSave);
		btnCancel=driver.findElement(wObjects.ButtonCancel);
		//driver.switchTo().frame(0);
		TextBoxName.sendKeys(Branchname);
		TextBoxCode.sendKeys(BranchCode);
		btnSave=driver.findElement(wObjects.ButtonSave);
		if(!btnSave.isEnabled()){
			System.err.println("ERROR - Invalid Branch name/Code. Please enter valid inputs");
			btnCancel.click();
			System.err.println("ERROR - Branch not created");
		}
		else {
			btnSave.click();
		}
		util.SleepTime(3);
	}
	public int validateBranchDetails(String BranchName, String BranchCode) {
		BranchTable=driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody"));
		String currentBranchName="";
		String currentBranchCode="";
		String currentCellText="";
		int iBranchRow=0;
		if(BranchTable.isDisplayed()) {
			List<WebElement> rows=BranchTable.findElements(By.tagName("tr"));
			System.out.println("Total no. of rows displayed in the table :"+rows.size());
			if(rows.size()==0){
				System.err.print("No Branch details are available");
			}
			if(rows.size()>0) {
				for(int iRowCount=0;iRowCount<rows.size();iRowCount++) {
					//buttonDelete=driver.findElement(By.xpath("//tbody/tr["+iRowCount+"]/td[4]/button[3]"));
					//buttonEdit=driver.findElement(By.xpath("//tbody/tr["+iRowCount+"]/td[4]/button[2]"));
					List<WebElement> columns=rows.get(iRowCount).findElements(By.tagName("td"));
					System.out.println("Total no. of Colums displayed :"+columns.size());
					for (int iColumnCount=0;iColumnCount<3;iColumnCount++) {
						if (iColumnCount==1){
							currentCellText=columns.get(iColumnCount).getText();
							System.out.println("Current Cell text displayed: "+currentCellText);
							if(currentCellText==BranchName){
								System.out.println("Branch name: '"+BranchName+"' exists");
							}
						if(iColumnCount==2){
							currentCellText=columns.get(iColumnCount).getText();
							System.out.println("Current Cell text displayed: "+currentCellText);
							if(currentCellText==BranchCode){
								System.out.println("Branch Code: '"+BranchCode+"' exists");
								iBranchRow=iRowCount;
								break;
							}
						}
						/*	else {
								System.err.println("Branch name does not match");
							}*/
						}
					}
					if(iRowCount>=rows.size()){
						System.err.println("Could not find Branch Name: '"+BranchName+"' and with Branch Code: '"+BranchCode+"'");
					}
					
				}
			}
		}
		else {
			System.out.println("Branch details not displayed, possibly due to unavailability of branch details");
		}
		return iBranchRow;
	}
	
	public void DeleteBranch(String BranchName, String BranchCode) {
		int iBranchRow = validateBranchDetails(BranchName, BranchCode);
		if(iBranchRow==0){
			System.err.println("ERROR - Could not find the current branch :"+BranchName);
		}
		if(iBranchRow>0) {
			buttonDelete=driver.findElement(By.xpath("//tbody/tr["+iBranchRow+"]/td[4]/button[3]"));
			buttonDelete.click();
			confirmDeleteMsg=driver.findElement(wObjects.confirmDeleteMsg);
			buttonDelete_Msgbox=driver.findElement(wObjects.buttonDelete_Msgbox);
			buttonCancel_Msgbox=driver.findElement(wObjects.buttonCancel_Msgbox);
			util.SleepTime(3);
			util.WaitUntilLoad(3, driver, buttonDelete_Msgbox);
			buttonDelete_Msgbox.click();
			util.SleepTime(3);
			if(confirmDeleteMsg.isDisplayed()){
				System.err.println("ERROR - Could not delete the selected Branch");
				buttonCancel_Msgbox.click();
				util.SleepTime(3);
			}
			iBranchRow = validateBranchDetails(BranchName, BranchCode);
			if(iBranchRow==0){
				System.out.println("Branch :"+BranchName+" delete successfully");
			}
			else {
				System.err.println("Branch :"+BranchName+" not deleted from Branch page");
			}
		}
	}
}
