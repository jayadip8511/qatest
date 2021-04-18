package com.flipkart.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.expectThrows;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.webobjects.GoogleWebElements;

public class GooglePage extends GoogleWebElements {

	WebDriver driver;
	WebDriverWait wait;
	WebDriverWait wait1;
	WebDriverWait wait2;
	Actions action;
	JavascriptExecutor js;

	public GooglePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 10);
		action = new Actions(this.driver);
		js = (JavascriptExecutor) driver;
	}

	public void enterTextInSearchBox(String searchText) {
		wait.until(ExpectedConditions.visibilityOf(googleSearchBox)).sendKeys(searchText);
		googleSearchBox.sendKeys(Keys.ARROW_DOWN);
		googleSearchBox.sendKeys(Keys.ENTER);

	}

	public void clickOnFlipkartLink() {
		wait.until(ExpectedConditions.visibilityOf(flipkartLink)).click();
	}


}
