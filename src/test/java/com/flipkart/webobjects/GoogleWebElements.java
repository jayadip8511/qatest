package com.flipkart.webobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactoryFinder;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleWebElements {

	@FindBy(xpath = "//input[@title='Search']")
	protected WebElement googleSearchBox;
	@FindBy(xpath="//h3[text()='Flipkart']/following-sibling::div//cite")
	protected WebElement flipkartLink;

}
	