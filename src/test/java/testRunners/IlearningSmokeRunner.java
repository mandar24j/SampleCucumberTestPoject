package testRunners;

import cucumber.api.CucumberOptions;
import utilities.PreSetup;

//@RunWith(Cucumber.class)
@CucumberOptions
        (
                features = {
                        "classpath:Features/smokeMaanticIlearning.feature"
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
public class IlearningSmokeRunner extends PreSetup {

}
