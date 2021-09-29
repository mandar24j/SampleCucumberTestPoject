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

public class PegaPage extends BasePage {
    @FindBy(how = How.ID, using = "txtUserID")
    static WebElement userName;
    @FindBy(how = How.ID, using = "txtPassword")
    static WebElement password;
    @FindBy(how = How.ID, using = "sub")
    static WebElement loginButton;
    @FindBy(how = How.XPATH, using = "//*[@data-test-id='2019032707440904433327']")
    static WebElement safsButton;
    @FindBy(how = How.XPATH, using = "//*[text()='Home']")
    static WebElement homeMenu;
    @FindBy(how = How.XPATH, using = "//*[text()='Leads']")
    static WebElement leadsMenu;
    @FindBy(how = How.XPATH, using = "//*[@data-test-id='201803191319580476561']")
    static WebElement leadDetails;
    @FindBy(how = How.XPATH, using = "//*[text()='Clear filters']")
    static WebElement clearFilter;
    @FindBy(how = How.XPATH, using = "//*[@data-test-id='20141222052109006713340']")
    static WebElement filterLeadsInput;
    @FindBy(how = How.XPATH, using = "//*[@data-test-id='20190911130636037429876']")
    static WebElement filterLeadsEnter;

    String leadsFirstRowName = "(//*[@data-test-id='201412160519150793380419'])[1]";

    WebDriver driver;
    WebDriverWait wait;

    public PegaPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void userLoginPega(String loginUser, String loginPassword) {
        dh.openNewTabAndSwitchToIt();
        driver.get(PreSetup.appUrlPega);
        try {
            userName.sendKeys(loginUser);
            password.sendKeys(loginPassword);
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            loginButton.click();
        } catch (Exception ex) {
        }

    }

    public String userVerifiesNewLeadIsPopulatedInPega(String firstName, String lastName) throws InterruptedException {
        openLeadsPageInPega();
        openLeadDetails(lastName);
        String leadDetails = verifyLead(firstName, lastName);
        driver.close();
        return leadDetails;
    }


    private String verifyLead(String firstName, String lastName) throws InterruptedException {
        Thread.sleep(3000);
        String actualLeadInPega = dh.getText(leadDetails);
        Assert.assertTrue(actualLeadInPega.contains(firstName));
        Assert.assertTrue(actualLeadInPega.contains(lastName));
        return actualLeadInPega;
    }

    private void openLeadsPageInPega() throws InterruptedException {
        dh.click(safsButton);
        dh.scrollBy(0, -50);
        Thread.sleep(2000);
        dh.click(leadsMenu);
    }

    private void openLeadDetails(String lastName) throws InterruptedException {
        Thread.sleep(2000);
        dh.click(clearFilter);
        Thread.sleep(1000);
        dh.sendKeys(filterLeadsInput, lastName + Keys.ENTER);
        Thread.sleep(2000);
        dh.click(filterLeadsEnter);
        dh.waitForElement(leadsFirstRowName);
        dh.click(driver.findElement(By.xpath(leadsFirstRowName)));
        try {
            Thread.sleep(2000);
            dh.click(driver.findElement(By.xpath(leadsFirstRowName)));
        } catch (Exception ex) {
        }

    }
}
