package stepDefinations;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import seleniumPages.HomePage;
import utilities.PreSetup;

public class StepDefination {
	HomePage homePage;

	@Given("^user is on home page$")
	public void user_is_on_home_page() throws Throwable {
		homePage = new HomePage(PreSetup.driver, PreSetup.wait);
		homePage.userIsOnHomePage();
	}

	@When("^user navigate to Vacation tab$")
	public void user_navigate_to_Vacation_tab() throws Throwable {
		homePage.navigateToVacationTab();
	}

	@When("^user selects Flight, Hotel and \"([^\"]*)\"$")
	public void user_selects_Flight_Hotel_and(Boolean car) throws Throwable {
		homePage.selectFlightHotelCar(car);
	}

	@When("^user enters \"([^\"]*)\", \"([^\"]*)\" locations$")
	public void user_enters_locations(String origin, String destination) throws Throwable {
		homePage.userEnterLocations(origin, destination);
	}

	@When("^user enters \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\"$")
	public void user_enters(int departDate, String departTime, int returnDate, String returnTime) throws Throwable {
		homePage.userEntersDepartureReturnDetails(departDate, departTime, returnDate, returnTime);
	}

	@Then("^user gets atleast one search result$")
	public void user_gets_atleast_one_search_result() throws Throwable {
		homePage.verifyuserGetsAtleasrOneResult();
	}

}
