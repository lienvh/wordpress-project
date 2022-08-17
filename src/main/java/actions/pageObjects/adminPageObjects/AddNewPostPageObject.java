package actions.pageObjects.adminPageObjects;

import actions.commons.BasePage;
import actions.commons.PageGeneratorManager;
import actions.pageUIs.adminPageUIs.AddNewPostPageUI;
import org.openqa.selenium.WebDriver;

public class AddNewPostPageObject extends BasePage {
    private WebDriver driver;

    public AddNewPostPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void inputToPostTitle(String postTitle) {
        waitForElementVisible(driver, AddNewPostPageUI.TITLE_TEXTBOX);
        sendKeyToElement(driver, AddNewPostPageUI.TITLE_TEXTBOX, postTitle);
    }

    public void clickToPostBody() {
        waitForElementClickable(driver, AddNewPostPageUI.BODY_BUTTON);
        clickToElement(driver, AddNewPostPageUI.BODY_BUTTON);
    }

    public void inputToPostBody(String postBody) {
        waitForElementVisible(driver, AddNewPostPageUI.BODY_TEXTBOX);
        clearValueInElementByDeleteKey(driver, AddNewPostPageUI.BODY_TEXTBOX);
        sendKeyToElement(driver, AddNewPostPageUI.BODY_TEXTBOX, postBody);

    }

    public void clickToPrePublishOrUpdateButton() {
        waitForElementClickable(driver, AddNewPostPageUI.PRE_PUBLISH_OR_UPDATE_BUTTON);
        clickToElement(driver, AddNewPostPageUI.PRE_PUBLISH_OR_UPDATE_BUTTON);
        sleepInSecond(1);

    }

    public void clickToPublishButton() {
        waitForElementClickable(driver, AddNewPostPageUI.PUBLISH_BUTTON);
        clickToElement(driver, AddNewPostPageUI.PUBLISH_BUTTON);
        sleepInSecond(1);

    }

    public boolean isPostPublishMessageDisplayed(String postPublishMessage) {
        waitForElementVisible(driver, AddNewPostPageUI.POST_PUBLISHED_OR_EDITED_MESSAGE, postPublishMessage);
        return isElementDisplayed(driver, AddNewPostPageUI.POST_PUBLISHED_OR_EDITED_MESSAGE, postPublishMessage);
    }

    public AllPostsPageObject openAllPostsUrl(String allPostsUrl) {
        openPageUrl(driver, allPostsUrl);
        return PageGeneratorManager.getAllPostsPage(driver);
    }
}
