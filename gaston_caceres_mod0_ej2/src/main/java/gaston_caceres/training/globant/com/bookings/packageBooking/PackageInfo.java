package gaston_caceres.training.globant.com.bookings.packageBooking;

import org.joda.time.DateTime;

import gaston_caceres.training.globant.com.bookings.packageBooking.flight.PackageFlightInfo;
import gaston_caceres.training.globant.com.bookings.packageBooking.hotel.HotelInfo;

public class PackageInfo {

	private String departureAirport;
	private String arrivalAirport;

	private DateTime departureDate;
	private DateTime retournDate;

	private int children;
	private int adults;

	private HotelInfo hotel;
	private PackageFlightInfo departureFlight;
	private PackageFlightInfo retournFlight;
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

	public void setHotel(HotelInfo hotelInfo) {
		hotel = hotelInfo;
	}

	public HotelInfo getHotel() {
		return hotel;
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

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public int getRooms() {
		return rooms;
	}
	
	public void setDepartureFlight(PackageFlightInfo flightInfo) {
		departureFlight = flightInfo;
	}
	public PackageFlightInfo getDepartureFlight(){
		return departureFlight;
	}
	public void setRetourneFlight(PackageFlightInfo flightInfo) {
		retournFlight = flightInfo;
	}
	public PackageFlightInfo getRetournFlight(){
		return retournFlight;
	}

}
