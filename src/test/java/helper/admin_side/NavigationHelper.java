package helper.admin_side;

import base.HelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class NavigationHelper extends HelperBase {
    //Constructor
    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void openMenuItem(String itemName, String subItemName) {
        for (int i = 0; i < getMenuItems().size(); i++) {
            List<WebElement> menuItems = getMenuItems();
            if (itemName.equalsIgnoreCase(menuItems.get(i).findElement(By.xpath("./a")).getText())) {
                actions.moveToElement(menuItems.get(i))
                        .build()
                        .perform();
                wait.until(attributeContains(menuItems.get(i), "class", "hover"));

                List<WebElement> subItems = menuItems.get(i).findElements(By.xpath("./ul[contains(@class, 'submenu')]/li"));
                for (int j = 0; j < subItems.size(); j++) {
                    if (subItemName.equalsIgnoreCase(subItems.get(j).findElement(By.xpath("./a")).getText())) {
                        actions.moveToElement(subItems.get(j))
                                .click()
                                .build()
                                .perform();
                        break;
                    }
                }
            }
        }
    }

    private List<WebElement> getMenuItems() {
        return wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class = 'menu']/li[contains(@class, 'maintab')]")));
    }
}
