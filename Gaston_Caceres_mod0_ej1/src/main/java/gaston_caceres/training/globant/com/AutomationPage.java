package gaston_caceres.training.globant.com;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class AutomationPage {

	@FindBy(xpath = ".//*[@id='access']/div[3]/ul/li[1]/a")
	private WebElement homeLink;

	@FindBy(xpath = ".//*[@id='access']/div[3]/ul/li[2]/a")
	private WebElement contactLink;

	@FindBy(id = "s")
	private WebElement searchBox;

	private String title;

	public String getTitle() {
		return this.title;
	}

	public abstract boolean isCorrectPage(WebDriver webDriver);

	public ContactPage goToContact(WebDriver webDriver) {
		contactLink.click();
//		return (ContactPage)this;
		return PageFactory.initElements(webDriver, ContactPage.class);
	}

	public HomePage goToHome(WebDriver webDriver) {
		homeLink.click();
//		return (HomePage)this;
		return PageFactory.initElements(webDriver, HomePage.class);
	}
	
	public SearchPage goToSearch(String query, WebDriver webDriver){
		searchBox.sendKeys(query);
		searchBox.submit();
//		return (SearchPage) this;
		return PageFactory.initElements(webDriver, SearchPage.class);
	}
}
