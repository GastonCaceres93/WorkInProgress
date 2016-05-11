package gaston_caceres.training.globant.com;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage  {

	private static final String SEARCH_TITLE = " | Search Results | Automation Training";
	private static final String NO_RESULTS_TEXT = "Nothing Found";
	
	private WebDriver webDriver;
	
	@FindBy(id="s")
	private WebElement searchBox;

	@FindBy(tagName = "article")
	private List<WebElement> searchResults;
	
	public SearchPage(WebDriver webDriver){
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public boolean isCorrectPage() {
		return webDriver != null && webDriver.getTitle().endsWith(SEARCH_TITLE);
	}
	
	public SearchPage search(String query){
		searchBox.sendKeys(query);
		searchBox.submit();
		
		retrieveSearchResults();
		return this;
	}
	
	private void retrieveSearchResults(){
		this.searchResults = webDriver.findElements(By.tagName("article"));
	}
	

	public boolean searchHasResults() {
		String articleTitle = null;
		if (searchResults != null && !searchResults.isEmpty()) {
			articleTitle = searchResults.get(0).findElement(By.tagName("h1")).getText();
		}
		return NO_RESULTS_TEXT.equals(articleTitle);
	}

}
