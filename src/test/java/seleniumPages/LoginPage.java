package seleniumPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//*[@value='I Agree']")
    static WebElement acceptTC;

    @FindBy(how = How.ID, using = "un")
    static WebElement userName;
    @FindBy(how = How.ID, using = "pw")
    static WebElement password;

    @FindBy(how = How.XPATH, using = "//*[@value='Sign In']")
    static WebElement submitButton;


    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void userLogin(String loginUser, String loginPassword) {
        dh.click(acceptTC);
        wait.until(ExpectedConditions.elementToBeClickable(userName));
        userName.sendKeys(loginUser);
        password.sendKeys(loginPassword);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }
}
