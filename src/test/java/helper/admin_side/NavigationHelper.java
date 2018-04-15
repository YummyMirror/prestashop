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

    //Additional methods
    private List<WebElement> getMenuItems() {
        return wait.until(visibilityOfAllElementsLocatedBy(By.xpath("//nav/ul[contains(@class, 'menu')]" +
                                                                    "/li[contains(@id, 'subtab') or contains(@id, 'Admin')]")));
    }

    private List<WebElement> getSubItems(WebElement parent) {
        return parent.findElements(By.xpath(".//li"));
    }

    //Main methods
    public void openMenuItem(String itemName, String subItemName) {
        for (int i = 0; i < getMenuItems().size(); i++) {
            List<WebElement> menuItems = getMenuItems();
            if (itemName.equalsIgnoreCase(menuItems.get(i).findElement(By.xpath("./a")).getText())) {
                menuItems.get(i).click();
                wait.until(attributeContains(getMenuItems().get(i), "class", "active"));

                for (int j = 0; j < getSubItems(getMenuItems().get(i)).size(); j++) {
                    List<WebElement> subItems = getSubItems(getMenuItems().get(i));
                    if (subItemName.equalsIgnoreCase(subItems.get(j).findElement(By.xpath("./a")).getText())) {
                        subItems.get(j).click();
                        wait.until(attributeContains(getSubItems(getMenuItems().get(i)).get(j), "class", "active"));
                        break;
                    }
                }
            }
        }
    }
}
