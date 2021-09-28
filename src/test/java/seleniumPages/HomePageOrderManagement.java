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

public class HomePageOrderManagement extends BasePage {
    @FindBy(how = How.XPATH, using = "//*[text()='New Lead']")
    static WebElement newLeadButton;
    @FindBy(how = How.XPATH, using = "//*[text()='Save Draft']")
    static WebElement saveDraft;
    @FindBy(how = How.XPATH, using = "//*[text()='First Name']/../..//input")
    static WebElement firstNameInput;
    @FindBy(how = How.XPATH, using = "//*[text()='Last Name']/../..//input")
    static WebElement lastNameInput;
    @FindBy(how = How.XPATH, using = "//*[text()='Ultimate Parent Account']/../..//*[contains(@id,'_value')]")
    static WebElement ultimateParentAccountDropdown;
    @FindBy(how = How.XPATH, using = "  //*[text()='Global Market']/../..//*[contains(@id,'_value')]")
    static WebElement globalMarketDropdown;
    @FindBy(how = How.XPATH, using = "//*[text()='Account Name']/../..//input")
    static WebElement accountNameInput;
    @FindBy(how = How.XPATH, using = "//*[text()='Products']/../..//*[contains(@id,'_value')]")
    static WebElement productsDropdown;
    @FindBy(how = How.XPATH, using = "//*[text()='Sales Stage']/../..//*[contains(@id,'_value')]")
    static WebElement salesStageDropdown;
    @FindBy(how = How.XPATH, using = "//*[text()='Deal Type']/../..//*[contains(@id,'_value')]")
    static WebElement dealTypeDropdown;
    @FindBy(how = How.XPATH, using = "//*[text()='EST Contract Value']/../..//input")
    static WebElement estContractValueInput;
    @FindBy(how = How.XPATH, using = "//*[text()='Estimated Deal Close Date']/../..//input")
    static WebElement estimatedDealCloseDateInput;
    @FindBy(how = How.XPATH, using = "//*[text()='Estimated Deal Close Date']")
    static WebElement estimatedDealCloseDateText;
    @FindBy(how = How.XPATH, using = "//*[text()='Next']")
    static WebElement nextButton;
    @FindBy(how = How.XPATH, using = "//*[text()='Industry']/../..//*[contains(@id,'_value')]")
    static WebElement industryDropdown;
    @FindBy(how = How.XPATH, using = "//*[text()='Region']/../..//*[contains(@id,'_value')]")
    static WebElement regionDropdown;
    @FindBy(how = How.XPATH, using = "//*[text()='Customer Category']/../..//*[contains(@id,'_value')]")
    static WebElement customerCategoryDropdown;
    @FindBy(how = How.XPATH, using = "//*[text()='yes']")
    static WebElement yesButton;
    @FindBy(how = How.XPATH, using = "//*[text()='No']")
    static WebElement noButton;
    @FindBy(how = How.XPATH, using = "//*[text()='Submit']")
    static WebElement submitButton;
    @FindBy(how = How.XPATH, using = "(//*[text()='Refresh']/../..)[1]")
    static WebElement opportunitiesRefreshButton;
    String opportunitiesTable = "//*[text()='OPPORTUNITIES']";
    String opportunitiesFirstRow = "//*[text()='Pega Id']/../../../..//tbody//tr[1]";
    WebDriver driver;
    WebDriverWait wait;


    public HomePageOrderManagement(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void userIsOnHomePage(String appUrl) {
        dh.switchToFirstTab();
        driver.get(appUrl);
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException Ex) {

        }
    }


    public void createNewLead(String firstName, String lastName, String ultimateParentAccount, String globalMarket, String accountName, String products, String salesStage, String estContractValue, String dealType, int estimatedDealCloseDate, String industry, String region, String customCategory, String partnerInvolved) throws InterruptedException {
        clickNewLeadButton();
        enterLeadDetails(firstName, lastName, ultimateParentAccount, globalMarket, accountName, products, salesStage, estContractValue, dealType, estimatedDealCloseDate);
        enterOtherDetails(industry, region, customCategory, partnerInvolved);
        reviewAndSubmitLead();
        Thread.sleep(2000);
    }

    private void clickNewLeadButton() throws InterruptedException {
        dh.scrollTop();
        dh.click(newLeadButton);
    }

    private void enterLeadDetails(String firstName, String lastName, String ultimateParentAccount, String globalMarket, String accountName, String products, String salesStage, String estContractValue, String dealType, int estimatedDealCloseDate) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(saveDraft));
        dh.sendKeys(firstNameInput, firstName);
        dh.sendKeys(lastNameInput, lastName);
        selectDropdown(ultimateParentAccountDropdown, ultimateParentAccount);
        selectDropdown(globalMarketDropdown, globalMarket);
        dh.sendKeys(accountNameInput, accountName);
        selectDropdown(productsDropdown, products);
        selectDropdown(salesStageDropdown, salesStage);
        dh.sendKeys(estContractValueInput, estContractValue);
        selectDropdown(dealTypeDropdown, dealType);
        dh.sendKeys(estimatedDealCloseDateInput, HelperUtil.futureDate(estimatedDealCloseDate));
        dh.click(estimatedDealCloseDateText);
        dh.click(nextButton);
        dh.click(nextButton);
    }

    private void enterOtherDetails(String industry, String region, String customerCategory, String partnerInvolved) throws InterruptedException {
        selectDropdown(industryDropdown, industry);
        selectDropdown(regionDropdown, region);
        selectDropdown(customerCategoryDropdown, customerCategory);
        selectPartnerInvolved(partnerInvolved);
        dh.click(nextButton);
    }

    private void selectPartnerInvolved(String partnerInvolved) throws InterruptedException {
        if (partnerInvolved.equalsIgnoreCase("Yes")) {
            try {
                dh.click(yesButton);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                dh.click(noButton);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void reviewAndSubmitLead() throws InterruptedException {
        dh.click(submitButton);
    }

    private void selectDropdown(WebElement element, String option) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        dh.sendKeys(element, option);
        Thread.sleep(1500);
        dh.sendKeys(element, option);
        element.sendKeys(Keys.ENTER);
    }

    public String verifyNewLeadCreatedSuccessfully(String accountName, String industry, String dealType, String salesStage) throws InterruptedException {
        Thread.sleep(3000);
        driver.get(PreSetup.appUrl);
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(opportunitiesTable))));
        dh.moveToElement(driver.findElement(By.xpath(opportunitiesFirstRow)));
        Thread.sleep(3000);
        String firstRow = dh.getText(driver.findElement(By.xpath(opportunitiesFirstRow))).trim();
        System.out.println("************************************ Order Created : " + firstRow);

        //Verify First Row
        Assert.assertTrue("Expected Account name : " + accountName, firstRow.contains(accountName));
        Assert.assertTrue(firstRow.contains(industry));
        Assert.assertTrue(firstRow.contains(dealType));
        Assert.assertTrue(firstRow.contains(salesStage));
        return firstRow;
    }
}