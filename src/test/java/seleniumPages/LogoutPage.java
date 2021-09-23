package seleniumPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'UserProfileLayout')]")
    static WebElement userProfile;

    @FindBy(how = How.XPATH, using = "(//button[@type='button'])[4]")
    static WebElement signoutButton;


    WebDriver driver;
    WebDriverWait wait;

    public LogoutPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(userProfile));
        userProfile.click();
        wait.until(ExpectedConditions.elementToBeClickable(signoutButton));
        signoutButton.click();
    }
}
