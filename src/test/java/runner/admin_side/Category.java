package runner.admin_side;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/test/resources/cucumber/admin_side/categoryA.feature"},
                 glue = {"base", "step_definition"},
                 strict = true)
public class Category extends AbstractTestNGCucumberTests {}
