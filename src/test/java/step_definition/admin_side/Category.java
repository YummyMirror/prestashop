package step_definition.admin_side;

import base.CucumberBase;
import cucumber.api.DataTable;
import cucumber.api.java8.En;
import model.admin_side.CategoryData;
import model.admin_side.UserData;
import java.io.File;
import java.util.Comparator;
import java.util.Set;
import static org.testng.Assert.*;

public class Category implements En {
    private CucumberBase base;
    private Set<CategoryData> before;
    private CategoryData category;
    private CategoryData deletedCategory;

    public Category(CucumberBase base) {
        this.base = base;

        //Create category
        Given("^Number of categories before creation$", () -> {
            base.app.navigate().openUrl(base.app.properties().getProperty("baseUrl"));
            base.app.login().loginAs(new UserData().setLogin(base.app.properties().getProperty("login"))
                                                   .setPassword(base.app.properties().getProperty("password")));
            base.app.navigate().openMenuItem("Catalog", "Categories");
            before = base.app.category().getCategories();
        });
        When("^I fill the form with data:$", (DataTable table) -> {
            category = base.app.category().transform(table.raw().get(1));
            base.app.category().clickAddNewButton();
            base.app.category().fillCategoryForm(category);
        });
        And("^I click the 'Save' button$", () -> {
            base.app.category().clickSaveButtonToCreate();
        });
        Then("^Number of groups is incremented$", () -> {
            Set<CategoryData> after = base.app.category().getCategories();
            assertEquals(after.size(), before.size() + 1, "Sizes are not equals!");

            before.add(category.setId(after.stream().max(Comparator.comparingInt(CategoryData::getId)).get().getId()));
            assertEquals(after, before, "Collections are not equals!");
        });

        //Delete category
        When("^I delete random category$", () -> {
            if (before.size() == 0) {
                CategoryData category = new CategoryData().setName("Temp name")
                                                          .setDisplayed(true)
                                                          .setDescription("Team Desc")
                                                          .setCoverImage(new File("src/test/resources/image/1.jpg"));
                base.app.category().clickAddNewButton();
                base.app.category().fillCategoryForm(category);
                base.app.category().clickSaveButtonToCreate();
            }
            deletedCategory = before.stream().findAny().get();
            base.app.category().deleteCategory(deletedCategory);
        });
        Then("^Number of categories is decremented$", () -> {
            Set<CategoryData> after = base.app.category().getCategories();
            assertEquals(after.size() + 1, before.size(), "Sizes are not equals!");

            before.remove(deletedCategory);
            assertEquals(after, before, "Collections are not equals!");
        });
    }
}
