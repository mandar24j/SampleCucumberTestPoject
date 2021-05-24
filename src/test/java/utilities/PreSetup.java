package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class PreSetup {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Properties prop;

    static {
        try {
            prop = new Properties();
            prop.load(new FileInputStream("src//test//resources//configFiles//config.properties"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public static void launchApp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 30);
    }
}
