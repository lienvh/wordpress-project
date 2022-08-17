package admin;

import actions.commons.BaseTest;
import actions.commons.PageGeneratorManager;
import actions.environmentConfig.Environment;
import actions.pageObjects.adminPageObjects.DashboardPageObject;
import actions.pageObjects.adminPageObjects.LoginPageObject;
import actions.reportConfig.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class LoginTest extends BaseTest {
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        // get environment value from Maven command line
        String environmentName = System.getProperty("envMaven");
        ConfigFactory.setProperty("envOwner", environmentName);
        Environment env = ConfigFactory.create(Environment.class);

        registeredUsername = "automationfc";
        registeredPassword = "12345678a";
        unRegisteredUsername = "automation";
        invalidPassword = "12345678";

        emptyAccountMessage = "Error: The username field is empty.\nError: The password field is empty.\n";
        unregisteredAccountMessage = "Error: The username automation is not registered on this site. If you are unsure of your username, try your email address instead.\n";
        incorrectAccountMessage = "Error: The password you entered for the username automationfc is incorrect. Lost your password?\n";

        driver = getBrowserDriver(browserName, env.adminUrl());
        loginPage = PageGeneratorManager.getLoginPage(driver);
    }

    @Test
    public void Login_01_Blank_Username_Password(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login_01_Empty_Username_Password");

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: leave blank Username text box");
        loginPage.inputToUsernameTextbox("");

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: leave blank password text box");
        loginPage.inputToPasswordTextbox("");

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: click to Login button");
        loginPage.clickToLoginButton();

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04: verify login error message is displayed");
        Assert.assertTrue(loginPage.errorMessageIsDisplayed(emptyAccountMessage));
    }

    @Test
    public void Login_02_Unregistered_Username(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login_02_Unregistered_Username");

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: input to Username text box with value " + unRegisteredUsername);
        loginPage.inputToUsernameTextbox(unRegisteredUsername);

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: input to Password text box with value " + registeredPassword);
        loginPage.inputToPasswordTextbox(registeredPassword);

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: click to Login button");
        loginPage.clickToLoginButton();

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04: verify login error message is displayed");
        Assert.assertTrue(loginPage.errorMessageIsDisplayed(unregisteredAccountMessage));
    }

    @Test
    public void Login_03_Invalid_Password(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login_03_Invalid_Password");

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: input to Username text box with value " + registeredUsername);
        loginPage.inputToUsernameTextbox(registeredUsername);

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: input to Password text box with value " + invalidPassword);
        loginPage.inputToPasswordTextbox(invalidPassword);

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: click to Login button");
        loginPage.clickToLoginButton();

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04: verify login error message is displayed");
        Assert.assertTrue(loginPage.errorMessageIsDisplayed(incorrectAccountMessage));
    }

    @Test
    public void Login_04_Valid_Username_Password(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login_04_Valid_Username_Password");

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: input to Username text box with value " + registeredUsername);
        loginPage.inputToUsernameTextbox(registeredUsername);

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: input to Password text box with value " + registeredPassword);
        loginPage.inputToPasswordTextbox(registeredPassword);

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: click to Login button");
        loginPage.clickToLoginButton();

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04: verify Dashboard page is displayed");
        DashboardPageObject dashboardPage = PageGeneratorManager.getDashboardPage(driver);
        Assert.assertTrue(dashboardPage.pageTitleIsDisplayed());

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }

    private WebDriver driver;
    private LoginPageObject loginPage;

    private String registeredUsername, registeredPassword, unRegisteredUsername, invalidPassword, emptyAccountMessage, unregisteredAccountMessage, incorrectAccountMessage;

}
