package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ProductsPage {

    private AndroidDriver driver;

    public ProductsPage(AndroidDriver driver) {
        this.driver = driver;
    }

    private By firstProduct = AppiumBy.xpath("(//android.widget.TextView[@content-desc='test-Item title'])[1]");

    // Products page title
    private By productsTitle = AppiumBy.accessibilityId("test-PRODUCTS");

    public boolean isProductsPageDisplayed() {
        return driver.findElement(productsTitle).isDisplayed();
    }

    public void selectFirstProduct() {
        driver.findElement(firstProduct).click();
    }
}

