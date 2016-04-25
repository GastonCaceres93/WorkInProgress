package gaston_caceres.training.globant.com;

import static gaston_caceres.training.globant.com.AutomationLocators.CONTACT_LINK;
import static gaston_caceres.training.globant.com.AutomationLocators.HOME_LINK;
import static gaston_caceres.training.globant.com.AutomationLocators.SEARCH_BOX_ID;
import static gaston_caceres.training.globant.com.AutomationLocators.CALENDAR_ID;
import static gaston_caceres.training.globant.com.AutomationLocators.POSTS_TAG;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AutomationPage {

	private static final String HOME_TITLE = "Automation Training | Aprender a automatizar en un solo sitio";
	private static final String SITE = "http://10.28.148.127/wordpress";

	protected WebDriver webDriver;

	@FindBy(xpath = HOME_LINK)
	private WebElement homeLink;

	@FindBy(xpath = CONTACT_LINK)
	private WebElement contactLink;

	@FindBy(id = SEARCH_BOX_ID)
	private WebElement searchBox;

	// esto es una tabla....
	@FindBy(id = CALENDAR_ID)
	private WebElement calendar;

	@FindBy(tagName = POSTS_TAG)
	private Set<WebElement> articles;

	private ContactPage contactPage;
	private SearchPage searchPage;

	public AutomationPage(WebDriver webDriver) {
		this.webDriver = webDriver;
		this.webDriver.get(SITE);
		PageFactory.initElements(webDriver, this);
	}

	public AutomationPage goToHome() {
		homeLink.click();
		PageFactory.initElements(webDriver, this);
		return this;
	}

	public boolean isHomePage() {
		// return homePage.isCorrectPage(webDriver);
		return HOME_TITLE.equals(webDriver.getTitle());
	}

	/* ====serch===== */
	public SearchPage search(String query) {
		searchBox.sendKeys(query);
		searchBox.submit();
		return PageFactory.initElements(webDriver, SearchPage.class);
		// return this;
	}

	public boolean isSearchResultsPage() {
		return searchPage.isCorrectPage(webDriver);
	}

	public boolean searchHasResults() {
		return this.searchPage.searchHasResults();
	}

	/* =====contact======= */

	public AutomationPage makeContact(String name, String email, String subject, String message) {
		contactLink.click();
		contactPage = PageFactory.initElements(webDriver, ContactPage.class);
		contactPage.fillForm(name, email, subject, message).sendForm();
		return this;
	}

	public ContactPage goToContact() {
		contactLink.click();
		contactPage = PageFactory.initElements(webDriver, ContactPage.class);
		return contactPage;
	}

	public boolean isContactPage() {
		return contactPage.isCorrectPage(webDriver);
	}

	public boolean isContactResponseOk() {
		WebElement response = webDriver.findElement(By.id("cntctfrm_contact_form"));
		return response == null;
	}

	public void quit() {
		webDriver.quit();
	}
}
