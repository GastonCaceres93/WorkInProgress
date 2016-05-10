package gaston_caceres.training.globant.com.bookings.hotel;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gaston_caceres.training.globant.com.utils.CalendarElement;

public class HotelBooking {

	private WebDriver webDriver;
	private String hotelName;
	private DateTime checkIn;
	private DateTime checkOut;

	private HotelSearch hotelSearch;
	
	private String currentHandle;

	public HotelBooking(WebDriver driver) {
		webDriver = driver;
		PageFactory.initElements(webDriver, this);
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
		this.checkIn = date;
		findElement(By.id("hotel-checkin"), 10).click();
		new CalendarElement(webDriver).selectDate(date);
		return this;
	}

	public HotelBooking selectCheckOutDate(DateTime date) {
		this.checkOut = date;
		findElement(By.id("hotel-checkout"), 10).click();
		new CalendarElement(webDriver).selectDate(date);
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
