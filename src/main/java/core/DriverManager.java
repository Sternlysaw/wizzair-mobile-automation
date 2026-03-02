package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class DriverManager {

    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        try {
            String platform = ConfigReader.get("platform");

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", platform);
            caps.setCapability("deviceName", ConfigReader.get("deviceName"));

            if ("android".equalsIgnoreCase(platform)) {
                caps.setCapability("automationName", "UiAutomator2");
                caps.setCapability("appPackage", ConfigReader.get("appPackage"));
                caps.setCapability("appActivity", ConfigReader.get("appActivity"));

                driver.set(new AndroidDriver(
                        new URL(ConfigReader.get("appiumServer")),
                        caps
                ));
            } else {
                throw new RuntimeException("Only Android is wired for now. iOS will be added next.");
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