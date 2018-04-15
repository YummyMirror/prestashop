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
            base.app.navigate().openUrl(base.app.properties().getProperty("baseUrl"));
            base.app.login().loginAs(new UserData().setLogin(base.app.properties().getProperty("login"))
                                                   .setPassword(base.app.properties().getProperty("password")));
            base.app.navigate().openMenuItem("Catalog", "Categories");
        });
        When("^I click the \'Export\' button$", () -> {
            proxy.newHar();
            base.app.category().clickExportButton();
        });
        Then("^I verify that \'csv\' file is downloaded$", () -> {
            List<HarEntry> downloadFilesList = base.app.category().getDownloadFilesList(proxy.getHar().getLog().getEntries());
            if (base.app.wd() instanceof ChromeDriver)
                assertEquals(downloadFilesList.size(), 1);
        });
        And("^Name of the file contains \'csv\'$", () -> {
            if (base.app.category().waitForFileDownload(dir, 5))
                assertTrue(base.app.category().getLastFileName(dir).contains("category"));
            if (proxy != null)
                proxy.endHar();
        });
    }
}