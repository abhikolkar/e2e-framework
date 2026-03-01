package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import pages.ProductDetailsPage;
import pages.CartPage;
import pages.CheckoutPage;

public class ProductTest extends BaseTest {

    @Test
    public void verifyProductSelectionFlow() {

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailsPage detailsPage = new ProductDetailsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        // ===============================
        // Step 1: Login
        // ===============================
        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(
                productsPage.isProductsPageDisplayed(),
                "FAIL: User did not land on Products page after login!"
        );

        // ===============================
        // Step 2: Select Product
        // ===============================
        productsPage.selectFirstProduct();

        Assert.assertTrue(
                detailsPage.isProductNameDisplayed(),
                "FAIL: Product name is not displayed!"
        );

        Assert.assertTrue(
                detailsPage.isProductPriceDisplayed(),
                "FAIL: Product price is not displayed!"
        );

        System.out.println("Step 2 Passed: Product details validated");

        // ===============================
        // Step 3: Add To Cart
        // ===============================
        detailsPage.clickAddToCart();

        Assert.assertTrue(
                detailsPage.isCartBadgeUpdated("1"),
                "FAIL: Cart badge was not updated to 1!"
        );

        System.out.println("Cart badge updated successfully");

        // Open Cart
        detailsPage.openCart();

        // Validate Product In Cart
        Assert.assertTrue(
                cartPage.isCorrectProductDisplayed("Sauce Labs Backpack")
        );

        // Validate Quantity
        Assert.assertEquals(
                cartPage.getProductQuantity(),
                "1",
                "FAIL: Product quantity is incorrect!"
        );

        System.out.println("Step 3 Passed: Add to cart validation successful!");

        // ===============================
        // Step 4: Checkout Flow
        // ===============================

        // Click Checkout
        checkoutPage.clickCheckout();

        // Enter Checkout Details
        checkoutPage.enterCheckoutDetails("Abhishek", "Kolkar", "400001");

        // Finish Order
        checkoutPage.clickFinish();

        // Validate Success Message
        Assert.assertTrue(
                checkoutPage.isSuccessMessageDisplayed(),
                "FAIL: Success message not displayed!"
        );

        System.out.println("Step 4 Passed: Checkout completed successfully!");
    }
}
