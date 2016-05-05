package gaston_caceres.training.globant.com.bookings.packageBooking.hotel;

public class HotelInfo {

	private String name;
	private HotelStars stars;
	private String price;
	private String telephone;
	private Integer userReviews;

	public HotelInfo() {
	}

	public String getName() {
		return name;
	}

	public void setName(String hotelName) {
		name = hotelName;
	}

	public HotelStars getStars() {
		return stars;
	}

	public void setStars(HotelStars hotelStars) {
		stars = hotelStars;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String hotelPrice) {
		price = hotelPrice;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String hotelTelephone) {
		telephone = hotelTelephone;
	}

	public Integer getUserReviews() {
		return userReviews;
	}

	public void setUserReviews(Integer hotelUserReviews) {
		userReviews = hotelUserReviews;
	}

}
