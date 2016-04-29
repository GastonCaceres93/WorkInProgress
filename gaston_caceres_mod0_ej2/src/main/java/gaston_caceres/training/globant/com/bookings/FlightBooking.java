package gaston_caceres.training.globant.com.bookings;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gaston_caceres.training.globant.com.search.FlightSearch;
import gaston_caceres.training.globant.com.utils.CalendarElement;
import gaston_caceres.training.globant.com.utils.ElementToValidate;
import gaston_caceres.training.globant.com.utils.FlightsConnections;
import gaston_caceres.training.globant.com.utils.ValidatePage;
import gaston_caceres.training.globant.com.utils.ValidationType;

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
	private String flightType;
	private String from, to, departureDate;

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
		this.flightType = "oneway";
		return this;
	}

	public FlightBooking multipleDestinations() {
		multipleDestinationsTab.click();
		this.connections = new HashSet<FlightsConnections>();
		return this;
	}

	public FlightBooking selectDepartureDate(DateTime date) {
		webDriver.findElement(By.id("flight-departing")).click();
		new CalendarElement(webDriver).selectDate(date);
		this.departureDate = webDriver.findElement(By.id("flight-departing")).getText();
		return this;
	}

	public FlightBooking selectRetournDate(DateTime date) {
		webDriver.findElement(By.id("flight-returning")).click();
		new CalendarElement(webDriver).selectDate(date);
		return this;
	}

	public FlightBooking adultsTraveling(int adults) {
		this.adultsTraveling.sendKeys(String.valueOf(adults));
		return this;
	}

	public FlightBooking childrenTraveling(int children) {
		this.childrenTraveling.sendKeys(String.valueOf(children));
		return this;

	}

	public FlightBooking selectDestinationAirport(String destinationAirport) {
		this.to = destinationAirport;
		this.toBox.sendKeys(destinationAirport);
		return this;

	}

	public FlightBooking selectDepartureAirport(String departureAirport) {
		this.from = departureAirport;
		this.fromBox.sendKeys(departureAirport);
		return this;
	}

	public FlightSearch searchFlights() {
		webDriver.findElement(By.id("search-button")).click();
		FlightSearch flightSearch = new FlightSearch(webDriver);

		return flightSearch;
	}

	public boolean validatePage() {
		return new ValidatePage().validElements(webDriver,getElementsToValidate());
	}

	public FlightBooking addConnection(String from, String to, DateTime departure) {
		FlightsConnections connection = new FlightsConnections(webDriver, connectionsCount);
		connectionsCount++;
		this.connections.add(connection);
		return this;
	}

	private Set<ElementToValidate> getElementsToValidate() {
		Set<ElementToValidate> elements = new HashSet<ElementToValidate>();

		elements.add(new ElementToValidate(By.xpath(".//*[@Id='columnAFilter']/h3"), "Filter your results by",
				ValidationType.IS_ELEMENT_PRESENT, ValidationType.COMPLETE_TEXT));

		elements.add(new ElementToValidate(null, getFlightSearchURL(), ValidationType.PARTIAL_URL));

		elements.add(new ElementToValidate(By.id("wizardSearch"), null, ValidationType.IS_ELEMENT_PRESENT));

		elements.add(new ElementToValidate(By.id("flightModuleLisdt"), null, ValidationType.IS_ELEMENT_PRESENT));
		
		
		// elements.add(new ElementToValidate(By.id("departureAirport"),
		// from.toUpperCase(), ValidationType.PARTIAL_TEXT));

		// elements.add(new ElementToValidate(By.id("returnAirport"),
		// to.toUpperCase(), ValidationType.PARTIAL_TEXT));

		return elements;
	}

	private String getFlightSearchURL() {
		String basicURL = "https://www.travelocity.com/Flights-Search?trip=%s&leg1=from:%s,to:%s,departure:%s";
		String url = String.format(basicURL, flightType, from, to, departureDate);
		return url;
	}
}
