package gaston_caceres.training.globant.com.bookings.packageBooking.hotel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gaston_caceres.training.globant.com.bookings.packageBooking.PackageBooking;
import gaston_caceres.training.globant.com.utils.ElementToValidate;
import gaston_caceres.training.globant.com.utils.ValidationType;

public class PackageHotel {

	private WebDriver webDriver;

	private HotelSort sortedBy;

	private List<WebElement> hotels;

	private WebElement hotelSelected;

	private HotelInfo hotel;

	private boolean hotelBookingReady;
	
	public PackageHotel(WebDriver webDriver) {
		this.webDriver = webDriver;
		hotel = new HotelInfo();
		inProcess();
	}

	public PackageHotel sort(HotelSort sortBy) {
		inProcess();
		try {
			this.sortedBy = sortBy;
			new WebDriverWait(webDriver, 20).until(ExpectedConditions.presenceOfElementLocated(sortBy.locator()))
					.click();
		} catch (Exception e) {
			System.out.println("sort field not found....");
			e.printStackTrace();
		} finally {
			loadHotels();
		}
		return this;
	}

	public Set<ElementToValidate> getElementsToValidateSort() {
		Set<ElementToValidate> elements = new HashSet<ElementToValidate>();
		elements.add(new ElementToValidate(sortedBy.locator(), "class", "current", ValidationType.ATTRIBUTE));
		return elements;
	}

	private void loadHotels() {
		if (hotelListReady()) {
			hotels = webDriver.findElements(By.xpath(".//*[@Id='packageSearchResults']/div"));
		}
	}

	public PackageHotel selectHotelBy(HotelStars hotelStars) {
		hotelSelected = null;
		HotelStars hotelS = null;
		while (hotelSelected == null) {
			for (WebElement hotel : hotels) {
				hotelS = getHotelStars(hotel);
				if (hotelStars.equals(hotelS) || hotelS.ordinal() > hotelStars.ordinal()) {
					hotelSelected = hotel;
					hotelBookingReady = true;
					break;
				}
			}
			// TODO buscar otra forma
			if (hotelSelected == null)
				nextPageHotels();
		}
		return this;
	}

	private HotelStars getHotelStars(WebElement hotel) {
		StringBuilder starsBuilder = new StringBuilder(".");
		starsBuilder.append(hotel.findElement(By.cssSelector(".hotel_name")).findElement(By.xpath("div/span"))
				.getAttribute("class"));
		String stars = starsBuilder.toString().replace(" ", ".");
		return HotelStars.byValue(stars);
	}

	private String getHotelName(WebElement hotelRaw) {
		String name = hotelRaw.findElement(By.cssSelector(".hotel_name")).findElement(By.xpath("h2")).getText();
		return name;
	}

	private String getHotelPrice(WebElement hotelRaw) {
		String price = hotelRaw.findElement(By.cssSelector(".formatted_price")).getText();
		return price;
	}

	private boolean hotelListReady() {
		return (new WebDriverWait(webDriver, 10)).until(
				ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(By.id("packageSearchResults")),
						ExpectedConditions.invisibilityOfElementLocated(By.id("divInterstitial"))));
	}

	public PackageHotel nextPageHotels() {
		webDriver.findElement(By.id("nextpage")).click();
		loadHotels();
		return this;
	}

	public PackageHotel previuosPageHotels() {
		webDriver.findElement(By.id("previouspage")).click();
		loadHotels();
		return this;
	}

	public boolean hotelDone() {
		return hotelBookingReady;
	}

	public PackageHotel selectFirstRoomAvailable() {
		changeToRoomWindow();
		
		WebElement roomsContainer = (new WebDriverWait(webDriver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("rooms-and-rates")));
		List<WebElement> rooms = roomsContainer
				.findElements(By.cssSelector(".segment.no-target.room.cf.room-above-fold"));
		rooms.get(0).findElement(By.cssSelector(".btn.btn-secondary.btn-sub-action.book-button")).click();
		return this;
	}

	private void inProcess() {
		hotelBookingReady = false;
	}

	public PackageHotel continueBooking() {
		updateHotelInfo();
		hotelSelected.click();
		return this;
	}

	private void updateHotelInfo() {
		hotel.setName(getHotelName(hotelSelected));
		hotel.setPrice(getHotelPrice(hotelSelected));
		hotel.setStars(getHotelStars(hotelSelected));
	}

	public HotelInfo getHotelInfo() {
		return hotel;
	}
	
	private void changeToRoomWindow(){
		for(String handle : webDriver.getWindowHandles()){
			if(!handle.equals(PackageBooking.currentHandle())){
				PackageBooking.updateCurrentHandle(handle);
			}else{
				webDriver.switchTo().window(handle).close();
			}
		}
		webDriver.switchTo().window(PackageBooking.currentHandle());
	}

}
