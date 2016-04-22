package gaston_caceres.training.globant.com;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends AutomationPage {

	private static final String SEARCH_TITLE = "";
	private static final String NO_RESULTS_TEXT = "Nothing Found";

	@FindBy(tagName = "article")
	private List<WebElement> searchResults;

	private String searchQuery;

	@Override
	public boolean isCorrectPage(WebDriver webDriver) {
		StringBuilder searchPageTitle = new StringBuilder();
		searchPageTitle.append(searchQuery);
		searchPageTitle.append(SEARCH_TITLE);

		return SEARCH_TITLE.equals(webDriver.getTitle());
	}

	public boolean searchHasResults() {
		String articleTitle = null;
		if (searchResults != null && !searchResults.isEmpty()) {
			articleTitle = searchResults.get(0).findElement(By.tagName("h1")).getText();
		}
		return NO_RESULTS_TEXT.equals(articleTitle);
	}

}
