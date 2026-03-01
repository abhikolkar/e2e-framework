package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;

import java.time.Duration;

public class CheckoutPage {

    private AppiumDriver driver;
    private WebDriverWait wait;

    public CheckoutPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Step 1 Button
    private By checkoutBtn = AppiumBy.accessibilityId("test-CHECKOUT");

    // Checkout Information Fields
    private By firstNameField = AppiumBy.accessibilityId("test-First Name");
    private By lastNameField = AppiumBy.accessibilityId("test-Last Name");
    private By postalCodeField = AppiumBy.accessibilityId("test-Zip/Postal Code");

    private By continueBtn = AppiumBy.accessibilityId("test-CONTINUE");

    // Overview Page
    private By finishBtn = AppiumBy.accessibilityId("test-FINISH");

    // Confirmation
    private By confirmationTitle = AppiumBy.xpath(
            "//android.widget.TextView[@text='CHECKOUT: COMPLETE!']"
    );

    private By successMessage = AppiumBy.xpath(
            "//android.widget.TextView[contains(@text,'THANK YOU')]"
    );



    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
    }

    public void enterCheckoutDetails(String firstName, String lastName, String postalCode) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(postalCodeField).sendKeys(postalCode);

        driver.findElement(continueBtn).click();
    }

    public void clickFinish() {

        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().description(\"test-FINISH\"));"
        ));

        wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
    }
}


