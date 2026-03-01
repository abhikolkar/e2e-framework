package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    private AndroidDriver driver;

    public CartPage(AndroidDriver driver) {
        this.driver = driver;
    }

    private By cartItemTitle  = AppiumBy.xpath(
            "//android.widget.TextView[@text=\"Sauce Labs Backpack\"]"
    );

    private By cartQuantity = AppiumBy.accessibilityId("test-Item quantity");

    public boolean isCorrectProductDisplayed(String expectedName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemTitle));

        String actualName = driver.findElement(cartItemTitle).getText();

        return actualName.trim().equals(expectedName.trim());
    }

    public String getProductQuantity() {

        By quantityText = AppiumBy.xpath(
                "//android.view.ViewGroup[@content-desc='test-Amount']" +
                        "/android.widget.TextView"
        );

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantityText));

        return driver.findElement(quantityText).getText();
    }
}
