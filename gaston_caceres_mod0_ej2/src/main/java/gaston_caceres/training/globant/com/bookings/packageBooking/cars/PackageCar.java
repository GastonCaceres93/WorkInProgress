package gaston_caceres.training.globant.com.bookings.packageBooking.cars;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PackageCar {

	private WebDriver webDriver;

	public PackageCar(WebDriver driver) {
		webDriver = driver;
	}

	private CarType carType;

	public PackageCar selectCarBy(CarType carType) {
		this.carType = carType;
		WebElement carTypeRow = (new WebDriverWait(webDriver, 25)
				.until(ExpectedConditions.presenceOfElementLocated(By.id(carType.id()))));
		WebElement car = getCar(carTypeRow, 1);
		car.click();
		return this;
	}

	private WebElement getCar(WebElement carList, int carPosition) {
		WebElement car = null;
		try {
			car = carList.findElement(By.xpath("td[" + carPosition + "]/div/a"));
		} catch (NoSuchElementException e) {

		} finally {
			carPosition++;
		}
		return car != null ? car : getCar(carList, carPosition);
	}

	public CarType getCarTypeSelected() {
		return this.carType;
	}

}
