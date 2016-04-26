package gaston_caceres.training.globant.com.tests;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import gaston_caceres.training.globant.com.AutomationPage;
import gaston_caceres.training.globant.com.ContactPage;
import gaston_caceres.training.globant.com.SearchPage;

public class ExerciseOneTests implements IExserciseOneTests {

	private AutomationPage automationPage;

	@BeforeClass
	public void setUpTest() {
		automationPage = new AutomationPage(new FirefoxDriver());
	}

	@Test
	public void testCase_1() {
		automationPage.goToHome();
		Assert.assertTrue(automationPage.isHomePage());
	}

	@Test
	public void testCase_2() {
		SearchPage search = automationPage.goToSearch();

		search.search("whaaaazzzzaaaaaappppp");

		Assert.assertTrue(search.searchHasResults());
	}

	@Test
	public void testCase_3() {
	}

	@Test
	public void testCase_4() {
		ContactPage contact = automationPage.goToContact();

		Assert.assertTrue(contact.isCorrectPage());

		contact.fillForm("Gaston", "gaston.caceres@globant.com", "submit completo", "completo, todo bien").sendForm();

		Assert.assertTrue(contact.verifyContactOk());
	}

	@Test
	public void testCase_5() {
		ContactPage contact = automationPage.goToContact();

		Assert.assertTrue(contact.isCorrectPage());

		contact.fillForm("Gaston", null, "submit completo", "completo, todo bien").sendForm();
		Assert.assertFalse(contact.verifyContactOk());

		contact.fillForm("Gaston", "gaston.caceres@globant.com", "submit completo", "completo, todo bien").sendForm();
		Assert.assertTrue(contact.verifyContactOk());
	}

	@Test
	public void testCase_6() {
		automationPage.goToHome();
		Assert.assertTrue(automationPage.isHomePage());

		if (!automationPage.anyPostThisMonth()) {
			automationPage.findFirstPosts().writePostsTitles();
		}
	}

	@AfterClass
	public void tearDownTest() {
		automationPage.quit();
	}

}
