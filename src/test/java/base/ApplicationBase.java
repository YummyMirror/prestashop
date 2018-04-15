package base;

import helper.admin_side.CategoryHelper;
import helper.admin_side.LoginHelper;
import helper.admin_side.NavigationHelper;
import org.openqa.selenium.WebDriver;

public abstract class ApplicationBase {
    protected WebDriver wd;
    private LoginHelper loginHelper;
    private NavigationHelper navigationHelper;
    private CategoryHelper categoryHelper;

    //Delegate getters
    public LoginHelper login() {
        if (loginHelper == null)
            loginHelper = new LoginHelper(wd);
        return loginHelper;
    }

    public NavigationHelper navigate() {
        if (navigationHelper == null)
            navigationHelper = new NavigationHelper(wd);
        return navigationHelper;
    }

    public CategoryHelper category() {
        if (categoryHelper == null)
            categoryHelper = new CategoryHelper(wd);
        return categoryHelper;
    }
}
