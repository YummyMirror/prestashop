package base;

import helper.admin_side.CategoryHelper;
import helper.admin_side.LoginHelper;
import helper.admin_side.NavigationHelper;
import org.openqa.selenium.WebDriver;

public abstract class ApplicationBase {
    protected LoginHelper loginHelper;
    protected NavigationHelper navigationHelper;
    protected CategoryHelper categoryHelper;

    protected void initDelegate(WebDriver wd) {
        loginHelper = new LoginHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        categoryHelper = new CategoryHelper(wd);
    }

    //Delegate getters
    public LoginHelper login() {
        return loginHelper;
    }

    public NavigationHelper navigate() {
        return navigationHelper;
    }

    public CategoryHelper category() {
        return categoryHelper;
    }
}
