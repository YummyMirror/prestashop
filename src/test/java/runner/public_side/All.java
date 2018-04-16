package runner.public_side;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/test/resources/cucumber/public_side"},
                 glue = {"base", "step_definition"},
                 strict = true)
public class All extends AbstractTestNGCucumberTests {}
