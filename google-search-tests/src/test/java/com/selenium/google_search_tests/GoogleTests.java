package com.selenium.google_search_tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import test.java.com.selenium.google_pageobject.GoogleSearchPage;
import test.java.com.selenium.google_pageobject.GoogleSearchResultsPage;

public class GoogleTests {

	WebDriver driver;
	GoogleSearchPage gleSrchPg;
	GoogleSearchResultsPage gleSrchRsltPg;

	/*
	 * On Start up launch www.google.com
	 */
	@BeforeTest
	public void launchURL() {
		driver = new FirefoxDriver();
		driver.get("http://www.google.com/");
		driver.manage().window().maximize();
	}

	/*
	 * Verify that there are more than 1,000,000,000 results
	 */
	@Test
	public void googleSearchTest() throws Exception {

		double resultCount;
		gleSrchPg = new GoogleSearchPage(driver);
		Assert.assertEquals(gleSrchPg.isGooglePageLoaded(), true,
				"Google Search Page is not loaded");
		gleSrchRsltPg = gleSrchPg.searchItems("TEST");
		Assert.assertEquals(gleSrchRsltPg.isGoogleResultsPageLoaded(), true,
				"Google Search Results Page is not loaded");
		resultCount = gleSrchRsltPg.getTheResultsCount();
		Assert.assertEquals(resultCount > 1000000000, true,
				"Result is not more than 1,000,000,000 and actual count is - "
						+ (int) resultCount);
	}

	/*
	 * Quit the browser after completing the execution
	 */
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
