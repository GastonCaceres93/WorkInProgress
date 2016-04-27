package gaston_caceres.training.globant.com.bookings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HotelBooking {

	private WebDriver webDriver;

	public HotelBooking(WebDriver webDriver){
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

}
