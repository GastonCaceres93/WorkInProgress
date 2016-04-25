package gaston_caceres.training.globant.com;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {

	private static final String CONTACT_TITLE = "Contact us | Automation Training";
	private static final String CONTACT_SUCCESSFUL = "Thank you for contacting us.";

	private WebDriver webDriver;
	
	@FindBy(id = "cntctfrm_contact_name")
	private WebElement nameTxtBox;

	@FindBy(id = "cntctfrm_contact_email")
	private WebElement emailTxtBox;

	@FindBy(id = "cntctfrm_contact_subject")
	private WebElement subjectTxtBox;

	@FindBy(id = "cntctfrm_contact_message")
	private WebElement messageAreaBox;

	@FindBy(xpath = ".//*[@id='cntctfrm_contact_form']/div[9]/input[4]")
	private WebElement submitButton;

	@FindBy(xpath = ".//*[@id='comments']//li")
	private Set<WebElement> coments;
	
	public ContactPage(WebDriver webDriver){
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public boolean isCorrectPage() {
		return CONTACT_TITLE.equals(webDriver.getTitle());
	}

	public ContactPage fillForm(String name, String email, String subject, String message) {
		if (validFields()) {
			nameTxtBox.sendKeys(name);
			emailTxtBox.sendKeys(email);
			subjectTxtBox.sendKeys(subject);
			messageAreaBox.sendKeys(message);
		}
		return this;
	}

	public ContactPage sendForm() {
		submitButton.click();
		return this;
	}

	private boolean validFields() {
		
		return nameTxtBox != null && emailTxtBox != null && subjectTxtBox != null && messageAreaBox != null
				&& subjectTxtBox != null && submitButton != null;
	}
	
	public boolean verifyContactResult(){
		webDriver.findElement(By.id("post-2"));
		return true;
	}

}
