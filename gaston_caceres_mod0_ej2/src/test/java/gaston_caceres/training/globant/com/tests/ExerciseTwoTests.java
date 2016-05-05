package gaston_caceres.training.globant.com.tests;

import org.joda.time.DateTime;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import gaston_caceres.training.globant.com.TravelocityHome;
import gaston_caceres.training.globant.com.bookings.flight.FlightBooking;
import gaston_caceres.training.globant.com.bookings.flight.FlightInfo;
import gaston_caceres.training.globant.com.bookings.flight.FlightReview;
import gaston_caceres.training.globant.com.bookings.flight.FlightSelection;
import gaston_caceres.training.globant.com.bookings.flight.FlightSort;
import gaston_caceres.training.globant.com.bookings.flight.WhoIsTraveling;
import gaston_caceres.training.globant.com.bookings.packageBooking.PackageBooking;
import gaston_caceres.training.globant.com.bookings.packageBooking.hotel.HotelSort;
import gaston_caceres.training.globant.com.bookings.packageBooking.hotel.HotelStars;
import gaston_caceres.training.globant.com.utils.ValidatePage;

public class ExerciseTwoTests {

	private TravelocityHome travelocityHome;

	@BeforeMethod
	public void before() {
		travelocityHome = new TravelocityHome(new FirefoxDriver());
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
		flight.selectDepartureAirport(departureAirport);
		flight.selectArrivalAirport(arrivalAirport);
		flight.adultsTraveling(adults);
		flight.selectDepartureDate(departureDate);
		flight.childrenTraveling(children);

		FlightSelection flightSelection = flight.searchFlights();

		travelocityHome.closeOtherHandles();

		ValidatePage pageValidation = new ValidatePage(travelocityHome.getDriver());
		assert (pageValidation.validElements(flightSelection.getElementsToValidateFlightSelectionPage()));

		flightSelection.sortFlights(FlightSort.DURATION_SHORTEST);
		assert (flightSelection.validateSort());

		FlightReview flightReview = flightSelection.selectFlight(1);

		/**
		 * no estoy seguro en que pagina se realizarian las siguientes acciones,
		 * quizas el examen esta desactualizado.<br>
		 * 5.In the new page (Select your departure to Los Angeles), select the
		 * first result.<br>
		 * 6. In the new page (Select your departure to Las Vegas), select the
		 * third result. <br>
		 * 7.In the pop-up, select “no, thanks, maybe later”
		 */

		FlightInfo flightInfo = FlightBooking.getFlightInfo();

		assert (pageValidation.validElements(flightReview.getElementsToValidateFlightReviewPage(flightInfo)));

		WhoIsTraveling whoIsTraveling = flightReview.continueBooking();
		assert (pageValidation.validElements(whoIsTraveling.getElementsToValidateFlightReviewPage()));
	}

	@Test
	public void test_02() {

		long seventy_days = 6048000000L;
		long thirteen_days = 1123200000L;
		DateTime departureDate = new DateTime(System.currentTimeMillis() + seventy_days);
		DateTime retournDate = new DateTime(departureDate.getMillis() + thirteen_days);
		String departureAirport = "LAS";
		String arrivalAirport = "LAX";
		int adults = 1;
		int children = 0;
		int rooms = 1;

		PackageBooking packageBooking = travelocityHome.goHome().goFlightAndHotel();
		ValidatePage pageValidation = new ValidatePage(travelocityHome.getDriver());

	
		packageBooking.selectDepartureAirport(departureAirport);
		packageBooking.selectArrivalAirport(arrivalAirport);
		packageBooking.selectDepartureDate(departureDate);
		packageBooking.selectRetournDate(retournDate);

		packageBooking.childrenTraveling(children);
		packageBooking.adultsTraveling(adults);
		packageBooking.rooms(rooms);

		//hotel and room
		packageBooking.search();
		packageBooking.hotel().sort(HotelSort.BY_PRICE);
		assert (pageValidation.validElements(packageBooking.hotel().getElementsToValidateSort()));
		packageBooking.hotel().selectHotelBy(HotelStars.TWO_AND_HALF);
		packageBooking.hotel().continueBooking();
		packageBooking.hotel().selectFirstRoomAvailable();
		
		
		//flight
		packageBooking.flight();
		
		

	}

	@AfterMethod
	public void after() {
//		this.travelocityHome.quit();
	}

}
