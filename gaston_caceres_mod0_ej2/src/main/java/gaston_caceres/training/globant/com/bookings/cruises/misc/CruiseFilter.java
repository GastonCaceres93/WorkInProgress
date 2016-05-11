package gaston_caceres.training.globant.com.bookings.cruises.misc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CruiseFilter {

	private WebDriver webDriver;

	public CruiseFilter(WebDriver driver) {
		webDriver = driver;
	}

	public CruiseFilter filterByCruiseLenght(CruiseLenght lenght) {
		if(isSimpleFilter()){
			webDriver.findElement(lenght.locator()).click();
		}else{
			new WebDriverWait(webDriver, 3).until(ExpectedConditions.and(
					ExpectedConditions.presenceOfElementLocated(CruiseLenght.CONTAINER_LOCATOR),
					ExpectedConditions.elementToBeClickable(CruiseLenght.CONTAINER_LOCATOR)));
			
			webDriver.findElement(CruiseLenght.CONTAINER_LOCATOR).click();
			webDriver.findElement(lenght.locator()).click();
			webDriver.findElement(CruiseLenght.UPDATE_RESULTS_LOCATOR).click();
		}
		return this;
	}
	
	private boolean isSimpleFilter(){
		try {
			new WebDriverWait(webDriver, 5).until(ExpectedConditions.presenceOfElementLocated(By.id("cruise-side-menu")));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
