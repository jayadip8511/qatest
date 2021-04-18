package com.flipkart.webobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactoryFinder;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FlipkartWebElements {

	@FindBy(xpath = "//input[@title='Search']")
	protected WebElement googleSearchBox;
	@FindBy(xpath="//h3[text()='Flipkart']/following-sibling::div//cite")
	protected WebElement flipkartLink;
	@FindBy(xpath = "//img[@alt='Appliances']")
	protected WebElement appliancesLink;
	@FindBy(xpath="//a[text()='Air Conditioners']")
	protected WebElement airconditioners;
	@FindBy(xpath="//a[text()='Window ACs']")
	protected WebElement WindowACs;
	@FindBy(xpath="//span[contains(text(),'COMPARE')]/parent::span/parent::a")
	protected WebElement comparebtn;
	@FindBy(xpath = "//a[@href='/pages/privacypolicy']")
	protected WebElement privacylink;
	@FindBy(xpath = "//span[contains(.,'Deliver to')]")
	protected WebElement placeOrderBtn;
	@FindBy(xpath = "//input[contains(@placeholder,'Enter delivery pincode')]")
	protected WebElement pincodeTextbox;
	@FindBy(xpath = "//input[contains(@placeholder,'Enter delivery pincode')]/following-sibling::span[text()='Check']")
	protected WebElement checkLink;
	@FindBy(xpath = "//span[contains(text(),'380015')]/parent::div/following-sibling::img")
	protected WebElement pincodeDropdown;
	
	
	
	//div//span/following-sibling::div[contains(.,'Currently out of stock for')]
	//div//span/following-sibling::div[contains(.,'Currently out of stock for')]/parent::div//a
	

}
	