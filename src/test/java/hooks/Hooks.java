package hooks;

import core.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.PermissionHandler;
import utils.ScreenshotUtils;
import io.appium.java_client.android.AndroidDriver;
import java.nio.file.Path;

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

                if (scenario.isFailed()) {
                    AppiumDriver driver = DriverManager.getDriver();
                    Path screenshotPath =
                            ScreenshotUtils.takeScreenshot(driver, scenario.getName());

                    scenario.log("Screenshot saved at: " + screenshotPath);
                }
            }
        } finally {
            if (DriverManager.hasDriver()) {
                try {
                    AndroidDriver driver =
                            (AndroidDriver) DriverManager.getDriver();

                    driver.terminateApp("com.wizzair.WizzAirApp");

                } catch (Exception ignored) {
                }
            }
            DriverManager.quitDriver();
        }
    }
}