package gaston_caceres.training.globant.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;;

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

	// @FindBy(xpath = ".//*[@id='cntctfrm_contact_form']/div[9]/input[4]")
	// private WebElement submitButton;

	@FindBy(id = "cntctfrm_contact_form")
	private WebElement contactForm;

	public ContactPage(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public boolean isCorrectPage() {
		return CONTACT_TITLE.equals(webDriver.getTitle());
	}

	public ContactPage fillForm(String name, String email, String subject, String message) {
		if (validateFieldsAndClear()) {
			nameTxtBox.sendKeys(name);
			emailTxtBox.sendKeys(email);
			subjectTxtBox.sendKeys(subject);
			messageAreaBox.sendKeys(message);
		}
		return this;
	}

	public ContactPage sendForm() {
		contactForm.submit();
		return this;
	}

	private boolean validateFieldsAndClear() {
		boolean valid = nameTxtBox != null && emailTxtBox != null && subjectTxtBox != null && messageAreaBox != null
				&& subjectTxtBox != null;
		if (valid) {
			nameTxtBox.clear();
			emailTxtBox.clear();
			subjectTxtBox.clear();
			messageAreaBox.clear();
			subjectTxtBox.clear();
		}
		return valid;
	}

	public boolean verifyContactOk() {
		boolean contactOk = false;
		contactOk = webDriver.findElements(By.id("cntctfrm_thanks")).size() > 0;
		return contactOk;
	}

}
