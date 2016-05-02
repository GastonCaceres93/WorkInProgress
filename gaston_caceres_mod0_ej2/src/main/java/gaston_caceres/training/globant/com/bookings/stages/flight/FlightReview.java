package gaston_caceres.training.globant.com.bookings.stages.flight;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gaston_caceres.training.globant.com.utils.ElementToValidate;
import gaston_caceres.training.globant.com.utils.FlightInfo;
import gaston_caceres.training.globant.com.utils.ValidationType;

public class FlightReview {

	private WebDriver webDriver;

	public FlightReview(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public WhoIsTraveling continueBooking() {
		new WebDriverWait(webDriver, 3)
				.until(ExpectedConditions.presenceOfElementLocated(By.id("FlightUDPBookNowButton1"))).click();
		return new WhoIsTraveling(webDriver);
	}

	public Set<ElementToValidate> getElementsToValidateFlightReviewPage(FlightInfo flightInfo) {
		Set<ElementToValidate> elements = new HashSet<ElementToValidate>();

		elements.add(
				new ElementToValidate(By.id("FlightUDPTripMetaArrivalCity"), null, ValidationType.IS_ELEMENT_PRESENT));

		elements.add(new ElementToValidate(By.id("FlightUDPBookNowButton1"), null, ValidationType.IS_ELEMENT_PRESENT));

		elements.add(new ElementToValidate(By.id("trip-summary-title"), null, ValidationType.IS_ELEMENT_PRESENT));

		elements.add(new ElementToValidate(By.id("departure-date-0"),
				getDepartureDateFormated(flightInfo.getDepartureDate()), ValidationType.IS_ELEMENT_PRESENT, ValidationType.COMPLETE_TEXT));

		elements.add(new ElementToValidate(By.xpath(".//*[@Id='FlightUDPTripMetaArrivalCity']/h1"), "Review your trip",
				ValidationType.COMPLETE_TEXT));

		elements.add(new ElementToValidate(null, "https://www.travelocity.com/Details", ValidationType.PARTIAL_URL));

		return elements;
	}

	/**
	 * 
	 * @param date
	 * @return date with format MMM dd, yyyy
	 */
	private String getDepartureDateFormated(DateTime date) {
		String dateFormatted = null;
		return dateFormatted;
	}

}
