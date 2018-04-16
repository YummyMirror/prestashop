package helper.public_side;

import base.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class NavigationHelperP extends HelperBase {
    //Constructor
    public NavigationHelperP(WebDriver wd) {
        super(wd);
    }

    public void openLoginPage() {
        click(By.xpath("//div[@class = 'user-info']/a[contains(@href, 'my-account')]"));
        wait.until(urlContains("login"));
    }

    public void openForgotPassPage() {
        click(By.xpath("//a[contains(@href, 'password-recovery')]"));
        wait.until(urlContains("password-recovery"));
    }
}
