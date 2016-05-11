package gaston_caceres.training.globant.com.bookings.packageBooking.hotel;

import org.openqa.selenium.By;

public enum HotelSort {
	BY_PRICE(By.id("sbarprice")),
	BY_GUEST_RATING(By.id("sbarguestrating")), 
	BY_STAR_RATING(By.id("sbarstarRating")), 
	BY_PACKAGE_DISCOUNT(By.id("sbarsavings")), 
	BY_MOST_POPULAR(By.id("sbarexpediapicks"));

	private By locator;

	private HotelSort(By locator) {
		this.locator = locator;
	}

	public By locator() {
		return this.locator;
	}
}
