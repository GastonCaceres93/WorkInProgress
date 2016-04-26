package gaston_caceres.training.globant.com.tests;

public interface IExserciseOneTests {

	/**
	 * 1.Open the site.<br>
	 * 2.Verify the page is the expected one according the title.
	 */
	public void testCase_1();

	/**
	 * 1.Perform a search.<br>
	 * 2.Verify if it have results.
	 */
	public void testCase_2();

	/**
	 * 1.Verify the displayed date for the post is the date of the post
	 * creation.
	 */
	public void testCase_3();

	/**
	 * 1.Enter to 'Contact us'<br>
	 * 2.Complete all the fields with correct data <br>
	 * 3.Send the form 4.Verify the form was correctly sent.
	 */
	public void testCase_4();

	/**
	 * 1.Enter to 'Contact us' <br>
	 * 2.Complete the fields leaving at least one field empty <br>
	 * 3. Verify the form was not accepted. <br>
	 * 4.Complete all fields<br>
	 * 5. Send the form <br>
	 * 6.Verify the form was correctly sent.
	 */
	public void testCase_5();

	/**
	 * 1.Go to the Home Page.<br>
	 * 2.Using the calendar: Verify how many days of the current month have
	 * posts. <br>
	 * 3.Enter to the first day with a post. <br>
	 * 4.Count how many post that day has and print the titles in the console
	 */
	public void testCase_6();
}
