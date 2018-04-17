package base;

import application.Application;
import cucumber.api.Scenario;
import cucumber.api.java8.En;
import model.admin_side.UserData;

public class CucumberBase implements En {
    public Application app = new Application();

    public CucumberBase() {
        //Global
        Before(0, () -> {
            app.init();
        });
        After(0, (Scenario scenario) -> {
            app.takeScreenshot(scenario);
            app.stop();
        });

        //@Public
        Before(new String[] {"@Public"}, 10000, 1, () -> {
            app.navigateP().openUrl(app.properties().getProperty("publicBaseUrl"));
            app.navigateP().openLoginPage();
        });

        //@Wiser
        Before(new String[] {"@Wiser"}, 10000, 2, () -> {
            app.wiser().start();
        });
        After(new String[] {"@Wiser"}, 10000, 2, () -> {
            app.wiser().stop();
        });

        //@Admin
        Before(new String[] {"@Admin"}, 10000, 1, () -> {
            app.navigateA().openUrl(app.properties().getProperty("adminBaseUrl"));
        });

        //@Category
        Before(new String[] {"@Category"},1000000, 2,  () -> {
            app.loginA().loginAs(new UserData().setLogin(app.properties().getProperty("adminLogin"))
                                               .setPassword(app.properties().getProperty("adminPassword")));
            app.navigateA().openMenuItem("Catalog", "Categories");
        });
    }
}
