import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import framework.tools.ReadPropertyTool;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    private static final String RESOURCES_PATH = "src/main/resources/";
    private static final String NAME = "aviasales.properties";
    private static final String MAIN_URL = ReadPropertyTool.getData("mainUrl", RESOURCES_PATH, NAME);

    @BeforeMethod
    protected void beforeMethod() {
        getBrowser().maximize();
        getBrowser().goTo(MAIN_URL);
        AqualityServices.getLogger().info("Opening main page");
    }

    @AfterMethod
    public void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
            AqualityServices.getLogger().info("Closing browser");
        }
    }

    protected Browser getBrowser() {
        return AqualityServices.getBrowser();
    }
}
