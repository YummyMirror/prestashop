package step_definition.public_side;

import base.CucumberBase;
import cucumber.api.java8.En;
import model.public_side.MailMessage;
import java.util.List;
import static org.testng.Assert.*;

public class SendForgotPass implements En {
    private CucumberBase base;
    private List<MailMessage> messages;
    private MailMessage message;

    public SendForgotPass(CucumberBase base) {
        this.base = base;

        Given("^I open the \'Forgot password\' page$", () -> {
            base.app.navigateP().openForgotPassPage();
        });
        When("^I enter the \'email\'$", () -> {
            base.app.forgotPassP().enterEmailToSendPassword(base.app.properties().getProperty("publicLogin"));
        });
        And("^I click the \'Send email\' button$", () -> {
            base.app.forgotPassP().clickSendEmailButton();
        });
        Then("^I check that one email is sent$", () -> {
            messages = base.app.wiser().getMessages();
            assertEquals(messages.size(), 1);
        });
        And("^I check that sender is correct$", () -> {
            message = messages.stream().findFirst().get();
            assertEquals(message.getFrom(), base.app.properties().getProperty("adminLogin"));
        });
        And("^I check that receiver is correct$", () -> {
            assertEquals(message.getTo(), base.app.properties().getProperty("publicLogin"));
        });
    }
}
