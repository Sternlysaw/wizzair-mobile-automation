package utils;

import core.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class PermissionHandler {

    private PermissionHandler() {}

    /**
     * Best-effort handler for first-launch permission dialogs on Android.
     * Safe to call even when no dialogs are present.
     */
    public static void handleAndroidFirstLaunchDialogs() {
        AppiumDriver driver = DriverManager.getDriver();
        if (!(driver instanceof AndroidDriver)) return;

        // Try a few times in case multiple permission dialogs appear sequentially
        for (int i = 0; i < 6; i++) {
            if (!tapIfPresentAny(driver)) {
                return; // nothing to handle -> stop early
            }
            sleep(350); // tiny pause to allow next dialog to render
        }
    }

    private static boolean tapIfPresentAny(AppiumDriver driver) {
        // System permissions (varies by Android version/OEM)
        if (tapByIdIfPresent(driver, "com.android.permissioncontroller:id/permission_allow_button")) return true;
        if (tapByIdIfPresent(driver, "com.android.permissioncontroller:id/permission_allow_foreground_only_button")) return true;
        if (tapByIdIfPresent(driver, "com.android.permissioncontroller:id/permission_allow_one_time_button")) return true;
        if (tapByIdIfPresent(driver, "com.android.permissioncontroller:id/permission_deny_button")) return true; // sometimes you want deny; keep for flexibility

        // Older package name on some images
        if (tapByIdIfPresent(driver, "com.android.packageinstaller:id/permission_allow_button")) return true;
        if (tapByIdIfPresent(driver, "com.android.packageinstaller:id/permission_allow_once_button")) return true;

        // Android 13+ notifications permission sometimes
        if (tapByTextIfPresent(driver, "Allow")) return true;
        if (tapByTextIfPresent(driver, "While using the app")) return true;
        if (tapByTextIfPresent(driver, "Only this time")) return true;
        if (tapByTextIfPresent(driver, "OK")) return true;

        // In-app “welcome / cookies / continue” style popups (best-effort)
        if (tapByTextIfPresent(driver, "Continue")) return true;
        if (tapByTextIfPresent(driver, "Accept")) return true;
        if (tapByTextIfPresent(driver, "I agree")) return true;
        if (tapByTextIfPresent(driver, "Got it")) return true;

        return false;
    }

    private static boolean tapByIdIfPresent(AppiumDriver driver, String id) {
        List<WebElement> els = driver.findElements(AppiumBy.id(id));
        if (!els.isEmpty()) {
            els.get(0).click();
            return true;
        }
        return false;
    }

    private static boolean tapByTextIfPresent(AppiumDriver driver, String text) {
        // XPath is OK here because it's a fallback for system dialogs and first-launch screens
        List<WebElement> els = driver.findElements(AppiumBy.xpath("//*[@text='" + escape(text) + "']"));
        if (!els.isEmpty()) {
            els.get(0).click();
            return true;
        }
        return false;
    }

    private static String escape(String s) {
        return s.replace("'", "\\'");
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}