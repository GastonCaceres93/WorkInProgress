package gaston_caceres.training.globant.com.bookings.packageBooking;

import org.joda.time.DateTime;

public class PackageInfo {

	private String departureAirport;
	private String arrivalAirport;

	private DateTime departureDate;
	private DateTime retournDate;

	private int children;
	private int adults;

	private String hotelName;
	private int hotelStars;
	private int rooms;

	private String carModel;

	private float packagePrice;
	private String packageType;

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public DateTime getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(DateTime departureDate) {
		this.departureDate = departureDate;
	}

	public DateTime getRetournDate() {
		return retournDate;
	}

	public void setRetournDate(DateTime retournDate) {
		this.retournDate = retournDate;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	public int getAdults() {
		return adults;
	}

	public void setAdults(int adults) {
		this.adults = adults;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public int getHotelStars() {
		return hotelStars;
	}

	public void setHotelStars(int hotelStars) {
		this.hotelStars = hotelStars;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public float getPackagePrice() {
		return packagePrice;
	}

	public void setPackagePrice(float packagePrice) {
		this.packagePrice = packagePrice;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getPackageType() {
		return this.packageType;
	}

}
