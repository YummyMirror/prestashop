package base;

import application.Application;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CucumberBase {
    public Application app = new Application();

    @Before
    public void setUp() {
        app.init();
    }

    @After
    public void tearDown() {
        app.stop();
    }
}
