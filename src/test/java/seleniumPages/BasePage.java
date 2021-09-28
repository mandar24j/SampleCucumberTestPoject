package seleniumPages;

import utilities.DriverHelper;
import utilities.PreSetup;


public class BasePage {

    DriverHelper dh = new DriverHelper(PreSetup.driver, PreSetup.wait);
}
