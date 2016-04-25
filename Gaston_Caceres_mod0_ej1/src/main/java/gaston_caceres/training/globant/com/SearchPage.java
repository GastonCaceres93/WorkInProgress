package gaston_caceres.training.globant.com;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage  {

	private static final String SEARCH_TITLE = " | Search Results | Automation Training";
	private static final String NO_RESULTS_TEXT = "Nothing Found";

	@FindBy(tagName = "article")
	private List<WebElement> searchResults;

	public boolean isCorrectPage(WebDriver webDriver) {
		return webDriver != null && webDriver.getTitle().endsWith(SEARCH_TITLE);
	}

	public boolean searchHasResults() {
		String articleTitle = null;
		if (searchResults != null && !searchResults.isEmpty()) {
			articleTitle = searchResults.get(0).findElement(By.tagName("h1")).getText();
		}
		return NO_RESULTS_TEXT.equals(articleTitle);
	}

}
