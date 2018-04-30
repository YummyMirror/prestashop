package base;

import helper.service.WiserHelper;
import helper.admin_side.CategoryHelperA;
import helper.admin_side.LoginHelperA;
import helper.admin_side.NavigationHelperA;
import helper.public_side.ForgotPasswordHelperP;
import helper.public_side.LoginHelperP;
import helper.public_side.NavigationHelperP;
import org.openqa.selenium.WebDriver;
import java.util.Objects;

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
        if (Objects.isNull(loginHelperA))
            loginHelperA = new LoginHelperA(wd);
        return loginHelperA;
    }

    public NavigationHelperA navigateA() {
        if (Objects.isNull(navigationHelperA))
            navigationHelperA = new NavigationHelperA(wd);
        return navigationHelperA;
    }

    public CategoryHelperA categoryA() {
        if (Objects.isNull(categoryHelperA))
            categoryHelperA = new CategoryHelperA(wd);
        return categoryHelperA;
    }

    //Public_side
    public LoginHelperP loginP() {
        if (Objects.isNull(loginHelperP))
            loginHelperP = new LoginHelperP(wd);
        return loginHelperP;
    }

    public NavigationHelperP navigateP() {
        if (Objects.isNull(navigationHelperP))
            navigationHelperP = new NavigationHelperP(wd);
        return navigationHelperP;
    }

    public ForgotPasswordHelperP forgotPassP() {
        if (Objects.isNull(forgotPasswordHelperP))
            forgotPasswordHelperP = new ForgotPasswordHelperP(wd);
        return forgotPasswordHelperP;
    }

    //Service
    public WiserHelper wiser() {
        if (Objects.isNull(wiserHelper))
            wiserHelper = new WiserHelper();
        return wiserHelper;
    }
}
