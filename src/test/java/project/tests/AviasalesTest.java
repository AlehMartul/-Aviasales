package project.tests;

import aquality.selenium.browser.AqualityServices;
import framework.tools.ReadPropertyTool;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import project.pages.MainPage;
import project.pages.ResultOfSearchPage;
import project.steps.Steps;

public class AviasalesTest extends BaseTest {

    private static final String FORMAT_OF_DURATION = new ReadPropertyTool
            (RESOURCES_PATH, AVIASALES_PROPERTIES).getProperty("durationInInitialFormat");

    @Parameters({"departureCity", "destinationCity", "departureDate"})
    @Test
    public void testSearchFlightByParameters(String departureCity, String destinationCity, String departureDate)
            throws NumberFormatException {
        AqualityServices.getLogger().info("Step one - open aviasales page");
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.isPageLoaded(mainPage.getBtnSearchTickets()), "Main page didn't load");

        AqualityServices.getLogger().info("Step two - set date and city of flight");
        mainPage.clearDepartureField();
        mainPage.setDeparturePlace(departureCity);
        Assert.assertTrue(mainPage.isDeparturePlaceSet(departureCity), "City of departure didn't set");
        mainPage.setDestinationPlace(destinationCity);
        Assert.assertTrue(mainPage.isDestinationPlaceSet(destinationCity), "City of destination didn't set");
        mainPage.clickOnCalendar();
        mainPage.setDateOfDeparture(departureDate);
        Assert.assertTrue(mainPage.isDateOfDepartureSet(departureDate), "Date of departure didn't set");
        mainPage.clickOnNoNeedReturn();
        mainPage.clickOnOpenBooking();
        mainPage.clickOnSearchTicketsButton();

        AqualityServices.getLogger().info("Step three - looking for faster flight");
        ResultOfSearchPage resultOfSearchPage = new ResultOfSearchPage();
        Assert.assertTrue(resultOfSearchPage.isPageLoaded());
        new Steps().findFaster(FORMAT_OF_DURATION);
    }
}
