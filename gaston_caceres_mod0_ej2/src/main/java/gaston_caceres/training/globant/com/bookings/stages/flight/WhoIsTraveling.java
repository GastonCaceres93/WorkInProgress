package gaston_caceres.training.globant.com.bookings.stages.flight;

import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import gaston_caceres.training.globant.com.utils.ElementToValidate;
import gaston_caceres.training.globant.com.utils.ValidationType;

public class WhoIsTraveling {

	private WebDriver webDriver;

	public WhoIsTraveling(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public Set<ElementToValidate> getElementsToValidateFlightReviewPage() {
		Set<ElementToValidate> elements = new HashSet<ElementToValidate>();

		elements.add(new ElementToValidate(By.xpath(".//*[@id='preferences']/form/fieldset/h2"), "Who's traveling?", ValidationType.COMPLETE_TEXT));

		elements.add(new ElementToValidate(By.id("cko-form"), null, ValidationType.IS_ELEMENT_PRESENT));

		elements.add(new ElementToValidate(By.id("trip-summary"), null, ValidationType.IS_ELEMENT_PRESENT));

		elements.add(new ElementToValidate(By.id("login-module-toggle"), null, ValidationType.IS_ELEMENT_PRESENT));
		
		elements.add(new ElementToValidate(null, "https://www.travelocity.com/FlightCheckout", ValidationType.PARTIAL_URL));

		return elements;
	}

}
