package gaston_caceres.training.globant.com.bookings.packageBooking.flight;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PackageFlight {

	private WebDriver webDriver;

	private boolean flightDone;

	private PackageFlightInfo departureFlight;
	private PackageFlightInfo retournFlight;

	private List<WebElement> flights;

	public PackageFlight(WebDriver driver) {
		webDriver = driver;
		flightDone = false;
		departureFlight = new PackageFlightInfo();
		retournFlight = new PackageFlightInfo();
	}

	public PackageFlight sort(FlightSort sortBy) {
		inProcess();
		try {
			new WebDriverWait(webDriver, 20).until(ExpectedConditions.presenceOfElementLocated(sortBy.locator()))
					.click();
		} catch (Exception e) {
			System.out.println("sort field not found....");
			e.printStackTrace();
		} finally {
			loadFlights();
		}
		return this;
	}

	private void inProcess() {
		flightDone = false;
	}

	private boolean flightsListReady() {
		return (new WebDriverWait(webDriver, 10))
				.until(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(By.id("resultList")),
						ExpectedConditions.invisibilityOfElementLocated(By.id("resultsInterstitial")),
						ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("resultList"))));
	}

	private void loadFlights() {
		if (flightsListReady()) {
			flights = webDriver.findElements(By.xpath(".//*[@Id='resultList']/div"));
		}
	}

	public PackageFlight selectDepartureFlight(int flightPosition) {
		WebElement flight = getFlight(flightPosition);
		updateFlightInfo(flight, departureFlight);
		selectFlight(flight);
		return this;
	}

	public PackageFlight selectRetournFlight(int flightPosition) {
		WebElement flight = getFlight(flightPosition);
		updateFlightInfo(flight, retournFlight);
		selectFlight(flight);
		return this;
	}

	private WebElement getFlight(int flightPosition) {
		WebElement flight = null;
		if (flights == null || flights.isEmpty()) {
			loadFlights();
		}
		try {
			flight = flights.get(flightPosition - 1);
		} catch (IndexOutOfBoundsException e) {
			//what should i do when the flight requested does not exist...
		}
		return flight != null ? flight : getFlight(flights.size());
	}

	private void selectFlight(WebElement flight) {
		flight.findElement(By.id("select")).click();
		loadFlights();
	}

	private void updateFlightInfo(WebElement flight, PackageFlightInfo flightInfo) {
		flightInfo.setDepartureAirport(
				flight.findElement(By.cssSelector(".dept.dTime")).findElement(By.xpath("span[1]")).getText());
		flightInfo.setDepartureTime(
				flight.findElement(By.cssSelector(".dept.dTime")).findElement(By.xpath("span[2]")).getText());

		flightInfo.setArrivalAirport(
				flight.findElement(By.cssSelector(".dest.aTime")).findElement(By.xpath("span[2]")).getText());
		flightInfo.setArrivalTime(
				flight.findElement(By.cssSelector(".dest.aTime")).findElement(By.xpath("span[3]")).getText());
	}

	public boolean flightDone() {
		return flightDone;
	}

	public PackageFlightInfo getDepartureFlightInfo() {
		return departureFlight;
	}

	public PackageFlightInfo getRetournFlightInfo() {
		return retournFlight;
	}

}
