package gaston_caceres.training.globant.com.bookings.packageBooking.cars;

public enum CarType {
	
	ECONOMY("class-EC","Economy Car"),
	COMPACT("class-CC","Compact Car"),
	MID_SIZE("class-IC","Midsize Car"),
	STANDARD("class-SC","Standard Car"),
	FULL_SIZE("class-FC","Fullsize Car"),
	MINI_VAN("class-MV","Mini Van"),
	PREMIUM("class-PC","Premium Car"),
	SUV_LUXURY("class-LF","Luxury SUV"),
	SUV_COMPACT("class-CF","Compact SUV"),
	SUV_STANDARD("class-SF","Standard SUV"),
	SUV_MID_SIZE("class-IF","Midsize SUV"),
	SUV_FULL_SIZE("class-FF","Fullsize SUV"),
	SUV_SPECIAL("class-XF","Special SUV"),
	SUV_PREMIUM("class-PF","Premium SUV"),
	PICKUP_PREMIUM("class-PP","Premium Pickup Regular Cab"),
	LUXURY("class-LC","Luxury Car"),
	SPORT_STANDARD("class-SS","Standard SportsCar"),
	CONVERTIBLE_STANDARD("class-ST","Standard Convertible"),
	CONVERTIBLE_LUXURY("class-LT",""),
	CONVERTIBLE_PREMIUM("class-PT",""),
	SPECIAL("class-XC","Special Car");
	
	private String id;
	private String carTypeVisible;
	
	private CarType(String idLocator,String carTypeVisible){
		id = idLocator;
		this.carTypeVisible=carTypeVisible;
	}
	
	public String id(){
		return id;
	}
	
	public String visibleName(){
		return carTypeVisible;
	}
	

}
