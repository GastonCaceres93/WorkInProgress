package gaston_caceres.training.globant.com.bookings.cruises.misc;

public enum Destinations {

	CARIBEAN("caribbean"),
	BAHAMAS("bahamas"),
	MEXICO("mexico"),
	ALASKA("alaska"),
	EUROPE("europe"),
	BERMUDA("bermuda"),
	HAWAII("hawaii"),
	CANADA_NEW_ENGLAND("canada-new-england"),
	AFRICA("africa"),
	ARTIC_ANTARTIC("arctic-antarctic"),
	ASIA("asia"),
	AUSTRALIA_NEW_ZELAND("australia-new-zealand"),
	CENTRAL_AMERICA("central-america"),
	GALAPAGOS("galapagos"),
	GET_AWAY_AT_SEA("getaway-at-sea"),
	MIDDLE_EAST("middle-east"),
	PACIFIC_COASTAL("pacific-coastal"),
	PANAMA_CANAL("panama-canal"),
	SOUTH_AMERICA("south-america"),
	SOUTH_PACIFIC("south-pacific"),
	TRANSATRLANTIC("transatlantic"),
	TRANSPACIFIC("transpacific"),
	WORLD("world-cruise");
	
	private String value;
	
	private Destinations(String value){
		this.value = value;
	}
	
	public String value(){
		return value;
	}
	
	
}
