package project.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElement;
import framework.tools.ReadPropertyTool;
import org.openqa.selenium.By;
import project.tests.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ResultOfSearchPage extends BasePage {

    private static final String XPATH_OF_SCHEDULE = "//span[contains(@class,'link')and contains(@class,'selected')]";
    private static final String XPATH_OF_DURATIONS =
            "//div[contains(@class,'duration')and contains(@class,'segment-route')]";
    private static final String TIMEOUT =
            new ReadPropertyTool(BaseTest.RESOURCES_PATH, BaseTest.AVIASALES_PROPERTIES).getProperty("timeout");
    private static final String POLLING_INTERVAL =
            new ReadPropertyTool(BaseTest.RESOURCES_PATH, BaseTest.AVIASALES_PROPERTIES).getProperty("pollingInterval");
    private IButton schedule = AqualityServices.getElementFactory()
            .getButton(By.xpath(XPATH_OF_SCHEDULE), "schedule");

    public ResultOfSearchPage() {
        super(By.xpath(XPATH_OF_SCHEDULE), "MainPage");
    }

    public String getXpathOfDurations (){
        return XPATH_OF_DURATIONS;
    }

    public boolean isPageLoaded() {
        AqualityServices.getConditionalWait().waitFor(() -> schedule.state().waitForDisplayed(),
                Duration.ofSeconds(Integer.parseInt(TIMEOUT)), Duration.ofMillis(Integer.parseInt(POLLING_INTERVAL)),
                "Page should be loaded");
        return schedule.state().waitForDisplayed();
    }

    public List<String> getAllDurations() {
        AqualityServices.getLogger().info("Getting duration of the all flights");
        List<IButton> durations = AqualityServices.getElementFactory().
                findElements(By.xpath(XPATH_OF_DURATIONS), ElementType.BUTTON);
        List<String> durStr = new ArrayList<>();
        for (IElement e : durations) {
            durStr.add(e.getText());
        }
        return durStr;
    }
}
