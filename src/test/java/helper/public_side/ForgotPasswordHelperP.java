package helper.public_side;

import base.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordHelperP extends HelperBase {
    //Constructor
    public ForgotPasswordHelperP(WebDriver wd) {
        super(wd);
    }

    public void enterEmailToSendPassword(String email) {
        input(By.xpath("//input[@id = 'email']"), email);
    }

    public void clickSendEmailButton() {
        click(By.xpath("//button[contains(@class, 'down')]"));
    }
}
