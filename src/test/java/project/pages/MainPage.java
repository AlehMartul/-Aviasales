package project.pages;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class MainPage extends BasePage {

    private static final String ENTER_DATE_FORMAT = "//*[text()='%s']";
    private static final String A = "a";
    private static final String XPATH_SEARCH_BUTTON = "//button[contains(@class,'home')]";
    private IButton btnSearchTickets = AqualityServices.getElementFactory()
            .getButton(By.xpath(XPATH_SEARCH_BUTTON), "search tickets button");
    private ITextBox txbDepartureField = AqualityServices.getElementFactory()
            .getTextBox(By.xpath("//input[@id='origin']"), "departure");
    private ITextBox txbDestinationField = AqualityServices.getElementFactory()
            .getTextBox(By.xpath("//input[@id='destination']"), "destination");
    private IButton btnNoNeedReturn = AqualityServices.getElementFactory()
            .getButton(By.xpath("//button[contains(@class,'cancel-departure')]"), "Not need ticket for return button");
    private ILabel lblOpenBooking = AqualityServices.getElementFactory()
            .getLabel(By.xpath("//label[contains(@class,'of_input_checkbox')]"), "Open Booking in new window");
    private IButton btnCalendar = AqualityServices.getElementFactory()
            .getButton(By.xpath("//div[contains(@class,'field')and contains(@class,'departure')]"), "Calendar");

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

    public void setDateOfDeparture(String departureDate) {
        AqualityServices.getLogger().info("Setting departure date");
        String setDepartureDate = String.format(ENTER_DATE_FORMAT, departureDate);
        IButton btnDepartureDate = AqualityServices.getElementFactory()
                .getButton(By.xpath(setDepartureDate), "date of departure");
        btnDepartureDate.click();
    }

    public boolean isDateOfDepartureSet(String departureDate) {
        AqualityServices.getLogger().info("Checking the date of departure");
        String setDepartureDate = String.format(ENTER_DATE_FORMAT, departureDate);
        IButton btnDepartureDate = AqualityServices.getElementFactory()
                .getButton(By.xpath(setDepartureDate), "date of departure");
        return btnDepartureDate.getText().contains(departureDate);
    }

    public void clearDepartureField() {
        AqualityServices.getLogger().info("Clearing the departure city field");
        txbDepartureField.sendKeys(Keys.CONTROL + A + Keys.DELETE);
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

    public IButton getBtnSearchTickets(){
        return btnSearchTickets;
    }
}