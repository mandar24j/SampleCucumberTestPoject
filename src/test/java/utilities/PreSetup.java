package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumPages.LoginPage;
import seleniumPages.LogoutPage;

import java.io.FileInputStream;
import java.util.Properties;

public class PreSetup {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Properties prop;
    public static String loginUser;
    public static String loginPassword;
    public static String appUrl;
    public static String appUrlPega;
    public static LoginPage loginPage;
    public static LogoutPage logoutPage;

    static {
        try {
            prop = new Properties();
            prop.load(new FileInputStream("src//test//resources//configFiles//config.properties"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public static void launchApp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 30);
        appUrl = prop.getProperty("appUrl");
        appUrlPega = prop.getProperty("appUrlPega");
        driver.get(appUrl);
        loginUser = prop.getProperty("loginUser");
        loginPassword = prop.getProperty("userPassword");
        loginPage = new LoginPage(PreSetup.driver, PreSetup.wait);
        loginPage.userLogin(PreSetup.loginUser, PreSetup.loginPassword);
    }

    //@AfterClass
    public static void logout() {
        logoutPage = new LogoutPage(PreSetup.driver, PreSetup.wait);
        logoutPage.logout();
    }
}
