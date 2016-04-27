package gaston_caceres.training.globant.com.bookings;

import org.openqa.selenium.WebDriver;

public class FlightAndHotelBooking {

	private FlightBooking flight;
	private HotelBooking hotel;
	private WebDriver webDriver;
	
	

	public FlightAndHotelBooking(WebDriver webDriver) {
		this.webDriver = webDriver;
		this.flight = new FlightBooking(webDriver);
		this.hotel = new HotelBooking(webDriver);
	}

}
