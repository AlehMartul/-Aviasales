import aquality.selenium.elements.interfaces.IElement;
import org.openqa.selenium.By;

import java.util.concurrent.TimeoutException;

public abstract class BasePage {

    private By locator;
    private String pageName;

    protected BasePage(By locator, String pageName) {
        this.locator = locator;
        this.pageName = pageName;
    }

    public abstract boolean isPageLoaded() throws TimeoutException;
}