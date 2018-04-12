import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/test/resources/cucumber/login.feature"},
                 glue = {"base", "step_definition"})
public class LoginRunner extends AbstractTestNGCucumberTests {}
