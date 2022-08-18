package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import pageObjects.adminPageObjects.DashboardPageObject;
import pageObjects.userPageObjects.HomePageObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPagetUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitForAlertPresence(WebDriver driver) {
        WebDriverWait explicitWait;
        explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();

    }

    public void cancelAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    public String getAlertText(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    public void sendKeyToAlert(WebDriver driver, String textValue) {
        waitForAlertPresence(driver).sendKeys(textValue);
    }

    public void switchToWindowById(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }

    }

    public void switchToWindowByTitle(WebDriver driver, String windowTitle) {
        Set<String> allWindowns = driver.getWindowHandles();
        for (String runWindow : allWindowns) {
            driver.switchTo().window(runWindow);
            String currentTitle = driver.getTitle();
            if (currentTitle.equals(windowTitle)) {
                break;
            }
        }
    }

    public void closeAllWindowWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                driver.close();
            }
            driver.switchTo().window(parentID);
        }
    }

    private By getByLocator(String locatorType) {
        By by = null;
        if (locatorType.toLowerCase().startsWith("id")) {
            by = By.id(locatorType.substring(3).trim());
        } else if (locatorType.toLowerCase().startsWith("name")) {
            by = By.name(locatorType.substring(5).trim());
        } else if (locatorType.toLowerCase().startsWith("class")) {
            by = By.className(locatorType.substring(6).trim());
        } else if (locatorType.toLowerCase().startsWith("css")) {
            by = By.cssSelector(locatorType.substring(4).trim());
        } else if (locatorType.toLowerCase().startsWith("xpath")) {
            by = By.xpath(locatorType.substring(6).trim());
        } else {
            throw new RuntimeException("Locator type is not supported");
        }
        return by;

    }

    private String getDynamicLocator(String locatorType, String... dynamicValues) {
        locatorType = String.format(locatorType, (Object[]) dynamicValues);
        return locatorType;

    }

    public WebElement getElement(WebDriver driver, String locatorType) {
        return driver.findElement(getByLocator(locatorType));

    }

    public WebElement getElement(WebDriver driver, String locatorType, String... dynamicValues) {
        locatorType = getDynamicLocator(locatorType, dynamicValues);
        return driver.findElement(getByLocator(locatorType));

    }

    private List<WebElement> getElements(WebDriver driver, String locatorType) {
        return driver.findElements(getByLocator(locatorType));
    }

    public List<WebElement> getElements(WebDriver driver, String locatorType, String... dynamicValues) {
        locatorType = getDynamicLocator(locatorType, dynamicValues);
        return driver.findElements(getByLocator(locatorType));
    }

    public void clickToElement(WebDriver driver, String locatorType) {
        getElement(driver, locatorType).click();
    }

    public void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
        locatorType = getDynamicLocator(locatorType, dynamicValues);
        getElement(driver, locatorType).click();
    }

    public void sendKeyToElement(WebDriver driver, String locatorType, String textValue) {
        WebElement element = getElement(driver, locatorType);
        element.clear();
        element.sendKeys(textValue);
    }

    public void sendKeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
        locatorType = getDynamicLocator(locatorType, dynamicValues);
        WebElement element = getElement(driver, locatorType);
        element.clear();
        element.sendKeys(textValue);
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String dropdownlocatorType, String textItem) {
        Select select = new Select(getElement(driver, dropdownlocatorType));
        select.selectByVisibleText(textItem);

    }

    public void selectItemInDefaultDropdown(WebDriver driver, String dropdownlocatorType, String textItem,
                                            String... dynamicValues) {
        dropdownlocatorType = getDynamicLocator(dropdownlocatorType, dynamicValues);
        Select select = new Select(getElement(driver, dropdownlocatorType));
        select.selectByVisibleText(textItem);

    }

    public String getSelectedItemInDefaultDropdown(WebDriver driver, String dropdownlocatorType) {
        Select select = new Select(getElement(driver, dropdownlocatorType));
        return select.getFirstSelectedOption().getText();
    }

    public String getSelectedItemInDefaultDropdown(WebDriver driver, String dropdownlocatorType,
                                                   String... dynamicValues) {
        dropdownlocatorType = getDynamicLocator(dropdownlocatorType, dynamicValues);
        Select select = new Select(getElement(driver, dropdownlocatorType));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String dropdownlocatorType) {
        Select select = new Select(getElement(driver, dropdownlocatorType));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator,
                                           String expectedTextItem) {
        clickToElement(driver, expectedTextItem);
        sleepInSecond(1);
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
        List<WebElement> allItems = getElements(driver, childLocator);

        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedTextItem)) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scollInToView(true);", item);
                sleepInSecond(1);
                item.click();
                break;
            }

        }
    }

    public String getAttributeValue(WebDriver driver, String locatorType, String attributeName) {
        return getElement(driver, locatorType).getAttribute(attributeName);
    }

    public String getAttributeValue(WebDriver driver, String locatorType, String attributeName,
                                    String... dynamicValues) {
        locatorType = getDynamicLocator(locatorType, dynamicValues);
        return getElement(driver, locatorType).getAttribute(attributeName);
    }

    public String getElementText(WebDriver driver, String locatorType) {
        return getElement(driver, locatorType).getText();
    }

    public String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
        locatorType = getDynamicLocator(locatorType, dynamicValues);
        return getElement(driver, locatorType).getText();
    }

    public String getCssValue(WebDriver driver, String locatorType, String propertyName) {
        return getElement(driver, locatorType).getCssValue(propertyName);
    }

    public String convertRgbaToHex(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    public int getElementSize(WebDriver driver, String locatorType) {
        return getElements(driver, locatorType).size();
    }

    public int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
        locatorType = getDynamicLocator(locatorType, dynamicValues);
        return getElements(driver, locatorType).size();
    }

    public void checkToDefaultCheckBoxRadio(WebDriver driver, String locatorType) {
        WebElement element = getElement(driver, locatorType);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void checkToDefaultCheckBoxRadio(WebDriver driver, String locatorType, String... dynamicValues) {
        locatorType = getDynamicLocator(locatorType, dynamicValues);
        WebElement element = getElement(driver, locatorType);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToDefaultCheckBoxRadio(WebDriver driver, String locatorType) {
        WebElement element = getElement(driver, locatorType);
        if (element.isSelected()) {
            element.click();
        }
    }

    public void uncheckToDefaultCheckBoxRadio(WebDriver driver, String locatorType, String... dynamicValues) {
        locatorType = getDynamicLocator(locatorType, dynamicValues);
        WebElement element = getElement(driver, locatorType);
        if (element.isSelected()) {
            element.click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locatorType) {
        return getElement(driver, locatorType).isDisplayed();
    }

    public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
        locatorType = getDynamicLocator(locatorType, dynamicValues);
        return getElement(driver, locatorType).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, String locatorType) {
        return getElement(driver, locatorType).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locatorType, String... dynamicValues) {
        locatorType = getDynamicLocator(locatorType, dynamicValues);
        return getElement(driver, locatorType).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locatorType) {
        return getElement(driver, locatorType).isEnabled();
    }

    public void switchToFrameIframe(WebDriver driver, String locatorType) {
        driver.switchTo().frame(getElement(driver, locatorType));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void hoverMouseToElement(WebDriver driver, String locatorType) {
        Actions action = new Actions(driver);
        action.moveToElement(getElement(driver, locatorType)).perform();
    }

    public void pressKeyToElement(WebDriver driver, Keys key) {
        Actions action = new Actions(driver);
        action.sendKeys(key).perform();
    }

    public void clearValueInElementByDeleteKey(WebDriver driver, String locatorType) {
        getElement(driver, locatorType).sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));

    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(javaScript);
    }

    public void scrollToBottomPage(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void highlightElement(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getElement(driver, locatorType);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
                "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
                originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getElement(driver, locatorType));
    }

    public void scrollToElement(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locatorType));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locatorType, String value) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')",
                getElement(driver, locatorType));
    }

    public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
                getElement(driver, locatorType));
    }

    public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public String getElementValidationMessage(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
                getElement(driver, locatorType));
    }

    public boolean isImageLoaded(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                getElement(driver, locatorType));

        return status;

    }

    public boolean isImageLoaded(WebDriver driver, String locatorType, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        locatorType = getDynamicLocator(locatorType, dynamicValues);
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                getElement(driver, locatorType));
        return status;

    }

    public void waitForElementVisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
    }

    public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
        locatorType = getDynamicLocator(locatorType, dynamicValues);
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
    }

    public void waitForAllElementVisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
    }

    public void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
        locatorType = getDynamicLocator(locatorType, dynamicValues);
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
    }

    public void waitForElementClickable(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }

    public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
        locatorType = getDynamicLocator(locatorType, dynamicValues);
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }

    public void waitForElementInvisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
    }

    public void waitForAllElementInvisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getElements(driver, locatorType)));
    }

    public void waitForElementPresence(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByLocator(locatorType)));
    }

    public void waitForAllElementPresence(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locatorType)));
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void overrideImlicitTimeout(WebDriver driver, long timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    public Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setCookie(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(3);
    }

    public HomePageObject openUserSite(WebDriver driver, String userUrl) {
        openPageUrl(driver, userUrl);
        return PageGeneratorManager.getHomePage(driver);
    }

    public DashboardPageObject openAdminSite(WebDriver driver, String adminAppUrl) {
        openPageUrl(driver, adminAppUrl);
        return PageGeneratorManager.getDashboardPage(driver);
    }

}

