package base;

import application.Application;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import java.io.IOException;

public class CucumberBase {
    public Application app = new Application();

    @Before
    public void setUp() throws IOException {
        app.init();
    }

    @After
    public void tearDown() {
        app.stop();
    }
}
