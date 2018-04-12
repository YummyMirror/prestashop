package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public abstract class HelperBase {
    protected WebDriver wd;
    protected WebDriverWait wait;

    //Constructor
    public HelperBase(WebDriver wd) {
        this.wd = wd;
        wait = new WebDriverWait(wd, 10);
    }

    public void openUrl(String url) {
        wd.navigate().to(url);
        wait.until(urlContains(url));
    }

    public void input(By locator, String value) {
        if (value != null && ! value.isEmpty()) {
            String currentValue = wd.findElement(locator).getAttribute("value");
            if (! value.equals(currentValue)) {
                wd.findElement(locator).click();
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(value);
            }
        }
    }

    public void click(By locator) {
        wait.until(elementToBeClickable(locator)).click();
    }

    public Boolean isElementPresent(By locator) {
        return wd.findElements(locator).size() > 0;
    }
}
