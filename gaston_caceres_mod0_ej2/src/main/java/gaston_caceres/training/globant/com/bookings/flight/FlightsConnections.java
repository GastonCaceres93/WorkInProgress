package gaston_caceres.training.globant.com.bookings.flight;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import gaston_caceres.training.globant.com.utils.CalendarElement;

public class FlightsConnections {

	private WebDriver webDriver;

	private WebElement fromBox;
	private WebElement toBox;
	private WebElement calendarBox;

	private String fromBoxLocator = "flight-%d-origin";
	private String toBoxLocator = "flight-%d-destination";
	private String calendarBoxLocator = "flight-%d-departing";

	public FlightsConnections(WebDriver webDriver, int connectionNumber) {
		this.webDriver = webDriver;
		fromBoxLocator = String.format(fromBoxLocator, connectionNumber);
		toBoxLocator = String.format(toBoxLocator, connectionNumber);
		calendarBoxLocator = String.format(calendarBoxLocator, connectionNumber);
		initElements();
	}

	private void initElements() {
		this.fromBox = this.webDriver.findElement(By.id(fromBoxLocator));
		this.toBox = this.webDriver.findElement(By.id(toBoxLocator));
		this.calendarBox = this.webDriver.findElement(By.id(calendarBoxLocator));
	}

	public FlightsConnections selectDepartureAirport(String departureAirport) {
		this.fromBox.sendKeys(departureAirport);
		return this;
	}

	public FlightsConnections selectDestinationAirport(String destinationAirport) {
		this.toBox.sendKeys(destinationAirport);
		return this;
	}

	public FlightsConnections selectDepartureDate(DateTime date) {
		this.calendarBox.click();
		new CalendarElement(webDriver).selectDate(date);
		return this;
	}

}
