package pageObjects.adminPageObjects;

import commons.BasePage;
import pageUIs.adminPageUIs.LoginPageUI;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void inputToUsernameTextbox(String username) {
        waitForElementVisible(driver, LoginPageUI.USER_NAME_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.USER_NAME_TEXTBOX, username);
    }

    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public void clickToLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
        sleepInSecond(2);
    }

    public boolean errorMessageIsDisplayed(String errorMessage) {
        waitForElementVisible(driver, LoginPageUI.LOGIN_ERROR_MESSAGE);
        System.out.println("text: " + getAttributeValue(driver, LoginPageUI.LOGIN_ERROR_MESSAGE, "innerText"));
        return getAttributeValue(driver, LoginPageUI.LOGIN_ERROR_MESSAGE, "innerText").equals(errorMessage);
    }
}
