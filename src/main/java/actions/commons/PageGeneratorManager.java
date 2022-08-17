package actions.commons;

import actions.pageObjects.adminPageObjects.AddNewPostPageObject;
import actions.pageObjects.adminPageObjects.AllPostsPageObject;
import actions.pageObjects.adminPageObjects.DashboardPageObject;
import actions.pageObjects.adminPageObjects.LoginPageObject;
import actions.pageObjects.userPageObjects.HomePageObject;
import actions.pageObjects.userPageObjects.PostDetailPageObject;
import actions.pageObjects.userPageObjects.SearchResultPageObject;
import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    public static LoginPageObject getLoginPage(WebDriver driver) {
        return new LoginPageObject(driver);
    }

    public static DashboardPageObject getDashboardPage(WebDriver driver) {
        return new DashboardPageObject(driver);
    }

    public static AllPostsPageObject getAllPostsPage(WebDriver driver) {
        return new AllPostsPageObject(driver);
    }

    public static AddNewPostPageObject getAddNewPostPage(WebDriver driver) {
        return new AddNewPostPageObject(driver);
    }

    public static HomePageObject getHomePage(WebDriver driver) {
        return new HomePageObject(driver);
    }

    public static PostDetailPageObject getPostDetailPage(WebDriver driver) {
        return new PostDetailPageObject(driver);
    }

    public static SearchResultPageObject getSearchResultPage(WebDriver driver) {
        return new SearchResultPageObject(driver);
    }
}
