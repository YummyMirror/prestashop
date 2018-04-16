package runner.admin_side;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/test/resources/cucumber/admin_side"},
                 glue = {"base", "step_definition"},
                 strict = true,
                 format = {"json:target/cucumber.json", "html:target/html-cucumber-report"})
public class AllRunner extends AbstractTestNGCucumberTests {}
