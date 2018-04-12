package base;

import helper.LoginHelper;
import helper.NavigateHelper;
import org.openqa.selenium.WebDriver;

public abstract class ApplicationBase {
    protected LoginHelper loginHelper;
    protected NavigateHelper navigateHelper;

    protected void initDelegate(WebDriver wd) {
        loginHelper = new LoginHelper(wd);
        navigateHelper = new NavigateHelper(wd);
    }

    //Delegate getters
    public LoginHelper login() {
        return loginHelper;
    }

    public NavigateHelper navigate() {
        return navigateHelper;
    }
}
