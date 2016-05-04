package gaston_caceres.training.globant.com.bookings.packageBooking.hotel;

public enum HotelStars {

	ONE(".value.stars1-0"), 
	ONE_AND_HALF(".value.stars1-5"), 
	TWO(".value.stars2-0"), 
	TWO_AND_HALF(".value.stars2-5"), 
	THREE(".value.stars3-0"), 
	THREE_AND_HALF(".value.stars3-5"), 
	FOUR(".value.stars4-0"), 
	FOUR_AND_HALF(".value.stars4-5"), 
	FIVE(".value.stars5-0");

	private String cssClass;

	private HotelStars(String cssClass) {
		this.cssClass = cssClass;
	}
	
	public String cssLocator(){
		return cssClass;
	}
	
	
	public static HotelStars byValue(String css){
		for(HotelStars starsCss : HotelStars.values()){
			if(starsCss.cssLocator().equals(css)){
				return starsCss;
			}
		}
		return null;
	}
}
