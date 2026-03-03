package hooks;

import core.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ScreenshotUtils;

import java.nio.file.Path;

public class Hooks {

    @Before
    public void beforeScenario() {
        DriverManager.initDriver();
    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                AppiumDriver driver = DriverManager.getDriver();
                Path screenshotPath = ScreenshotUtils.takeScreenshot(driver, scenario.getName());
                scenario.log("Screenshot saved: " + screenshotPath.toString());
            }
        } finally {
            DriverManager.quitDriver();
        }
    }
}