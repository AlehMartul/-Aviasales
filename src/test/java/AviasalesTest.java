import framework.tools.ReadPropertyTool;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.concurrent.TimeoutException;

public class AviasalesTest extends BaseTest {

    private static final String RESOURCES_PATH = "src/main/resources/";
    private static final String NAME = "aviasales.properties";
    private String departureCity = ReadPropertyTool.getData("departureCity", RESOURCES_PATH, NAME);
    private String destinationCity = ReadPropertyTool.getData("destinationCity", RESOURCES_PATH, NAME);
    private String departureDate = ReadPropertyTool.getData("departureDate", RESOURCES_PATH, NAME);

    @Test
    public void testSearchFlightByParameters() throws TimeoutException, NumberFormatException {
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.isPageLoaded(), "Main page didn't load");
        mainPage.clearDepartureField();
        mainPage.setDeparturePlace(departureCity);
        Assert.assertTrue(mainPage.isDeparturePlaceSet(departureCity), "City of departure didn't set");
        mainPage.setDestinationPlace(destinationCity);
        Assert.assertTrue(mainPage.isDestinationPlaceSet(destinationCity), "City of destination didn't set");
        mainPage.clickOnCalendar();
        mainPage.setDateOfDeparture();
        Assert.assertTrue(mainPage.isDateOfDepartureSet(departureDate), "Date of departure didn't set");
        mainPage.clickOnNoNeedReturn();
        mainPage.clickOnOpenBooking();
        mainPage.clickOnSearchTicketsButton();
        ResultOfSearchPage resultOfSearchPage = new ResultOfSearchPage();
        Assert.assertTrue(resultOfSearchPage.isPageLoaded());
        resultOfSearchPage.getTimeOfFasterFlight();
    }
}
