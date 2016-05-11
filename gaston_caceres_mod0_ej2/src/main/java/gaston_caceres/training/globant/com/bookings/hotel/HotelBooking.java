package gaston_caceres.training.globant.com.bookings.hotel;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gaston_caceres.training.globant.com.utils.CalendarHelper;

public class HotelBooking {

	private WebDriver webDriver;
	private String hotelName;

	private HotelSearch hotelSearch;
	
	private String currentHandle;
	
	private CalendarHelper calendarHelper;

	public HotelBooking(WebDriver driver) {
		webDriver = driver;
		calendarHelper = new CalendarHelper(driver);
	}

	public HotelBooking hotelOnly() {
		findElement(By.id("tab-hotel-tab"), 10).click();
		return this;
	}

	public HotelBooking setHotelName(String hotelName) {
		this.hotelName = hotelName;
		findElement(By.id("hotel-destination"), 10).sendKeys(hotelName);
		return this;
	}

	public HotelBooking selectCheckInDate(DateTime date) {
		findElement(By.id("hotel-checkin"), 10).click();
		calendarHelper.selectDate(date);
		return this;
	}

	public HotelBooking selectCheckOutDate(DateTime date) {
		findElement(By.id("hotel-checkout"), 10).click();
		calendarHelper.selectDate(date);
		return this;
	}

	public HotelBooking search() {
		currentHandle = webDriver.getWindowHandle();

		webDriver.findElement(By.id("search-button")).click();
		
		hotelSearch = new HotelSearch(webDriver, hotelName);
		
		closeOtherWindows(currentHandle);
		return this;
	}

	private WebElement findElement(By by, int timeout) {
		new WebDriverWait(webDriver, timeout)
				.until(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(by)));
		return webDriver.findElement(by);
	}

	public void updateHandle(String handle) {
		currentHandle = handle;
	}

	public void closeOtherWindows(String keepHandle) {
		for (String handle : webDriver.getWindowHandles()) {
			if (!keepHandle.equals(handle)) {
				webDriver.switchTo().window(handle).close();
			}
		}
		webDriver.switchTo().window(keepHandle);
	}
	
	public HotelSearch hotelSearch(){
		return hotelSearch;
	}

}
