package gaston_caceres.training.globant.com.bookings.packageBooking.cars;

public enum CarType {
	
	ECONOMY("class-EC"),
	COMPACT("class-CC"),
	MID_SIZE("class-IC"),
	STANDARD("class-SC"),
	FULL_SIZE("class-FC"),
	MINI_VAN("class-MV"),
	PREMIUM("class-PC"),
	SUV_LUXURY("class-LF"),
	SUV_COMPACT("class-CF"),
	SUV_STANDARD("class-SF"),
	SUV_MID_SIZE("class-IF"),
	SUV_FULL_SIZE("class-FF"),
	SUV_SPECIAL("class-XF"),
	SUV_PREMIUM("class-PF"),
	PICKUP_PREMIUM("class-PP"),
	LUXURY("class-LC"),
	SPORT_STANDARD("class-SS"),
	CONVERTIBLE_STANDARD("class-ST"),
	CONVERTIBLE_LUXURY("class-LT"),
	CONVERTIBLE_PREMIUM("class-PT"),
	SPECIAL("class-XC");
	
	private String id;
	private CarType(String idLocator){
		id = idLocator;
	}
	
	public String id(){
		return id;
	}
	

}
