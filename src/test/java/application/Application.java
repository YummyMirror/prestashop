package application;

import base.ApplicationBase;
import com.google.common.io.Files;
import cucumber.api.Scenario;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Application extends ApplicationBase {
    private WebDriver wd;
    private WebDriverWait wait;
    private Actions actions;
    private JavascriptExecutor js;
    private Properties properties;
    private enum Browser {
        CHROME,
        FIREFOX
    }
    File downloadDir = new File("src\\test\\resources\\download");
    private BrowserMobProxy proxy;

    //Getters
    public WebDriver wd() {
        return wd;
    }

    public Properties properties() {
        return properties;
    }

    public BrowserMobProxy proxy() {
        return proxy;
    }

    public File getDownloadDir() {
        return downloadDir;
    }

    public void init() throws IOException {
        proxy = new BrowserMobProxyServer();
        proxy.start(0);
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        loadProperties();
        wd = createDriver(Browser.CHROME, seleniumProxy);
        wait = new WebDriverWait(wd, 10);
        actions = new Actions(wd);
        js = (JavascriptExecutor) wd;
        wd.manage().window().maximize();
        initDelegate(wd);
    }

    public void stop() {
        if (wd != null)
            wd.quit();
    }

    private WebDriver createDriver(Browser browser, Proxy seleniumProxy) {
        WebDriver wd = null;
        switch (browser) {
            case CHROME:
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_settings.popups", 0);
                prefs.put("download.default_directory", downloadDir.getAbsolutePath());
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs);
                options.setProxy(seleniumProxy);
                wd = new ChromeDriver(options);
                break;
            case FIREFOX:
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("browser.download.folderList", 2);
                profile.setPreference("browser.download.dir", downloadDir.getAbsolutePath());
                profile.setPreference("browser.download.manager.showWhenStarting", false);
                profile.setPreference("browser.helperApps.neverAsk.openFile", "application/force-download");
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/force-download");
                profile.setPreference("browser.helperApps.alwaysAsk.force", false);
                FirefoxOptions ffOptions = new FirefoxOptions();
                ffOptions.setProfile(profile);
                ffOptions.setProxy(seleniumProxy);
                ffOptions.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
                wd = new FirefoxDriver(ffOptions);
                break;
        }
        return wd;
    }

    private void loadProperties() throws IOException {
        properties = new Properties();
        properties.load(new FileReader(new File("src/test/resources/property/admin.properties")));
    }

    public void takeScreenshot(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            File screenshot = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot, new File("src/test/resources/screenshot/" + scenario.getName() + "_" + System.currentTimeMillis() + ".jpg"));
        }
    }
}