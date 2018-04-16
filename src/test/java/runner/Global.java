package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/test/resources/cucumber"},
                 glue = {"base", "step_definition"},
                 strict = true,
                 format = {"json:target/all.json", "html:target/all-cucumber-html"})
public class Global extends AbstractTestNGCucumberTests {}
