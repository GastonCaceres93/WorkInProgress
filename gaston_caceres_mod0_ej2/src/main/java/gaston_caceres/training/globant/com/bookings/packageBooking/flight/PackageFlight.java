package gaston_caceres.training.globant.com.bookings.packageBooking.flight;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PackageFlight {

	private WebDriver webDriver;

	private WebElement departureFlight;

	private WebElement returnFlight;

	private FlightSort sortedBy;

	private boolean flightDone;
	
	private List<WebElement> flights;

	public PackageFlight(WebDriver driver) {
		webDriver = driver;
		flightDone = false;
	}

	public PackageFlight sort(FlightSort sortBy) {
		inProcess();
		try {
			this.sortedBy = sortBy;
			new WebDriverWait(webDriver, 20).until(ExpectedConditions.presenceOfElementLocated(sortBy.locator())).click();
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
		return (new WebDriverWait(webDriver, 10)).until(
				ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(By.id("packageSearchResults")),
						ExpectedConditions.invisibilityOfElementLocated(By.id("divInterstitial"))));
	}
	
	private void loadFlights(){
		
	}

}
