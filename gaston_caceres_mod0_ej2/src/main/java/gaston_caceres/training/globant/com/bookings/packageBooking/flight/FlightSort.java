package gaston_caceres.training.globant.com.bookings.packageBooking.flight;

import org.openqa.selenium.By;

public enum FlightSort {
	DEPARTURE_TIME(By.id("departure")),
	ARRIVAL_TIME(By.id("arrival")),
	STOPS(By.id("stops")),
	DURATION(By.id("duration")),
	PRICE(By.id("price"));
	
	private By locator;

	private FlightSort(By locator) {
		this.locator = locator;
	}

	public By locator() {
		return locator;
	}
}
