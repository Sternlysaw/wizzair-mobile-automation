package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DriverManager {

    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        try {
            String platform = ConfigReader.get("platform");

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", platform);

            if ("android".equalsIgnoreCase(platform)) {

                caps.setCapability("deviceName", ConfigReader.get("androidDeviceName"));
                caps.setCapability("automationName", "UiAutomator2");
                caps.setCapability("appPackage", ConfigReader.get("androidAppPackage"));
                caps.setCapability("appActivity", ConfigReader.get("androidAppActivity"));

                driver.set(new AndroidDriver(
                        new java.net.URI(ConfigReader.get("appiumServer")).toURL(),
                        caps
                ));

            } else if ("ios".equalsIgnoreCase(platform)) {

                // iOS placeholder (requires macOS + Xcode)
                throw new RuntimeException(
                        "iOS execution requires macOS + Xcode (XCUITest / WebDriverAgent). " +
                                "Current environment is Windows-only."
                );

            } else {
                throw new RuntimeException(
                        "Unknown platform: " + platform +
                                " (use 'android' or 'ios' in config.properties)"
                );
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize driver", e);
        }
    }

    public static AppiumDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("Driver is null. Did you call DriverManager.initDriver()?");
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}