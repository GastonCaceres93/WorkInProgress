package gaston_caceres.training.globant.com;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//works as search result page to
public class HomePage extends AutomationPage {

	private static final String HOME_TITLE = "Automation Training | Aprender a automatizar en un solo sitio";

	// esto es una tabla....
	@FindBy(id = "wp-calendar")
	private WebElement calendar;

	@FindBy(tagName = "article")
	private Set<WebElement> articles;

	@Override
	public boolean isCorrectPage(WebDriver webDriver) {
		return HOME_TITLE.equals(webDriver.getTitle());
	}

}
