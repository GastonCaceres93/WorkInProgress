package gaston_caceres.training.globant.com.bookings.cruises.misc;

import org.openqa.selenium.By;

public enum CruiseLenght {
	
	
	ANY(By.id("Any-length")),
	ONE_TO_TWO_NIGHTS(By.id("1|2-length")),
	THREE_TO_FIVE_NIGHTS(By.id("3|5-length")),
	SIX_TO_NINE_NIGHTS(By.id("6|9-length")),
	TEN_TO_FOURTEEN_NIGHTS(By.id("10|14-length")),
	FIFTEEN_PLUS_NIGHTS(By.id("15-length"));

	public static final By CONTAINER_LOCATOR = By.id("menu-length");
	public static final By UPDATE_RESULTS_LOCATOR = By.id("update-button-length");

	private By idLocator;

	private CruiseLenght(By locator) {
		idLocator = locator;
	}

	public By locator() {
		return idLocator;
	}

}
