package helper.admin_side;

import base.HelperBase;
import model.admin_side.CategoryData;
import net.lightbody.bmp.core.har.HarEntry;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CategoryHelper extends HelperBase {
    //Constructor
    public CategoryHelper(WebDriver wd) {
        super(wd);
    }

    //Additional methods
    private void selectCategory(int id) {
        wd.findElement(By.xpath("//input[@value = '" + id + "']")).click();
    }

    private void clickBulkActionsButton() {
        click(By.xpath("//button[contains(@id, 'bulk_action')]"));
        wait.until(attributeContains(By.xpath("//button[contains(@id, 'bulk_action')]/.."),
                            "class", "open"));
    }

    private void clickButtonFromBulkActions(String buttonName) {
        List<WebElement> buttons = wd.findElements(By.xpath("//div[contains(@class, 'bulk-actions')]/ul//a"));
        for (WebElement button : buttons) {
            if (buttonName.equalsIgnoreCase(button.getText().trim())) {
                button.click();
                break;
            }
        }
    }

    private File getLatestFile(File dir) {
        assert dir.exists() && dir.isDirectory();
        return Arrays.stream(Objects.requireNonNull(dir.listFiles()))
                     .max(Comparator.comparingDouble(File::lastModified)).get();
    }

    //Main methods
    public Set<CategoryData> getCategories() {
        Set<CategoryData> categories = new HashSet<>();
        List<WebElement> rows = wd.findElements(By.xpath("//tr[contains(@id, 'tr')]"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.xpath("./td"));
            int id = Integer.parseInt(cells.get(1).getText());
            String name = cells.get(2).getText();
            categories.add(new CategoryData().setId(id)
                                             .setName(name));
        }
        return categories;
    }

    public void clickAddNewButton() {
        click(By.xpath("//a[contains(@id, 'new_category')]"));
        wait.until(urlContains("addcategory"));
    }

    public void fillCategoryForm(CategoryData category) {
        input(By.xpath("//input[@id = 'name_1']"), category.getName());
        radio(By.xpath("//span[contains(@class, 'fixed-width')]/label"), category.getDisplayed());
        textAreaInFrame(By.xpath("//iframe[@id = 'description_1_ifr']"), category.getDescription());
        upload(By.xpath("//input[@id = 'image']"), category.getCoverImage());
    }

    public void clickSaveButtonToCreate() {
        click(By.xpath("//button[@id = 'category_form_submit_btn']"));
        wait.until(visibilityOfElementLocated(By.xpath("//div[@class = 'alert alert-success']")));
    }

    public CategoryData transform(List<String> list) {
        String name = list.get(0);
        Boolean isDisplayed = Boolean.valueOf(list.get(1));
        String description = list.get(2);
        String coverImage = list.get(3);
        return new CategoryData().setName(name)
                                 .setDisplayed(isDisplayed)
                                 .setDescription(description)
                                 .setCoverImage(new File(coverImage));
    }

    public void deleteCategory(CategoryData category) {
        selectCategory(category.getId());
        clickBulkActionsButton();
        clickButtonFromBulkActions("Delete selected");
        alert().accept();
        click(By.xpath("//div[@class = 'panel-footer']//button[not(@name = 'cancel')]"));
        wait.until(visibilityOfElementLocated(By.xpath("//div[contains(@class, 'alert-success')]")));
    }

    public void clickExportButton() {
        click(By.xpath("//a[@id = 'desc-category-export']"));
    }

    public List<HarEntry> getDownloadFilesList(List<HarEntry> list) {
        return list.stream()
                   .filter(i -> i.getResponse().getStatus() == 200 &&
                                i.getResponse().getContent().getMimeType().contains("application/force-download"))
                   .collect(Collectors.toList());
    }

    public Boolean waitForFileDownload(File dir, int secToWait) throws InterruptedException {
        for (int i = 0; i < secToWait; i++) {
            if (! getLatestFile(dir).getName().contains("csv"))
                Thread.sleep(1000);
            else
                break;
        }
        return getLatestFile(dir).getName().contains("csv");
    }

    public String getLastFileName(File dir) {
        return getLatestFile(dir).getName();
    }
}
