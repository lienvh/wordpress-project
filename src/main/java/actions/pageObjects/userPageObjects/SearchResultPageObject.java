package actions.pageObjects.userPageObjects;

import actions.commons.BasePage;
import actions.pageUIs.userPageUIs.SearchResultPageUI;
import org.openqa.selenium.WebDriver;


public class SearchResultPageObject extends BasePage {
    private WebDriver driver;

    public SearchResultPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isNothingSearchResultTitlDisplayed() {
        waitForElementVisible(driver, SearchResultPageUI.NOTHING_SEARCH_RESULT_TITLE);
        return isElementDisplayed(driver, SearchResultPageUI.NOTHING_SEARCH_RESULT_TITLE);
    }
}
