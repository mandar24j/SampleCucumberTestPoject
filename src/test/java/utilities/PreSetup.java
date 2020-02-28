package utilities;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		System.setProperty("webdriver.chrome.driver", prop.getProperty("chromeExe"));
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(prop.getProperty("appUrl"));
		wait = new WebDriverWait(driver, 30);
	}
}
