package helper.admin_side;

import base.HelperBase;
import model.admin_side.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class LoginHelperA extends HelperBase {
    //Constructor
    public LoginHelperA(WebDriver wd) {
        super(wd);
    }

    //Main methods
    public void fillLoginForm(UserData loginData) {
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

    public void loginAs(UserData loginData) {
        fillLoginForm(loginData);
        clickLoginButton();
    }
}
