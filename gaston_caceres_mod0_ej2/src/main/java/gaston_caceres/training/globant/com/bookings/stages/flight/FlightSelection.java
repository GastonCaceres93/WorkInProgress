package gaston_caceres.training.globant.com.bookings.stages.flight;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import gaston_caceres.training.globant.com.bookings.FlightBooking;
import gaston_caceres.training.globant.com.utils.ElementToValidate;
import gaston_caceres.training.globant.com.utils.FlightSort;
import gaston_caceres.training.globant.com.utils.ValidationType;

public class FlightSelection {

	private static final String FLIGHTS_CONTAINER_ID = "flightModuleList";
	private static final long HOURS_TO_MILLISECONDS = 3600000;
	private static final long MINUTES_TO_MILLISECONDS = 60000;

	private Select flightSort;
	private List<WebElement> flightsFound;
	private WebDriver webDriver;
	private FlightSort sortedBy;

	private WebElement flightSelected;

	public FlightSelection(WebDriver webDriver) {
		this.webDriver = webDriver;
		initFields();
	}

	private void initFields() {
		this.flightSort = new Select((new WebDriverWait(webDriver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='sortBar']//select"))));
	}

	private void loadFlightsFound() {
		try {
			// haciendo tiempo para que ordene los vuelos de acuerdo al filtro.
			(new WebDriverWait(webDriver, 3)).until(ExpectedConditions.presenceOfElementLocated(By.id("super_dummy")));
		} catch (Exception e) {
			// no me interesa la excepcion, porque no existe un elemto con id
			// super_dummy
		} finally {

			flightsFound = webDriver.findElement(By.id(FLIGHTS_CONTAINER_ID)).findElements(By.xpath("div/li"));

		}
	}

	public FlightSelection sortFlights(FlightSort sortBy) {
		this.sortedBy = sortBy;
		this.flightSort.selectByVisibleText(this.sortedBy.text());
		return this;
	}

	public FlightReview selectFlight(int flightPosition) {
		try {
			loadFlightsFound();
			this.flightSelected = this.flightsFound.get(flightPosition - 1);
			String xpath = getFlightBuyXpath();
			WebElement buy = (new WebDriverWait(webDriver, 10))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			updateFlightInfo();
			buy.click();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return new FlightReview(webDriver);
	}
	// public FlightSelection selectFlight(int flightPosition) {
	// try {
	// loadFlightsFound();
	// this.flightSelected = this.flightsFound.get(flightPosition - 1);
	// String xpath = getFlightBuyXpath();
	// WebElement buy = (new WebDriverWait(webDriver, 10))
	// .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
	// buy.click();
	// } catch (IndexOutOfBoundsException e) {
	// e.printStackTrace();
	// }
	// return this;
	// }

	private String getFlightBuyXpath() {
		return ".//*[@id='" + this.flightSelected.getAttribute("id") + "']" + "//button";
	}

	public boolean validateSort() {
		loadFlightsFound();
		boolean valid = true;
		switch (sortedBy) {
		case ARRIVAL_EARLIEST:
			valid = sortedByArrivalTime(true);
			break;
		case ARRIVAL_LATESTS:
			valid = sortedByArrivalTime(false);
			break;
		case DEPARTURE_EARLIEST:
			valid = sortedByDepartureTime(true);
			break;
		case DEPARTURE_LATEST:
			valid = sortedByDepartureTime(false);
			break;
		case DURATION_SHORTEST:
			valid = sortedByDurationTime(true);
			break;
		case DURATION_LONGEST:
			valid = sortedByDurationTime(false);
			break;
		case PRICE_HIGHEST:
			valid = sortedByPrice(false);
			break;
		case PRICE_LOWEST:
			valid = sortedByPrice(true);
			break;

		default:
			valid = false;
			break;
		}
		return valid;
	}

	private long getArrivalTime(WebElement flight) {
		String arrival = flight.findElement(By.className(".arrival-time.arrival-0-emphasis")).getText();
		return getTime(arrival);
	}

	private long getDepartureTime(WebElement flight) {
		String departure = flight.findElement(By.className(".departure-time.departure-0-emphasis")).getText();
		return getTime(departure);
	}

	private long getFlightDuration(WebElement flight) {
		String duration = flight.findElement(By.cssSelector(".primary.duration-emphasis")).getText();
		duration = duration.replace("h ", ":");
		return getTime(duration);
	}

	private long getTime(String time) {
		String[] timeA = time.split(":");
		long hours = Long.valueOf(timeA[0]) * HOURS_TO_MILLISECONDS;
		long minutes = Long.valueOf(timeA[1].substring(0, 1)) * MINUTES_TO_MILLISECONDS;
		return hours + minutes;
	}

	private float getPrice(WebElement flight) {
		return (Float.valueOf(flight.findElement(By.cssSelector(".price-column.price-split-line"))
				.getAttribute("data-test-price-per-traveler").substring(1)));
	}

	private boolean sortedByArrivalTime(boolean earliest) {
		boolean valid = true;
		long firstArrivalTime = getArrivalTime(this.flightsFound.get(0));
		long lastArrivalTime = getArrivalTime(this.flightsFound.get(this.flightsFound.size() - 1));
		if (earliest) {
			valid = firstArrivalTime < lastArrivalTime;
		} else {
			valid = firstArrivalTime > lastArrivalTime;
		}
		return valid;
	}

	private boolean sortedByDepartureTime(boolean earliest) {
		boolean valid = true;
		long firstDepartureTime = getDepartureTime(this.flightsFound.get(0));
		long lastDepartureTime = getDepartureTime(this.flightsFound.get(this.flightsFound.size() - 1));
		if (earliest) {
			valid = firstDepartureTime < lastDepartureTime;
		} else {
			valid = firstDepartureTime > lastDepartureTime;
		}
		return valid;

	}

	private boolean sortedByDurationTime(boolean shortest) {
		boolean valid = true;
		long firstFlightDuration = getFlightDuration(this.flightsFound.get(0));
		long lastFlightDuration = getFlightDuration(this.flightsFound.get(this.flightsFound.size() - 1));
		if (shortest) {
			valid = firstFlightDuration < lastFlightDuration;
		} else {
			valid = firstFlightDuration > lastFlightDuration;
		}
		return valid;
	}

	private boolean sortedByPrice(boolean lowest) {
		boolean valid = true;
		float firstPrice = getPrice(this.flightsFound.get(0));
		float lastPrice = getPrice(this.flightsFound.get(this.flightsFound.size() - 1));
		if (lowest) {
			valid = firstPrice < lastPrice;
		} else {
			valid = firstPrice > lastPrice;
		}
		return valid;
	}

	public Set<ElementToValidate> getElementsToValidateFlightSelectionPage() {
		Set<ElementToValidate> elements = new HashSet<ElementToValidate>();

		elements.add(new ElementToValidate(By.xpath(".//*[@Id='columnAFilter']/h3"), "Filter your results by",
				ValidationType.IS_ELEMENT_PRESENT, ValidationType.COMPLETE_TEXT));

		elements.add(
				new ElementToValidate(null, "ttps://www.travelocity.com/Flights-Search?", ValidationType.PARTIAL_URL));

		elements.add(new ElementToValidate(By.id("wizardSearch"), null, ValidationType.IS_ELEMENT_PRESENT));

		elements.add(new ElementToValidate(By.id("flightModuleList"), null, ValidationType.IS_ELEMENT_PRESENT));

		elements.add(new ElementToValidate(By.id("departureAirport"), null, ValidationType.IS_ELEMENT_PRESENT));

		return elements;
	}
	
	private void updateFlightInfo(){
		FlightBooking.getFlightInfo().setPrice(getPrice(flightSelected));
		FlightBooking.getFlightInfo().setTravelTime(getFlightDuration(flightSelected));
	}
}
