package gaston_caceres.training.globant.com.bookings.flight;

public enum FlightSort {
	PRICE_LOWEST("Price (Lowest)"), 
	PRICE_HIGHEST("Price (Highest)"), 
	DURATION_SHORTEST("Duration (Shortest)"), 
	DURATION_LONGEST("Duration (Longest)"), 
	DEPARTURE_EARLIEST("Departure (Earliest)"),
	DEPARTURE_LATEST("Departure (Latest)"), 
	ARRIVAL_EARLIEST("Arrival (Earliest)"), 
	ARRIVAL_LATESTS("Arrival (Latests)");

	private String text;

	private FlightSort(String text) {
		this.text = text;
	}
	
	public String text(){
		return text;
	}
}
