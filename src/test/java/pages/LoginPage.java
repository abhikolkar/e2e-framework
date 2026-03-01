package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginPage {

    private AndroidDriver driver;

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    // ===== LOCATORS =====
    private By usernameField = AppiumBy.accessibilityId("test-Username");
    private By passwordField = AppiumBy.accessibilityId("test-Password");
    private By loginButton   = AppiumBy.accessibilityId("test-LOGIN");
    private By errorMessage  = AppiumBy.xpath("//android.widget.TextView[contains(@text,'Username and password')]");

    // ===== ACTIONS =====
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // ===== VALIDATIONS =====
    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }
}
