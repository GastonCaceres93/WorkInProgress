package gaston_caceres.training.globant.com.bookings.packageBooking;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gaston_caceres.training.globant.com.bookings.packageBooking.flight.PackageFlight;
import gaston_caceres.training.globant.com.bookings.packageBooking.hotel.PackageHotel;
import gaston_caceres.training.globant.com.utils.CalendarElement;

public class PackageBooking {

	private WebDriver webDriver;

	@FindBy(id = "package-origin")
	private WebElement departureAirport;

	@FindBy(id = "package-destination")
	private WebElement arrivalAirport;

	@FindBy(id = "package-departing")
	private WebElement departureDate;

	@FindBy(id = "package-returning")
	private WebElement retournDate;

	@FindBy(id = "package-rooms")
	private WebElement rooms;

	@FindBy(id = "package-1-adults")
	private WebElement adults;

	@FindBy(id = "package-1-children")
	private WebElement children;

	private PackageInfo packageInfo;

	private PackageHotel hotel;
	private PackageFlight flight;

	private static String currentHandle;

	public PackageBooking(WebDriver webDriver) {
		this.webDriver = webDriver;
		packageInfo = new PackageInfo();
		PageFactory.initElements(webDriver, this);
	}

	public PackageBooking bookFlightPlusHotel() {
		webDriver.findElement(By.id("package-fh-label")).click();
		return this;
	}

	public PackageBooking bookFlightPlusHotelAndCar() {
		webDriver.findElement(By.id("package-fhc-label")).click();
		return this;
	}

	public PackageBooking bookFlightPlusCar() {
		webDriver.findElement(By.id("package-fc-label")).click();
		return this;
	}

	public PackageBooking bookHotelPlusCar() {
		webDriver.findElement(By.id("package-hc-label")).click();
		return this;
	}

	public PackageBooking search() {
		currentHandle = webDriver.getWindowHandle();

		webDriver.findElement(By.id("search-button")).click();
		
		closeOtherWindows(currentHandle);
		return this;
	}

	public PackageBooking selectDepartureDate(DateTime date) {
		departureDate.click();
		packageInfo.setDepartureDate(date);
		new CalendarElement(webDriver).selectDate(date);
		return this;
	}

	public PackageBooking selectRetournDate(DateTime date) {
		retournDate.click();
		packageInfo.setRetournDate(date);
		new CalendarElement(webDriver).selectDate(date);
		return this;
	}

	public PackageBooking adultsTraveling(int adults) {
		this.adults.sendKeys(String.valueOf(adults));
		packageInfo.setAdults(adults);
		return this;
	}

	public PackageBooking childrenTraveling(int children) {
		this.children.sendKeys(String.valueOf(children));
		packageInfo.setChildren(children);
		return this;

	}

	public PackageBooking selectArrivalAirport(String arrivalAirport) {
		this.arrivalAirport.sendKeys(arrivalAirport);
		packageInfo.setArrivalAirport(arrivalAirport);
		return this;

	}

	public PackageBooking selectDepartureAirport(String departureAirport) {
		this.departureAirport.sendKeys(departureAirport);
		packageInfo.setDepartureAirport(departureAirport);
		return this;
	}

	public PackageBooking rooms(int rooms) {
		this.rooms.sendKeys(String.valueOf(rooms));
		packageInfo.setRooms(rooms);
		return this;
	}

	public PackageInfo getPackageInfo() {
		return this.packageInfo;
	}

	public PackageHotel hotel() {
		if (hotel == null) {
			hotel = new PackageHotel(webDriver);
		}
		return this.hotel;
	}

	public PackageBooking selectFirstRoom() {
		hotel.selectFirstRoomAvailable();
		return this;
	}

	public PackageFlight flight() {
		if (flight == null) {
			flight = new PackageFlight(webDriver);
		}
		return flight;
	}

	private void closeOtherWindows(String keepHandle) {
		for (String handle : webDriver.getWindowHandles()) {
			if (keepHandle != null && !keepHandle.equals(handle)) {
				webDriver.switchTo().window(handle);
				webDriver.close();
			}
		}
		webDriver.switchTo().window(keepHandle);
	}

	public static String currentHandle() {
		return currentHandle;
	}

	public static void updateCurrentHandle(String newHandle) {
		currentHandle = newHandle;
	}

}
