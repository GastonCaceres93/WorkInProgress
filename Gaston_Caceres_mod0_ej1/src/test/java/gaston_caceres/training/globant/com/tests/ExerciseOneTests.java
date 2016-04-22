package gaston_caceres.training.globant.com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import gaston_caceres.training.globant.com.AutomationPage;
import gaston_caceres.training.globant.com.ContactPage;
import gaston_caceres.training.globant.com.HomePage;
import gaston_caceres.training.globant.com.SearchPage;

public class ExerciseOneTests {

	private WebDriver webDriver;
	private AutomationPage page;

	private static final String SITE = "http://10.28.148.127/wordpress";

	@BeforeClass
	public void setUpTest() {
		webDriver = new FirefoxDriver();
	}

	/*
	 * Open the site. Verify the page is the expected one according the title.
	 */
	@Test
	public void testCase_1() {
		webDriver.get(SITE);
		HomePage page = PageFactory.initElements(webDriver, HomePage.class);
		assert (page.isCorrectPage(webDriver));
	}

	/*
	 * Perform a search. Verify if it have results.
	 */
	@Test
	public void testCase_2() {
		SearchPage search = page.goToSearch("waaazzzzaaaapppp", webDriver); 
		
		assert (search.isCorrectPage(webDriver));

		assert (search.searchHasResults());
	}

	// Verify the displayed date for the post is the date of the post creation.
	@Test
	public void testCase_3() {
	}

	/*
	 * Enter to 'Contact us', Complete all the fields with correct data, Send
	 * the form, Verify the form was correctly sent.
	 */
	@Test
	public void testCase_4() {
		page.goToContact(webDriver);
		// page = PageFactory.initElements(webDriver, ContactPage.class);

		assert (page.isCorrectPage(webDriver));

		((ContactPage) page).fillForm("Gaston", "gaston.caceres@globant.com", "submit completo", "completo, todo bien");

	}

	/*
	 * Enter to 'Contact us', Complete the fields leaving at least one field
	 * empty, Verify the form was not accepted. Complete all fields, Send the
	 * form Verify the form was correctly sent.
	 * 
	 */
	@Test
	public void testCase_5() {
	}

	/*
	 * Go to the Home Page Using the calendar: Verify how many days of the
	 * current month have posts. Enter to the first day with a post. Count how
	 * many post that day has and print the titles in the console
	 */
	@Test
	public void testCase_6() {
	}

	@AfterClass
	public void tearDownTest() {
		webDriver.quit();
	}

}
