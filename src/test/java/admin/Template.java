package admin;

import actions.commons.BaseTest;
import actions.environmentConfig.Environment;
import actions.reportConfig.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class Template extends BaseTest {
    private WebDriver driver;
    private Environment env;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        // get environment value from Maven command line
        String environmentName = System.getProperty("envMaven");
        ConfigFactory.setProperty("envOwner", environmentName);
        env = ConfigFactory.create(Environment.class);
        driver = getBrowserDriver(browserName, env.adminUrl());

    }

    @Test
    public void TC_01(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_01");
        ExtentTestManager.getTest().log(Status.INFO, "TC_Name - Step 01: xxx");

    }

    @Test
    public void TC_02(Method method) {
        ExtentTestManager.startTest(method.getName(), "TC_02");
        ExtentTestManager.getTest().log(Status.INFO, "TC_Name - Step 01: xxx");

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
