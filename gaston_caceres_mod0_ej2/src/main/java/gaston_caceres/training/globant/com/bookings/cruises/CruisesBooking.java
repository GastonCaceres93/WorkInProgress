package gaston_caceres.training.globant.com.bookings.cruises;

import java.text.SimpleDateFormat;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import gaston_caceres.training.globant.com.bookings.cruises.misc.CruiseInfo;
import gaston_caceres.training.globant.com.bookings.cruises.misc.Destinations;

public class CruisesBooking {

	private WebDriver webDriver;
	private CruiseInfo cruise;
	private CruisesSelection cruiseSelection;

	public CruisesBooking(WebDriver webDriver) {
		this.webDriver = webDriver;
		cruise = new CruiseInfo();
		PageFactory.initElements(webDriver, this);
	}

	public CruisesBooking selectDestination(Destinations destination) {
		By locator = By.id("cruise-destination");
		Select destinationSelect = new Select(webDriver.findElement(locator));
		new WebDriverWait(webDriver, 1).until(ExpectedConditions.elementToBeClickable(locator));
		destinationSelect.selectByValue(destination.value());
		cruise.setDestination(destination);
		return this;
	}

	/**
	 * @param monthYear
	 *            with format <b>MM yyyy</b>
	 */
	public CruisesBooking selectDepartureMonth(String monthYear) {
		By locator= By.id("cruise-departure-month");
		
		Select departureMonth = new Select((new WebDriverWait(webDriver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(locator)));
		
		new WebDriverWait(webDriver, 1).until(ExpectedConditions.elementToBeClickable(locator));
		
		departureMonth.selectByValue(getDepartureValue(monthYear));
		return this;
	}

	private String getDepartureValue(String monthYear) {
		String departureValue = null;
		try {
			DateTime departureDate = new DateTime(new SimpleDateFormat("MM yyyy").parse(monthYear));
			cruise.setDeparture(departureDate.toDate());
			departureValue = new SimpleDateFormat("yyyy-MM-dd").format(departureDate.toDate());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return departureValue;
	}

	public CruisesBooking search() {
		webDriver.findElement(By.id("search-button")).click();
		return this;
	}
	
	public CruisesSelection cruiseSelection(){
		if(cruiseSelection==null){
			cruiseSelection = new CruisesSelection(webDriver);
		}
		return cruiseSelection;
	}

}
