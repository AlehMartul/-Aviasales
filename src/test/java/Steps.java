import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElement;
import framework.tools.ReadPropertyTool;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Steps {

    private static final String RESOURCES_PATH = "src/main/resources/";
    private static final String NAME = "aviasales.properties";
    private static final String DURATION_FORMAT = ReadPropertyTool
            .getData("durationInInitialFormat", RESOURCES_PATH, NAME);
    private DateTimeFormatter customDateTimeFormatter = DateTimeFormat.forPattern("HH.mm");

    private List<String> getAllDurations(String xPath) {
        AqualityServices.getLogger().info("Getting duration of the all flights");
        List<IButton> durations = AqualityServices.getElementFactory().
                findElements(By.xpath(xPath), ElementType.BUTTON);
        List<String> durStr = new ArrayList<>();
        for (IElement e : durations) {
            durStr.add(e.getText());
        }
        return durStr;
    }

    private List<DateTime> formatToHours(String xPath) {
        AqualityServices.getLogger().info("Converting durations in date format");
        ArrayList<DateTime> durationInInitialFormat = new ArrayList<>();
        for (int i = 0; i < getAllDurations(xPath).size(); i++) {
            DateTimeFormatter initialUIDateTimeFormatter = DateTimeFormat.forPattern(DURATION_FORMAT);
            DateTime dateTime = initialUIDateTimeFormatter.parseDateTime(getAllDurations(xPath).get(i));
            durationInInitialFormat.add(dateTime);
        }
        return durationInInitialFormat;
    }

    public void findFaster(String xPath) {
        AqualityServices.getLogger().info("Looking faster flight");
        List<DateTime> result = formatToHours(xPath);
        DateTime min = Collections.min(result);
        System.out.println("Duration of faster flight is " + customDateTimeFormatter.print(min));
    }
}
