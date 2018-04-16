package helper.public_side;

import base.HelperBase;
import model.public_side.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class LoginHelperP extends HelperBase {
    //Constructor
    public LoginHelperP(WebDriver wd) {
        super(wd);
    }

    public void fillLoginForm(UserData userData) {
        input(By.xpath("//form[@id = 'login-form']//input[@name = 'email']"), userData.getLogin());
        input(By.xpath("//form[@id = 'login-form']//input[@name = 'password']"), userData.getPassword());
    }

    public void clickSignInButton() {
        click(By.xpath("//button[@id = 'submit-login']"));
        wait.until(visibilityOfElementLocated(By.xpath("//div[@class ='user-info']/a[contains(@href, 'logout')]")));
    }

    public Boolean isUserLoggedIn() {
        return isElementPresent(By.xpath("//div[@class ='user-info']/a[contains(@href, 'logout')]"));
    }
}
