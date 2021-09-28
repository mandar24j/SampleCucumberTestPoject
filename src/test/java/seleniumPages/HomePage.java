package seleniumPages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.HelperUtil;
import utilities.PreSetup;

public class HomePage extends BasePage {
    @FindBy(how = How.XPATH, using = "(//a[text()='Vacations'])[1]")
    static WebElement vacationTab;
    @FindBy(how = How.XPATH, using = "//button[text()='Car']")
    static WebElement carButton;
    @FindBy(how = How.ID, using = "farefinder-package-origin-location-input")
    static WebElement originLocation;
    @FindBy(how = How.ID, using = "farefinder-package-destination-location-input")
    static WebElement destinationLocation;
    @FindBy(how = How.ID, using = "farefinder-package-startdate-input")
    static WebElement startDate;
    @FindBy(how = How.ID, using = "farefinder-package-pickuptime-input")
    static WebElement startTime;
    @FindBy(how = How.ID, using = "farefinder-package-enddate-input")
    static WebElement endDate;
    @FindBy(how = How.ID, using = "farefinder-package-dropofftime-input")
    static WebElement endTime;
    @FindBy(how = How.XPATH, using = "//div[@id='errorContainer']")
    static WebElement error;
    @FindBy(how = How.ID, using = "farefinder-package-search-button")
    static WebElement findDealButton;
    @FindBy(how = How.XPATH, using = "(//*[@class='uib-typeahead-match active'])[1]")
    static WebElement firstMatch;
    WebDriver driver;
    WebDriverWait wait;


    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void userIsOnHomePage(String appUrl) {
        if (!driver.getCurrentUrl().trim().equalsIgnoreCase(appUrl.trim())) {
            driver.get(appUrl);
        }
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException Ex) {

        }
    }

    public void navigateToVacationTab() {
        wait.until(ExpectedConditions.elementToBeClickable(vacationTab));
        vacationTab.click();
    }

    public void selectFlightHotelCar(Boolean car) {
        // select the car as per input
        if (car)
            if (!carButton.getAttribute("class").contains("active")) {
                carButton.click();
            }
        if (!car) {
            if (carButton.getAttribute("class").contains("active")) {
                carButton.click();
            }
        }

    }

    public void userEnterLocations(String origin, String destination) {
        originLocation.clear();
        originLocation.sendKeys(origin);
        wait.until(ExpectedConditions.elementToBeClickable(firstMatch));
        originLocation.sendKeys(Keys.TAB);
        destinationLocation.clear();
        destinationLocation.sendKeys(destination);
        wait.until(ExpectedConditions.elementToBeClickable(firstMatch));
        destinationLocation.sendKeys(Keys.TAB);

    }

    public void userEntersDepartureReturnDetails(int departDate, String departTime, int returnDate, String returnTime) {
        startDate.clear();
        startDate.sendKeys(HelperUtil.futureDate(departDate));
        startTime.sendKeys(departTime);
        endDate.clear();
        endDate.sendKeys(HelperUtil.futureDate(returnDate));
        endTime.sendKeys(returnTime);
    }

    public void verifyUserGetsAtleastOneResult() {
        findDealButton.click();
        wait.until(ExpectedConditions.visibilityOf(error));
        boolean flag = !(error.getText().toLowerCase().contains(PreSetup.prop.getProperty("errorText")));
        Assert.assertTrue("No surch results, expecting atleast one result", flag);
    }

}