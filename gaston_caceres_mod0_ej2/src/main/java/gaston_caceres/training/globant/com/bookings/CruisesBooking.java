package gaston_caceres.training.globant.com.bookings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CruisesBooking {

	private WebDriver webDriver;

	public CruisesBooking(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}
}
