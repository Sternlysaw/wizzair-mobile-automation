package utils;

import core.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ScrollUtils {

    private ScrollUtils() {}

    /**
     * Android-only: scroll in a scrollable container until text is visible.
     * Uses UiScrollable which is reliable for long lists.
     */
    public static WebElement androidScrollToText(String text) {
        AppiumDriver driver = DriverManager.getDriver();

        if (!(driver instanceof AndroidDriver)) {
            throw new IllegalStateException("androidScrollToText can only be used with AndroidDriver");
        }

        String uiAutomator = "new UiScrollable(new UiSelector().scrollable(true))"
                + ".scrollIntoView(new UiSelector().textContains(\"" + escapeQuotes(text) + "\"))";

        return driver.findElement(AppiumBy.androidUIAutomator(uiAutomator));
    }

    private static String escapeQuotes(String s) {
        return s.replace("\"", "\\\"");
    }
}