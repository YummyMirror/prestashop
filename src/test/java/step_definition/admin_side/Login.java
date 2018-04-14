package step_definition.admin_side;

import base.CucumberBase;
import cucumber.api.java8.En;
import model.admin_side.UserData;
import static org.testng.Assert.*;

public class Login implements En {
    private CucumberBase base;

    public Login(CucumberBase base) {
        this.base = base;

        //Valid login
        Given("^I open the administration login page$", () -> {
            base.app.navigate().openUrl(base.app.properties().getProperty("baseUrl"));
        });
        When("^I enter both valid \'username\' and \'password\'$", () -> {
            base.app.login().fillLoginForm(new UserData().setLogin(base.app.properties().getProperty("login"))
                                                         .setPassword(base.app.properties().getProperty("password")));
        });
        And("^I click the login page$", () -> {
            base.app.login().clickLoginButton();
        });
        Then("^I verify that user is logged in$", () -> {
            assertTrue(base.app.login().isUserLoggedIn(), "User is not logged in!");
        });

        //Invalid login
        When("^I enter invalid \'(.*)\' and \'(.*)\'$", (String login, String password) -> {
            base.app.login().fillLoginForm(new UserData().setLogin(login).setPassword(password));
        });
        Then("^I verify that user is not logged in$", () -> {
            assertTrue(base.app.login().isLoginButtonPresent(), "User is logged in!");
        });
    }
}
