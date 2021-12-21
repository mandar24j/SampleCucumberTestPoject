package seleniumPages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.HelperUtil;

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
    @FindBy(how = How.XPATH, using = "//*[contains(text(),'New Lead')]")
    static WebElement newLeadId;
    @FindBy(how = How.XPATH, using = "//*[@placeholder='Search TLUS_OPPORTUNITIES']")
    static WebElement searchOpportunity;
    @FindBy(how = How.XPATH, using = "//*[text()='Search']")
    static WebElement searchButton;

    String opportunitiesTable = "//*[text()='OPPORTUNITIES']";
    String opportunitiesFirstRow = "//*[text()='Pega Id']/../../../..//tbody//tr[1]";
    String pegaIdColumn = "//*[text()='Pega Id']";
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


    public String createNewLead(String firstName, String lastName, String ultimateParentAccount, String globalMarket, String accountName, String products, String salesStage, String estContractValue, String dealType, int estimatedDealCloseDate, String industry, String region, String customCategory, String partnerInvolved) throws InterruptedException {
        clickNewLeadButton();
        dh.switchToSecondTab();
        String leadId = "";
        leadId = dh.getText(newLeadId).trim().split(" ")[2];
        enterLeadDetails(firstName, lastName, ultimateParentAccount, globalMarket, accountName, products, salesStage, estContractValue, dealType, estimatedDealCloseDate);
        enterOtherDetails(industry, region, customCategory, partnerInvolved);
        reviewAndSubmitLead();
        Thread.sleep(2000);
        System.out.println("New Lead Created with ID>> " + leadId);
        return leadId;
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
        dh.sendKeys(estimatedDealCloseDateInput, HelperUtil.futureDate(estimatedDealCloseDate) + "\t");
        Thread.sleep(500);
        //dh.click(estimatedDealCloseDateText);
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

    public String verifyNewLeadCreatedSuccessfully(String leadId, String accountName, String industry, String dealType, String salesStage) throws InterruptedException {
        String firstRow = "";
        try {
            Thread.sleep(1000);
            //driver.get(PreSetup.appUrl);
            //Thread.sleep(8000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pegaIdColumn)));
            dh.sendKeys(searchOpportunity, leadId);
            dh.click(searchButton);
            Thread.sleep(1000);
            dh.click(searchButton);
            Thread.sleep(3000);
            //wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(opportunitiesTable))));
            //dh.moveToElement(driver.findElement(By.xpath(opportunitiesFirstRow)));
            dh.scrollBy(0, 1000);
            Thread.sleep(3000);
            firstRow = dh.getText(driver.findElement(By.xpath(opportunitiesFirstRow))).trim();
            //System.out.println("************************************ Order Created : " + firstRow);

            //Verify First Row
            Assert.assertTrue("Expected Lead ID : " + leadId, firstRow.contains(leadId));
            Assert.assertTrue("Expected Account name : " + accountName, firstRow.contains(accountName));
            Assert.assertTrue("Expected Industry : " + industry, firstRow.contains(accountName));
            Assert.assertTrue("Expected Deal Type : " + dealType, firstRow.contains(accountName));
            Assert.assertTrue("Expected Sales Stage : " + salesStage, firstRow.contains(accountName));
        } finally {
            driver.close();
            dh.switchToFirstTab();
            return firstRow;
        }

    }
}