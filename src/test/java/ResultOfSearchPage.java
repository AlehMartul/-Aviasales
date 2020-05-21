import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

public class ResultOfSearchPage extends BasePage {

    private static final String XPATH_OF_SCHEDULE = "//span[@class='prediction-advice__link --selected']";
    private static final String XPATH_OF_DURATIONS = "//div[@class='segment-route__duration']";
    private IButton schedule = AqualityServices.getElementFactory()
            .getButton(By.xpath(XPATH_OF_SCHEDULE), "schedule");

    public ResultOfSearchPage() {
        super(By.xpath(XPATH_OF_SCHEDULE), "MainPage");
    }

    public void getTimeOfFasterFlight (){
        new Steps().findFaster(XPATH_OF_DURATIONS);
    }

    @Override
    public boolean isPageLoaded() throws TimeoutException {
        AqualityServices.getConditionalWait().waitForTrue(() -> schedule.state().waitForDisplayed(),
                Duration.ofSeconds(40), Duration.ofMillis(5000), "File should be downloaded");
        return schedule.state().waitForDisplayed();
    }
}
