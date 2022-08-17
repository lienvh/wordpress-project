package actions.pageObjects.adminPageObjects;

import actions.commons.BasePage;
import actions.commons.PageGeneratorManager;
import actions.pageUIs.adminPageUIs.DashboardPageUI;
import org.openqa.selenium.WebDriver;

public class DashboardPageObject extends BasePage {
    private WebDriver driver;

    public DashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean pageTitleIsDisplayed() {
        waitForElementVisible(driver, DashboardPageUI.PAGE_TITLE);
        return isElementDisplayed(driver, DashboardPageUI.PAGE_TITLE);
    }

    public AllPostsPageObject clickToPostsMenuLink() {
        waitForElementVisible(driver, DashboardPageUI.POSTS_MENU_LINK);
        clickToElement(driver, DashboardPageUI.POSTS_MENU_LINK);
        return PageGeneratorManager.getAllPostsPage(driver);
    }
}
