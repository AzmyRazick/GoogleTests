package test.java.com.selenium.google_pageobject;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * Unit test for simple App.
 */
public class GoogleSearchPage {
	WebDriver driver;

	/*
	 * Google Search Page Constructor
	 */
	public GoogleSearchPage(WebDriver _driver) {
		this.driver = _driver;
	}

		/*
		 * Verifying Google Search Page is loaded
		 * 
		 * @return boolean
		 */
		public boolean isGooglePageLoaded() throws Exception {

		WebElement icoGoogleSearch = driver.findElement(By
				.cssSelector("#hplogo"));
		WebElement txtGoogleSearchField = driver.findElement(By
				.cssSelector("#lst-ib"));
		WebElement btnGoogleSearch = driver.findElement(By
				.cssSelector("[value~=Google]"));
		WebElement btnGoogleImFeelingLucky = driver.findElement(By
				.cssSelector("[value~=Feeling]"));
		int count = 0;

		while (!icoGoogleSearch.isDisplayed() || count == 5) {
			Thread.sleep(1000);
			count++;
		}

		if (!icoGoogleSearch.isDisplayed())
			throw new NoSuchElementException(
					"Google Search Logo is not displayed");
		if (!txtGoogleSearchField.isDisplayed())
			throw new NoSuchElementException(
					"Google Search Text Field is not displayed");
		if (!btnGoogleSearch.isDisplayed())
			throw new NoSuchElementException(
					"Google Search Button is not displayed");
		if (!btnGoogleImFeelingLucky.isDisplayed())
			throw new NoSuchElementException(
					"I'm Feeling Lucky button is not displayed");

		return true;
	}

	/*
	 * Search a given item in google
	 * 
	 * @return GoogleSearchResultsPage
	 */
	public GoogleSearchResultsPage searchItems(String _searchText)
			throws Exception {

		WebElement txtGoogleSearchField = driver.findElement(By
				.cssSelector("#lst-ib"));

		if (txtGoogleSearchField.isDisplayed()) {
			txtGoogleSearchField.click();
			txtGoogleSearchField.clear();
			txtGoogleSearchField.sendKeys(_searchText);
			txtGoogleSearchField.sendKeys(Keys.ENTER);
		} else
			throw new NoSuchElementException(
					"Google Search Field is not displayed");

		return new GoogleSearchResultsPage(driver);
	}
}
