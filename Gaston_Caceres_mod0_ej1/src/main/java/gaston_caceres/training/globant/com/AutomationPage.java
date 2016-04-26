package gaston_caceres.training.globant.com;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AutomationPage {

	private static final String HOME_TITLE = "Automation Training | Aprender a automatizar en un solo sitio";
	private static final String SITE = "http://10.28.148.127/wordpress";

	protected WebDriver webDriver;

	@FindBy(xpath = ".//*[@id='access']/div[3]/ul/li[1]/a")
	private WebElement homeLink;

	@FindBy(xpath = ".//*[@id='access']/div[3]/ul/li[2]/a")
	private WebElement contactLink;

	@FindBy(id = "s")
	private WebElement searchBox;

	@FindBy(id = "wp-calendar")
	private WebElement calendar;

	@FindBy(tagName = "article")
	private List<WebElement> articlesTitles;

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
		return HOME_TITLE.equals(webDriver.getTitle());
	}

	public SearchPage goToSearch() {
		return new SearchPage(webDriver);
	}

	public ContactPage goToContact() {
		contactLink.click();
		return new ContactPage(webDriver);
	}

	public boolean anyPostThisMonth() {
		return webDriver.findElements(By.xpath(".//*[@id='wp-calendar']/tbody//a")).size() > 0;
	}

	public AutomationPage findFirstPosts() {
		locatePosts();
		articlesTitles = webDriver.findElements(By.xpath(".//*[@id='content']//article//h1//a"));
		return this;
	}
	
	private void locatePosts(){
		while(isElementPresent(By.xpath(".//*[@id='prev']//a"))){
			webDriver.findElement(By.xpath(".//*[@id='prev']//a")).click();
		}
		calendar = webDriver.findElement(By.id("wp-calendar"));
		calendar.findElement(By.tagName("a")).click();
	}

	public AutomationPage writePostsTitles() {
		for(WebElement element : this.articlesTitles){
			System.out.println(element.getText());
		}
		return this;
	}

	public void quit() {
		webDriver.quit();
	}
	
	private boolean isElementPresent(By by){
		try {
			webDriver.findElement(by);
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}
}
