package com.flipkart.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.expectThrows;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.webobjects.FlipkartWebElements;

public class FlipkartPage extends FlipkartWebElements {

	WebDriver driver;
	WebDriverWait wait;
	WebDriverWait wait1;
	WebDriverWait wait2;
	Actions action;
	JavascriptExecutor js;

	public FlipkartPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 100);
		wait1 = new WebDriverWait(this.driver, 1);
		wait2 = new WebDriverWait(this.driver, 10);
		action = new Actions(this.driver);
		js = (JavascriptExecutor) driver;
	}

	public void wait_reactjs() {
		JavascriptExecutor js = (JavascriptExecutor) this.driver;

		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			System.out.println("Page Is loaded.");
			return;
		}

		for (int i = 0; i < 60; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}

	public String getTitle() {
		String signupTitle = driver.getTitle();
		return signupTitle;
	}

	public String validateforgotPasswordPageTitle() {
		wait.until(ExpectedConditions.titleContains("Forgot Password | Callhippo.com"));
		return driver.getTitle();
	}

	public void enterTextInSearchBox(String searchText) {
		wait.until(ExpectedConditions.visibilityOf(googleSearchBox)).sendKeys(searchText);
		googleSearchBox.sendKeys(Keys.ARROW_DOWN);
		googleSearchBox.sendKeys(Keys.ENTER);

	}

	public void clickOnFlipkartLink() {
		wait.until(ExpectedConditions.visibilityOf(flipkartLink)).click();
	}

	public void closeLoginPopup() {
		wait.until(ExpectedConditions.visibilityOf(privacylink));
		action.sendKeys(Keys.ESCAPE).build().perform();
	}

	public void openSubMenuOfAppliances() {
		wait.until(ExpectedConditions.visibilityOf(appliancesLink));
		action.moveToElement(appliancesLink).build().perform();

	}

	public void openSubMenuOfAirConditioners() {

		wait.until(ExpectedConditions.visibilityOf(airconditioners));
		action.moveToElement(airconditioners).build().perform();
	}

	public void clickOnWindowACs() {
		wait.until(ExpectedConditions.visibilityOf(WindowACs)).click();

	}

	public void clickOnCheckboxOfProduct(String numberOfsequance) {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
				"(//label/span[contains(text(),'Add to Compare')]/parent::label/parent::div//input/following-sibling::div)["
						+ numberOfsequance + "]"))))
				.click();
	}

	public void clickOnCompareButton() {
		wait.until(ExpectedConditions.visibilityOf(comparebtn)).click();
	}

	public void getValueOfProductInColumn(String columnNumber) {
		String price = wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("(//div[@class='row']/div[contains(@class,'col')]["
						+ columnNumber + "]//div[contains(text(),'₹')])[1]"))))
				.getText();
		int a = new Integer(columnNumber);
		int productNumber = a - 1;
		System.out.println("Product " + productNumber + " " + price);
	}

	public void addTocartAllProducts() throws InterruptedException {

//		wait.until(
//				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//button[contains(.,'ADD TO CART')])[3]"))));

//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//button[contains(.,'ADD TO CART')])["+index+"]")))).click();
//
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[contains(.,'ADD TO CART')])["+index+"]")));
		for (int i = 1; i <= 3; i++) {
			Thread.sleep(5000);
			action.click(driver.findElement(By.xpath("(//button[contains(.,'ADD TO CART')])[1]"))).build().perform();

			int attempt2 = 0;
			int MAX_ATTEMPTS2 = 5;

			while (attempt2 < MAX_ATTEMPTS2) {
				try {

					wait.until(ExpectedConditions.visibilityOf(placeOrderBtn));

					break;
				} catch (StaleElementReferenceException e) {
//					System.out.println(attempt2);
					// if ( error.message.includes( "StaleElementReference" ) )

					attempt2++;
				}
			}

			if (i != 3) {
				driver.navigate().back();
			}

		}
	}

	public void addToCartAllProducts() throws InterruptedException {

//		wait.until(
//				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(.,'ADD TO CART')]"))));

		int attempt = 0;
		int MAX_ATTEMPTS = 5;

		while (attempt < MAX_ATTEMPTS) {
			try {

				wait.until(ExpectedConditions
						.visibilityOf(driver.findElement(By.xpath("(//button[contains(.,'ADD TO CART')])[3]"))));

				break;
			} catch (StaleElementReferenceException e) {
//				System.out.println(attempt);
				// if ( error.message.includes( "StaleElementReference" ) )
				attempt++;
			}
		}

		Thread.sleep(5000);
		List<WebElement> ele = driver.findElements(By.xpath("//button[contains(.,'ADD TO CART')]"));

		for (int i = 1; i <= ele.size(); i++) {
			Thread.sleep(5000);

			int attempt1 = 0;
			int MAX_ATTEMPTS1 = 5;

			while (attempt1 < MAX_ATTEMPTS1) {
				try {

					wait.until(ExpectedConditions.visibilityOf(ele.get(i)));
					ele.get(i).click();

					break;
				} catch (StaleElementReferenceException e) {
//					System.out.println(attempt1);
					driver.navigate().refresh();
					attempt1++;
				}
			}

			int attempt2 = 0;
			int MAX_ATTEMPTS2 = 5;

			while (attempt2 < MAX_ATTEMPTS2) {
				try {

					wait.until(ExpectedConditions.visibilityOf(placeOrderBtn));

					break;
				} catch (StaleElementReferenceException e) {
//					System.out.println(attempt2);
					// if ( error.message.includes( "StaleElementReference" ) )

					attempt2++;
				}
			}

			if (i != 3) {
				driver.navigate().back();
			}
		}
	}

	public void enterPinCode(String pincode) {
		wait.until(ExpectedConditions.visibilityOf(pincodeTextbox)).sendKeys(pincode);
	}

	public void clickOnCheckLink() {
		wait.until(ExpectedConditions.visibilityOf(checkLink)).click();
	}

	public void openPincodeDropdown() {
		wait.until(ExpectedConditions.visibilityOf(pincodeDropdown)).click();
	}

	public void getOutofStockProductName() {

		
		int attempt2 = 0;
		int MAX_ATTEMPTS2 = 5;

		while (attempt2 < MAX_ATTEMPTS2) {
			try {

				wait.until(ExpectedConditions.visibilityOf(
						driver.findElement(By.xpath("//div//span/following-sibling::div/parent::div//a")))).getText();
				
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
						"//div//span/following-sibling::div/parent::div//a/parent::div/parent::div//div[contains(text(),'Currently out of stock for')]")))).getText();

				break;
			} catch (StaleElementReferenceException e) {
//				System.out.println(attempt2);
				// if ( error.message.includes( "StaleElementReference" ) )

				attempt2++;
			}
		}

		List<WebElement> allProduct = driver
				.findElements(By.xpath("//div//span/following-sibling::div/parent::div//a"));

		// div//span/following-sibling::div/parent::div//a[text()='Panasonic 1.5 Ton
		// Window AC - White']/parent::div/parent::div//div[contains(text(),'Currently
		// out of stock for')]

		if (allProduct.size() > 0) {

			for (int i = 0; i < allProduct.size(); i++) {

				
				String productName = allProduct.get(i).getText();

				String pr1 = productName.substring(1, 30);
				String xpath1 = "//div//span/following-sibling::div/parent::div//a[contains(text(),'"+pr1+"')]/parent::div/parent::div//div[contains(text(),'Currently out of stock for')]";
			System.out.println(xpath1);
				List<WebElement> outOfStock = driver.findElements(By.xpath(xpath1));
				
			int size =	outOfStock.size();

			System.out.println("------ Size: "+size);
			if(size>0) {
				System.out.println("\n"+productName);
				System.out.println(outOfStock.get(0).getText()+"\n");
				
			}else {
				System.out.println("\n"+productName);
				System.out.println("In Stock \n");
				
			}
			
			
			}

		}

//		List<WebElement> ele = driver.findElements(By
//				.xpath("//div//span/following-sibling::div[contains(.,'Currently out of stock for')]/parent::div//a"));
//		List<WebElement> elePin = driver
//				.findElements(By.xpath("//div//span/following-sibling::div[contains(.,'Currently out of stock for')]"));
//
//		if (ele.size() > 0) {
//
//			for (int i = 0; i < ele.size(); i++) {
//				System.out.println("\n" + ele.get(i).getText());
//				System.out.println(elePin.get(i).getText() + "\n");
//			}
//
//		}

	}

}
