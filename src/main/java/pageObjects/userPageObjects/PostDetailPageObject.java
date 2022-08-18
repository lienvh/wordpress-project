package pageObjects.userPageObjects;

import commons.BasePage;
import pageUIs.userPageUIs.PostDetailPageUI;
import org.openqa.selenium.WebDriver;

public class PostDetailPageObject extends BasePage {
    private WebDriver driver;

    public PostDetailPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPostTitleDisplayed(String postTitle) {
        waitForElementVisible(driver, PostDetailPageUI.POST_TITLE, postTitle);
        return isElementDisplayed(driver, PostDetailPageUI.POST_TITLE, postTitle);
    }

    public boolean isPostCreatedDateDisplayed(String postTitle, String currentDate) {
        waitForElementVisible(driver, PostDetailPageUI.POST_CREATED_DATE, postTitle, currentDate);
        return isElementDisplayed(driver, PostDetailPageUI.POST_CREATED_DATE, postTitle, currentDate);
    }

    public boolean isPostAuthorDisplayed(String postTitle, String postAuthor) {
        waitForElementVisible(driver, PostDetailPageUI.POST_AUTHOR, postTitle, postAuthor);
        return isElementDisplayed(driver, PostDetailPageUI.POST_AUTHOR, postTitle, postAuthor);
    }

    public boolean isPostBodyDisplayed(String postTitle, String postBody) {
        waitForElementVisible(driver, PostDetailPageUI.POST_BODY, postTitle, postBody);
        return isElementDisplayed(driver, PostDetailPageUI.POST_BODY, postTitle, postBody);
    }

}
