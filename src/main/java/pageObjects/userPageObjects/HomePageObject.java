package pageObjects.userPageObjects;

import commons.BasePage;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageUIs.userPageUIs.HomePageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPostTitleDisplayed(String postTitle) {
        return isElementDisplayed(driver, HomePageUI.POST_TITLE_BY_TEXT, postTitle);
    }

    public boolean isPostCreatedDateDisplayed(String postTitle, String postCreatedDate) {
        return isElementDisplayed(driver, HomePageUI.POST_CREATED_DATE_BY_TEXT, postTitle, postCreatedDate);
    }

    public boolean isPostAuthorDisplayed(String postTitle, String postAuthor) {
        return isElementDisplayed(driver, HomePageUI.POST_AUTHOR_BY_TEXT, postTitle, postAuthor);
    }

    public boolean isPostBodyDisplayed(String postTitle, String postBody) {
        return isElementDisplayed(driver, HomePageUI.POST_BODY_BY_TEXT, postTitle, postBody);
    }

    public PostDetailPageObject clickToPostByText(String postTitle) {
        waitForElementClickable(driver, HomePageUI.POST_TITLE_BY_TEXT, postTitle);
        clickToElement(driver, HomePageUI.POST_TITLE_BY_TEXT, postTitle);
        return PageGeneratorManager.getPostDetailPage(driver);
    }

    public void enterToPostSearchTextbox(String postTitleEdited) {
        waitForElementVisible(driver, HomePageUI.POST_SEARCH_TEXBOX);
        sendKeyToElement(driver, HomePageUI.POST_SEARCH_TEXBOX, postTitleEdited);

    }

    public SearchResultPageObject clickToPostSearchButton() {
        waitForElementClickable(driver, HomePageUI.POST_SEARCH_BUTTON);
        clickToElement(driver, HomePageUI.POST_SEARCH_BUTTON);
        return PageGeneratorManager.getSearchResultPage(driver);
    }

    public boolean isPostTitleUnDisplayed(String postTitleEdited) {
        overrideImlicitTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
        List<WebElement> elements = getElements(driver, HomePageUI.POST_TITLE_BY_TEXT, postTitleEdited);
        overrideImlicitTimeout(driver, GlobalConstants.LONG_TIMEOUT);

        if (elements.size() == 0) {
            return true;

        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;

        }
        return false;
    }
}
