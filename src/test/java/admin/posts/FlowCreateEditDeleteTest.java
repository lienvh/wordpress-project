package admin.posts;

import commons.BaseTest;
import commons.DataHelper;
import commons.PageGeneratorManager;
import environmentConfig.Environment;
import pageObjects.adminPageObjects.AddNewPostPageObject;
import pageObjects.adminPageObjects.AllPostsPageObject;
import pageObjects.adminPageObjects.DashboardPageObject;
import pageObjects.adminPageObjects.LoginPageObject;
import pageObjects.userPageObjects.HomePageObject;
import pageObjects.userPageObjects.PostDetailPageObject;
import pageObjects.userPageObjects.SearchResultPageObject;
import reportConfig.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class FlowCreateEditDeleteTest extends BaseTest {
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        // get environment value from Maven command line
        String environmentName = System.getProperty("envMaven");
        ConfigFactory.setProperty("envOwner", environmentName);
        env = ConfigFactory.create(Environment.class);
        DataHelper dataFaker = DataHelper.getData();

        String username = "automationfc";
        String password = "12345678a";
        postTitle = "automationfc title_" + dataFaker.getRandomNumber();
        postBody = "automationfc body_" + dataFaker.getRandomNumber();
        postAuthor = "automationfc";
        postTitleEdited = "title_edited_" + dataFaker.getRandomNumber();
        postBodyEdited = "body_edited_" + dataFaker.getRandomNumber();

        postPublishedMessage = "Post published.";
        postUpdatedMessage = "Post updated.";
        movedToTrashMessage = "1 post moved to the Trash. ";
        notFoundSearchResultMessage = "No posts found.";

        driver = getBrowserDriver(browserName, env.adminUrl());
        LoginPageObject loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.inputToUsernameTextbox(username);
        loginPage.inputToPasswordTextbox(password);
        loginPage.clickToLoginButton();
        dashboardPage = PageGeneratorManager.getDashboardPage(driver);
        Assert.assertTrue(dashboardPage.pageTitleIsDisplayed());

    }

    @Test
    public void Post_01_Add_New(Method method) {
        ExtentTestManager.startTest(method.getName(), "Post_01_Add_New");

        ExtentTestManager.getTest().log(Status.INFO, "Add_New - Step_01: Click to Posts menu link and navigate to admin AllPosts page");
        allPostsPage = dashboardPage.clickToPostsMenuLink();

        ExtentTestManager.getTest().log(Status.INFO, "Add_New - Step_02: get AllPost page URL");
        allPostsUrl = allPostsPage.getPagetUrl(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Add_New - Step_03: Click to Add New button and navigate to admin Add New Post");
        addNewOrEditPostPage = allPostsPage.clickToAddNewButton();

        ExtentTestManager.getTest().log(Status.INFO, "Add_New - Step_04: Enter to post Title with value " + postTitle);
        addNewOrEditPostPage.inputToPostTitle(postTitle);

        ExtentTestManager.getTest().log(Status.INFO, "Add_New - Step_05: Click to post Body");
        addNewOrEditPostPage.clickToPostBody();

        ExtentTestManager.getTest().log(Status.INFO, "Add_New - Step_06: Enter to post Body with value " + postBody);
        addNewOrEditPostPage.inputToPostBody(postBody);

        ExtentTestManager.getTest().log(Status.INFO, "Add_New - Step_07: Click to Pre-Publish button");
        addNewOrEditPostPage.clickToPrePublishOrUpdateButton();

        ExtentTestManager.getTest().log(Status.INFO, "Add_New - Step_08: Click to Publish button");
        addNewOrEditPostPage.clickToPublishButton();

        ExtentTestManager.getTest().log(Status.INFO, "Add_New - Step_09: Verify 'Post Published.' message is displayed");
        Assert.assertTrue(addNewOrEditPostPage.isPostPublishMessageDisplayed(postPublishedMessage));

    }

    @Test
    public void Post_02_Search_Created_Post_At_Admin_Site(Method method) {
        ExtentTestManager.startTest(method.getName(), "Post_02_Search_Created_Post_At_Admin_Site");

        ExtentTestManager.getTest().log(Status.INFO, "Search_Post - Step_01: open admin AllPosts page");
        allPostsPage = addNewOrEditPostPage.openAllPostsUrl(allPostsUrl);

        ExtentTestManager.getTest().log(Status.INFO, "Search_Post - Step_02: Enter to Search text box with value " + postTitle);
        allPostsPage.inputToSearchTextbox(postTitle);

        ExtentTestManager.getTest().log(Status.INFO, "Search_Post - Step_03: Click to Search button");
        allPostsPage.clickToSearchButton();

        ExtentTestManager.getTest().log(Status.INFO, "Search_Post - Step_04: Verify results table contains the created post title");
        Assert.assertTrue(allPostsPage.isSearchTableContainsPostInfoByID("title", postTitle));

        ExtentTestManager.getTest().log(Status.INFO, "Search_Post - Step_05: Verify results table contains the created post author");
        Assert.assertTrue(allPostsPage.isSearchTableContainsPostInfoByID("author", postAuthor));

    }

    @Test
    public void Post_03_Verify_Created_Post_At_User_Site(Method method) {
        ExtentTestManager.startTest(method.getName(), "Post_03_Search_Created_Post_At_User_Site");

        ExtentTestManager.getTest().log(Status.INFO, "Verify_Post - Step_01: Open User site");
        homePage = allPostsPage.openUserSite(driver, env.userUrl());

        ExtentTestManager.getTest().log(Status.INFO, "Verify_Post - Step_02: Verify Post info is displayed at user Home page");
        Assert.assertTrue(homePage.isPostTitleDisplayed(postTitle));
        Assert.assertTrue(homePage.isPostCreatedDateDisplayed(postTitle, getCurrentDate()));
        Assert.assertTrue(homePage.isPostAuthorDisplayed(postTitle, postAuthor));
        Assert.assertTrue(homePage.isPostBodyDisplayed(postTitle, postBody));

        ExtentTestManager.getTest().log(Status.INFO, "Verify_Post - Step_03: Click to the Post Title and navigate to Post Detail page");
        postDetail = homePage.clickToPostByText(postTitle);

        ExtentTestManager.getTest().log(Status.INFO, "Verify_Post - Step_04: Verify Post info is displayed at user Post Detail page");
        Assert.assertTrue(postDetail.isPostTitleDisplayed(postTitle));
        Assert.assertTrue(postDetail.isPostCreatedDateDisplayed(postTitle, getCurrentDate()));
        Assert.assertTrue(postDetail.isPostAuthorDisplayed(postTitle, postAuthor));
        Assert.assertTrue(postDetail.isPostBodyDisplayed(postTitle, postBody));

    }

    @Test
    public void Post_04_Edit_Post(Method method) {
        ExtentTestManager.startTest(method.getName(), "Post_04_Edit_Post");

        ExtentTestManager.getTest().log(Status.INFO, "Edit_Post - Step_01: Open admin site");
        dashboardPage = postDetail.openAdminSite(driver, env.adminUrl());

        ExtentTestManager.getTest().log(Status.INFO, "Edit_Post - Step_02: Click to 'Posts' menu link and navigate to admin AllPosts page");
        allPostsPage = dashboardPage.clickToPostsMenuLink();

        ExtentTestManager.getTest().log(Status.INFO, "Edit_Post - Step_03: Input to Search text box with value " + postTitle);
        allPostsPage.inputToSearchTextbox(postTitle);

        ExtentTestManager.getTest().log(Status.INFO, "Edit_Post - Step_04: Click to Search button");
        allPostsPage.clickToSearchButton();

        ExtentTestManager.getTest().log(Status.INFO, "Edit_Post - Step_05: Click to post title and navigate to admin Edit post page");
        addNewOrEditPostPage = allPostsPage.clickToPostTitle(postTitle);

        ExtentTestManager.getTest().log(Status.INFO, "Edit_Post - Step_06: Input to post title with value " + postTitleEdited);
        addNewOrEditPostPage.inputToPostTitle(postTitleEdited);

        ExtentTestManager.getTest().log(Status.INFO, "Edit_Post - Step_07: Input to post body with value " + postBodyEdited);
        addNewOrEditPostPage.inputToPostBody(postBodyEdited);

        ExtentTestManager.getTest().log(Status.INFO, "Edit_Post - Step_08: Click to Update button");
        addNewOrEditPostPage.clickToPrePublishOrUpdateButton();

        ExtentTestManager.getTest().log(Status.INFO, "Edit_Post - Step_09: Verify 'Post updated' message is displayed");
        Assert.assertTrue(addNewOrEditPostPage.isPostPublishMessageDisplayed(postUpdatedMessage));
    }

    @Test
    public void Post_05_Search_Edited_Post_At_Admin_site(Method method) {
        ExtentTestManager.startTest(method.getName(), "Post_05_Search_Edited_Post_At_Admin_site");

        ExtentTestManager.getTest().log(Status.INFO, "Edit_Post - Step_01: open admin AllPosts page");
        allPostsPage = addNewOrEditPostPage.openAllPostsUrl(allPostsUrl);

        ExtentTestManager.getTest().log(Status.INFO, "Edit_Post - Step_02: Input to Search text box with value " + postTitleEdited);
        allPostsPage.inputToSearchTextbox(postTitleEdited);

        ExtentTestManager.getTest().log(Status.INFO, "Edit_Post - Step_03: Click to Search button");
        allPostsPage.clickToSearchButton();

        ExtentTestManager.getTest().log(Status.INFO, "Edit_Post - Step_04: Verify results table contains the edited post title");
        Assert.assertTrue(allPostsPage.isSearchTableContainsPostInfoByID("title", postTitleEdited));

        ExtentTestManager.getTest().log(Status.INFO, "Edit_Post - Step_05: Verify results table contains the edited post author");
        Assert.assertTrue(allPostsPage.isSearchTableContainsPostInfoByID("author", postAuthor));
    }

    @Test
    public void Post_06_Verify_Edited_Post_At_User_Site(Method method) {
        ExtentTestManager.startTest(method.getName(), "Post_06_Search_Edited_Post_At_User_Site");

        ExtentTestManager.getTest().log(Status.INFO, "Verify_Post - Step_01: Open user Home page");
        homePage = allPostsPage.openUserSite(driver, env.userUrl());

        ExtentTestManager.getTest().log(Status.INFO, "Verify_Post - Step_02: Verify edited post title is displayed at user Home page");
        Assert.assertTrue(homePage.isPostTitleDisplayed(postTitleEdited));

        ExtentTestManager.getTest().log(Status.INFO, "Verify_Post - Step_03: Verify post created date is displayed at user Home page");
        Assert.assertTrue(homePage.isPostCreatedDateDisplayed(postTitleEdited, getCurrentDate()));

        ExtentTestManager.getTest().log(Status.INFO, "Verify_Post - Step_04: Verify edited post author is displayed at user Home page");
        Assert.assertTrue(homePage.isPostAuthorDisplayed(postTitleEdited, postAuthor));

        ExtentTestManager.getTest().log(Status.INFO, "Verify_Post - Step_05: Verify edited post body is displayed at user Home page");
        Assert.assertTrue(homePage.isPostBodyDisplayed(postTitleEdited, postBodyEdited));

        ExtentTestManager.getTest().log(Status.INFO, "Verify_Post - Step_06: Click to the Post Title and navigate to Post Detail page");
        postDetail = homePage.clickToPostByText(postTitleEdited);

        ExtentTestManager.getTest().log(Status.INFO, "Verify_Post - Step_07: Verify edited post title is displayed at user Post Detail page");
        Assert.assertTrue(postDetail.isPostTitleDisplayed(postTitleEdited));

        ExtentTestManager.getTest().log(Status.INFO, "Verify_Post - Step_08: Verify post created date is displayed at user Post Detail page");
        Assert.assertTrue(postDetail.isPostCreatedDateDisplayed(postTitleEdited, getCurrentDate()));

        ExtentTestManager.getTest().log(Status.INFO, "Verify_Post - Step_09: Verify edited post author is displayed at user Post Detail page");
        Assert.assertTrue(postDetail.isPostAuthorDisplayed(postTitleEdited, postAuthor));

        ExtentTestManager.getTest().log(Status.INFO, "Verify_Post - Step_10: Verify edited post body is displayed at user Post Detail page");
        Assert.assertTrue(postDetail.isPostBodyDisplayed(postTitleEdited, postBodyEdited));

    }

    @Test
    public void Post_07_Delete_Post(Method method) {
        ExtentTestManager.startTest(method.getName(), "Post_07_Delete_Post");

        ExtentTestManager.getTest().log(Status.INFO, "Delete_Post - Step_01: Open admin site");
        dashboardPage = postDetail.openAdminSite(driver, env.adminUrl());

        ExtentTestManager.getTest().log(Status.INFO, "Delete_Post - Step_02: Click to 'Posts' menu link and navigate to admin AllPosts page");
        allPostsPage = dashboardPage.clickToPostsMenuLink();

        ExtentTestManager.getTest().log(Status.INFO, "Delete_Post - Step_03: Check to checkbox on a row contains post title '" + postTitleEdited + "'");
        allPostsPage.checkToCheckboxByPostTitle(postTitleEdited);

        ExtentTestManager.getTest().log(Status.INFO, "Delete_Post - Step_04: Select 'Move to Trash' option on Action dropdown");
        allPostsPage.selectItemInActionDropdown("Move to Trash");

        ExtentTestManager.getTest().log(Status.INFO, "Delete_Post - Step_05: Click to 'Apply' button");
        allPostsPage.clickToApplyButton();

        ExtentTestManager.getTest().log(Status.INFO, "Delete_Post - Step_06: Verify '1 post moved to the Trash' message is displayed");
        Assert.assertTrue(allPostsPage.isMovedTheTrashMessageDisplayed(movedToTrashMessage));
    }

    @Test
    public void Post_08_Search_Deleted_Post_At_Admin_Site(Method method) {
        ExtentTestManager.startTest(method.getName(), "Post_08_Search_Deleted_Post_At_Admin_Site");

        ExtentTestManager.getTest().log(Status.INFO, "Search_Post - Step_01: Input to Search text box with value " + postTitleEdited);
        allPostsPage.inputToSearchTextbox(postTitleEdited);

        ExtentTestManager.getTest().log(Status.INFO, "Search_Post - Step_02: Click to Search button");
        allPostsPage.clickToSearchButton();

        ExtentTestManager.getTest().log(Status.INFO, "Search_Post - Step_03: Verify post moved to trash is undisplayed on AllPosts page ");
        Assert.assertTrue(allPostsPage.isNotFoundSearchResultMessageDisplayed(notFoundSearchResultMessage));
    }

    @Test
    public void Post_09_Search_Deleted_Post_At_User_Site(Method method) {
        ExtentTestManager.startTest(method.getName(), "Post_09_Search_Deleted_Post_At_User_Site");

        ExtentTestManager.getTest().log(Status.INFO, "Search_Post - Step_01: Open User site");
        homePage = allPostsPage.openUserSite(driver, env.userUrl());

        ExtentTestManager.getTest().log(Status.INFO, "Search_Post - Step_02: Verify post moved to trash is unDisplayed at user Home page");
        Assert.assertTrue(homePage.isPostTitleUnDisplayed(postTitleEdited));

        ExtentTestManager.getTest().log(Status.INFO, "Search_Post - Step_03: Input to post search text box with value " + postTitleEdited);
        homePage.enterToPostSearchTextbox(postTitleEdited);

        ExtentTestManager.getTest().log(Status.INFO, "Search_Post - Step_04: Click to post search button");
        SearchResultPageObject searchResultPage = homePage.clickToPostSearchButton();

        ExtentTestManager.getTest().log(Status.INFO, "Search_Post - Step_05: Verify post moved to trash is undisplayed");
        Assert.assertTrue(searchResultPage.isNothingSearchResultTitlDisplayed());

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }

    private WebDriver driver;
    private Environment env;

    private DashboardPageObject dashboardPage;
    private AllPostsPageObject allPostsPage;
    private AddNewPostPageObject addNewOrEditPostPage;
    private HomePageObject homePage;
    private PostDetailPageObject postDetail;
    String postTitle, postBody, postPublishedMessage, allPostsUrl, postAuthor, postTitleEdited, postBodyEdited, postUpdatedMessage, movedToTrashMessage, notFoundSearchResultMessage;
}
