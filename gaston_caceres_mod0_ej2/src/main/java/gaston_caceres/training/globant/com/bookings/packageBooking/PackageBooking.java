package gaston_caceres.training.globant.com.bookings.packageBooking;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
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
import gaston_caceres.training.globant.com.utils.CalendarHelper;
import gaston_caceres.training.globant.com.utils.ElementToValidate;
import gaston_caceres.training.globant.com.utils.ValidationType;

public class PackageBooking {

	private static final String INVALID_PARTIAL_HOTEL_STAY = "our partial check-in and check-out dates must fall within your arrival and departure dates. Please review your dates.";

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

	private CalendarHelper calendarHelper;

	private static String currentHandle;

	public PackageBooking(WebDriver webDriver) {
		this.webDriver = webDriver;
		packageInfo = new PackageInfo();
		calendarHelper = new CalendarHelper(webDriver);
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
		calendarHelper.selectDate(date);
		return this;
	}

	public PackageBooking selectRetournDate(DateTime date) {
		retournDate.click();
		packageInfo.setRetournDate(date);
		calendarHelper.selectDate(date);
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

	public boolean checkPackageBookingResult() {
		boolean validBooking = false;

		WebElement hotelName = new WebDriverWait(webDriver, 10)
				.until(ExpectedConditions.presenceOfElementLocated(By.id("A12004_16036")));
		List<WebElement> travelDates = new WebDriverWait(webDriver, 10)
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("A5006_12722")));
		if (hotelName.getText().equals(packageInfo.getHotel().getName()) && validTravelDates(travelDates)
				&& correctCarSelection()) {
			validBooking = true;
		}

		return validBooking;
	}

	private boolean validTravelDates(List<WebElement> travelDates) {
		boolean valid = false;
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
		String departureDate = format.format(packageInfo.getDepartureDate().toDate());
		String retournDate = format.format(packageInfo.getRetournDate().toDate());
		String departureDateVisible = null;
		String retournDateVisible = null;

		if (travelDates != null && travelDates.size() == 2) {
			departureDateVisible = travelDates.get(0).getText();
			retournDateVisible = travelDates.get(1).getText();
			if (departureDate.contains(departureDateVisible) && retournDate.contains(retournDateVisible)) {
				valid = true;
			}
		}

		return valid;
	}

	private boolean correctCarSelection() {
		try {
			String carType = car().getCarTypeSelected().visibleName();
			new WebDriverWait(webDriver, 10).until(
					ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[contains(text(),'" + carType + "')]")));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Set<ElementToValidate> getElementsToValidateBooking() {
		Set<ElementToValidate> elements = new HashSet<ElementToValidate>();
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
		String departureDate = format.format(packageInfo.getDepartureDate().toDate());
		String retournDate = format.format(packageInfo.getRetournDate().toDate());

		elements.add(new ElementToValidate(By.id("A12004_16036"), null, packageInfo.getHotel().getName(),
				ValidationType.IS_ELEMENT_PRESENT, ValidationType.COMPLETE_TEXT));
		elements.add(new ElementToValidate(By.xpath(".//*[@Id='A5006_12722'][1]"), null, departureDate,
				ValidationType.IS_ELEMENT_PRESENT, ValidationType.COMPLETE_TEXT));
		elements.add(new ElementToValidate(By.xpath(".//*[@Id='A5006_12722'][2]"), null, retournDate,
				ValidationType.IS_ELEMENT_PRESENT, ValidationType.COMPLETE_TEXT));

		return elements;
	}

	public PackageBooking partialHotelStay() {
		webDriver.findElement(By.id("partialHotelBooking")).click();
		return this;
	}

	public PackageBooking selectHotelCheckInDate(DateTime date) {
		WebElement checkIn = webDriver.findElement(By.id("package-checkin"));
		checkIn.clear();
		checkIn.click();

		calendarHelper.selectDate(date);
		return this;
	}

	public PackageBooking selectHotelCheckOutDate(DateTime date) {
		WebElement checkOut = webDriver.findElement(By.id("package-checkout"));
		checkOut.clear();
		checkOut.click();
		calendarHelper.selectDate(date);
		return this;
	}

	public boolean validHotelStay() {
		boolean valid = true;
		try {
			WebElement invalidDates = new WebDriverWait(webDriver, 10)
					.until(ExpectedConditions.presenceOfElementLocated(By.className("partialStayDatesOutOfRange")));
			valid = invalidDates.getText().equals(INVALID_PARTIAL_HOTEL_STAY);
		} catch (Exception e) {
		}

		return valid;
	}

}
