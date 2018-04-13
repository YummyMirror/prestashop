package step_definition.admin_side;

import base.CucumberBase;
import cucumber.api.DataTable;
import cucumber.api.java8.En;
import model.admin_side.CategoryData;
import model.admin_side.UserData;
import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import static org.testng.Assert.*;

public class Category implements En {
    private CucumberBase base;
    private Set<CategoryData> before;
    private CategoryData categoryData;

    public Category(CucumberBase base) {
        this.base = base;

        Given("^Number of categories before creation$", () -> {
            base.app.navigate().openUrl("http://localhost/prestashop/admin7415x81bm/index.php");
            base.app.login().loginAs(new UserData().setLogin("123@mail.ru").setPassword("password"));
            base.app.navigate().openMenuItem("Catalog", "Categories");
            before = base.app.category().getCategories();
        });
        When("^I fill the form with data:$", (DataTable table) -> {
            categoryData = transform(table.raw().get(1));
            base.app.category().clickAddNewButton();
            base.app.category().fillCategoryForm(categoryData);
        });
        And("^I click the 'Save' button$", () -> {
            base.app.category().clickSaveButtonToCreate();
        });
        Then("^Number of groups is incremented$", () -> {
            Set<CategoryData> after = base.app.category().getCategories();
            assertEquals(after.size(), before.size() + 1);

            before.add(categoryData.setId(after.stream().max(Comparator.comparingInt(CategoryData::getId)).get().getId()));
            assertEquals(after, before);
        });
    }

    private CategoryData transform(List<String> list) {
        String name = list.get(0);
        Boolean isDisplayed = Boolean.valueOf(list.get(1));
        String description = list.get(2);
        String coverImage = list.get(3);
        return new CategoryData().setName(name)
                                 .setDisplayed(isDisplayed)
                                 .setDescription(description)
                                 .setCoverImage(new File(coverImage));
    }
}
