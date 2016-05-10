package gaston_caceres.training.globant.com.bookings.packageBooking;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gaston_caceres.training.globant.com.bookings.packageBooking.cars.PackageCar;
import gaston_caceres.training.globant.com.bookings.packageBooking.flight.PackageFlight;
import gaston_caceres.training.globant.com.bookings.packageBooking.hotel.PackageHotel;
import gaston_caceres.training.globant.com.utils.CalendarElement;
import gaston_caceres.training.globant.com.utils.ElementToValidate;

public class PackageBooking {
	
	private static final String INVALID_PARTIAL_HOTEL_STAY= "our partial check-in and check-out dates must fall within your arrival and departure dates. Please review your dates.";

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
	private PackageCar car;

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
		packageInfo.setHotel(hotel.getHotelInfo());
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
		packageInfo.setDepartureFlight(flight.getDepartureFlightInfo());
		packageInfo.setRetourneFlight(flight.getRetournFlightInfo());
		return flight;
	}

	public PackageCar car() {
		if (car == null) {
			car = new PackageCar(webDriver);
		}
		return car;
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
	
	public boolean checkPackageBookingResult(){
		boolean validBooking = true;
		
		//cada uno tiene 2 elementos, el primero es el viaje de ida y el segundo es el viaje de regreso
		List<WebElement> departureAirports = webDriver.findElements(By.id("departureairport"));
		List<WebElement> arrivalAirports = webDriver.findElements(By.id("arrivalairport"));
		List<WebElement> departureTimes = webDriver.findElements(By.id("departuretime"));
		List<WebElement> arrivalTimes = webDriver.findElements(By.id("arrivaltime"));
		
		
		return validBooking;
	}
	
	public Set<ElementToValidate> getElementsToValidateBooking(){
		Set<ElementToValidate> elements = new HashSet<ElementToValidate>();
		
		return elements;
	}
	
	public PackageBooking partialHotelStay(){
		webDriver.findElement(By.id("partialHotelBooking")).click();
		return this;
	}
	
	public PackageBooking selectHotelCheckInDate(DateTime date) {
		WebElement checkIn = webDriver.findElement(By.id("package-checkin"));
		checkIn.clear();
		checkIn.click();

		new CalendarElement(webDriver).selectDate(date);
		return this;
	}

	public PackageBooking selectHotelCheckOutDate(DateTime date) {
		WebElement checkOut = webDriver.findElement(By.id("package-checkout"));
		checkOut.clear();
		checkOut.click();
		new CalendarElement(webDriver).selectDate(date);
		return this;
	}
	
	public boolean validHotelStay(){
		boolean valid = true;
		try {
			WebElement invalidDates = new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.className("partialStayDatesOutOfRange")));
			valid = invalidDates.getText().equals(INVALID_PARTIAL_HOTEL_STAY);
		} catch (Exception e) {
		}
		
		return valid;
	}

}
