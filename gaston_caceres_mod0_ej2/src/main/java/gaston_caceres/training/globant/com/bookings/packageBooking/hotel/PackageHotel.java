package gaston_caceres.training.globant.com.bookings.packageBooking.hotel;

import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gaston_caceres.training.globant.com.utils.ElementToValidate;
import gaston_caceres.training.globant.com.utils.ValidationType;

public class PackageHotel {

	private WebDriver webDriver;
	
	private HotelSort sortedBy;

	public PackageHotel(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public PackageHotel sort(HotelSort sortBy) {
		try {
			this.sortedBy = sortBy;
			new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(sortBy.locator())).click();
		} catch (NoSuchElementException e) {
			System.out.println("sort field not found....");
		}
		return this;
	}
	
	public Set<ElementToValidate> getElementsToValidateSort(){
		Set<ElementToValidate> elements = new HashSet<ElementToValidate>();
		elements.add(new ElementToValidate(sortedBy.locator(), "class", "current", ValidationType.ATTRIBUTE));
		return elements;
	}

}
