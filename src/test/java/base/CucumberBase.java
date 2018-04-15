package base;

import application.Application;
import cucumber.api.java8.En;

public class CucumberBase implements En {
    public Application app = new Application();

    public CucumberBase() {
        Before(() -> {
            app.init();
        });

        After(() -> {
            app.stop();
        });
    }
}
