package gaston_caceres.training.globant.com.utils;

import javax.swing.SortOrder;

import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import gaston_caceres.training.globant.com.TravelocityHome;
import gaston_caceres.training.globant.com.bookings.FlightBooking;
import gaston_caceres.training.globant.com.search.FlightSearch;

public class ElementHandling {

	public static void main(String[] args) {
		WebDriver webDriver = new FirefoxDriver();
		try {
			TravelocityHome home = new TravelocityHome(webDriver);
			long time = System.currentTimeMillis() + 12960000000L;
//			long timd = System.currentTimeMillis() + 13392000000L;
			DateTime date = new DateTime(time);
//			DateTime dated = new DateTime(timd);
			home.goHome();
			FlightBooking flight = home.goFlight().oneWayTrip();
			flight.selectDepartureDate(date);
//			flight.selectRetournDate(dated);

			flight.selectDepartureAirport("LAS");
			flight.selectDestinationAirport("LAX");
			
			flight.adultsTraveling(1);
			FlightSearch search = flight.searchFlights();
			search.sortFlights(FlightSort.DURATION_SHORTEST);
			
			
			search.selectFlight(1);

			// SimpleDateFormat f = new SimpleDateFormat("MMM yyyy");
			// String dateA= "abr 2016";
			// Date d = f.parse(dateA);
			//
			// DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
			// Locale.ENGLISH);
			// String formattedDate = df.format(date.toDate());
			//
			// SimpleDateFormat fs = new SimpleDateFormat("MM/dd/yy");
			// formattedDate = f.format(fs.parse(formattedDate));
			//
			// System.out.println(formattedDate);
			// System.out.println(f.format(d));

		} catch (Exception e) {
			e.printStackTrace();
			webDriver.quit();
		} finally {
			// webDriver.quit();
		}
	}

}
