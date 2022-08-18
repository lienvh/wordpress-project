package admin.posts;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import commons.PageGeneratorManager;
import environmentConfig.Environment;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.adminPageObjects.AllPostsPageObject;
import pageObjects.adminPageObjects.DashboardPageObject;
import pageObjects.adminPageObjects.LoginPageObject;
import reportConfig.ExtentTestManager;

import java.lang.reflect.Method;

public class PostsListTest extends BaseTest {
    private WebDriver driver;
    private Environment env;
    private DashboardPageObject dashboardPage;
    private AllPostsPageObject allPostsPage;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        // get environment value from Maven command line
        String environmentName = System.getProperty("envMaven");
        ConfigFactory.setProperty("envOwner", environmentName);
        env = ConfigFactory.create(Environment.class);

        String username = "automationfc";
        String password = "12345678a";

        driver = getBrowserDriver(browserName, env.adminUrl());
        LoginPageObject loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.inputToUsernameTextbox(username);
        loginPage.inputToPasswordTextbox(password);
        loginPage.clickToLoginButton();
        dashboardPage = PageGeneratorManager.getDashboardPage(driver);
        Assert.assertTrue(dashboardPage.pageTitleIsDisplayed());

    }

   @Test
    public void Post_List_01_Paging(Method method) {
        ExtentTestManager.startTest(method.getName(), "Post_List_01_Paging");

        ExtentTestManager.getTest().log(Status.INFO, "Paging - Step_01: Click to Posts menu link and navigate to admin AllPosts page");
        allPostsPage = dashboardPage.clickToPostsMenuLink();

        ExtentTestManager.getTest().log(Status.INFO, "Paging - Step_02: Verify total records displayed on the screen match the database");
        Assert.assertTrue(allPostsPage.isTotalRecordMatchDatabaseByPostStatus("all"));

        ExtentTestManager.getTest().log(Status.INFO, "Paging - Step_03: Verify total records published displayed on the screen match the database");
        Assert.assertTrue(allPostsPage.isTotalRecordMatchDatabaseByPostStatus("publish"));

        ExtentTestManager.getTest().log(Status.INFO, "Paging - Step_04: Verify total records draft displayed on the screen match the database");
        Assert.assertTrue(allPostsPage.isTotalRecordMatchDatabaseByPostStatus("draft"));

        ExtentTestManager.getTest().log(Status.INFO, "Paging - Step_05: Verify total records moved to trash displayed on the screen match the database");
        Assert.assertTrue(allPostsPage.isTotalRecordMatchDatabaseByPostStatus("trash"));
    }

    @Test
    public void Post_List_02(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_02");
        ExtentTestManager.getTest().log(Status.INFO, "TC_Name - Step 01: xxx");

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
