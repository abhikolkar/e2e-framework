package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginTest extends BaseTest {

    // ==============================
    // POSITIVE SCENARIO
    // ==============================

    @Test
    public void validLoginTest() {

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        // Validation
        Assert.assertTrue(
                productsPage.isProductsPageDisplayed(),
                "User did not land on Products page!"
        );
    }

    // ==============================
    // NEGATIVE SCENARIO
    // ==============================

    @Test
    public void invalidLoginTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("invalid_user", "wrong_password");

        // Validation
        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Error message not displayed for invalid login!"
        );
    }
}
