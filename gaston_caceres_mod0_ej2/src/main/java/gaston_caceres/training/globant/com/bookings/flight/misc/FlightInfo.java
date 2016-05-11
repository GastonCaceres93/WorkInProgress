package gaston_caceres.training.globant.com.bookings.flight.misc;

import org.joda.time.DateTime;

public class FlightInfo {

	private String departureAirport;
	private String destinationAirport;
	private String tripType;
	private DateTime departureDate;
	private DateTime retournDate;
	private long travelTime;
	private float price;

	private int adults;
	private int children;
	
	public FlightInfo(){
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(String arrivingAirport) {
		this.destinationAirport = arrivingAirport;
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public DateTime getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(DateTime departureDate) {
		this.departureDate = departureDate;
	}

	public DateTime getRetournDate() {
		return this.retournDate;
	}

	public void setRetournDate(DateTime retournDate) {
		this.retournDate = retournDate;
	}

	public long getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(long travelTime) {
		this.travelTime = travelTime;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getPriceAsText(){
		return "$".concat(String.valueOf(price));
	}

	public int getAdults() {
		return adults;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

}
