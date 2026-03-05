package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import utils.AppiumHealthCheck;

import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class DriverManager {

    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        AppiumDriver d = driver.get();
        if (d == null) {
            throw new IllegalStateException("Driver is not initialized. Did @Before hook run?");
        }
        return d;
    }

    public static boolean hasDriver() {
        return driver.get() != null;
    }

    public static void initDriver() {
        if (hasDriver()) return;

        String platform = ConfigReader.getOptional("platform");
        if (platform == null) platform = "android";
        String appiumServer = ConfigReader.get("appiumServer");

        // keep your existing check (if you want), or remove if you don’t need it
        AppiumHealthCheck.verifyServerUp(appiumServer);

        try {
            URL serverUrl = URI.create(appiumServer).toURL();

            if ("android".equalsIgnoreCase(platform)) {
                UiAutomator2Options options = buildAndroidOptions();
                driver.set(new AndroidDriver(serverUrl, options));

            } else if ("ios".equalsIgnoreCase(platform)) {
                XCUITestOptions options = buildIosOptions();
                driver.set(new IOSDriver(serverUrl, options));

            } else {
                throw new RuntimeException("Unsupported platform: " + platform + " (use android or ios)");
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Appium driver for platform=" + platform, e);
        }
    }

    private static UiAutomator2Options buildAndroidOptions() {
        // Uses your existing keys from config.properties
        UiAutomator2Options options = new UiAutomator2Options();

        // recommended
        String deviceName = ConfigReader.getOptional("androidDeviceName");
        if (deviceName != null) options.setDeviceName(deviceName);

        String udid = ConfigReader.getOptional("androidUdid");
        if (udid != null) options.setUdid(udid);

        String appPath = ConfigReader.getOptional("androidApp"); // optional if you use installed app
        if (appPath != null) options.setApp(appPath);

        String appPackage = ConfigReader.getOptional("androidAppPackage");
        if (appPackage != null) options.setAppPackage(appPackage);

        String appActivity = ConfigReader.getOptional("androidAppActivity");
        if (appActivity != null) options.setAppActivity(appActivity);

        // common defaults
        options.setAutomationName("UiAutomator2");
        options.setAutoGrantPermissions(true);

        boolean noReset = ConfigReader.getBoolean("noReset", true);
        boolean fullReset = ConfigReader.getBoolean("fullReset", false);
        options.setNoReset(noReset);
        options.setFullReset(fullReset);

        String newCommandTimeout = ConfigReader.getOptional("newCommandTimeoutSeconds");
        if (newCommandTimeout != null) {
            options.setNewCommandTimeout(Duration.ofSeconds(Long.parseLong(newCommandTimeout)));
        }

        return options;
    }

    private static XCUITestOptions buildIosOptions() {
        XCUITestOptions options = new XCUITestOptions();

        // iOS config keys you should add in config.properties:
        // iosDeviceName, iosPlatformVersion, iosUdid (optional for simulator), iosBundleId OR iosApp
        String deviceName = ConfigReader.getOptional("iosDeviceName");
        if (deviceName != null) options.setDeviceName(deviceName);

        String platformVersion = ConfigReader.getOptional("iosPlatformVersion");
        if (platformVersion != null) options.setPlatformVersion(platformVersion);

        String udid = ConfigReader.getOptional("iosUdid");
        if (udid != null) options.setUdid(udid);

        String iosApp = ConfigReader.getOptional("iosApp"); // path to .app/.ipa if you use it
        if (iosApp != null) options.setApp(iosApp);

        String bundleId = ConfigReader.getOptional("iosBundleId"); // if app already installed
        if (bundleId != null) options.setBundleId(bundleId);

        options.setAutomationName("XCUITest");

        boolean noReset = ConfigReader.getBoolean("noReset", true);
        boolean fullReset = ConfigReader.getBoolean("fullReset", false);
        options.setNoReset(noReset);
        options.setFullReset(fullReset);

        // Optional but commonly helpful:
        // options.setWdaLaunchTimeout(Duration.ofSeconds(60));
        // options.setWdaConnectionTimeout(Duration.ofSeconds(60));

        String newCommandTimeout = ConfigReader.getOptional("newCommandTimeoutSeconds");
        if (newCommandTimeout != null) {
            options.setNewCommandTimeout(Duration.ofSeconds(Long.parseLong(newCommandTimeout)));
        }

        return options;
    }

    public static void quitDriver() {
        AppiumDriver d = driver.get();
        if (d != null) {
            try {
                d.quit();
            } catch (Exception ignored) {
            } finally {
                driver.remove();
            }
        }
    }
}