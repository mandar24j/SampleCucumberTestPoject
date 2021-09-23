package seleniumPages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.PreSetup;

public class CoursesPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//*[text()='New Course']")
    static WebElement newCourseButton;

    @FindBy(how = How.XPATH, using = "//*[text()='Course Name']/../..//input")
    static WebElement courseNameInput;

    @FindBy(how = How.XPATH, using = "//*[text()='Status']/../..//*[contains(@id,'_value')]")
    static WebElement selectStatus;

    @FindBy(how = How.XPATH, using = "//*[text()='Priority']/../..//*[contains(@id,'_value')]")
    static WebElement selectPriority;

    @FindBy(how = How.XPATH, using = "//*[text()='Trainer']/../..//input")
    static WebElement trainerInput;

    @FindBy(how = How.XPATH, using = "//*[text()='Submit Course']")
    static WebElement submitButton;

    @FindBy(how = How.XPATH, using = "//*[contains(@id,'suggestionsHint')]")
    static WebElement suggestionHints;

    @FindBy(how = How.XPATH, using = "//*[text()='Delete Course']")
    static WebElement deleteCourseButton;

    @FindBy(how = How.XPATH, using = "//*[text()='Delete']")
    static WebElement confirmDeleteCourseButton;

    @FindBy(how = How.XPATH, using = "//*[text()='Yes']")
    static WebElement confirmDeleteCourseYesButton;


    WebDriver driver;
    WebDriverWait wait;

    public CoursesPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void createNewCourse(String courseName, String status, String priority, String trainer) throws InterruptedException {
        dh.click(newCourseButton);
        System.out.println("************************* Creating Course : " + courseName);
        dh.sendKeys(courseNameInput, courseName);
        selectDropdown(selectStatus, status);
        selectDropdown(selectPriority, priority);
        dh.sendKeys(trainerInput, trainer);
        wait.until(ExpectedConditions.elementToBeClickable(suggestionHints));
        trainerInput.sendKeys(Keys.ENTER);
        submitButton.click();
    }

    private void selectDropdown(WebElement element, String option) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        dh.sendKeys(element, option);
        Thread.sleep(1000);
        dh.sendKeys(element, option);
        element.sendKeys(Keys.ENTER);
    }


    public void userVerifiesTheNewCourseIsCreatedSuccessfully(String courseName, String status, String priority) throws InterruptedException {
        Thread.sleep(5000);
        String courseTableFirstRow = "//*[text()='Course Name']/ancestor::table//tbody//tr[1]";
        String priory = "//*[text()='Course Name']/ancestor::table//tbody//tr[1]//td[2]//div//img";
        String firstRow = dh.getText(driver.findElement(By.xpath(courseTableFirstRow)));
        Assert.assertTrue(firstRow.contains(courseName));
        Assert.assertTrue(firstRow.contains(status));
        String actualPriority = driver.findElement(By.xpath(priory)).getAttribute("alt");
        Assert.assertEquals(priority, actualPriority);
    }

    public void userDeletesTheCourse(String courseName) throws InterruptedException {
        String courseLink = "//*[text()='" + courseName + "']";
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(courseLink))));
        driver.findElement(By.xpath(courseLink)).click();
        Thread.sleep(2000);
        dh.click(deleteCourseButton);
        Thread.sleep(500);
        dh.click(confirmDeleteCourseButton);
        Thread.sleep(500);
        dh.click(confirmDeleteCourseYesButton);
        Thread.sleep(2000);
    }

    public void userVerifiesTheCourseIsDeletedSuccessfully(String courseName) throws InterruptedException {
        driver.get(PreSetup.appUrl);
        Thread.sleep(5000);
        String courseTableFirstRow = "//*[text()='Course Name']/ancestor::table//tbody//tr[1]";
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(courseTableFirstRow))));
        String firstRow = dh.getText(driver.findElement(By.xpath(courseTableFirstRow)));
        Assert.assertFalse("Actual : " + firstRow + "; Expected : " + courseName, firstRow.contains(courseName));
    }
}
