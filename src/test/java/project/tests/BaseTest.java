package project.tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import framework.tools.ReadPropertyTool;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    public static final String RESOURCES_PATH = "src/main/resources/";
    public static final String AVIASALES_PROPERTIES = "aviasales.properties";
    private static final String MAIN_URL = new ReadPropertyTool(BaseTest.RESOURCES_PATH, BaseTest.AVIASALES_PROPERTIES)
            .getProperty("mainUrl");

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
