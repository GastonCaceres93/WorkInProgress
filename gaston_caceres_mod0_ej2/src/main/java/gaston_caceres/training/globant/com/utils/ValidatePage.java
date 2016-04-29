package gaston_caceres.training.globant.com.utils;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ValidatePage {

	public boolean validElements(WebDriver webDriver, Set<ElementToValidate> elementsToValidate) {
		boolean validPage = true;
		for (ElementToValidate element : elementsToValidate) {
			if (!validateElement(webDriver, element)) {
				validPage = false;
				break;
			}
		}
		return validPage;
	}

	private boolean validateElement(WebDriver webDriver, ElementToValidate element) {
		boolean valid = true;
		for (ValidationType validation : element.validations()) {
			switch (validation) {
			case COMPLETE_TEXT:
				valid = validateCompleteText(webDriver, element);
				break;
			case COMPLETE_URL:
				valid = validateCompleteURL(webDriver, element);
				break;
			case IS_ELEMENT_PRESENT:
				valid = isElementPresent(webDriver, element.locator());
				break;
			case PARTIAL_TEXT:
				valid = validatePartialText(webDriver, element);
				break;
			case PARTIAL_URL:
				valid = validatePartialURL(webDriver, element);
				break;
			default:
				break;
			}

			if (!valid) {
				break;
			}
		}

		return valid;
	}

	private boolean validateCompleteURL(WebDriver webDriver, ElementToValidate element) {
		return webDriver.getCurrentUrl().equals(element.value());
	}

	private boolean validatePartialURL(WebDriver webDriver, ElementToValidate element) {
		return webDriver.getCurrentUrl().contains(element.value());
	}

	private boolean validateCompleteText(WebDriver webDriver, ElementToValidate element) {
		WebElement webEl = (new WebDriverWait(webDriver, 3)
				.until(ExpectedConditions.presenceOfElementLocated(element.locator())));
		return element.value() != null && element.value().equals(webEl.getText());
	}

	private boolean validatePartialText(WebDriver webDriver, ElementToValidate element) {
		WebElement webEl = (new WebDriverWait(webDriver, 3)
				.until(ExpectedConditions.presenceOfElementLocated(element.locator())));
		return element.value() != null && webEl.getText().contains(element.value());
	}
	/*
	 * public boolean validateByElementsPresent(By... locators) { boolean
	 * validPage = true;
	 * 
	 * for (By locator : locators) { if (!isElementPresent(locator)) { validPage
	 * = false; break; } }
	 * 
	 * return validPage; }
	 * 
	 * public boolean validateByElmentsAndValue(Map<String, By>
	 * elementsAndValue) { boolean validPage = true;
	 * 
	 * for (String value : elementsAndValue.keySet()) { if
	 * (isElementPresent(elementsAndValue.get(value))) { if
	 * (!value.equals(webDriver.findElement(elementsAndValue.get(value)).getText
	 * ())) { validPage = false; break; } } else { validPage = false; break; } }
	 * 
	 * return validPage; }
	 */

	private boolean isElementPresent(WebDriver webDriver, By locator) {
		try {
			(new WebDriverWait(webDriver, 3)).until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}catch (TimeoutException e) {
			return false;
		}
	}

}
