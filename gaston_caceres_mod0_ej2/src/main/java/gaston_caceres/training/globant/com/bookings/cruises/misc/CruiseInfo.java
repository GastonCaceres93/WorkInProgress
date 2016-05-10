package gaston_caceres.training.globant.com.bookings.cruises.misc;

import java.util.Date;

public class CruiseInfo {

	private Destinations destination;
	private Date departure;
	private String price;

	public void setDestination(Destinations destination) {
		this.destination = destination;
	}

	public Destinations getDestination() {
		return destination;
	}

	public void setDeparture(Date departureDate) {
		this.departure = departureDate;
	}

	public Date getDepartureDate() {
		return this.departure;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPrice() {
		return this.price;
	}
}
