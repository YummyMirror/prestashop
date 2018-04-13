package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public abstract class HelperBase {
    protected WebDriver wd;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor js;

    //Constructor
    public HelperBase(WebDriver wd) {
        this.wd = wd;
        wait = new WebDriverWait(wd, 10);
        actions = new Actions(wd);
        js = (JavascriptExecutor) wd;
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

    public void click(WebElement element) {
        wait.until(elementToBeClickable(element)).click();
    }

    public Boolean isElementPresent(By locator) {
        return wd.findElements(locator).size() > 0;
    }

    public void check(By locator, Boolean value) {
        if (value != null) {
            if (value) {
                if (! wd.findElement(locator).isSelected())
                    wd.findElement(locator).click();
            } else {
                if (wd.findElement(locator).isSelected())
                    wd.findElement(locator).click();
            }
        }
    }

    public void radio(By locator, Boolean value) {
        if (value != null) {
            List<WebElement> radios = wd.findElements(locator);
            if (value) {
                radios.stream().filter(i -> i.getAttribute("for").contains("on")).findFirst().get().click();
            } else {
                radios.stream().filter(i -> i.getAttribute("for").contains("off")).findFirst().get().click();
            }
        }
    }

    private void toFrame(By locator) {
        wait.until(frameToBeAvailableAndSwitchToIt(locator));
    }

    public void textAreaInFrame(By frameLocator, String value) {
        if (value != null && ! value.isEmpty()) {
            toFrame(frameLocator);
            String currentValue = wd.findElement(By.xpath("//body")).getText();
            if (! value.equals(currentValue))
                wd.findElement(By.xpath("//body")).clear();
                wd.findElement(By.xpath("//body")).sendKeys(value);
                wd.switchTo().defaultContent();
        }
    }

    public void upload(By locator, File file) {
        if (file != null)
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
    }

    public Alert alert() {
        return wait.until(alertIsPresent());
    }
}
