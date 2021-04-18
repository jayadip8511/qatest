package com.init;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

	static WebDriver driver;
	// public static WebDriver driver1 =null;


	public static WebDriver init() {

		System.setProperty("webdriver.chrome.driver", "Data\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--use-fake-ui-for-media-stream");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		// driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

		return driver;

	}

	public static WebDriver init1() throws MalformedURLException {

		final String USERNAME = "bsuser74416";
		final String AUTOMATE_KEY = "VYzNesrDGwaVQXzh37Cj";
		final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--use-fake-ui-for-media-stream");

		DesiredCapabilities caps = DesiredCapabilities.chrome();

		// DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("os", "Windows");
		caps.setCapability("os_version", "10");
		caps.setCapability("browser", "Chrome");
		caps.setCapability("browser_version", "80");
		caps.setCapability("browserstack.idleTimeout", "300");
		caps.setCapability("name", "jaydeepprajapati2's First Test");
		caps.setCapability(ChromeOptions.CAPABILITY, options);

		driver = new RemoteWebDriver(new URL(URL), caps);

		driver.manage().window().maximize();
		// driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		return driver;

	}
	


}
