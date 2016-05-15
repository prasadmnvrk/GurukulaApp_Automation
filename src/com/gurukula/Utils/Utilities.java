/*
 * This class consists of all common functions used in Automation
 * */
package com.gurukula.Utils;

import org.eclipse.jdt.internal.compiler.flow.ExceptionHandlingFlowContext;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	public void WaitUntilLoad(long iWaitTime, WebDriver driver, WebElement by) {
		iWaitTime=iWaitTime*1000;
		WebDriverWait waittime=new WebDriverWait(driver, iWaitTime);
		waittime.until(ExpectedConditions.visibilityOf(by));
		try {
			Thread.sleep(iWaitTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SleepTime(long iWaitTime){
		iWaitTime=iWaitTime*1000;
		try {
			Thread.sleep(iWaitTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
