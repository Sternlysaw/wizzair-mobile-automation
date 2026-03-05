package hooks;

import core.ConfigReader;
import core.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.util.HashMap;
import java.util.Map;

public class Hooks {

    @Before
    public void beforeScenario() {
        DriverManager.initDriver();
    }

    @After
    public void afterScenario() {
        try {
            if (DriverManager.hasDriver()) {
                AppiumDriver driver = DriverManager.getDriver();

                String platform = ConfigReader.getOptional("platform");
                if (platform == null) platform = "android";
                platform = platform.trim().toLowerCase();

                // Use correct key per platform:
                // - iOS expects bundleId
                // - Android commonly uses appId (package)
                Map<String, Object> args = new HashMap<>();

                if ("ios".equals(platform)) {
                    String bundleId = ConfigReader.get("iosBundleId");
                    args.put("bundleId", bundleId);
                    driver.executeScript("mobile: terminateApp", args);
                    System.out.println("[HOOKS] Terminated iOS app: " + bundleId);
                } else {
                    String appId = ConfigReader.get("androidAppPackage");
                    args.put("appId", appId);
                    driver.executeScript("mobile: terminateApp", args);
                    System.out.println("[HOOKS] Terminated Android app: " + appId);
                }
            }
        } catch (Exception e) {
            System.out.println("[HOOKS] terminateApp failed (will still quit driver): " + e.getMessage());
        } finally {
            try {
                DriverManager.quitDriver();
                System.out.println("[HOOKS] Driver quit completed.");
            } catch (Exception e) {
                System.out.println("[HOOKS] Driver quit failed: " + e.getMessage());
            }
        }
    }
}