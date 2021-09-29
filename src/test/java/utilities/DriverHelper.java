package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class DriverHelper {

    static WebDriver driver;
    static WebDriverWait wait;
    Actions actions;
    JavascriptExecutor js = (JavascriptExecutor) driver;

    public DriverHelper(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public static void click(WebElement element) throws InterruptedException {
        int attempts = 3;
        while (attempts > 0) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element));
                wait.until(ExpectedConditions.visibilityOf(element));
                element.click();
                break;
            } catch (Exception ex) {
                attempts--;
                Thread.sleep(500);
            }
        }

    }

    public void sendKeys(WebElement element, String text) throws InterruptedException {
        int attempts = 3;
        while (attempts > 0) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element));
                wait.until(ExpectedConditions.visibilityOf(element));
                element.sendKeys(text);
                break;
            } catch (Exception ex) {
                attempts--;
                Thread.sleep(500);
            }
        }
    }

    public String getText(WebElement element) throws InterruptedException {
        int attempts = 3;
        while (attempts > 0) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element));
                wait.until(ExpectedConditions.visibilityOf(element));
                break;
            } catch (Exception ex) {
                attempts--;
                Thread.sleep(500);
            }
        }
        return element.getText();
    }

    public void moveToElement(WebElement element) throws InterruptedException {
        int attempts = 3;
        while (attempts > 0) {
            actions = new Actions(driver);
            try {
                actions.moveToElement(element).build().perform();
                break;
            } catch (Exception ex) {
                Thread.sleep(500);
                attempts--;
            }
        }

    }

    public void scrollTop() {
        js.executeScript("window.scrollTo(0,0)");
    }

    public void scrollBy(int x, int y) {
        js.executeScript("window.scrollTo(" + x + "," + y + ")");
    }

    public void openNewTabAndSwitchToIt() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }


    public void mouseHover(WebElement element) {
        actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void switchToFirstTab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    public void jsClick(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    public void waitForElement(String xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(xpath), 0));
    }

    public void waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));

    }


}
