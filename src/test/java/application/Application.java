package application;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Application {
    private WebDriver wd;
    private WebDriverWait wait;
    private enum Browser {
        CHROME,
        FIREFOX,
        IE
    }

    public void init() {
        wd = createDriver(Browser.CHROME);
        wait = new WebDriverWait(wd, 10);
        wd.manage().window().maximize();
    }

    public void stop() {
        if (wd != null)
            wd.quit();
    }

    private WebDriver createDriver(Browser browser) {
        WebDriver wd = null;
        switch (browser) {
            case CHROME:
                wd = new ChromeDriver();
                break;
            case FIREFOX:
                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
                wd = new FirefoxDriver(ffOptions);
                break;
            case IE:
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.introduceFlakinessByIgnoringSecurityDomains();
                ieOptions.ignoreZoomSettings();
                wd = new InternetExplorerDriver(ieOptions);
                break;
        }
        return wd;
    }
}
