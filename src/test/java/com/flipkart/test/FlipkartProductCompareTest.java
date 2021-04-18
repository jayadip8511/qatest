package com.flipkart.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.flipkart.pages.FlipkartPage;
import com.flipkart.pages.GooglePage;
import com.init.Common;
import com.init.PropertiesFile;
import com.init.TestBase;


public class FlipkartProductCompareTest {
	FlipkartPage flipkartPage;
	GooglePage googlePage;
	WebDriver driver;
	static Common excel;
	PropertiesFile url = new PropertiesFile("Data\\url Configuration.properties");

	SoftAssert softAssert;

	
	Common excelTestResult;
	
	public FlipkartProductCompareTest() throws Exception {

		excel = new Common("Data\\ConsoleResult.xlsx");
		softAssert = new SoftAssert();
	}

	@BeforeTest
	public void initialization() throws Exception {


		driver = TestBase.init();
		System.out.println("Opened phoneAap1 session");
		flipkartPage = PageFactory.initElements(driver, FlipkartPage.class);
		googlePage = PageFactory.initElements(driver, GooglePage.class);
		
	}

	@BeforeMethod
	public void login() throws IOException, InterruptedException {
			driver.get(url.googleURL());
	}

	@AfterMethod
	public void endTestWork(ITestResult result) throws Exception {
		if (ITestResult.FAILURE == result.getStatus()) {
			String testname = "Fail";
			Common.Screenshot(driver, testname, "Fail " + result.getMethod().getMethodName());
			System.out.println(testname + " - " + result.getMethod().getMethodName());

		} else {
			String testname = "Pass";
			Common.Screenshot(driver, testname, "Pass " + result.getMethod().getMethodName());
		}
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {

		driver.quit();

	}


	@Test(priority = 1, retryAnalyzer = com.init.Retry.class)
	public void verify_flipkart_product_compare() throws Exception {

		googlePage.enterTextInSearchBox("Flipkart");
		googlePage.clickOnFlipkartLink();
		
		flipkartPage.closeLoginPopup();
		
		flipkartPage.openSubMenuOfAppliances();
		flipkartPage.openSubMenuOfAirConditioners();
		flipkartPage.clickOnWindowACs();
		
		flipkartPage.clickOnCheckboxOfProduct("2");
		flipkartPage.clickOnCheckboxOfProduct("3");
		flipkartPage.clickOnCheckboxOfProduct("6");
		
		flipkartPage.clickOnCompareButton();
		
		flipkartPage.getValueOfProductInColumn("2");
		flipkartPage.getValueOfProductInColumn("3");
		flipkartPage.getValueOfProductInColumn("4");
		
		flipkartPage.addTocartAllProducts();
		flipkartPage.enterPinCode("380015");
		flipkartPage.clickOnCheckLink();
		
		flipkartPage.getOutofStockProductName();
		
		flipkartPage.openPincodeDropdown();
		flipkartPage.enterPinCode("384001");
		flipkartPage.clickOnCheckLink();
		
		flipkartPage.getOutofStockProductName();
		

	}
	
}



