package pageObjects.adminPageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import jdbcConnection.MySqlConnection;
import org.openqa.selenium.WebDriver;
import pageUIs.adminPageUIs.AllPostsPageUI;

import java.sql.*;

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

    public void inputToSearchTextbox(String postTitle) {
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
        waitForElementVisible(driver, AllPostsPageUI.VALUE_EACH_COLUMN_BY_HEADER_INDEX, String.valueOf(headerIndex), value);
        return isElementDisplayed(driver, AllPostsPageUI.VALUE_EACH_COLUMN_BY_HEADER_INDEX, String.valueOf(headerIndex), value);
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

    public int getTotalRecordByPostStatus(String postStatus) {
        waitForElementVisible(driver, AllPostsPageUI.TOTAL_RECORD_BY_POST_STATUS, postStatus);
        String totalRecoredText = getElementText(driver, AllPostsPageUI.TOTAL_RECORD_BY_POST_STATUS, postStatus);
        return Integer.parseInt(totalRecoredText.replace("(", "").replace(")", ""));
    }

    public int getTotalRecordByPostStatusInDatabase(String postStatus) {
        Connection conn = MySqlConnection.getMySqlConnection();
        int numberOfRows;
        ResultSet rs;
        try {
            if (postStatus.equalsIgnoreCase("all")) {
                Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String sql = "SELECT * FROM automationfc.wp_posts WHERE post_type = 'post' AND post_status IN('publish','draft') ;";
                rs = statement.executeQuery(sql);
            } else {
                String sql = "SELECT * FROM automationfc.wp_posts WHERE post_type = 'post' AND post_status =? ;";
                PreparedStatement pstm = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                pstm.setString(1, postStatus);
                rs = pstm.executeQuery();
            }
            rs.last();
            numberOfRows = rs.getRow();
            conn.close();
            return numberOfRows;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isTotalRecordMatchDatabaseByPostStatus(String postStatus) {
        int totalRecordDisplayed = getTotalRecordByPostStatus(postStatus);
        int totalRecordQueried = getTotalRecordByPostStatusInDatabase(postStatus);
       // System.out.println("totalRecordDisplayed: " + totalRecordDisplayed);
       // System.out.println("totalRecordQueried: " + totalRecordQueried);
        if (totalRecordDisplayed == totalRecordQueried) {
            return true;
        }
        return false;
    }
}
