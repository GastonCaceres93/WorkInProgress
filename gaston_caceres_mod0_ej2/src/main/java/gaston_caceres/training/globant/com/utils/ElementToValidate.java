package gaston_caceres.training.globant.com.utils;

import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;

public class ElementToValidate {

	private Set<ValidationType> validations;

	private By locator;

	private String attribute;

	private String value;

	public ElementToValidate(By locator, String attribute, String value, ValidationType... types) {
		this.validations = new HashSet<ValidationType>();
		for (ValidationType validation : types) {
			this.validations.add(validation);
		}
		this.locator = locator;
		this.value = value;
		this.attribute = attribute;
	}

	public By locator() {
		return this.locator;
	}

	public String value() {
		return this.value;
	}

	public Set<ValidationType> validations() {
		return this.validations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locator == null) ? 0 : locator.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElementToValidate other = (ElementToValidate) obj;
		if (locator == null) {
			if (other.locator != null)
				return false;
		} else if (!locator.equals(other.locator))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	public String getAttribute() {
		return this.attribute;
	}
}
