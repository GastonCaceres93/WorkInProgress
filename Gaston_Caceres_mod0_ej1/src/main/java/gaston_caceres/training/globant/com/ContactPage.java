package gaston_caceres.training.globant.com;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactPage extends AutomationPage {

	private static final String CONTACT_TITLE = "";

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

	@Override
	public boolean isCorrectPage(WebDriver webDriver) {
		return CONTACT_TITLE.equals(webDriver.getTitle());
	}

	public void fillForm(String name, String email, String subject, String message) {

	}

}
