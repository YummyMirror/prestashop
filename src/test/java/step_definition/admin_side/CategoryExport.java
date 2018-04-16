package step_definition.admin_side;

import base.CucumberBase;
import cucumber.api.java8.En;
import model.admin_side.UserData;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.core.har.HarEntry;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.util.List;
import static org.testng.Assert.*;

public class CategoryExport implements En {
    private CucumberBase base;
    private BrowserMobProxy proxy;

    public CategoryExport(CucumberBase base) {
        this.base = base;
        File dir = base.app.getDownloadDir();

        Given("^I open the categories page$", () -> {
            if (proxy == null)
                proxy = base.app.proxy();
            base.app.navigateA().openUrl(base.app.properties().getProperty("adminBaseUrl"));
            base.app.loginA().loginAs(new UserData().setLogin(base.app.properties().getProperty("adminLogin"))
                                                    .setPassword(base.app.properties().getProperty("adminPassword")));
            base.app.navigateA().openMenuItem("Catalog", "Categories");
        });
        When("^I click the \'Export\' button$", () -> {
            proxy.newHar();
            base.app.categoryA().clickExportButton();
        });
        Then("^I verify that \'csv\' file is downloaded$", () -> {
            List<HarEntry> downloadFilesList = base.app.categoryA().getDownloadFilesList(proxy.getHar().getLog().getEntries());
            if (base.app.wd() instanceof ChromeDriver)
                assertEquals(downloadFilesList.size(), 1);
        });
        And("^Name of the file contains \'csv\'$", () -> {
            if (base.app.categoryA().waitForFileDownload(dir, 5))
                assertTrue(base.app.categoryA().getLastFileName(dir).contains("category"));
            if (proxy != null)
                proxy.endHar();
        });
    }
}
