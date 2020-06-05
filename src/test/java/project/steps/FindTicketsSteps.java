package project.steps;

import aquality.selenium.browser.AqualityServices;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import project.pages.ResultOfSearchPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindTicketsSteps {

    private DateTimeFormatter customDateTimeFormatter = DateTimeFormat.forPattern("HH.mm");

    private List<DateTime> formatToHours(String formatOfDuration) {
        AqualityServices.getLogger().info("Converting durations in date format");
        ArrayList<DateTime> durationInInitialFormat = new ArrayList<>();
        ArrayList<String> allDurations = (ArrayList<String>) new ResultOfSearchPage().getAllDurations();
        for (int i = 0; i < allDurations.size(); i++) {
            DateTimeFormatter initialUIDateTimeFormatter = DateTimeFormat.forPattern(formatOfDuration);
            DateTime dateTime = initialUIDateTimeFormatter.parseDateTime(allDurations.get(i));
            durationInInitialFormat.add(dateTime);
        }
        return durationInInitialFormat;
    }

    public void findFaster(String formatOfDuration) {
        AqualityServices.getLogger().info("Looking faster flight");
        List<DateTime> result = formatToHours(formatOfDuration);
        DateTime min = Collections.min(result);
        AqualityServices.getLogger().info("Duration of faster flight is " + customDateTimeFormatter.print(min));
    }
}
