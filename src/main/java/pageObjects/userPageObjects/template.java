package pageObjects.userPageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class template extends BasePage {
    private WebDriver driver;

    public template(WebDriver driver) {
        this.driver = driver;
    }
}
