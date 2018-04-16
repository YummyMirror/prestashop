package base;

import helper.service.WiserHelper;
import helper.admin_side.CategoryHelperA;
import helper.admin_side.LoginHelperA;
import helper.admin_side.NavigationHelperA;
import helper.public_side.ForgotPasswordHelperP;
import helper.public_side.LoginHelperP;
import helper.public_side.NavigationHelperP;
import org.openqa.selenium.WebDriver;

public abstract class ApplicationBase {
    //Admin_side
    protected WebDriver wd;
    private LoginHelperA loginHelperA;
    private NavigationHelperA navigationHelperA;
    private CategoryHelperA categoryHelperA;
    //Public_side
    private LoginHelperP loginHelperP;
    private NavigationHelperP navigationHelperP;
    private ForgotPasswordHelperP forgotPasswordHelperP;
    //Service
    private WiserHelper wiserHelper;

    //Admin_side
    public LoginHelperA loginA() {
        if (loginHelperA == null)
            loginHelperA = new LoginHelperA(wd);
        return loginHelperA;
    }

    public NavigationHelperA navigateA() {
        if (navigationHelperA == null)
            navigationHelperA = new NavigationHelperA(wd);
        return navigationHelperA;
    }

    public CategoryHelperA categoryA() {
        if (categoryHelperA == null)
            categoryHelperA = new CategoryHelperA(wd);
        return categoryHelperA;
    }

    //Public_side
    public LoginHelperP loginP() {
        if (loginHelperP == null)
            loginHelperP = new LoginHelperP(wd);
        return loginHelperP;
    }

    public NavigationHelperP navigateP() {
        if (navigationHelperP == null)
            navigationHelperP = new NavigationHelperP(wd);
        return navigationHelperP;
    }

    public ForgotPasswordHelperP forgotPassP() {
        if (forgotPasswordHelperP == null)
            forgotPasswordHelperP = new ForgotPasswordHelperP(wd);
        return forgotPasswordHelperP;
    }

    //Service
    public WiserHelper wiser() {
        if (wiserHelper == null)
            wiserHelper = new WiserHelper();
        return wiserHelper;
    }
}
