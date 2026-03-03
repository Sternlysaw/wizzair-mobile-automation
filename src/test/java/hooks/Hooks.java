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
            // Only attempt screenshots/logging if driver exists
            if (DriverManager.hasDriver()) {
                // later we can add screenshot-on-failure here safely
            }
        } finally {
            DriverManager.quitDriver();
        }
    }
}