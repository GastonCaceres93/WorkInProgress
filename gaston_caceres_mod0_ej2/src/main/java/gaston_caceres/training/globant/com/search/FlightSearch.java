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

	private WebElement flightSorter;
	private Select flightSort;
	
	private List<WebElement> flightsFound;
	private WebDriver webDriver;

	private WebElement flightSelected;

	public FlightSearch(WebDriver webDriver) {
		this.webDriver = webDriver;

		initFields();
	}

	private void initFields() {
//		this.flightSorter = (new WebDriverWait(webDriver, 10))
//				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='sortBar']//select")));
		this.flightSort = new Select((new WebDriverWait(webDriver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='sortBar']//select"))));
		loadFlightsFound();
	}

	private void loadFlightsFound() {
		WebElement container = (new WebDriverWait(webDriver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id(FLIGHTS_CONTAINER_ID)));
		flightsFound = container.findElements(By.xpath("div/li"));
	}

	public FlightSearch sortFlights(FlightSort sortBy) {
		this.flightSort.selectByVisibleText(sortBy.text());
		loadFlightsFound();
		return this;
	}

	public FlightSearch selectFlight(int flightPosition) {
		try {
//			this.flightSelected = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='"+FLIGHTS_CONTAINER_ID+"']/div/li[" + flightPosition + "]")));
			this.flightSelected = this.flightsFound.get(flightPosition - 1);
			String xpath = getFlightBuyXpath();
			WebElement buy = (new WebDriverWait(webDriver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
//			WebElement buy = flightSelected.findElement(By.xpath("//button"));
//			this.flightSelected.findElement(By.xpath("//button")).click();
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
