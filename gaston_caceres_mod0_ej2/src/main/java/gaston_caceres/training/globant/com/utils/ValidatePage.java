package gaston_caceres.training.globant.com.utils;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ValidatePage {

	public static boolean validateByElementsPresent(WebDriver webDriver, By... locators) {
		boolean validPage = true;

		for (By locator : locators) {
			if (!isElementPresent(locator, webDriver)) {
				validPage = false;
				break;
			}
		}

		return validPage;
	}

	public static boolean validateByElmentsAndValue(WebDriver webDriver, Map<String, By> elementsAndValue) {
		boolean validPage = true;

		for (String value : elementsAndValue.keySet()) {
			if (isElementPresent(elementsAndValue.get(value), webDriver)) {
				if (!value.equals(webDriver.findElement(elementsAndValue.get(value)).getText())) {
					validPage = false;
					break;
				}
			} else {
				validPage = false;
				break;
			}
		}

		return validPage;
	}

	private static boolean isElementPresent(By locator, WebDriver webDriver) {
		try {
			webDriver.findElement(locator);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
