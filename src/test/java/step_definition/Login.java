package step_definition;

import base.CucumberBase;
import cucumber.api.java8.En;
import model.LoginData;
import static org.testng.Assert.*;

public class Login implements En {
    private CucumberBase base;

    public Login(CucumberBase base) {
        this.base = base;

        Given("^I open the administration login page$", () -> {
            base.app.navigate().openUrl("http://localhost/prestashop/admin7415x81bm/index.php");
        });
        When("^I enter both valid \'(.+)\' and \'(.+)\'$", (String login, String password) -> {
            base.app.login().fillLoginForm(new LoginData().setLogin(login).setPassword(password));
        });
        And("^I click the login page$", () -> {
            base.app.login().clickLoginButton();
        });
        Then("^I verify that user is logged in$", () -> {
            assertTrue(base.app.login().isUserLoggedIn());
        });
    }
}
