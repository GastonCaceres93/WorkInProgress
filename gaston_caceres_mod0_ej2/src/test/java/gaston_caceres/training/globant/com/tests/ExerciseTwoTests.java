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
import gaston_caceres.training.globant.com.bookings.packageBooking.cars.CarType;
import gaston_caceres.training.globant.com.bookings.packageBooking.hotel.HotelSort;
import gaston_caceres.training.globant.com.bookings.packageBooking.hotel.HotelStars;
import gaston_caceres.training.globant.com.utils.ValidatePage;

public class ExerciseTwoTests {

	private TravelocityHome travelocityHome;

	@BeforeMethod
	public void before() {
		travelocityHome = new TravelocityHome(new FirefoxDriver());
	}

//	@Test
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

		PackageBooking packageBooking = travelocityHome.goHome().goPackageBooking().bookFlightPlusHotelAndCar();
		ValidatePage pageValidation = new ValidatePage(travelocityHome.getDriver());
	
		packageBooking.selectDepartureAirport(departureAirport);
		packageBooking.selectArrivalAirport(arrivalAirport);
		packageBooking.selectDepartureDate(departureDate);
		packageBooking.selectRetournDate(retournDate);

		packageBooking.childrenTraveling(children);
		packageBooking.adultsTraveling(adults);
		packageBooking.rooms(rooms);

		packageBooking.search();
		//Verify results page using at least 5 validations.
		assert (pageValidation.validElements(packageBooking.hotel().getElementsToValidateHotelSearchPage()));
		
		
		//hotel and room
		packageBooking.hotel().sort(HotelSort.BY_PRICE);

		//Sort by price. Verify the results were correctly sorted.
		assert (pageValidation.validElements(packageBooking.hotel().getElementsToValidateSort()));
		
		packageBooking.hotel().selectHotelBy(HotelStars.THREE);
		packageBooking.hotel().continueBooking();
		
		//In the new page, verify the hotel is the selected in the previous step using at least 3 validations.
		assert(pageValidation.validElements(packageBooking.hotel().getElementsToValidateHotelSelected()));
		
		packageBooking.hotel().selectFirstRoomAvailable();
		
		
		//flight
		packageBooking.flight().selectDepartureFlight(1);
		packageBooking.flight().selectRetournFlight(3);
		
		packageBooking.car().selectCarBy(CarType.PREMIUM);
		
		//Verify Trip Details using at least 5 validations.
		//TODO
		assert(pageValidation.validElements(packageBooking.getElementsToValidateBooking()));

	}

	@AfterMethod
	public void after() {
		this.travelocityHome.quit();
	}

}
