import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import framework.tools.ReadPropertyTool;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class MainPage extends BasePage {

    private static final String RESOURCES_PATH = "src/main/resources/";
    private static final String NAME = "aviasales.properties";
    private static final String ENTER_DATE = "//*[text()='%s']";
    private static final String DEPARTURE_DATE = ReadPropertyTool.getData("departureDate", RESOURCES_PATH, NAME);
    private static final String SET_DEPARTURE_DATE = String.format(ENTER_DATE, DEPARTURE_DATE);
    private static final String XPATH_SEARCH_BUTTON = "//button[contains(@class, 'home')]";
    private IButton btnSearchTickets = AqualityServices.getElementFactory()
            .getButton(By.xpath(XPATH_SEARCH_BUTTON), "search tickets button");
    private ITextBox txbDepartureField = AqualityServices.getElementFactory()
            .getTextBox(By.xpath("//input[@id = 'origin']"), "departure");
    private ITextBox txbDestinationField = AqualityServices.getElementFactory()
            .getTextBox(By.xpath("//input[@id = 'destination']"), "destination");
    private IButton btnDepartureDate = AqualityServices.getElementFactory()
            .getButton(By.xpath(SET_DEPARTURE_DATE), "date of departure");
    private IButton btnNoNeedReturn = AqualityServices.getElementFactory()
            .getButton(By.xpath("//button[contains(@class,'cancel-departure')]"), "Not need ticket for return button");
    private ILabel lblOpenBooking = AqualityServices.getElementFactory()
            .getLabel(By.xpath("//label[@class='of_input_checkbox__label']"), "Open Booking in new window");
    private IButton btnCalendar = AqualityServices.getElementFactory()
            .getButton(By.xpath("//div[@class='trip-duration__field --departure']"), "Calendar");

    public MainPage() {
        super(By.xpath(XPATH_SEARCH_BUTTON), "MainPage");
    }

    public void clickOnSearchTicketsButton() {
        AqualityServices.getLogger().info("Clicking on 'Search tickets' button");
        btnSearchTickets.click();
    }

    public void clickOnCalendar() {
        AqualityServices.getLogger().info("Clicking on the calendar");
        btnCalendar.click();
    }

    public void clickOnOpenBooking() {
        AqualityServices.getLogger().info("Unmarking checkbox 'open Booking in new window'");
        lblOpenBooking.click();
    }

    public void clickOnNoNeedReturn() {
        AqualityServices.getLogger().info("Clicking on the 'no need return ticket' button");
        btnNoNeedReturn.click();
    }

    public void setDateOfDeparture() {
        AqualityServices.getLogger().info("Setting departure date");
        btnDepartureDate.click();
    }

    public boolean isDateOfDepartureSet(String date) {
        AqualityServices.getLogger().info("Checking the date of departure");
        return btnDepartureDate.getText().contains(date);
    }

    public void clearDepartureField() {
        AqualityServices.getLogger().info("Clearing the departure city field");
        txbDepartureField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
    }

    public void setDeparturePlace(String departureCity) {
        AqualityServices.getLogger().info("Setting departure city");
        txbDepartureField.sendKeys(departureCity);
    }

    public boolean isDeparturePlaceSet(String city) {
        AqualityServices.getLogger().info("Checking the city of departure");
        return txbDepartureField.getValue().contains(city);
    }

    public void setDestinationPlace(String destinationCity) {
        AqualityServices.getLogger().info("Setting destination city");
        txbDestinationField.sendKeys(destinationCity);
    }

    public boolean isDestinationPlaceSet(String city) {
        AqualityServices.getLogger().info("Checking destination point");
        return txbDestinationField.getValue().contains(city);
    }

    @Override
    public boolean isPageLoaded() {
        return btnSearchTickets.state().waitForDisplayed();
    }
}