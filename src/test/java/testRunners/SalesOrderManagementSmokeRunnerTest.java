package testRunners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.PreSetup;

import static utilities.PreSetup.launchApp;

//@RunWith(Cucumber.class)
@CucumberOptions
        (
                features = {
                        "classpath:Features/salesOrderManagementSmoke.feature"
                },
                glue = "stepDefinations",
                monochrome = true,
                strict = true,
                plugin = {
                        "pretty",
                        "html:target/cucumber-html",
                        "json:target/cucumber-json/cucumber.json",
                        "rerun:target/rerun.txt",
                        "junit:target/cucumber-reports/Cucumber.xml"
                }
        )

@Test
public class SalesOrderManagementSmokeRunnerTest extends AbstractTestNGCucumberTests {
}
