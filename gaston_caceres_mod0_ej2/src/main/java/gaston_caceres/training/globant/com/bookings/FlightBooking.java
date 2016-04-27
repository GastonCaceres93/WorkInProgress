package gaston_caceres.training.globant.com.bookings;

import java.util.Set;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gaston_caceres.training.globant.com.utils.CalendarElement;
import gaston_caceres.training.globant.com.utils.FlightsConnections;

public class FlightBooking {

	private WebDriver webDriver;

	@FindBy(id = "flight-type-roundtrip")
	private WebElement roundTripTab;

	@FindBy(id = "flight-type-one-way")
	private WebElement oneWayTripTab;

	@FindBy(id = "flight-type-multi-dest")
	private WebElement multipleDestinationsTab;

	@FindBy(id = "flight-origin")
	private WebElement fromBox;

	@FindBy(id = "flight-destination")
	private WebElement toBox;

	@FindBy(id = "flight-adults")
	private WebElement adultsTraveling;

	@FindBy(id = "flight-adults")
	private WebElement childrenTraveling;
	
	private CalendarElement calendar;

	private Set<FlightsConnections> connections;

	public FlightBooking(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public FlightBooking roundTrip() {
		roundTripTab.click();
		return this;
	}

	public FlightBooking oneWayTrip() {
		oneWayTripTab.click();
		return this;
	}

	public FlightBooking multipleDestinations() {
		multipleDestinationsTab.click();
		return this;
	}

	public FlightBooking selectDepartureDate(DateTime date) {
		webDriver.findElement(By.id("flight-departing")).click();
		this.calendar = new CalendarElement(webDriver).selectDate(date);
		return this;
	}

	public FlightBooking selectRetournDate(DateTime date) {
		webDriver.findElement(By.id("flight-returning")).click();
		this.calendar = new CalendarElement(webDriver).selectDate(date);
		return this;
	}

}
