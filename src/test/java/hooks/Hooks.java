package hooks;

import core.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.PermissionHandler;
import utils.ScreenshotUtils;

public class Hooks {

    @Before
    public void beforeScenario() {
        DriverManager.initDriver();
        PermissionHandler.handleAndroidFirstLaunchDialogs();
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            if (DriverManager.hasDriver()) {
                AppiumDriver driver = DriverManager.getDriver();

                boolean screenshotAlways = Boolean.parseBoolean(
                        System.getProperty("screenshotAlways", "false")
                );

                if (scenario.isFailed() || screenshotAlways) {
                    ScreenshotUtils.ScreenshotResult shot =
                            ScreenshotUtils.capture(driver, scenario.getName());

                    // Attach to Cucumber report (works in Cucumber 7)
                    scenario.attach(shot.getBytes(), "image/png", "screenshot");

                    // Also log the file path (handy for CI artifacts)
                    scenario.log("Screenshot saved at: " + shot.getPath());
                }
            }
        } finally {
            // Best-effort app terminate (Android only here)
            if (DriverManager.hasDriver()) {
                try {
                    AppiumDriver driver = DriverManager.getDriver();
                    if (driver instanceof AndroidDriver) {
                        ((AndroidDriver) driver).terminateApp("com.wizzair.WizzAirApp");
                    }
                } catch (Exception ignored) {
                    // ignore
                }
            }

            DriverManager.quitDriver();
        }
    }
}