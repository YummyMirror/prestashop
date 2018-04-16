package step_definition.admin_side;

import base.CucumberBase;
import cucumber.api.java8.En;
import model.admin_side.UserData;
import static org.testng.Assert.*;

public class Login implements En {
    private CucumberBase base;

    public Login(CucumberBase base) {
        this.base = base;

        //Valid loginA
        Given("^I open the administration login page$", () -> {
            base.app.navigateA().openUrl(base.app.properties().getProperty("adminBaseUrl"));
        });
        When("^I enter both valid \'username\' and \'password\'$", () -> {
            base.app.loginA().fillLoginForm(new UserData().setLogin(base.app.properties().getProperty("adminLogin"))
                                                          .setPassword(base.app.properties().getProperty("adminPassword")));
        });
        And("^I click the login button$", () -> {
            base.app.loginA().clickLoginButton();
        });
        Then("^I verify that user is logged in$", () -> {
            assertTrue(base.app.loginA().isUserLoggedIn(), "User is not logged in!");
        });

        //Invalid loginA
        When("^I enter invalid \'(.*)\' and \'(.*)\'$", (String login, String password) -> {
            base.app.loginA().fillLoginForm(new UserData().setLogin(login)
                                                          .setPassword(password));
        });
        Then("^I verify that user is not logged in$", () -> {
            assertTrue(base.app.loginA().isLoginButtonPresent(), "User is logged in!");
        });
    }
}
