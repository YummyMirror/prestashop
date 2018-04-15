package base;

import application.Application;
import cucumber.api.Scenario;
import cucumber.api.java8.En;

public class CucumberBase implements En {
    public Application app = new Application();

    public CucumberBase() {
        Before(() -> {
            app.init();
        });

        After((Scenario scenario) -> {
            app.takeScreenshot(scenario);
            app.stop();
        });
    }
}
