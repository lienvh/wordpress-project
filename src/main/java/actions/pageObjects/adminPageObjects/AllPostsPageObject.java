package actions.pageObjects.adminPageObjects;

import actions.commons.BasePage;
import actions.commons.PageGeneratorManager;
import actions.pageObjects.userPageObjects.HomePageObject;
import actions.pageUIs.adminPageUIs.AllPostsPageUI;
import org.openqa.selenium.WebDriver;

public class AllPostsPageObject extends BasePage {
    private WebDriver driver;

    public AllPostsPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public AddNewPostPageObject clickToAddNewButton() {
        waitForElementClickable(driver, AllPostsPageUI.ADD_NEW_BUTTON);
        clickToElement(driver, AllPostsPageUI.ADD_NEW_BUTTON);
        return PageGeneratorManager.getAddNewPostPage(driver);
    }

    public void inputToSearchTexbox(String postTitle) {
        waitForElementVisible(driver, AllPostsPageUI.SEARCH_TEXTBOX);
        sendKeyToElement(driver, AllPostsPageUI.SEARCH_TEXTBOX, postTitle);

    }

    public void clickToSearchButton() {
        waitForElementClickable(driver, AllPostsPageUI.SEARCH_BUTTON);
        clickToElement(driver, AllPostsPageUI.SEARCH_BUTTON);
        sleepInSecond(2);

    }

    public boolean isSearchTableContainsPostInfoByID(String headerID, String value) {
        int headerIndex = getElementSize(driver, AllPostsPageUI.HEADER_INDEX_BY_ID, headerID) + 1;
        waitForElementVisible(driver, AllPostsPageUI.VALUE_EACH_COLUMN_BY_HEADER_INDEX,
                String.valueOf(headerIndex), value);
        return isElementDisplayed(driver, AllPostsPageUI.VALUE_EACH_COLUMN_BY_HEADER_INDEX,
                String.valueOf(headerIndex), value);
    }

    public AddNewPostPageObject clickToPostTitle(String postTitle) {
        waitForElementVisible(driver, AllPostsPageUI.TITLE_POST_BY_TEXT, postTitle);
        clickToElement(driver, AllPostsPageUI.TITLE_POST_BY_TEXT, postTitle);
        return PageGeneratorManager.getAddNewPostPage(driver);
    }

    public void checkToCheckboxByPostTitle(String postTitle) {
        waitForElementClickable(driver, AllPostsPageUI.CHECKBOX_ON_ROW_BY_POST_TITLE, postTitle);
        clickToElement(driver, AllPostsPageUI.CHECKBOX_ON_ROW_BY_POST_TITLE, postTitle);

    }

    public void clickToApplyButton() {
        waitForElementClickable(driver, AllPostsPageUI.APPLY_BUTTON);
        clickToElement(driver, AllPostsPageUI.APPLY_BUTTON);

    }

    public boolean isMovedTheTrashMessageDisplayed(String movedToTrashMessage) {
        waitForElementVisible(driver, AllPostsPageUI.MOVED_TO_TRASH_MESSAGE, movedToTrashMessage);
        return isElementDisplayed(driver, AllPostsPageUI.MOVED_TO_TRASH_MESSAGE, movedToTrashMessage);
    }

    public boolean isNotFoundSearchResultMessageDisplayed(String notFoundSearchResultMessage) {
        waitForElementVisible(driver, AllPostsPageUI.NOT_FOUND_SEARCH_RESULT, notFoundSearchResultMessage);
        return isElementDisplayed(driver, AllPostsPageUI.NOT_FOUND_SEARCH_RESULT, notFoundSearchResultMessage);
    }

    public void selectItemInActionDropdown(String itemName) {
        waitForElementClickable(driver, AllPostsPageUI.ACTION_DROPDOWN, itemName);
        selectItemInDefaultDropdown(driver, AllPostsPageUI.ACTION_DROPDOWN, itemName);

    }

}
