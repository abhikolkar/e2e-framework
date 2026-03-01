package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URL;
import java.time.Duration;

public class BaseTest {

    protected AndroidDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();

        // ===== Platform Details =====
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");

        // ===== Real Device Details =====
        options.setDeviceName("Samsung S23 Ultra");
        options.setUdid("RZCXC065SAJ");

        // ===== Use Installed App (IMPORTANT) =====
        options.setAppPackage("com.swaglabsmobileapp");
        options.setAppActivity("com.swaglabsmobileapp.MainActivity");

        // ===== Stability Settings =====
        options.setAppWaitActivity("*");
        options.setAppWaitForLaunch(true);
        options.setAppWaitDuration(Duration.ofSeconds(60));

        options.setAutoGrantPermissions(true);

        // ===== Reset Strategy (VERY IMPORTANT) =====
        options.setNoReset(true);      // Do NOT uninstall app
        options.setFullReset(false);   // Do NOT reinstall app

        // ===== Create Driver =====
        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"),
                options
        );

        // ===== Implicit Wait =====
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        System.out.println("App Launched Successfully!");
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {

            try {
                driver.terminateApp("com.swaglabsmobileapp");
                System.out.println("App Terminated Successfully!");
            } catch (Exception e) {
                System.out.println("App already terminated.");
            }

            driver.quit();
            System.out.println("Session Closed Successfully!");
        }
    }
}
