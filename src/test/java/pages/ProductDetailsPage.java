package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ProductDetailsPage {

    private AndroidDriver driver;
    private WebDriverWait wait;

    public ProductDetailsPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ===== LOCATORS =====

    private By productName = AppiumBy.xpath(
            "//android.widget.TextView[@text='Sauce Labs Backpack']"
    );

    private By productPrice = AppiumBy.xpath(
            "//android.widget.TextView[@content-desc='test-Price']"
    );

    private By addToCartButton = AppiumBy.accessibilityId("test-ADD TO CART");

    private By cartBadge = AppiumBy.xpath("//android.widget.TextView[@text='1']");

    private By cartIcon = AppiumBy.accessibilityId("test-Cart");

    private By quantityLocator = AppiumBy.xpath(
            "//android.view.ViewGroup[@content-desc='test-Amount']//android.widget.TextView"
    );


    // ===== PRODUCT VALIDATIONS =====

    public boolean isProductNameDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productName));
        return driver.findElement(productName).isDisplayed();
    }

    public boolean isProductPriceDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productPrice));
        return driver.findElement(productPrice).isDisplayed();
    }


    // ===== ACTIONS =====

    public void clickAddToCart() {
        // Scroll until Add To Cart button is visible
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                                ".scrollIntoView(new UiSelector().description(\"test-ADD TO CART\"));"
                )
        );

        // Click the button
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton))
                .click();
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        driver.findElement(cartIcon).click();
    }


    // ===== CART VALIDATIONS =====

    public boolean isCartBadgeUpdated(String expectedCount) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));

        String badgeCount = driver.findElement(cartBadge).getText();

        return badgeCount.equals(expectedCount);
    }

    public boolean isCorrectProductDisplayedInCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productName));
        return driver.findElement(productName).isDisplayed();
    }

    public boolean isQuantityCorrect() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantityLocator));
        String quantity = driver.findElement(quantityLocator).getText();
        return quantity.equals("1");
    }
}
