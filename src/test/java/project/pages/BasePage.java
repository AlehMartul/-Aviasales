package project.pages;

import aquality.selenium.elements.interfaces.IElement;
import org.openqa.selenium.By;

public abstract class BasePage {

    private By locator;
    private String pageName;

    protected BasePage(By locator, String pageName) {
        this.locator = locator;
        this.pageName = pageName;
    }

    public boolean isPageLoaded(IElement element){
        return element.state().waitForDisplayed();
    }
}