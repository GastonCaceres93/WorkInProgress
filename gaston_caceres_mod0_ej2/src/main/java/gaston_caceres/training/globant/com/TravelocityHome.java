package gaston_caceres.training.globant.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gaston_caceres.training.globant.com.bookings.CruisesBooking;
import gaston_caceres.training.globant.com.bookings.flight.FlightBooking;
import gaston_caceres.training.globant.com.bookings.packageBooking.PackageBooking;
import gaston_caceres.training.globant.com.bookings.HotelBooking;

public class TravelocityHome {

	private static final String TRAVELOCITY_HOME_URL = "https://www.travelocity.com/";

	private WebDriver webDriver;

	@FindBy(id = "tab-flight-tab")
	private WebElement flightsLink;

	@FindBy(id = "tab-hotel-tab")
	private WebElement hotelsLink;

	@FindBy(id = "tab-package-tab")
	private WebElement flightPlusHotelLink;

	@FindBy(id = "tab-cruise-tab")
	private WebElement cruisesLink;

	private String mainHandle;

	public TravelocityHome(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public TravelocityHome goHome() {
		this.webDriver.get(TRAVELOCITY_HOME_URL);
		this.mainHandle = webDriver.getWindowHandle();
		PageFactory.initElements(webDriver, this);
		return this;
	}

	public PackageBooking goPackageBooking() {
		this.flightPlusHotelLink.click();
		return new PackageBooking(webDriver);
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

	public void quit() {
		this.webDriver.quit();
	}

	public WebDriver getDriver() {
		return this.webDriver;
	}

	public void closeOtherHandles() {
		for (String handle : webDriver.getWindowHandles()) {
			if (!handle.equals(mainHandle)) {
				webDriver.switchTo().window(handle).close();
			}
		}
		webDriver.switchTo().window(mainHandle);
	}

}
