package test.java.com.selenium.google_pageobject;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleSearchResultsPage {

	WebDriver driver;

	/*
	 * Google Search Results Page Constructor
	 */
	public GoogleSearchResultsPage(WebDriver _driver) {
		this.driver = _driver;
	}

	/*
	 * Verifying Google Search Results Page is loaded
	 * 
	 * @return boolean
	 */
	public boolean isGoogleResultsPageLoaded() throws Exception {

		Thread.sleep(3000);
		WebElement txtSearchResultsCount = driver.findElement(By
				.cssSelector("#resultStats"));
		List<WebElement> txtSearchResults = driver.findElements(By
				.cssSelector(".r>a"));
		int count = 0;

		while (!txtSearchResultsCount.isDisplayed() || count == 5) {
			Thread.sleep(1000);
			count++;
		}

		if (!txtSearchResultsCount.isDisplayed())
			throw new NoSuchElementException(
					"Google Search Results count is not displayed");
		if (txtSearchResults.isEmpty())
			throw new NoSuchElementException(
					"Google Search Results are not displayed");

		return true;
	}

	/*
	 * Verifying Google Search Results Page is loaded
	 * 
	 * @return double results count
	 */
	public double getTheResultsCount() throws Exception {

		WebElement txtSearchResultsCount = driver.findElement(By
				.cssSelector("#resultStats"));
		String resultsText = "";

		if (txtSearchResultsCount.isDisplayed()) {
			resultsText = txtSearchResultsCount.getText();
			if (resultsText.isEmpty())
				throw new NullPointerException(
						"Unable to get the search results count");
		} else

			throw new NoSuchElementException(
					"Google Search Results count is not displayed");

		return Double.parseDouble(resultsText.replaceAll("[\\D]", ""));
	}
}
