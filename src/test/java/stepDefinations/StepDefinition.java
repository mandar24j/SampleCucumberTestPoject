package stepDefinations;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import seleniumPages.*;
import utilities.PreSetup;

import java.util.Random;

import static utilities.PreSetup.*;

public class StepDefinition {
    static String leadId;
    public String subString;
    HomePage homePage;
    HomePageOrderManagement homePageOrderManagement;
    LoginPage loginPage;
    PegaPage pegaPage;
    CoursesPage coursesPage;
    Scenario scenario;
    String leadDetails;
    String leadDetailsInPega;

    @Before
    public void setupMethod() throws InterruptedException {
        new PreSetup();
        launchApp();
    }

    @Given("^user is on home page$")
    public void user_is_on_home_page() throws Throwable {
        homePageOrderManagement = new HomePageOrderManagement(PreSetup.driver, PreSetup.wait);
        homePageOrderManagement.userIsOnHomePage(PreSetup.appUrl);
    }

    @When("^user creates a new lead \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void user_creates_a_new_lead(String firstName, String lastName, String ultimateParentAccount, String globalMarket, String accountName, String products, String salesStage, String estContractValue, String dealType, int estimatedDealCloseDate, String industry, String region, String customCategory, String partnerInvolved) throws Throwable {
        Random random = new Random();
        subString = String.format("%04d", random.nextInt(10000));
        leadId = homePageOrderManagement.createNewLead(firstName, lastName + subString, ultimateParentAccount, globalMarket, accountName, products, salesStage, estContractValue, dealType, estimatedDealCloseDate, industry, region, customCategory, partnerInvolved);

    }

    @When("^user verifies the new lead is created successfully \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void user_verifies_the_new_lead_is_created_successfully(String accountName, String industry, String dealType, String salesStage) throws Throwable {
        leadDetails = homePageOrderManagement.verifyNewLeadCreatedSuccessfully(leadId, accountName, industry, dealType, salesStage);

    }

    @When("^user login into Pega Application \"([^\"]*)\",\"([^\"]*)\"$")
    public void userLoginIntoPegaApplication(String userName, String password) throws Throwable {
        pegaPage = new PegaPage(PreSetup.driver, PreSetup.wait);
        pegaPage.userLoginPega(userName, password);
    }

    @Then("^user verifies new lead \"([^\"]*)\", \"([^\"]*)\"is populated$")
    public void user_verifies_new_lead_is_populated(String firstName, String lastName) throws Throwable {
        leadDetailsInPega = pegaPage.userVerifiesNewLeadIsPopulatedInPega(firstName, lastName + subString);
    }


    @When("^user creates a new course \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\"$")
    public void user_creates_a_new_course(String courseName, String status, String priority, String trainer) throws Throwable {
        Random random = new Random();
        subString = String.format("%04d", random.nextInt(10000));
        coursesPage = new CoursesPage(PreSetup.driver, PreSetup.wait);
        coursesPage.createNewCourse(courseName + subString, status, priority, trainer);
    }

    @When("^user verifies the new course is created successfully \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\"$")
    public void user_verifies_the_new_course_is_created_successfully(String courseName, String status, String priority) throws Throwable {
        coursesPage.userVerifiesTheNewCourseIsCreatedSuccessfully(courseName + subString, status, priority);

    }

    @Then("^user deletes the course \"([^\"]*)\"$")
    public void user_deletes_the_course(String courseName) throws Throwable {
        coursesPage.userDeletesTheCourse(courseName + subString);
    }

    @Then("^user verifies course \"([^\"]*)\" is deleted successfully$")
    public void user_verifies_course_is_deleted_successfully(String courseName) throws Throwable {
        coursesPage.userVerifiesTheCourseIsDeletedSuccessfully(courseName + subString);
    }

    @After
    public void tearDown(Scenario scenario) {
        scenario.write("Lead Created in Appian: " + leadDetails);
        //scenario.write("Lead Details populated in Pega " + leadDetailsInPega);
        if (scenario.isFailed()) {
            // Take a screenshot...
            final byte[] screenshot = ((TakesScreenshot) PreSetup.driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
        }
        //logout();
        driver.quit();
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
        homePage.verifyUserGetsAtleastOneResult();
    }


}
