package gaston_caceres.training.globant.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gaston_caceres.training.globant.com.bookings.CruisesBooking;
import gaston_caceres.training.globant.com.bookings.FlightAndHotelBooking;
import gaston_caceres.training.globant.com.bookings.FlightBooking;
import gaston_caceres.training.globant.com.bookings.HotelBooking;

public class TravelocityHome {

	private static final String TRAVELOCITY_HOME_URL = "https://www.travelocity.com/";
	// private static final String TRAVELOCITY_FLIGHTS_URL =
	// "https://www.travelocity.com/Flights";
	// private static final String TRAVELOCITY_HOTELS_URL =
	// "https://www.travelocity.com/Hotels";
	// private static final String TRAVELOCITY_CRUISES_URL =
	// "https://www.travelocity.com/Cruises";

	private WebDriver webDriver;

	@FindBy(id = "tab-flight-tab")
	private WebElement flightsLink;

	@FindBy(id = "tab-hotel-tab")
	private WebElement hotelsLink;

	@FindBy(id = "tab-package-tab")
	private WebElement flightPlusHotelLink;

	@FindBy(id = "tab-cruise-tab")
	private WebElement cruisesLink;

	public TravelocityHome(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public TravelocityHome goHome() {
		this.webDriver.get(TRAVELOCITY_HOME_URL);
		PageFactory.initElements(webDriver, this);
		return this;
	}

	public FlightAndHotelBooking goFlightAndHotel() {
		this.flightPlusHotelLink.click();
		return new FlightAndHotelBooking(webDriver);
	}

	public FlightBooking goFlight() {
		flightsLink.click();
		return new FlightBooking(webDriver);
	}

	public HotelBooking goHotel() {
		hotelsLink.click();
		return new HotelBooking(webDriver);
	}

	public CruisesBooking goCruising() {
		cruisesLink.click();
		return new CruisesBooking(webDriver);
	}
}
