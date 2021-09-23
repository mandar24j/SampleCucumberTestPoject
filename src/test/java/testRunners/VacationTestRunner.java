package testRunners;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import utilities.PreSetup;
import cucumber.api.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions
(
   features = {
		   "classpath:Features/VacationScenarios.feature"
		   	  },
   	glue = "stepDefinations",
   monochrome = true,
   strict = true,
   plugin = { 
		   		"pretty", 
		   		"html:target/cucumber-html", 
		   		"json:target/cucumber-json/cucumber.json", 
		   		"rerun:target/rerun.txt"
		   	}
)
public class VacationTestRunner extends PreSetup{

}
