package gaston_caceres.training.globant.com.tests;

import org.joda.time.DateTime;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import gaston_caceres.training.globant.com.TravelocityHome;
import gaston_caceres.training.globant.com.bookings.FlightBooking;
import gaston_caceres.training.globant.com.bookings.stages.flight.FlightReview;
import gaston_caceres.training.globant.com.bookings.stages.flight.FlightSelection;
import gaston_caceres.training.globant.com.utils.FlightInfo;
import gaston_caceres.training.globant.com.utils.FlightSort;
import gaston_caceres.training.globant.com.utils.ValidatePage;

public class ExerciseTwoTests {

	private TravelocityHome travelocityHome;

	@BeforeClass
	public void before() {
		this.travelocityHome = new TravelocityHome(new FirefoxDriver());
	}

	@Test
	public void test_01() {

		long seventy_days_from_now = 6048000000L;
		DateTime departureDate = new DateTime(System.currentTimeMillis() + seventy_days_from_now);
		String departureAirport = "LAS";
		String arrivalAirport = "LAX";
		int adults = 1;
		int children = 0;

		FlightBooking flight = this.travelocityHome.goHome().goFlight();

		flight.oneWayTrip();
		flight.selectArrivalAirport(arrivalAirport);
		flight.selectDepartureAirport(departureAirport);
		flight.selectDepartureDate(departureDate);
		flight.adultsTraveling(adults);
		flight.childrenTraveling(children);

		FlightSelection flightSelection = flight.searchFlights();

		// validate correct page
		ValidatePage pageValidation = new  ValidatePage(travelocityHome.getDriver());
		assert(pageValidation.validElements(flightSelection.getElementsToValidateFlightSelectionPage()));
		
		
		flightSelection.sortFlights(FlightSort.DURATION_SHORTEST);

		// validate sort
		assert(flightSelection.validateSort());
		
		FlightReview flightReview = flightSelection.selectFlight(1);

		/**
		 *no estoy seguro en que pagina se realizarian las siguientes acciones, quizas el examen esta desactualizado.<br>
		 * 5.In the new page (Select your departure to Los Angeles), select the
		 * first result.<br>
		 * 6. In the new page (Select your departure to Las Vegas), select the
		 * third result. <br>
		 * 7.In the pop-up, select “no, thanks, maybe later”
		 */

		// validate trip details
		//Verify the trip information is correct using 5 validations. Continue
		FlightInfo flightInfo = flight.getFlightInfo();
		assert(pageValidation.validElements(flightReview.getElementsToValidateFlightReviewPage(flightInfo)));
		
		flightReview.continueBooking();
		//Verify the “Who’s travelling” page is opened using at least 5 validations.

	}
	
	@AfterClass
	public void after(){
		this.travelocityHome.quit();
	}

}
