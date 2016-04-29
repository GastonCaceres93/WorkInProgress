package gaston_caceres.training.globant.com.search;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import gaston_caceres.training.globant.com.utils.FlightSort;

public class FlightSearch {

	private static final String FLIGHTS_CONTAINER_ID = "flightModuleList";

	private Select flightSort;

	private List<WebElement> flightsFound;
	private WebDriver webDriver;

	private WebElement flightSelected;

	public FlightSearch(WebDriver webDriver) {
		this.webDriver = webDriver;
		initFields();
	}

	private void initFields() {
		this.flightSort = new Select((new WebDriverWait(webDriver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='sortBar']//select"))));
	}

	private void loadFlightsFound() {
		try {
			//haciendo tiempo para que ordene los vuelos de acuerdo al filtro.
			(new WebDriverWait(webDriver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("super_dummy")));
		} catch (Exception e) {
			// no me interesa la excepcion, porque no existe un elemto con id
			// super_dummy
		} finally {
			
			flightsFound = webDriver.findElement(By.id(FLIGHTS_CONTAINER_ID))
										.findElements(By.xpath("div/li"));

		}
	}

	public FlightSearch sortFlights(FlightSort sortBy) {
		this.flightSort.selectByVisibleText(sortBy.text());
		return this;
	}

	public FlightSearch selectFlight(int flightPosition) {
		try {
			loadFlightsFound();
			this.flightSelected = this.flightsFound.get(flightPosition - 1);
			String xpath = getFlightBuyXpath();
			WebElement buy = (new WebDriverWait(webDriver, 10))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			buy.click();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return this;
	}

	private String getFlightBuyXpath() {
		return ".//*[@id='" + this.flightSelected.getAttribute("id") + "']" + "//button";
	}

}
