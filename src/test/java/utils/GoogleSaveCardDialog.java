package utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.util.List;

public class GoogleSaveCardDialog {

    private GoogleSaveCardDialog() {}

    // Broad marker: text that often appears on the save-card prompt
    private static final List<By> MARKERS = List.of(
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i).*save.*card.*\")"),
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i).*save.*to.*google.*\")"),
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i).*autofill.*\")")
    );

    // Prefer dismiss/negative actions first
    private static final List<By> DISMISS_BUTTONS = List.of(
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)later\")"),
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)not now\")"),
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)no thanks\")"),
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)never\")"),
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)cancel\")"),
            AppiumBy.androidUIAutomator("new UiSelector().textMatches(\"(?i)close\")"),
            AppiumBy.androidUIAutomator("new UiSelector().descriptionMatches(\"(?i)close\")")
    );

    public static void dismissIfPresent(AppiumDriver driver) {
        if (!looksPresent(driver)) return;

        for (By b : DISMISS_BUTTONS) {
            if (!driver.findElements(b).isEmpty()) {
                driver.findElement(b).click();
                return;
            }
        }

        // If present but we can't find a known dismiss button, back is a safe fallback
        try {
            driver.navigate().back();
        } catch (Exception ignored) {}
    }

    private static boolean looksPresent(AppiumDriver driver) {
        for (By m : MARKERS) {
            if (!driver.findElements(m).isEmpty()) return true;
        }
        return false;
    }
}