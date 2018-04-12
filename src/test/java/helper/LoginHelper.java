package helper;

import base.HelperBase;
import model.LoginData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class LoginHelper extends HelperBase {
    //Constructor
    public LoginHelper(WebDriver wd) {
        super(wd);
    }

    public void fillLoginForm(LoginData loginData) {
        input(By.xpath("//input[@id = 'email']"), loginData.getLogin());
        input(By.xpath("//input[@id = 'passwd']"), loginData.getPassword());
    }

    public void clickLoginButton() {
        click(By.xpath("//button[@id = 'submit_login']"));
    }

    public Boolean isUserLoggedIn() {
        return wait.until(visibilityOfElementLocated(By.xpath("//span[@class = 'employee_avatar_small']"))).isDisplayed();
    }

    public Boolean isLoginButtonPresent() {
        return isElementPresent(By.xpath("//button[@id = 'submit_login']"));
    }
}
