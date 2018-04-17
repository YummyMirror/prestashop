package step_definition.public_side;

import base.CucumberBase;
import cucumber.api.java8.En;
import model.public_side.UserData;
import static org.testng.Assert.*;

public class Login implements En {
    private CucumberBase base;

    public Login(CucumberBase base) {
        this.base = base;

        Given("^I open the public login page$", () -> {});
        When("^I enter valid \'username\' and \'password\'$", () -> {
            base.app.loginP().fillLoginForm(new UserData().setLogin(base.app.properties().getProperty("publicLogin"))
                                                          .setPassword(base.app.properties().getProperty("publicPassword")));
        });
        And("^I click login button$", () -> {
            base.app.loginP().clickSignInButton();
        });
        Then("^I verify that user is logged in to profile$", () -> {
            assertTrue(base.app.loginP().isUserLoggedIn());
        });
    }
}
