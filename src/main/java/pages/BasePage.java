package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Click using WebElement (with scroll)
    public void click(WebElement element) {
        scrollIntoView(element);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            jsClick(element); // fallback
        }
    }

    // Click using By locator (with wait and scroll)
    protected void click(By locator) {
        WebElement element = waitForVisibility(locator);
        scrollIntoView(element);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            jsClick(element); // fallback
        }
    }

    // JavaScript click
    protected void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    // JavaScript click with By
    protected void jsClick(By locator) {
        WebElement element = waitForVisibility(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    // Scroll into view
    protected void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Wait for visibility
    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for clickability
    protected WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Send keys safely
    protected void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text == null ? "" : text);
    }

    // Get text safely
    protected String getText(By locator) {
        return waitForVisibility(locator).getText();
    }

    // Navigate to a URL
    public void navigateTo(String url) {
        driver.get(url);
    }

    // Check if element is visible
    protected boolean isElementVisible(By locator) {
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Chọn option trong select theo value
    protected void selectByValue(By locator, String value) {
        WebElement element = waitForVisibility(locator);
        Select select = new Select(element);
        select.selectByValue(value);
    }
}
