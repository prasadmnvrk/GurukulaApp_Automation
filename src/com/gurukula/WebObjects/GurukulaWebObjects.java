package com.gurukula.WebObjects;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GurukulaWebObjects {
	//WebDriver driver = new FirefoxDriver();
	int iRowCount;
	public static By LinkLogIn = By.linkText("login");
	public static By LinkRegisterAccount = By.linkText("*Register*account*");
	public static By LinkHome = By.xpath(".//*[@id='navbar-collapse']/ul/li[1]/a[2]");
	public static By DropDownAccountMenu = By.xpath("//li[@class='dropdown pointer']/a[@class='dropdown-toggle']/span");
	public static By DropDownItemAuthenticate = By.xpath(".//*[@id='navbar-collapse']/ul/li[2]/ul/li[1]/a");
	public static By DropDownItemRegister = By.xpath(".//*[@id='navbar-collapse']/ul/li[2]/ul/li[2]/a");
	public static By TextBoxUserName = By.id("username");
	public static By TextBoxPassword = By.id("password");
	public static By CBoxAutomaticLogin = By.id("rememberMe");
	public static By AuthenticationMessage = By.xpath("//div[@class='row']//div[1]/div[1]/strong");
	public static By ButtonAuthenticate = By.xpath("//form[@role='form']/button");
	public static By MenuEntities= By.xpath("//li[@ui-sref-active='active']/a[@class='dropdown-toggle']/span");
	public static By MenuItemBranch = By.xpath(".//*[@id='navbar-collapse']/ul/li[2]/ul/li[1]/a");
	public static By MenuItemStaff = By.xpath(".//*[@id='navbar-collapse']/ul/li[2]/ul/li[2]/a");
	public static By Settings = By.xpath(".//*[@id='navbar-collapse']/ul/li[3]/ul/li[1]/a");
	public static By Password = By.xpath(".//*[@id='navbar-collapse']/ul/li[3]/ul/li[2]/a");
	public static By Sessions = By.xpath(".//*[@id='navbar-collapse']/ul/li[3]/ul/li[3]/a");
	public static By ButtonCancel = By.xpath(".//*[@id='saveBranchModal']/div/div/form/div[3]/button[1]");
	public static By ButtonCreateNewBranch = By.xpath("html/body/div[3]/div[1]/div/div[1]/div/div[1]/button");
	public static By TextBoxBName = By.name("name");
	public static By TextBoxBCode = By.name("code");
	public static By TextBoxSName = By.xpath(".//*[@id='saveStaffModal']/div/div/form/div[2]/div[2]/input");
	public static By confirmDeleteMsg = By.xpath("//div[@class='modal-header']/h4");
	public static By buttonDelete_Msgbox = By.xpath("//div[@class='modal-footer']/button[2]");
	public static By buttonCancel_Msgbox = By.xpath("//div[@class='modal-footer']/button[1]");
	public static By ButtonSave = By.xpath(".//*[@id='saveBranchModal']/div/div/form/div[3]/button[2]");
	public static By DropdownAccount = By.xpath(".//*[@id='navbar-collapse']/ul/li[3]/a/span");
	public static By LogOut = By.xpath(".//*[@id='navbar-collapse']/ul/li[3]/ul/li[4]/a");
	public static By HTMLTable = By.xpath("//div[@class='table-responsive']/table/tbody");
	public static By btnCancel = By.xpath(".//*[@id='saveBranchModal']/div/div/form/div[3]/button[1]");
	public static By dropdownBranch = By.name("related_branch");
	public static By CreateStaff = By.xpath("//div[@class='col-md-4']/button");
	public static By StaffLabel = By.id("myStaffLabel");
	public static By UserTable = By.xpath("//div[@class='table-responsive']/table/tbody");
}
