package gaston_caceres.training.globant.com.bookings.flight;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gaston_caceres.training.globant.com.bookings.flight.misc.FlightInfo;
import gaston_caceres.training.globant.com.utils.CalendarHelper;

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

	private Set<FlightsConnections> connections;
	private int connectionsCount = 2;

	private static FlightInfo flightInfo;
	
	private CalendarHelper calendarHelper;

	public FlightBooking(WebDriver webDriver) {
		this.webDriver = webDriver;
		flightInfo = new FlightInfo();
		PageFactory.initElements(webDriver, this);
		calendarHelper = new CalendarHelper(webDriver);
	}

	public FlightBooking roundTrip() {
		roundTripTab.click();
		return this;
	}

	public FlightBooking oneWayTrip() {
		oneWayTripTab.click();
		flightInfo.setTripType("oneway");
		return this;
	}

	public FlightBooking multipleDestinations() {
		multipleDestinationsTab.click();
		this.connections = new HashSet<FlightsConnections>();
		return this;
	}

	public FlightBooking selectDepartureDate(DateTime date) {
		webDriver.findElement(By.id("flight-departing")).click();

		flightInfo.setDepartureDate(date);

		calendarHelper.selectDate(date);
		return this;
	}

	public FlightBooking selectRetournDate(DateTime date) {
		webDriver.findElement(By.id("flight-returning")).click();

		flightInfo.setRetournDate(date);

		calendarHelper.selectDate(date);
		return this;
	}

	public FlightBooking adultsTraveling(int adults) {
		this.adultsTraveling.sendKeys(String.valueOf(adults));
		flightInfo.setAdults(adults);
		return this;
	}

	public FlightBooking childrenTraveling(int children) {
		this.childrenTraveling.sendKeys(String.valueOf(children));
		flightInfo.setChildren(children);
		return this;

	}

	public FlightBooking selectArrivalAirport(String destinationAirport) {
		this.toBox.sendKeys(destinationAirport);
		flightInfo.setDestinationAirport(destinationAirport);
		return this;

	}

	public FlightBooking selectDepartureAirport(String departureAirport) {
		this.fromBox.sendKeys(departureAirport);
		flightInfo.setDepartureAirport(departureAirport);
		return this;
	}

	public FlightSelection searchFlights() {
		webDriver.findElement(By.id("search-button")).click();
		FlightSelection flightSelection = new FlightSelection(webDriver);
		return flightSelection;
	}

	public FlightBooking addConnection(String from, String to, DateTime departure) {
		FlightsConnections connection = new FlightsConnections(webDriver, connectionsCount);
		connectionsCount++;
		this.connections.add(connection);
		return this;
	}

	public static FlightInfo getFlightInfo() {
		return flightInfo;
	}
}
