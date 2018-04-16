package runner.admin_side;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/test/resources/cucumber/admin_side/login.feature"},
                 glue = {"base", "step_definition"},
                 strict = true)
public class LoginRunner extends AbstractTestNGCucumberTests {}
